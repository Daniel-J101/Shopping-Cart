package models;

public class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        if(name == null || name.isEmpty() || price < 0)
            throw new IllegalArgumentException("Invalid parameters");
        this.name = name;
        this.price = price;
    }

    public Item(Item source) {
        this.name = source.name;
        this.price = source.price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null  || name.isEmpty())
            throw new IllegalArgumentException("Invalid parameters");
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price < 0)
            throw new IllegalArgumentException("Invalid parameters");
        this.price = price;
    }

    public String toString() {
        return name + ": $" + price + " ";
    }

    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Item))
            return false;
        Item check = (Item) obj;
        return check.name.equals(name) && check.price == this.price;
    }
}
