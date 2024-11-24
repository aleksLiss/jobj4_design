package ru.job4j.ood.lsp.products.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.food.Meat;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


class ShopTest {

    @Test
    public void whenAddOneProductThenReturnOneProduct() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expected = new Meat("beef", 22, 10, dateTime.minusDays(15), dateTime.plusDays(10));
        Store shop = new Shop();
        shop.addToShop(expected);
        Food result = shop.buy("beef").getFirst();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddThreeProductsThenGetThreeProduct() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("chicken", 22, 10, dateTime.minusDays(10), dateTime.plusDays(15));
        Food expectedTwo = new Meat("pork", 22, 10, dateTime.minusDays(15), dateTime.plusDays(10));
        Food expectedThree = new Meat("beef", 22, 10, dateTime.minusDays(20), dateTime.plusDays(15));
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
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, dateTime.minusDays(20), dateTime.plusDays(15));
        Food expectedTwo = new Meat("beef", 22, 10, dateTime.minusDays(30), dateTime.plusDays(25));
        Food expectedThree = new Meat("beef", 22, 10, dateTime.minusDays(30), dateTime.plusDays(25));
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
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, dateTime.minusDays(40), dateTime.plusDays(30));
        Food expectedThree = new Meat("chicken", 22, 10, dateTime.minusDays(30), dateTime.plusDays(20));
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
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, dateTime.minusDays(15), dateTime.plusDays(20));
        Store shop = new Shop();
        shop.addToShop(expectedOne);
        assertThat(shop.buy("chicken")).isEmpty();
    }


    @Test
    public void whenAddOneProductAndDeleteOneProductThenReturnEmptyList() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, dateTime.minusDays(20), dateTime.plusDays(15));
        Store shop = new Shop();
        shop.addToShop(expectedOne);
        shop.deleteFromShop(expectedOne);
        assertThat(shop.getAll()).isEmpty();
    }


    @Test
    public void whenAddThreeProductsAndDeleteOneProductThenReturnListWithTwoProducts() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, dateTime.minusDays(20), dateTime.plusDays(15));
        Food expectedTwo = new Meat("pork", 22, 10, dateTime.minusDays(30), dateTime.plusDays(25));
        Food expectedThree = new Meat("lemon", 22, 10, dateTime.minusDays(20), dateTime.plusDays(15));
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