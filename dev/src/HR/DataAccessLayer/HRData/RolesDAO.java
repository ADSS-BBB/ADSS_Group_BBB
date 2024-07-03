package HR.DataAccessLayer.HRData;

import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RolesDAO {
    private static RolesDAO instance;
    private Connection connection;

    public static RolesDAO getInstance() {
        if (instance == null){
            instance = new RolesDAO();
        }
        return instance;
    }

    public RolesDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

    public void insert(RolesDTO rolesDTO) throws SQLException {
        String query = "INSERT INTO Roles (EmployeeID, BranchId, role) VALUES(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, rolesDTO.getEmployeeID());
            statement.setInt(2, rolesDTO.getBranchId());
            statement.setString(3, rolesDTO.getRole());
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("failed in adding a role");
        }
    }

    public void delete(Integer EmployeeID, String role) throws SQLException {
        String query = "DELETE FROM Roles WHERE EmployeeID = ? AND role = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, EmployeeID);
            statement.setString(2, role);
            statement.executeUpdate();
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("failed in deleting a role");
        }
    }

    public LinkedList<RolesDTO> Load() throws SQLException {
        String query = "SELECT * FROM Roles";
        LinkedList<RolesDTO> roles = new LinkedList<>();
        Integer EmployeeID = -1;
        Integer BranchID = -1;
        String role = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                EmployeeID = result.getInt("EmployeeID");
                BranchID = result.getInt("BranchId");
                role = result.getString("role");
                roles.add(new RolesDTO(EmployeeID, BranchID, role));
            }
            result.close();
            connection.close();
            return roles;
        }catch (SQLException e) {
            System.out.println("failed in loading roles");
        }
        return null;
    }

    public void LoadData() throws Exception{
        for (RolesDTO role : Load()){
            EmployeeController.getInstance().getEmployee(role.getEmployeeID()).addRole(role.getRole());
        }
    }

}
