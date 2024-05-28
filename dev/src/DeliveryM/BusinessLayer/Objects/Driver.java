public class Driver {

	private int id;
	private String name;
	private String licensetype;
	private boolean isAvailable;

	public Driver(int id ,String name ,String licensetype,boolean isAvailable){

		this.id=id;
		this.name=name;
		this.licensetype=licensetype;
		this.isAvailable=isAvailable;
		
	}

	public int getidDriver(){return id;}
	public String getnameDriver(){return name;}
	public String getlicensetypeDriver(){return licensetype;}
	public boolean getisAvailableDriver(){return isAvailable;}
	public void getisAvailableDriver(newis){this.isAvailable=newis}




}