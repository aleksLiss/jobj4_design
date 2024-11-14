package ru.job4j.ocp.second;

public class Auto {

    /** Задание: необходим транспорт.
     * Изначальное требование: перевозка людей.
     */
    public void goTaxiVehicle() {
        System.out.println("Go Taxi....");
    }

    /** Задание изменилось: теперь необходим транспорт
     * для перевозки грузов.
     */
    public void goTruckVehicle() {
        System.out.println("Go Truck......");
    }

    /** Задание вновь изменилось: необходим транспорт
     * для участия в гонках.
     * ИТОГО: очередное изменение вводных,
     * из-за которых пришлось дописывать код.
     */
    public void goSportVehicle() {
        System.out.println("Go Sport car....");
    }
}
