package DeliveryM.BusinessLayer.Objects;

import java.util.List;

public class Driver {

	private int id;
	private int humanId;
	private String name;
	private String licenseType;
	private boolean isAvailable;
	private List<LocItemDoc> documents;

	public Driver(int id,int driverId ,String name ,String licensetype){

		this.id=id;
		this.name=name;
		this.humanId =driverId;
		this.licenseType =licensetype;
		this.isAvailable=true;
		this.documents= null;
		
	}

	public int getIdDriver(){return id;}
	public String getNameDriver(){return name;}
	public String getLicenseTypeDriver(){return licenseType;}
	public boolean getIsAvailableDriver(){return isAvailable;}
	public void setIsAvailableDriver(boolean newis){this.isAvailable=newis;}

	public void addDoc(LocItemDoc doc){
		this.documents.add(doc);
	}
	public void deleteDoc(LocItemDoc dec){
		for (int i=0;i<this.documents.size();i++){
			if(documents.get(i).getDocId()==dec.getDocId())
				this.documents.remove(i);
		}
	}
	public void deleteDocByDelId(int delId){
		for (int i=0;i<documents.size();i++){
			if(documents.get(i).getDeliveryId()==delId)
				documents.remove(i);
		}
	}



}