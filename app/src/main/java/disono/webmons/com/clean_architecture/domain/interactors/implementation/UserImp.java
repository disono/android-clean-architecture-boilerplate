package disono.webmons.com.clean_architecture.domain.interactors.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.blueprint.User;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.repository.UserRepository;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 *
 * This is an interactor boilerplate with a reference to a model userRepository.
 */
public class UserImp extends AbstractInteractor implements User {
    private User.Callback mCallback;
    private UserRepository mUserRepository;

    public UserImp(Executor executor, MainThread mainThread,
                   Callback callback, UserRepository userRepository) {
        super(executor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
    }

    @Override
    public void run() {
        // TODO implement this with your business logic
    }
}
