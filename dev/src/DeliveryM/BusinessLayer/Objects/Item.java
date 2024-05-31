package DeliveryM.BusinessLayer.Objects;

public class Item{
	

	private int id;
	private String name;
	private int weight;
	private int quantity;
	private int destinationid;
	public Item(int id ,String name,int weight,int quantity,int destinationid){
		this.id=id;
		this.name=name;
		this.weight=weight;
		this.quantity=quantity;
		this.destinationid=destinationid;
	}
	public int getDestinationid(){return destinationid;}
	public int getIdItem(){return id;}
	public String getNameItem(){return name;}
	public int getItemWeight(){return weight;}
	public int getQuantity(){ return quantity;}

}