package com.danikula.videocache.file;

import java.io.File;

public class TotalSizeLruDiskUsage extends LruDiskUsage {
    private final long maxSize;

    public TotalSizeLruDiskUsage(long maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.maxSize = maxSize;
    }

    protected boolean accept(File file, long totalSize, int totalCount) {
        return totalSize <= this.maxSize;
    }
}
