package HR.ServiceLayer;

import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.BranchPackage.BranchController;
import com.google.gson.Gson;

import java.util.HashMap;

public class BranchService {
     BranchController branchController;

     public BranchService(){
         branchController=BranchController.getInstance();
     }

     public String addBranch(Integer id , String location) throws Exception{
         try{
             String result = branchController.addBranch(id , location);
             return result;
         } catch (Exception e){
             return "failed while trying to add branch";
         }
     }

    public String removeBranch(Integer id) throws Exception{
        try{
            String result = branchController.removeBranch(id);
            return result;
        } catch (Exception e){
            return "failed while trying to remove branc";
        }
    }

    public String addEmployee(Integer employeeId, Integer branchId) throws Exception{
        try{
            String result = branchController.addEmployee(employeeId , branchId);
            return result;
        } catch (Exception e){
            return "failed while trying to add employee";
        }
    }

    public String removeEmployee(Integer employeeId, Integer branchId) throws Exception{
        try{
            String result = branchController.removeEmployee(employeeId , branchId);
            return result;
        } catch (Exception e){
            return "failed while trying to remove employee";
        }
    }

    public String addRole(Integer id , String role) throws Exception{
         try{
             String result = branchController.addRole(id,role);
             return result;
         } catch (Exception e){
             return "failed while trying to add role";
         }
    }

    public String removeRole(Integer id, String role) throws Exception{
        try{
            String result = branchController.removeRole(id, role);
            return result;
        } catch (Exception e){
            return "failed while trying to remove role";
        }
    }

    public String setShift1Hours(Integer branchId, Integer[] shift1Hours) throws Exception{
         try{
             Integer[] result = BranchController.getInstance().setShift1Hours(branchId,shift1Hours);
             Gson gson = new Gson();
             String json = gson.toJson(result);
             return json;
         } catch (Exception e){
             return "failed while trying to setShifthours1";
         }
    }

    public String setShift2Hours(Integer branchId, Integer[] shift2Hours) throws Exception{
        try{
            Integer[] result = BranchController.getInstance().setShift2Hours(branchId,shift2Hours);
            Gson gson = new Gson();
            String json = gson.toJson(result);
            return json;
        } catch (Exception e){
            return "failed while trying to setShifthours2";
        }
    }

    public String updateBranchShift(Integer id) throws Exception{
         try {
             HashMap<Integer, Branch> result = BranchController.getInstance().updateBranchShift(id);
             Gson gson = new Gson();
             String json = gson.toJson(result);
             return json;
         } catch (Exception e){
             return "failed while trying to update BranchHistory";
         }
    }



}
