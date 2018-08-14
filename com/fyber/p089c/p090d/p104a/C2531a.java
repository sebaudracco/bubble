package com.fyber.p089c.p090d.p104a;

import com.fyber.utils.C2665m;
import com.fyber.utils.FyberLogger;
import java.util.Calendar;
import java.util.Locale;

/* compiled from: BufferingHelper */
public final class C2531a {
    private final C2530a f6272a;
    private long f6273b = 0;
    private boolean f6274c = false;
    private int f6275d = 0;
    private long f6276e;
    private long f6277f;
    private int f6278g = 0;
    private long f6279h = 0;
    private long f6280i;
    private long f6281j;
    private boolean f6282k = false;

    /* compiled from: BufferingHelper */
    public interface C2530a {
        void mo3932a(boolean z);
    }

    public C2531a(C2530a c2530a) {
        if (c2530a == null) {
            throw new IllegalArgumentException("FybBufferingStateChangedListener is required");
        }
        this.f6272a = c2530a;
        this.f6276e = Calendar.getInstance().getTimeInMillis();
    }

    public final void m8032a(long j) {
        this.f6281j = j;
    }

    public final void m8031a() {
        this.f6277f = Calendar.getInstance().getTimeInMillis() - this.f6276e;
    }

    public final boolean m8033a(long j, boolean z, boolean z2) {
        if (j == this.f6281j) {
            m8030a(false, j, this.f6281j);
            return this.f6274c;
        } else if (z || j != this.f6273b || this.f6282k) {
            this.f6282k = z2;
            if (this.f6282k) {
                return this.f6274c;
            }
            if (j >= this.f6273b + 430 || j < 430) {
                m8030a(false, j, this.f6281j);
            } else {
                m8030a(true, j, this.f6281j);
            }
            this.f6273b = j;
            return this.f6274c;
        } else {
            if (this.f6275d != this.f6278g) {
                this.f6275d++;
            } else if (!z2) {
                this.f6275d = 0;
                m8030a(false, j, this.f6281j);
            }
            return this.f6274c;
        }
    }

    private void m8030a(boolean z, long j, long j2) {
        if ((!this.f6274c) == z) {
            String str = "BufferingHelper";
            String str2 = "changing to%s buffering";
            Object[] objArr = new Object[1];
            objArr[0] = z ? "" : " NOT";
            FyberLogger.m8448d(str, String.format(str2, objArr));
            if (C2665m.m8522a()) {
                if (z) {
                    this.f6276e = Calendar.getInstance().getTimeInMillis();
                    this.f6280i = j - this.f6279h;
                    FyberLogger.m8448d("BufferingHelper", "mTotalPlayedTimeSinceBuffering = " + this.f6280i);
                    long j3 = (this.f6280i * 100) / j2;
                    FyberLogger.m8448d("BufferingHelper", "percentage = " + j3);
                    FyberLogger.m8448d("BufferingHelper", String.format(Locale.ENGLISH, "buffering %d percent took %d ms ", new Object[]{Long.valueOf(j3), Long.valueOf(this.f6277f)}));
                    if (j3 > 0) {
                        FyberLogger.m8448d("BufferingHelper", String.format(Locale.ENGLISH, "Required buffering time for %d percent %d", new Object[]{Integer.valueOf(15), Long.valueOf((15 * this.f6277f) / j3)}));
                        this.f6278g = (int) (j3 / 500);
                        FyberLogger.m8448d("BufferingHelper", "mBufferCounter - " + this.f6278g);
                    }
                } else {
                    this.f6277f = Calendar.getInstance().getTimeInMillis() - this.f6276e;
                    this.f6279h = j;
                    this.f6273b = j - 1000;
                }
            }
            this.f6272a.mo3932a(z);
            this.f6274c = z;
        }
    }
}
