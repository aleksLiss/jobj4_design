package ru.job4j.ood.lsp.products;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.food.Meat;
import ru.job4j.ood.lsp.products.formatt.DateFormatter;
import ru.job4j.ood.lsp.products.formatt.FoodsDateFormatter;
import ru.job4j.ood.lsp.products.store.Shop;
import ru.job4j.ood.lsp.products.store.Store;
import ru.job4j.ood.lsp.products.store.Trash;
import ru.job4j.ood.lsp.products.store.Warehouse;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenAddThreeProductsThenAddItIntoWarehouse() {
        DateFormatter sdf = new FoodsDateFormatter();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        List<Store> storeList = List.of(warehouse, shop, trash);
        Food beef = new Meat("beef", 34.1, 15, sdf.parse("2025/01/15"), sdf.parse("2024/11/10"));
        Food chicken = new Meat("chicken", 20.5, 5, sdf.parse("2025/01/20"), sdf.parse("2024/11/10"));
        Food pork = new Meat("pork", 29.4, 10, sdf.parse("2025/01/30"), sdf.parse("2024/11/10"));
        List<Food> foodList = List.of(beef, chicken, pork);
        ControlQuality control = new ControlQuality(storeList);
        control.calculateQuality(foodList);
        assertThat(warehouse.getAll())
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder(beef, chicken, pork);
    }

    @Test
    public void whenAddThreeProductsThenAddItIntoShopAndNotCalculatePriceWithDiscount() {
        DateFormatter sdf = new FoodsDateFormatter();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        List<Store> storeList = List.of(warehouse, shop, trash);
        Food beef = new Meat("beef", 34.1, 15, sdf.parse("2025/02/10"), sdf.parse("2024/10/20"));
        Food chicken = new Meat("chicken", 20.5, 5, sdf.parse("2025/02/05"), sdf.parse("2024/10/25"));
        Food pork = new Meat("pork", 29.4, 10, sdf.parse("2025/02/10"), sdf.parse("2024/10/15"));
        List<Food> foodList = List.of(beef, chicken, pork);
        ControlQuality control = new ControlQuality(storeList);
        control.calculateQuality(foodList);
        assertThat(shop.getAll())
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder(beef, chicken, pork);
    }

    @Test
    public void whenAddThreeProductsThenAddItIntoShopAndCalculateNewPriceWithDiscount() {
        DateFormatter sdf = new FoodsDateFormatter();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        List<Store> storeList = List.of(warehouse, shop, trash);
        Food beef = new Meat("beef", 34.1, 15, sdf.parse("2024/12/20"), sdf.parse("2024/07/20"));
        double expectedPrice = beef.getPrice() * (double) (100 - beef.getDiscount()) / 100;
        List<Food> foodList = List.of(beef);
        ControlQuality control = new ControlQuality(storeList);
        control.calculateQuality(foodList);
        assertThat(shop.buy("beef").getFirst().getPrice()).isEqualTo(expectedPrice);
    }

    @Test
    public void whenAddThreeProductsThenAddItIntoTrash() {
        DateFormatter sdf = new FoodsDateFormatter();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        List<Store> storeList = List.of(warehouse, shop, trash);
        Food beef = new Meat("beef", 34.1, 15, sdf.parse("2024/11/23"), sdf.parse("2024/06/20"));
        Food chicken = new Meat("chicken", 20.5, 5, sdf.parse("2024/11/23"), sdf.parse("2024/08/25"));
        Food pork = new Meat("pork", 29.4, 10, sdf.parse("2024/11/23"), sdf.parse("2024/09/15"));
        List<Food> foodList = List.of(beef, chicken, pork);
        ControlQuality control = new ControlQuality(storeList);
        control.calculateQuality(foodList);
        assertThat(trash.getAll())
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder(chicken, beef, pork);
    }

    @Test
    public void whenAddThreeProductsThenAddEveryProductIntoEachShops() {
        DateFormatter sdf = new FoodsDateFormatter();
        Store trash = new Trash();
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        List<Store> storeList = List.of(warehouse, shop, trash);
        Food beef = new Meat("beef", 34.1, 15, sdf.parse("2024/12/20"), sdf.parse("2024/07/20"));
        Food chicken = new Meat("chicken", 20.5, 5, sdf.parse("2025/01/20"), sdf.parse("2024/11/10"));
        Food pork = new Meat("pork", 29.4, 10, sdf.parse("2024/11/23"), sdf.parse("2024/09/15"));
        List<Food> foodList = List.of(beef, chicken, pork);
        ControlQuality control = new ControlQuality(storeList);
        control.calculateQuality(foodList);
        assertThat(warehouse.getAll()).isNotEmpty().hasSize(1).containsExactlyInAnyOrder(chicken);
    }
}