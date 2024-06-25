package HR.DataAccessLayer.HRData;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.Contract;

import java.util.HashMap;
import java.util.LinkedList;

public class EmployeeDTO {
    private Integer employeeID;
    private String employeeName;
    private BankAccount bankaccount;
    private Contract DealDetails;
    private LinkedList<String> roles;
    private Integer branchID;
    private HashMap<Integer,Boolean> WeeklyShifts;
    private LinkedList<Integer> ShiftsHistory;
}
