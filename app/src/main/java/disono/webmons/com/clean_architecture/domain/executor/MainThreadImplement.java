package disono.webmons.com.clean_architecture.domain.executor;

import android.os.Handler;
import android.os.Looper;

/**
 * Author: Archie, Disono (disono.apd@gmail.com / webmonsph@gmail.com)
 * Website: www.webmons.com
 * License: Apache 2.0
 * Copyright 2016 Webmons Development Studio.
 * Created at: 2016-04-12 11:26 AM
 * <p>
 * This class makes sure that the runnable we provide will be run on the main UI thread.
 */
public class MainThreadImplement implements MainThread {
    private static MainThread mainThread;

    private Handler handler;

    private MainThreadImplement() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }

    public static MainThread getInstance() {
        if (mainThread == null) {
            mainThread = new MainThreadImplement();
        }

        return mainThread;
    }
}
