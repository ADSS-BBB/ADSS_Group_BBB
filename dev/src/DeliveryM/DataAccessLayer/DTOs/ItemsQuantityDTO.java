package DeliveryM.DataAccessLayer.DTOs;

public class ItemsQuantityDTO extends DataDTO {

    private int QId;
    private String ItemName;
    private int quantity;
    private int deliveryId;
    private int itemWeight;

    public ItemsQuantityDTO(int QId, String ItemName, int quantity, int deliveryId,int itemWeight) {
        this.QId = QId;
        this.ItemName = ItemName;
        this.quantity = quantity;
        this.deliveryId = deliveryId;
        this.itemWeight=itemWeight;
    }

    // Getters and Setters
    public int getQId() {
        return QId;
    }
    public int getItemWeight() {
        return itemWeight;
    }
    public void setQId(int QId) {
        this.QId = QId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }
}
