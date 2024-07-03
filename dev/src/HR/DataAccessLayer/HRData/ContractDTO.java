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
    private Date startDate;

    public ContractDTO(Integer employeeID, Integer contractID, Integer branchID, Integer salary, String employmentType, java.sql.Date startDate){
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

    public Date getStartDate() {
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Contract DTO2Object() {
        LocalDate start = startDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return new Contract(contractID, salary, branchID, employmentType, start);
    }
}
