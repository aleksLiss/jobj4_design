package ru.job4j.ood.lsp.products.food;

import java.time.LocalDateTime;

public class Fruits extends Food {

    public Fruits(String name, double price, int discount, LocalDateTime createDate, LocalDateTime expiryDate) {
        super(name, price, discount, createDate, expiryDate);
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
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    @Override
    public LocalDateTime getCreateDate() {
        return createDate;
    }


}
