package disono.webmons.com.clean_architecture.util.sensor.Motion;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;

import java.util.List;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-28 05:53 PM
 */
public class AccelListener {
    public static int STOPPED = 0;
    public static int STARTING = 1;
    public static int RUNNING = 2;
    public static int ERROR_FAILED_TO_START = 3;
    private Handler mainHandler = null;
    private SensorEventListener listener;

    private Runnable mainRunnable = new Runnable() {
        @Override
        public void run() {
            AccelListener.this.timeout();
        }
    };

    private Application application;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    // most recent values
    private float x, y, z;
    private int status;
    private long timestamp;
    private int accuracy = SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM;

    public AccelListener(Activity activity) {
        this.application = activity.getApplication();
        mSensorManager = (SensorManager) application.getSystemService(Context.SENSOR_SERVICE);
    }

    public void listener(SensorEventListener listener) {
        this.listener = listener;
    }

    public int start() {
        // if already starting or running, then restart timeout and return
        if ((this.status == AccelListener.RUNNING) || (this.status == AccelListener.STARTING)) {
            startTimeout();
            return this.status;
        }

        this.setStatus(AccelListener.STARTING);

        // get accelerometer from sensor manager
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        // if found, then register as listener
        if ((list != null) && (list.size() > 0)) {
            this.mSensor = list.get(0);

            if (this.mSensorManager.registerListener(listener, this.mSensor, SensorManager.SENSOR_DELAY_UI)) {
                this.setStatus(AccelListener.STARTING);
            } else {
                this.setStatus(AccelListener.ERROR_FAILED_TO_START);
                this.fail(AccelListener.ERROR_FAILED_TO_START, "Device sensor returned an error");
                return this.status;
            }
        } else {
            this.setStatus(AccelListener.ERROR_FAILED_TO_START);
            this.fail(AccelListener.ERROR_FAILED_TO_START, "No sensors found to register accelerometer listening to.");
            return this.status;
        }

        startTimeout();

        return this.status;
    }

    private void stop() {
        stopTimeout();

        if (this.status != AccelListener.STOPPED) {
            this.mSensorManager.unregisterListener(listener);
        }

        this.setStatus(AccelListener.STOPPED);
        this.accuracy = SensorManager.SENSOR_STATUS_UNRELIABLE;
    }

    private void startTimeout() {
        // set a timeout callback on the main thread.
        stopTimeout();
        mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.postDelayed(mainRunnable, 2000);
    }

    private void stopTimeout() {
        if (mainHandler != null) {
            mainHandler.removeCallbacks(mainRunnable);
        }
    }

    private void timeout() {
        if (this.status == AccelListener.STARTING) {
            // call win with latest cached position
            this.timestamp = System.currentTimeMillis();
            this.win();
        }
    }

    private void setStatus(int status) {
        this.status = status;
    }

    private void win() {
        // success return object
    }

    private void fail(int code, String message) {
        // error object
    }
}
