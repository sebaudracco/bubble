package com.mopub.common;

import android.os.AsyncTask;

class CacheService$DiskLruCacheGetTask extends AsyncTask<Void, Void, byte[]> {
    private final CacheService$DiskLruCacheGetListener mDiskLruCacheGetListener;
    private final String mKey;

    CacheService$DiskLruCacheGetTask(String key, CacheService$DiskLruCacheGetListener diskLruCacheGetListener) {
        this.mDiskLruCacheGetListener = diskLruCacheGetListener;
        this.mKey = key;
    }

    protected byte[] doInBackground(Void... voids) {
        return CacheService.getFromDiskCache(this.mKey);
    }

    protected void onPostExecute(byte[] bytes) {
        if (isCancelled()) {
            onCancelled();
        } else if (this.mDiskLruCacheGetListener != null) {
            this.mDiskLruCacheGetListener.onComplete(this.mKey, bytes);
        }
    }

    protected void onCancelled() {
        if (this.mDiskLruCacheGetListener != null) {
            this.mDiskLruCacheGetListener.onComplete(this.mKey, null);
        }
    }
}
