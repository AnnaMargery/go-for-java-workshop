package org.goforjava.domain;

import org.goforjava.db.DB;

import java.time.LocalDate;
import java.util.*;
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
        return employeeDB
                .findAll()
                .stream()
                .filter(employee -> LocalDate.now()
                        .getYear() - employee
                        .getBirthDate()
                        .getYear() >= years)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findThreeTopCompensatedEmployees() {
        return employeeDB
                .findAll()
                .stream()
                .sorted(Comparator
                        .comparingLong(Employee::getGrossSalary)
                        .reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Department> findDepartmentWithLowestCompensationAverage() {



        return Optional.empty();
    }

//    @Override
//    public List<Employee> findEmployeesBasedIn(Localtion localtion) {
//
//        List<Employee> employeesByLocation = new ArrayList<>();
//        List<Department> departmentByLocation = new ArrayList<>();
//
//        for (Department department : departmentDB.findAll()) {
//            if (department.getLocation() == localtion) {
//                departmentByLocation.add(department);
//            }
//        }
//
//        for (Department department : departmentByLocation) {
//            for (Employee employee : employeeDB.findAll()) {
//                if (department.getId().equals(employee.getDepartmentId())) {
//                    employeesByLocation.add(employee);
//                }
//            }
//        }
//        return employeesByLocation;
//    }

    @Override
    public List<Employee> findEmployeesBasedIn(Localtion localtion) {
       return employeeDB
                .findAll()
                .stream()
                .filter(e -> departmentDB
                        .findById(e.getDepartmentId())
                        .get()
                        .getLocation()
                        .equals(localtion))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, Long> countEmployeesByHireYear() {
        return employeeDB
                .findAll()
                .stream()
                .collect(Collectors
                        .groupingBy(employee -> employee
                                .getHireDate()
                                .getYear(), Collectors
                                .counting()));
    }

    @Override
    public Map<Localtion, Long> countEmployeesByLocation() {
        return employeeDB
                .findAll()
                .stream()
                .collect(Collectors
                        .groupingBy(employee -> departmentDB
                                .findById(employee.getDepartmentId())
                        .get()
                        .getLocation(),Collectors.counting()));
    }
}
