package HR.DataAccessLayer.HRData;

import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;

public class ContractDAO {
    private static ContractDAO instance;
    private Connection connection;

    public static ContractDAO getInstance() throws Exception{
        if (instance == null){
            instance = new ContractDAO();
        }
        return instance;
    }
    private static Connection toConnect() throws SQLException {
        String url = "jdbc:sqlite:src/SuperLee.db";
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return connection;
    }

    public ContractDAO() throws Exception{
        this.connection = toConnect();
    }

    public void insert(ContractDTO contract) throws SQLException {
        String query = "INSERT INTO contracts (employeeID, contractID, branchID, salary, employmentType, startDate) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, contract.getEmployeeID());
            statement.setInt(2, contract.getContractID());
            statement.setInt(3, contract.getBranchID());
            statement.setInt(4, contract.getSalary());
            statement.setString(5, contract.getEmploymentType());
            statement.setString(6, contract.getStartDate());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in adding cotractdto");
        }
    }

    public void delete(Integer contractID) throws SQLException {
        String query = "DELETE FROM contracts WHERE contractID = ?";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, contractID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in deleting cotract");
        }
    }

    public void editEmploymentType(Integer contractID, String employmentType) throws SQLException {
        String query = "UPDATE contracts SET employmentType = ? WHERE contractID = ?";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employmentType);
            statement.setInt(2, contractID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in editing employment type");
        }
    }

    public void editSalary(Integer contractID, Integer salary) throws SQLException {
        String query = "UPDATE contracts SET salary = ? WHERE contractID = ?";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, salary);
            statement.setInt(2, contractID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in editing employmee salary");
        }
    }

    public ContractDTO getContract(Integer employeeID) throws SQLException{
        String query = "SELECT * FROM contracts WHERE employeeID = ?";
        Integer contratID;
        Integer branchID;
        Integer salary;
        String employmentType;
        String startDate;
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeID);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                contratID = result.getInt("contractID");
                branchID = result.getInt("branchID");
                salary = result.getInt("salary");
                employmentType = result.getString("employmentType");
                startDate = result.getString("startDate");
                result.close();
                connection.close();
                return new ContractDTO(employeeID, contratID, branchID, salary, employmentType, startDate);
            }
        } catch (SQLException e){
            System.out.println("failed in getting contract");
        }
        return null;
    }

    public LinkedList<ContractDTO> Load() throws SQLException {
        String query = "SELECT * FROM contracts";
        LinkedList<ContractDTO> contracts = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()){
            connection = toConnect();
            while (result.next()){
                Integer employeeID = result.getInt("employeeID");
                Integer contratID = result.getInt("contractID");
                Integer branchID = result.getInt("branchID");
                Integer salary = result.getInt("salary");
                String employmentType =result.getString("employmentType");
                String startDate = result.getString("startDate");
                contracts.add(new ContractDTO(employeeID, contratID, branchID, salary, employmentType, startDate));
            }
            result.close();
            connection.close();
            return contracts;
        } catch (SQLException e) {
            System.out.println("failed in contracts load");
        }
        return null;
    }

    public void LoadData() throws Exception{
        for (ContractDTO contract : Load()){
            String[] date = contract.getStartDate().split("-");
            LocalDate startDate = LocalDate.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
            EmployeeController.getInstance().getEmployee(contract.getEmployeeID()).addDTOContract(contract.getContractID(), contract.getBranchID(), contract.getSalary(), contract.getEmploymentType(), startDate);
        }
    }
}
