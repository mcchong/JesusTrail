package com.fauziazarinn.jesustrail;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.main_navigation_items));
        setListAdapter(adapter);
        
        AtlasManager.loadAtlas(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
		case 0:
			// TODO: Before The Trail view
			break;
		case 1:
			Intent intent = new Intent(this, MapActivity.class);
		    startActivity(intent);
			break;

		default:
			break;
		}
		super.onListItemClick(l, v, position, id);
	}
    
}
