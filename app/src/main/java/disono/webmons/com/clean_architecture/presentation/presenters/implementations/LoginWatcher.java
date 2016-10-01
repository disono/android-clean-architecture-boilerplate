package disono.webmons.com.clean_architecture.presentation.presenters.implementations;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.interfaces.LoginContract;
import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.presentation.presenters.base.AbstractPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.interfaces.LoginPresenter;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 8/22/2016 10:50 PM
 */
public class LoginWatcher extends AbstractPresenter implements LoginPresenter, LoginContract.Callback {
    private Executor mExecutor;
    private MainThread mMainThread;
    private LoginPresenter.View mView;
    private Inputs mInputs;

    private LoginWatcher loginImplement;
    private LoginContract authenticationInteractor;

    public LoginWatcher(Executor executor, MainThread mainThread, View view, Inputs inputs) {
        super(executor, mainThread);
        this.loginImplement = this;

        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mView = view;
        this.mInputs = inputs;

        // run thread
        this.authenticationInteractor = new disono.webmons.com.clean_architecture.domain.interactors.implementations.LoginImplement(
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
        this.mView.formInputs();
        this.authenticationInteractor.execute();
    }

    @Override
    public void registerActivity() {
        this.mView.registerActivity();
    }

    @Override
    public void forgotActivity() {
        this.mView.forgotActivity();
    }

    @Override
    public void failed(String message) {
        this.mView.hideProgress();
        this.mView.showError(message);
    }

    @Override
    public void success(MeModel meModel) {
        this.mView.hideProgress();
        this.mView.mainActivity(meModel);
    }
}
