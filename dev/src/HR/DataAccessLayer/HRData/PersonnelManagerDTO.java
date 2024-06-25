package HR.DataAccessLayer.HRData;

import HR.DomainLayer.Contract;

import java.util.HashMap;
import java.util.LinkedList;

public class PersonnelManagerDTO {
    private String name;
    private HashMap<Integer , Contract> employees;
    private LinkedList<Integer> shiftsHistory;
    private HashMap<Integer , LinkedList<Integer>> availableEmployees;
    private HashMap<Integer , HashMap<Integer, String>> schedule;
}
