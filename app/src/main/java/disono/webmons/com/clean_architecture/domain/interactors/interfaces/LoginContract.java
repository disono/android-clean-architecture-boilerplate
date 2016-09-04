package disono.webmons.com.clean_architecture.domain.interactors.interfaces;

import disono.webmons.com.clean_architecture.domain.interactors.base.Interactor;
import disono.webmons.com.clean_architecture.domain.models.MeModel;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-05-04 09:42 PM
 */
public interface LoginContract extends Interactor {
    // add interactor callback methods here
    interface Callback {
        void failed(String message);

        void success(MeModel meModel);
    }

    // add interactor methods here
}
