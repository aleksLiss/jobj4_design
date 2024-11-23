package ru.job4j.ood.lsp.products.food;

import java.util.Date;

public abstract class Food {
    String name;
    double price;
    int discount;
    Date expiryDate;
    Date createDate;

    public Food(String name, double price, int discount, Date expiryDate, Date createDate) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
    }

    public abstract String getName();

    public abstract double getPrice();

    public abstract int getDiscount();

    public abstract Date getExpiryDate();

    public abstract Date getCreateDate();

    @Override
    public String toString() {
        return "Food{"
                + "name='"
                + name
                + '\''
                + ", price="
                + price
                + ", discount="
                + discount
                + ", expiryDate="
                + expiryDate
                + ", createDate="
                + createDate
                + '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

