package HR.DataAccessLayer.HRData;

import java.sql.Connection;

public class BankAccountDAO {
    private static BankAccountDAO instance;
    private Connection connection;

    public static BankAccountDAO getInstance() {
        if (instance == null){
            instance = new BankAccountDAO();
        }
        return instance;
    }

    public BankAccountDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }
}
