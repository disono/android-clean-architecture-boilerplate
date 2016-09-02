package disono.webmons.com.clean_architecture.presentation.presenters.blueprint;

import disono.webmons.com.clean_architecture.presentation.presenters.base.BasePresenter;
import disono.webmons.com.clean_architecture.presentation.ui.BaseView;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 5:38 PM
 */
public interface GeneralSettingsPresenter extends BasePresenter {
    // add your view methods
    interface View extends BaseView {
        void listeners();

        void update();

        void setValues();

        void success();
    }

    // add your presenter methods
    void submit();
}
