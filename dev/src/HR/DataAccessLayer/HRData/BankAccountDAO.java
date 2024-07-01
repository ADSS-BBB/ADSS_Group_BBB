package HR.DataAccessLayer.HRData;

import java.sql.*;
import java.util.LinkedList;

public class BankAccountDAO {
    private static BankAccountDAO instance;
    private Connection connection;

    public static BankAccountDAO getInstance() {
        if (instance == null) {
            instance = new BankAccountDAO();
        }
        return instance;
    }

    public BankAccountDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

    public void insert(BankAccountDTO bank) throws SQLException {
        String query = "INSERT INTO bankaccounts (username, password, balance) VALUES (?, ?, ?)";
        try {
            PreparedStatement statment = connection.prepareStatement(query);
            statment.setString(1, bank.getUsername());
            statment.setString(2, bank.getPassword());
            statment.setInt(3, bank.getBalance());
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

    public LinkedList<BankAccountDTO> Load() throws SQLException {
        String query = "SELECT * FROM bankaccounts";
        LinkedList<BankAccountDTO> bankAccounts = new LinkedList<>();
        String username;
        String password;
        Integer balance;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()){
                username = result.getString("username");
                password = result.getString("password");
                balance = result.getInt("balance");
                bankAccounts.add(new BankAccountDTO(username, password, balance));
            }
            result.close();
            connection.close();
            return bankAccounts;
        } catch (SQLException e) {
            System.out.println("failed in loading bank account data");
        }
        return null;
    }
}
