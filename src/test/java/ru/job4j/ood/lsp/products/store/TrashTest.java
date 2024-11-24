package ru.job4j.ood.lsp.products.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.food.Meat;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {

    @Test
    public void whenAddOneProductThenReturnOneProduct() {
        LocalDateTime now = LocalDateTime.now();
        Food expected = new Meat("beef", 22, 10, now.minusDays(10), now);
        Store trashStore = new Trash();
        trashStore.addToShop(expected);
        Food result = trashStore.buy("beef").getFirst();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddThreeProductsThenGetThreeProduct() {
        LocalDateTime now = LocalDateTime.now();
        Food expectedOne = new Meat("chicken", 22, 10, now.minusDays(15), now);
        Food expectedTwo = new Meat("pork", 22, 10, now.minusDays(20), now);
        Food expectedThree = new Meat("beef", 22, 10, now.minusDays(30), now);
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
        LocalDateTime now = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, now.minusDays(15), now);
        Food expectedTwo = new Meat("beef", 22, 10, now.minusDays(5), now);
        Food expectedThree = new Meat("beef", 22, 10, now.minusDays(15), now);
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
        LocalDateTime now = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, now.minusDays(16), now);
        Food expectedThree = new Meat("chicken", 22, 10, now.minusDays(11), now);
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
        LocalDateTime now = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, now.minusDays(8), now);
        Store trashStore = new Trash();
        trashStore.addToShop(expectedOne);
        assertThat(trashStore.buy("chicken")).isEmpty();
    }


    @Test
    public void whenAddOneProductAndDeleteOneProductThenReturnEmptyList() {
        LocalDateTime now = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, now.minusDays(5), now);
        Store trashStore = new Trash();
        trashStore.addToShop(expectedOne);
        trashStore.deleteFromShop(expectedOne);
        assertThat(trashStore.getAll()).isEmpty();
    }


    @Test
    public void whenAddThreeProductsAndDeleteOneProductThenReturnListWithTwoProducts() {
        LocalDateTime now = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, now.minusDays(5), now);
        Food expectedTwo = new Meat("pork", 22, 10, now.minusDays(15), now);
        Food expectedThree = new Meat("lemon", 22, 10, now.minusDays(25), now);
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