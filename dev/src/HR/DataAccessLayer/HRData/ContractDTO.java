package HR.DataAccessLayer.HRData;

import HR.DomainLayer.Contract;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class ContractDTO {
    private Integer employeeID;
    private Integer contractID;
    private Integer branchID;
    private Integer salary;
    private String employmentType;
    private String startDate;

    public ContractDTO(Integer employeeID, Integer contractID, Integer branchID, Integer salary, String employmentType, String startDate){
        this.employeeID = employeeID;
        this.contractID = contractID;
        this.branchID = branchID;
        this.salary = salary;
        this.employmentType = employmentType;
        this.startDate = startDate;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getContractID() {
        return contractID;
    }

    public Integer getBranchID() {
        return branchID;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void setContractID(Integer contractID) {
        this.contractID = contractID;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Contract DTO2Object() {
        String[] date = startDate.split("-");
        LocalDate start = LocalDate.of(Integer.parseInt(date[2]),Integer.parseInt(date[1]),Integer.parseInt(date[0]));
        return new Contract(contractID, salary, branchID, employmentType, start);
    }
}
