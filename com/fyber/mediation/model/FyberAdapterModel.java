package com.fyber.mediation.model;

import com.fyber.mediation.FyberAdapter;

public class FyberAdapterModel {
    private static volatile FyberAdapterModel sInstance;
    private FyberAdapter mFyberAdapter;

    private FyberAdapterModel() {
    }

    public static synchronized FyberAdapterModel getInstance() {
        FyberAdapterModel fyberAdapterModel;
        synchronized (FyberAdapterModel.class) {
            if (sInstance == null) {
                sInstance = new FyberAdapterModel();
            }
            fyberAdapterModel = sInstance;
        }
        return fyberAdapterModel;
    }

    public final FyberAdapter getFyberAdapter() {
        return this.mFyberAdapter;
    }

    public final void setFyberAdapter(FyberAdapter adapter) {
        this.mFyberAdapter = adapter;
    }
}
