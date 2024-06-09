package DeliveryM;
import DeliveryM.PresentationLayer.Application;
import DeliveryM.PresentationLayer.Application;
import DeliveryM.ServiceLayer.MainApplication;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws Exception {
        MainApplication service=new MainApplication();
        Application menu = new Application();
        menu.menu();
    }
}
