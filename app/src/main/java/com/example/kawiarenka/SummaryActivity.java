package com.example.kawiarenka;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {
    private EditText editName, editTable, editNotes;
    private TextView summaryList, totalPrice;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        summaryList = findViewById(R.id.summaryList);
        totalPrice = findViewById(R.id.totalPrice);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnSend = findViewById(R.id.btnSend);
        btnClear = findViewById(R.id.btnClear);

        editName = findViewById(R.id.editName);
        editTable = findViewById(R.id.editTable);
        editNotes = findViewById(R.id.editNotes);

        updateSummary();

        // Przywróć stan po odtworzeniu aktywności
        if (savedInstanceState != null) {
            editName.setText(savedInstanceState.getString("editName"));
            editTable.setText(savedInstanceState.getString("editTable"));
            editNotes.setText(savedInstanceState.getString("editNotes"));
            summaryList.setText(savedInstanceState.getString("summaryList"));
            totalPrice.setText(savedInstanceState.getString("totalPrice"));
        }

        btnBack.setOnClickListener(v -> finish());

        btnSend.setOnClickListener(v -> {
            String subject = "Zamówienie z aplikacji Kawiarenka";
            StringBuilder body = new StringBuilder();

            String name = editName.getText().toString().trim();
            String table = editTable.getText().toString().trim();
            String notes = editNotes.getText().toString().trim();

            if (!name.isEmpty()) body.append("Imię i nazwisko: ").append(name).append("\n");
            if (!table.isEmpty()) body.append("Numer stolika: ").append(table).append("\n");
            if (!notes.isEmpty()) body.append("Uwagi: ").append(notes).append("\n");
            if (body.length() > 0) body.append("\n");

            body.append(summaryList.getText().toString());
            body.append("\nSuma: ").append(String.format("%.2f zł", OrderManager.getInstance().getTotalPrice()));

            String mailTo = "mailto:Bananki@gmail.com"
                    + "?subject=" + Uri.encode(subject)
                    + "&body=" + Uri.encode(body.toString());

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse(mailTo));
            startActivity(Intent.createChooser(emailIntent, "Wyślij zamówienie e-mailem"));
        });

        btnClear.setOnClickListener(v -> {
            OrderManager.getInstance().clear();
            updateSummary();
            Toast.makeText(this, "Koszyk został wyczyszczony", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateSummary() {
        StringBuilder sb = new StringBuilder();
        for (DrinkModel d : OrderManager.getInstance().getDrinks()) {
            sb.append("Napoje: ").append(d.getName()).append(" - ").append(String.format("%.2f zł", d.getPrice())).append("\n");
        }
        for (Snack s : OrderManager.getInstance().getSnacks()) {
            sb.append("Przekąski: ").append(s.getName()).append(" - ").append(String.format("%.2f zł", s.getPrice())).append("\n");
        }
        summaryList.setText(sb.toString());
        totalPrice.setText("Suma: " + String.format("%.2f zł", OrderManager.getInstance().getTotalPrice()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("editName", editName.getText().toString());
        outState.putString("editTable", editTable.getText().toString());
        outState.putString("editNotes", editNotes.getText().toString());
        outState.putString("summaryList", summaryList.getText().toString());
        outState.putString("totalPrice", totalPrice.getText().toString());
    }
}