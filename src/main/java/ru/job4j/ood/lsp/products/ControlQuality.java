package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.discounter.CalculateDiscounter;
import ru.job4j.ood.lsp.products.discounter.CalculatorNewPriceFood;
import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.store.Store;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class ControlQuality {

    private final Map<String, Store> storeMap;
    private final CalculateDiscounter calculateDiscounter;

    public ControlQuality(Map<String, Store> storeMap) {
        this.storeMap = storeMap;
        this.calculateDiscounter = new CalculatorNewPriceFood();
    }

    public void calculateQuality(List<Food> foods) {
        for (Food food : foods) {
            long diff = calculateDiff(food);
            if (inRange(diff, 0, 24)) {
                storeMap.get("warehouse").addToShop(food);
            }
            if (inRange(diff, 25, 75)) {
                storeMap.get("shop").addToShop(food);
            }
            if (inRange(diff, 75, 99)) {
                food.setPrice(calculateDiscounter.calculateNewPrice(food));
                storeMap.get("shop").addToShop(food);
            }
            if (inRange(diff, 100, 100)) {
                storeMap.get("trash").addToShop(food);
            }
        }
    }

    private boolean inRange(long num, long min, long max) {
        return num >= min && num <= max;
    }

    private long calculateDiff(Food food) {
        LocalDateTime start = food.getCreateDate();
        LocalDateTime finish = food.getExpiryDate();
        LocalDateTime now = LocalDateTime.now();
        long df = ChronoUnit.SECONDS.between(start, now);
        long all = ChronoUnit.SECONDS.between(start, finish);
        return Math.abs((df * 100) / all);
    }
}
