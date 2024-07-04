package DeliveryM.BusinessLayer.Objects;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Driver {

	private int humanId;
	private String name;
	private String licenseType;
	private boolean isAvailable;
	private List<LocItemDoc> documents;
	private HashMap<Integer ,Shift> weeklyShifts;
	public Driver(int driverId, String name, String licenseType ) {
		this.humanId = driverId;
		this.name = name;
		this.licenseType = licenseType;
		this.isAvailable = true;
		this.documents = null;
		this.weeklyShifts=new HashMap<>();
	}

	public int getHumanId() {
		return humanId;
	}

	public void setHumanId(int humanId) {
		this.humanId = humanId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public List<LocItemDoc> getDocuments() {
		return documents;
	}

	public void setDocuments(List<LocItemDoc> documents) {
		this.documents = documents;
	}

	public int getMaxWeightToDriver(int humanId){
		return switch (licenseType) {
			case "C" -> 7000;
			case "C1" -> 12000;
			case "E" -> 20000;
			default -> 0;
		};
	}
	//add
	public Shift updateshift(int shiftid, LocalDateTime start, LocalDateTime end){
		Shift toadd=new Shift(shiftid,start,end);
		weeklyShifts.put(start.getDayOfWeek().getValue()+1,toadd);
		return toadd;
	}
	public LocalDateTime getstart(int day){
		return weeklyShifts.get(day).getStartingTime();
	}
	public LocalDateTime getend(int day){
		return weeklyShifts.get(day).getEndingTime();
	}
	public HashMap<Integer,Shift> getshift(){
		return this.weeklyShifts;
	}
}
