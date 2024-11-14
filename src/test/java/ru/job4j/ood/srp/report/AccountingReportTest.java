package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class AccountingReportTest {

    @Test
    public void whenConvertSalaryFromRUBtoRUBThenGetCurrentSalary() {
        Calendar now = Calendar.getInstance();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Store store = new MemoryStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee employee = new Employee("Vova", now, now, 100);
        AccountingReport accountingReport = new AccountingReport(converter, store, parser);
        store.add(employee);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(employee.getName()).append(" ")
                .append(parser.parse(employee.getHired())).append(" ")
                .append(parser.parse(employee.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, employee.getSalary(), Currency.RUB))
                .append(System.lineSeparator());
        String result = accountingReport.generate(employee1 -> true, Currency.RUB, Currency.RUB);
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenConvertSalaryFromUSDtoUSDThenGetCurrentSalary() {
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker = new Employee("Misha", now, now, 100);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Store store = new MemoryStore();
        store.add(worker);
        AccountingReport report = new AccountingReport(converter, store, parser);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.USD, worker.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        String result = report.generate(employee -> true, Currency.USD, Currency.USD);
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenConvertSalaryFromEURtoEURThenGetCurrentSalary() {
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker = new Employee("Misha", now, now, 100);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Store store = new MemoryStore();
        store.add(worker);
        AccountingReport report = new AccountingReport(converter, store, parser);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.EUR, worker.getSalary(), Currency.EUR))
                .append(System.lineSeparator());
        String result = report.generate(employee -> true, Currency.EUR, Currency.EUR);
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenConvertSalaryFromRUBtoUSDThenGetCurrentSalary() {
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker = new Employee("Misha", now, now, 100);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Store store = new MemoryStore();
        store.add(worker);
        AccountingReport report = new AccountingReport(converter, store, parser);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        String result = report.generate(employee -> true, Currency.RUB, Currency.USD);
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenConvertSalaryFromUSDtoEURThenGetCurrentSalary() {
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker = new Employee("Misha", now, now, 100);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Store store = new MemoryStore();
        store.add(worker);
        AccountingReport report = new AccountingReport(converter, store, parser);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.USD, worker.getSalary(), Currency.EUR))
                .append(System.lineSeparator());
        String result = report.generate(employee -> true, Currency.USD, Currency.EUR);
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenConvertSalaryFromEURtoUSDThenGetCurrentSalary() {
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker = new Employee("Misha", now, now, 100);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Store store = new MemoryStore();
        store.add(worker);
        AccountingReport report = new AccountingReport(converter, store, parser);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.EUR, worker.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        String result = report.generate(employee -> true, Currency.EUR, Currency.USD);
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenConvertSalaryFromUSDtoRUBThenGetCurrentSalary() {
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker = new Employee("Misha", now, now, 100);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Store store = new MemoryStore();
        store.add(worker);
        AccountingReport report = new AccountingReport(converter, store, parser);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.USD, worker.getSalary(), Currency.RUB))
                .append(System.lineSeparator());
        String result = report.generate(employee -> true, Currency.USD, Currency.RUB);
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenConvertSalaryFromEURtoRUBThenGetCurrentSalary() {
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker = new Employee("Misha", now, now, 100);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Store store = new MemoryStore();
        store.add(worker);
        AccountingReport report = new AccountingReport(converter, store, parser);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.EUR, worker.getSalary(), Currency.RUB))
                .append(System.lineSeparator());
        String result = report.generate(employee -> true, Currency.EUR, Currency.RUB);
        assertThat(result).isEqualTo(expected.toString());
    }

    @Test
    public void whenConvertSalaryFromRUBtoEURThenGetCurrentSalary() {
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker = new Employee("Misha", now, now, 100);
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Store store = new MemoryStore();
        store.add(worker);
        AccountingReport report = new AccountingReport(converter, store, parser);
        StringBuilder expected = new StringBuilder();
        expected.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.EUR))
                .append(System.lineSeparator());
        String result = report.generate(employee -> true, Currency.RUB, Currency.EUR);
        assertThat(result).isEqualTo(expected.toString());
    }
}