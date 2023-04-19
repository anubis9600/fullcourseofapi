package tech.anubislab.fullcourseofapi.service;

import java.util.List;

import tech.anubislab.fullcourseofapi.model.Employee;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    String deleteEmployeeById(String id);

    
}
