package com.vungle.publisher;

import org.json.JSONException;

/* compiled from: vungle */
public abstract class vt<T> {
    public abstract T mo6940b() throws JSONException;

    public String m13454e() throws JSONException {
        Object b = mo6940b();
        return b == null ? null : b.toString();
    }
}
