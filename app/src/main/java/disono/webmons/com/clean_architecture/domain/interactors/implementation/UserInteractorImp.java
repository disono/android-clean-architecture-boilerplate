package disono.webmons.com.clean_architecture.domain.interactors.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.UserInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.repository.Repository;

/**
 * Author: Archie, Disono (disono.apd@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-12 11:26 AM
 *
 * This is an interactor boilerplate with a reference to a model repository.
 */
public class UserInteractorImp extends AbstractInteractor implements UserInteractor {
    private UserInteractor.Callback callback;
    private Repository repository;

    public UserInteractorImp(Executor executor, MainThread mainThread,
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
