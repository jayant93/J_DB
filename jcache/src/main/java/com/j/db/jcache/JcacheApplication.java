package com.j.db.jcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This class implements a JCache-based application with an LRU cache mechanism.
 *
 * Features:
 * - Uses JCache API for cache management.
 * - Implements LRU (Least Recently Used) eviction policy.
 * - Provides APIs for cache CRUD operations.
 *
 * LRU Mechanism:
 * - Removes the least recently accessed entries when the cache exceeds its maximum size.
 * - Ensures efficient memory usage and optimal performance.
 *
 * Author: jayant kumar
 * Date: 18 Nov 2024
 */
@SpringBootApplication
@EnableScheduling
public class JcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(JcacheApplication.class, args);
	}

}
