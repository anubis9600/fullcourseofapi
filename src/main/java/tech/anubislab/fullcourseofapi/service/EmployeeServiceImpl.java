package tech.anubislab.fullcourseofapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import tech.anubislab.fullcourseofapi.error.EmployeeNotFoundException;
import tech.anubislab.fullcourseofapi.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> employees = new ArrayList<>();

    Employee employee;

    @Override
    public Employee save(Employee employee) {

        if(employee.getEmployeeId() == null || employee.getEmailId().isEmpty()){
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees ;
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employees.stream()
            .filter(employee -> employee
            .getEmployeeId()
            .equalsIgnoreCase(id))
            .findFirst().orElseThrow(()-> new EmployeeNotFoundException(""+"Employe avec "+id+" nexiste pas"));
    }

    @Override
    public String deleteEmployeeById(String id) {
        Employee employee = employees.stream()
            .filter(e -> e.getEmployeeId()
            .equalsIgnoreCase(id))
            // .findFirst().get();
            .findFirst().orElseThrow(()-> new EmployeeNotFoundException(""+"Employe que vous voulez supprimez avec "+id+" nexiste pas"));


        employees.remove(employee);

        return "Employee supprime avec succes, avec id: "+id;
    }

    
}