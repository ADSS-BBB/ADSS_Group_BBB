package DeliveryM.BusinessLayer.Objects;

public class Item{
	

	private int id;
	private String name;
	private int weight;
	private int quantity;

	public Item(int id ,String name,int weight,int quantity){
		this.id=id;
		this.name=name;
		this.weight=weight;
		this.quantity=quantity;
	}

	public int getIdItem(){return id;}
	public String getNameItem(){return name;}
	public int getItemWeight(){return weight;}
	public int getQuantity(){ return quantity;}

}