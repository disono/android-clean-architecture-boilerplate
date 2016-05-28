package disono.webmons.com.clean_architecture.domain.executor.imp;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import disono.webmons.com.clean_architecture.domain.executor.Executor;
import disono.webmons.com.clean_architecture.domain.interactors.base.AbstractInteractor;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-12 11:26 AM
 *
 * This singleton class will make sure that each interactors operation gets a background thread.
 */
public class ThreadExecutor implements Executor {
    // This is a singleton
    private static volatile ThreadExecutor threadExecutor;

    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingDeque<Runnable> WORK_QUEUE = new LinkedBlockingDeque<>();

    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadExecutor() {
        long keepAlive = KEEP_ALIVE_TIME;
        threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE);
    }

    @Override
    public void execute(final AbstractInteractor interactor) {
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                // run the main logic
                interactor.run();

                // mark it as finished
                interactor.onFinished();
            }
        });
    }

    /**
     * Returns a singleton instance of this executor. If the executor is not initialized then it initializes it and returns
     * the instance.
     */
    public static Executor getInstance() {
        if (threadExecutor == null) {
            threadExecutor = new ThreadExecutor();
        }

        return threadExecutor;
    }
}
