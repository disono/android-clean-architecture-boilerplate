package disono.webmons.com.clean_architecture.presentation.presenters.base;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;

/**
 * This is a base class for all presenters which are communicating with interactors. This base class will hold a
 * reference to the Executor and MainThread objects that are needed for running interactors in a background thread.
 */
public abstract class AbstractPresenter {
    protected Executor executor;
    protected MainThread mainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        executor = executor;
        mainThread = mainThread;
    }
}
