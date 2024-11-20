package ru.job4j.ood.ocp.json;

import com.google.gson.JsonParseException;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportGregorianCalendarParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReportToJsonTest {
    @Test
    public void whenAddOneEmployeeThenReturnReportToJsonWithOneEmployee() throws JsonParseException {
        Store store = new MemoryStore();
        GregorianCalendar now =
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41);
        store.add(new Employee("John Doe", now, now, 5000));
        DateTimeParser<GregorianCalendar> parser = new ReportGregorianCalendarParser();
        ReportToJson report = new ReportToJson(store, parser);
        String result = report.generate(employee -> true);
        String expected = "["
                + "{"
                + "\"name\":\"John Doe\","
                + "\"hired\":\"08:06:2023 17:41\","
                + "\"fired\":\"08:06:2023 17:41\","
                + "\"salary\":5000.0"
                + "}"
                + "]";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddTwoEmployeesThenReturnReportToJsonWithTwoEmployees() {
        Store store1 = new MemoryStore();
        GregorianCalendar calendar =
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 42);
        store1.add(new Employee("Vova", calendar, calendar, 10000));
        store1.add(new Employee("Misha", calendar, calendar, 100));
        DateTimeParser<GregorianCalendar> parser1 = new ReportGregorianCalendarParser();
        ReportToJson report = new ReportToJson(store1, parser1);
        String result = report.generate(employee -> true);
        String expected = "[{\"name\":\"Vova\","
                + "\"hired\":\"08:06:2023 17:42\","
                + "\"fired\":\"08:06:2023 17:42\","
                + "\"salary\":10000.0},"
                + "{"
                + "\"name\":\"Misha\","
                + "\"hired\":\"08:06:2023 17:42\","
                + "\"fired\":\"08:06:2023 17:42\","
                + "\"salary\":100.0}]";
        assertThat(result).isEqualTo(expected);
    }
}
