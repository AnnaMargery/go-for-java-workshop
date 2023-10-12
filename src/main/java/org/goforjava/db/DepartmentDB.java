package org.goforjava.db;

import org.goforjava.domain.Department;
import org.goforjava.domain.Id;

import java.util.*;
import java.util.stream.Collectors;

public class DepartmentDB implements DB<Department> {

    private final Map<Id, Department> departmentMap = new HashMap<>();

//    @Override
//    public List<Department> findAll() {
//        List<Department> departments = new ArrayList<>();
//        for (Map.Entry<Id, Department> entry : departmentMap.entrySet()) {
//            Id id = entry.getKey();
//            Department department = entry.getValue();
//            departments.add(department);
//        }
//        return departments;
//    }

    @Override
    public List<Department> findAll() {
        return departmentMap.values().stream().collect(Collectors.toList());
    }

//    @Override
//    public Optional<Department> findById(Id id) {
//        Department department = departmentMap.get(id);
//        return Optional.ofNullable(department);
//    }

    @Override
    public Optional<Department> findById(Id id) {
        return Optional.ofNullable(departmentMap.get(id));
    }

    @Override
    public void put(Id id, Department toPut) {
        departmentMap.put(id, toPut);
    }
}
