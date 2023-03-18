package org.malinaInk.model;

import java.util.Objects;

public class Employee {

    private int employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String gender;
    private int ageOfEmployee;
    private City employeeCity;

    public Employee() {
    }

    public Employee(String employeeFirstName, String employeeLastName, String gender, int ageOfEmployee, City employeeCity) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.gender = gender;
        this.ageOfEmployee = ageOfEmployee;
        this.employeeCity = employeeCity;
    }

    public Employee(int employeeId, String employeeFirstName, String employeeLastName, String gender, int ageOfEmployee, City employeeCity) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.gender = gender;
        this.ageOfEmployee = ageOfEmployee;
        this.employeeCity = employeeCity;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAgeOfEmployee() {
        return ageOfEmployee;
    }

    public void setAgeOfEmployee(int ageOfEmployee) {
        this.ageOfEmployee = ageOfEmployee;
    }

    public City getEmployeeCity() {
        return employeeCity;
    }

    public void setEmployeeCity(City employeeCity) {
        this.employeeCity = employeeCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId && ageOfEmployee == employee.ageOfEmployee && Objects.equals(employeeFirstName, employee.employeeFirstName) && Objects.equals(employeeLastName, employee.employeeLastName) && Objects.equals(gender, employee.gender) && Objects.equals(employeeCity, employee.employeeCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeFirstName, employeeLastName, gender, ageOfEmployee, employeeCity);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", gender='" + gender + '\'' +
                ", ageOfEmployee=" + ageOfEmployee +
                ", employeeCity=" + employeeCity +
                '}';
    }
}
