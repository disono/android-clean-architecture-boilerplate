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
public class Pref {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String prefName;

    public String getString(Context context, String key) {
        prefName = context.getResources().getString(R.string.main_package);
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, null);
        }

         return null;
    }

    public int getInt(Context context, String key) {
        prefName = context.getResources().getString(R.string.main_package);
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getInt(key, 0);
        }

        return -1;
    }

    public float getFloat(Context context, String key) {
        prefName = context.getResources().getString(R.string.main_package);
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getFloat(key, 0);
        }

        return -1;
    }

    public boolean getBool(Context context, String key) {
        prefName = context.getResources().getString(R.string.main_package);
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        return sharedPreferences != null && sharedPreferences.getBoolean(key, false);
    }

    public void createString(Context context, String key, String value) {
        prefName = context.getResources().getString(R.string.main_package);
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public void createInt(Context context, String key, int value) {
        prefName = context.getResources().getString(R.string.main_package);
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    public void createFloat(Context context, String key, float value) {
        prefName = context.getResources().getString(R.string.main_package);
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putFloat(key, value);
            editor.apply();
        }
    }

    public void createBool(Context context, String key, boolean value) {
        prefName = context.getResources().getString(R.string.main_package);
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public void delete(Context context, String key) {
        prefName = context.getResources().getString(R.string.main_package);
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(key).apply();
        }
    }

    public void clear(Context context) {
        prefName = context.getResources().getString(R.string.main_package);
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().apply();
        }
    }
}
