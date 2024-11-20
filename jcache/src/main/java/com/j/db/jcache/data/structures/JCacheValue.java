package com.j.db.jcache.data.structures;

import java.time.Instant;
import java.util.Objects;

/**
 * JCacheValue is a generic class representing a single entry in the key-value store.
 *
 * @param <K> The type of the value associated with the key
 */
public class JCacheValue<K> {
    // Time to Live in seconds
    private Integer ttl;

    // Time of object creation
    private Instant timeOfCreation;

    // Key associated with the value
    private String key;

    // Value associated with the key
    private K valueAssociatedWithKey;

    // No-argument constructor
    public JCacheValue() {
    }

    // Parameterized constructor
    public JCacheValue(Integer ttl, Instant timeOfCreation, String key, K valueAssociatedWithKey) {
        this.ttl = ttl;
        this.timeOfCreation = timeOfCreation;
        this.key = key;
        this.valueAssociatedWithKey = valueAssociatedWithKey;
    }

    // Getters and Setters
    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        if (ttl != null && ttl < 0) {
            throw new IllegalArgumentException("TTL must be non-negative.");
        }
        this.ttl = ttl;
    }

    public Instant getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Instant timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public K getValueAssociatedWithKey() {
        return valueAssociatedWithKey;
    }

    public void setValueAssociatedWithKey(K valueAssociatedWithKey) {
        this.valueAssociatedWithKey = valueAssociatedWithKey;
    }

    @Override
    public String toString() {
        return "JCacheValue{" +
                "ttl=" + ttl +
                ", timeOfCreation=" + timeOfCreation +
                ", key='" + key + '\'' +
                ", valueAssociatedWithKey=" + valueAssociatedWithKey +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JCacheValue<?> that = (JCacheValue<?>) o;
        return Objects.equals(ttl, that.ttl) &&
                Objects.equals(timeOfCreation, that.timeOfCreation) &&
                Objects.equals(key, that.key) &&
                Objects.equals(valueAssociatedWithKey, that.valueAssociatedWithKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ttl, timeOfCreation, key, valueAssociatedWithKey);
    }
}
