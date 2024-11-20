package com.j.db.jcache.restcontroller;

import com.j.db.jcache.data.structures.JCache;
import com.j.db.jcache.data.structures.JCacheValue;
import com.j.db.jcache.exceptions.CacheEmptyException;
import com.j.db.jcache.exceptions.InternalServerErrorException;
import com.j.db.jcache.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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

    AtomicInteger count = new AtomicInteger(0);

    @Autowired
    public ChacheController(JCache jCache) {
        this.jCache = jCache;
    }

    @PostMapping(consumes = "application/json")
    public void setCache(@RequestBody JCacheValue jCacheValue){
        System.out.println("Reached server adding cache : "+count.incrementAndGet());
        jCacheValue.setTimeOfCreation(Instant.now());
        try {
            jCache.addElementToCache(jCacheValue.getKey(), jCacheValue);
        }catch (Exception e){
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<JCacheValue> getCachedValueByKey(@RequestParam String key){

        if(!jCache.getKeyStore().containsKey(key))
             throw new ResourceNotFoundException("Element with Key :"+key +" is not present in JCache" +
                     " ,Either it was removed due to Expired TTL or according to our eviction policy" +
                     " its not being used very frequently OR it was never added to the cache");

        return new ResponseEntity<>(jCache.getElementByKey(key),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<JCacheValue>> getAllCachedValues(){
        List<JCacheValue> jCacheValueArrayList = jCache.getKeyStore().values().stream().toList();
        if(jCacheValueArrayList.isEmpty()){
            throw new CacheEmptyException("Cache is Emtpy Right Now add some elements and try again");
        }
        return new ResponseEntity<>(jCacheValueArrayList,HttpStatus.OK);
    }
}
