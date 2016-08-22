package disono.webmons.com.clean_architecture.presentation.presenters.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.blueprint.User;
import disono.webmons.com.clean_architecture.presentation.presenters.blueprint.MainPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.base.AbstractPresenter;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 */
public class MainPresenterImp extends AbstractPresenter implements MainPresenter, User.Callback {
    private MainPresenter.View mview;

    public MainPresenterImp(Executor executor,
                            MainThread mainThread,
                            View view) {
        super(executor, mainThread);
        mview = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }
}
