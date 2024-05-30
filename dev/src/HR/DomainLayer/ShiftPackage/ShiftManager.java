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
}
