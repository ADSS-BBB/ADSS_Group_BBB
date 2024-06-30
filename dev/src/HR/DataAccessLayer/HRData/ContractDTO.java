package HR.DataAccessLayer.HRData;

import HR.DomainLayer.Contract;

import java.time.LocalDate;

public class ContractDTO {
    private Integer contractID;
    private Integer branchID;
    private Integer salary;
    private String employmentType;
    private LocalDate startDate;

    public ContractDTO(Integer contractID, Integer branchID, Integer salary, String employmentType, LocalDate startDate){
        this.contractID = contractID;
        this.branchID = branchID;
        this.salary = salary;
        this.employmentType = employmentType;
        this.startDate = startDate;
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

    public LocalDate getStartDate() {
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Contract DTO2Object() {
        return new Contract(contractID, salary, branchID, employmentType, startDate);
    }
}
