package DeliveryM.BusinessLayer.Objects;

public class Item{

	private String name;
	private int weight;
	private int destinationid;
	public Item(String name,int weight,int destinationid){
		this.name=name;
		this.weight=weight;
		this.destinationid=destinationid;
	}

	public int getDestinationId(){return destinationid;}
	public String getNameItem(){return name;}
	public int getItemWeight(){return weight;}

}