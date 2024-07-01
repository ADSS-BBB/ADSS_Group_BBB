package HR.DataAccessLayer.HRData;

import java.sql.*;
import java.util.LinkedList;

public class ContractDAO {
    private static ContractDAO instance;
    private Connection connection;

    public static ContractDAO getInstance() {
        if (instance == null){
            instance = new ContractDAO();
        }
        return instance;
    }

    public ContractDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

    public void insert(ContractDTO contract) throws SQLException {
        String query = "INERT INTO contracts (contractID, branchID, salary, employmentType, startDate) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, contract.getContractID());
            statement.setInt(2, contract.getBranchID());
            statement.setInt(3, contract.getSalary());
            statement.setString(4, contract.getEmploymentType());
            statement.setDate(5, contract.getStartDate());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in adding cotractdto");
        }
    }

    public void delete(Integer branchID) throws SQLException {
        String query = "DELETE FROM contracts WHERE branchID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, branchID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in deleting cotract");
        }
    }

    public void editEmploymentType(Integer branchID, String employmentType) throws SQLException {
        String query = "UPDATE contracts SET employmentType = ? WHERE branchID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employmentType);
            statement.setInt(2, branchID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in editing employment type");
        }
    }

    public void editSalary(Integer branchID, Integer salary) throws SQLException {
        String query = "UPDATE contracts SET salary = ? WHERE branchID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, salary);
            statement.setInt(2, branchID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in editing employmee salary");
        }
    }

    public LinkedList<ContractDTO> Load() throws SQLException {
        String query = "SELECT * FROM contracts";
        LinkedList<ContractDTO> contracts = new LinkedList<>();
        Integer contratID;
        Integer branchID;
        Integer salary;
        String employmentType;
        Date startDate;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()){
                contratID = result.getInt("contractID");
                branchID = result.getInt("branchID");
                salary = result.getInt("salary");
                employmentType =result.getString("employmentType");
                startDate = result.getDate("startDate");
                contracts.add(new ContractDTO(contratID, branchID, salary, employmentType, startDate));
            }
            result.close();
            connection.close();
            return contracts;
        } catch (SQLException e) {
            System.out.println("failed in contracts load");
        }
        return null;
    }
}
