package ru.job4j.serialization.xml;

public class Person {
    private final String name;
    private final int age;
    private final Address address;

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='"
                + name + '\''
                + ", age="
                + age
                + ", address="
                + address
                + '}';
    }

    public static void main(String[] args) {
        final Person person = new Person("Jack", 43,
                new Address("Crosby Street", 12, 3));
        System.out.println(person);
    }
}
