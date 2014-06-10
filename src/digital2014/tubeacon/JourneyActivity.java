package digital2014.tubeacon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;

import digital2014.tubeacon.nav.Vertex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;


public class JourneyActivity extends Activity{ 

	private RouteItem currentLocation;
	private LinearLayout ll;
	private ArrayList<RouteItem> route;
	private PointsOfInterestListAdapter poiListAdapter;
	private ListView poiList;
	private IBeaconManager iBeaconManager;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journey_screen);
	
		HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		ll = new LinearLayout(this);
		hsv.addView(ll);
		poiList = (ListView)findViewById(R.id.listView1);
		
		poiListAdapter = new PointsOfInterestListAdapter(this);
		poiList.setAdapter(poiListAdapter);
		
		poiList.setOnItemClickListener(new OnItemClickListener(){
			 
			 			@Override
			 			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
			 					long arg3) {
			 				Intent i = new Intent(getApplicationContext(), POIActivity.class);
			 				startActivity(i);
			 				
			 			}});
		
		this.setRoute(CurrentJourney.journey);
		this.setCurrentLocation(CurrentJourney.location);
		
		iBeaconManager = IBeaconManager.getInstanceForApplication(this);
		iBeaconManager.setRangeNotifier(new RangeNotifier() {

			@Override
			public void didRangeBeaconsInRegion(Collection<IBeacon> arg0,
					Region arg1) {
				
				if (arg0.size() > 0) {
					Iterator<IBeacon> itt = arg0.iterator();	
					while (itt.hasNext()) {
						
						IBeacon temp = itt.next();
						
						if (temp.getProximity() == IBeacon.PROXIMITY_NEAR ||
						temp.getProximity() == IBeacon.PROXIMITY_IMMEDIATE) {
							Vertex currentLoc = CurrentJourney.g.getCurrentLocation(
								temp.getProximityUuid(), 
											temp.getMajor(), temp.getMinor());
							if (currentLoc != null) {
								currentLocation = new RouteItem(currentLoc.getUUID(), currentLoc.getMajor(), currentLoc.getMinor(), currentLoc.getFriendlyName());
								runOnUiThread(
									new Runnable() {
										
										public void run() { rebuildRouteView(); }
									});
								}
							}}}}});
	
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
		
		for (int i = route.size()-1; i >=0 ; i--) {
			lv = new LineView(this);
			lv.setStationName(route.get(i).getName());
			
			if (route.get(i).equals(currentLocation)) {
				lv.setColour(Color.DKGRAY);
				poiListAdapter.setPointsOfInterest(route.get(i).getPointsOfInterest());
				poiListAdapter.notifyDataSetChanged();
			} else { lv.setColour(Color.LTGRAY); }
			
			lv.setHeight(300);
			lv.setWidth(400);
			ll.addView(lv);
			
			ll.setOnClickListener(new OnClickListener() {
				public void onClick(View arg0) {
  					
					 Intent i = new Intent(getApplicationContext(), StationActivity.class);
					 	startActivity(i);
					  					
				}
			});
			
			
			poiList.setAdapter(poiListAdapter);
		}
		
	}

	
}
