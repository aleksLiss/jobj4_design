package ru.job4j.ood.lsp.products.discounter;

import ru.job4j.ood.lsp.products.food.Food;

public interface CalculateDiscounter {

    double calculateNewPrice(Food food);
}
