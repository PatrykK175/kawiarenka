package com.example.kawiarenka;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SnacksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SnackAdapter snackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recycler_snacks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SnackDatabaseHelper dbHelper = new SnackDatabaseHelper(this);
        List<Snack> snacks = dbHelper.getAllSnacks();
        snackAdapter = new SnackAdapter(snacks);
        recyclerView.setAdapter(snackAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
