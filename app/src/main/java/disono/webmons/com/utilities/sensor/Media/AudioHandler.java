package disono.webmons.com.utilities.sensor.Media;

import android.app.Activity;
import android.app.Application;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-07-29 05:22 PM
 */
public class AudioHandler {
    public static String TAG = "AudioHandler:Class";

    private Application application;
    MediaPlayer mediaPlayer;

    public AudioHandler(Activity activity) {
        this.application = activity.getApplication();
    }

    /**
     * Play audio on source
     *
     * @param uri
     */
    public void play(String uri) {
        Uri source = Uri.parse(uri);

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(this.application.getApplicationContext(), source);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Timber.e("%s, message: %s", TAG, e.getMessage());
        }
    }

    /**
     * Stop playing audio
     */
    public void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
