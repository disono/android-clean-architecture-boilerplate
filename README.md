# Android Clean Architecture Boilerplate

# Libraries
<p>AppCompat</p>
<p>Support Design</p>
<p><a href="https://github.com/JakeWharton/timber">Timber</a></p>
<p><a href="http://jakewharton.github.io/butterknife">Butterknife</a></p>
<p><a href="https://github.com/square/picasso">Picasso</a></p>
<p><a href="http://google.github.io/dagger/">Dagger</a></p>
<p><a href="http://square.github.io/retrofit">Retrofit</a></p>
<p><a href="https://github.com/google/gson">GSON</a></p>
<p><a href="https://github.com/ReactiveX/RxJava">RXJava</a></p>
<p><a href="http://square.github.io/okhttp/">okHTTP</a></p>
<p><a href="https://developers.facebook.com/docs/android/">Facebook SDK</a></p>
<p><a href="hhttps://docs.fabric.io/android/twitter/twitter.html">Twitter SDK</a></p>
<p><a href="https://developers.google.com/api-client-library/java/google-api-java-client/oauth2">Google SDK</a></p>

# Utilities Usage
### Camera
```sh
@Inject
Launcher launcher;

REQUEST_IMAGE_CAPTURE = launcher.REQUEST_IMAGE_CAPTURE;
launcher.takePicture();

// on activity result
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
    Bundle extras = data.getExtras();
    Bitmap imageBitmap = (Bitmap) extras.get("data");
    if (imageBitmap != null) {
      // location imageBitmap.toString()
    }
  }
}
```

### Accelerometer
```sh
@Inject
AccelListener accelListener;

// accelerometer
accelListener.listener(new SensorEventListener() {
  public void onSensorChanged(SensorEvent event) {
    // only look at accelerometer events
    if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
      return;
    }
    
    // X: event.values[0]
    // Y: event.values[1]
    // Z: event.values[2]
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {

  }
});

// start
try {
  accelListener.start();
} catch (Exception e) {
  e.printStackTrace();
}

// stop
accelListener.stop();
```
### GPS
```sh
@Inject
GPS gps;

// gps
gps.run(new LocationListener() {
  @Override
  public void onLocationChanged(Location location) {
    // LAT: location.getLatitude()
    // LNG: location.getLongitude()
  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {

  }

  @Override
  public void onProviderEnabled(String provider) {

  }

  @Override
  public void onProviderDisabled(String provider) {

  }
});
```

### Screen Orientation
```sh
@Inject
ScreenOrientation orientation;
    
// screen orientation
TYPES:
UNSPECIFIED, LANDSCAPE_PRIMARY, PORTRAIT_PRIMARY, LANDSCAPE, PORTRAIT, LANDSCAPE_SECONDARY, PORTRAIT_SECONDARY

try {
  orientation.apply(TYPES);
} catch (Exception e) {
  e.printStackTrace();
}
```

### Vibration
```sh
@Inject
Vibrate vibrate;
    
long[] patterns = {100, 200, 300, 400, 500};
vibrate.pattern(patterns, 1);
```

### Media
```sh
@Inject
AudioHandler audioHandler;

// play audio
audioHandler.play("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.audio);
```

### Dialogs
```sh
// error dialog
DialogFactory.error(ctx, "Title", "Message!",
  new DialogInterfaceFactory().OnClick(new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
                      
    }
})).show();
```

# Other Resources
<p><a href="https://developers.google.com/api-client-library/java/google-api-java-client/oauth2">Using OAuth 2.0 with the Google API Client Library for Java</a></p>
