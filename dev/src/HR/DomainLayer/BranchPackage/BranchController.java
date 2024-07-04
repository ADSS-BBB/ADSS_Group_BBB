package HR.DomainLayer.BranchPackage;

import HR.DataAccessLayer.HRData.SuperLeeDataController;
import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.util.HashMap;
import java.util.LinkedList;

public class BranchController {
    SuperLeeDataController superLeeDataController = SuperLeeDataController.getInstance();
    private static BranchController instance;
    private HashMap<Integer, Branch> branches = new HashMap<>();

    public BranchController() throws Exception {
    }

    public static BranchController getInstance() throws Exception{
        if (instance == null) {
            instance = new BranchController();
        }
        return instance;
    }

    public LinkedList<String> getRoles(Integer branchID) throws Exception {
        if (branches.containsKey(branchID)){
            return branches.get(branchID).getRoles();
        }
        throw new Exception("Branch not found");
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


    public String addRole(Integer branchId, String role) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        superLeeDataController.insertroles(null, branchId, role);
        return branches.get(branchId).AddRole(role);
    }

    public String removeRole(Integer branchId, String role) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        superLeeDataController.deleteroles(branchId, role);
        return branches.get(branchId).RemoveRole(role);
    }

    public String addEmployee(Integer employeeId, Integer branchId) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        branches.get(branchId).AddBranchEmployee(employeeId);
        return "employee added successfully";

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
        superLeeDataController.insertbranch(branchId, location);
        return "Branch added successfully";
    }

    public String addBranchfromDTO(Integer branchId, String location) throws Exception{
        branches.put(branchId, new Branch(location, branchId));
        return "succesfully added";
    }

    public String removeBranch(Integer branchId) throws Exception {
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        branches.remove(branchId);
        superLeeDataController.deletebranch(branchId);
        return "Branch removed successfully";
    }

    public HashMap<Integer, Branch> updateBranchShift(Integer branchId) throws Exception{
        if (branchId == null || branchId < 0){
            throw new Exception("Branch id is null");
        }
        if (!branches.containsKey(branchId)){
            throw new Exception("Branch is not existed");
        }
        branches.get(branchId).updateBranchShift();
        return branches;
    }

    //for testing


    public static void setInstancetonull(BranchController instance) {
        BranchController.instance = null;
    }
}
