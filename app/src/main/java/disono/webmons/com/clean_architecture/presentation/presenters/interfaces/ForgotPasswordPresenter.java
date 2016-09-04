package disono.webmons.com.clean_architecture.presentation.presenters.interfaces;

import disono.webmons.com.clean_architecture.presentation.presenters.base.BasePresenter;
import disono.webmons.com.clean_architecture.presentation.ui.BaseView;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/4/2016 10:58 AM
 */
public interface ForgotPasswordPresenter extends BasePresenter {
    // add your view methods
    interface View extends BaseView {
        void listeners();

        void success();

        void submit();
    }

    // add your presenter methods
    void submit();
}