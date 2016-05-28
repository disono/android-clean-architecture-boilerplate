package disono.webmons.com.clean_architecture;

import android.app.Application;

import disono.webmons.com.clean_architecture.DI.modules.ApplicationComponent;
import disono.webmons.com.clean_architecture.DI.modules.DaggerApplicationComponent;
import timber.log.Timber;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-13 03:20 PM
 */
public class AndroidApplication extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .build();

        Timber.plant(new Timber.DebugTree());
    }

    public ApplicationComponent component() {
        return component;
    }
}
