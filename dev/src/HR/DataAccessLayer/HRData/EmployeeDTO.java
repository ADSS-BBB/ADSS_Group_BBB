package HR.DataAccessLayer.HRData;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.Contract;
import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.util.LinkedList;

public class EmployeeDTO {
    private Integer employeeID;
    private String employeeName;
    private String bankUsername;
    private Integer contractID;
    private Integer branchID;

    public EmployeeDTO(Integer employeeID, String employeeName, String bankUsername, Integer contractID, Integer branchID, String role){
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.bankUsername = bankUsername;
        this.contractID = contractID;
        this.branchID = branchID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getBankUsername() {
        return bankUsername;
    }

    public Integer getContractID() {
        return contractID;
    }

    public Integer getBranchID() {
        return branchID;
    }


    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setBankUsername(String bankUsername) {
        this.bankUsername = bankUsername;
    }

    public void setContractID(Integer contractID) {
        this.contractID = contractID;
    }

    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }


    public Employee DTO2Object() throws Exception{
        try {
            Contract contract = EmployeeController.getInstance().getEmployee(employeeID).getDealdetails();
            BankAccount bankAccount = EmployeeController.getInstance().getEmployee(employeeID).getBankAccount();
            return new Employee(employeeID, employeeName, contract,bankAccount);
        } catch (Exception e){
            System.out.println("failed in employee DTO");
        }
        throw new Exception("failed in employee DTO");
    }


}
