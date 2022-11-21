package com.magers.candystore;

public class Candy {
    private int id;
    private String name;
    private double price;

    public Candy (int newId, String newName, double newPrice) {
        setId(newId);
        setName(newName);
        setPrice(newPrice);
    }

    public void setId(int newId) {
        id = newId;
    }

    public void setName (String newName) {
        name = newName;
    }

    public void setPrice(double newPrice) {
        if (newPrice >= 0.0) //check to ensure legit price
            price = newPrice;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return id + "; " + name + "; " + price;
    }
}
