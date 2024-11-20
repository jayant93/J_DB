package com.j.db.jcache.restcontroller;

import com.j.db.jcache.data.structures.JCache;
import com.j.db.jcache.data.structures.JCacheValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jayantkumar
 * Controller for caching operations
 * SET,GET ,EXPIRE ,DELETE
 *
 */
@RestController
@RequestMapping("/jcache")
public class ChacheController {

    private final JCache jCache;

    @Autowired
    public ChacheController(JCache jCache) {
        this.jCache = jCache;
    }

    @PostMapping
    public void setCache(@RequestBody JCacheValue jCacheValue){
        jCacheValue.setTimeOfCreation(Instant.now());
        jCache.addElementToCache(jCacheValue.getKey(),jCacheValue);
    }

    @GetMapping
    public JCacheValue getCachedValueByKey(@RequestParam String key){
        return jCache.getElementByKey(key);
    }

    @GetMapping("/all")
    public List<JCacheValue> getAllCachedValues(){
        List<JCacheValue> jCacheValueArrayList = jCache.getKeyStore().values().stream().toList();
        return jCacheValueArrayList;
    }
}
