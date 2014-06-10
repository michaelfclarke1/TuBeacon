package digital2014.tubeacon; 

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PointsOfInterestListAdapter extends BaseAdapter {
	
	private ArrayList<PointOfInterest> poiList;
	private LayoutInflater inflater;
	
	public PointsOfInterestListAdapter(Context context) {
	
		super();
		
		inflater = (LayoutInflater)context.getSystemService(
									Context.LAYOUT_INFLATER_SERVICE);
		
		this.poiList = new ArrayList<PointOfInterest>();
		
	}
	
	public void addPointOfInterest(PointOfInterest poi) {
	
		if (!poiList.contains(poi)) poiList.add(poi);
		
	}
	
	public void setPointsOfInterest(ArrayList<PointOfInterest> poiList) {
		this.poiList = poiList;
	}
	
	public void removeProject(PointOfInterest poi) {
		poiList.remove(poi);
	}
	
	public int getCount() {
		return poiList.size();
	}
	
	public Object getItem(int position) {
		return poiList.get(position);
	}
	
	public long getItemId(int position) {
		return position;	
	}
	
	public View getView(int position, View view, ViewGroup viewGroup) {
		
		view = inflater.inflate(R.layout.poi_list_item, null);
		TextView poiText = (TextView) view.findViewById(R.id.poi_list_item_text);
		
		PointOfInterest poi = poiList.get(position);
		
	    poiText.setText(poi.getName());
		
		return view;

	}
	
	public ArrayList<PointOfInterest> getAll() {
		return this.poiList;
	}
	
	public void clear() {
		poiList.clear();
		
	}
	
}
