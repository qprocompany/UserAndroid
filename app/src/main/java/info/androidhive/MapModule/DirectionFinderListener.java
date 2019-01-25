package info.androidhive.MapModule;

import android.location.Location;

import java.util.List;

import info.androidhive.MapModule.Route;

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);

    void onLocationChanged(Location location);
}
