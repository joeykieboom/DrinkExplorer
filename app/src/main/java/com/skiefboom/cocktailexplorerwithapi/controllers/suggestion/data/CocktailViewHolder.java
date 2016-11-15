package com.skiefboom.cocktailexplorerwithapi.controllers.suggestion.data;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.skiefboom.cocktailexplorerwithapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CocktailViewHolder extends RecyclerView.ViewHolder {

    @Nullable
    @BindView(R.id.cocktail_name) TextView cocktailName;
    @BindView(R.id.cocktail_description) TextView cocktailDescription;
    @BindView(R.id.cocktail_subtitle) TextView cocktailSubtitle;

    public CocktailViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
