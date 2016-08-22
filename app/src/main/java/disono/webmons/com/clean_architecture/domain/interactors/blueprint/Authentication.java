package disono.webmons.com.clean_architecture.domain.interactors.blueprint;

import disono.webmons.com.clean_architecture.domain.interactors.base.Interactor;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-04 09:42 PM
 */
public interface Authentication extends Interactor {
    interface Callback {
        boolean onAttempt(String email, String password);

        boolean onLogin(int id);

        boolean onLogout();

        String onUser();

        boolean onCheck();

        void onFailed(String message);
    }
}
