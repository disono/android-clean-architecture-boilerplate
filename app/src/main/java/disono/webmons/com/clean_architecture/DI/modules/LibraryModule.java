package disono.webmons.com.clean_architecture.DI.modules;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import disono.webmons.com.clean_architecture.util.lib.LocalStorage;
import disono.webmons.com.clean_architecture.util.lib.SMS;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-07-20 06:37 PM
 */
@Module
public class LibraryModule {
    private Activity activity;

    public LibraryModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    public LocalStorage provideLocalStorage() {
        return new LocalStorage(activity);
    }

    @Provides
    @Singleton
    public SMS provideSMS() {
        return new SMS(activity);
    }
}
