package HR.DataAccessLayer.HRData;

import java.time.LocalDate;
import java.util.LinkedList;

public class ShiftDTO {
    private Integer shiftID;
    private Integer branchID;
    private String type;
    private Integer minWorkers;
    private LinkedList<Integer> employees;
    private LinkedList<Integer> cancelaations;
    private LocalDate time;
}
