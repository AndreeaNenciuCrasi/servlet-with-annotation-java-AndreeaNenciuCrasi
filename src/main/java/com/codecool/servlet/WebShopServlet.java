package com.codecool.servlet;

import java.util.HashSet;
import java.util.Set;

public class WebShopServlet {
    private Stock shopStock;
    private Cart cart = new Cart();

    public void initStock(){
        Set<Item> itemsStock = new HashSet<>();

        itemsStock.add(new Item("Nivea Creme", 10.00));
        itemsStock.add(new Item("Name of the rose", 45.00));
        itemsStock.add(new Item("Phone SAMSUNG Galaxy", 700.00));
        itemsStock.add(new Item("Night Dress", 899.00));
        itemsStock.add(new Item("Gaming Headset", 170.00));

        this.shopStock = new Stock(itemsStock);
    }

}
