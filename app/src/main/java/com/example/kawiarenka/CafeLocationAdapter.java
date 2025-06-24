package com.example.kawiarenka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CafeLocationAdapter
        extends RecyclerView.Adapter<CafeLocationAdapter.ViewHolder> {

    private final List<CafeLocation> locations;
    private final LayoutInflater     inflater;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, addressText, hoursText;
        ViewHolder(View itemView) {
            super(itemView);
            nameText    = itemView.findViewById(R.id.textName);
            addressText = itemView.findViewById(R.id.textAddress);
            hoursText   = itemView.findViewById(R.id.textHours);
        }
    }

    public CafeLocationAdapter(Context ctx, List<CafeLocation> data) {
        this.inflater   = LayoutInflater.from(ctx);
        this.locations  = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cafe_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int pos) {
        CafeLocation c = locations.get(pos);
        h.nameText   .setText(c.getName());
        h.addressText.setText(c.getAddress());
        h.hoursText  .setText(c.getOpeningHours());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
}
