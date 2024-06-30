package HR.DataAccessLayer.HRData;

import java.sql.Connection;

public class ContractDAO {
    private static ContractDAO instance;
    private Connection connection;

    public static ContractDAO getInstance() {
        if (instance == null){
            instance = new ContractDAO();
        }
        return instance;
    }

    public ContractDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }

}
