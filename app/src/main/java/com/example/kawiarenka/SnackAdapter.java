package com.example.kawiarenka;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SnackAdapter extends RecyclerView.Adapter<SnackAdapter.SnackViewHolder> {

    private List<Snack> snackList;

    public SnackAdapter(List<Snack> snacks) {
        this.snackList = snacks;
    }

    @NonNull
    @Override
    public SnackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.snack_item, parent, false);
        return new SnackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SnackViewHolder holder, int position) {
        Snack snack = snackList.get(position);
        holder.name.setText(snack.getName());
        holder.description.setText(snack.getDescription());
        holder.price.setText(String.format("%.2f zł", snack.getPrice()));

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Dodano: " + snack.getName(), Toast.LENGTH_SHORT).show();
            // Dodaj przekąskę do zamówienia tutaj, jeśli masz system zamówień
        });
    }

    @Override
    public int getItemCount() {
        return snackList.size();
    }

    public static class SnackViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, price;

        public SnackViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_snack_name);
            description = itemView.findViewById(R.id.text_snack_description);
            price = itemView.findViewById(R.id.text_snack_price);
        }
    }
}
