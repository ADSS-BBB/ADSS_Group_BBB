package DeliveryM.DataAccessLayer.DAOs;

import DeliveryM.DataAccessLayer.DTOs.StorekeeperDTO;
import DeliveryM.DataAccessLayer.DTOs.TruckDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StorekeeperDAO extends DataDAO{
    private final String stroekeeperTable = "StroeKeepers";
    private Connection connection = null;
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public StorekeeperDAO() throws ClassNotFoundException {
        super("StroeKeepers");
        this.connection = connect();
    }


    public void addStorekeeper(StorekeeperDTO storekeeper) throws SQLException {
        this.connection = connect();
        String sql = "INSERT INTO StoreKeepers (storeKeeperid, workAddress) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, storekeeper.getStoreKeeperId());
            stmt.setString(2, storekeeper.getWorkAddress());
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
    //there is no delete for store keeper
    public void deleteStorekeeper(int id) throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM StoreKeepers WHERE storeKeeperid = ?";
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

    public List<StorekeeperDTO> getAllStoreKeepes() {
        List<StorekeeperDTO> keepers = new ArrayList<>();
        this.connection = connect();
        String sql = "SELECT * FROM StoreKeepers";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("storeKeeperid");
                String adr = rs.getString("workAddress");
                StorekeeperDTO toAdd = new StorekeeperDTO(id,adr);
                keepers.add(toAdd);
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
        return keepers;
    }
}
