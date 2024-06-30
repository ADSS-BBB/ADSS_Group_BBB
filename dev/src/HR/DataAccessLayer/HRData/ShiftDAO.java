package HR.DataAccessLayer.HRData;

import java.sql.Connection;

public class ShiftDAO {
    private static ShiftDAO instance;
    private Connection connection;

    public static ShiftDAO getInstance() {
        if (instance == null){
            instance = new ShiftDAO();
        }
        return instance;
    }

    public ShiftDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

}
