package HR.DomainLayer.ShiftPackage;

import HR.DataAccessLayer.HRData.SuperLeeDataController;
import HR.DomainLayer.BranchPackage.BranchController;
import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ShiftController {
    SuperLeeDataController superLeeDataController = SuperLeeDataController.getInstance();
    private static ShiftController instance;
    private HashMap<Integer, Shift> shifts = new HashMap<>();

    public ShiftController() throws Exception {
    }

    public static ShiftController getInstance() throws Exception {
        if (instance == null) {
            instance = new ShiftController();
        }
        return instance;
    }

    public Shift getShift(Integer ShiftId) throws Exception {
        if (ShiftId == null) {
            throw new Exception("Shift ID is null");
        }
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
        superLeeDataController.editminworkers(ShiftId, minWorkers);
        return shifts.get(ShiftId).setMinWorkers(minWorkers);
    }

    public String addEmployee(Integer ShiftId, Integer WorkerId, String role) throws Exception {
        if (ShiftId == null || ShiftId < 0){
            throw new Exception("ShiftId is null");
        }
        if (!shifts.containsKey(ShiftId)){
            throw new Exception("Shift is not existed");
        }
        superLeeDataController.insertschedule(ShiftId, WorkerId, EmployeeController.getInstance().getEmployee(WorkerId).getBranchId(), role);
        return shifts.get(ShiftId).AddEmployee(WorkerId);
    }

    public String removeEmployee(Integer ShiftId, Integer WorkerId) throws Exception {
        if (ShiftId == null || ShiftId < 0){
            throw new Exception("ShiftId is null");
        }
        if (!shifts.containsKey(ShiftId)){
            throw new Exception("Shift is not existed");
        }
        superLeeDataController.deleteschedule(ShiftId, WorkerId);
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

    public String addShift(Integer ShiftId, LocalDate time , Integer MinWorkers, String Type, Integer BranchId) throws Exception {
        if (ShiftId == null || ShiftId < 0){
            throw new Exception("ShiftId is null");
        }
        if (shifts.containsKey(ShiftId)){
            throw new Exception("Shift already exist");
        }
        if (time == null || time.isBefore(LocalDate.now())){
            throw new Exception("time is null");
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
        if (!shifts.isEmpty()){
            for (Shift shift : shifts.values()){
                if (shift.getTime().equals(time) && shift.getType().equals(Type) && shift.getBranchId().equals(BranchId)){
                    throw new Exception("shift already exist in this day and type");
                }
            }
        }
        shifts.put(ShiftId, new Shift(ShiftId , time, MinWorkers, Type, BranchId));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = time.format(formatter);
        superLeeDataController.insertshift(ShiftId, BranchId, Type, MinWorkers, formattedDate);
        return "Shift added successfully";
    }

    public String Dtoaddshift(Integer ShiftId, LocalDate time , Integer MinWorkers, String Type, Integer BranchId) throws Exception{
        shifts.put(ShiftId, new Shift(ShiftId, time, MinWorkers, Type, BranchId));
        return "succesfully added shift from data";
    }
    //this function is in progress and it should check if there is a storekeeper in the shift
    public String posibletodelivery(String deliverytime){
        return "to be implemented";
    }

    //for testing


    public static void setInstancetonull(ShiftController instance) {
        ShiftController.instance = null;
    }
}
