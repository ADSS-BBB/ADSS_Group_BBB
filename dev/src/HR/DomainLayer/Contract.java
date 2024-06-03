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


    public Integer getContractID() {
        return ContractID;
    }

    public LocalDate getStartDate() {
        return StartDate;
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

    public String setSalary(Integer salary) throws Exception{
        if (salary == null || salary < 0){
            throw new Exception("Salary cannot be null or negative");
        }
        this.Salary = salary;
        return "salary updated";
    }

    public String setEmploymentType(String newType) throws Exception{
        if (newType == null || newType.equals("")){
            return "employment type cannot be null or empty";
        }
        if (!newType.equals("full") && !newType.equals("partial")){
            throw new Exception("Invalid Type");
        }
        if (newType.equals(EmploymentType)){
            throw new Exception("already in this type");
        }
        this.EmploymentType = newType;
        return "employment type updated";
    }
    public boolean isEmpty(){
        if (ContractID == null || BranchId == null || StartDate == null || Salary == null || EmploymentType == null){
            return true;
        }
        return false;
    }
}
