package HR.DataAccessLayer.HRData;

import HR.DomainLayer.PersonnelManager;

import java.sql.*;
import java.util.LinkedList;

public class ScheduleDAO {
    private static ScheduleDAO instance;
    private Connection connection;

    public static ScheduleDAO getInstance() {
        if (instance == null){
            instance = new ScheduleDAO();
        }
        return instance;
    }

    public ScheduleDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

    public void insert(ScheduleDTO schedule) throws SQLException {
        String query = "INSERT INTO schedule (ShiftID, EmployeeID, BranchID, role) VALUES (?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, schedule.getShiftID());
            statement.setInt(2, schedule.getEmployeeID());
            statement.setInt(3, schedule.getBranchID());
            statement.setString(4, schedule.getRole());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed to insert into schedule");
        }
    }

    public void delete(Integer ShiftID, Integer EmployeeID) throws SQLException {
        String query = "DELETE FROM schedule WHERE ShiftID = ? AND EmployeeID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ShiftID);
            statement.setInt(2, EmployeeID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed to delete from schedule");
        }
    }

    public ScheduleDTO getSchedule(Integer ShiftID, Integer EmployeeID) throws SQLException {
        String query = "SELECT * FROM schedule WHERE ShiftID = ? AND EmployeeID = ?";
        Integer BranchId = -1;
        String role = "";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ShiftID);
            statement.setInt(2, EmployeeID);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                BranchId = result.getInt("BranchID");
                role = result.getString("role");
            }
            result.close();
            connection.close();
            return new ScheduleDTO(ShiftID, EmployeeID, BranchId, role);
        } catch (SQLException e) {
            System.out.println("failed to get schedule");
        }
        return null;
    }

    public LinkedList<ScheduleDTO> Load() throws SQLException {
        String query = "SELECT * FROM schedule";
        LinkedList<ScheduleDTO> schedules = new LinkedList<>();
        Integer ShiftID = -1;
        Integer EmployeeID = -1;
        Integer BranchID = -1;
        String role = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                ShiftID = result.getInt("ShiftID");
                EmployeeID = result.getInt("EmployeeID");
                BranchID = result.getInt("BranchID");
                role = result.getString("role");
                schedules.add(new ScheduleDTO(ShiftID, EmployeeID, BranchID, role));
            }
            result.close();
            connection.close();
            return schedules;
        }catch (SQLException e) {
            System.out.println("failed to load schedule");
        }
        return null;
    }



}
