package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.PathCheck;
import ru.job4j.ood.srp.formatter.PathChecker;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ProgrammerReportTest {

    @Test
    public void whenEmptyListOfEmployeesThenEmptyCSVFile() {
        Store store1 = new MemoryStore();
        String path = "./src/main/java/ru/job4j/ood/srp/scv/ProgrammerReport.scv";
        DateTimeParser parser = new ReportDateTimeParser();
        PathChecker checker1 = new PathCheck();
        ProgrammerReport report = new ProgrammerReport(store1, parser, path, checker1);
        report.generate((employee) -> true);
        StringBuilder result = new StringBuilder();
        String expected = "Name; Hired; Fired; Salary;";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            result.append(reader.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assertThat(result.toString()).isNotEmpty().isEqualTo(expected);
    }

    @Test
    public void whenListOfEmployeesHasOneEmployeeThenFileCSVHasOneEmployee() {
        Store store1 = new MemoryStore();
        String path = "./src/main/java/ru/job4j/ood/srp/scv/ProgrammerReport.scv";
        DateTimeParser parser = new ReportDateTimeParser();
        PathChecker checker1 = new PathCheck();
        Calendar now = Calendar.getInstance();
        Employee employee1 = new Employee("Vova", now, now, 100);
        store1.add(employee1);
        ProgrammerReport report = new ProgrammerReport(store1, parser, path, checker1);
        report.generate((employee) -> true);
        StringBuilder result = new StringBuilder();
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(employee1.getName()).append(";")
                .append(parser.parse(employee1.getHired())).append(";")
                .append(parser.parse(employee1.getFired())).append(";")
                .append(employee1.getSalary())
                .append(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                result.append(reader.readLine());
                result.append(System.lineSeparator());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assertThat(result.toString()).isNotEmpty().isEqualTo(expected.toString());
    }

    @Test
    public void whenListOfEmployeesHasTwoEmployeesThenFileCSVHasTwoEmployees() {
        Store store1 = new MemoryStore();
        String path = "./src/main/java/ru/job4j/ood/srp/scv/ProgrammerReport.scv";
        DateTimeParser parser = new ReportDateTimeParser();
        PathChecker checker1 = new PathCheck();
        Calendar now = Calendar.getInstance();
        Employee employee1 = new Employee("Vova", now, now, 100);
        Employee employee2 = new Employee("Misha", now, now, 1000);
        store1.add(employee1);
        store1.add(employee2);
        ProgrammerReport report = new ProgrammerReport(store1, parser, path, checker1);
        report.generate((employee) -> true);
        StringBuilder result = new StringBuilder();
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(employee1.getName()).append(";")
                .append(parser.parse(employee1.getHired())).append(";")
                .append(parser.parse(employee1.getFired())).append(";")
                .append(employee1.getSalary())
                .append(System.lineSeparator())
                .append(employee2.getName()).append(";")
                .append(parser.parse(employee2.getHired())).append(";")
                .append(parser.parse(employee2.getFired())).append(";")
                .append(employee2.getSalary())
                .append(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                result.append(reader.readLine());
                result.append(System.lineSeparator());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assertThat(result.toString()).isNotEmpty().isEqualTo(expected.toString());
    }
}
