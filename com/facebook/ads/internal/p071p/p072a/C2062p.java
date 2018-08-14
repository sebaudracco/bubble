package com.facebook.ads.internal.p071p.p072a;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class C2062p implements Map<String, String> {
    private Map<String, String> f4904a = new HashMap();

    public C2062p m6623a(Map<? extends String, ? extends String> map) {
        putAll(map);
        return this;
    }

    public String m6624a() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.f4904a.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(str);
            String str2 = (String) this.f4904a.get(str2);
            if (str2 != null) {
                stringBuilder.append("=");
                try {
                    stringBuilder.append(URLEncoder.encode(str2, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    public String m6625a(Object obj) {
        return (String) this.f4904a.get(obj);
    }

    public String m6626a(String str, String str2) {
        return (String) this.f4904a.put(str, str2);
    }

    public String m6627b(Object obj) {
        return (String) this.f4904a.remove(obj);
    }

    public byte[] m6628b() {
        byte[] bArr = null;
        try {
            bArr = m6624a().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bArr;
    }

    public void clear() {
        this.f4904a.clear();
    }

    public boolean containsKey(Object obj) {
        return this.f4904a.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.f4904a.containsValue(obj);
    }

    public Set<Entry<String, String>> entrySet() {
        return this.f4904a.entrySet();
    }

    public /* synthetic */ Object get(Object obj) {
        return m6625a(obj);
    }

    public boolean isEmpty() {
        return this.f4904a.isEmpty();
    }

    public Set<String> keySet() {
        return this.f4904a.keySet();
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        return m6626a((String) obj, (String) obj2);
    }

    public void putAll(Map<? extends String, ? extends String> map) {
        this.f4904a.putAll(map);
    }

    public /* synthetic */ Object remove(Object obj) {
        return m6627b(obj);
    }

    public int size() {
        return this.f4904a.size();
    }

    public Collection<String> values() {
        return this.f4904a.values();
    }
}
