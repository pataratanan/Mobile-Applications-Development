/*
Pataratanan Visitserngtrakul 20310367

DECLARATION
I hereby certify that no other part of this submission has been copied from any other sources, including the Internet, books or other student’s work except the ones I have listed below. No part of the code has been written/produced for me by another person or copied from any other source.
I hold a copy of this assignment that I can produce if the original is lost or damaged.
*/
package com.example.mypantry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "new_pantry.db";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "new_pantry";

    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String PRICE_COL = "price";

    private static final String QUANTITY_COL = "quantity";

    private static final String LOCATION_COL = "location";
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COL + "  TEXT, " +
                PRICE_COL + " TEXT, " +
                QUANTITY_COL + " TEXT, " +
                LOCATION_COL + " TEXT);";
        db.execSQL(query);
    }

    public void addNewPantry(String pantryName, String pantryPrice, String pantryQuantity, String pantryLocation) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, pantryName);
        values.put(PRICE_COL, pantryPrice);
        values.put(QUANTITY_COL, pantryQuantity);
        values.put(LOCATION_COL, pantryLocation);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the pantry.
    public ArrayList<PantryModal> readPantry() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorPantry = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<PantryModal> pantryModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorPantry.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                pantryModalArrayList.add(new PantryModal(cursorPantry.getString(1),
                        cursorPantry.getString(2),
                        cursorPantry.getString(3),
                        cursorPantry.getString(4)));
            } while (cursorPantry.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorPantry.close();
        return pantryModalArrayList;
    }

    // below is the method for updating pantry
    public void updatePantry(String originalPantryName,
                             String pantryName,
                             String pantryPrice,
                             String pantryQuantity,
                             String pantryLocation) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, pantryName);
        values.put(PRICE_COL, pantryPrice);
        values.put(QUANTITY_COL, pantryQuantity);
        values.put(LOCATION_COL, pantryLocation);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalPantryName});
        db.close();
    }

    // below is the method for updating pantry
    public void addShopping(String originalPantryName,
                             String pantryName,
                             String pantryPrice,
                             String pantryQuantity,
                             String pantryLocation) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, pantryName);
        values.put(PRICE_COL, pantryPrice);
        values.put(QUANTITY_COL, pantryQuantity);
        values.put(LOCATION_COL, pantryLocation);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalPantryName});
        db.close();
    }

    // below is the method for deleting pantry.
    public void deletePantry(String pantryName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete pantry
        // and we are comparing it with pantry name.
        db.delete(TABLE_NAME, "name=?", new String[]{pantryName});
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
