package DeliveryM.DataAccessLayer.DAOs;

import DeliveryM.DataAccessLayer.DTOs.DriverDTO;
import DeliveryM.DataAccessLayer.DTOs.ShiftDTO;
import DeliveryM.DataAccessLayer.DTOs.StorekeeperDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShiftDAO extends DataDAO{
    private final String shiftsTable = "Shifts";
    private Connection connection = null;
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public ShiftDAO() throws ClassNotFoundException {
        super("Shifts");
        this.connection = connect();
    }

    public void addshift(ShiftDTO shift) throws SQLException {
        this.connection = connect();
        String sql = "INSERT INTO Shifts (shiftId, startingTime, endingTime, driverId,storeKeeperId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shift.getShiftId());
            stmt.setString(2, shift.getStartingTime());
            stmt.setString(3, shift.getEndingTime());
            stmt.setInt(4, shift.getDriverId());
            stmt.setInt(5, shift.getStoreKeeperId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public void deleteshift(int shiftid,int driverid,int storekeeperid) throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM Shifts WHERE shiftId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, shiftid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    public List<ShiftDTO> getAllShifts() {
        List<ShiftDTO> shifts = new ArrayList<>();
        this.connection = connect();
        String sql = "SELECT * FROM Shifts";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("shiftId");
                String startingTime = rs.getString("startingTime");
                String endingTime = rs.getString("endingTime");
                int driverId = rs.getInt("driverId");
                int storeKeeperId = rs.getInt("storeKeeperId");
                ShiftDTO toAdd = new ShiftDTO(id,startingTime,endingTime,driverId,storeKeeperId);
                shifts.add(toAdd);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return shifts;
    }
}
