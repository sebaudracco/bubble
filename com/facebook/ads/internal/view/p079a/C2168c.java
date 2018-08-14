package com.facebook.ads.internal.view.p079a;

import java.util.HashMap;
import java.util.Map;

public class C2168c {
    public final String f5220a;
    public final long f5221b;
    public final long f5222c;
    public final long f5223d;
    public final long f5224e;
    public final long f5225f;
    public final long f5226g;
    public final long f5227h;

    public static class C2167a {
        private final String f5212a;
        private long f5213b = -1;
        private long f5214c = -1;
        private long f5215d = -1;
        private long f5216e = -1;
        private long f5217f = -1;
        private long f5218g = -1;
        private long f5219h = -1;

        public C2167a(String str) {
            this.f5212a = str;
        }

        public C2167a m6940a(long j) {
            this.f5213b = j;
            return this;
        }

        public C2168c m6941a() {
            return new C2168c(this.f5212a, this.f5213b, this.f5214c, this.f5215d, this.f5216e, this.f5217f, this.f5218g, this.f5219h);
        }

        public C2167a m6942b(long j) {
            this.f5214c = j;
            return this;
        }

        public C2167a m6943c(long j) {
            this.f5215d = j;
            return this;
        }

        public C2167a m6944d(long j) {
            this.f5216e = j;
            return this;
        }

        public C2167a m6945e(long j) {
            this.f5217f = j;
            return this;
        }

        public C2167a m6946f(long j) {
            this.f5218g = j;
            return this;
        }

        public C2167a m6947g(long j) {
            this.f5219h = j;
            return this;
        }
    }

    private C2168c(String str, long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        this.f5220a = str;
        this.f5221b = j;
        this.f5222c = j2;
        this.f5223d = j3;
        this.f5224e = j4;
        this.f5225f = j5;
        this.f5226g = j6;
        this.f5227h = j7;
    }

    public Map<String, String> m6948a() {
        Map<String, String> hashMap = new HashMap(7);
        hashMap.put("initial_url", this.f5220a);
        hashMap.put("handler_time_ms", String.valueOf(this.f5221b));
        hashMap.put("load_start_ms", String.valueOf(this.f5222c));
        hashMap.put("response_end_ms", String.valueOf(this.f5223d));
        hashMap.put("dom_content_loaded_ms", String.valueOf(this.f5224e));
        hashMap.put("scroll_ready_ms", String.valueOf(this.f5225f));
        hashMap.put("load_finish_ms", String.valueOf(this.f5226g));
        hashMap.put("session_finish_ms", String.valueOf(this.f5227h));
        return hashMap;
    }
}
