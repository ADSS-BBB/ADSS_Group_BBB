package DomainLayer.BranchPackage;

import java.util.LinkedList;

public class Branch {
    private String Location;
    private Integer BranchId;
    private Integer ShiftId1;
    private Integer ShiftId2;
    private LinkedList<String> roles;

    public Branch(String Location, Integer BranchId, Integer ShiftId1, Integer ShiftId2) {
        this.Location = Location;
        this.BranchId = BranchId;
        this.ShiftId1 = ShiftId1;
        this.ShiftId2 = ShiftId2;
        roles = new LinkedList<>();

    }
}
