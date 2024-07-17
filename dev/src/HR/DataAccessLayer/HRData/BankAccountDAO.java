package HR.DataAccessLayer.HRData;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.sql.*;
import java.util.LinkedList;

public class BankAccountDAO {
    private static BankAccountDAO instance;
    private Connection connection;

    public static BankAccountDAO getInstance() throws Exception{
        if (instance == null) {
            instance = new BankAccountDAO();
        }
        return instance;
    }

    public BankAccountDAO() throws Exception{
        this.connection = toConnect();
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

    public void insert(BankAccountDTO bank) throws SQLException {
        String query = "INSERT INTO bankaccounts (employeeID, username, password, balance) VALUES (?, ?, ?, ?)";
        try {
            connection = toConnect();
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setInt(1, bank.getEmployeeID());
            statment.setString(2, bank.getUsername());
            statment.setString(3, bank.getPassword());
            statment.setInt(4, bank.getBalance());
            statment.executeUpdate();
            statment.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in inserting bankdto");
        }
    }

    public void delete(String username) throws SQLException {
        String query = "DELETE FROM bankaccounts WHERE username = ?";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("failed in deleting bank account");
        }
    }

    public void editbankbalance(String username, Integer balance) throws SQLException {
        String query = "UPDATE bankaccounts SET balance = ? WHERE username = ?";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setInt(2, balance);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in updating bank balance");
        }
    }

    public void editbankpassword(String username, String password) throws SQLException{
        String query = "UPDATE bankaccounts SET password = ? WHERE username = ?";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in updating bank password");
        }
    }

    public BankAccountDTO getBankAccount(Integer employeeID) throws SQLException{
        String query = "SELECT * FROM bankaccounts WHERE employeeID = ?";
        String username;
        String password;
        Integer balance;
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, employeeID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                username = result.getString("username");
                password = result.getString("password");
                balance = result.getInt("balance");
                result.close();
                connection.close();
                return new BankAccountDTO(employeeID, username, password, balance);
            }
        } catch (SQLException e){
            System.out.println("failed in getting bank account");
        }
        return null;
    }

    public LinkedList<BankAccountDTO> Load() throws SQLException {
        String query = "SELECT * FROM bankaccounts";
        LinkedList<BankAccountDTO> bankAccounts = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()){
            connection = toConnect();
            while (result.next()){
                Integer employeeID = result.getInt("employeeID");
                String username = result.getString("username");
                String password = result.getString("password");
                Integer balance = result.getInt("balance");
                bankAccounts.add(new BankAccountDTO(employeeID, username, password, balance));
            }
            result.close();
            connection.close();
            return bankAccounts;
        } catch (SQLException e) {
            System.out.println("failed in loading bank account data");
        }
        return null;
    }


    public void LoadData() throws Exception {
        for(BankAccountDTO bank : Load()){
            EmployeeController.getInstance().getEmployee(bank.getEmployeeID()).setBankAccount(new BankAccount(bank.getUsername(), bank.getPassword(), bank.getBalance()));
        }
    }
}
