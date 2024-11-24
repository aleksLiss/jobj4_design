package ru.job4j.ood.lsp.products.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.food.Meat;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    public void whenAddOneProductThenReturnOneProduct() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expected = new Meat("beef", 22, 10, dateTime.minusDays(10), dateTime.plusDays(40));
        Store warehouse = new Warehouse();
        warehouse.addToShop(expected);
        Food result = warehouse.buy("beef").getFirst();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddThreeProductsThenGetThreeProduct() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("chicken", 22, 10, dateTime.minusDays(5), dateTime.plusDays(30));
        Food expectedTwo = new Meat("pork", 22, 10, dateTime.minusDays(15), dateTime.plusDays(50));
        Food expectedThree = new Meat("beef", 22, 10, dateTime.minusDays(10), dateTime.plusDays(45));
        Store warehouse = new Warehouse();
        warehouse.addToShop(expectedOne);
        warehouse.addToShop(expectedTwo);
        warehouse.addToShop(expectedThree);
        assertThat(warehouse.buy("beef").getLast()).isEqualTo(expectedThree);
    }

    @Test
    public void whenAddZeroProductsAndGetAllProductsThenReturnEmptyList() {
        Store warehouse = new Warehouse();
        assertThat(warehouse.getAll())
                .isEmpty();
    }

    @Test
    public void whenAddOneProductAndGetAllProductsThenReturnProductsListWithOneProduct() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, dateTime.minusDays(10), dateTime.plusDays(55));
        Food expectedTwo = new Meat("beef", 22, 10, dateTime.minusDays(10), dateTime.plusDays(50));
        Food expectedThree = new Meat("beef", 22, 10, dateTime.minusDays(10), dateTime.plusDays(45));
        Store warehouse = new Warehouse();
        warehouse.addToShop(expectedOne);
        warehouse.addToShop(expectedTwo);
        warehouse.addToShop(expectedThree);
        assertThat(warehouse.getAll())
                .isNotEmpty()
                .hasSize(3)
                .containsExactlyInAnyOrder(expectedThree, expectedOne, expectedTwo);
    }

    @Test
    public void whenAddTwoProductsAndGetAllOneProductThenReturnTwoProducts() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, dateTime.minusDays(10), dateTime.plusDays(55));
        Food expectedThree = new Meat("chicken", 22, 10, dateTime.minusDays(10), dateTime.plusDays(55));
        Store warehouse = new Warehouse();
        warehouse.addToShop(expectedOne);
        warehouse.addToShop(expectedThree);
        assertThat(warehouse.getAll())
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(expectedThree, expectedOne);
    }

    @Test
    public void whenAddOneProductAndGetProductWithAnotherNameThenReturnEmptyList() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, dateTime.minusDays(5), dateTime.plusDays(40));
        Store warehouse = new Warehouse();
        warehouse.addToShop(expectedOne);
        assertThat(warehouse.buy("chicken")).isEmpty();
    }

    @Test
    public void whenAddOneProductAndDeleteOneProductThenReturnEmptyList() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, dateTime.minusDays(5), dateTime.plusDays(40));
        Store warehouse = new Warehouse();
        warehouse.addToShop(expectedOne);
        warehouse.deleteFromShop(expectedOne);
        assertThat(warehouse.getAll()).isEmpty();
    }

    @Test
    public void whenAddThreeProductsAndDeleteOneProductThenReturnListWithTwoProducts() {
        LocalDateTime dateTime = LocalDateTime.now();
        Food expectedOne = new Meat("beef", 22, 10, dateTime.minusDays(5), dateTime.plusDays(55));
        Food expectedTwo = new Meat("pork", 22, 10, dateTime.minusDays(5), dateTime.plusDays(55));
        Food expectedThree = new Meat("lemon", 22, 10, dateTime.minusDays(5), dateTime.plusDays(55));
        Store warehouse = new Warehouse();
        warehouse.addToShop(expectedOne);
        warehouse.addToShop(expectedTwo);
        warehouse.addToShop(expectedThree);
        warehouse.deleteFromShop(expectedOne);
        assertThat(warehouse.getAll())
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(expectedThree, expectedTwo);
    }
}