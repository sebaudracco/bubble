package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.annotation.GuardedBy;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.client.CookieStore;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieIdentityComparator;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

@ThreadSafe
public class BasicCookieStore implements CookieStore, Serializable {
    private static final long serialVersionUID = -7581093305228232025L;
    @GuardedBy("this")
    private final TreeSet<Cookie> cookies = new TreeSet(new CookieIdentityComparator());

    public synchronized void addCookie(Cookie cookie) {
        if (cookie != null) {
            this.cookies.remove(cookie);
            if (!cookie.isExpired(new Date())) {
                this.cookies.add(cookie);
            }
        }
    }

    public synchronized void addCookies(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cooky : cookies) {
                addCookie(cooky);
            }
        }
    }

    public synchronized List<Cookie> getCookies() {
        return new ArrayList(this.cookies);
    }

    public synchronized boolean clearExpired(Date date) {
        boolean z;
        if (date == null) {
            z = false;
        } else {
            z = false;
            Iterator<Cookie> it = this.cookies.iterator();
            while (it.hasNext()) {
                if (((Cookie) it.next()).isExpired(date)) {
                    it.remove();
                    z = true;
                }
            }
        }
        return z;
    }

    public synchronized void clear() {
        this.cookies.clear();
    }

    public synchronized String toString() {
        return this.cookies.toString();
    }
}
