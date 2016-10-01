package disono.webmons.com.dependencies.components;

import javax.inject.Singleton;

import dagger.Component;
import disono.webmons.com.clean_architecture.presentation.ui.activities.MainActivity;
import disono.webmons.com.clean_architecture.presentation.ui.activities.authenticate.ForgotPasswordActivity;
import disono.webmons.com.clean_architecture.presentation.ui.activities.authenticate.LoginActivity;
import disono.webmons.com.clean_architecture.presentation.ui.activities.authenticate.RegisterActivity;
import disono.webmons.com.clean_architecture.presentation.ui.activities.settings.SettingsActivity;
import disono.webmons.com.clean_architecture.presentation.ui.fragments.settings.GeneralSettingsFragment;
import disono.webmons.com.clean_architecture.presentation.ui.fragments.settings.SecuritySettingsFragment;
import disono.webmons.com.dependencies.modules.LibraryModule;
import disono.webmons.com.dependencies.modules.SensorModule;

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

    void inject(RegisterActivity registerActivity);

    void inject(SettingsActivity settingsActivity);

    void inject(GeneralSettingsFragment generalSettingsFragment);

    void inject(SecuritySettingsFragment securitySettingsFragment);

    void inject(ForgotPasswordActivity forgotPasswordActivity);
}
