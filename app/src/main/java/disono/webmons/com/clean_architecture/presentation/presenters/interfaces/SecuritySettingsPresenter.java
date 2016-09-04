package disono.webmons.com.clean_architecture.presentation.presenters.interfaces;

import disono.webmons.com.clean_architecture.presentation.presenters.base.BasePresenter;
import disono.webmons.com.clean_architecture.presentation.ui.BaseView;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/3/2016 6:48 PM
 */
public interface SecuritySettingsPresenter extends BasePresenter {
    interface View extends BaseView {
        void listeners();

        void setValues();

        void success();

        void submit();
    }

    void submit();
}
