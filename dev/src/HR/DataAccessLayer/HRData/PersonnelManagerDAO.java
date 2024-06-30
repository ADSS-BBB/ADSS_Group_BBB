package HR.DataAccessLayer.HRData;

import java.sql.Connection;

public class PersonnelManagerDAO {
    private static PersonnelManagerDAO instance;
    private Connection connection;

    public static PersonnelManagerDAO getInstance() {
        if (instance == null){
            instance = new PersonnelManagerDAO();
        }
        return instance;
    }

    public PersonnelManagerDAO() {
        this.connection = SuperLeeDataController.getInstance().getConnection();
    }
}
