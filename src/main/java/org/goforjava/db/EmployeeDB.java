package org.goforjava.db;

import org.goforjava.domain.Employee;
import org.goforjava.domain.Id;

import java.util.*;

public class EmployeeDB implements DB<Employee> {

    private final Map<Id, Employee> employeeMap = new HashMap<>();

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        for (Map.Entry<Id, Employee> entry : employeeMap.entrySet()) {
            Id id = entry.getKey();
            Employee employee = entry.getValue();
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public Optional<Employee> findById(Id id) {
        Employee employee = employeeMap.get(id);
        return Optional.ofNullable(employee);
    }

    @Override
    public void put(Id id, Employee toPut) {
        employeeMap.put(id, toPut);
    }
}
