package com.skiefboom.cocktailexplorerwithapi.controllers.drink;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skiefboom.cocktailexplorerwithapi.R;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDrinkHeader extends Fragment {

    @BindView(R.id.fragment_drink_header_cocktail_name) TextView cocktailName;
    @BindView(R.id.fragment_drink_header_cocktail_subtitle) TextView cocktailSubtitle;
    @BindView(R.id.fragment_drink_header_circle_image) CircleImageView cocktailImage;

    public FragmentDrinkHeader() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drink_header, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);


    }

    public void updateHeader(Drink drink) {

        cocktailName.setText(drink.name);
    }
}
