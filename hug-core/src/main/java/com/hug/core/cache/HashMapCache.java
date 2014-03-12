package com.hug.core.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author 김형운 (guddns@gmail.com)
 * @since 2014. 3. 10.
 */
public class HashMapCache
{
	private final Cache<String, Object> cache;
	private long expire;


	@SuppressWarnings("unchecked")
	public HashMapCache(long expire) {
		CacheBuilder builder = CacheBuilder.newBuilder().concurrencyLevel(4);
		if (expire > 0) {
			this.expire = expire;
			builder.expireAfterAccess(expire, TimeUnit.MINUTES);
		}

		cache = builder.build();
	}

	public Map<String, Object> getCache() {
		return this.cache.asMap();
	}

	public Object get(final String key) {
		return cache.getIfPresent(key);
	}

	public void set(final String key, final Object value) {
		cache.put(key, value);
	}

	public void remove(final String key) {
		cache.invalidate(key);
	}

	public void removeAll(final String... keys) {
		cache.invalidateAll(Arrays.asList(keys));
	}

	public void removeAll(final Iterable<String> keys) {
		cache.invalidateAll(keys);
	}

	public boolean exists(final String key) {
		return cache.getIfPresent(key) != null;
	}

	public synchronized void clear() {
		cache.cleanUp();
	}
}
