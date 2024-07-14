package HR.DataAccessLayer.HRData;

import HR.DomainLayer.ShiftPackage.Shift;
import HR.DomainLayer.ShiftPackage.ShiftController;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;

public class ShiftDAO {
    private static ShiftDAO instance;
    private Connection connection;

    public static ShiftDAO getInstance() throws Exception{
        if (instance == null){
            instance = new ShiftDAO();
        }
        return instance;
    }

    private static Connection toConnect() throws SQLException {
        String url = "jdbc:sqlite:C:/Users/Win10/Desktop/ADSS_Group_BBB/ADSS_Group_BBB/SuperLee.db";
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return connection;
    }

    public ShiftDAO() throws Exception{
        this.connection = toConnect();
    }

    public void insert(ShiftDTO shift) throws SQLException {
        String query = "INSERT INTO shifts (shiftID, branchID, type, minWorkers, time) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, shift.getShiftID());
            statement.setInt(2, shift.getBranchID());
            statement.setString(3, shift.getType());
            statement.setInt(4, shift.getMinWorkers());
            statement.setString(5, shift.getTime());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println("can't isnert a shift");
        }
    }

    public void delete(Integer shiftID) throws SQLException{
        String query = "DELETE FROM shifts WHERE shiftID = ?";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, shiftID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("can't delete a shift");
        }
    }

    public void editMinWorkers (Integer shiftID, Integer MinWorkers) throws SQLException{
        String query = "UPDATE shifts SET minWorkers = ? WHERE shiftID =?";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, MinWorkers);
            statement.setInt(2, shiftID);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in editing min workers");
        }
    }

    public ShiftDTO getShift(Integer shiftID) throws SQLException {
        String query = "SELECT * FROM shifts WHERE shiftID = ?";
        Integer branchID = -1;
        String type = "";
        Integer minWorkers= -1;
        String time = "";
        try {
            connection = toConnect();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, shiftID);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                branchID = result.getInt("branchID");
                type = result.getString("type");
                minWorkers = result.getInt("minWorkers");
                time = result.getString("time");
            }
            result.close();
            connection.close();
            return new ShiftDTO(shiftID, branchID, type, minWorkers, time);
        } catch (SQLException e) {
            System.out.println("failed in getting shiftdto");
        }
        return null;
    }

    public LinkedList<ShiftDTO> Load() throws SQLException {
        String query = "SELECT * FROM shifts";
        LinkedList<ShiftDTO> shifts = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()){
            connection = toConnect();
            while (result.next()){
                Integer shiftID = result.getInt("shiftID");
                Integer branchID = result.getInt("branchID");
                String type = result.getString("type");
                Integer minWorkers = result.getInt("minWorkers");
                String time = result.getString("time");
                shifts.add(new ShiftDTO(shiftID, branchID, type, minWorkers, time));
            }
            result.close();
            connection.close();
            return shifts;
        } catch (SQLException e) {
            System.out.println("failed in loading shifts");
        }
        return null;
    }

    public void LoadData() throws Exception{
        for (ShiftDTO shift : Load()){
            String[] date = shift.getTime().split("-");
            LocalDate time = LocalDate.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
            ShiftController.getInstance().Dtoaddshift(shift.getShiftID(), time, shift.getMinWorkers(),shift.getType(), shift.getBranchID());
        }
    }
}
