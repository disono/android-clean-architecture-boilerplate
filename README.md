# Android Clean Architecture Boilerplate
is a starting blank template for Android Projects

# Libraries
* **AppCompat**
* **Support Design**
* **Timber:** [https://github.com/JakeWharton/timber](https://github.com/JakeWharton/timber)
* **Butterknife:** [http://jakewharton.github.io/butterknife](http://jakewharton.github.io/butterknife)
* **Picasso:** [https://github.com/square/picasso](https://github.com/square/picasso)
* **Dagger:** [http://google.github.io/dagger/](http://google.github.io/dagger/)
* **Retrofit:** [http://square.github.io/retrofit](http://square.github.io/retrofit)
* **GSON:** [https://github.com/google/gson](https://github.com/google/gson)
* **RXJava** [https://github.com/ReactiveX/RxJava](https://github.com/ReactiveX/RxJava)
* **okHTTP** [http://square.github.io/okhttp](http://square.github.io/okhttp)
* **Facebook SDK** [https://developers.facebook.com/docs/android](https://developers.facebook.com/docs/android)
* **Twitter SDK** [hhttps://docs.fabric.io/android/twitter/twitter.html](https://docs.fabric.io/android/twitter/twitter.html)
* **Google SDK** [https://developers.google.com/api-client-library/java/google-api-java-client/oauth2](https://developers.google.com/api-client-library/java/google-api-java-client/oauth2)
* **EasyForm** [https://github.com/emmasuzuki/EasyForm](https://github.com/emmasuzuki/EasyForm)
* **Sweet Alert Dialog** [https://github.com/pedant/sweet-alert-dialog](https://github.com/pedant/sweet-alert-dialog)
* **Sugar ORM** [https://github.com/satyan/sugar](https://github.com/satyan/sugar)
* **Java JWT** [https://github.com/jwtk/jjwt](https://github.com/jwtk/jjwt)
* **Particle** [https://github.com/JeasonWong/Particle](https://github.com/JeasonWong/Particle)
* **CircleImageView** [https://github.com/hdodenhof/CircleImageView](https://github.com/hdodenhof/CircleImageView)
* **Material DateTime Picker - Select a time/date in style** [https://github.com/wdullaer/MaterialDateTimePicker](https://github.com/wdullaer/MaterialDateTimePicker)
* **RippleEffect** [https://github.com/traex/RippleEffect](https://github.com/traex/RippleEffect)

# Utilities Usage
### Running Logic on Thread
```sh
MainThreadImp.getInstance().post(new Runnable() {
  @Override
  public void run() {
    
  }
});
```

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

### Audio Player
```sh
@Inject
AudioHandler audioHandler;

// play audio
audioHandler.play("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.audio);
```

### Video Player
```sh
@Inject
VideoHandler videoHandler;

videoHandler.play("https://www.youtube.com/path-to-video");
videoHandler.play("http://your-domain-name/video.mp4");
videoHandler.play("file:///your-path/demo.mp4");
videoHandler.play("file:///android_asset/your-path/demo.mp4");
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

### Alers
```sh
@Inject
WBAlerts wbAlerts;

wbAlerts.error("Error Name", "Error Message").show();
```

### API Call
```sh
// Call API
YourAdapter yourAdapter = new YourAdapter();
Call<YOURMODELNAME> call = yourAdapter.getUser("username");
call.enqueue(new Callback<YOURMODELNAME>() {
  @Override
  public void onResponse(Call<YOURMODELNAME> call, Response<YOURMODELNAME> response) {
    final Response<YOURMODELNAME> res = response;
    
    // sample response model
    // res.body().getEmail()
  }
  
  @Override
  public void onFailure(Call<YOURMODELNAME> call, Throwable t) {
    
  }
});
```

### SMTP
```sh
@Inject
SMTP smtp;

smtp.username = "your-gmail@gmail.com";
smtp.password = "your-password";

smtp.host = "smtp.gmail.com";

// single email
smtp.email = "to-someone@yahoo.com";
// multiple email
smtp.emailMultiple = "to-someone@yahoo.com,to-other-someone@yahoo.com";

smtp.subject = "JavaMail Demo";
smtp.message = "Content of the demo";

smtp.attachment = new File(Environment.getExternalStorageDirectory() +
  File.separator + "DCIM" + File.separator + "Camera" + File.separator + "your-image.png").toString();

// send the email
smtp.execute();
```

### IMAP
```sh
@Inject
IMAP imap;

private class FetchEmail extends AsyncTask<Void, Void, Void> {
  @Override
        protected Void doInBackground(Void... voids) {
            imap.username = "your-email@your-provider.com";
            imap.password = "your-email-password";

            imap.host = "imap.your-provider.com";

            IMAP.ContentMessages[] messages = imap.fetch();
            for (IMAP.ContentMessages message : messages) {
              // message.getReceivedDate().toString()
            }

            // close the connection
            imap.close();

            return null;
        }
}

// execute the task
new FetchEmail().execute();
```

### Network Connection Type
```sh
@Inject
Network network;

// type
network.connectionInfo().getString("type");
// info
network.connectionInfo().getString("info");
```

# Other Resources
<p><a href="https://developers.google.com/api-client-library/java/google-api-java-client/oauth2">Using OAuth 2.0 with the Google API Client Library for Java</a></p>

# License
Android Clean Architecture Boilerplate is licensed under the Apache License (ASL) license. For more information, see the LICENSE file in this repository.
