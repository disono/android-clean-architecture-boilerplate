package disono.webmons.com.clean_architecture.dependencies.modules;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import disono.webmons.com.clean_architecture.util.sensor.Camera.Launcher;
import disono.webmons.com.clean_architecture.util.sensor.GeoLocation.GPS;
import disono.webmons.com.clean_architecture.util.sensor.Media.AudioHandler;
import disono.webmons.com.clean_architecture.util.sensor.Media.AudioRecord;
import disono.webmons.com.clean_architecture.util.sensor.Motion.AccelListener;
import disono.webmons.com.clean_architecture.util.sensor.Orientation.ScreenOrientation;
import disono.webmons.com.clean_architecture.util.sensor.Vibration.Vibrate;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-28 01:02 PM
 */
@Module
public class SensorModule {
    private Activity activity;

    public SensorModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    public Launcher provideCamera() {
        return new Launcher(activity);
    }

    @Provides
    @Singleton
    public GPS provideLocation() {
        return new GPS(activity);
    }

    @Provides
    @Singleton
    public AccelListener provideMotion() {
        return new AccelListener(activity);
    }

    @Provides
    @Singleton
    public ScreenOrientation provideOrientation() {
        return new ScreenOrientation(activity);
    }

    @Provides
    @Singleton
    public Vibrate provideVibrate() {
        return new Vibrate(activity);
    }

    @Provides
    @Singleton
    public AudioHandler provideAudioHandler() {
        return new AudioHandler(activity);
    }

    @Provides
    @Singleton
    public AudioRecord provideAudioRecord() {
        return new AudioRecord(activity);
    }
}
