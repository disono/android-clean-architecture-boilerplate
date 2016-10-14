package disono.webmons.com.utilities.sensor.GeoLocation;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-28 05:31 PM
 */
public class GPS {
    private final static String TAG = "GPS:Class";
    private Application application;

    public GPS(Activity activity) {
        this.application = activity.getApplication();
    }

    public LocationManager manager() {
        return (LocationManager) application.getSystemService(Context.LOCATION_SERVICE);
    }

    public void run(LocationListener listener) {
        if (ActivityCompat.checkSelfPermission(application,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(application, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "GPS is not available or enabled on this device.");
            return;
        }

        this.manager().requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }

    public void runOnce(LocationListener listener) {
        if (ActivityCompat.checkSelfPermission(application,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(application, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, "GPS is not available or enabled on this device.");
            return;
        }

        this.manager().requestSingleUpdate(LocationManager.GPS_PROVIDER, listener, null);
    }
}
