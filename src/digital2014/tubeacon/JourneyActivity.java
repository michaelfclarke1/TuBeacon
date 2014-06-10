package digital2014.tubeacon;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class JourneyActivity extends Activity {

	private RouteItem currentLocation;
	private LinearLayout ll;
	private ArrayList<RouteItem> route;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journey_screen);
		
		HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		ll = new LinearLayout(this);
		hsv.addView(ll);
		
	}
	
	/**
	 * Sets the route list, i.e. a list of iBeacons we should find on our
	 * route.
	 * 
	 * @param route The route.
	 */
	public void setRoute(ArrayList<RouteItem> route) {
		this.route = route;
	}
	
	/**
	 * Updates the current location in the route list.
	 * 
	 * @param currentLocation Most recently discovered iBeacon.
	 */
	private void setCurrentLocation(RouteItem currentLocation) {
		this.currentLocation = currentLocation;
		rebuildRouteView();
	}
	
	/**
	 * Builds the route list, with the current route location set
	 * to dark gray and all the others set to light gray.
	 */
	private void rebuildRouteView() {
		
		LineView lv;
		ll.removeAllViews();
		
		for (int i = 0; i < route.size(); i++) {
			lv = new LineView(this);
			
			if (route.get(i).equals(currentLocation)) {
				lv.setColour(Color.DKGRAY);
			} else { lv.setColour(Color.DKGRAY); }
			
			lv.setHeight(300);
			lv.setWidth(400);
			ll.addView(lv);
		}
		
	}
	
}