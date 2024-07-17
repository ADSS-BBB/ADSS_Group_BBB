package HR.DomainLayer.ShiftPackage;

import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class Shift {
    private Integer ShiftId;
    private LocalDate Time;
    private LinkedList<Integer> Employees;
    private LinkedList<Integer> Cancelations;
    private Integer MinWorkers;
    private String Type;
    private Integer BranchId;


    public Shift(Integer ShiftId, LocalDate time, Integer MinWorkers, String Type, Integer BranchId) throws Exception {
        this.ShiftId = ShiftId;
        this.Time = time;
        this.MinWorkers = MinWorkers;
        Employees = new LinkedList<>();
        Cancelations = new LinkedList<>();
        this.Type = Type;
        this.BranchId = BranchId;
        ShiftController.getInstance().getShifts().put(ShiftId , this);

    }
    public Shift getShift(Integer ShiftId) {
        return this;
    }
    public Integer getShiftId() {
        return ShiftId;
    }
    public LocalDate getTime() {
        return Time;
    }
    public String getType() {
        return Type;
    }
    public Integer getBranchId() {
        return BranchId;
    }

    public LinkedList<Integer> getEmployees() {
        return Employees;
    }
    public LinkedList<Integer> getCancelation() {
        return Cancelations;
    }
    public Integer getMinWorkers() {
        return MinWorkers;
    }
    public String setMinWorkers(Integer MinWorkers) throws Exception {
        if (MinWorkers == null || MinWorkers <= 0) {
            throw new Exception("MinWorkers cannot be negative");
        }
        if (MinWorkers > Employees.size()) {
            throw new Exception("MinWorkers cannot be greater than all Employees");
        }
        this.MinWorkers = MinWorkers;
        return "Minimum workers set successfully";
    }

    public String AddEmployee(Integer WorkerId) throws Exception {
        if (WorkerId == null || WorkerId < 0) {
            throw new Exception("WorkerId cannot be null or empty");
        }
        if (this.Employees.contains(WorkerId)){
            throw new Exception("Employee already exists");
        }
        if (!EmployeeController.getInstance().getEmployees().containsKey(WorkerId)) {
            throw new Exception("Employee does not exist");
        }
        if (!EmployeeController.getInstance().getEmployee(WorkerId).getWeeklyAvailableShifts().contains(ShiftId)) {
            throw new Exception("Employee is not available");
        }
        Employees.add(WorkerId);
        return "Employee added successfully";
    }
    public String RemoveEmployee(Integer WorkerId) throws Exception {
        if (WorkerId == null || WorkerId < 0) {
            throw new Exception("WorkerId cannot be null or empty");
        }
        if (!EmployeeController.getInstance().getEmployees().containsKey(WorkerId)) {
            throw new Exception("Employee does not exist");
        }
        if (!this.Employees.contains(WorkerId)) {
            throw new Exception("Employee is not working");
        }
        Employees.remove(WorkerId);
        return "Employee removed successfully";
    }

    public String AddCancelation(Integer TransactionId) throws Exception {
        if (TransactionId == null || TransactionId < 0) {
            throw new Exception("TransactionId cannot be null or empty");
        }
        if (this.Cancelations.contains(TransactionId)) {
            throw new Exception("Cancelation already exists");
        }
        Cancelations.add(TransactionId);
        return "Cancelation added successfully";
    }







}
