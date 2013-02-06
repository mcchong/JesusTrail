package com.fauziazarinn.jesustrail;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

// TODO: Get good map tiles
// TODO: Restrict map (when not connected via wifi)

public class MapActivity extends Activity {

	MapItemizedOverlay myItemizedOverlay = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		

		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setUseDataConnection(false);

		Drawable marker=getResources().getDrawable(android.R.drawable.star_big_on);
		int markerWidth = marker.getIntrinsicWidth();
		int markerHeight = marker.getIntrinsicHeight();
		marker.setBounds(0, markerHeight, markerWidth, 0);

		ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());

		myItemizedOverlay = new MapItemizedOverlay(marker, resourceProxy);
		mapView.getOverlays().add(myItemizedOverlay);

//		GeoPoint myPoint1 = new GeoPoint(35305080, 32732707); //32.732707,35.30508
//		myItemizedOverlay.addItem(myPoint1, "myPoint1", "myPoint1");
		GeoPoint myPoint2 = new GeoPoint(32732707, 35305080); //32.732707,35.30508
		myItemizedOverlay.addItem(myPoint2, "myPoint2", "myPoint2");
		
		MapController mapController = mapView.getController();
		mapController.setZoom(18);
		mapController.animateTo(myPoint2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_map, menu);
		return true;
	}

}
