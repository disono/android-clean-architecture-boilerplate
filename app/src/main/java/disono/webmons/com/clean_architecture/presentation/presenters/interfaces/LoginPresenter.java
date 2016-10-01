package disono.webmons.com.clean_architecture.presentation.presenters.interfaces;

import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.presentation.presenters.base.BasePresenter;
import disono.webmons.com.clean_architecture.presentation.ui.BaseView;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/22/2016 10:49 PM
 */
public interface LoginPresenter extends BasePresenter {
    // add your view methods
    interface View extends BaseView {
        void mainActivity(MeModel meModel);

        void registerActivity();

        void forgotActivity();

        void listeners();

        void formInputs();
    }

    // add your watcher methods
    void submit();

    void registerActivity();

    void forgotActivity();
}