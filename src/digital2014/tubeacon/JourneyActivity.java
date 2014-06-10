package digital2014.tubeacon;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;


public class JourneyActivity extends Activity {

	private RouteItem currentLocation;
	private LinearLayout ll;
	private ArrayList<RouteItem> route;
	private PointsOfInterestListAdapter poiListAdapter;
	private ListView poiList;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journey_screen);
		
		HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		ll = new LinearLayout(this);
		hsv.addView(ll);
		poiList = (ListView)findViewById(R.id.listView1);
		
		poiListAdapter = new PointsOfInterestListAdapter(this);
		poiList.setAdapter(poiListAdapter);
		
		RouteItem station1 = new RouteItem("aaa", 1, 2);
		station1.addPointOfInterest(new PointOfInterest("Church"));
		station1.addPointOfInterest(new PointOfInterest("Studio"));
		RouteItem station2 = new RouteItem("aaa", 1, 1);
		station2.addPointOfInterest(new PointOfInterest("Arrgh"));
		station2.addPointOfInterest(new PointOfInterest("Noooo"));
		RouteItem station3 = new RouteItem("aaa", 1, 3);
		station3.addPointOfInterest(new PointOfInterest("Another Place"));
		station3.addPointOfInterest(new PointOfInterest("Some Other Place"));
		
		ArrayList<RouteItem> list = new ArrayList<RouteItem>();
		list.add(station1);
		list.add(station2);
		list.add(station3);
		this.setRoute(list);
	
		this.setCurrentLocation(station2);
		
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
				poiListAdapter.setPointsOfInterest(route.get(i).getPointsOfInterest());
				poiListAdapter.notifyDataSetChanged();
			} else { lv.setColour(Color.LTGRAY); }
			
			lv.setHeight(300);
			lv.setWidth(400);
			ll.addView(lv);poiList.setAdapter(poiListAdapter);
		}
		
	}
	
}
