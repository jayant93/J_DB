package com.j.db.jcache.services.daemon;

import com.j.db.jcache.data.structures.JCache;
import com.j.db.jcache.data.structures.JCacheValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

/**
 * @author jayantkumar
 * Scheduler to check the Cache Data structure
 * to remove the elements after TTL expires
 */

@Component
public class ExpiryScheduler {

    private final JCache jCache;

    @Autowired
    public ExpiryScheduler(JCache jCache) {
        this.jCache = jCache;
    }

    @Scheduled(fixedRate = 5000)
    public void removeElementFromCache(){
     jCache.evictExpiredEntries();
    }

}
