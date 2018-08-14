package com.moat.analytics.mobile.vng;

import android.view.View;
import java.util.Map;
import org.json.JSONObject;

abstract class C3490h<PlayerOrIMAAd> extends C3480c<PlayerOrIMAAd> {
    int f8903l = Integer.MIN_VALUE;
    private C3489a f8904m = C3489a.UNINITIALIZED;
    private int f8905n = Integer.MIN_VALUE;
    private double f8906o = Double.NaN;
    private int f8907p = Integer.MIN_VALUE;
    private int f8908q = 0;

    class C34881 implements Runnable {
        final /* synthetic */ C3490h f8896a;

        C34881(C3490h c3490h) {
            this.f8896a = c3490h;
        }

        public void run() {
            try {
                if (this.f8896a.j.get() == null || this.f8896a.m11866i()) {
                    this.f8896a.m11865h();
                } else if (Boolean.valueOf(this.f8896a.m11884m()).booleanValue()) {
                    this.f8896a.h.postDelayed(this, 200);
                } else {
                    this.f8896a.m11865h();
                }
            } catch (Exception e) {
                this.f8896a.m11865h();
                C3502m.m11942a(e);
            }
        }
    }

    protected enum C3489a {
        UNINITIALIZED,
        PAUSED,
        PLAYING,
        STOPPED,
        COMPLETED
    }

    C3490h(String str) {
        super(str);
    }

    private void m11878n() {
        this.h.postDelayed(new C34881(this), 200);
    }

    protected JSONObject mo6529a(MoatAdEvent moatAdEvent) {
        Integer j;
        if (moatAdEvent.f8854b.equals(MoatAdEvent.f8852a)) {
            try {
                j = mo6531j();
            } catch (Exception e) {
                j = Integer.valueOf(this.f8905n);
            }
            moatAdEvent.f8854b = j;
        } else {
            j = moatAdEvent.f8854b;
        }
        if (moatAdEvent.f8854b.intValue() < 0 || (moatAdEvent.f8854b.intValue() == 0 && moatAdEvent.f8856d == MoatAdEventType.AD_EVT_COMPLETE && this.f8905n > 0)) {
            j = Integer.valueOf(this.f8905n);
            moatAdEvent.f8854b = j;
        }
        if (moatAdEvent.f8856d == MoatAdEventType.AD_EVT_COMPLETE) {
            if (j.intValue() == Integer.MIN_VALUE || this.f8903l == Integer.MIN_VALUE || !m11862a(j, Integer.valueOf(this.f8903l))) {
                this.f8904m = C3489a.STOPPED;
                moatAdEvent.f8856d = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.f8904m = C3489a.COMPLETED;
            }
        }
        return super.mo6529a(moatAdEvent);
    }

    public boolean mo6530a(Map<String, String> map, PlayerOrIMAAd playerOrIMAAd, View view) {
        try {
            if (!this.e) {
                m11878n();
            }
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
        return super.mo6530a(map, playerOrIMAAd, view);
    }

    protected abstract Integer mo6531j();

    protected abstract boolean mo6544k();

    protected abstract Integer mo6545l();

    boolean m11884m() {
        if (this.j.get() == null || m11866i()) {
            return false;
        }
        int intValue;
        try {
            int intValue2 = mo6531j().intValue();
            if (this.f8905n >= 0 && intValue2 < 0) {
                return false;
            }
            this.f8905n = intValue2;
            if (intValue2 == 0) {
                return true;
            }
            intValue = mo6545l().intValue();
            boolean k = mo6544k();
            double d = ((double) intValue) / 4.0d;
            double a = C3515s.m11985a();
            MoatAdEventType moatAdEventType = null;
            if (intValue2 > this.f8907p) {
                this.f8907p = intValue2;
            }
            if (this.f8903l == Integer.MIN_VALUE) {
                this.f8903l = intValue;
            }
            if (k) {
                if (this.f8904m == C3489a.UNINITIALIZED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_START;
                    this.f8904m = C3489a.PLAYING;
                } else if (this.f8904m == C3489a.PAUSED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                    this.f8904m = C3489a.PLAYING;
                } else {
                    MoatAdEventType moatAdEventType2;
                    intValue = ((int) Math.floor(((double) intValue2) / d)) - 1;
                    if (intValue > -1 && intValue < 3) {
                        moatAdEventType2 = f[intValue];
                        if (!this.g.containsKey(moatAdEventType2)) {
                            this.g.put(moatAdEventType2, Integer.valueOf(1));
                            moatAdEventType = moatAdEventType2;
                        }
                    }
                    moatAdEventType2 = null;
                    moatAdEventType = moatAdEventType2;
                }
            } else if (this.f8904m != C3489a.PAUSED) {
                moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                this.f8904m = C3489a.PAUSED;
            }
            boolean z = moatAdEventType != null;
            if (!(z || Double.isNaN(this.f8906o) || Math.abs(this.f8906o - a) <= 0.05d)) {
                moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                z = true;
            }
            if (z) {
                dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(intValue2), Double.valueOf(a)));
            }
            this.f8906o = a;
            this.f8908q = 0;
            return true;
        } catch (Exception e) {
            intValue = this.f8908q;
            this.f8908q = intValue + 1;
            return intValue < 5;
        }
    }

    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            C3511p.m11978a("[SUCCESS] ", a() + " stopTracking succeeded for " + e());
        } catch (Exception e) {
            C3502m.m11942a(e);
        }
    }
}
