package info.androidhive.recyclerviewsearch;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineListener;
import com.mapbox.android.core.location.LocationEnginePriority;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.CameraMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.modes.RenderMode;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationEngineListener,
        PermissionsListener, MapboxMap.OnMapClickListener {

    private MapView mapView;
    private MapboxMap map;
    private PermissionsManager PermissionsManager;
    private LocationEngine LocationEngine;
    private Button startButton;
    private LocationLayerPlugin LocationLayerPlugin;
    private Location originLocation;
    private Point origonPosition;
    private Point destinationPosition;
    private Marker destionationMarker;
    private NavigationMapRoute NavigationMapRoute;
    private static  final String TAG = "map";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Mapbox.getInstance(this,getString(R.string.API_Key));
        setContentView(R.layout.activity_maps);
        startButton = findViewById(R.id.startbutton);
        mapView = (MapView) findViewById(R.id.mapbox);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //launch
                NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                        .origin(origonPosition)
                        .destination(destinationPosition)
                        //kalo test di true kalo real false
                        .shouldSimulateRoute(true)
                        .build();
                NavigationLauncher.startNavigation(MapsActivity.this,options);
            }

        });
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        map = mapboxMap;
        map.addOnMapClickListener(this);
        enableLocation();

    }
    private void enableLocation(){
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            //disni
            initilizationEngine();
            initilizationLayer();

        }else {
            PermissionsManager = new PermissionsManager(this);
            PermissionsManager.requestLocationPermissions(this);
        }

    }
    @SuppressLint("MissingPermission")
    private void initilizationEngine(){
        LocationEngine = new LocationEngineProvider(this).obtainBestLocationEngineAvailable();
        LocationEngine.setPriority(LocationEnginePriority.HIGH_ACCURACY);
        LocationEngine.activate();

        Location lastLocation = LocationEngine.getLastLocation();
        if (lastLocation !=null){
            originLocation = lastLocation;
            setCameraPosition(lastLocation);
        } else {
            LocationEngine.addLocationEngineListener(this);
        }
    }

    @SuppressLint("MissingPermission")
    private void initilizationLayer(){
        LocationLayerPlugin = new LocationLayerPlugin(mapView,map,LocationEngine);
        LocationLayerPlugin.setLocationLayerEnabled(true);
        LocationLayerPlugin.setCameraMode(CameraMode.TRACKING);
        LocationLayerPlugin.setRenderMode(RenderMode.NORMAL);
    }
    private void setCameraPosition(Location location){
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(),
                location.getLongitude()),13.0));
    }

    @Override
    public void onMapClick(@NonNull LatLng point) {
        if (destionationMarker !=null){
            map.removeMarker(destionationMarker);
        }
        destionationMarker = map.addMarker(new MarkerOptions().position(point));

        destinationPosition = Point.fromLngLat(point.getLongitude(),point.getLatitude());
        origonPosition = Point.fromLngLat(originLocation.getLongitude(), originLocation.getLatitude());
        getRoute(origonPosition,destinationPosition);

        startButton.setEnabled(true);
        startButton.setBackgroundResource(R.color.blue700);

    }
    private void getRoute(Point origin , Point Destination){
        NavigationRoute.builder()
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(Destination)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        if (response.body() == null){
                            Log.e(TAG,"ERROR");
                            return;
                        } else if (response.body().routes().size() == 0 ){
                            Log.e(TAG,"rute tidak di temukan");
                        }
                        DirectionsRoute currentRoute = response.body().routes().get(0);

                        if (NavigationMapRoute != null){
                            NavigationMapRoute.removeRoute();
                        } else {
                            NavigationMapRoute = new NavigationMapRoute(null,mapView,map);
                        }
                        NavigationMapRoute.addRoute(currentRoute);
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                        Log.e(TAG,"Error map"+ t.getMessage());

                    }
                });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onConnected() {
        LocationEngine.requestLocationUpdates();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null){
            originLocation = location;
            setCameraPosition(location);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }





    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        //toast
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            enableLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
