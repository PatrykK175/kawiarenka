package com.example.kawiarenka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SnackAdapter extends RecyclerView.Adapter<SnackAdapter.SnackViewHolder> {

    private final List<Snack> snackList;
    private final Context context;

    public SnackAdapter(Context context, List<Snack> snacks) {
        this.context = context;
        this.snackList = snacks;
    }

    @NonNull
    @Override
    public SnackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
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
            OrderManager.getInstance().addSnack(snack);
            Toast.makeText(context, "Dodano: " + snack.getName(), Toast.LENGTH_SHORT).show();
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