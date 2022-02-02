package models;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public void setItem(int index, Item item) {
        items.set(index, new Item(item));
    }




    /**
     * Name: add
     * @param item
     * @return boolean
     *
     * Inside the function:
     *   1. Adds an item to the cart if it wasn't already added.
     */

    public boolean add(Item item) {
        if(items.contains(item))
            return false;

        items.add(new Item(item));
        return true;
    }

    /**
     * Name: remove
     * @param name
     *
     * Inside the function:
     *   1. Removes the item that matches the name passed in.
     */

    public void remove(String name) {
        if(items.isEmpty())
            throw new IllegalStateException("items cannot be empty");
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getName().equals(name))
                items.remove(i);
        }
    }

    /**
     *  Name: checkout
     *  @return (String)
     *
     *  Inside the function:
     *   1. Calculates the subtotal (price before tax).
     *   2. Calculates the tax (assume tax is 13%).
     *   3. Calculates total: subtotal + tax
     *   4. Returns a String that resembles a receipt. See below.
     */

    public String checkout() {
        if(items.isEmpty())
            throw new IllegalStateException("items cannot be empty");

        double subtotal = 0;

        for (Item item : items) {
            subtotal += item.getPrice();
        }
        double tax = subtotal*.13;

        return "\tRECEIPT\n\n" + "\tSubtotal: $" + String.format("%.2f", subtotal) + "\n" + "\tTax: $" + String.format("%.2f", tax) + "\n" + "\tTotal: $" + String.format("%.2f", (subtotal + tax)) + "\n";
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public String toString() {
        String temp = "";

        for (Item item : items) {
            temp += item + "\n";
        }
        return temp;
    }

}