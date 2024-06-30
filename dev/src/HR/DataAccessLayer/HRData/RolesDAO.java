package HR.DataAccessLayer.HRData;

import java.sql.Connection;

public class RolesDAO {
    private static RolesDAO instance;
    private Connection connection;

    public static RolesDAO getInstance() {
        if (instance == null){
            instance = new RolesDAO();
        }
        return instance;
    }

    public RolesDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

}
