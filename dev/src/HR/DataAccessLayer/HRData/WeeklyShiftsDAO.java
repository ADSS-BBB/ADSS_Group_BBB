package HR.DataAccessLayer.HRData;

import java.sql.*;
import java.util.LinkedList;

public class WeeklyShiftsDAO {
    private static WeeklyShiftsDAO instance;
    private Connection connection;

    public static WeeklyShiftsDAO getInstance() {
        if (instance == null){
            instance = new WeeklyShiftsDAO();
        }
        return instance;
    }

    public WeeklyShiftsDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }


    public void insert(WeeklyShiftsDTO weeklyShifts) throws SQLException {
        String query = "INSERT INTO shiftshistory (EmployeeID, BranchID, ShiftID) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, weeklyShifts.getEmployeeID());
            statement.setInt(2, weeklyShifts.getBranchID());
            statement.setInt(3, weeklyShifts.getShiftID());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in adding to weeklyShifts");
        }
    }

    public void delete(Integer ShiftID, Integer EmployeeID) throws SQLException {
        String query = "DELETE FROM weeklyShifts WHERE ShiftID = ? AND EmployeeID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ShiftID);
            statement.setInt(2, EmployeeID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in deleting from weeklyshifts");
        }
    }

    public LinkedList<WeeklyShiftsDTO> Load() throws SQLException {
        String query = "SELECT * FROM weeklyShifts";
        LinkedList<WeeklyShiftsDTO> weeklyShifts = new LinkedList<>();
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
                weeklyShifts.add(new WeeklyShiftsDTO(EmployeeID, BranchID, ShiftID));
            }
            result.close();
            connection.close();
            return weeklyShifts;
        } catch (SQLException e) {
            System.out.println("failed in loading shifts history");
        }
        return null;
    }
}
