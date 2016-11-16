package com.skiefboom.cocktailexplorerwithapi.controllers.main;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.skiefboom.cocktailexplorerwithapi.R;
import com.skiefboom.cocktailexplorerwithapi.controllers.suggestion.SuggestionActivity;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink_Table;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;
    @BindView(R.id.activity_main_collapsing_layout) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.activity_main_fab) FloatingActionButton fab;

    @BindView(R.id.activity_main_placeholder) LinearLayout viewPlaceHolder;
    @BindView(R.id.activity_main_placeholder_btn) Button btnPlaceHolder;
    @BindView(R.id.activity_main_recycler_view) RecyclerView cocktailRV;
    @BindView(R.id.activity_main_swipe_refresh) SwipeRefreshLayout refreshLayout;

    protected List<Drink> drinks;
    protected DrinkAdapter drinkAdapter;
    protected LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        collapsingToolbarLayout.setTitle(getString(R.string.app_name));
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);

        drinks = SQLite.select().from(Drink.class).where(Drink_Table.saved.eq(true)).queryList();
        drinkAdapter = new DrinkAdapter(drinks);

        layoutManager = new LinearLayoutManager(getBaseContext());
        cocktailRV.setLayoutManager(layoutManager);
        cocktailRV.setAdapter(drinkAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), SuggestionActivity.class);
                startActivity(intent);
            }
        });

        if (drinks.size() > 1) {
            viewPlaceHolder.setVisibility(GONE);
            cocktailRV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRefresh() {

//        getDrinks(0);
    }

    private class DrinkAdapter extends RecyclerView.Adapter<ViewHolder> {

        protected List<Drink> drinks;

        public DrinkAdapter(List<Drink> drinks) {

            this.drinks = drinks;
        }

        public void updateList(List<Drink> drinks) {
            this.drinks = drinks;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = getLayoutInflater().inflate(R.layout.listitem_main_cocktail, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            Drink drink = drinks.get(position);

            holder.cocktailName.setText(drink.name);
        }

        @Override
        public int getItemCount() {

            if(drinks == null){
                return 0;
            }

            return drinks.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cocktail_name) TextView cocktailName;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
