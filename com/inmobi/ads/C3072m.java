package com.inmobi.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.inmobi.ads.C3029b.C3027a;
import com.inmobi.ads.C3046c.C3037a;
import com.inmobi.ads.p110a.C2967a;
import com.inmobi.ads.p110a.C2967a.C2966a;
import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.configs.C3121b.C2911b;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: AssetStore */
public final class C3072m implements C2911b {
    private static final String f7493a = C3072m.class.getSimpleName();
    private static C3072m f7494h;
    private static final Object f7495i = new Object();
    private static final Object f7496j = new Object();
    private static final Object f7497k = new Object();
    private C3067l f7498b;
    private C3037a f7499c;
    private ExecutorService f7500d;
    private C3071a f7501e;
    private HandlerThread f7502f;
    private AtomicBoolean f7503g = new AtomicBoolean(false);
    private HashMap<String, List<C3053b>> f7504l = new HashMap();
    private final C2966a f7505m = new C30681(this);

    /* compiled from: AssetStore */
    public interface C3053b {
        void mo6241a(C3029b c3029b, String str);

        void mo6242a(C3029b c3029b, boolean z, String str);
    }

    /* compiled from: AssetStore */
    class C30681 implements C2966a {
        final /* synthetic */ C3072m f7484a;

        C30681(C3072m c3072m) {
            this.f7484a = c3072m;
        }

        public void mo6244a(@NonNull C3143c c3143c, @NonNull String str, @NonNull C3029b c3029b, String str2) {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Asset fetch succeeded for remote URL (" + c3029b.m9467b() + ")");
            C3029b a = new C3027a().m9462a(c3029b.m9467b(), str, c3143c, this.f7484a.f7499c.m9610a(), this.f7484a.f7499c.m9613d()).m9463a();
            this.f7484a.f7498b.m9887b(a);
            this.f7484a.m9908a(false, a, str2);
        }

        public void mo6243a(@NonNull C3029b c3029b, String str) {
            Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Asset fetch failed for remote URL (" + c3029b.m9467b() + ")");
            if (c3029b.f7220c <= 0) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Exhausted all attempts; signaling clients of failure to fetch this asset");
                this.f7484a.m9905a(c3029b, str);
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Updating asset fetch attempt timestamp; will be reattempted later");
                c3029b.f7222e = System.currentTimeMillis();
                this.f7484a.f7498b.m9887b(c3029b);
            }
            this.f7484a.m9921g();
            try {
                this.f7484a.m9915b();
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Encountered unexpected error in starting asset fetcher");
                C3135c.m10255a().m10279a(new C3132b(e));
            }
        }
    }

    /* compiled from: AssetStore */
    private static final class C3071a extends Handler {
        private WeakReference<C3072m> f7489a;
        private List<C3029b> f7490b = new ArrayList();
        private final C2966a f7491c;
        private final String f7492d;

        /* compiled from: AssetStore */
        class C30701 implements C2966a {
            final /* synthetic */ C3071a f7488a;

            C30701(C3071a c3071a) {
                this.f7488a = c3071a;
            }

            public void mo6244a(C3143c c3143c, String str, C3029b c3029b, String str2) {
                C3072m c3072m = (C3072m) this.f7488a.f7489a.get();
                if (c3072m != null) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Asset fetch succeeded.Updating asset with location on disk (file://" + str + ")");
                    C3029b a = new C3027a().m9462a(c3029b.m9467b(), str, c3143c, c3072m.m9919e().m9610a(), c3072m.m9919e().m9613d()).m9463a();
                    c3072m.m9920f().m9887b(a);
                    c3072m.m9908a(false, a, str2);
                    this.f7488a.m9899b(c3029b);
                    return;
                }
                Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Encountered unexpected error in handling onAssetFetchSucceeded: AssetStore is null");
            }

            public void mo6243a(C3029b c3029b, String str) {
                C3072m c3072m = (C3072m) this.f7488a.f7489a.get();
                if (c3072m != null) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Asset fetch failed");
                    if (c3029b.f7220c > 0) {
                        c3029b.f7220c--;
                        c3029b.f7222e = System.currentTimeMillis();
                        c3072m.m9920f().m9887b(c3029b);
                    }
                    this.f7488a.m9901c(c3029b);
                    return;
                }
                Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Encountered unexpected error in handling onAssetFetchFailed: AssetStore is null");
            }
        }

        public C3071a(@NonNull Looper looper, @NonNull C3072m c3072m, @NonNull String str) {
            super(looper);
            this.f7489a = new WeakReference(c3072m);
            this.f7492d = str;
            this.f7491c = new C30701(this);
        }

        public void handleMessage(Message message) {
            try {
                C3072m c3072m;
                C3029b c3029b;
                C3029b c3029b2;
                switch (message.what) {
                    case 1:
                        if (this.f7489a.get() != null) {
                            C3037a q;
                            c3072m = (C3072m) this.f7489a.get();
                            C3037a e = c3072m.m9919e();
                            if (e == null) {
                                C3045a c3046c = new C3046c();
                                C3121b.m10178a().m10190a(c3046c, null);
                                q = c3046c.m9732q();
                            } else {
                                q = e;
                            }
                            for (C3029b c3029b3 : c3072m.m9920f().m9886b(q.m9611b())) {
                                if (this.f7490b.indexOf(c3029b3) == -1 && !c3029b3.m9469d()) {
                                    this.f7490b.add(c3029b3);
                                }
                            }
                            if (this.f7490b.isEmpty()) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "No assets to cache now ...");
                                c3072m.m9921g();
                                return;
                            }
                            Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Found " + this.f7490b.size() + " assets that should be cached");
                            Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Scheduling asset fetch ...");
                            c3029b2 = (C3029b) this.f7490b.get(0);
                            Message obtain = Message.obtain();
                            obtain.what = 2;
                            obtain.obj = c3029b2;
                            long currentTimeMillis = System.currentTimeMillis() - c3029b2.f7222e;
                            if (currentTimeMillis < ((long) (q.m9611b() * 1000))) {
                                sendMessageDelayed(obtain, ((long) (q.m9611b() * 1000)) - currentTimeMillis);
                                return;
                            } else {
                                sendMessage(obtain);
                                return;
                            }
                        }
                        return;
                    case 2:
                        if (this.f7489a.get() != null) {
                            c3072m = (C3072m) this.f7489a.get();
                            if (C3155d.m10406a()) {
                                c3029b3 = (C3029b) message.obj;
                                if (c3029b3.f7220c == 0) {
                                    c3072m.m9905a(c3029b3, this.f7492d);
                                    m9897a(c3029b3);
                                    return;
                                } else if (c3029b3.m9469d()) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Asset is already cached, signaling asset fetch success");
                                    m9899b(c3029b3);
                                    return;
                                } else {
                                    int a = (c3072m.m9919e().m9610a() - c3029b3.f7220c) + 1;
                                    if (a == 0) {
                                        Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Caching asset (" + c3029b3.m9467b() + ")");
                                    } else {
                                        Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Retry attempt #" + a + " to cache asset (" + c3029b3.m9467b() + ")");
                                    }
                                    new C2967a(this.f7491c, this.f7492d).m9118a(c3029b3);
                                    return;
                                }
                            }
                            c3072m.m9921g();
                            c3072m.m9917c();
                            return;
                        }
                        return;
                    case 3:
                        break;
                    case 4:
                        c3029b2 = (C3029b) message.obj;
                        C3072m c3072m2 = (C3072m) this.f7489a.get();
                        if (c3072m2 != null) {
                            c3072m2.m9920f().m9889c(c3029b2);
                            break;
                        }
                        break;
                    default:
                        return;
                }
                this.f7490b.remove((C3029b) message.obj);
                if (this.f7490b.isEmpty()) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "All assets fetched; scheduling another run to check for fresh assets");
                    sendEmptyMessage(1);
                    return;
                }
                m9896a();
            } catch (Throwable e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Encountered unexpected error in Asset fetch handler");
                C3135c.m10255a().m10279a(new C3132b(e2));
            }
        }

        private void m9897a(C3029b c3029b) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            obtain.obj = c3029b;
            sendMessage(obtain);
        }

        private void m9899b(C3029b c3029b) {
            Message obtain = Message.obtain();
            obtain.what = 3;
            obtain.obj = c3029b;
            sendMessage(obtain);
        }

        private void m9896a() {
            Message obtain = Message.obtain();
            obtain.what = 2;
            obtain.obj = this.f7490b.get(0);
            sendMessage(obtain);
        }

        private void m9901c(C3029b c3029b) {
            int indexOf = this.f7490b.indexOf(c3029b);
            if (-1 != indexOf) {
                C3072m c3072m = (C3072m) this.f7489a.get();
                if (c3072m != null) {
                    C3029b c3029b2 = (C3029b) this.f7490b.get(indexOf == this.f7490b.size() + -1 ? 0 : indexOf + 1);
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    obtain.obj = c3029b2;
                    if (System.currentTimeMillis() - c3029b.f7222e < ((long) (c3072m.m9919e().m9611b() * 1000))) {
                        sendMessageDelayed(obtain, (long) (c3072m.m9919e().m9611b() * 1000));
                    } else {
                        sendMessage(obtain);
                    }
                }
            }
        }
    }

    private C3072m() {
        C3045a c3046c = new C3046c();
        C3121b.m10178a().m10190a(c3046c, (C2911b) this);
        this.f7499c = c3046c.m9732q();
        this.f7498b = C3067l.m9879a();
        this.f7500d = Executors.newFixedThreadPool(1);
        this.f7502f = new HandlerThread("assetFetcher");
        this.f7502f.start();
        this.f7501e = new C3071a(this.f7502f.getLooper(), this, "AssetPrefetch");
    }

    public static C3072m m9903a() {
        C3072m c3072m = f7494h;
        if (c3072m == null) {
            synchronized (f7495i) {
                c3072m = f7494h;
                if (c3072m == null) {
                    c3072m = new C3072m();
                    f7494h = c3072m;
                }
            }
        }
        return c3072m;
    }

    public void mo6102a(C3045a c3045a) {
        this.f7499c = ((C3046c) c3045a).m9732q();
    }

    public void m9913a(String str, C3053b c3053b) {
        synchronized (f7497k) {
            if (c3053b != null) {
                List list = (List) this.f7504l.get(str);
                if (list == null) {
                    list = new ArrayList();
                }
                list.add(c3053b);
                this.f7504l.put(str, list);
            }
        }
    }

    public void m9916b(String str, C3053b c3053b) {
        if (c3053b != null) {
            List list = (List) this.f7504l.get(str);
            list.remove(c3053b);
            if (list.size() == 0) {
                this.f7504l.remove(str);
            } else {
                this.f7504l.put(str, list);
            }
        }
    }

    private void m9908a(boolean z, C3029b c3029b, String str) {
        synchronized (f7497k) {
            List list = (List) this.f7504l.get(c3029b.m9467b());
            if (list != null) {
                for (int size = list.size(); size != 0; size = list.size()) {
                    try {
                        ((C3053b) list.get(0)).mo6242a(c3029b, z, str);
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7493a, "Encountered unexpected error in onAssetFetchSucceeded handler: " + e.getMessage());
                        C3135c.m10255a().m10279a(new C3132b(e));
                    }
                }
            }
        }
    }

    private void m9905a(C3029b c3029b, String str) {
        synchronized (f7497k) {
            List list = (List) this.f7504l.get(c3029b.m9467b());
            if (list != null) {
                for (int size = list.size(); size != 0; size = list.size()) {
                    try {
                        ((C3053b) list.get(0)).mo6241a(c3029b, str);
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7493a, "Encountered unexpected error in onAssetFetchFailed handler: " + e.getMessage());
                        C3135c.m10255a().m10279a(new C3132b(e));
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m9915b() {
        /*
        r6 = this;
        r0 = com.inmobi.commons.core.utilities.C3155d.m10406a();
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r1 = f7496j;
        monitor-enter(r1);
        r0 = r6.f7503g;	 Catch:{ all -> 0x005e }
        r2 = 0;
        r3 = 1;
        r0 = r0.compareAndSet(r2, r3);	 Catch:{ all -> 0x005e }
        if (r0 == 0) goto L_0x009b;
    L_0x0014:
        r0 = r6.f7502f;	 Catch:{ all -> 0x005e }
        if (r0 != 0) goto L_0x0027;
    L_0x0018:
        r0 = new android.os.HandlerThread;	 Catch:{ all -> 0x005e }
        r2 = "assetFetcher";
        r0.<init>(r2);	 Catch:{ all -> 0x005e }
        r6.f7502f = r0;	 Catch:{ all -> 0x005e }
        r0 = r6.f7502f;	 Catch:{ all -> 0x005e }
        r0.start();	 Catch:{ all -> 0x005e }
    L_0x0027:
        r0 = r6.f7501e;	 Catch:{ all -> 0x005e }
        if (r0 != 0) goto L_0x003b;
    L_0x002b:
        r0 = new com.inmobi.ads.m$a;	 Catch:{ all -> 0x005e }
        r2 = r6.f7502f;	 Catch:{ all -> 0x005e }
        r2 = r2.getLooper();	 Catch:{ all -> 0x005e }
        r3 = "AssetPrefetch";
        r0.<init>(r2, r6, r3);	 Catch:{ all -> 0x005e }
        r6.f7501e = r0;	 Catch:{ all -> 0x005e }
    L_0x003b:
        r2 = new java.util.ArrayList;	 Catch:{ all -> 0x005e }
        r2.<init>();	 Catch:{ all -> 0x005e }
        r0 = r6.f7498b;	 Catch:{ all -> 0x005e }
        r3 = r6.f7499c;	 Catch:{ all -> 0x005e }
        r3 = r3.m9611b();	 Catch:{ all -> 0x005e }
        r0 = r0.m9886b(r3);	 Catch:{ all -> 0x005e }
        r3 = r0.isEmpty();	 Catch:{ all -> 0x005e }
        if (r3 == 0) goto L_0x0061;
    L_0x0052:
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x005e }
        r2 = f7493a;	 Catch:{ all -> 0x005e }
        r3 = "No assets to cache. Returning ...";
        com.inmobi.commons.core.utilities.Logger.m10359a(r0, r2, r3);	 Catch:{ all -> 0x005e }
        monitor-exit(r1);	 Catch:{ all -> 0x005e }
        goto L_0x0006;
    L_0x005e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x005e }
        throw r0;
    L_0x0061:
        r3 = r0.iterator();	 Catch:{ all -> 0x005e }
    L_0x0065:
        r0 = r3.hasNext();	 Catch:{ all -> 0x005e }
        if (r0 == 0) goto L_0x0082;
    L_0x006b:
        r0 = r3.next();	 Catch:{ all -> 0x005e }
        r0 = (com.inmobi.ads.C3029b) r0;	 Catch:{ all -> 0x005e }
        r4 = r2.indexOf(r0);	 Catch:{ all -> 0x005e }
        r5 = -1;
        if (r4 != r5) goto L_0x0065;
    L_0x0078:
        r4 = r0.m9469d();	 Catch:{ all -> 0x005e }
        if (r4 != 0) goto L_0x0065;
    L_0x007e:
        r2.add(r0);	 Catch:{ all -> 0x005e }
        goto L_0x0065;
    L_0x0082:
        r0 = r2.isEmpty();	 Catch:{ all -> 0x005e }
        if (r0 == 0) goto L_0x009e;
    L_0x0088:
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x005e }
        r2 = f7493a;	 Catch:{ all -> 0x005e }
        r3 = "All assets are cached. Nothing to do here ...";
        com.inmobi.commons.core.utilities.Logger.m10359a(r0, r2, r3);	 Catch:{ all -> 0x005e }
        r0 = r6.f7503g;	 Catch:{ all -> 0x005e }
        r2 = 0;
        r0.set(r2);	 Catch:{ all -> 0x005e }
        r6.m9917c();	 Catch:{ all -> 0x005e }
    L_0x009b:
        monitor-exit(r1);	 Catch:{ all -> 0x005e }
        goto L_0x0006;
    L_0x009e:
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ all -> 0x005e }
        r3 = f7493a;	 Catch:{ all -> 0x005e }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x005e }
        r4.<init>();	 Catch:{ all -> 0x005e }
        r5 = "Scheduling asset fetch for ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x005e }
        r2 = r2.size();	 Catch:{ all -> 0x005e }
        r2 = r4.append(r2);	 Catch:{ all -> 0x005e }
        r4 = " assets";
        r2 = r2.append(r4);	 Catch:{ all -> 0x005e }
        r2 = r2.toString();	 Catch:{ all -> 0x005e }
        com.inmobi.commons.core.utilities.Logger.m10359a(r0, r3, r2);	 Catch:{ all -> 0x005e }
        r0 = r6.f7501e;	 Catch:{ all -> 0x005e }
        r2 = 1;
        r0.sendEmptyMessage(r2);	 Catch:{ all -> 0x005e }
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.m.b():void");
    }

    public void m9917c() {
        this.f7503g.set(false);
        synchronized (f7496j) {
            if (!(this.f7503g.get() || this.f7502f == null)) {
                this.f7502f.getLooper().quit();
                this.f7502f.interrupt();
                this.f7502f = null;
                this.f7501e = null;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m9918d() {
        /*
        r4 = this;
        r2 = f7496j;
        monitor-enter(r2);
        r0 = r4.f7498b;	 Catch:{ all -> 0x002b }
        r0 = r0.m9885b();	 Catch:{ all -> 0x002b }
        r1 = r0.isEmpty();	 Catch:{ all -> 0x002b }
        if (r1 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r2);	 Catch:{ all -> 0x002b }
    L_0x0010:
        return;
    L_0x0011:
        r1 = r0.iterator();	 Catch:{ all -> 0x002b }
    L_0x0015:
        r0 = r1.hasNext();	 Catch:{ all -> 0x002b }
        if (r0 == 0) goto L_0x002e;
    L_0x001b:
        r0 = r1.next();	 Catch:{ all -> 0x002b }
        r0 = (com.inmobi.ads.C3029b) r0;	 Catch:{ all -> 0x002b }
        r3 = r0.m9468c();	 Catch:{ all -> 0x002b }
        if (r3 == 0) goto L_0x0015;
    L_0x0027:
        r4.m9904a(r0);	 Catch:{ all -> 0x002b }
        goto L_0x0015;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x002b }
        throw r0;
    L_0x002e:
        r0 = r4.f7498b;	 Catch:{ all -> 0x002b }
        r1 = r4.f7499c;	 Catch:{ all -> 0x002b }
        r1 = r1.m9612c();	 Catch:{ all -> 0x002b }
        r3 = r0.m9882a(r1);	 Catch:{ all -> 0x002b }
        r0 = r3.size();	 Catch:{ all -> 0x002b }
        if (r0 <= 0) goto L_0x0055;
    L_0x0040:
        r0 = 0;
        r1 = r0;
    L_0x0042:
        r0 = r3.size();	 Catch:{ all -> 0x002b }
        if (r1 >= r0) goto L_0x0055;
    L_0x0048:
        r0 = r3.get(r1);	 Catch:{ all -> 0x002b }
        r0 = (com.inmobi.ads.C3029b) r0;	 Catch:{ all -> 0x002b }
        r4.m9904a(r0);	 Catch:{ all -> 0x002b }
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0042;
    L_0x0055:
        monitor-exit(r2);	 Catch:{ all -> 0x002b }
        goto L_0x0010;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.m.d():void");
    }

    private void m9904a(C3029b c3029b) {
        this.f7498b.m9889c(c3029b);
        File file = new File(c3029b.m9465a());
        if (file.exists()) {
            file.delete();
        }
    }

    public void m9914a(@NonNull final String str, @NonNull final String str2) {
        this.f7500d.execute(new Runnable(this) {
            final /* synthetic */ C3072m f7487c;

            public void run() {
                Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Attempting to cache remote URL: " + str);
                C3029b a = this.f7487c.f7498b.m9881a(str);
                if (!(a == null || a.f7221d == null || a.f7221d.length() == 0)) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Found disk cache location (" + a.f7221d + ")");
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Checking if file exists at this location ...");
                    if (new File(a.f7221d).exists()) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Cache hit; file exists location on disk (" + a.f7221d + ")");
                        this.f7487c.m9908a(true, a, str2);
                        return;
                    }
                    Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "File does not exist at location (" + a.f7221d + ")");
                    this.f7487c.f7498b.m9889c(a);
                }
                a = new C3027a().m9461a(str, this.f7487c.f7499c.m9610a(), this.f7487c.f7499c.m9613d()).m9463a();
                if (this.f7487c.f7498b.m9881a(str) != null) {
                    this.f7487c.f7498b.m9887b(a);
                } else {
                    this.f7487c.f7498b.m9883a(a);
                }
                Logger.m10359a(InternalLogLevel.INTERNAL, C3072m.f7493a, "Cache miss; attempting to cache asset");
                new C2967a(this.f7487c.f7505m, str2).m9118a(a);
            }
        });
    }

    C3037a m9919e() {
        return this.f7499c;
    }

    C3067l m9920f() {
        return this.f7498b;
    }

    void m9921g() {
        this.f7503g.set(false);
    }
}
