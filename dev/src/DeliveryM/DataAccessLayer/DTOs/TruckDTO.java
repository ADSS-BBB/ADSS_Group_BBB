package DeliveryM.DataAccessLayer.DTOs;

public class TruckDTO extends DataDTO {
    private int number;
    private final String numberColumn = "number";
    private String model;
    private final String modelColumn = "model";
    private int weight;
    private final String  weightColumn="weight";
    private int maxWeight;
    private final String maxWeightColumn="maxWeight";
    private String isAvailable; // Corrected to boolean for clarity
    private final String isAvaliableColumn="isAvaliable";
    public TruckDTO(int number, String model, int weight, int maxWeight, String isAvailable) {
        this.number = number;
        this.model = model;
        this.weight = weight;
        this.maxWeight = maxWeight;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String isAvailable() {
        return isAvailable;
    }

    public void setAvailable(String available) {
        isAvailable = available;
    }


}
