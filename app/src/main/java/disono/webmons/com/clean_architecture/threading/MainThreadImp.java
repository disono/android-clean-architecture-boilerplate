package disono.webmons.com.clean_architecture.threading;

import android.os.Looper;
import android.os.Handler;

import disono.webmons.com.clean_architecture.domain.executor.MainThread;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Created at: 2016-04-12 11:26 AM
 *
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
public class MainThreadImp implements MainThread {
    private static MainThread mainThread;

    private Handler handler;

    private MainThreadImp() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }

    public static MainThread getInstance() {
        if (mainThread == null) {
            mainThread = new MainThreadImp();
        }

        return mainThread;
    }
}
