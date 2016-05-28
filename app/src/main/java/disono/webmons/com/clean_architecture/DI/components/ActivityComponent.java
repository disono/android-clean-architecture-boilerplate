package disono.webmons.com.clean_architecture.DI.components;

import javax.inject.Singleton;

import dagger.Component;
import disono.webmons.com.clean_architecture.DI.modules.SensorModule;
import disono.webmons.com.clean_architecture.presentation.ui.activities.MainActivity;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-28 04:48 PM
 */
@Singleton
@Component(
        modules = SensorModule.class
)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
