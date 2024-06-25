package HR.ServiceLayer;

import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.ShiftPackage.Shift;
import HR.DomainLayer.ShiftPackage.ShiftController;
import HR.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class ShiftService {

    ShiftController shiftController;

    public ShiftService() {
        shiftController = ShiftController.getInstance();

    }

    public String addShift(Integer ShiftId, LocalDate time , Integer MinWorkers, String Type, Integer BranchId) throws Exception {
        try{
            String result = shiftController.addShift(ShiftId,time, MinWorkers, Type, BranchId);
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

    public String getShift(Integer ShiftId) throws Exception{
        try {
            Shift result = ShiftController.getInstance().getShift(ShiftId);
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            String json = gson.toJson(result);
            return json;
        } catch (Exception e) {
            return "failed while trying to retrieve shift";
        }
    }


}
