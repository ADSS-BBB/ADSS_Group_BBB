package DeliveryM.DataAccessLayer.DTOs;

public class DriverDTO extends DataDTO {

    private int humantid;
    private final String humanidColumn = "humantId";
    private String name;
    private final String nameColumn = "name";
    private String licenseType;
    private final String licenseTypeColumn = "licenseType";
    private String isAvailable;
    private final String isAvailableColumn = "isAvailable";

    public DriverDTO(int humantid, String name, String licenseType, String isAvailable) {
        this.humantid = humantid;
        this.name = name;
        this.licenseType = licenseType;
        this.isAvailable = isAvailable;
    }

    public int getHumantid() {
        return humantid;
    }

    public void setHumantid(int humantid) {
        this.humantid = humantid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getHumanidColumn() {
        return humanidColumn;
    }

    public String getNameColumn() {
        return nameColumn;
    }

    public String getLicenseTypeColumn() {
        return licenseTypeColumn;
    }

    public String getIsAvailableColumn() {
        return isAvailableColumn;
    }
}
