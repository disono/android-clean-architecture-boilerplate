package disono.webmons.com.utilities.sensor.Orientation;

import android.app.Activity;
import android.content.pm.ActivityInfo;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-29 02:40 PM
 */
public class ScreenOrientation {
    private Activity activity;

    public ScreenOrientation(Activity activity) {
        this.activity = activity;
    }

    public void apply(String orientation) throws Exception {
        switch (orientation) {
            case "UNSPECIFIED":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                break;
            case "LANDSCAPE_PRIMARY":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case "PORTRAIT_PRIMARY":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case "LANDSCAPE":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                break;
            case "PORTRAIT":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                break;
            case "LANDSCAPE_SECONDARY":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
                break;
            case "PORTRAIT_SECONDARY":
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                break;
            default:
                throw new Exception("Unrecognised screen orientation.");
        }
    }
}
