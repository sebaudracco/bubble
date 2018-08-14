package com.yandex.metrica.impl;

import java.util.concurrent.TimeUnit;

public interface C4364d {

    public static class C4370a<T> {
        public static final long f11838a = TimeUnit.SECONDS.toMillis(10);
        private long f11839b;
        private long f11840c;
        private T f11841d;

        public C4370a() {
            this(f11838a);
        }

        public C4370a(long j) {
            this.f11840c = 0;
            this.f11841d = null;
            this.f11839b = j;
        }

        public T m15043a() {
            return this.f11841d;
        }

        public void m15044a(T t) {
            this.f11841d = t;
            this.f11840c = System.currentTimeMillis();
        }

        public final boolean m15045b() {
            return this.f11841d == null;
        }

        public final boolean m15046c() {
            long currentTimeMillis = System.currentTimeMillis() - this.f11840c;
            return currentTimeMillis > this.f11839b || currentTimeMillis < 0;
        }

        public T m15047d() {
            return m15046c() ? null : this.f11841d;
        }
    }
}
