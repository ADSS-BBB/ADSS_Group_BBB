package HR.DataAccessLayer.HRData;

import HR.DomainLayer.BankAccount;


public class BankAccountDTO {
    private Integer employeeID;
    private String username;
    private String password;
    private Integer balance;

    public BankAccountDTO(Integer employeeID, String username , String password , Integer balance){
        this.employeeID = employeeID;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public BankAccount DTO2Object(){
        return new BankAccount(username, password, balance);
    }
}
