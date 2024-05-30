package HR.DomainLayer;

import java.time.LocalDate;
import java.util.LinkedList;

public class Contract {
    private Integer ContractID;
    private Integer BranchId;
    private LocalDate StartDate;
    private Integer Salary;
    private String EmploymentType;

    public Contract(Integer ContractID, Integer Salary, Integer BranchId, String EmploymentType) {
        this.ContractID = ContractID;
        this.BranchId = BranchId;
        this.StartDate = LocalDate.now();
        this.Salary = Salary;
        this.EmploymentType = EmploymentType;

    }

    public Integer getSalary(){
        return this.Salary;
    }

    public void setSalary(Integer salary){
        this.Salary = salary;
    }

    public Integer getBranchId() {
        return BranchId;
    }

    public void setBranchId(Integer Id) {
        this.BranchId = Id;
    }

    public String getEmploymentType() {
        return EmploymentType;
    }

    public void setEmploymentType(String employmentType) {
        EmploymentType = employmentType;
    }
}
