package HR.DataAccessLayer.HRData;

import HR.DomainLayer.BankAccount;


public class BankAccountDTO {
    private String username;
    private String password;
    private Integer balance;

    public BankAccountDTO(String username , String password , Integer balance){
        this.username = username;
        this.password = password;
        this.balance = balance;
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
