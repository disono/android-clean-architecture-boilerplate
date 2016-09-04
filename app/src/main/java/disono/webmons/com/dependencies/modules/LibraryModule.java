package disono.webmons.com.dependencies.modules;

import android.app.Activity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import disono.webmons.com.utilities.library.Dialogs.Sweet.WBAlerts;
import disono.webmons.com.utilities.library.LocalStorage;
import disono.webmons.com.utilities.library.Mail.IMAP;
import disono.webmons.com.utilities.library.Mail.SMTP;
import disono.webmons.com.utilities.library.SMS;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
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

    @Provides
    @Singleton
    public SMTP provideSMTP() {
        return new SMTP(activity);
    }

    @Provides
    @Singleton
    public IMAP provideIMAP() {
        return new IMAP(activity);
    }

    @Provides
    @Singleton
    public WBAlerts provideWBAlerts() {
        return new WBAlerts(activity);
    }
}
