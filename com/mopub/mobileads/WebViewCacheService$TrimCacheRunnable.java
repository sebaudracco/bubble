package com.mopub.mobileads;

class WebViewCacheService$TrimCacheRunnable implements Runnable {
    private WebViewCacheService$TrimCacheRunnable() {
    }

    public void run() {
        WebViewCacheService.trimCache();
    }
}
