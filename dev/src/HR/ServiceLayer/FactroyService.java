package HR.ServiceLayer;

public class FactroyService {
    private ShiftService shiftService;
    private EmployeeService employeeService;
    private BranchService branchService;

    public FactroyService() {
        this.shiftService = new ShiftService();
        this.employeeService = new EmployeeService();
        this.branchService = new BranchService();

    }
}
