package com.danikula.videocache.sourcestorage;

import com.danikula.videocache.SourceInfo;

public class NoSourceInfoStorage implements SourceInfoStorage {
    public SourceInfo get(String url) {
        return null;
    }

    public void put(String url, SourceInfo sourceInfo) {
    }

    public void release() {
    }
}
