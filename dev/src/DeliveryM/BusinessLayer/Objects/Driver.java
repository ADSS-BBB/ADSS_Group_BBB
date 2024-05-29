package BusinessLayer.Objects;

import java.util.List;

public class Driver {

	private int id;
	private String name;
	private String licenseType;
	private boolean isAvailable;
	private List<LocItemDoc> documents;

	public Driver(int id ,String name ,String licensetype,boolean isAvailable,List<LocItemDoc> documents){

		this.id=id;
		this.name=name;
		this.licenseType =licensetype;
		this.isAvailable=isAvailable;
		this.documents=documents;
		
	}

	public int getIdDriver(){return id;}
	public String getNameDriver(){return name;}
	public String getLicenseTypeDriver(){return licenseType;}
	public boolean getIsAvailableDriver(){return isAvailable;}
	public void setIsAvailableDriver(boolean newis){this.isAvailable=newis;}




}