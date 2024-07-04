package DeliveryM.DataAccessLayer.DAOs;

import DeliveryM.DataAccessLayer.DTOs.DriverDTO;
import DeliveryM.DataAccessLayer.DTOs.LocationDTO;
import DeliveryM.DataAccessLayer.DTOs.TruckDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO extends DataDAO {

    private final String locationTable = "Locations";
    private Connection connection = null;
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public LocationDAO() throws ClassNotFoundException {
        super("Locations");
        this.connection = connect();
    }

    public void addLocation(LocationDTO location) throws SQLException {
        this.connection = connect();
        String sql = "INSERT INTO Locations (Id, address, contactNumber, contactName, deliveryId, area) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, location.getId());
            stmt.setString(2, location.getAddress());
            stmt.setString(3, location.getContactNumber());
            stmt.setString(4, location.getContactName());
            stmt.setInt(5,location.getDeliveryId()); // Assuming isAvailable is a boolean
            stmt.setString(6, location.getArea());
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

    public void deleteLocation(int id) throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM Locations WHERE Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
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

    public void updateDelivery(String address, int deliveryid) throws SQLException {
        this.connection = connect();
        String sql = "UPDATE Locations SET deliveryId = ? WHERE address = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, deliveryid);
            stmt.setString(2, address);
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

    public List<LocationDTO> getAllLocations() {
        List<LocationDTO> locations = new ArrayList<>();
        this.connection = connect();
        String sql = "SELECT * FROM Locations";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String address = rs.getString("address");
                String contactNumber = rs.getString("contactNumber");
                String contactName = rs.getString("contactName");
                int deliveryId = rs.getInt("deliveryId");
                String area=rs.getString("area");
                LocationDTO locationDTO = new LocationDTO(id,address,contactNumber,contactName,deliveryId,area);
                locations.add(locationDTO);
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
        return locations;
    }
}
