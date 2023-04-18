/*
Pataratanan Visitserngtrakul 20310367

DECLARATION
I hereby certify that no other part of this submission has been copied from any other sources, including the Internet, books or other student’s work except the ones I have listed below. No part of the code has been written/produced for me by another person or copied from any other source.
I hold a copy of this assignment that I can produce if the original is lost or damaged.
*/
package com.example.mypantry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class ViewPantry extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<PantryModal> pantryModalArrayList;
    private DatabaseHelper myDB;
    private PantryRVAdapter pantryRVAdapter;
    private RecyclerView pantriesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pantry);

        // initializing our all variables.
        pantryModalArrayList = new ArrayList<>();
        myDB = new DatabaseHelper(ViewPantry.this);

        // getting pantry array
        // list from db class.
        pantryModalArrayList = myDB.readPantry();

        // on below line passing our array lost to our adapter class.
        pantryRVAdapter = new PantryRVAdapter(pantryModalArrayList, ViewPantry.this);
        pantriesRV = findViewById(R.id.idRVPantries);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewPantry.this, RecyclerView.VERTICAL, false);
        pantriesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        pantriesRV.setAdapter(pantryRVAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.item1) {
            Intent intent = new Intent(ViewPantry.this, AddActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.item2) {
            Intent intent = new Intent(ViewPantry.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if(id == R.id.item3){
            Intent intent = new Intent(ViewPantry.this, ViewShopping.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}