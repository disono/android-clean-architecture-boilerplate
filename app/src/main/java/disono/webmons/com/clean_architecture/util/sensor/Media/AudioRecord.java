package disono.webmons.com.clean_architecture.util.sensor.Media;

import android.app.Activity;
import android.app.Application;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.IOException;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-07-29 05:22 PM
 */
public class AudioRecord {
    public static String TAG = "AudioRecord";
    private static MediaRecorder audioRecorder;

    private Application application;

    public AudioRecord(Activity activity) {
        this.application = activity.getApplication();
    }

    /**
     * Start record with auto generate output file
     */
    public static void start() {
        start(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio-" + Math.random() + ".3gp");
    }

    /**
     * Start record with uri output file
     *
     * @param uriOutputFile
     */
    public static void start(String uriOutputFile) {
        audioRecorder = new MediaRecorder();

        audioRecorder = new MediaRecorder();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        audioRecorder.setOutputFile(uriOutputFile);

        try {
            audioRecorder.prepare();
            audioRecorder.start();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            Timber.e("%s, message: %s", TAG, e.getMessage());
        }
    }

    /**
     * Stop recording
     */
    public static void stop() {
        if (audioRecorder != null) {
            audioRecorder.stop();
            audioRecorder.release();
            audioRecorder = null;
        }
    }

    /**
     * onPause
     */
    public static void pause() {
        if (audioRecorder != null) {
            audioRecorder.release();
            audioRecorder = null;
        }
    }
}
