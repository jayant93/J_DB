package com.j.db.jcache.data.structures;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JCache represents the data structure of our key-value store
 */
@Component
public class JCache {

    @Value("${cache.key-value-store.limit:400}")
    private int capacity;

    // KeyValue store with final capacity, using LinkedHashMap with access order
    private final Map<String, JCacheValue> keyValueStore;

    // Constructor initializing the store
    public JCache() {
        // LinkedHashMap with access order set to true for adding accessed element at the top
        keyValueStore = new LinkedHashMap<>(capacity, 0.75f, true) {
            // Override removeEldestEntry to implement LRU eviction
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, JCacheValue> eldest) {
                return size() > capacity;
            }
        };
    }

    /**
     * Adding key and value to the key-value store
     *
     * @param key   the key
     * @param value the value
     */
    public void addElementToCache(String key, JCacheValue value) {
        keyValueStore.put(key, value);
    }

    /**
     * Removes an element from the cache
     *
     * @param key the key to remove
     */
    public void removeElementFromCache(String key) {
        keyValueStore.remove(key);
    }

    /**
     * Retrieves an element by key
     *
     * @param key the key
     * @return the cached value
     */
    public JCacheValue getElementByKey(String key) {
        return keyValueStore.get(key);
    }

    /**
     * Evicts expired entries based on TTL and time of creation
     */
    public void evictExpiredEntries() {
        Instant now = Instant.now();
        keyValueStore.entrySet().removeIf(entry -> isExpired(entry.getValue(), now));
    }

    /**
     * Checks if the cache entry has expired based on TTL and time of creation
     *
     * @param cacheValue the cache value to check
     * @param currentTime the current time to compare expiration
     * @return true if the cache entry has expired
     */
    private boolean isExpired(JCacheValue cacheValue, Instant currentTime) {
        Instant expirationTime = cacheValue.getTimeOfCreation().plusSeconds(cacheValue.getTtl());
        return currentTime.isAfter(expirationTime);
    }

    /**
     * Get the current size of the cache
     *
     * @return the size of the cache
     */
    public int size() {
        return keyValueStore.size();
    }

    public Map<String, JCacheValue> getKeyStore() {
        return keyValueStore;
    }

    @Override
    public String toString() {
        return "JCache{" +
                "capacity=" + capacity +
                ", keyValueStore=" + keyValueStore +
                '}';
    }
}
