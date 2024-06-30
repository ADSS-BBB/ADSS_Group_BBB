package HR.DataAccessLayer.HRData;

import java.sql.Connection;

public class EmployeeDAO {
    private static EmployeeDAO instance;
    private Connection connection;

    public static EmployeeDAO getInstance() {
        if (instance == null){
            instance = new EmployeeDAO();
        }
        return instance;
    }

    public EmployeeDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }
}
