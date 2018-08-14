package com.unit.three.p139a;

import android.util.SparseBooleanArray;
import com.unit.three.p141c.C4078f;
import com.unit.three.p143e.C4088b;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class C4047d {
    private static C4047d f9340a;
    private long f9341b = 32500;
    private long f9342c = 27500;
    private boolean f9343d = false;
    private boolean f9344e = false;
    private volatile C4046d f9345f;
    private volatile C4045b f9346g;
    private volatile C4045b f9347h;
    private int f9348i;
    private C4035c f9349j;
    private long f9350k = -3;
    private SparseBooleanArray f9351l = new SparseBooleanArray();
    private LinkedBlockingQueue f9352m;

    public interface C4035c {
        void mo6913d();
    }

    public final class C4044a {
        public static final int f9331a = 1;
        public static final int f9332b = 2;
        private static final /* synthetic */ int[] f9333c = new int[]{1, 2};

        public static int[] m12468a() {
            return (int[]) f9333c.clone();
        }
    }

    final class C4045b implements Runnable {
        private int f9334a;
        private boolean f9335b;

        private C4045b() {
        }

        public final C4045b m12469a(int i) {
            this.f9334a = i;
            return this;
        }

        public final void m12470a() {
            this.f9335b = true;
        }

        public final int m12471b() {
            return this.f9334a;
        }

        public final void run() {
            if (!this.f9335b && C4047d.m12474a().f9351l.get(this.f9334a)) {
                C4047d.m12474a().m12481d();
                C4047d.m12474a().m12476a(C4044a.f9332b);
                if (C4047d.m12474a().f9349j != null) {
                    C4047d.m12474a().f9349j.mo6913d();
                }
            }
        }
    }

    final class C4046d implements Runnable {
        private LinkedBlockingQueue f9336a;
        private boolean f9337b;
        private long f9338c;
        private boolean f9339d;

        public C4046d(LinkedBlockingQueue linkedBlockingQueue, boolean z) {
            this.f9336a = linkedBlockingQueue;
            this.f9337b = z;
        }

        public final long m12472a() {
            return this.f9338c;
        }

        public final void m12473b() {
            this.f9339d = true;
        }

        public final void run() {
            if (!this.f9339d) {
                if (this.f9336a == null) {
                    this.f9336a = C4047d.m12474a().f9352m;
                }
                if (this.f9336a != null) {
                    int i;
                    try {
                        int i2;
                        C4047d.m12474a().m12481d();
                        ByteBuffer allocate = ByteBuffer.allocate(240);
                        allocate.put((byte) 104);
                        if (this.f9337b) {
                            C4047d.m12474a();
                            i2 = Integer.MAX_VALUE;
                        } else {
                            i2 = C4047d.m12474a().m12491c();
                        }
                        allocate.putInt(i2);
                        allocate.put(C4078f.m12561a(this.f9337b ? 1 : 2));
                        allocate.putLong(C4047d.m12474a().f9350k);
                        byte[] c = C4050g.m12495a().m12499c();
                        if (c != null) {
                            allocate.position(40);
                            allocate.putInt(c.length);
                            allocate.put(c);
                        }
                        C4050g.m12495a().m12497b();
                        allocate.position(0);
                        allocate.limit(240);
                        this.f9336a.put(allocate);
                        this.f9338c = System.currentTimeMillis();
                    } catch (Throwable th) {
                        C4037a.m12449a();
                        C4037a.m12453a(th);
                        return;
                    }
                    C4045b d = this.f9337b ? C4047d.m12474a().f9347h : C4047d.m12474a().f9346g;
                    if (this.f9337b) {
                        C4047d.m12474a();
                        i = Integer.MAX_VALUE;
                    } else {
                        i = C4047d.m12474a().m12491c();
                    }
                    C4088b.m12615a().m12616b().schedule(d.m12469a(i), this.f9337b ? 5000 : C4047d.m12474a().f9341b, TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    private C4047d() {
    }

    public static C4047d m12474a() {
        if (f9340a == null) {
            synchronized (C4047d.class) {
                if (f9340a == null) {
                    f9340a = new C4047d();
                }
            }
        }
        return f9340a;
    }

    private synchronized void m12476a(int i) {
        if (!this.f9343d) {
            switch (C4048e.f9353a[i - 1]) {
                case 1:
                    if (this.f9342c > 5000) {
                        this.f9342c /= 2;
                        this.f9341b += this.f9342c;
                        break;
                    }
                    this.f9343d = true;
                    break;
                case 2:
                    this.f9342c /= 2;
                    this.f9341b -= this.f9342c;
                    break;
                default:
                    break;
            }
        }
    }

    private synchronized void m12481d() {
        if (this.f9348i < Integer.MAX_VALUE) {
            this.f9348i++;
        } else {
            this.f9348i = 0;
            this.f9351l = new SparseBooleanArray();
        }
        this.f9351l.put(this.f9348i, true);
    }

    public final void m12486a(long j, int i, boolean z) {
        this.f9351l.put(i, false);
        if (z && i == Integer.MAX_VALUE) {
            C4088b.m12615a().m12616b().remove(this.f9347h);
        } else if (i == this.f9346g.m12471b()) {
            this.f9350k = this.f9345f.m12472a() == 0 ? -3 : j - this.f9345f.m12472a();
            C4088b.m12615a().m12616b().remove(this.f9346g);
            m12476a(C4044a.f9331a);
            m12489a(null, false);
        }
    }

    public final void m12487a(C4035c c4035c) {
        this.f9349j = c4035c;
    }

    public final void m12488a(LinkedBlockingQueue linkedBlockingQueue) {
        this.f9352m = linkedBlockingQueue;
    }

    public final void m12489a(LinkedBlockingQueue linkedBlockingQueue, boolean z) {
        try {
            if (this.f9345f == null) {
                this.f9345f = new C4046d(linkedBlockingQueue, false);
            }
            if (this.f9346g == null) {
                this.f9346g = new C4045b();
            }
            if (this.f9347h == null) {
                this.f9347h = new C4045b();
            }
            if (z) {
                C4088b.m12615a().m12617c().execute(new C4046d(linkedBlockingQueue, true));
            } else if (!this.f9343d) {
                C4088b.m12615a().m12616b().schedule(this.f9345f, this.f9341b, TimeUnit.MILLISECONDS);
            } else if (!this.f9344e) {
                this.f9344e = true;
                C4088b.m12615a().m12616b().scheduleWithFixedDelay(this.f9345f, this.f9341b, this.f9341b, TimeUnit.MILLISECONDS);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void m12490b() {
        try {
            if (this.f9345f != null) {
                this.f9345f.m12473b();
                C4088b.m12615a().m12616b().remove(this.f9345f);
                this.f9345f = null;
            }
            if (this.f9346g != null) {
                this.f9346g.m12470a();
                C4088b.m12615a().m12616b().remove(this.f9346g);
                this.f9346g = null;
            }
            if (this.f9347h != null) {
                this.f9347h.m12470a();
                C4088b.m12615a().m12616b().remove(this.f9347h);
                this.f9347h = null;
            }
        } catch (Throwable th) {
        }
    }

    public final int m12491c() {
        return this.f9348i;
    }
}
