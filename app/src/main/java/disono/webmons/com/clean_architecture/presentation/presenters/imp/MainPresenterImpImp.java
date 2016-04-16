package disono.webmons.com.clean_architecture.presentation.presenters.imp;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.UserInteractor;
import disono.webmons.com.clean_architecture.presentation.presenters.MainPresenter;
import disono.webmons.com.clean_architecture.presentation.presenters.base.AbstractPresenter;

public class MainPresenterImpImp extends AbstractPresenter implements MainPresenter, UserInteractor.Callback {
    private MainPresenter.View mview;

    public MainPresenterImpImp(Executor executor,
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
