package disono.webmons.com.utilities.sensor.Camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.io.File;

import disono.webmons.com.utilities.helpers.WBFile;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-26 06:39 PM
 */
public class Launcher {
    private final static String TAG = "Launcher:Class";
    private Activity mActivity;
    public final int REQUEST_IMAGE_CAPTURE = 1000;
    public final int REQUEST_VIDEO_CAPTURE = 1002;

    protected final static String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    public Launcher(Activity activity) {
        this.mActivity = activity;
    }

    // take a picture
    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(mActivity.getApplicationContext().getPackageManager()) != null) {
            mActivity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void takePicture(FragmentActivity fragmentActivity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(mActivity.getApplicationContext().getPackageManager()) != null) {
            fragmentActivity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void takeVideo(String duration, String quality) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);


        // Specify the maximum allowed recording duration in seconds.
        if (duration != null) {
            intent.putExtra("android.intent.extra.durationLimit", duration);
        }

        // The name of the Intent-extra used to control the quality of a recorded video. This is an integer property.
        // Currently value 0 means low quality, suitable for MMS messages, and value 1 means high quality. In the future other quality levels may be added.
        if (quality != null) {
            intent.putExtra("android.intent.extra.videoQuality", quality);
        }

        mActivity.startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
    }

    public void takeVideo() {
        // default recording in 20 minutes only
        // quality is high
        this.takeVideo("1200", "1");
    }

    public Intent takeIntentPicture() {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    public String videoLocation(Intent data) {
        Uri uriData = null;

        if (data != null) {
            // Get the uri of the video clip
            uriData = data.getData();
        }

        if (uriData == null) {
            File movie = new File(this.getTempDirectoryPath(), WBFile.randomString(64) + ".mp4");
            uriData = Uri.fromFile(movie);

            String path = WBFile.getRealPathFromURI(mActivity, uriData);
            Log.i(TAG, "Video Location: " + path);
            return path;
        } else {
            String path = WBFile.getRealPathFromURI(mActivity, uriData);
            Log.i(TAG, "Video Location: " + path);
            return path;
        }
    }

    public File videoFile(Intent data) {
        Uri uriData = null;

        if (data != null) {
            // Get the uri of the video clip
            uriData = data.getData();
        }

        if (uriData == null) {
            File movie = new File(this.getTempDirectoryPath(), WBFile.randomString(64) + ".mp4");
            uriData = Uri.fromFile(movie);

            return new File(WBFile.getRealPathFromURI(mActivity, uriData));
        } else {
            return new File(WBFile.getRealPathFromURI(mActivity, uriData));
        }
    }

    public String getTempDirectoryPath() {
        // Use internal storage
        File cache = mActivity.getCacheDir();

        // Create the cache directory if it doesn't exist
        cache.mkdirs();
        return cache.getAbsolutePath();
    }
}
