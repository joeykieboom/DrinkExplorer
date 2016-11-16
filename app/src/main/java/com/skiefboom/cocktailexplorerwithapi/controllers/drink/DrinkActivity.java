package com.skiefboom.cocktailexplorerwithapi.controllers.drink;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.skiefboom.cocktailexplorerwithapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrinkActivity extends AppCompatActivity {

    public static final String DRINK_ID = "drinkId";

    @BindView(R.id.fragment_drink_toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

}
