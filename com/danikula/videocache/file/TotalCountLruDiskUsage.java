package com.danikula.videocache.file;

import java.io.File;

public class TotalCountLruDiskUsage extends LruDiskUsage {
    private final int maxCount;

    public TotalCountLruDiskUsage(int maxCount) {
        if (maxCount <= 0) {
            throw new IllegalArgumentException("Max count must be positive number!");
        }
        this.maxCount = maxCount;
    }

    protected boolean accept(File file, long totalSize, int totalCount) {
        return totalCount <= this.maxCount;
    }
}
