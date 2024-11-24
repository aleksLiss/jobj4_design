package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.List;

public interface Store {

    List<Food> buy(String name);

    void addToShop(Food food);

    void deleteFromShop(Food food);

    List<Food> getAll();
}
