package disono.webmons.com.clean_architecture.presentation.presenters.implementations;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.implementations.GeneralSettingsImplement;
import disono.webmons.com.clean_architecture.domain.interactors.interfaces.GeneralSettingsContract;
import disono.webmons.com.clean_architecture.domain.models.MeModel;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.presentation.presenters.base.AbstractPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.interfaces.GeneralSettingsPresenter;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 5:39 PM
 */
public class GeneralSettingsWatcher extends AbstractPresenter implements GeneralSettingsPresenter, GeneralSettingsContract.Callback {
    private Executor mExecutor;
    private MainThread mMainThread;
    private GeneralSettingsPresenter.View mView;
    private Inputs mInputs;

    private GeneralSettingsContract generalSettingsContract;
    private GeneralSettingsWatcher generalSettingsWatcher;

    public GeneralSettingsWatcher(Executor executor, MainThread mainThread, View view, Inputs inputs) {
        super(executor, mainThread);
        generalSettingsWatcher = this;

        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mView = view;
        this.mInputs = inputs;

        this.generalSettingsContract = new GeneralSettingsImplement(
                this.mExecutor,
                this.mMainThread,
                this.generalSettingsWatcher,
                this.mInputs
        );

        this.mView.listeners();
        this.mView.setValues();
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
    public void submit() {
        this.mView.showProgress();
        this.mView.submit();
        this.generalSettingsContract.execute();
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
