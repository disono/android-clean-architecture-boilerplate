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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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
import disono.webmons.com.clean_architecture.util.sensor.Media.AudioHandler;
import disono.webmons.com.clean_architecture.util.sensor.Motion.AccelListener;
import disono.webmons.com.clean_architecture.util.sensor.Orientation.ScreenOrientation;
import disono.webmons.com.clean_architecture.util.sensor.Vibration.Vibrate;
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

    @Inject
    ScreenOrientation orientation;

    @Inject
    Vibrate vibrate;

    @Inject
    AudioHandler audioHandler;

    @BindView(R.id.btn_start_acc)
    Button btn_start_acc;
    @BindView(R.id.btn_stop_acc)
    Button btn_stop_acc;
    @BindView(R.id.txt_acc_x)
    TextView txt_acc_x;
    @BindView(R.id.txt_acc_y)
    TextView txt_acc_y;
    @BindView(R.id.txt_acc_z)
    TextView txt_acc_z;

    @BindView(R.id.txt_gps_lat)
    TextView txt_gps_lat;
    @BindView(R.id.txt_gps_lng)
    TextView txt_gps_lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ActivityBaseComponent.inject(this);
        ActivityBaseComponent.component().inject(this);

        ctx = this.getApplication().getApplicationContext();

        // camera
        REQUEST_IMAGE_CAPTURE = launcher.REQUEST_IMAGE_CAPTURE;
        launcher.takePicture();

        // accelerometer
        accelListener.listener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                // only look at accelerometer events
                if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
                    return;
                }

                txt_acc_x.setText(String.valueOf("X: " + event.values[0]));
                txt_acc_y.setText(String.valueOf("Y: " + event.values[1]));
                txt_acc_z.setText(String.valueOf("Z: " + event.values[2]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        });

        // start the sensor accelerometer
        btn_start_acc.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                try {
                    accelListener.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // stop the sensor accelerometer
        btn_stop_acc.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                accelListener.stop();
            }
        });

        // gps
        gps.run(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                txt_gps_lat.setText(String.valueOf("Lat: " + location.getLatitude()));
                txt_gps_lng.setText(String.valueOf("Lng: " + location.getLongitude()));
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

        // screen orientation
        try {
            orientation.apply("LANDSCAPE");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // vibration
        long[] patterns = {100, 200, 300, 400, 500};
        vibrate.pattern(patterns, 1);

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

        // play audio
        audioHandler.play("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.audio);
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
