package com.rate.limiter;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter {

    /*
        Example:
        CAPACITY = 10
        REFILL_RATE = 2 tokens/min

        Each user gets:
        - max 10 requests burst
        - 2 new requests per min
     */

    private  final int CAPACITY;
    private final int REFILL_RATE;
    private static TokenBucketRateLimiter instance;

    /*
        userId -> Bucket
     */
    private final ConcurrentHashMap<String, Bucket> buckets;

    private TokenBucketRateLimiter() {
        this.CAPACITY =10;
        this.REFILL_RATE = 2;
        this.buckets = new ConcurrentHashMap<>();

    }

    public static synchronized TokenBucketRateLimiter getInstance() {
        if (instance==null){
            instance = new TokenBucketRateLimiter();
        }
        return instance;
    }


    /*
        Main API
     */
    public boolean allowRequest(String userId) {

        // create bucket if absent
        Bucket bucket = buckets.computeIfAbsent(
                userId,
                key -> new Bucket(
                        CAPACITY,
                        System.currentTimeMillis()
                )
        );

        /*
            Synchronize only per user bucket
            NOT entire rate limiter
         */
        synchronized (bucket) {

            refillTokens(bucket);

            if (bucket.tokens > 0) {
                bucket.tokens--;
                return true;
            }

            return false;
        }
    }


    /*
        Refill logic
     */
    private void refillTokens(Bucket bucket) {

        long now = System.currentTimeMillis();

        long elapsedTime =
                now - bucket.lastRefillTimestamp;

        /*
            elapsed minute
         */
        long elapsedSeconds =
                elapsedTime / 60000;

        if (elapsedSeconds <= 0) {
            return;
        }

        /*
            tokens to add
         */
        int tokensToAdd =
                (int) (elapsedSeconds * REFILL_RATE);

        /*
            add tokens but never exceed capacity
         */
        bucket.tokens = Math.min(
                CAPACITY,
                bucket.tokens + tokensToAdd
        );

        /*
            update refill timestamp
         */
        bucket.lastRefillTimestamp = now;
    }


    /*
        Per-user bucket
     */
    private static class Bucket {

        private int tokens;

        private long lastRefillTimestamp;

        public Bucket(int tokens,
                      long lastRefillTimestamp) {

            this.tokens = tokens;
            this.lastRefillTimestamp =
                    lastRefillTimestamp;
        }
    }


}

