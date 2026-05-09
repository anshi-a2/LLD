package com.rate.limiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        TokenBucketRateLimiter tokenBucketRateLimiter = TokenBucketRateLimiter.getInstance();
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
        System.out.println(tokenBucketRateLimiter.allowRequest("A"));
	}

}
