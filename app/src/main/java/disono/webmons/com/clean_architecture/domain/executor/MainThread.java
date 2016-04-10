package disono.webmons.com.clean_architecture.domain.executor;

public interface MainThread {
    /**
     * Make runnable operation run in the main thread.
     *
     * @param runnable The runnable to run.
     */
    void post(final Runnable runnable);
}
