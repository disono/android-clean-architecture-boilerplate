package disono.webmons.com.clean_architecture.domain.interactors.implementation;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.executor.MainThread;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;
import disono.webmons.com.clean_architecture.domain.interactors.blueprint.SettingsInteractor;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 9/2/2016 12:34 PM
 */
public class SettingsImplement extends AbstractInteractor implements SettingsInteractor {
    public SettingsImplement(Executor threadExecutor, MainThread mainThread) {
        super(threadExecutor, mainThread);
    }

    @Override
    public void run() {

    }
}
