package com.squareup.picasso;

import android.graphics.Bitmap;

class GetAction extends Action<Void> {
    GetAction(Picasso picasso, Request data, int memoryPolicy, int networkPolicy, Object tag, String key) {
        super(picasso, null, data, memoryPolicy, networkPolicy, 0, null, key, tag, false);
    }

    void complete(Bitmap result, Picasso$LoadedFrom from) {
    }

    public void error() {
    }
}
