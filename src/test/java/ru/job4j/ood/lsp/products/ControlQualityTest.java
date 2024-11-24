package ru.job4j.ood.lsp.products;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.food.Meat;
import ru.job4j.ood.lsp.products.store.Shop;
import ru.job4j.ood.lsp.products.store.Store;
import ru.job4j.ood.lsp.products.store.Trash;
import ru.job4j.ood.lsp.products.store.Warehouse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenAddThreeProductsThenAddItIntoWarehouse() {
        LocalDateTime now = LocalDateTime.now();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Food beef = new Meat("beef", 34.1, 15, now.minusDays(2), now.plusDays(30));
        Food chicken = new Meat("chicken", 20.5, 5, now.minusDays(1), now.plusDays(40));
        Food pork = new Meat("pork", 29.4, 10, now.minusDays(3), now.plusDays(40));
        List<Food> foodList = List.of(beef, chicken, pork);
        Map<String, Store> storeMap = new HashMap<>();
        storeMap.put("warehouse", warehouse);
        storeMap.put("shop", shop);
        storeMap.put("trash", trash);
        ControlQuality control = new ControlQuality(storeMap);
        control.calculateQuality(foodList);
        assertThat(warehouse.getAll())
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder(beef, chicken, pork);
    }

    @Test
    public void whenAddThreeProductsThenAddItIntoShopAndNotCalculatePriceWithDiscount() {
        LocalDateTime now = LocalDateTime.now();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Food beef = new Meat("beef", 34.1, 15, now.minusDays(20), now.plusDays(20));
        Food chicken = new Meat("chicken", 20.5, 5, now.minusDays(30), now.plusDays(40));
        Food pork = new Meat("pork", 29.4, 10, now.minusDays(10), now.plusDays(30));
        List<Food> foodList = List.of(beef, chicken, pork);
        Map<String, Store> storeMap = new HashMap<>();
        storeMap.put("warehouse", warehouse);
        storeMap.put("shop", shop);
        storeMap.put("trash", trash);
        ControlQuality control = new ControlQuality(storeMap);
        control.calculateQuality(foodList);
        assertThat(shop.getAll())
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder(beef, chicken, pork);
    }

    @Test
    public void whenAddThreeProductsThenAddItIntoShopAndCalculateNewPriceWithDiscount() {
        LocalDateTime now = LocalDateTime.now();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Food beef = new Meat("beef", 34.1, 15, now.minusDays(30), now.plusDays(5));
        double expectedPrice = beef.getPrice() * (double) (100 - beef.getDiscount()) / 100;
        List<Food> foodList = List.of(beef);
        Map<String, Store> storeMap = new HashMap<>();
        storeMap.put("warehouse", warehouse);
        storeMap.put("shop", shop);
        storeMap.put("trash", trash);
        ControlQuality control = new ControlQuality(storeMap);
        control.calculateQuality(foodList);
        assertThat(shop.buy("beef").getFirst().getPrice()).isEqualTo(expectedPrice);
    }

    @Test
    public void whenAddThreeProductsThenAddItIntoTrash() {
        LocalDateTime now = LocalDateTime.now();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Food beef = new Meat("beef", 34.1, 15, now.minusDays(15), now.plusDays(0));
        Food chicken = new Meat("chicken", 20.5, 5, now.minusDays(20), now.plusDays(0));
        Food pork = new Meat("pork", 29.4, 10, now.minusDays(10), now.plusDays(0));
        List<Food> foodList = List.of(beef, chicken, pork);
        Map<String, Store> storeMap = new HashMap<>();
        storeMap.put("warehouse", warehouse);
        storeMap.put("shop", shop);
        storeMap.put("trash", trash);
        ControlQuality control = new ControlQuality(storeMap);
        control.calculateQuality(foodList);
        assertThat(trash.getAll())
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder(chicken, beef, pork);
    }

    @Test
    public void whenAddThreeProductsThenAddEveryProductIntoEachShops() {
        LocalDateTime now = LocalDateTime.now();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Food beef = new Meat("beef", 34.1, 15, now.minusDays(5), now.plusDays(30));
        Food chicken = new Meat("chicken", 20.5, 5, now.minusDays(20), now.plusDays(5));
        Food pork = new Meat("pork", 29.4, 10, now.minusDays(15), now.plusDays(0));
        List<Food> foodList = List.of(beef, chicken, pork);
        Map<String, Store> storeMap = new HashMap<>();
        storeMap.put("warehouse", warehouse);
        storeMap.put("shop", shop);
        storeMap.put("trash", trash);
        ControlQuality control = new ControlQuality(storeMap);
        control.calculateQuality(foodList);
        assertThat(warehouse.getAll()).isNotEmpty().hasSize(1).containsExactlyInAnyOrder(beef);
    }
}