package ru.job4j.ood.lsp.products.food;

import java.util.Date;

public class Meat extends Food {

    public Meat(String name, double price, int discount, Date expiryDate, Date createDate) {
        super(name, price, discount, expiryDate, createDate);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getDiscount() {
        return discount;
    }

    @Override
    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }
}
