package disono.webmons.com.utilities.helpers;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 8:56 PM
 */
public class WBFile {

    /**
     * Bitmap URI
     *
     * @param context
     * @param inImage
     * @return
     */
    public static Uri getBmpUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    /**
     * Get the real path from URI
     *
     * @param activity
     * @param uri
     * @return
     */
    public static String getRealPathFromURI(Activity activity, Uri uri) {
        Cursor cursor = activity.getContentResolver().query(uri, null, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    /**
     * Bitmap to file
     *
     * @param activity
     * @param bitmap
     * @return
     */
    public static File bmpToFile(Activity activity, Bitmap bitmap) {
        // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
        Uri tempUri = WBFile.getBmpUri(activity.getApplicationContext(), bitmap);

        // CALL THIS METHOD TO GET THE ACTUAL PATH
        return new File(WBFile.getRealPathFromURI(activity, tempUri));
    }
}
