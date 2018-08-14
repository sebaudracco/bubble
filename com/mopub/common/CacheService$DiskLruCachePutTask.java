package com.mopub.common;

import android.os.AsyncTask;

class CacheService$DiskLruCachePutTask extends AsyncTask<Void, Void, Void> {
    private final byte[] mContent;
    private final String mKey;

    CacheService$DiskLruCachePutTask(String key, byte[] content) {
        this.mKey = key;
        this.mContent = content;
    }

    protected Void doInBackground(Void... voids) {
        CacheService.putToDiskCache(this.mKey, this.mContent);
        return null;
    }
}
