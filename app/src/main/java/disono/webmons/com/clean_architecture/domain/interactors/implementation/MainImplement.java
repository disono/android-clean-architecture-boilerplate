package disono.webmons.com.clean_architecture.domain.interactors.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.blueprint.MainInteractor;
import disono.webmons.com.clean_architecture.domain.repository.MeRepository;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 * <p>
 * This is an interactor boilerplate with a reference to a model userRepository.
 */
public class MainImplement extends AbstractInteractor implements MainInteractor {
    private MainInteractor.Callback mCallback;
    private MeRepository mMeRepository;

    public MainImplement(Executor executor, MainThread mainThread,
                         Callback callback, MeRepository meRepository) {
        super(executor, mainThread);

        mCallback = callback;
        mMeRepository = meRepository;
    }

    @Override
    public void run() {
        // TODO implement this with your business logic
    }
}
