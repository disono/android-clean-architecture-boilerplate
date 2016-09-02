package disono.webmons.com.clean_architecture.presentation.presenters.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.blueprint.LoginInteractor;
import disono.webmons.com.clean_architecture.domain.model.MeModel;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.presentation.presenters.base.AbstractPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.blueprint.LoginPresenter;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/22/2016 10:50 PM
 */
public class LoginImplement extends AbstractPresenter implements LoginPresenter, LoginInteractor.Callback {
    private Executor mExecutor;
    private MainThread mMainThread;
    private LoginPresenter.View mView;
    private Inputs mInputs;

    private LoginImplement loginImplement;
    private LoginInteractor authenticationInteractor;

    public LoginImplement(Executor executor, MainThread mainThread, View view, Inputs inputs) {
        super(executor, mainThread);
        this.loginImplement = this;

        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mView = view;
        this.mInputs = inputs;

        // run thread
        this.authenticationInteractor = new disono.webmons.com.clean_architecture.domain.interactors.implementation.LoginImplement(
                this.mExecutor,
                this.mMainThread,
                this.loginImplement,
                this.mInputs
        );

        // listeners for UI
        this.mView.listeners();
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

    @Override
    public void submit() {
        this.mView.showProgress();
        this.mView.forms();
        this.authenticationInteractor.execute();
    }

    @Override
    public void register() {
        this.mView.register();
    }

    @Override
    public void failed(String message) {
        this.mView.hideProgress();
        this.mView.showError(message);
    }

    @Override
    public void success(MeModel meModel) {
        this.mView.hideProgress();
        this.mView.dashboard(meModel);
    }
}
