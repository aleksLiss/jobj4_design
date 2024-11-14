package ru.job4j.ood.srp.report;


import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.PathChecker;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.*;
import java.util.function.Predicate;

public class ProgrammerReport {

    private final Store store;
    private final DateTimeParser dateTimeParser;
    private final String path;
    private final PathChecker checker;

    public ProgrammerReport(Store store, DateTimeParser dateTimeParser, String path, PathChecker checker) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.path = path;
        this.checker = checker;
    }

    private String fromStoreToString(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(dateTimeParser.parse(employee.getFired())).append(";")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    private String checkPath(String path) {
        try {
            if (!checker.check(path)) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return path;
    }

    public void generate(Predicate<Employee> filter) {
        String res = fromStoreToString(filter);
        checkPath(path);
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(res);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
