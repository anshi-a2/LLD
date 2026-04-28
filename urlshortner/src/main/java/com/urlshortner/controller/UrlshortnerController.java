package com.urlshortner.controller;

import com.urlshortner.dto.ShortenRequest;
import com.urlshortner.service.ShortenUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlshortnerController {

    @Autowired
    ShortenUrlService shortenUrlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody ShortenRequest req) {
        String url = shortenUrlService.shortUrl(req.getLongurl());
        return ResponseEntity.ok(url);
    }

    @GetMapping("/{shorten}")
    public ResponseEntity<?> redirect(@PathVariable String shorten) {
        String originalUrl = shortenUrlService.getOriginalUrl(shorten);
        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(302)
                .location(URI.create(originalUrl))
                .build();
    }
}
