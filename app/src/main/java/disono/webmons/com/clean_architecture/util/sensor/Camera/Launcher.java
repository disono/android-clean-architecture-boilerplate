package disono.webmons.com.clean_architecture.util.sensor.Camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-26 06:39 PM
 */
public class Launcher {
    private Activity activity;
    public final int REQUEST_IMAGE_CAPTURE = 1000;

    protected final static String[] permissions = { Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE };

    public Launcher(Activity activity) {
        this.activity = activity;
    }

    // take a picture
    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(activity.getApplicationContext().getPackageManager()) != null) {
            activity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
