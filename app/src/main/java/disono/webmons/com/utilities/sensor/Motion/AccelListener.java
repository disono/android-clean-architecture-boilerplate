package disono.webmons.com.utilities.sensor.Motion;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.List;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-28 05:53 PM
 */
public class AccelListener {
    private static final String TAG = "AccelListener";

    private Application application;
    public int accuracy = SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM;

    private SensorEventListener listener;
    // sensor manager
    private SensorManager mSensorManager;
    // acceleration sensor returned by sensor manager
    private Sensor mSensor;

    public static int STOPPED = 0;
    public static int STARTING = 1;
    public static int RUNNING = 2;
    public static int ERROR_FAILED_TO_START = 3;

    // status listener
    public int status;

    public AccelListener(Activity activity) {
        this.application = activity.getApplication();
        mSensorManager = (SensorManager) application.getSystemService(Context.SENSOR_SERVICE);
    }

    // listen for sensor data
    public void listener(SensorEventListener listener) {
        this.listener = listener;
    }

    // start the sensor
    public int start() throws Exception {
        // if already starting or running, then restart timeout and return
        if ((this.status == AccelListener.RUNNING) || (this.status == AccelListener.STARTING)) {
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
                this.fail(AccelListener.ERROR_FAILED_TO_START, "Device sensor returned an error.");
                return this.status;
            }
        } else {
            this.setStatus(AccelListener.ERROR_FAILED_TO_START);
            this.fail(AccelListener.ERROR_FAILED_TO_START, "No sensors found to register accelerometer listening to.");
            return this.status;
        }

        return this.status;
    }

    // stop the sensor
    public void stop() {
        if (this.status != AccelListener.STOPPED) {
            this.mSensorManager.unregisterListener(listener);
        }

        this.setStatus(AccelListener.STOPPED);
        this.accuracy = SensorManager.SENSOR_STATUS_UNRELIABLE;
    }

    // set the status of the sensor
    public void setStatus(int status) {
        this.status = status;
    }

    // error occured
    public void fail(int code, String message) throws Exception {
        Timber.d("%s code: %i, message: %s", TAG, code, message);
        throw new Exception("Code: " + code + ", message: " + message);
    }
}
