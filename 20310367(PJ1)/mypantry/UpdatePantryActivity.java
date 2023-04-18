/*
Pataratanan Visitserngtrakul 20310367

DECLARATION
I hereby certify that no other part of this submission has been copied from any other sources, including the Internet, books or other student’s work except the ones I have listed below. No part of the code has been written/produced for me by another person or copied from any other source.
I hold a copy of this assignment that I can produce if the original is lost or damaged.
*/
package com.example.mypantry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePantryActivity extends AppCompatActivity {

    private EditText etName,etPrice,etQuantity,etLocation;
    private Button btnUpdate,btnDelete,btnSaveToList;
    private DatabaseHelper myDB;
    String pantryName, pantryPrice, pantryQuantity, pantryLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pantry);

        // initializing all our variables.
        etName = (EditText)findViewById(R.id.etName);
        etPrice = (EditText)findViewById(R.id.etPrice);
        etQuantity = (EditText)findViewById(R.id.etQuantity);
        etLocation = (EditText)findViewById(R.id.etLocation);
        btnUpdate = (Button)findViewById(R.id.update_button);
        btnDelete = (Button)findViewById(R.id.delete_button);
        btnSaveToList = (Button)findViewById(R.id.saveToList_button);
        // on below line we are initialing our db class.
        myDB = new DatabaseHelper(UpdatePantryActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        pantryName = getIntent().getStringExtra("name");
        pantryPrice = getIntent().getStringExtra("price");
        pantryQuantity = getIntent().getStringExtra("quantity");
        pantryLocation = getIntent().getStringExtra("location");

        // setting data to edit text
        // of our update activity.
        etName.setText(pantryName);
        etPrice.setText(pantryPrice);
        etQuantity.setText(pantryQuantity);
        etLocation.setText(pantryLocation);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.updatePantry(pantryName, etName.getText().toString(),
                        etPrice.getText().toString(),
                        etQuantity.getText().toString(),
                        etLocation.getText().toString());

                // displaying a toast message that pantry has been updated.
                Toast.makeText(UpdatePantryActivity.this, "Pantry Updated..", Toast.LENGTH_SHORT).show();

                // launching add activity.
                Intent i = new Intent(UpdatePantryActivity.this, ViewPantry.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deletePantry(pantryName);
                Toast.makeText(UpdatePantryActivity.this, "Deleted the pantry", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdatePantryActivity.this, ViewPantry.class);
                startActivity(i);
            }
        });

        btnSaveToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.addShopping(pantryName, etName.getText().toString(),
                        etPrice.getText().toString(),
                        etQuantity.getText().toString(),
                        etLocation.getText().toString());

                // displaying a toast message that pantry has been added to shopping list item.
                Toast.makeText(UpdatePantryActivity.this, "Added to Shopping List Item..", Toast.LENGTH_SHORT).show();

                // launching add activity.
                Intent i = new Intent(UpdatePantryActivity.this, ViewShopping.class);
                startActivity(i);
            }
        });
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
            Intent intent = new Intent(UpdatePantryActivity.this, AddActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.item2) {
            Intent intent = new Intent(UpdatePantryActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if(id == R.id.item3){
            Intent intent = new Intent(UpdatePantryActivity.this, ViewShopping.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}