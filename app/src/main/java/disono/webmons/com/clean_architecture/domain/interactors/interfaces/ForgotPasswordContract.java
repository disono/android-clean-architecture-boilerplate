package disono.webmons.com.clean_architecture.domain.interactors.interfaces;

import disono.webmons.com.clean_architecture.domain.interactors.base.Interactor;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/4/2016 10:56 AM
 */
public interface ForgotPasswordContract extends Interactor {
    // add interactor callback methods here
    interface Callback {
        void failed(String message);

        void success();
    }

    // add interactor methods here
}