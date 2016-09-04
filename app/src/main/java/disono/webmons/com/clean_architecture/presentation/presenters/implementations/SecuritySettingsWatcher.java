package disono.webmons.com.clean_architecture.presentation.presenters.implementations;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.implementations.SecuritySettingsImplement;
import disono.webmons.com.clean_architecture.domain.interactors.interfaces.SecuritySettingsContract;
import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.presentation.presenters.base.AbstractPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.interfaces.SecuritySettingsPresenter;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/3/2016 6:46 PM
 */
public class SecuritySettingsWatcher extends AbstractPresenter implements SecuritySettingsPresenter, SecuritySettingsContract.Callback {
    private Executor mExecutor;
    private MainThread mMainThread;
    private SecuritySettingsPresenter.View mView;
    private Inputs mInputs;

    private SecuritySettingsContract securitySettingsContract;
    private SecuritySettingsWatcher securitySettingsWatcher;

    public SecuritySettingsWatcher(Executor executor, MainThread mainThread, View view, Inputs inputs) {
        super(executor, mainThread);
        securitySettingsWatcher = this;

        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mView = view;
        this.mInputs = inputs;

        this.securitySettingsContract = new SecuritySettingsImplement(
                this.mExecutor,
                this.mMainThread,
                this.securitySettingsWatcher,
                this.mInputs
        );

        this.mView.listeners();
        this.mView.setValues();
    }

    @Override
    public void submit() {
        this.mView.showProgress();
        this.mView.submit();
        this.securitySettingsContract.execute();
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
        this.mView.success();
    }
}
