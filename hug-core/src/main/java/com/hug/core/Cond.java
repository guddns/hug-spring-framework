package com.hug.core;

import static java.lang.String.format;

/**
 * <p>Description: </p>
 *
 * @author Kim Hyeong Un
 * @since 2014. 2. 24.
 */
public final class Cond
{
	public static <T> T firstNotNull(T first, T second) {
		return first != null ? first : checkNotNull(second);
	}

	public static void checkArgument(boolean expression) {
		if (!expression) {
			throw new IllegalArgumentException();
		}
	}

	public static void checkArgument(boolean expression, Object errorMessage) {
		if (!expression) {
			throw new IllegalArgumentException(String.valueOf(errorMessage));
		}
	}

	public static void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
		if (!expression) {
			throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
		}
	}

	public static void checkState(boolean expression) {
		if (!expression) {
			throw new IllegalStateException();
		}
	}

	public static void checkState(boolean expression, Object errorMessage) {
		if (!expression) {
			throw new IllegalStateException(String.valueOf(errorMessage));
		}
	}

	public static void checkState(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
		if (!expression) {
			throw new IllegalStateException(format(errorMessageTemplate, errorMessageArgs));
		}
	}

	public static <T> T checkNotNull(T reference) {
		if (reference == null) {
			throw new NullPointerException();
		}
		return reference;
	}

	public static <T> T checkNotNull(T reference, Object errorMessage) {
		if (reference == null) {
			throw new NullPointerException(String.valueOf(errorMessage));
		}
		return reference;
	}

	public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object... errorMessageArgs) {
		if (reference == null) {
			throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs));
		}
		return reference;
	}

	public static boolean isNullOrEmpty(String string) {
		return string == null || string.length() == 0;
	}
}
