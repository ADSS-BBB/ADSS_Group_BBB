package DeliveryM.DataAccessLayer.DAOs;

import DeliveryM.DataAccessLayer.DTOs.DriverDTO;
import DeliveryM.DataAccessLayer.DTOs.TruckDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TruckDAO extends DataDAO {
    private final String truckTable = "Trucks";
    private Connection connection = null;
    private static final String DB_URL = "jdbc:sqlite:SuperLee.db";

    public TruckDAO() throws ClassNotFoundException {
        super("Trucks");
        this.connection = connect();
    }

    public void addTruck(TruckDTO truck) throws SQLException {
        this.connection = connect();
        String sql = "INSERT INTO Trucks (number, model, weight, maxWeight, isAvaliable) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, truck.getNumber());
            stmt.setString(2, truck.getModel());
            stmt.setInt(3, truck.getWeight());
            stmt.setInt(4, truck.getMaxWeight());
            stmt.setString(5, truck.isAvailable());
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
    public void deleteAllTrucks() throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM Trucks";
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

    public void deleteTruck(int number) throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM Trucks WHERE number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, number);
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

    public void updateisAvailable(int id, String isAvailable) throws SQLException {
        this.connection = connect();
        String sql = "UPDATE Trucks SET isAvaliable = ? WHERE number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, isAvailable);
            stmt.setInt(2, id);
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

    public List<TruckDTO> getAllTrucks() {
        List<TruckDTO> trucks = new ArrayList<>();
        this.connection = connect();
        String sql = "SELECT * FROM Trucks";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int number = rs.getInt("number");
                String model = rs.getString("model");
                int weight = rs.getInt("weight");
                int maxWeight = rs.getInt("maxWeight");
                String isAvailable = rs.getString("isAvaliable");
                TruckDTO truck = new TruckDTO(number, model, weight,maxWeight ,isAvailable);
                trucks.add(truck);
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
        return trucks;
    }
}
