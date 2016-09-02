package disono.webmons.com.clean_architecture.dependencies.components;

import javax.inject.Singleton;

import dagger.Component;
import disono.webmons.com.clean_architecture.dependencies.modules.LibraryModule;
import disono.webmons.com.clean_architecture.dependencies.modules.SensorModule;
import disono.webmons.com.clean_architecture.presentation.ui.activities.MainActivity;
import disono.webmons.com.clean_architecture.presentation.ui.activities.authenticate.LoginActivity;
import disono.webmons.com.clean_architecture.presentation.ui.activities.settings.SettingsActivity;
import disono.webmons.com.clean_architecture.presentation.ui.fragments.settings.GeneralSettingsFragment;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-28 04:48 PM
 */
@Singleton
@Component(
        modules = {SensorModule.class, LibraryModule.class}
)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(SettingsActivity settingsActivity);

    void inject(GeneralSettingsFragment generalSettingsFragment);
}
