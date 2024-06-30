package HR.DataAccessLayer.HRData;

import java.sql.Connection;

public class ShiftsHistoryDAO {
    private static ShiftsHistoryDAO instance;
    private Connection connection;

    public static ShiftsHistoryDAO getInstance() {
        if (instance == null){
            instance = new ShiftsHistoryDAO();
        }
        return instance;
    }

    public ShiftsHistoryDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

}
