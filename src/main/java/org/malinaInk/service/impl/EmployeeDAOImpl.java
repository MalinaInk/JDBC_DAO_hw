package org.malinaInk.service.impl;
import org.malinaInk.model.City;
import org.malinaInk.model.Employee;
import org.malinaInk.service.EmployeeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
private final Connection connection;
    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) {
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM city WHERE city_name = (?)")){

            statement.setString(1, employee.getEmployeeCity().getCityName());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employee.getEmployeeCity().setCityId(resultSet.getInt("city_id"));
            } else {
                PreparedStatement statement1 = connection.prepareStatement(
                        "INSERT INTO city (city_name) VALUES (?) RETURNING city_id");
                statement1.setString(1, employee.getEmployeeCity().getCityName());
                resultSet = statement1.executeQuery();
                if (resultSet.next()) {
                    employee.getEmployeeCity().setCityId(resultSet.getInt("city_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try(PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO employee (first_name, last_name, gender, age, city_id)"  +
                                               "VALUES ((?), (?), (?), (?), (?))")){

            statement.setString(1, employee.getEmployeeFirstName());
            statement.setString(2, employee.getEmployeeLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAgeOfEmployee());
            statement.setInt(5, employee.getEmployeeCity().getCityId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readById(int id) {

        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id AND id=(?)")) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                employee.setEmployeeId(Integer.parseInt(resultSet.getString("id")));
                employee.setEmployeeFirstName(resultSet.getString("first_name"));
                employee.setEmployeeLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAgeOfEmployee(Integer.parseInt(resultSet.getString("age")));
                employee.setEmployeeCity(new City(resultSet.getInt("city_id"),
                        resultSet.getString("name_city")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {

        List<Employee> employeeList = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id ORDER BY id")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int employeeId = Integer.parseInt(resultSet.getString("id"));
                String employeeFirstName = resultSet.getString("first_name");
                String employeeLastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int ageOfEmployee = Integer.parseInt(resultSet.getString("age"));
                City employeeCity = new City
                        (resultSet.getInt("city_id"),
                        resultSet.getString("city_name"));

                employeeList.add(new Employee(employeeId, employeeFirstName, employeeLastName, gender, ageOfEmployee,employeeCity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void updateEmployeeById(String employeeFirstName, String employeeLastName, String gender, int ageOfEmployee, int cityId, int employeeId) {

        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE employee SET (first_name=(?), last_name=(?), gender=(?), age=(?), city_id=(?)) WHERE id=(?)")) {

            statement.setString(1, employeeFirstName);
            statement.setString(2, employeeLastName);
            statement.setString(3, gender);
            statement.setInt(4, ageOfEmployee);
            statement.setInt(5, cityId);
            statement.setInt(6, employeeId);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM employee WHERE id=(?)")) {
           statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
