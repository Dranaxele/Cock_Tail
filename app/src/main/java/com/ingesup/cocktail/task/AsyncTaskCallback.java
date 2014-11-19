package com.ingesup.cocktail.task;

/**
 * Created by lopes_f on 11/19/2014.
 * <florian.lopes@outlook.com>
 */
public interface AsyncTaskCallback<T> {

	public void deliverResult(T result);
}
