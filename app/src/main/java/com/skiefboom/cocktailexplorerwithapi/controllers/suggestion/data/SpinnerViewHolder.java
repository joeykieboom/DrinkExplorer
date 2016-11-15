package com.skiefboom.cocktailexplorerwithapi.controllers.suggestion.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.skiefboom.cocktailexplorerwithapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpinnerViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.listitem_spinner)
    ProgressWheel spinner;

    public SpinnerViewHolder(View view) {

        super(view);
        ButterKnife.bind(this, view);
    }
}
