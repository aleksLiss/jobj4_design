package ru.job4j.ood.srp.model;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Objects;

@XmlRootElement(name = "employeeWrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeWrapper {

    private String name;
    private String hired;
    private String fired;
    private double salary;

    public EmployeeWrapper(String name, String hired, String fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public EmployeeWrapper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHired() {
        return hired;
    }

    public void setHired(String hired) {
        this.hired = hired;
    }

    public String getFired() {
        return fired;
    }

    public void setFired(String fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeeWrapper that = (EmployeeWrapper) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
