package com.skiefboom.cocktailexplorerwithapi.controllers.drink;

import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.skiefboom.cocktailexplorerwithapi.R;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrinkActivityFragment extends Fragment {

    protected Drink drink;

    @BindView(R.id.fragment_drink_image_toolbar) ImageView toolbarImage;
//    @BindView(R.id.fragment_drink_fragment_header) Fragment fragmentHeader;

    public DrinkActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drink, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        int drinkId = getActivity().getIntent().getIntExtra(DrinkActivity.DRINK_ID, 0);

        drink = drinkId != 0 ? Drink.getById(drinkId) : null;

        Picasso.with(getContext()).load("http://assets.absolutdrinks.com/drinks/transparent-background-white/" + drink.drinkId + ".png").into(toolbarImage);

        Toast.makeText(getActivity(), drink.drinkId, Toast.LENGTH_SHORT).show();

        setHeaderInfo();
    }

    public void setHeaderInfo() {

//        FragmentDrinkHeader header = (FragmentDrinkHeader) fragmentHeader;
//        header.updateHeader(drink);
    }
}
