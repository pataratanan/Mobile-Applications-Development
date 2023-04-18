/*
Pataratanan Visitserngtrakul 20310367

DECLARATION
I hereby certify that no other part of this submission has been copied from any other sources, including the Internet, books or other student’s work except the ones I have listed below. No part of the code has been written/produced for me by another person or copied from any other source.
I hold a copy of this assignment that I can produce if the original is lost or damaged.
*/
package com.example.mypantry;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PantryRVAdapter extends RecyclerView.Adapter<PantryRVAdapter.ViewHolder>{
    // variable for our array list and context
    private ArrayList<PantryModal> pantryModalArrayList;
    private Context context;

    // constructor
    public PantryRVAdapter(ArrayList<PantryModal> pantryModalArrayList, Context context) {
        this.pantryModalArrayList = pantryModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pantry_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        PantryModal modal = pantryModalArrayList.get(position);
        holder.pantryNameTV.setText(modal.getPantryName());
        holder.pantryPriceTV.setText(modal.getPantryPrice());
        holder.pantryQuantityTV.setText(modal.getPantryQuantity());
        holder.pantryLocationTV.setText(modal.getPantryLocation());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdatePantryActivity.class);

                // below we are passing all our values.
                i.putExtra("name", modal.getPantryName());
                i.putExtra("price", modal.getPantryPrice());
                i.putExtra("quantity", modal.getPantryQuantity());
                i.putExtra("location", modal.getPantryLocation());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return pantryModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView pantryNameTV, pantryPriceTV, pantryQuantityTV, pantryLocationTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            pantryNameTV = itemView.findViewById(R.id.idTVPantryName);
            pantryPriceTV = itemView.findViewById(R.id.idTVPantryPrice);
            pantryQuantityTV = itemView.findViewById(R.id.idTVPantryQuantity);
            pantryLocationTV = itemView.findViewById(R.id.idTVPantryLocation);
        }
    }
}
