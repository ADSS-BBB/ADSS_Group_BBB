package HR.DataAccessLayer.HRData;

import java.sql.*;
import java.util.LinkedList;

public class PersonnelManagerDAO {
    private static PersonnelManagerDAO instance;
    private Connection connection;

    public static PersonnelManagerDAO getInstance() throws Exception{
        if (instance == null){
            instance = new PersonnelManagerDAO();
        }
        return instance;
    }

    private static Connection toConnect() throws SQLException {
        String url = "jdbc:sqlite:C:/Users/Win10/Desktop/ADSS_Group_BBB/ADSS_Group_BBB/SuperLee.db";
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return connection;
    }

    public PersonnelManagerDAO() throws Exception{
        this.connection = toConnect();
    }

    public void insert(PersonnelManagerDTO HRmanager) throws SQLException {
        String query = "INSERT INTO HRmanagers (name) VALUES (?)";
        try {
            connection = toConnect();
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
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()){
            connection = toConnect();
            while (result.next()){
                String name = result.getString("name");
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
