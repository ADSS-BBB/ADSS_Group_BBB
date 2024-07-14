package HR.DataAccessLayer.HRData;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.Contract;
import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;

public class EmployeeDAO {
    private static EmployeeDAO instance;
    private Connection connection;


    public static EmployeeDAO getInstance() throws Exception{
        if (instance == null){
            instance = new EmployeeDAO();
        }
        return instance;
    }
    private static Connection toConnect() throws SQLException {
        String url = "jdbc:sqlite:C:/Users/Win10/Desktop/ADSS_Group_BBB/ADSS_Group_BBB/SuperLee.db";
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return connection;
    }

    public EmployeeDAO() throws Exception{
        this.connection = toConnect();
    }

    public void insert(EmployeeDTO employee) throws SQLException {
        String query = "INSERT INTO employees (employeeID, employeeName, bankUsername, contractID, branchID) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = toConnect();
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
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in deleting employee");
        }
    }

    public EmployeeDTO getEmployee(Integer employeeID) throws SQLException {
        String query = "SELECT * FROM employees WHERE employeeID = ?";
        String employeeName = "";
        String bankUsername = "";
        Integer contractID = -1;
        Integer branchID = -1;
        try{
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeID);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                employeeName = result.getString("employeeName");
                bankUsername = result.getString("bankUsername");
                contractID = result.getInt("contractID");
                branchID = result.getInt("branchID");
            }
            result.close();
            connection.close();
            return new EmployeeDTO(employeeID, employeeName, bankUsername, contractID, branchID);
        } catch (SQLException e) {
            System.out.println("failed in getting employee");
            }
        return null;
        }



    public LinkedList<EmployeeDTO> Load() throws SQLException {
        LinkedList<EmployeeDTO> employeeList = new LinkedList<>();
        String query = "SELECT * FROM employees";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()){
            connection = toConnect();
            while (result.next()) {
                Integer employeeID = result.getInt("employeeID");
                String employeeName = result.getString("employeeName");
                String bankUsername = result.getString("bankUsername");
                Integer contractID = result.getInt("contractID");
                Integer branchID = result.getInt("branchID");
                employeeList.add(new EmployeeDTO(employeeID, employeeName, bankUsername, contractID, branchID));
            }
            result.close();
            connection.close();
            return employeeList;
        } catch (SQLException e) {
            System.out.println("failed in loading employee");
        }
        return null;
    }

    public void LoadData() throws Exception{
        for (EmployeeDTO employee : Load()){
            ContractDTO contractDTO = ContractDAO.getInstance().getContract(employee.getEmployeeID());
            String[] startdate = contractDTO.getStartDate().split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(startdate[2]), Integer.parseInt(startdate[1]),Integer.parseInt(startdate[0]));
            Contract contract = new Contract(contractDTO.getContractID(), contractDTO.getSalary(),contractDTO.getBranchID(), contractDTO.getEmploymentType(), date);
            BankAccountDTO bankAccountDTO = BankAccountDAO.getInstance().getBankAccount(employee.getEmployeeID());
            BankAccount bankAccount = new BankAccount(bankAccountDTO.getUsername(), bankAccountDTO.getPassword(), bankAccountDTO.getBalance());
            EmployeeController.getInstance().addEmployeefromDTO(employee.getEmployeeID(), employee.getEmployeeName(), contract, bankAccount);
        }
    }


}
