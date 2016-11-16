package com.skiefboom.cocktailexplorerwithapi.controllers.suggestion;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.skiefboom.cocktailexplorerwithapi.R;
import com.skiefboom.cocktailexplorerwithapi.controllers.drink.DrinkActivity;
import com.skiefboom.cocktailexplorerwithapi.controllers.suggestion.data.SuggestionAdapter;
import com.skiefboom.cocktailexplorerwithapi.data.api.Api;
import com.skiefboom.cocktailexplorerwithapi.data.api.ApiCallback;
import com.skiefboom.cocktailexplorerwithapi.data.api.responses.DrinkListResponse;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink;
import com.skiefboom.cocktailexplorerwithapi.helpers.PersistentHelper;
import com.skiefboom.cocktailexplorerwithapi.listeners.EndlessRecyclerViewScrollListener;
import com.skiefboom.cocktailexplorerwithapi.listeners.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class SuggestionActivityFragment extends Fragment {

    @BindView(R.id.fragment_suggestion_recycler_view) RecyclerView suggestionList;

    protected EndlessRecyclerViewScrollListener scrollListener;
    protected SuggestionAdapter suggestionAdapter;
    protected LinearLayoutManager layoutManager;
    protected List<Drink> suggestions;
    protected boolean spinnerAdded = false;

    public SuggestionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_suggestion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        suggestions = SQLite.select().from(Drink.class).queryList();
        suggestionAdapter = new SuggestionAdapter(getContext(), suggestions);
        layoutManager = new LinearLayoutManager(getContext());
        suggestionList.setLayoutManager(layoutManager);
        suggestionList.setAdapter(suggestionAdapter);

        int count = PersistentHelper.getDrinkCount();

        if (count <= 0) {
            getDrinks(0);
        }

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                if(!spinnerAdded) {

                    suggestionAdapter.setSpinnerVisible(true);
                    spinnerAdded = true;
                }

                int count = PersistentHelper.getDrinkCount();

                getDrinks(count + 11);
            }
        };

        suggestionList.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                final Drink drink = suggestionAdapter.suggestions.get(position);

                if (drink != null) {

                    Intent intent = new Intent(getContext(), DrinkActivity.class);
                    intent.putExtra(DrinkActivity.DRINK_ID, drink.id);
                    startActivity(intent);
                }
            }
        }));

        suggestionList.addOnScrollListener(scrollListener);
    }

    public void getDrinks(final Integer fromCount) {

        HashMap<String, String> attrs = new HashMap<>();
        attrs.put("start", String.valueOf(fromCount));
        attrs.put("pageSize", String.valueOf(10));

        Api.drinks.getDrinks(attrs).enqueue(new ApiCallback<DrinkListResponse>() {
            @Override
            public void onOK(Call<DrinkListResponse> call, Response<DrinkListResponse> response) {

                if(spinnerAdded) {
                    suggestionAdapter.setSpinnerVisible(false);
                    spinnerAdded = false;
                }

                PersistentHelper.setDrinkCount(fromCount);
                refreshData();
            }

            @Override
            public void onError(Call<DrinkListResponse> call, Throwable t) {

            }
        });
    }

    private void refreshData() {

        suggestions = new ArrayList<>();
        suggestions.addAll(SQLite.select().from(Drink.class).where().queryList());
        suggestionAdapter.updateList(suggestions);
    }
}
