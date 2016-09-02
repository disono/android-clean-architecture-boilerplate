package disono.webmons.com.clean_architecture.utilities.helpers;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/1/2016 10:52 PM
 */
public class WBSecurity {
    private final static String TAG = "WBSecurity:Class";

    public static String MD5(String toHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(toHash.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);

            for (byte anA : a) {
                sb.append(Character.forDigit((anA & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(anA & 0x0f, 16));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Timber.e("%s, Error: %s", TAG, e.getMessage());
        }

        return null;
    }

    public static String encodeBase64(String toConvert) {
        // encode data on your side using BASE64
        return Base64.encodeToString(toConvert.getBytes(), Base64.DEFAULT);
    }

    public static String decodeBase64(String converted) {
        return new String(Base64.decode(converted, Base64.DEFAULT));
    }
}
