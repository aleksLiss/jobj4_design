package ru.job4j.ood.lsp.products.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.food.Meat;
import ru.job4j.ood.lsp.products.formatt.DateFormatter;
import ru.job4j.ood.lsp.products.formatt.FoodsDateFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {

    @Test
    public void whenAddOneProductThenReturnOneProduct() {
        DateFormatter p = new FoodsDateFormatter();
        Food expected = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store trashStore = new Trash();
        trashStore.addToShop(expected);
        Food result = trashStore.buy("beef").getFirst();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddThreeProductsThenGetThreeProduct() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("chicken", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedTwo = new Meat("pork", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedThree = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store trashStore = new Trash();
        trashStore.addToShop(expectedOne);
        trashStore.addToShop(expectedTwo);
        trashStore.addToShop(expectedThree);
        assertThat(trashStore.buy("beef").getFirst()).isEqualTo(expectedThree);
    }

    @Test
    public void whenAddZeroProductsAndGetAllProductsThenReturnEmptyList() {
        Store trashStore = new Trash();
        assertThat(trashStore.getAll())
                .isEmpty();
    }


    @Test
    public void whenAddOneProductAndGetAllProductsThenReturnProductsListWithOneProduct() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedTwo = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedThree = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store trashStore = new Trash();
        trashStore.addToShop(expectedOne);
        trashStore.addToShop(expectedTwo);
        trashStore.addToShop(expectedThree);
        assertThat(trashStore.getAll())
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder(expectedThree, expectedOne, expectedTwo);
    }

    @Test
    public void whenAddTwoProductsAndGetAllOneProductThenReturnTwoProducts() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedThree = new Meat("chicken", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store trashStore = new Trash();
        trashStore.addToShop(expectedOne);
        trashStore.addToShop(expectedThree);
        assertThat(trashStore.getAll())
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(expectedThree, expectedOne);
    }

    @Test
    public void whenAddOneProductAndGetProductWithAnotherNameThenReturnEmptyList() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store trashStore = new Trash();
        trashStore.addToShop(expectedOne);
        assertThat(trashStore.buy("chicken")).isEmpty();
    }


    @Test
    public void whenAddOneProductAndDeleteOneProductThenReturnEmptyList() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store trashStore = new Trash();
        trashStore.addToShop(expectedOne);
        trashStore.deleteFromShop(expectedOne);
        assertThat(trashStore.getAll()).isEmpty();
    }


    @Test
    public void whenAddThreeProductsAndDeleteOneProductThenReturnListWithTwoProducts() {
        DateFormatter p = new FoodsDateFormatter();
        Food expectedOne = new Meat("beef", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedTwo = new Meat("pork", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Food expectedThree = new Meat("lemon", 22, 10, p.parse("2024/11/23"), p.parse("2024/11/20"));
        Store trashStore = new Trash();
        trashStore.addToShop(expectedOne);
        trashStore.addToShop(expectedTwo);
        trashStore.addToShop(expectedThree);
        trashStore.deleteFromShop(expectedOne);
        assertThat(trashStore.getAll())
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(expectedThree, expectedTwo);
    }
}