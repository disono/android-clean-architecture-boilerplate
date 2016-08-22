package disono.webmons.com.clean_architecture.dependencies.components;

import javax.inject.Singleton;

import dagger.Component;
import disono.webmons.com.clean_architecture.dependencies.modules.AppModule;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-28 12:58 PM
 */
@Singleton
@Component(
        modules = {AppModule.class}
)
public interface ApplicationComponent {

}
