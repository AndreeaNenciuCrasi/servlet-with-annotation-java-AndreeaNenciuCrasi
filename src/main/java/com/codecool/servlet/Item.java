package com.codecool.servlet;

public class Item {
    private static int count = 0;
    private int id=0;
    private String name;
    private Double price;

    public Item(String name, Double price) {
        this.id = count++;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
