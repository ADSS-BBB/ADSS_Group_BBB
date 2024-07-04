package DeliveryM.DataAccessLayer.DAOs;

import DeliveryM.DataAccessLayer.DTOs.ItemsQuantityDTO;
import DeliveryM.DataAccessLayer.DTOs.LocItemDocDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocItemDocDAO extends DataDAO{
    private final String locItemDocsTable = "LocItemDocs";
    private Connection connection = null;
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public LocItemDocDAO() throws ClassNotFoundException {
        super("LocItemDocs");
        this.connection = connect();
    }


    public void addLocDocs(LocItemDocDTO docs) throws SQLException {
        this.connection = connect();
        String sql = "INSERT INTO LocItemDocs (deliveryId, docid, locId, currecntTruckWeight,Qid,driverId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, docs.getDeliveryId());
            stmt.setInt(2, docs.getDocumentationId());
            stmt.setString(3,docs.getLocationId());
            stmt.setInt(4, docs.getCurrentTruckWeight());
            stmt.setInt(5, docs.getQid());
            stmt.setInt(6, docs.getDriverId());
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
    public void deleteDocs(int docsid) throws SQLException {
        this.connection = connect();
        String sql = "DELETE FROM LocItemDocs WHERE docid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, docsid);
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

    public List<LocItemDocDTO> getAlllocitemdoc() {
        List<LocItemDocDTO> locdocs = new ArrayList<>();
        this.connection = connect();
        String sql = "SELECT * FROM LocItemDocs";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int deliveryId = rs.getInt("deliveryId");
                int  docid = rs.getInt("docid");
                String locid = rs.getString("locid");
                int currecntTruckWeight = rs.getInt("currecntTruckWeight");
                int Qid = rs.getInt("Qid");
                int driverId=rs.getInt("driverId");
                LocItemDocDTO toadd = new LocItemDocDTO(deliveryId,docid,locid,currecntTruckWeight,Qid,driverId);
                locdocs.add(toadd);
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
        return locdocs;
    }

}
