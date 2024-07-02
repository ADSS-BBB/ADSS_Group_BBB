package HR.DataAccessLayer.HRData;

import java.sql.*;
import java.util.LinkedList;

public class BranchDAO {
    private static BranchDAO instance;
    private Connection connection;

    public static BranchDAO getInstance() {
        if (instance == null){
            instance = new BranchDAO();
        }
        return instance;
    }

    public BranchDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

    public void insert(BranchDTO branch) throws SQLException {
        String query = "INSERT INTO branches (branchID, location) VALUES (?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, branch.getBranchID());
            statement.setString(2, branch.getLocation());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in adding branchdto");
        }
    }

    public void delete(Integer branchID) throws SQLException {
        String query = "DELETE FROM branches WHERE branchID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, branchID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in deleting branchdto");
        }
    }

    public void editlocation(Integer branchID, String location) throws SQLException {
        String query = "UPDATE branches SET location = ? WHERE branchID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, location);
            statement.setInt(2, branchID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in editing branch location");
        }
    }

    public LinkedList<BranchDTO> Load() throws SQLException {
        String query = "SELECT * FROM branches";
        LinkedList<BranchDTO> branches = new LinkedList<>();
        Integer branchID;
        String location;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()){
                branchID = result.getInt("branchID");
                location = result.getString("location");
                branches.add( new BranchDTO(branchID, location));
            }
            result.close();
            connection.close();
            return branches;
        } catch (SQLException e) {
            System.out.println("failed in branches load");
        }
        return null;
    }

}
