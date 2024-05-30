package HR.DomainLayer;

public class BankAccount {
    private String Username;
    private String Password;
    private Integer Balance;

    public BankAccount(String username, String password, Integer balance) {
        this.Username = username;
        this.Password = password;
        this.Balance = balance;
    }

    public Integer getBalance() {
        return Balance;
    }

    public String getPassword() {
        return Password;
    }

    public String getUsername() {
        return Username;
    }
}
