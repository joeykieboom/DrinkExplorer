package com.skiefboom.cocktailexplorerwithapi.controllers.suggestion.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skiefboom.cocktailexplorerwithapi.R;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink;

import java.util.List;

public class SuggestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static final int ITEM_TYPE_SUGGESTION = 0;
    public static final int ITEM_TYPE_SPINNER = 1;

    public List<Drink> suggestions;
    private LayoutInflater inflater;
    private boolean spinnerVisible;

    public SuggestionAdapter(Context context, List<Drink> suggestions) {

        inflater = LayoutInflater.from(context);
        this.suggestions = suggestions;
    }

    public void updateList(List<Drink> suggestions) {

        this.suggestions = suggestions;
        notifyDataSetChanged();
    }

    public void setSpinnerVisible(boolean visible) {

        spinnerVisible = visible;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_SUGGESTION) {

            View itemView = inflater.inflate(R.layout.listitem_main_cocktail, parent, false);
            return new CocktailViewHolder(itemView);
        } else {

            View itemView = inflater.inflate(R.layout.listitem_spinner, parent, false);
            return new SpinnerViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int itemType = getItemViewType(position);

        if (itemType == ITEM_TYPE_SUGGESTION) {

            CocktailViewHolder cocktailViewHolder = (CocktailViewHolder) holder;

            Drink suggestion = suggestions.get(position);
            cocktailViewHolder.cocktailName.setText(suggestion.name);
            cocktailViewHolder.cocktailDescription.setText(suggestion.descriptionPlain);
            String hasAlcohol = suggestion.isAlcoholic ? "Does have alcohol!" : "Does not have any alcohol!";
            cocktailViewHolder.cocktailSubtitle.setText(hasAlcohol);
        } else {

            SpinnerViewHolder viewHolder = (SpinnerViewHolder) holder;

            viewHolder.spinner.spin();
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position >= suggestions.size()) {

            return ITEM_TYPE_SPINNER;
        } else {

            return ITEM_TYPE_SUGGESTION;
        }
    }

    @Override
    public int getItemCount() {

        Integer notificationCount = suggestions != null ? suggestions.size() : 0;

        if(spinnerVisible) {
            notificationCount++;
        }

        return notificationCount;
    }
}


