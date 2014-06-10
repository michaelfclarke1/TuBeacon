package digital2014.tubeacon;

import java.util.ArrayList;

public class RouteItem {

	private String uuid;
	private int major;
	private int minor;
	
	private ArrayList<PointOfInterest> poiList;
	
	public RouteItem(String uuid, int major, int minor) {
		this.setUUID(uuid);
		this.setMajor(major);
		this.setMinor(minor);
		
		this.poiList = new ArrayList<PointOfInterest>();
		
	}
	
	public void addPointOfInterest(PointOfInterest poi) {
		if (!poiList.contains(poi)) {
			poiList.add(poi);
		}
	}
	
	public ArrayList<PointOfInterest> getPointsOfInterest() {
		return this.poiList;
	}
	
	public void setMajor(int major) { this.major = major; }
	public void setMinor(int minor) { this.minor = minor; }
	public void setUUID(String uuid) { this.uuid = uuid; }
	
	public int getMajor() { return this.major; }
	public int getMinor() { return this.minor; }
	public String getUUID() { return this.uuid; }
	
	public boolean equals(Object o) {
		if (o instanceof RouteItem) {
			RouteItem r = (RouteItem) o;
			return (r.getMajor() == this.getMinor() && r.getMinor() == this.getMinor() 
										&& r.getUUID().equalsIgnoreCase(this.getUUID()));
		}
		return false;
	}
	
}
