package HR.DataAccessLayer.HRData;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SuperLeeDataController {
    private Connection connection;
    private static SuperLeeDataController instance;
    private BankAccountDAO bankAccountDAO;
    private BranchDAO branchDAO;
    private ContractDAO contractDAO;
    private EmployeeDAO employeeDAO;
    private PersonnelManagerDAO personnelManagerDAO;
    private RolesDAO rolesDAO;
    private ScheduleDAO scheduleDAO;
    private ShiftDAO shiftDAO;
    private ShiftsHistoryDAO shiftsHistoryDAO;
    private WeeklyShiftsDAO weeklyShiftsDAO;

    public static SuperLeeDataController getInstance() {
        if (instance == null){
            instance = new SuperLeeDataController();
        }
        return instance;
    }

    public SuperLeeDataController() {
        this.connection = toConnect();
        this.bankAccountDAO = BankAccountDAO.getInstance();
        this.branchDAO = BranchDAO.getInstance();
        this.employeeDAO = EmployeeDAO.getInstance();
        this.contractDAO = ContractDAO.getInstance();
        this.personnelManagerDAO = PersonnelManagerDAO.getInstance();
        this.rolesDAO = RolesDAO.getInstance();
        this.scheduleDAO = ScheduleDAO.getInstance();
        this.shiftDAO = ShiftDAO.getInstance();
        this.weeklyShiftsDAO = WeeklyShiftsDAO.getInstance();
        this.shiftsHistoryDAO = ShiftsHistoryDAO.getInstance();
    }

    private static Connection toConnect() {
        String path = Paths.get("").toAbsolutePath().toString();
        String _connectionString = "jdbc:sqlite:" + path+"/superli.db";
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(_connectionString);
        } catch (SQLException e) {}
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

}
