package digital2014.tubeacon;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class HomeScreenActivity extends Activity {

	private RouteItem currentLocation;
	private LinearLayout ll;
	private ArrayList<RouteItem> route;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.Stations, android.R.layout.simple_spinner_item);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				/* Build route. */
				ArrayList<RouteItem> journey = new ArrayList<RouteItem>();
				
				RouteItem station1 = new RouteItem("aaa", 1, 2);
				station1.addPointOfInterest(new PointOfInterest("Church"));
				station1.addPointOfInterest(new PointOfInterest("Studio"));
				RouteItem station2 = new RouteItem("aaa", 1, 1);
				station2.addPointOfInterest(new PointOfInterest("Arrgh"));
				station2.addPointOfInterest(new PointOfInterest("Noooo"));
				RouteItem station3 = new RouteItem("aaa", 1, 3);
				station3.addPointOfInterest(new PointOfInterest("Another Place"));
				station3.addPointOfInterest(new PointOfInterest("Some Other Place"));
			
				journey.add(station1);
				journey.add(station2);
				journey.add(station3);
				
				CurrentJourney.journey = journey;
				CurrentJourney.location = station3;
				Intent i = new Intent(getApplicationContext(), JourneyActivity.class);
				startActivity(i);
				
			} 
			
			
		});
		
	}
	

	
	
}
