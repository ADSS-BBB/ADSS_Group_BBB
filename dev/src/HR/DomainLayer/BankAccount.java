package HR.DomainLayer;

import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.util.HashMap;
import java.util.LinkedList;

public class BankAccount {
    private String Username;
    private String Password;
    private Integer Balance;

    public BankAccount(String username, String password, Integer balance) {
        this.Username = username;
        this.Password = password;
        this.Balance = balance;

    }
    public String getUsername() {
        return Username;
    }
    public String setUsername(String username) throws Exception {
        if (username == null || username.equals("")) {
            throw new Exception("Username cannot be null or empty");
        }
        try {
            HashMap<Integer, Employee> employees = EmployeeController.getInstance().getEmployees();
            for (Employee employee : employees.values()) {
                if (employee.getBankAccount().getUsername().equals(username)) {
                    throw new Exception("Username is already taken");
                }
            }
        } catch (Exception e){}

        if (username.equals(this.Username)) {
            throw new Exception("Username are the same");
        }

        this.Username = username;
        return "Username successfully updated";
    }
    public String getPassword() {
        return Password;
    }
    public String setPassword(String password) throws Exception {
        if (password == null || password.equals("")) {
            throw new Exception("Password cannot be null or empty");
        }
        if (password.equals(this.Password)) {
            throw new Exception("Passwords are the same");
        }
        this.Password = password;
        return "Password successfully updated";
    }
    public Integer getBalance() {
        return Balance;
    }
    public void setBalance(Integer balance) {
        Balance = balance;
    }
    public boolean isEmpty(){
        if (Username == null || Username.equals("") || Password == null || Password.equals("")) {
            return true;
        }
        return false;
    }

}
