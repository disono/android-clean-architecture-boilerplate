package disono.webmons.com.clean_architecture.presentation.presenters.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.UserInteractor;
import disono.webmons.com.clean_architecture.presentation.presenters.MainPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.base.AbstractPresenter;

public class MainPresenterImplementation extends AbstractPresenter implements MainPresenter, UserInteractor.Callback {
    private MainPresenter.View view;

    public MainPresenterImplementation(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);
        view = view;
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
