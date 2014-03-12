package com.hug.core.async;

import java.util.concurrent.*;

/**
 * <p>Description: </p>
 *
 * @author 김형운 (guddns@gmail.com)
 * @since 2014. 3. 10.
 */
public class AsyncUtil
{
	/**
	 * ThreadPool 을 생성한다.
	 */
	private static final ExecutorService threadPool = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors() * 2);


	/**
	 * Callable 을 수행하고 FutureTask 를 반환한다.
	 * {@link java.util.concurrent.FutureTask#run()} 을 실행시켜야 한다.
	 */
	public static <T> FutureTask<T> newTask(final Callable<T> callable) {
		return new FutureTask<>(callable);
	}

	/**
	 * Runnable 을 수행하고 FutureTask 를 반환한다.
	 * {@link java.util.concurrent.FutureTask#run()} 을 실행시켜야 한다.
	 */
	public static <T> FutureTask<T> newTask(final Runnable runnable, final T result) {
		return new FutureTask<>(runnable, result);
	}

	/**
	 * Runnable 을 수행하고 FutureTask 를 반환한다.
	 * {@link java.util.concurrent.FutureTask#run()} 을 실행시켜야 한다.
	 */
	public static <T> FutureTask<T> newTask(final Runnable runnable) {
		return new FutureTask<>(runnable, null);
	}

	/**
	 * Callable 을 자동으로 시작한다.
	 */
	public static <T> Future<T> startTask(final Callable<T> callable) {
		return threadPool.submit(callable);
	}

	/**
	 * Runnable 을 자동으로 시작한다.
	 */
	public static <T> Future<T> startTask(final Runnable runnable, final T result) {
		return threadPool.submit(runnable, result);
	}
}
