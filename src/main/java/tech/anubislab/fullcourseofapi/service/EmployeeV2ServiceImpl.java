package tech.anubislab.fullcourseofapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.anubislab.fullcourseofapi.entity.EmployeeEntity;
import tech.anubislab.fullcourseofapi.model.Employee;
import tech.anubislab.fullcourseofapi.repository.EmployeeRepository;

@Service
public class EmployeeV2ServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    // Employee employee;

    List<Employee> employees = new ArrayList<>();

    @Override
    public Employee save(Employee employee) {
        if(employee.getEmployeeId() == null || employee.getEmailId().isEmpty()){
            employee.setEmployeeId(UUID.randomUUID().toString());
        }

        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, entity);

        repository.save(entity);
        
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = repository.findAll();
        employees = employeeEntities
                    .stream()
                    .map(employeeEntity ->{
                        Employee e = new Employee();
                        BeanUtils.copyProperties(employeeEntity, e);
                        return e;
                    }).collect(Collectors.toList());
        return employees;
    }

    @Override
    public Employee getEmployeeById(String id) {
        EmployeeEntity employeeEntity = repository.findById(id).orElse(null);
        Employee e = new Employee();
        BeanUtils.copyProperties(employeeEntity, e);        
        return e;
    }

    @Override
    public String deleteEmployeeById(String id) {
        EmployeeEntity employeeEntity = repository.findById(id).orElse(null);
        repository.deleteById(id);
        return "Employee suprimee";
    }

   
}