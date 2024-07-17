package HR.DataAccessLayer.HRData;

import java.sql.*;
import java.util.LinkedList;

public class ShiftsHistoryDAO {
    private static ShiftsHistoryDAO instance;
    private Connection connection;

    public static ShiftsHistoryDAO getInstance() throws Exception{
        if (instance == null){
            instance = new ShiftsHistoryDAO();
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

    public ShiftsHistoryDAO() throws Exception{
        this.connection = toConnect();
    }

    public void insert(ShiftsHistoryDTO shiftsHistory) throws SQLException{
        String query = "INSERT INTO shiftshistory (EmployeeID, BranchID, ShiftID) VALUES (?, ?, ?)";
        try {
            connection = toConnect();
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
            connection = toConnect();
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
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()){
            connection = toConnect();
            while (result.next()){
                Integer EmployeeID = result.getInt("EmploeeID");
                Integer BranchID = result.getInt("BranchID");
                Integer ShiftID = result.getInt("ShiftID");
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
