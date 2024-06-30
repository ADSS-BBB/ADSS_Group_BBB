package HR.DataAccessLayer.HRData;

import java.sql.Connection;

public class ScheduleDAO {
    private static ScheduleDAO instance;
    private Connection connection;

    public static ScheduleDAO getInstance() {
        if (instance == null){
            instance = new ScheduleDAO();
        }
        return instance;
    }

    public ScheduleDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

}
