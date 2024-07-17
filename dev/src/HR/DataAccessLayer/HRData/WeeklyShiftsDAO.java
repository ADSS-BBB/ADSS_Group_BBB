package HR.DataAccessLayer.HRData;

import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.sql.*;
import java.util.LinkedList;

public class WeeklyShiftsDAO {
    private static WeeklyShiftsDAO instance;
    private Connection connection;

    public static WeeklyShiftsDAO getInstance() throws Exception{
        if (instance == null){
            instance = new WeeklyShiftsDAO();
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

    public WeeklyShiftsDAO() throws Exception{
        this.connection = toConnect();
    }


    public void insert(WeeklyShiftsDTO weeklyShifts) throws SQLException {
        String query = "INSERT INTO weeklyShifts (EmployeeID, BranchID, ShiftID) VALUES (?, ?, ?)";
        try {
            connection = toConnect();
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
            connection = toConnect();
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
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()){
            connection = toConnect();
            while (result.next()){
                Integer EmployeeID = result.getInt("EmployeeID");
                Integer BranchID = result.getInt("BranchID");
                Integer ShiftID = result.getInt("ShiftID");
                weeklyShifts.add(new WeeklyShiftsDTO(EmployeeID, BranchID, ShiftID));
            }
            result.close();
            connection.close();
            return weeklyShifts;
        } catch (SQLException e) {
            System.out.println("failed in loading weekly shifts");
        }
        return null;
    }

    public void LoadData() throws Exception{
        for (WeeklyShiftsDTO weeklyShifts : Load()){
            EmployeeController.getInstance().addShiftToWeek(weeklyShifts.getShiftID(), weeklyShifts.getEmployeeID());
        }
    }
}
