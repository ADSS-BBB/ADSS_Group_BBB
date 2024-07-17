package DeliveryM.DataAccessLayer.DAOs;

import DeliveryM.DataAccessLayer.DTOs.DeliveryDTO;
import DeliveryM.DataAccessLayer.DTOs.DriverDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAO extends DataDAO {

    private final String deliveryTable = "Deliverys";
    private Connection connection = null;
    private static final String DB_URL = "jdbc:sqlite:database.db";


    public DeliveryDAO() throws ClassNotFoundException {
        super("Deliverys");
        this.connection = connect();
    }

    public void addDelivery(DeliveryDTO delivery) throws SQLException {
        this.connection = connect();
        String sql = "INSERT INTO Deliverys (id, exitTime, arrivalTime, truckId, driverId, sourceId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, delivery.getId());
            stmt.setString(2, delivery.getExitTime());
            stmt.setString(3, delivery.getArrivalTime());
            stmt.setInt(4, delivery.getTruckId());
            stmt.setInt(5, delivery.getDriverId());
            stmt.setInt(6, delivery.getSourceId());
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
    public void updatetruck(int id, int trucknum) throws SQLException {
        this.connection = connect();
        String sql = "UPDATE Deliverys SET truckId = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,trucknum );
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
    public void updatedriver(int id, int trucknum) throws SQLException {
        this.connection = connect();
        String sql = "UPDATE Deliverys SET driverId = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,trucknum );
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
    public void deleteDelivery(int id) throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM Deliverys WHERE id = ?";
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
    public void deleteAllDeliveries() throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM Deliverys";
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



    public List<DeliveryDTO> getAllDeliveries() {
        List<DeliveryDTO> deliveries = new ArrayList<>();
        this.connection = connect();
        String sql = "SELECT * FROM Deliverys";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String exitTime = rs.getString("exitTime");
                String arrivalTime = rs.getString("arrivalTime");
                int truckId = rs.getInt("truckId");
                int driverId = rs.getInt("driverId");
                int source = rs.getInt("sourceId");
                DeliveryDTO driver = new DeliveryDTO(id,exitTime,arrivalTime,truckId,driverId,source);
                deliveries.add(driver);
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
        return deliveries;
    }
}
