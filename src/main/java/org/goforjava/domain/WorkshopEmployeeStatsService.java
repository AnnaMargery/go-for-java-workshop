package org.goforjava.domain;

import org.goforjava.db.DB;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class WorkshopEmployeeStatsService implements EmployeeStatsService {

    private final DB<Employee> employeeDB;
    private final DB<Department> departmentDB;

    public WorkshopEmployeeStatsService(DB<Employee> employeeDB, DB<Department> departmentDB) {
        this.employeeDB = employeeDB;
        this.departmentDB = departmentDB;
    }

    @Override
    public List<Employee> findEmployeesOlderThen(long years) {
        return employeeDB.findAll().stream()
                .filter(employee -> LocalDate.now().getYear() - employee.getBirthDate().getYear()  > years)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findThreeTopCompensatedEmployees() {
       return employeeDB.findAll().stream()
               .sorted(Comparator.comparingLong(Employee::getGrossSalary).reversed())
               .limit(3).collect(Collectors.toList());
    }

    @Override
    public Optional<Department> findDepartmentWithLowestCompensationAverage() {
        return Optional.empty();
    }

    @Override
    public List<Employee> findEmployeesBasedIn(Localtion localtion) {
        return List.of();
    }

    @Override
    public Map<Integer, Long> countEmployeesByHireYear() {
        return Map.of();
    }

    @Override
    public Map<Localtion, Long> countEmployeesByLocation() {
        return Map.of();
    }
}
