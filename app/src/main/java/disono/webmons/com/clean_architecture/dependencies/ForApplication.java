package disono.webmons.com.clean_architecture.dependencies;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-28 01:06 PM
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {

}
