package com.fauziazarinn.jesustrail;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;
import org.osmdroid.views.overlay.Overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/*
 * @brief - The class for displaying a route
 * overlay on the Map
 */
public class RouteOverlay extends Overlay {
    private GeoPoint gp1;
    private GeoPoint gp2;
    private int color;

    public RouteOverlay(GeoPoint gp1, GeoPoint gp2, Context ctx) {
        this(ctx);
        this.gp1 = gp1;
        this.gp2 = gp2;
        this.color = ctx.getResources().getColor(R.color.LightCoral);
    }

    public RouteOverlay(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
	    Projection projection = mapView.getProjection();
	    Paint paint = new Paint();
	    Point point = new Point();
	    projection.toPixels(gp1, point);
	    paint.setColor(color);
	    Point point2 = new Point();
	    projection.toPixels(gp2, point2);
	    paint.setStrokeWidth(5);
	    paint.setAlpha(120);
	    canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);
	}

}
