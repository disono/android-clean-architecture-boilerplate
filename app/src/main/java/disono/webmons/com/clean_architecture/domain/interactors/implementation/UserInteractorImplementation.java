package disono.webmons.com.clean_architecture.domain.interactors.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.UserInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.repository.Repository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 */
public class UserInteractorImplementation extends AbstractInteractor implements UserInteractor {
    private UserInteractor.Callback callback;
    private Repository repository;

    public UserInteractorImplementation(Executor executor, MainThread mainThread,
                                        Callback callback, Repository repository) {
        super(executor, mainThread);
        callback = callback;
        repository = repository;
    }

    @Override
    public void run() {
        // TODO implement this with your business logic
    }
}
