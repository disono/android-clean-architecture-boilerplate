package disono.webmons.com.clean_architecture;

import android.app.Application;

import disono.webmons.com.clean_architecture.dependencies.ApplicationBaseComponent;
import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-13 03:20 PM
 */
public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationBaseComponent.inject(this);

        Timber.plant(new Timber.DebugTree());
    }
}
