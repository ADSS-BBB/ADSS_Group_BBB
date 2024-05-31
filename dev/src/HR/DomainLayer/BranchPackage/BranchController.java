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
        throw new Exception("Branch is not existed");
    }

    public HashMap<Integer, Branch> getBranches(){
        return branches;
    }

    public String setShift1Hours(Integer branchId, Integer[] shift1Hours) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        return branches.get(branchId).setShift1Hours(shift1Hours);
    }

    public String setShift2Hours(Integer branchId, Integer[] shift2Hours) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        return branches.get(branchId).setShift2Hours(shift2Hours);
    }

    public String addRole(Integer branchId, String role) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        return branches.get(branchId).AddRole(role);
    }

    public String removeRole(Integer branchId, String role) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        return branches.get(branchId).RemoveRole(role);
    }

    public String addEmployee(Integer employeeId, Integer branchId) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        return branches.get(branchId).AddBranchEmployee(employeeId);

    }

    public String removeEmployee(Integer employeeId, Integer branchId) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        return branches.get(branchId).RemoveBranchEmployee(employeeId);
    }


    public String addBranch(Integer branchId, String location) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (branches.containsKey(branchId)){
            throw new Exception("Branch already existed");
        }
        if (location == null || location.equals("")){
            throw new Exception("Location is null");
        }
        branches.put(branchId, new Branch(location, branchId));
        return "Branch added successfully";
    }

    public String removeBranch(Integer branchId) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        branches.remove(branchId);
        return "Branch removed successfully";
    }

    public String updateBranchShift(Integer branchId) throws Exception{
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        return branches.get(branchId).updateBranchShift();

    }

}
