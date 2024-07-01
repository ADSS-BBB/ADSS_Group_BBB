package HR.DataAccessLayer.HRData;

import java.sql.*;
import java.util.LinkedList;

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

    public void insert(PersonnelManagerDTO HRmanager) throws SQLException {
        String query = "INSERT INTO HRmanagers (name) VALUES (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, HRmanager.getName());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("failed in adding managerdto");
        }
    }

    public LinkedList<PersonnelManagerDTO> Load() throws SQLException {
        String query = "SELECT * FROM HRmanagers";
        LinkedList<PersonnelManagerDTO> HRmanagers = new LinkedList<>();
        String name;
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()){
                name = result.getString("name");
                HRmanagers.add(new PersonnelManagerDTO(name));
            }
            result.close();
            connection.close();
            return HRmanagers;
        } catch (SQLException e) {
            System.out.println("failed in load managers");
        }
        return null;
    }

}
