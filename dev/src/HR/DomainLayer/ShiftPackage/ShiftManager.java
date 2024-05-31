package HR.DomainLayer.ShiftPackage;

import HR.DomainLayer.Contract;

public class ShiftManager {
    private Integer Id;
    private Contract Dealdetails;
    private String name;

    public ShiftManager(Integer Id, Contract Dealdetails, String name) {
        this.Id = Id;
        this.Dealdetails = Dealdetails;
        this.name = name;
    }

    public Contract getDealdetails() {
        return Dealdetails;
    }

    public Integer getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String setSalary(Integer salary) throws Exception{
        this.Dealdetails.setSalary(salary);
        return "salary updated";
    }

    public String setEmploymentType(String newType) throws Exception{
        this.Dealdetails.setEmploymentType(newType);
        return "Employment Type updated";
    }


}
