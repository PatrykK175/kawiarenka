package com.example.kawiarenka;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Drink extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DrinkAdapter adapter;
    private DrinkDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.come_back);
        }

        recyclerView = findViewById(R.id.recyclerViewDrinks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DrinkDatabaseHelper(this);
        List<DrinkModel> drinks = databaseHelper.getAllDrinks();

        adapter = new DrinkAdapter(this, drinks);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}