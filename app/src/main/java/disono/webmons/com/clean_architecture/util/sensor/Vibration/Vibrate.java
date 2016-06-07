package disono.webmons.com.clean_architecture.util.sensor.Vibration;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-29 02:55 PM
 */
public class Vibrate {
    private AudioManager manager;
    private Vibrator vibrator;

    public Vibrate(Activity activity) {
        this.manager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        this.vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * Vibrate only once
     *
     * @param time
     */
    public void once(long time) {
        // zero defaults to half a second
        if (time == 0) {
            time = 500;
        }

        if (!this.isSilentMode()) {
            this.vibrator.vibrate(time);
        }
    }

    /**
     * Vibrate with pattern and with repeat
     *
     * @param patterns
     * @param repeat
     */
    public void pattern(long[] patterns, int repeat) {
        int size = patterns.length + 1;
        long[] patternList = new long[size];
        // add zero at the beginning of pattern list to align with W3C
        patternList[0] = 0;

        System.arraycopy(patterns, 0, patternList, 1, size - 1);

        if (!this.isSilentMode()) {
            this.vibrator.vibrate(patternList, repeat);
        }
    }

    /**
     * Vibrate with pattern and without repeat
     *
     * @param patterns
     */
    public void pattern(long[] patterns) {
        this.pattern(patterns, 0);
    }

    /**
     * Stop any currently running vibration
     */
    public void stop() {
        this.vibrator.cancel();
    }

    /**
     * Check if the phone is on silent mode
     *
     * @return
     */
    public boolean isSilentMode() {
        return this.manager.getRingerMode() == AudioManager.RINGER_MODE_SILENT;
    }
}
