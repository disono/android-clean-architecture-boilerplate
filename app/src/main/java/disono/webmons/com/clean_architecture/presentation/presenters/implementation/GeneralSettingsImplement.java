package disono.webmons.com.clean_architecture.presentation.presenters.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.blueprint.GeneralSettingsInteractor;
import disono.webmons.com.clean_architecture.domain.model.MeModel;
import disono.webmons.com.clean_architecture.presentation.converters.Inputs;
import disono.webmons.com.clean_architecture.presentation.presenters.base.AbstractPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.blueprint.GeneralSettingsPresenter;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 5:39 PM
 */
public class GeneralSettingsImplement extends AbstractPresenter implements GeneralSettingsPresenter, GeneralSettingsInteractor.Callback {
    private Executor mExecutor;
    private MainThread mMainThread;
    private GeneralSettingsPresenter.View mView;
    private Inputs mInputs;

    private GeneralSettingsInteractor generalSettingsInteractor;
    private GeneralSettingsImplement generalSettingsImplement;

    public GeneralSettingsImplement(Executor executor, MainThread mainThread, View view, Inputs inputs) {
        super(executor, mainThread);
        generalSettingsImplement = this;

        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mView = view;
        this.mInputs = inputs;

        this.generalSettingsInteractor = new disono.webmons.com.clean_architecture.domain.interactors.implementation.GeneralSettingsImplement(
                this.mExecutor = executor,
                this.mMainThread = mainThread,
                this.generalSettingsImplement,
                this.mInputs = inputs
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
        this.mView.update();
        this.generalSettingsInteractor.execute();
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
