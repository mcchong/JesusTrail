package com.fauziazarinn.jesustrail;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.widget.TabHost.OnTabChangeListener;

import com.fauziazarinn.jesustrail.R;

public class MainActivity extends FragmentActivity implements OnTabChangeListener{

	private FragmentTabHost tabHost;

		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		tabHost.addTab(tabHost.newTabSpec("trail_selection").setIndicator("On The Trail",
				getResources().getDrawable(android.R.drawable.ic_menu_compass)),
                TrailSelectionFragment.class, null);
		tabHost.addTab(tabHost.newTabSpec("info_selection").setIndicator("Information",
				getResources().getDrawable(android.R.drawable.ic_menu_help)),
                InfoSelectionFragment.class, null);
		
		AtlasManager.loadAtlas(this);
	}

	@Override
		public void onTabChanged(String tabId) {
			// TODO Auto-generated method stub
			
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
