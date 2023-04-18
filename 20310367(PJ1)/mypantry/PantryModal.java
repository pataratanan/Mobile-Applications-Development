/*
Pataratanan Visitserngtrakul 20310367

DECLARATION
I hereby certify that no other part of this submission has been copied from any other sources, including the Internet, books or other student’s work except the ones I have listed below. No part of the code has been written/produced for me by another person or copied from any other source.
I hold a copy of this assignment that I can produce if the original is lost or damaged.
*/
package com.example.mypantry;

public class PantryModal {
    // variables for pantry,
    // name, price, quantity, location and id.
    private String pantryName;
    private String pantryPrice;
    private String pantryQuantity;
    private String pantryLocation;
    private int id;

    // creating getter and setter methods
    public String getPantryName() {
        return pantryName;
    }

    public void setPantryName(String pantryName) {
        this.pantryName = pantryName;
    }

    public String getPantryPrice() {
        return pantryPrice;
    }

    public void setPantryPrice(String pantryPrice) {
        this.pantryPrice = pantryPrice;
    }

    public String getPantryQuantity() {
        return pantryQuantity;
    }

    public void setPantryQuantity(String pantryQuantity) {
        this.pantryQuantity = pantryQuantity;
    }

    public String getPantryLocation() {
        return pantryLocation;
    }

    public void setPantryLocation(String pantryLocation) {
        this.pantryLocation = pantryLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public PantryModal(String pantryName, String pantryPrice, String pantryQuantity, String pantryLocation) {
        this.pantryName = pantryName;
        this.pantryPrice = pantryPrice;
        this.pantryQuantity = pantryQuantity;
        this.pantryLocation = pantryLocation;
    }
}
