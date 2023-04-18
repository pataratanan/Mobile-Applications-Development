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

public class AddActivity extends AppCompatActivity {

    private EditText etName, etPrice, etQuantity, etLocation;
    private Button btnAdd, btnView;
    private DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = (EditText) findViewById(R.id.etName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etQuantity = (EditText) findViewById(R.id.etQuantity);
        etLocation = (EditText) findViewById(R.id.etLocation);
        btnAdd = (Button) findViewById(R.id.add_button);
        btnView = (Button) findViewById(R.id.view_button);
        myDB = new DatabaseHelper(AddActivity.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // below line is to get data from all edit text fields.
                String pantryName = etName.getText().toString();
                String pantryPrice = etPrice.getText().toString();
                String pantryQuantity = etQuantity.getText().toString();
                String pantryLocation = etLocation.getText().toString();

                // validating if the text fields are empty or not.
                if (pantryName.isEmpty() && pantryPrice.isEmpty() && pantryQuantity.isEmpty() && pantryLocation.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                myDB.addNewPantry(pantryName, pantryPrice, pantryQuantity, pantryLocation);

                // after adding the data we are displaying a toast message.
                Toast.makeText(AddActivity.this, "Pantry has been added.", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etPrice.setText("");
                etQuantity.setText("");
                etLocation.setText("");
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // opening a new activity via a intent.
                Intent i = new Intent(AddActivity.this, ViewPantry.class);
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
            Intent intent = new Intent(AddActivity.this, AddActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.item2) {
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if(id == R.id.item3){
            Intent intent = new Intent(AddActivity.this, ViewShopping.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
