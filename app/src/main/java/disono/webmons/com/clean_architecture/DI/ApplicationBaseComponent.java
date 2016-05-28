package disono.webmons.com.clean_architecture.DI;

import disono.webmons.com.clean_architecture.AndroidApplication;
import disono.webmons.com.clean_architecture.DI.components.ApplicationComponent;
import disono.webmons.com.clean_architecture.DI.components.DaggerApplicationComponent;
import disono.webmons.com.clean_architecture.DI.modules.AppModule;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-28 05:17 PM
 */
public class ApplicationBaseComponent {
    private static ApplicationComponent component;

    public static void inject(AndroidApplication application) {
        component = DaggerApplicationComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public static ApplicationComponent component() {
        return component;
    }
}
