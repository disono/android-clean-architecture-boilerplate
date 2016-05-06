package disono.webmons.com.clean_architecture.domain.interactors;

import disono.webmons.com.clean_architecture.domain.interactors.base.Interactor;

/**
 * Author: Archie, Disono (disono.apd@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-04 09:42 PM
 */
public interface AuthInteractor extends Interactor {
    interface Callback {
        boolean onAttempt(String email, String password);

        boolean onLogin(int id);

        boolean onCheck();

        boolean onLogout();

        String onUser();

        void onFailed(String message);
    }
}
