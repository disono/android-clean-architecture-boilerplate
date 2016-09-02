package disono.webmons.com.clean_architecture.domain.interactors.base;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 * <p>
 * This is the main interface of an interactor. Each interactor serves a specific use case.
 */
public interface Interactor {
    /**
     * This is the main method that starts an interactor. It will make sure that the interactor operation is done on a
     * background thread.
     */
    void execute();

    /**
     * Check if the thread is running
     *
     * @return
     */
    boolean isRunning();

    /**
     * Cancel the thread
     */
    void cancel();
}
