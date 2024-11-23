package ru.job4j.ood.lsp.products.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.food.Meat;
import ru.job4j.ood.lsp.products.formatt.DateFormatter;
import ru.job4j.ood.lsp.products.formatt.FoodsDateFormatter;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    public void whenAddOneProductThenReturnOneProduct() {
        DateFormatter p = new FoodsDateFormatter();
        Food expected = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store shop = new Shop();
        shop.addToShop(expected);
        Food result = shop.buy("beef").getFirst();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddThreeProductsThenGetThreeProduct() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("chicken", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedTwo = new Meat("pork", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedThree = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store shop = new Shop();
        shop.addToShop(expectedOne);
        shop.addToShop(expectedTwo);
        shop.addToShop(expectedThree);
        assertThat(shop.buy("beef").getFirst()).isEqualTo(expectedThree);
    }

    @Test
    public void whenAddZeroProductsAndGetAllProductsThenReturnEmptyList() {
        Store shop = new Shop();
        assertThat(shop.getAll())
                .isEmpty();
    }


    @Test
    public void whenAddOneProductAndGetAllProductsThenReturnProductsListWithOneProduct() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedTwo = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedThree = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store shop = new Shop();
        shop.addToShop(expectedOne);
        shop.addToShop(expectedTwo);
        shop.addToShop(expectedThree);
        assertThat(shop.getAll())
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder(expectedThree, expectedOne, expectedTwo);
    }

    @Test
    public void whenAddTwoProductsAndGetAllOneProductThenReturnTwoProducts() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedThree = new Meat("chicken", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store shop = new Shop();
        shop.addToShop(expectedOne);
        shop.addToShop(expectedThree);
        assertThat(shop.getAll())
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(expectedThree, expectedOne);
    }

    @Test
    public void whenAddOneProductAndGetProductWithAnotherNameThenReturnEmptyList() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store shop = new Shop();
        shop.addToShop(expectedOne);
        assertThat(shop.buy("chicken")).isEmpty();
    }


    @Test
    public void whenAddOneProductAndDeleteOneProductThenReturnEmptyList() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store shop = new Shop();
        shop.addToShop(expectedOne);
        shop.deleteFromShop(expectedOne);
        assertThat(shop.getAll()).isEmpty();
    }


    @Test
    public void whenAddThreeProductsAndDeleteOneProductThenReturnListWithTwoProducts() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedTwo = new Meat("pork", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedThree = new Meat("lemon", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store shop = new Shop();
        shop.addToShop(expectedOne);
        shop.addToShop(expectedTwo);
        shop.addToShop(expectedThree);
        shop.deleteFromShop(expectedOne);
        assertThat(shop.getAll())
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(expectedThree, expectedTwo);
    }
}