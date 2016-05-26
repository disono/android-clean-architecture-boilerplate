package disono.webmons.com.clean_architecture.util.lib;

import android.content.Context;
import android.content.SharedPreferences;

import disono.webmons.com.clean_architecture.R;

/**
 * Author: Archie, Disono (disono.apd@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-25 02:48 PM
 */
public class LocalStorage {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String prefName;

    public String getString(Context ctx, String key) {
        prefName = ctx.getResources().getString(R.string.main_package);
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, null);
        }

         return null;
    }

    public int getInt(Context ctx, String key) {
        prefName = ctx.getResources().getString(R.string.main_package);
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getInt(key, 0);
        }

        return -1;
    }

    public float getFloat(Context ctx, String key) {
        prefName = ctx.getResources().getString(R.string.main_package);
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getFloat(key, 0);
        }

        return -1;
    }

    public boolean getBool(Context ctx, String key) {
        prefName = ctx.getResources().getString(R.string.main_package);
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        return sharedPreferences != null && sharedPreferences.getBoolean(key, false);
    }

    public void createString(Context ctx, String key, String value) {
        prefName = ctx.getResources().getString(R.string.main_package);
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public void createInt(Context ctx, String key, int value) {
        prefName = ctx.getResources().getString(R.string.main_package);
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    public void createFloat(Context ctx, String key, float value) {
        prefName = ctx.getResources().getString(R.string.main_package);
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putFloat(key, value);
            editor.apply();
        }
    }

    public void createBool(Context ctx, String key, boolean value) {
        prefName = ctx.getResources().getString(R.string.main_package);
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public void delete(Context ctx, String key) {
        prefName = ctx.getResources().getString(R.string.main_package);
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(key).apply();
        }
    }

    public void clear(Context ctx) {
        prefName = ctx.getResources().getString(R.string.main_package);
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().apply();
        }
    }
}
