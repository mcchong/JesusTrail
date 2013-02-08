package com.fauziazarinn.jesustrail;

import java.util.ArrayList;
import java.util.List;

import org.osmdroid.util.GeoPoint;

import android.util.Log;

import com.ekito.simpleKML.model.Coordinate;
import com.ekito.simpleKML.model.Coordinates;
import com.ekito.simpleKML.model.Document;
import com.ekito.simpleKML.model.Feature;
import com.ekito.simpleKML.model.Folder;
import com.ekito.simpleKML.model.Kml;
import com.ekito.simpleKML.model.LineString;
import com.ekito.simpleKML.model.MultiGeometry;
import com.ekito.simpleKML.model.Placemark;

public class KMLManager {
	
	public static final String TAG = "KMLManager";
	
	// TODO: This is a very case-specific implementation
	// Maybe organize and sort the placemarks as needed, like a
	// Navigation data set as in
	// http://stackoverflow.com/questions/3109158/how-to-draw-a-path-on-a-map-using-kml-file/3109723#3109723
	public static List<GeoPoint> getRoutefromKml(Kml kml) {
		Coordinates j01Path = null;
		
		// Grab the placemark for the route
		// TODO: This is really ugly
		Document document = (Document)kml.getFeature();
		for (Feature tracksFeature : document.getFeatureList()) {
			Folder tracksFolder = (Folder)tracksFeature;
			if (tracksFolder.getName().equals("Tracks")) {
				for (Feature pathsFeature : tracksFolder.getFeatureList()) {
					Folder pathsFolder = (Folder)pathsFeature;
					if (pathsFolder.getName().equals("Paths")) {
						for (Feature placeMarkFeature : pathsFolder.getFeatureList()) {
							if (placeMarkFeature.getName().equals("J01: Nazareth to Zippori")) {
								MultiGeometry multiGeometry = (MultiGeometry)((Placemark)placeMarkFeature).getGeometryList().get(0);
								LineString linestring = (LineString)multiGeometry.getGeometryList().get(0);
								j01Path = linestring.getCoordinates();
							}
						}
					}
				}
			}
		}
		
		List<GeoPoint> result = new ArrayList<GeoPoint>();
		
		if (j01Path != null) {
			for (Coordinate c : j01Path.getList()) {
				// TODO: double check decimal points
				result.add(new GeoPoint(c.getLatitude(), c.getLongitude(), c.getAltitude()));
			}
			Log.d(TAG, result.size() + "points for the map");
		}
		
		return result;
	}

}
