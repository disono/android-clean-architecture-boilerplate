package disono.webmons.com.clean_architecture.DI.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import disono.webmons.com.clean_architecture.AndroidApplication;
import disono.webmons.com.clean_architecture.DI.ForApplication;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-28 01:03 PM
 */
@Module(
        includes = SensorModule.class
)
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContent() {
        return this.application;
    }
}
