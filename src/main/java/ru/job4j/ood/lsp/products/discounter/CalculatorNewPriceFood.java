package ru.job4j.ood.lsp.products.discounter;

import ru.job4j.ood.lsp.products.food.Food;

public class CalculatorNewPriceFood implements CalculateDiscounter {
    @Override
    public double calculateNewPrice(Food food) {
        return food.getPrice() * (double) (100 - food.getDiscount()) / 100;
    }
}
