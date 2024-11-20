package ru.job4j.ood.ocp.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeWrapper;
import ru.job4j.ood.srp.model.EmployeesWrapper;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportToXml implements Report {

    private DateTimeParser dateTimeParser;
    private Store store;

    public ReportToXml(Store store, DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String result = "";
        EmployeesWrapper employeesWrapper = new EmployeesWrapper();
        for (Employee employee : store.findBy(filter)) {
            EmployeeWrapper employeeWrapper = new EmployeeWrapper(
                    employee.getName(),
                    dateTimeParser.parse(employee.getHired()),
                    dateTimeParser.parse(employee.getFired()),
                    employee.getSalary()
            );
            employeesWrapper.add(employeeWrapper);
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(EmployeesWrapper.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(employeesWrapper, writer);
                result = writer.toString();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}