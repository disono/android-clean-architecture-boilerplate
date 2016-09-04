package disono.webmons.com.utilities.sensor.Media;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/24/2016 5:20 PM
 */
public class VideoHandler {
    public static String TAG = "VideoHandler:Class";

    private Application application;

    private static final String YOUTUBE = "youtube.com";
    private static final String ASSETS = "file:///android_asset/";

    public VideoHandler(Activity activity) {
        this.application = activity.getApplication();
    }

    /**
     * Play the video
     *
     * @param url
     */
    public void play(String url) {
        try {
            if (url.contains("bit.ly/") || url.contains("tinyurl.com/") || url.contains("goo.gl/") ||
                    url.contains("youtu.be/")) {

                // support for google / bitly / tinyurl / youtube shortens
                URLConnection urlConnection = new URL(url).openConnection();
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                // new redirected url
                url = urlConnection.getURL().toString();
                inputStream.close();
            }

            // create URI
            Uri uri = Uri.parse(url);
            Intent intent;

            // check to see if someone is trying to play a YouTube page.
            if (url.contains(YOUTUBE)) {
                // if we don't do it this way you don't have the option for youtube
                uri = Uri.parse("vnd.youtube:" + uri.getQueryParameter("v"));

                if (checkPackage("com.google.android.youtube")) {
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                } else {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.google.android.youtube"));
                }
            } else if (url.contains(ASSETS)) {
                // get file path in assets folder
                String filepath = url.replace(ASSETS, "");
                // get actual filename from path as command to write to internal storage doesn't like folders
                String filename = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());

                // don't copy the file if it already exists
                File file = new File(this.application.getFilesDir() + "/" + filename);
                if (!file.exists()) {
                    this.copy(filepath, filename);
                }

                // change uri to be to the new file in internal storage
                uri = Uri.parse("file://" + this.application.getFilesDir() + "/" + filename);

                intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "video/*");
            } else {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "video/*");
            }

            // launch the player
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.application.startActivity(intent);
        } catch (IOException e) {
            Timber.e("%s, message: %s", TAG, e.getMessage());
        }
    }

    /**
     * Copy the file to play
     *
     * @param fileFrom
     * @param fileTo
     * @throws IOException
     */
    private void copy(String fileFrom, String fileTo) throws IOException {
        // get file to be copied from assets
        InputStream inputStream = this.application.getAssets().open(fileFrom);

        // get file where copied too, in internal storage.
        // must be MODE_WORLD_READABLE or Android can't play it
        FileOutputStream outputStream = this.application.openFileOutput(fileTo, Context.MODE_WORLD_READABLE);

        // transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;

        while ((len = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, len);
        }

        inputStream.close();
        outputStream.close();
    }

    /**
     * Check package if installed
     *
     * @param packageName
     * @return
     */
    private boolean checkPackage(String packageName) {
        PackageManager packageManager = this.application.getPackageManager();

        try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Timber.e("%s, message: %s", TAG, e.getMessage());
            return false;
        }
    }
}
