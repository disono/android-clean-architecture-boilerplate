package disono.webmons.com.clean_architecture.domain.interactors.implementations;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.interfaces.MainContract;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 * <p>
 * This is an interactor boilerplate with a reference to a model userRepository.
 */
public class MainImplement extends AbstractInteractor implements MainContract {
    private MainContract.Callback mCallback;

    public MainImplement(Executor executor, MainThread mainThread,
                         Callback callback) {
        super(executor, mainThread);

        mCallback = callback;
    }

    @Override
    public void run() {
        // TODO implement this with your business logic
    }
}
