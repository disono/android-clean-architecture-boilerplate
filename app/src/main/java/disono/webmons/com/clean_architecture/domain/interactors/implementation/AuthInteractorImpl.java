package disono.webmons.com.clean_architecture.domain.interactors.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.AuthInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.repository.AuthRepository;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-05-04 09:48 PM
 */
public class AuthInteractorImpl extends AbstractInteractor implements AuthInteractor {
    private Executor mExecutor;
    private MainThread mMainThread;
    private AuthInteractor.Callback mCallback;
    private AuthRepository mAuthRepository;

    public AuthInteractorImpl(Executor executor, MainThread mainThread,
                              Callback callback, AuthRepository authRepository) {
        super(executor, mainThread);

        mExecutor = executor;
        mMainThread = mainThread;
        mCallback = callback;
        mAuthRepository = authRepository;
    }

    @Override
    public void run() {
        final boolean check = mAuthRepository.check();

        if (check) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onCheck();
                }
            });

            return;
        }

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onFailed("Invalid email or password.");
            }
        });
    }
}
