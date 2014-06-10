package digital2014.tubeacon; 

import java.util.ArrayList;

import digital2014.tubeacon.nav.*;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class DestinationAdapter implements SpinnerAdapter {
	
	private ArrayList<RouteItem> routeList;
	private LayoutInflater inflater;
	
	public DestinationAdapter(Context context, ArrayList<Vertex> destinations) {
		
		inflater = (LayoutInflater)context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);

		
		this.routeList = new ArrayList<RouteItem>();
		for (int i = 0; i < destinations.size(); i++) {
			Vertex v = destinations.get(i);
			RouteItem ri = new RouteItem(v.getUUID(), v.getMajor(),
												v.getMinor(), v.getFriendlyName());
			routeList.add(ri);
		}
		
	}
	
	public int getCount() {
		return routeList.size();
	}
	
	public String getItem(int position) {
		return routeList.get(position).getName();
	}
	
	public long getItemId(int position) {
		return position;	
	}
	
	public RouteItem getRouteItem(int position) {
		return routeList.get(position);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		return this.getCustomView(position, convertView, parent);
	}
	
	public View getCustomView(int position, View convertView, ViewGroup parent) {
		
		View v = inflater.inflate(R.layout.destination_list_item, parent, false);

           TextView text = (TextView) v.findViewById(R.id.text);
           RouteItem routeItem = routeList.get(position);
           text.setText(routeItem.getName());
           v.setId(position);
           return v;
		
	}
	
	public ArrayList<RouteItem> getAll() {
		return this.routeList;
	}
	
	public void clear() {
		routeList.clear();	
	}

	@Override
	public int getItemViewType(int arg0) {
		//return this.routeList.size();
		return 1;
	}

	@Override
	public int getViewTypeCount() {
		//return this.routeList.size();
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.routeList.isEmpty();
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
	}

	@Override
	public View getDropDownView(int arg0, View arg1, ViewGroup arg2) {
			return getCustomView(arg0, arg1, arg2);
	}
	
}
