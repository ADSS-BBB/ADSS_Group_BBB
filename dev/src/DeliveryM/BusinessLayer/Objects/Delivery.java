public class Delivery {
	
	private int id;
	private String date;
	private String time;
	private int truckNumber;
	private String source;
	private List<String> destinations;
	private int truckWeight;
	private Map<String,int> items; //name , quantity according to moodle
	private boolean status;


	public Delivery(int id ,String date ,String time ,int truckNumber ,String source,List<String> destinations,int truckWeight,Map<String,int> items,boolean status ){
		this.id=id;
		this.date=date;
		this.time=time;
		this.truckNumber=truckNumber;
		this.source=source;
		this.destinations=destination;
		this.truckWeight=truckWeight;
		this.items=items;//need to import item
		this.status=status;//or delete it from the constructor and automatic false

	
	}
	public int getid(){return id;}
	public String getdate(){return date;}
	public String gettime(){return time;}
	public int getrucknumber(){return truckNumber;}
	public String getsource(){return source;}
	public List<String> getdestinations(){return destination;}
	public int getruckweight(){return truckWeight;}
	public Map<String,int> getitems(){return items;}
	public boolean getstatusD(){return status;}
	public void setstatusD(boolean status1){status=status1}



}