package disono.webmons.com.utilities.exception;

import android.content.Context;

import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/3/2016 3:15 PM
 */
public class WBConsole {
    public static void e(Context context, String message) {
        Timber.e("%s, Error: %s", indentifier(context), message);
    }

    public static void e(String message) {
        Timber.e("%s, Error: %s", indentifier(null), message);
    }

    public static void e(String tag, String message) {
        Timber.e("%s, Error: %s", tag, message);
    }

    public static void i(Context context, String message) {
        Timber.i("%s, Info: %s", indentifier(context), message);
    }

    public static void i(String message) {
        Timber.i("%s, Info: %s", indentifier(null), message);
    }

    public static void i(String tag, String message) {
        Timber.e("%s, Error: %s", tag, message);
    }

    public static void d(Context context, String message) {
        Timber.d("%s, Debug: %s", indentifier(context), message);
    }

    public static void d(String message) {
        Timber.d("%s, Debug: %s", indentifier(null), message);
    }

    public static void d(String tag, String message) {
        Timber.e("%s, Error: %s", tag, message);
    }

    private static String indentifier(Context context) {
        String identifier = "Logs";

        if (context != null) {
            String className = context.getClass().getCanonicalName();
            String packageName = context.getPackageName();
            identifier = packageName + ":" + className;
        }

        return identifier;
    }
}
