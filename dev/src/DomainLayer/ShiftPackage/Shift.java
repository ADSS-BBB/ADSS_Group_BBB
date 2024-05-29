package DomainLayer.ShiftPackage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class Shift {
    private Integer ShiftId;
    private String Type;
    private LocalDateTime Time;
    private Integer ShiftManagerId;
    private HashMap<Integer, String> Employees;
    private LinkedList<Integer> Cancelation;
    private Integer MinWorkers;


    public Shift(Integer ShiftId, String Type, Integer ShiftManagerId , Integer MinWorkers) {
        this.ShiftId = ShiftId;
        this.Type = Type;
        this.Time = LocalDateTime.now();
        this.ShiftManagerId = ShiftManagerId;
        this.MinWorkers = MinWorkers;
        Employees = new HashMap<>();
        Cancelation = new LinkedList<>();

    }
}
