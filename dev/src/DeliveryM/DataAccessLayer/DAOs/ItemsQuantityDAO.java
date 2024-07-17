package DeliveryM.DataAccessLayer.DAOs;

import DeliveryM.DataAccessLayer.DTOs.DriverDTO;
import DeliveryM.DataAccessLayer.DTOs.ItemsQuantityDTO;
import DeliveryM.DataAccessLayer.DTOs.LocationDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsQuantityDAO extends DataDAO {

    private final String itemsQuantityTable = "ItemQuantity";
    private Connection connection = null;
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public ItemsQuantityDAO() throws ClassNotFoundException {
        super("ItemQuantity");
        this.connection = connect();
    }


    public void addItemquantity(ItemsQuantityDTO item) throws SQLException {
        this.connection = connect();
        String sql = "INSERT INTO ItemQuantity (Qid, itemName, quantity, deliveryId, itemWeight) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, item.getQId());
            stmt.setString(2, item.getItemName());
            stmt.setInt(3,item.getQuantity());
            stmt.setInt(4, item.getDeliveryId());
            stmt.setInt(5, item.getItemWeight());
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





    //need to check it and add update not delete
    public void deleteitemquantity(int itemid) throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM ItemQuantity WHERE Qid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemid);
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

    public void deleteAllItemQuantities() throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM ItemQuantity";
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


    public List<ItemsQuantityDTO> getAllItemsQuantity() {
        List<ItemsQuantityDTO> quantities = new ArrayList<>();
        this.connection = connect();
        String sql = "SELECT * FROM ItemQuantity";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Qid");
                String itemName = rs.getString("itemName");
                int quantity = rs.getInt("quantity");
                int deliveryId = rs.getInt("deliveryId");
                int itemWeight = rs.getInt("itemWeight");
                ItemsQuantityDTO toadd = new ItemsQuantityDTO(id,itemName,quantity,deliveryId,itemWeight);
                quantities.add(toadd);
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
        return quantities;
    }

    public List<ItemsQuantityDTO> getspecfic(int qid) {
        List<ItemsQuantityDTO> quantities = new ArrayList<>();
        this.connection = connect();
        String sql = "SELECT * FROM ItemQuantity WHERE Qid = ?"; // Corrected the SQL query to use Qid

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, qid); // Set the qid parameter in the query
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Qid");
                String itemName = rs.getString("itemName");
                int quantity = rs.getInt("quantity");
                int deliveryId = rs.getInt("deliveryId");
                int itemWeight = rs.getInt("itemWeight");

                ItemsQuantityDTO toadd = new ItemsQuantityDTO(id, itemName, quantity, deliveryId, itemWeight);
                quantities.add(toadd);
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

        return quantities;
    }


}
