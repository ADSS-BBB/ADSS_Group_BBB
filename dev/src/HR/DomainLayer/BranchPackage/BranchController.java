package HR.DomainLayer.BranchPackage;

import java.util.HashMap;

public class BranchController {
    private static BranchController instance;
    private HashMap<Integer, Branch> branches;

    public static BranchController getInstance() {
        if (instance == null) {
            instance = new BranchController();

        }
        return instance;
    }

    public Branch getBranch(Integer id) throws Exception {
        if (branches.containsKey(id)){
            return branches.get(id);
        }
        throw new Exception("Employee is not existed");
    }

    public HashMap<Integer, Branch> getBranches(){
        return branches;
    }

}
