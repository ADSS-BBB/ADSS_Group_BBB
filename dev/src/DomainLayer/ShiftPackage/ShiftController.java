package DomainLayer.ShiftPackage;

public class ShiftController {
    private static ShiftController instance;
    public static ShiftController getInstance() {
        if (instance == null) {
            instance = new ShiftController();

        }
        return instance;
    }
}
