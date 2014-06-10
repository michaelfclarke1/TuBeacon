package digital2014.tubeacon.nav;

public class NavigationStep {

	private Vertex vertex;
	
	public NavigationStep(Vertex vertex) {
		this.vertex = vertex;
	}
	
	public String getUUID() {
		return this.vertex.getUUID();
	}
	
	public int getMajor() {
		return this.vertex.getMajor();
	}
	
	public int getMinor() {
		return this.vertex.getMinor();
	}
	
	public String getName() {
		return this.vertex.getFriendlyName();
	}
	
}
