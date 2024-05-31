package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DriverController {

    private List<Driver> drivers;
    private Map<String,Integer> licenseWeight;
    private int docId;
    private int driverId;
    public DriverController(){
        this.drivers=new LinkedList<>();
        this.driverId=0;
        licenseWeight=new HashMap<>();
        docId=0;
        fillLicense();

    }
    //add
    //update isAvailable
    private void fillLicense(){
        //+ the truck's weight
        //C-> 1 - 7500
        //C1-> 7500 - 11000
        //E -> 11000 - 40000
        licenseWeight.put("C",7500);
        licenseWeight.put("C1",11000);
        licenseWeight.put("E",40000);

    }

    public void addDriver(int humanId,String name,String licenseType){
        drivers.add(new Driver(driverId,humanId,name,licenseType));
        driverId++;

    }
    public boolean deleteDriver(String name,String licenseType) throws Exception{
        List<Driver> current=drivers;
        for (int i=0;i<current.size();i++){
            if(current.get(i).getNameDriver().equals(name)){
                drivers.remove(i);
                return true;
            }
        }
        return false;
        //throw new Exception("driver does not exist!");
    }
    public boolean updateIsAvalible(int driverId){
        for (int i=0;i<drivers.size();i++) {
            if (drivers.get(i).getIdDriver() == driverId) {
                drivers.get(i).setIsAvailableDriver(!drivers.get(i).getIsAvailableDriver());
                return true;
            }
        }
        return false;
    }

    public Driver findDriver(int sum) {

        String licenseType="C";
        for (Map.Entry<String, Integer> entry : licenseWeight.entrySet()) {
            String type = entry.getKey();
            Integer limit = entry.getValue();
            if(sum<=limit)
                licenseType=type;
        }
        for (int i=0;i<drivers.size();i++) {
            Driver driver=drivers.get(i);
            if (driver.getIsAvailableDriver() && driver.getLicenseTypeDriver().equals(licenseType)) {
                updateIsAvalible(driver.getIdDriver());
                return driver;
            }
        }
        return  null;
    }

    private void sendDecuments(int driverId,List<LocItemDoc> docs) {
        for (int i = 0; i < drivers.size(); i++) {
            Driver driver = drivers.get(i);
            if (driver.getIdDriver() == driverId) {
                for (int j = 0; j < docs.size(); j++) {
                    driver.addDoc(docs.get(i));
                }
            }
        }
    }
    public void createSendDoc(Delivery delivery, int driverId){
        //for each destination must be a doc
        List<LocItemDoc> docs=new LinkedList<>();
        List<Location> delDest= delivery.getDestinations();
        List<Item> delItems=delivery.getItems();
        Map<String,Integer> itemQuantity=new HashMap<>();
        for (int i=0;i<delDest.size();i++){
            Location curr=delDest.get(i);
            for (int j=0;j<delItems.size();j++){
                if(delItems.get(j).getDestinationid()== curr.getlocationid())
                    itemQuantity.put(delItems.get(j).getNameItem(),delItems.get(j).getQuantity());
            }
            //adding the docs
            LocItemDoc doc=new LocItemDoc(docId,delivery,itemQuantity);
            docId++;
            docs.add(doc);

        }
        sendDecuments(driverId,docs);
    }

    public void deleteDoc ( int driverId, int delId){
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getIdDriver() == driverId)
                drivers.get(i).deleteDocByDelId(delId);
        }
    }


}
