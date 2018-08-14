package com.oneaudience.sdk.model;

import java.util.ArrayList;

public class TvInput {
    long timestamp;
    ArrayList<String> tvList;

    public TvInput(long j, ArrayList<String> arrayList) {
        this.timestamp = j;
        this.tvList = arrayList;
    }
}
