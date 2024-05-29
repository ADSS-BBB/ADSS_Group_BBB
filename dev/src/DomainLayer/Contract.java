package DomainLayer;

import java.time.LocalDate;
import java.util.LinkedList;

public class Contract {
    private Integer ContractID;
    private String BranchLocation;
    private LocalDate StartDate;
    private LinkedList<String> roles;
    private Integer Salary;
    private String EmploymentType;

    public Contract(Integer ContractID, Integer Salary, String BranchLocation, String EmploymentType) {
        this.ContractID = ContractID;
        this.BranchLocation = BranchLocation;
        this.StartDate = LocalDate.now();
        this.Salary = Salary;
        this.EmploymentType = EmploymentType;
        this.roles = new LinkedList<>();

    }


}
