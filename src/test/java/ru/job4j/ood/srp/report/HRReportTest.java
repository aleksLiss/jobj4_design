package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

class HRReportTest {

    @Test
    public void whenAddEmployeeThenReturnOnlyHisNameAndSalary() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Vova", now, now, 100);
        store.add(worker);
        HRReport report = new HRReport(store);
        String result = report.generate((employee) -> true);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(worker.getSalary()).append(" ")
                .append(System.lineSeparator());
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenEmployeeOneSalaryEqualsEmployeeTwoSalaryThenReturnEmployeeOneEmployeeTwo() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Vova", now, now, 100);
        Employee worker2 = new Employee("Misha", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        HRReport report = new HRReport(store);
        String result = report.generate((employee) -> true);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary()).append(" ")
                .append(System.lineSeparator());
        expected.append(worker2.getName()).append(" ")
                .append(worker2.getSalary()).append(" ")
                .append(System.lineSeparator());
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenEmployeeOneSalaryLessThanEmployeeTwoSalaryThenReturnEmployeeTwoEmployeeOne() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Vova", now, now, 100);
        Employee worker2 = new Employee("Misha", now, now, 10000);
        store.add(worker1);
        store.add(worker2);
        HRReport report = new HRReport(store);
        String result = report.generate((employee) -> true);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary()).append(" ")
                .append(System.lineSeparator());
        expected.append(worker1.getName()).append(" ")
                .append(worker1.getSalary()).append(" ")
                .append(System.lineSeparator());
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenEmployeeOneSalaryMoreEmployeeTwoSalaryThenReturnEmployeeOneEmployeeTwo() {
        Store store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Vova", now, now, 1000000);
        Employee worker2 = new Employee("Misha", now, now, 100);
        store.add(worker1);
        store.add(worker2);
        HRReport report = new HRReport(store);
        String result = report.generate((employee) -> true);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary()).append(" ")
                .append(System.lineSeparator());
        expected.append(worker2.getName()).append(" ")
                .append(worker2.getSalary()).append(" ")
                .append(System.lineSeparator());
        assertThat(result).isEqualTo(expected.toString());

    }
}