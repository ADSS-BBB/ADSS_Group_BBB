package HR.DataAccessLayer.HRData;

import HR.DomainLayer.BranchPackage.BranchController;

import java.sql.*;
import java.util.LinkedList;

public class BranchDAO {
    private static BranchDAO instance;
    private Connection connection;

    public static BranchDAO getInstance() throws Exception{
        if (instance == null){
            instance = new BranchDAO();
        }
        return instance;
    }

    private static Connection toConnect() throws ClassNotFoundException {
        String url = "jdbc:sqlite:C:/Users/Win10/Desktop/ADSS_Group_BBB/ADSS_Group_BBB/SuperLee.db";
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return connection;
    }

    public BranchDAO() throws Exception{
        this.connection = toConnect();
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

    public BranchDTO getBranch(Integer branchID) throws SQLException {
        String query = "SELECT * FROM branches WHERE branchID = ?";
        String location = "";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, branchID);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                location = result.getString("location");
            }
            result.close();
            connection.close();
            return new BranchDTO(branchID, location);
        } catch (SQLException e) {
            System.out.println("failed in getting branch");
        }
        return null;
    }

    public LinkedList<BranchDTO> Load() throws SQLException {
        String query = "SELECT * FROM branches";
        LinkedList<BranchDTO> branches = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()){
            while (result.next()){
                Integer branchID = result.getInt("branchID");
                String location = result.getString("location");
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

    public void LoadData() throws Exception {
        for (BranchDTO branch : Load()){
            BranchController.getInstance().addBranchfromDTO(branch.getBranchID(), branch.getLocation());
        }
    }

}
