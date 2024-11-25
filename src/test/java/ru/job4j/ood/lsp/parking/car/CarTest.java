package ru.job4j.ood.lsp.parking.car;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CarTest {

    @Test
    public void whenCreateNewCarWithSizeEqualsOneThenReturnPassengerCar() {
        Car result = new PassengerCar(1);
        assertThat(result).isEqualTo(new PassengerCar(1));
    }

    @Test
    public void whenCreateNewCarWithSizeEqualsTwoThenReturnSmallTruck() {
        Car result = new SmallTruck(2);
        assertThat(result).isEqualTo(new SmallTruck(2));
    }

    @Test
    public void whenCreateNewCarWithSizeEqualsThreeThenReturnBigTruck() {
        Car result = new BigTruck(3);
        assertThat(result).isEqualTo(new BigTruck(3));
    }

    @Test
    public void whenCreateNewCarWithSizeEqualsZeroThenGetException() {
        assertThatThrownBy(() -> new PassengerCar(0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenCreateNewCarWithSizeLessThatZeroThenGetException() {
        assertThatThrownBy(() -> new PassengerCar(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}