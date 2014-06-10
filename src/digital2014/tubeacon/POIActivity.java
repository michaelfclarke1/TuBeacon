package digital2014.tubeacon;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class POIActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.poi_actvity);
		
		TextView title = (TextView) findViewById(R.id.title);
		TextView description = (TextView) findViewById(R.id.description);
		
		title.setText(CurrentJourney.poi.getName());
		description.setText(CurrentJourney.poi.getDescription());
		
	}	

	
	
}
