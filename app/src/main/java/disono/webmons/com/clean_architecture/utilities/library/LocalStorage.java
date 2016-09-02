package disono.webmons.com.clean_architecture.utilities.library;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-25 02:48 PM
 */
public class LocalStorage {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String prefName;

    private Activity activity;
    private Context ctx;

    public LocalStorage(Activity activity) {
        this.activity = activity;

        ctx = this.activity.getApplication();
        prefName = this.activity.getApplicationContext().getPackageName();
    }

    /**
     * Get String
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, null);
        }

        return null;
    }

    /**
     * Get Integer
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getInt(key, 0);
        }

        return -1;
    }

    /**
     * Get float
     *
     * @param key
     * @return
     */
    public float getFloat(String key) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getFloat(key, 0);
        }

        return -1;
    }

    /**
     * Get boolean
     *
     * @param key
     * @return
     */
    public boolean getBool(String key) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        return sharedPreferences != null && sharedPreferences.getBoolean(key, false);
    }

    /**
     * Create String
     *
     * @param key
     * @param value
     */
    public void createString(String key, String value) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    /**
     * Create Integer
     *
     * @param key
     * @param value
     */
    public void createInt(String key, int value) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    /**
     * Create float
     *
     * @param key
     * @param value
     */
    public void createFloat(String key, float value) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putFloat(key, value);
            editor.apply();
        }
    }

    /**
     * Create boolean
     *
     * @param key
     * @param value
     */
    public void createBool(String key, boolean value) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    /**
     * Delete
     *
     * @param key
     */
    public void delete(String key) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(key).apply();
        }
    }

    /**
     * Clear all
     *
     * @param ctx
     */
    public void clear(Context ctx) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().apply();
        }
    }
}
