package com.fauziazarinn.jesustrail;

import com.fauziazarinn.jesustrail.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class TrailSelectionFragment extends ListFragment {
	
	private Activity activity;
	
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        return (LinearLayout)inflater.inflate(R.layout.trail_selection, container, false);
	}

	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        activity = getActivity();
        
        if (activity != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
            		android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.trail_stages));
            setListAdapter(adapter);
        }
    }

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			// TODO: Before The Trail view
			break;
		case 1:
			Intent intent = new Intent(activity, MapActivity.class);
		    startActivity(intent);
			break;

		default:
			break;
		}
		super.onListItemClick(l, v, position, id);
	}

}
