package ru.job4j.ood.ocp.xml;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportGregorianCalendarParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class ReportToXmlTest {

    @Test
    public void whenAddOneEmployeeThenReturnReportWithOneEmployee() {
        Store store = new MemoryStore();
        GregorianCalendar now =
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41);
        store.add(new Employee("John Doe", now, now, 5000));
        DateTimeParser<GregorianCalendar> parser = new ReportGregorianCalendarParser();
        ReportToXml report = new ReportToXml(store, parser);
        String result = report.generate(employee -> true);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employeesWrapper>\n"
                + "    <employeeWrapper>\n"
                + "        <name>John Doe</name>\n"
                + "        <hired>08:06:2023 17:41</hired>\n"
                + "        <fired>08:06:2023 17:41</fired>\n"
                + "        <salary>5000.0</salary>\n"
                + "    </employeeWrapper>\n"
                + "</employeesWrapper>\n";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddTwoEmployeesThenReturnReportWithTwoEmployees() {
        Store store = new MemoryStore();
        GregorianCalendar now =
                new GregorianCalendar(2023, Calendar.JUNE, 8, 17, 41);
        store.add(new Employee("John Doe", now, now, 5000));
        store.add(new Employee("Jack Black", now, now, 1000));
        DateTimeParser<GregorianCalendar> parser = new ReportGregorianCalendarParser();
        ReportToXml report = new ReportToXml(store, parser);
        String result = report.generate(employee -> true);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employeesWrapper>\n"
                + "    <employeeWrapper>\n"
                + "        <name>John Doe</name>\n"
                + "        <hired>08:06:2023 17:41</hired>\n"
                + "        <fired>08:06:2023 17:41</fired>\n"
                + "        <salary>5000.0</salary>\n"
                + "    </employeeWrapper>\n"
                + "    <employeeWrapper>\n"
                + "        <name>Jack Black</name>\n"
                + "        <hired>08:06:2023 17:41</hired>\n"
                + "        <fired>08:06:2023 17:41</fired>\n"
                + "        <salary>1000.0</salary>\n"
                + "    </employeeWrapper>\n"
                + "</employeesWrapper>\n";
        assertThat(result).isEqualTo(expected);
    }
}
