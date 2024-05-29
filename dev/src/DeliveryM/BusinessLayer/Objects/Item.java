package DeliveryM.BusinessLayer.Objects;

public class Item{
	

	private int id;
	private String name;
	private int weight;

	public Item(int id ,String name,int weight){
		this.id=id;
		this.name=name;
		this.weight=weight;
	}

	public int getidItem(){return id;}
	public String getnameItem(){return name;}
	public int getItemweight(){return weight;}

}