package disono.webmons.com.clean_architecture.presentation.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import disono.webmons.com.clean_architecture.DI.ActivityBaseComponent;
import disono.webmons.com.clean_architecture.R;
import disono.webmons.com.clean_architecture.data.services.git.adapter.GitAdapter;
import disono.webmons.com.clean_architecture.data.services.git.model.GitModel;
import disono.webmons.com.clean_architecture.presentation.presenters.listeners.DialogInterfaceFactory;
import disono.webmons.com.clean_architecture.presentation.presenters.MainPresenter.View;
import disono.webmons.com.clean_architecture.presentation.ui.activities.user.UserListActivity;
import disono.webmons.com.clean_architecture.threading.MainThreadImp;
import disono.webmons.com.clean_architecture.util.sensor.Camera.Launcher;
import disono.webmons.com.clean_architecture.util.sensor.GeoLocation.GPS;
import disono.webmons.com.clean_architecture.util.sensor.Motion.AccelListener;
import disono.webmons.com.clean_architecture.util.ui.DialogFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-12 11:26 AM
 */
public class MainActivity extends AppCompatActivity implements View {
    Context ctx;
    private int REQUEST_IMAGE_CAPTURE;

    @Inject
    Launcher launcher;

    @Inject
    GPS gps;

    @Inject
    AccelListener accelListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this.getApplication().getApplicationContext();

        ActivityBaseComponent.inject(this);
        ActivityBaseComponent.component().inject(this);

        REQUEST_IMAGE_CAPTURE = launcher.REQUEST_IMAGE_CAPTURE;
        launcher.takePicture();

        accelListener.listener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // only look at accelerometer events
                if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
                    return;
                }

                Toast.makeText(MainActivity.this, "Acc X: " + event.values[0],
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        });

        gps.run(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Toast.makeText(MainActivity.this, "Lat: " + location.getLatitude(),
                        Toast.LENGTH_SHORT).show();

                Toast.makeText(MainActivity.this, "Lng: " + location.getLongitude(),
                        Toast.LENGTH_SHORT).show();
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

        // dialog
        DialogFactory.error(this, "Error", "No internet connection!",
                new DialogInterfaceFactory().OnClick(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ctx, "Working...", Toast.LENGTH_LONG).show();
            }
        })).show();

        // activity user list
        Intent intent = new Intent(getApplicationContext(), UserListActivity.class);
        startActivity(intent);

        // call API Demo
        GitAdapter gitAdapter = new GitAdapter();
        Call<GitModel> call = gitAdapter.getUser("disono");
        call.enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                final Response<GitModel> res = response;

                MainThreadImp.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ctx, "API Response: " +
                                res.body().getEmail(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<GitModel> call, Throwable t) {
                MainThreadImp.getInstance().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ctx, "Error API...", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (imageBitmap != null) {
                Toast.makeText(ctx, "Data: " + imageBitmap.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
