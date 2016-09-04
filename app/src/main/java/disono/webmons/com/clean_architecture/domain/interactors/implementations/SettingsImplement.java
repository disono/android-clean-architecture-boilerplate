package disono.webmons.com.clean_architecture.domain.interactors.implementations;

import disono.webmons.com.clean_architecture.domain.executor.interfaces.Executor;
import disono.webmons.com.clean_architecture.domain.executor.interfaces.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.interfaces.SettingsContract;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 12:34 PM
 */
public class SettingsImplement extends AbstractInteractor implements SettingsContract {
    public SettingsImplement(Executor threadExecutor, MainThread mainThread) {
        super(threadExecutor, mainThread);
    }

    @Override
    public void run() {

    }
}
