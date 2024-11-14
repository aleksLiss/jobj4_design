package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HRReport {

    private final Store store;

    public HRReport(Store store) {
        this.store = store;
    }

    private List<Employee> sortEmployeesStore(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter);
        list.sort(Comparator.comparing(Employee::getSalary).reversed());
        return list;
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : sortEmployeesStore(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary()).append(" ")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}


