package com.your.app.p4ck4ge.name;

import java.util.ArrayList;

import java.util.List;

import com.nolesh.ads.OfferwallAdapter;
import com.your.app.p4ck4ge.name.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class OfferwallTab extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_offerwall, container, false);
						
		ListView lv = (ListView)view.findViewById(R.id.offerwallListView);
		
		//Embeds offerwall into your listView
//		AdNoleshSDK.showOfferwall(lv);
		
		//The AdNolesh SDK provides an OfferwallAdapter which can wrap another ListAdapter
		//and inserts ads at certain intervals.
		//So, if you want to mix your content with ad units, 
		//you should take a look at the code below
		
		final List<Item> items = new ArrayList<>();
		for(int i = 1; i<=15; i++){
			Item item = new Item("title "+i, "Some description #"+i);
			items.add(item); 
		}
		
		//Custom adapter
        final MyAdapter myAdapter = new MyAdapter(getContext(), R.layout.my_listview_item, items);
        //Wraps your list adapter
        final OfferwallAdapter newAdapter = OfferwallAdapter.wrapListAdapter(getContext(), myAdapter, lv);
		// Now you should use wrapped adapter
        lv.setAdapter(newAdapter);		
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
				
				//OPTION #1
				Object object = adapter.getItemAtPosition(position);
//				/*OR*/Object object = newAdapter.getItem(position);
				//If the user clicks on the ad unit, then the object is null.
				//In this case, we have to ignore this action
				if(object==null) return; 
				
				//OPTION #2
//				int originalPosition = newAdapter.getItemPosition(position);				
//				Object object = newAdapter.getUserItem(originalPosition);
				
				
				Item item = (Item)object;								
				Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_LONG).show();
			}
		});
		
		//A button that dynamically adds items to the listView
		Button btn = (Button) view.findViewById(R.id.btnAddItem);
		btn.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				int num = newAdapter.getOriginalCount()+1;
				Item item = new Item("title "+num, "Dynamic item #"+num);
				items.add(item); 
				newAdapter.notifyDataSetChanged();
			}
		});
		
		//A button that dynamically deletes last item from the listView
		btn = (Button) view.findViewById(R.id.btnDelItem);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				int num = newAdapter.getOriginalCount()-1;
				if(num<0) return;

				items.remove(num);				
				newAdapter.notifyDataSetChanged();
			}
		});
		
		return view;
	}
	
	//Example of custom adapter	
	public class Item {
	    String title;
	    String desc;	   

	    public Item(String title, String desc) {
	        this.title = title;	        
	        this.desc = desc;	      
	    }

		public CharSequence getTitle() {			
			return title;
		}

		public CharSequence getDesc() {			
			return desc;
		}		
	}
	
	public class MyAdapter extends ArrayAdapter<Item> {

		private int layoutResource;

		public MyAdapter(Context context, int layoutResource, List<Item> threeStringsList) {
		    super(context, layoutResource, threeStringsList);
		    this.layoutResource = layoutResource;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

		    View view = convertView;

		    if (view == null) {
		        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
		        view = layoutInflater.inflate(layoutResource, null);
		    }

		    Item item = getItem(position);

		    if (item != null) {
		        TextView titleTextView = (TextView) view.findViewById(R.id.titleTextView);
		        TextView descTextView = (TextView) view.findViewById(R.id.descTextView);
		       
		        if (titleTextView != null) {
		        	titleTextView.setText(item.getTitle());
		        }
		        if (descTextView != null) {
		        	descTextView.setText(item.getDesc());
		        }		      
		    }
		    return view;
		}
	}
}