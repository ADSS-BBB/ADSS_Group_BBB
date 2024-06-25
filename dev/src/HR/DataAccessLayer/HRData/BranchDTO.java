package HR.DataAccessLayer.HRData;

import java.util.LinkedList;

public class BranchDTO {
    private Integer branchID;
    private String location;
    private LinkedList<String> roles;
    private LinkedList<Integer> branchEmployees;
    private LinkedList<Integer> branchShiftsList;

    public BranchDTO() {}
}
