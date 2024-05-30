package HR.DomainLayer.ShiftPackage;

import java.util.HashMap;

public class ShiftController {
    private static ShiftController instance;
    private HashMap<Integer, Shift> shifts;

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
        throw new Exception("Employee is not existed");
    }

    public HashMap<Integer, Shift> getShifts(){
        return shifts;
    }

}
