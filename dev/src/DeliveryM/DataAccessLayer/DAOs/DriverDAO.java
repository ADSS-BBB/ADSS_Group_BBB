package DeliveryM.DataAccessLayer.DAOs;

import DeliveryM.DataAccessLayer.DTOs.DriverDTO;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO extends DataDAO {

    private final String driverTable = "Drivers";
    private Connection connection = null;
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public DriverDAO() throws ClassNotFoundException {
        super("Drivers");
        this.connection = connect();
    }


    public void addDriver(DriverDTO driver) throws SQLException {
        this.connection = connect();
        String sql = "INSERT INTO Drivers (humanId, name, licenseType, isAvailable) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, driver.getHumantid());
            stmt.setString(2, driver.getName());
            stmt.setString(3, driver.getLicenseType());
            stmt.setString(4, driver.getIsAvailable());
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
    public void deleteDriver(int humanId) throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM Drivers WHERE humanId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, humanId);
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

    public void deleteAllDrivers() throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM Drivers";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

    public void updateisAvailable(int humanId, String isAvailable) throws SQLException {
        this.connection = connect();
        String sql = "UPDATE Drivers SET isAvailable = ? WHERE humanId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isAvailable);
            stmt.setInt(2, humanId);
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

    public List<DriverDTO> getAllDrivers() {
        List<DriverDTO> drivers = new ArrayList<>();
        this.connection = connect();
        String sql = "SELECT * FROM Drivers";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int humanId = rs.getInt("humanId");
                String name = rs.getString("name");
                String licenseType = rs.getString("licenseType");
                String isAvailable = rs.getString("isAvailable");
                DriverDTO driver = new DriverDTO(humanId, name, licenseType, isAvailable);
                drivers.add(driver);
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
        return drivers;
    }
    public DriverDTO getDriverById(int id) throws SQLException {
        this.connection = connect();
        String query = "SELECT * FROM Drivers WHERE humanId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int humanId = rs.getInt("humanId");
                    String name = rs.getString("name");
                    String licenseType = rs.getString("licenseType");
                    String isAvailable = rs.getString("isAvailable");
                    DriverDTO driver = new DriverDTO(humanId, name, licenseType, isAvailable);
                    return driver;
                }
            }
        }catch (SQLException e) {
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
        return null;
}
}
