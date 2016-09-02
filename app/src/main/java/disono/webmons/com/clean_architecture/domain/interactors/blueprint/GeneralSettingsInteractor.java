package disono.webmons.com.clean_architecture.domain.interactors.blueprint;

import disono.webmons.com.clean_architecture.domain.interactors.base.Interactor;
import disono.webmons.com.clean_architecture.domain.model.MeModel;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 5:35 PM
 */
public interface GeneralSettingsInteractor extends Interactor {
    // add interactor callback methods here
    interface Callback {
        void failed(String message);

        void success(MeModel meModel);
    }

    // add interactor methods here
}
