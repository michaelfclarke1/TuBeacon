package digital2014.tubeacon;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class JourneyActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.journey_screen);
	
		HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView1);
		
		LinearLayout ll = new LinearLayout(this);
		hsv.addView(ll);
		
		LineView lv;
		
		for (int i = 0; i < 10; i++) {
			lv = new LineView(this);
			lv.setColour(Color.LTGRAY);
			lv.setHeight(300);
			lv.setWidth(400);
			ll.addView(lv);
		}		
		
		lv = new LineView(this);
		lv.setColour(Color.DKGRAY);
		lv.setHeight(300);
		lv.setWidth(400);
		ll.addView(lv);
		

		for (int i = 0; i < 10; i++) {
			lv = new LineView(this);
			lv.setColour(Color.LTGRAY);
			lv.setHeight(300);
			lv.setWidth(400);
			ll.addView(lv);
		}		
		
	}
	
}
