package disono.webmons.com.clean_architecture;

import android.app.Application;

import timber.log.Timber;

public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
