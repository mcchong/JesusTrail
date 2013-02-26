package com.fauziazarinn.jesustrail;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.ekito.simpleKML.Serializer;
import com.ekito.simpleKML.model.Kml;
import com.fauziazarinn.jesustrail.R;

// TODO: Don't restart map on orientation change
// TODO: Get good map tiles
// TODO: Restrict map (when not connected via wifi)

public class MapActivity extends Activity {

	private static final String TAG = "MapActivity";

	private class KMLParsingTask extends AsyncTask<String, String, Kml> {

		Serializer kmlSerializer;

		public KMLParsingTask() {
			kmlSerializer = new Serializer();
		}

		@Override
		protected Kml doInBackground(String... params) {
			Log.d(TAG, "read started");
			// this will create a Kml class based on the informations described in params[0] (assets/test.kml)
			Kml kml = null;
			try {
				InputStream is = getResources().getAssets().open(params[0]);
				Log.d(TAG, "parsing started");
				kml = kmlSerializer.read(is);
				Log.d(TAG, "parsing done");
			} catch (IOException e) {
				Log.e(TAG, e.getMessage());
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			Log.d(TAG, "read done");

			return kml;
		}

		protected void onPostExecute(Kml result) {
			addKMLContents(result);
		}

	}

	MapItemizedOverlay myItemizedOverlay = null;
	MapView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);


		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setUseDataConnection(false);

		Drawable marker=getResources().getDrawable(android.R.drawable.star_big_on);
		int markerWidth = marker.getIntrinsicWidth();
		int markerHeight = marker.getIntrinsicHeight();
		marker.setBounds(0, markerHeight, markerWidth, 0);

		ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());

		myItemizedOverlay = new MapItemizedOverlay(marker, resourceProxy);
		mapView.getOverlays().add(myItemizedOverlay);

		GeoPoint myPoint2 = new GeoPoint(32732707, 35305080); //32.732707,35.30508
		myItemizedOverlay.addItem(myPoint2, "myPoint2", "myPoint2");

		// Begin KML Parsing
		new KMLParsingTask().execute("trail_route.kml");

		MapController mapController = mapView.getController();
		mapController.setZoom(18);
		mapController.animateTo(myPoint2);
	}

	public void addKMLContents(Kml result) {
		// TODO: Get colour and style from KML
		List<GeoPoint> route = KMLManager.getRoutefromKml(result);		
		drawPath(route);
	}

	private void drawPath(List<GeoPoint> geoPoints) {
		List<Overlay> overlays = mapView.getOverlays();

		for (int i = 1; i < geoPoints.size(); i++) {
			overlays.add(new RouteOverlay(geoPoints.get(i - 1), geoPoints.get(i), this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_map, menu);
		return true;
	}

}
