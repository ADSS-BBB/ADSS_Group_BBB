package HR.DataAccessLayer.HRData;

import HR.DomainLayer.ShiftPackage.Shift;

import java.time.LocalDate;
import java.util.LinkedList;

public class ShiftDTO {
    private Integer shiftID;
    private Integer branchID;
    private String type;
    private Integer minWorkers;
    private LocalDate time;

    public ShiftDTO(Integer shiftID, Integer branchID, String type, Integer minWorkers, LocalDate time){
        this.shiftID = shiftID;
        this.branchID = branchID;
        this.type = type;
        this.minWorkers = minWorkers;
        this.time = time;
    }

    public Integer getShiftID() {
        return shiftID;
    }

    public Integer getBranchID() {
        return branchID;
    }

    public String getType() {
        return type;
    }

    public Integer getMinWorkers() {
        return minWorkers;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setShiftID(Integer shiftID) {
        this.shiftID = shiftID;
    }

    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    public void setMinWorkers(Integer minWorkers) {
        this.minWorkers = minWorkers;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Shift DTO2Object() {
        return new Shift(shiftID,time,minWorkers,type,branchID);
    }
}
