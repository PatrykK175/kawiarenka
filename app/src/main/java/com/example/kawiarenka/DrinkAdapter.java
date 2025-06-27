package com.example.kawiarenka;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    private final Context context;
    private final List<DrinkModel> drinkList;

    public DrinkAdapter(Context context, List<DrinkModel> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_drink, parent, false);
        return new DrinkViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, int position) {
        DrinkModel drink = drinkList.get(position);
        holder.name.setText(drink.getName());
        holder.description.setText(drink.getDescription());
        holder.price.setText(String.format("%.2f zł", drink.getPrice()));

        holder.itemView.setOnClickListener(v -> {
            OrderManager.getInstance().addDrink(drink);
            Toast.makeText(context, "Dodano: " + drink.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public static class DrinkViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, price;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.drinkName);
            description = itemView.findViewById(R.id.drinkDescription);
            price = itemView.findViewById(R.id.drinkPrice);
        }
    }
}