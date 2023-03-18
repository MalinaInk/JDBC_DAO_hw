package org.malinaInk.service;

import org.malinaInk.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    void create(Employee employee);
    Employee readById(int id);
    List<Employee> readAll();
    void updateEmployeeById(String employeeFirstName, String employeeLastName, String gender, int ageOfEmployee, int cityId, int employeeId);
    void deleteById(int id);
}
