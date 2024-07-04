package DeliveryM.DataAccessLayer.DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataDAO {
    protected String _table;
    private static final String DATABASE_URL = "jdbc:sqlite:src/DeliveryM/DataAccessLayer/database.db";

    // Constructor
    public DataDAO(String table) {
        this._table = table;
    }

    // Method to establish a connection to the SQLite database
    protected Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
            //System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
