package DomainLayer.EmployeePackage;

import DomainLayer.BankAccount;
import DomainLayer.Contract;

import java.util.HashMap;
import java.util.LinkedList;

public class Employee {
    private Integer EmployeeID;
    private String Username;
    private Contract Dealerdetails;
    private BankAccount BankAccount;
    private HashMap<Integer, Boolean> WeeklyShifts;
    private LinkedList<Integer> ShiftsHistory;


    public Employee(Integer EmployeeID , String Username,Contract Dealerdetails, BankAccount BankAccount) {
        this.EmployeeID = EmployeeID;
        this.Username = Username;
        this.Dealerdetails = Dealerdetails;
        this.BankAccount = BankAccount;
        WeeklyShifts = new HashMap<>();
        ShiftsHistory = new LinkedList<>();

    }
}
