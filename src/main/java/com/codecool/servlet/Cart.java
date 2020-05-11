package com.codecool.servlet;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> myItems;

    public Cart() {
        this.myItems = new ArrayList<Item>();
    }

    public ArrayList<Item> getMyItems() {
        return myItems;
    }

    public boolean addNewItem(Item item) {
        if (findItem(item.getName()) >= 0) {
            System.out.println("Item is already in file.");
            return false;
        }
        myItems.add(item);
        return true;
    }

    public boolean removeItem(Item item) {
        int foundPosition = findItem(item);
        if (foundPosition < 0) {
            System.out.println(item.getName() + ", was not found.");
            return false;
        }
        this.myItems.remove(foundPosition);
        System.out.println(item.getName() + ", was deleted.");
        return true;
    }

    public boolean updateItem(Item oldItem, Item newItem) {
        int foundPosition = findItem(oldItem);
        if (foundPosition < 0) {
            System.out.println(oldItem.getName() + ", was not found");
            return false;
        }else if(findItem(newItem.getName()) != -1){
            System.out.println("Contact with name " + newItem.getName() + " already exists. Update was not successful.");
            return false;
        }
        this.myItems.set(foundPosition, newItem);
        System.out.println(oldItem.getName() + ", was replaced with " + newItem.getName());
        return true;
    }

    private int findItem(String itemName) {
        for (int i = 0; i < this.myItems.size(); i++) {
            Item item = this.myItems.get(i);
            if (item.getName().equals(itemName)) {
                return i;
            }
        }
        return -1;
    }

    private int findItem(Item item) {
        return this.myItems.indexOf(item);
    }

//    private double cartItemsSum(){
//        int sum =0;
//        for(Item item: this.myItems){
//            sum +=item.getPrice();
//        }
//        return sum;
//    }

    @Override
    public String toString() {
        return "Cart{" +
                "myItems=" + myItems +
                '}';
    }
}
