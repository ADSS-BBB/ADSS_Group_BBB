package HR.DomainLayer.ShiftPackage;

import HR.DomainLayer.BranchPackage.BranchController;
import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.util.HashMap;

public class ShiftController {
    private static ShiftController instance;
    private HashMap<Integer, Shift> shifts = new HashMap<>();

    public static ShiftController getInstance() {
        if (instance == null) {
            instance = new ShiftController();
        }
        return instance;
    }

    public Shift getShift(Integer ShiftId) throws Exception {
        if (shifts.containsKey(ShiftId)){
            return shifts.get(ShiftId);
        }
        throw new Exception("Shift is not existed");
    }

    public HashMap<Integer, Shift> getShifts(){
        return shifts;
    }

    public String setMinWorkers(Integer ShiftId, Integer minWorkers) throws Exception {
        if (ShiftId == null || ShiftId < 0){
            throw new Exception("ShiftId is null");
        }
        if (!shifts.containsKey(ShiftId)){
            throw new Exception("Shift is not existed");
        }
        return shifts.get(ShiftId).setMinWorkers(minWorkers);
    }

    public String addEmployee(Integer ShiftId, Integer WorkerId) throws Exception {
        if (ShiftId == null || ShiftId < 0){
            throw new Exception("ShiftId is null");
        }
        if (!shifts.containsKey(ShiftId)){
            throw new Exception("Shift is not existed");
        }
        return shifts.get(ShiftId).AddEmployee(WorkerId);
    }

    public String removeEmployee(Integer ShiftId, Integer WorkerId) throws Exception {
        if (ShiftId == null || ShiftId < 0){
            throw new Exception("ShiftId is null");
        }
        if (!shifts.containsKey(ShiftId)){
            throw new Exception("Shift is not existed");
        }
        return shifts.get(ShiftId).RemoveEmployee(WorkerId);
    }

    public String addCancellation(Integer transictionId, Integer ShiftId) throws Exception {
        if (ShiftId == null || ShiftId < 0){
            throw new Exception("ShiftId is null");
        }
        if (!shifts.containsKey(ShiftId)){
            throw new Exception("Shift is not existed");
        }
        return shifts.get(ShiftId).AddCancelation(transictionId);
    }

    public String addShift(Integer ShiftId, Integer ShiftManagerId , Integer MinWorkers, String Type, Integer BranchId) throws Exception {
        if (ShiftId == null || ShiftId < 0){
            throw new Exception("ShiftId is null");
        }
        if (shifts.containsKey(ShiftId)){
            throw new Exception("Shift already exist");
        }
        if (ShiftManagerId == null || ShiftManagerId < 0){
            throw new Exception("ShiftManagerId is null");
        }
        if (!EmployeeController.getInstance().getEmployee(ShiftManagerId).getRoles().contains("Shift Manager")){
            throw new Exception("Employee is not a Shift Manager");
        }
        if (MinWorkers == null || MinWorkers < 0){
            throw new Exception("MinWorkers is null");
        }
        if (Type == null || Type.equals("")){
            throw new Exception("Type is null");
        }
        if (!Type.equals("Morning") && !Type.equals("Evening")){
            throw new Exception("Type is invalid");
        }
        if (BranchId == null || BranchId < 0){
            throw new Exception("BranchId is null");
        }
        if (!BranchController.getInstance().getBranches().containsKey(BranchId)){
            throw new Exception("Branch is not existed");
        }
        shifts.put(ShiftId, new Shift(ShiftId, ShiftManagerId, MinWorkers, Type, BranchId));
        return "Shift added successfully";

    }


}
