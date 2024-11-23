package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.List;

public abstract class AbstractStore implements Store {

    private List<Food> foods;

    public AbstractStore(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public List<Food> buy(String name) {
        return foods.stream()
                .filter(food1 -> food1.getName().equals(name))
                .toList();
    }

    @Override
    public boolean addToShop(Food food) {
        return foods.add(food);
    }

    @Override
    public void deleteFromShop(Food food) {
        foods.remove(food);
    }

    @Override
    public List<Food> getAll() {
        return foods;
    }
}
