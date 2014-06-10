package digital2014.tubeacon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;

import digital2014.tubeacon.nav.Graph;
import digital2014.tubeacon.nav.NavigationStep;
import digital2014.tubeacon.nav.Vertex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class HomeScreenActivity extends Activity implements IBeaconConsumer {
	
	private IBeaconManager iBeaconManager;
	private Vertex currentLocation = null;
	private Graph g;

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		
		g = new Graph(this, "stations");
		CurrentJourney.g = g;
		ArrayList<Vertex> destinations = g.locations();
		
		final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		final DestinationAdapter da = new DestinationAdapter(this, destinations);
		spinner.setAdapter(da);
				
		iBeaconManager = IBeaconManager.getInstanceForApplication(this);
		iBeaconManager.bind(this);
		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				/* Build route. */
				ArrayList<RouteItem> journey = new ArrayList<RouteItem>();
				
				RouteItem des = (RouteItem) da.getRouteItem((int)spinner.getSelectedItemId());
				Log.e("DES:", "UUID: " + des.getUUID() + "Major: " + des.getMajor() + "Minor: " + des.getMinor());
				Vertex dest = g.getCurrentLocation(des.getUUID(), des.getMajor(), des.getMinor());
				
				Log.e("NAVFROM:", currentLocation.getFriendlyName());
				Log.e("NAVTO:", dest.getFriendlyName());
				
				ArrayList<NavigationStep> navSteps = g.navigate(currentLocation, dest);
				if (navSteps != null) {
					
					navSteps.add(0, new NavigationStep(dest));
					Log.e("ARGH", ""+navSteps.size());
					for (int i = 0 ; i < navSteps.size(); i++) {
						NavigationStep ns = navSteps.get(i);
						RouteItem ri = new RouteItem(ns.getUUID(), ns.getMajor(), ns.getMinor(), ns.getName());
						ri.addPointsOfInterest(ns.getVertex().getPointsOfInterest());
						journey.add(ri);
					}
				
					CurrentJourney.journey = journey;
					CurrentJourney.location = des;
				
					Intent i = new Intent(getApplicationContext(), JourneyActivity.class);
					startActivity(i);
					
				}
				
			} 
			
			
		});
		
	}
	
public void onDestroy() {
		
		super.onDestroy();
		iBeaconManager.unBind(this);
		
	}

	@Override
	public void onIBeaconServiceConnect() {
		
		iBeaconManager.setRangeNotifier(new RangeNotifier() {

			@Override
			public void didRangeBeaconsInRegion(Collection<IBeacon> arg0,
					Region arg1) {
				

				Vertex currentLoc = null;
				
				/* Check to see if we found a iBeacon. */
				if (arg0.size() > 0) {
		
					/* We've got some, now loop through them. If the iBeacon
					 * "IMMEDIATE" or "NEAR" we should add the given project to 
					 * a list of "near" projects..
					 */
					Iterator<IBeacon> itt = arg0.iterator();	
					while (itt.hasNext()) {
						
						IBeacon temp = itt.next();
						
						if (temp.getProximity() == IBeacon.PROXIMITY_NEAR ||
						temp.getProximity() == IBeacon.PROXIMITY_IMMEDIATE) {
							currentLoc = g.getCurrentLocation(
								temp.getProximityUuid(), 
											temp.getMajor(), temp.getMinor());
							if (currentLoc != null) {
								currentLocation = currentLoc;
							
								runOnUiThread(
								
									new Runnable() {
										
										public void run() {
								
								EditText et = (EditText) findViewById(R.id.editText1);
								et.setText(currentLocation.getFriendlyName());
										}});
							}
						}
					
					}
				}
			}
		});
		
		try {
			 iBeaconManager.startRangingBeaconsInRegion(
					 new Region("myRangingUniqueId", null, null, null));
			 
		 } catch (RemoteException e) {   
			 e.printStackTrace();
		 }
		
	}

}
