package com.example.kawiarenka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView summaryList = findViewById(R.id.summaryList);
        TextView totalPrice = findViewById(R.id.totalPrice);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnSend = findViewById(R.id.btnSend);

        StringBuilder sb = new StringBuilder();
        for (DrinkModel d : OrderManager.getInstance().getDrinks()) {
            sb.append("Napoje: ").append(d.getName()).append(" - ").append(String.format("%.2f zł", d.getPrice())).append("\n");
        }
        for (Snack s : OrderManager.getInstance().getSnacks()) {
            sb.append("Przekąski: ").append(s.getName()).append(" - ").append(String.format("%.2f zł", s.getPrice())).append("\n");
        }
        summaryList.setText(sb.toString());
        totalPrice.setText("Suma: " + String.format("%.2f zł", OrderManager.getInstance().getTotalPrice()));

        btnBack.setOnClickListener(v -> finish());

        btnSend.setOnClickListener(v -> {
            String subject = "Zamówienie z aplikacji Kawiarenka";
            String body = sb.toString() + "\nSuma: " + String.format("%.2f zł", OrderManager.getInstance().getTotalPrice());
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(emailIntent, "Wyślij e-mail..."));
        });
    }
}