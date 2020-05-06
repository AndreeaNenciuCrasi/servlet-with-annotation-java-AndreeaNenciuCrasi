package com.codecool.servlet;

import java.util.HashSet;
import java.util.Set;

public class Stock {
    private Set<Item> stockList;

    public Stock(Set<Item> initialStock) {
        this.stockList = new HashSet<>();
        this.stockList.addAll(initialStock);
    }

    public boolean addItemInStock(Item item){
        return this.stockList.add(item);
    }

    public boolean removeItemFromStock(Item item){
        if(stockList.contains(item)){
            this.stockList.remove(item);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Items in Stock{" +
                "stockList=" + stockList +
                '}';
    }
}
