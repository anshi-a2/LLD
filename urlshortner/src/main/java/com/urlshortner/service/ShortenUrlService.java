package com.urlshortner.service;

import com.urlshortner.dto.UrlMapping;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ShortenUrlService {

    private static final String BASE_URL = "http://localhost:8080/";

    private final ConcurrentHashMap<String, UrlMapping> urlMapping = new ConcurrentHashMap<>();

    private final AtomicLong counter = new AtomicLong(1000);

    public String shortUrl(String longUrl) {
        Long id =   counter.incrementAndGet();
        String code = generateCode(id);
        UrlMapping urlMap = new UrlMapping(code, longUrl);
        urlMapping.put(code, urlMap);
        return BASE_URL + code;

    }

    public String getOriginalUrl(String shortCode) {
        UrlMapping mapping = urlMapping.get(shortCode);

        if (mapping == null) {
            return null;
        }

        mapping.incrementClicks();
        return mapping.getLongUrl();
    }

    private String generateCode(Long id) {

        String CHARSET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int BASE = 62;
        StringBuilder sb = new StringBuilder();

        while (id>0) {
            sb.append(CHARSET.charAt((int) (id%BASE)));
            id/=BASE;
        }

        return sb.reverse().toString();



    }
}
