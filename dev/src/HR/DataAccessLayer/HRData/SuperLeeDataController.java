package HR.DataAccessLayer.HRData;

import DeliveryM.BusinessLayer.Controllers.DriverController;
import HR.DomainLayer.BankAccount;
import HR.DomainLayer.Contract;
import HR.DomainLayer.EmployeePackage.Employee;

import java.io.File;
import java.nio.file.Paths;
import java.sql.*;

public class SuperLeeDataController {
    private static Connection connection;
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

    public static SuperLeeDataController getInstance() throws Exception{
        if (instance == null){
            instance = new SuperLeeDataController();
        }
        return instance;
    }

    public SuperLeeDataController() throws Exception{
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

    private static Connection toConnect() throws ClassNotFoundException {
        String url = "jdbc:sqlite:src/SuperLee.db";
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public String LoadData() throws Exception {
        shiftDAO.LoadData();
        branchDAO.LoadData();
        employeeDAO.LoadData();
        rolesDAO.LoadData();
        weeklyShiftsDAO.LoadData();
        DriverController.getInstance().loadData();
        return  "dataLoaded";
    }

    public String deleteData() throws Exception{
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM bankaccounts");
            statement.executeUpdate("DELETE FROM branches");
            statement.executeUpdate("DELETE FROM contracts");
            statement.executeUpdate("DELETE FROM employees");
            statement.executeUpdate("DELETE FROM HRmanagers");
            statement.executeUpdate("DELETE FROM Roles");
            statement.executeUpdate("DELETE FROM schedule");
            statement.executeUpdate("DELETE FROM shifts");
            statement.executeUpdate("DELETE FROM shiftshistory");
            statement.executeUpdate("DELETE FROM weeklyShifts");
            statement.executeUpdate("DELETE FROM Drivers");
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("failed in deleting data");
        }
        return "dataDeleted";
    }
    //bank
    public void insertbankaccount(Integer employeeID, String username, String password, Integer balance ) throws SQLException {
        BankAccountDTO bank = new BankAccountDTO(employeeID, username, password, balance);
        bankAccountDAO.insert(bank);
    }
    public void deletebankaccount(String username) throws SQLException {
        bankAccountDAO.delete(username);
    }
    public void editbankbalance(String username, Integer balance) throws SQLException {
        bankAccountDAO.editbankbalance(username, balance);
    }
    public void editbankpassword(String username, String password) throws SQLException {
        bankAccountDAO.editbankpassword(username, password);
    }
    //branch
    public void insertbranch(Integer branchid, String location) throws SQLException {
        BranchDTO branch = new BranchDTO(branchid, location);
        branchDAO.insert(branch);
    }
    public void deletebranch(Integer branchid) throws SQLException {
        branchDAO.delete(branchid);
    }
    public void editlocation(Integer branchid, String location) throws SQLException {
        branchDAO.editlocation(branchid, location);
    }
    public BranchDTO getBranch(Integer branchid) throws SQLException {
        return branchDAO.getBranch(branchid);
    }
    //contract
    public void insertcontract(Integer employeeID, Integer contractid, Integer branchid, Integer salary, String employmentType, String startdate) throws SQLException {
        ContractDTO contract  = new ContractDTO(employeeID, contractid, branchid, salary, employmentType, startdate);
        contractDAO.insert(contract);
    }
    public void deletecontract(Integer contractid) throws SQLException {
        contractDAO.delete(contractid);
    }
    public void editemployemenType(Integer contractid, String employmentType) throws SQLException {
        contractDAO.editEmploymentType(contractid, employmentType);
    }
    public void editsalary(Integer contractid, Integer salary) throws SQLException {
        contractDAO.editSalary(contractid, salary);
    }
    //employee
    public void insertemployee(Integer employeeid,String employeename, String baankusername, Integer contractid, Integer branchid) throws Exception {
        EmployeeDTO employee = new EmployeeDTO(employeeid, employeename, baankusername, contractid, branchid);
        employeeDAO.insert(employee);
    }
    public void deletetemployee(Integer employeeid) throws Exception {
        employeeDAO.delete(employeeid);
    }
    public EmployeeDTO getEmployee(Integer employeeid) throws SQLException {
        return employeeDAO.getEmployee(employeeid);
    }
    //perssonelmanager
    public void insertpersonnelmanager(String name) throws SQLException {
        PersonnelManagerDTO personnelManager = new PersonnelManagerDTO(name);
        personnelManagerDAO.insert(personnelManager);
    }
    //roles
    public void insertroles(Integer employeeid, Integer branchid, String role) throws SQLException {
        RolesDTO roles = new RolesDTO(employeeid, branchid, role);
        rolesDAO.insert(roles);
    }
    public void deleteroles(Integer employeeid, String role) throws SQLException {
        rolesDAO.delete(employeeid, role);
    }
    //schedule
    public void insertschedule(Integer shiftid, Integer employeeid, Integer branchid, String role) throws SQLException {
        ScheduleDTO schedule = new ScheduleDTO(shiftid, employeeid, branchid, role);
        scheduleDAO.insert(schedule);
    }
    public void deleteschedule(Integer shiftid, Integer employeeid) throws SQLException {
        scheduleDAO.delete(shiftid, employeeid);
    }
    public ScheduleDTO getSchedule(Integer shiftid, Integer employeeid) throws SQLException {
        return scheduleDAO.getSchedule(shiftid, employeeid);
    }
    //shift
    public void insertshift(Integer shiftid, Integer branchid, String type, Integer minworkers, String time) throws SQLException {
        ShiftDTO shift = new ShiftDTO(shiftid, branchid, type, minworkers, time);
        shiftDAO.insert(shift);
    }
    public void deleteshift(Integer shiftid) throws SQLException {
        shiftDAO.delete(shiftid);
    }
    public ShiftDTO getShift(Integer shiftid) throws SQLException {
        return shiftDAO.getShift(shiftid);
    }
    public void editminworkers(Integer shiftid, Integer minworkers) throws SQLException {
        shiftDAO.editMinWorkers(shiftid, minworkers);
    }
    //shifthistory
    public void insertshifthistory(Integer employeeid, Integer branchid, Integer shiftid) throws SQLException {
        ShiftsHistoryDTO shiftsHistory = new ShiftsHistoryDTO(employeeid, branchid,shiftid );
        shiftsHistoryDAO.insert(shiftsHistory);
    }
    public void deleteshifthistory(Integer shiftid, Integer employeeid) throws SQLException {
        shiftsHistoryDAO.delete(shiftid, employeeid);
    }
    //weeklyshifts
    public void insertweeklyshifts(Integer employeeid, Integer branchid, Integer shiftid) throws SQLException {
        WeeklyShiftsDTO weeklyShifts = new WeeklyShiftsDTO(employeeid, branchid, shiftid);
        weeklyShiftsDAO.insert(weeklyShifts);
    }
    public void deleteweeklyshifts(Integer shiftid, Integer employeeid) throws SQLException {
        weeklyShiftsDAO.delete(shiftid, employeeid);
    }


}
