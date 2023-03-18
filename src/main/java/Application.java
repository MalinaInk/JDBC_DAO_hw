import org.malinaInk.model.City;
import org.malinaInk.model.Employee;
import org.malinaInk.service.EmployeeDAO;
import org.malinaInk.service.impl.EmployeeDAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "malina8";
        final String url = "jdbc:postgresql://localhost:5432/postgres";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)")) {

            statement.setInt(1, 3);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String firstNameOfEmployee = "Имя: " + resultSet.getString("first_name");
                String lastNameOfEmployee = "Фамилия: " + resultSet.getString("last_name");
                String genderOfEmployee = "Пол: " + resultSet.getString("gender");
                int ageOfEmployee = resultSet.getInt("age");
                int cityIdOfEmployee = resultSet.getInt("city_id");


                System.out.println(firstNameOfEmployee);
                System.out.println(lastNameOfEmployee);
                System.out.println(genderOfEmployee);
                System.out.println("Возраст: " + ageOfEmployee);
                System.out.println("Город проживания: " + cityIdOfEmployee);

            }
        }


        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            City city6 = new City("Krasnoyarsk");
            Employee employee = new Employee("Nina", "Shirokhova", "female", 36, city6);
            employeeDAO.create(employee);

            List<Employee> list = new ArrayList<>(employeeDAO.readAll());
            for (Employee employee1 : list) {
                System.out.println(employee1);
            }

            employeeDAO.readById(4);

            employeeDAO.updateEmployeeById("Natalia", "Rumiantseva", "female", 33, 7, 10);

            employeeDAO.deleteById(10);

        }
    }
}
