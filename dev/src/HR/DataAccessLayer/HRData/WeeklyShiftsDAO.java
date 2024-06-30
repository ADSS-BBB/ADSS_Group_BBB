package HR.DataAccessLayer.HRData;

import java.sql.Connection;

public class WeeklyShiftsDAO {
    private static WeeklyShiftsDAO instance;
    private Connection connection;

    public static WeeklyShiftsDAO getInstance() {
        if (instance == null){
            instance = new WeeklyShiftsDAO();
        }
        return instance;
    }

    public WeeklyShiftsDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

}
