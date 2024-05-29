package DomainLayer.BranchPackage;

public class BranchController {
    private static BranchController instance;

    public static BranchController getInstance() {
        if (instance == null) {
            instance = new BranchController();

        }
        return instance;

    }
}
