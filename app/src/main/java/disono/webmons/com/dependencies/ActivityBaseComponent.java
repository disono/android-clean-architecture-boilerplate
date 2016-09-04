package disono.webmons.com.dependencies;

import android.app.Activity;

import disono.webmons.com.dependencies.components.ActivityComponent;
import disono.webmons.com.dependencies.components.DaggerActivityComponent;
import disono.webmons.com.dependencies.modules.LibraryModule;
import disono.webmons.com.dependencies.modules.SensorModule;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-28 05:06 PM
 */
public class ActivityBaseComponent {
    private static ActivityComponent component;

    public static void inject(Activity activity) {
        component = DaggerActivityComponent.builder()
                .sensorModule(new SensorModule(activity))
                .libraryModule(new LibraryModule(activity))
                .build();
    }

    public static ActivityComponent component() {
        return component;
    }
}
