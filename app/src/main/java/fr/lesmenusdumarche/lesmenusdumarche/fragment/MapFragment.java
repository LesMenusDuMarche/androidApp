package fr.lesmenusdumarche.lesmenusdumarche.fragment;

/**
 * Created by tremo on 21/03/16.
 */
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.MapEventsOverlay;
import org.osmdroid.bonuspack.overlays.MapEventsReceiver;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

import fr.lesmenusdumarche.lesmenusdumarche.R;
import fr.lesmenusdumarche.lesmenusdumarche.domain.NavigationStep;

public class MapFragment extends Fragment implements MapEventsReceiver {

    // UI Components
    View fragment;
    MapView map;

    // Map components
    RoadManager roadManager = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragment = inflater.inflate(R.layout.fragment_map, container, false);

        // UI Components
        map = (MapView) fragment.findViewById(R.id.map);

        // Init map
        initializeMap();

        return fragment;
    }

    protected void initializeMapOverlay() {
        // Events
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(this.getActivity(), this);
        map.getOverlays().add(0, mapEventsOverlay);

        // Own position
        final MyLocationNewOverlay locationOverlay = new MyLocationNewOverlay(getContext(), map);
        locationOverlay.disableMyLocation();
        locationOverlay.setDrawAccuracyEnabled(true);
        locationOverlay.runOnFirstFix(new Runnable() {
            @Override
            public void run() {
                map.getController().animateTo(locationOverlay.getMyLocation());
            }
        });

        map.getOverlays().add(locationOverlay);
    }

    protected void initializeMap() {
        map.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);    // Set source (OSM, GMaps, MapBox...)
        map.setTilesScaledToDpi(true);                  // Scale to DPI, for large screens like tablets

        map.setBuiltInZoomControls(true);               // Enable zoom buttons
        map.setMultiTouchControls(true);                // Enable pinch to zoom, rotate...

        IMapController mapController = map.getController(); // Create a map controller
        GeoPoint startPoint = new GeoPoint(48.115251, -1.638668);// Create a point
        mapController.setCenter(startPoint);                // Put the map center on the point

        mapController.setZoom(18);                          // Set an initial amount of zoom, else we will see an "earth mosaic"

        // Create roadmanager
        roadManager = new OSRMRoadManager(this.getContext());

        initializeMapOverlay();

        map.invalidate(); // We need that to make OSMDroid to reload the map
    }

    public void setNavigationSteps(List<NavigationStep> steps) {
        map.getOverlays().clear();
        initializeMapOverlay();

        // Init polyline
        Polyline polyline = new Polyline(getContext());
        polyline.setColor(R.color.colorPrimary);
        ArrayList<GeoPoint> polylinePoints = new ArrayList<>();

        // Draw markers
        for(NavigationStep ns : steps) {
            // Add to polyline
            polylinePoints.add(new GeoPoint(ns.getLatitude(), ns.getLongitude()));

            // Create marker and set a related object
            Marker marker = new Marker(map);
            marker.setRelatedObject(ns);

            // Set marker position and anchor
            marker.setPosition(new GeoPoint(ns.getLatitude(), ns.getLongitude()));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);

            // Stylize the marker
            Drawable markerIcon = ContextCompat.getDrawable(getContext(), R.drawable.map_cross);
            //markerIcon.setColorFilter(0xffff00, PorterDuff.Mode.MULTIPLY);//color

            marker.setIcon(markerIcon);
            marker.setTitle(ns.getSeller().getName());

            map.getOverlays().add(marker);
        }

        // Draw polyline
        polyline.setPoints(polylinePoints);
        map.getOverlays().add(polyline);

        map.invalidate();
    }

    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        return false;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }
}