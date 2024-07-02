package HR.DataAccessLayer.HRData;

import java.sql.*;
import java.util.LinkedList;

public class ShiftsHistoryDAO {
    private static ShiftsHistoryDAO instance;
    private Connection connection;

    public static ShiftsHistoryDAO getInstance() {
        if (instance == null){
            instance = new ShiftsHistoryDAO();
        }
        return instance;
    }

    public ShiftsHistoryDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

    public void insert(ShiftsHistoryDTO shiftsHistory) throws SQLException{
        String query = "INSERT INTO shiftshistory (EmployeeID, BranchID, ShiftID) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, shiftsHistory.getEmployeeID());
            statement.setInt(2, shiftsHistory.getBranchID());
            statement.setInt(3, shiftsHistory.getShiftID());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in adding to shiftshistory");
        }
    }

    public void delete(Integer ShiftID, Integer EmployeeID) throws SQLException {
        String query = "DELETE FROM shiftshistory WHERE ShiftID = ? AND EmployeeID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ShiftID);
            statement.setInt(2, EmployeeID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in deleting from shiftshistory");
        }
    }

    public LinkedList<ShiftsHistoryDTO> Load() throws SQLException {
        String query = "SELECT * FROM shiftshistory";
        LinkedList<ShiftsHistoryDTO> shiftsHistory = new LinkedList<>();
        Integer EmployeeID = -1;
        Integer BranchID = -1;
        Integer ShiftID = -1;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()){
                EmployeeID = result.getInt("EmploeeID");
                BranchID = result.getInt("BranchID");
                ShiftID = result.getInt("ShiftID");
                shiftsHistory.add(new ShiftsHistoryDTO(EmployeeID, BranchID, ShiftID));
            }
            result.close();
            connection.close();
            return shiftsHistory;
        } catch (SQLException e) {
            System.out.println("failed in loading shifts history");
        }
        return null;
    }

}
