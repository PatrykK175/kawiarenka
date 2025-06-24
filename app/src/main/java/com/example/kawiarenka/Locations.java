package com.example.kawiarenka;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Locations extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.come_back);
        }

        RecyclerView rv = findViewById(R.id.recyclerViewDrinks);
        rv.setLayoutManager(new LinearLayoutManager(this));
        CafeLocationDatabaseHelper db = new CafeLocationDatabaseHelper(this);
        List<CafeLocation> cafeList = db.getAllCafeLocations();
        CafeLocationAdapter adapter = new CafeLocationAdapter(this, cafeList);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
