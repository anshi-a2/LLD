    package com.urlshortner.dto;

    public class UrlMapping {
        private String shortCode;
        private String longUrl;
        private Long createdAt;
        private int clickCnt;

        public UrlMapping(String shortCode, String longUrl) {
            this.shortCode = shortCode;
            this.longUrl = longUrl;
            this.createdAt = System.currentTimeMillis();
            this.clickCnt = 0;
        }
        public String getShortCode() { return shortCode; }
        public String getLongUrl() { return longUrl; }
        public int getClickCount() { return clickCnt; }

        public void incrementClicks() {
            this.clickCnt++;
        }
    }
