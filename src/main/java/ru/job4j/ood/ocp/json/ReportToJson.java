package ru.job4j.ood.ocp.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeWrapper;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.Store;

import java.util.*;
import java.util.function.Predicate;

public class ReportToJson implements Report {

    private Store store;
    private DateTimeParser parser;

    public ReportToJson(Store store, DateTimeParser parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Gson gson = new GsonBuilder().create();
        List<EmployeeWrapper> employees = new ArrayList<>();
        for (Employee employee : store.findBy(filter)) {
            EmployeeWrapper employeeWrapper = new EmployeeWrapper(
                    employee.getName(),
                    parser.parse(employee.getHired()),
                    parser.parse(employee.getFired()),
                    employee.getSalary()
            );
            employees.add(employeeWrapper);
        }
        return gson.toJson(employees).toString();
    }
}
