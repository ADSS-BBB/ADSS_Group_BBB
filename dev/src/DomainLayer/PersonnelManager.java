package DomainLayer;

import java.util.HashMap;
import java.util.LinkedList;

public class PersonnelManager {
    private HashMap<Integer, Contract> Employees;
    private LinkedList<Integer> ShiftsHistory;
    private String name;

    public PersonnelManager(String name) {
        Employees = new HashMap<>();
        ShiftsHistory = new LinkedList<>();
        this.name = name;

    }
}
