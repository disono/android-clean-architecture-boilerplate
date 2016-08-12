package disono.webmons.com.clean_architecture.util.lib;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import disono.webmons.com.clean_architecture.R;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
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
        prefName = this.activity.getResources().getString(R.string.main_package);
    }

    public String getString(String key) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getString(key, null);
        }

         return null;
    }

    public int getInt(String key) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getInt(key, 0);
        }

        return -1;
    }

    public float getFloat(String key) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            return sharedPreferences.getFloat(key, 0);
        }

        return -1;
    }

    public boolean getBool(String key) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        return sharedPreferences != null && sharedPreferences.getBoolean(key, false);
    }

    public void createString(String key, String value) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public void createInt(String key, int value) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    public void createFloat(String key, float value) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putFloat(key, value);
            editor.apply();
        }
    }

    public void createBool(String key, boolean value) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public void delete(String key) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(key).apply();
        }
    }

    public void clear(Context ctx) {
        sharedPreferences = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        if (sharedPreferences != null) {
            sharedPreferences.edit().clear().apply();
        }
    }
}
