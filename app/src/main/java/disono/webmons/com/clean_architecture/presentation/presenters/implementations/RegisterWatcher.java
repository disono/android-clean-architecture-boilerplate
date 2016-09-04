package disono.webmons.com.clean_architecture.presentation.presenters.implementations;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.implementations.RegisterImplement;
import disono.webmons.com.clean_architecture.domain.interactors.interfaces.RegisterContract;
import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.presentation.presenters.base.AbstractPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.interfaces.RegisterPresenter;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/3/2016 10:54 PM
 */
public class RegisterWatcher extends AbstractPresenter implements RegisterPresenter, RegisterContract.Callback {
    private Executor mExecutor;
    private MainThread mMainThread;
    private RegisterPresenter.View mView;
    private Inputs mInputs;

    private RegisterContract registerContract;
    private RegisterWatcher registerWatcher;

    public RegisterWatcher(Executor executor, MainThread mainThread, View view, Inputs inputs) {
        super(executor, mainThread);
        registerWatcher = this;

        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mView = view;
        this.mInputs = inputs;

        this.registerContract = new RegisterImplement(
                this.mExecutor,
                this.mMainThread,
                this.registerWatcher,
                this.mInputs
        );

        this.mView.listeners();
    }

    @Override
    public void submit() {
        this.mView.showProgress();
        this.mView.submit();
        this.registerContract.execute();
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
    public void success(MeModel meModel) {
        this.mView.hideProgress();
        this.mView.success(meModel);
    }
}
