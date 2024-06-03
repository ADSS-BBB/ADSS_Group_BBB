package HR.ServiceLayer;

import HR.DomainLayer.ShiftPackage.ShiftController;
import com.google.gson.Gson;

public class ShiftService {

    ShiftController shiftController;

    public ShiftService() {
        shiftController = ShiftController.getInstance();

    }

    public String addShift(Integer ShiftId, Integer ShiftManagerId , Integer MinWorkers, String Type, Integer BranchId) throws Exception {
        try{
            String result = shiftController.addShift(ShiftId, ShiftManagerId, MinWorkers, Type, BranchId);
            return result;
        }
        catch (Exception e){
            return "failed while trying to add shift";
        }
    }

    public String addEmployee(Integer shiftid, Integer workerid) throws Exception{
        try {
            String result = shiftController.addEmployee(shiftid, workerid);
            return result;
        }
        catch (Exception e){
            return "failed while trying to add employee";
        }
    }

    public String removeEmployee(Integer shiftid, Integer workerid) throws Exception{
        try {
            String result = shiftController.removeEmployee(shiftid, workerid);
            return result;
        }
        catch (Exception e){
            return "failed while trying to remove employee";
        }
    }

    public String addCancellation(Integer transctionid ,Integer shiftid) throws Exception{
        try {
            String result = shiftController.addCancellation(transctionid, shiftid);
            return result;
        }
        catch (Exception e){
            return "failed while trying to add cancellation";
        }
    }

    public String setMinWorkers(Integer shiftid, Integer minworkers) throws Exception{
        try {
            String result = shiftController.setMinWorkers(shiftid, minworkers);
            return result;
        }
        catch (Exception e){
            return "failed while trying to set minimum workers";
        }
    }


}
