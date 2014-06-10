package digital2014.tubeacon;

public class RouteItem {

	private String uuid;
	private int major;
	private int minor;
	
	public RouteItem(String uuid, int major, int minor) {
		this.setUUID(uuid);
		this.setMajor(major);
		this.setMinor(minor);
	}
	
	public void setMajor(int major) { this.major = major; }
	public void setMinor(int minor) { this.minor = minor; }
	public void setUUID(String uuid) { this.uuid = uuid; }
	
	public int getMajor() { return this.major; }
	public int getMinor() { return this.minor; }
	public String getUUID() { return this.uuid; }
	
}
