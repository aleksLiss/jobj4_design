package ru.job4j.ood.srp.model;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "employeesWrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesWrapper {

    @XmlElement(name = "employeeWrapper")
    private List<EmployeeWrapper> employeeWrappers = new ArrayList<>();

    public EmployeesWrapper() {
    }

    public EmployeesWrapper(List<EmployeeWrapper> employeeWrappers) {
        this.employeeWrappers = employeeWrappers;
    }

    public List<EmployeeWrapper> getEmployeeWrappers() {
        return employeeWrappers;
    }

    public void setEmployeeWrappers(List<EmployeeWrapper> employeeWrappers) {
        this.employeeWrappers = employeeWrappers;
    }

    public void add(EmployeeWrapper employeeWrapper) {
        employeeWrappers.add(employeeWrapper);
    }
}
