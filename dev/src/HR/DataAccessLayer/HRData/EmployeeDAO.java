package HR.DataAccessLayer.HRData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDAO {
    private static EmployeeDAO instance;
    private Connection connection;

    public static EmployeeDAO getInstance() {
        if (instance == null){
            instance = new EmployeeDAO();
        }
        return instance;
    }

    public EmployeeDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

    public void insert(EmployeeDTO employee) throws SQLException {
        String query = "INSERT INTO employees (employeeID, employeeName, bankUsername, contractID, branchID) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employee.getEmployeeID());
            statement.setString(2, employee.getEmployeeName());
            statement.setString(3, employee.getBankUsername());
            statement.setInt(4, employee.getContractID());
            statement.setInt(5, employee.getBranchID());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in adding employeedto");
        }
    }

    public void delete(Integer employeeID) throws SQLException {
        String query = "DELETE FROM employees WHERE employeeID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in deleting employee");
        }
    }



}
