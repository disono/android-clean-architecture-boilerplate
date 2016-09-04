package disono.webmons.com.clean_architecture.presentation.presenters.implementations;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.implementations.ForgotPasswordImplement;
import disono.webmons.com.clean_architecture.domain.interactors.interfaces.ForgotPasswordContract;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.presentation.presenters.base.AbstractPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.interfaces.ForgotPasswordPresenter;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/4/2016 11:02 AM
 */
public class ForgotPasswordWatcher extends AbstractPresenter implements ForgotPasswordPresenter, ForgotPasswordContract.Callback {
    private Executor mExecutor;
    private MainThread mMainThread;
    private ForgotPasswordPresenter.View mView;
    private Inputs mInputs;

    private ForgotPasswordContract forgotPasswordContract;
    private ForgotPasswordWatcher forgotPasswordWatcher;

    public ForgotPasswordWatcher(Executor executor, MainThread mainThread, View view, Inputs inputs) {
        super(executor, mainThread);
        forgotPasswordWatcher = this;

        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mView = view;
        this.mInputs = inputs;

        this.forgotPasswordContract = new ForgotPasswordImplement(
                this.mExecutor,
                this.mMainThread,
                this.forgotPasswordWatcher,
                this.mInputs
        );

        this.mView.listeners();
    }

    @Override
    public void submit() {
        this.mView.showProgress();
        this.mView.submit();
        this.forgotPasswordContract.execute();
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
        this.mView.hideProgress();
        this.mView.showError(message);
    }

    @Override
    public void failed(String message) {
        this.mView.hideProgress();
        this.mView.showError(message);
    }

    @Override
    public void success() {
        this.mView.hideProgress();
        this.mView.success();
    }
}
