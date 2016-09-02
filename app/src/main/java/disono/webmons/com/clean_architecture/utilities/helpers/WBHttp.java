package disono.webmons.com.clean_architecture.utilities.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;

import java.io.IOException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 2:05 PM
 */
public class WBHttp {

    /**
     * Download image then set on src
     *
     * @param source
     * @param img_avatar
     */
    public static void imgSetCIDownload(String source, CircleImageView img_avatar) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final Bitmap[] mIcon_val = {null};

        Thread thread = new Thread(() -> {
            URL imgURL = null;
            try {
                imgURL = new URL(source);
                mIcon_val[0] = BitmapFactory.decodeStream(imgURL.openConnection().getInputStream());
                img_avatar.setImageBitmap(mIcon_val[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
