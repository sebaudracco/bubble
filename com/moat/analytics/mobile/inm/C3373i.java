package com.moat.analytics.mobile.inm;

import org.json.JSONObject;

abstract class C3373i<PlayerOrIMAAd> extends C3372f<PlayerOrIMAAd> {
    protected C3386k f8533j = C3386k.UNINITIALIZED;
    protected int f8534k = Integer.MIN_VALUE;
    protected double f8535l = Double.NaN;
    protected int f8536m = Integer.MIN_VALUE;
    protected int f8537n = Integer.MIN_VALUE;
    private int f8538o = 0;

    public C3373i(String str, C3371a c3371a, ao aoVar) {
        super(str, c3371a, aoVar);
    }

    protected JSONObject mo6472a(MoatAdEvent moatAdEvent) {
        Integer f;
        if (moatAdEvent.adPlayhead.equals(MoatAdEvent.TIME_UNAVAILABLE)) {
            try {
                f = mo6477f();
            } catch (Exception e) {
                f = Integer.valueOf(this.f8534k);
            }
            moatAdEvent.adPlayhead = f;
        } else {
            f = moatAdEvent.adPlayhead;
        }
        if (moatAdEvent.adPlayhead.intValue() < 0) {
            f = Integer.valueOf(this.f8534k);
            moatAdEvent.adPlayhead = f;
        }
        if (moatAdEvent.eventType == MoatAdEventType.AD_EVT_COMPLETE) {
            if (f.intValue() == Integer.MIN_VALUE || this.f8537n == Integer.MIN_VALUE || !m11493a(f, Integer.valueOf(this.f8537n))) {
                this.f8533j = C3386k.STOPPED;
                moatAdEvent.eventType = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.f8533j = C3386k.COMPLETED;
            }
        }
        return super.mo6472a(moatAdEvent);
    }

    protected void mo6473b() {
        super.mo6473b();
        this.d.postDelayed(new C3385j(this), 200);
    }

    protected abstract Integer mo6477f();

    protected abstract boolean mo6478g();

    protected abstract Integer mo6479h();

    protected boolean m11504i() {
        if (this.f.get() == null || m11498e()) {
            return false;
        }
        int intValue;
        try {
            int intValue2 = mo6477f().intValue();
            if (this.f8534k >= 0 && intValue2 < 0) {
                return false;
            }
            this.f8534k = intValue2;
            if (intValue2 == 0) {
                return true;
            }
            intValue = mo6479h().intValue();
            boolean g = mo6478g();
            double d = ((double) intValue) / 4.0d;
            double d2 = m11497d();
            MoatAdEventType moatAdEventType = null;
            if (intValue2 > this.f8536m) {
                this.f8536m = intValue2;
            }
            if (this.f8537n == Integer.MIN_VALUE) {
                this.f8537n = intValue;
            }
            if (g) {
                if (this.f8533j == C3386k.UNINITIALIZED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_START;
                    this.f8533j = C3386k.PLAYING;
                } else if (this.f8533j == C3386k.PAUSED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                    this.f8533j = C3386k.PLAYING;
                } else {
                    MoatAdEventType moatAdEventType2;
                    intValue = ((int) Math.floor(((double) intValue2) / d)) - 1;
                    if (intValue > -1 && intValue < 3) {
                        moatAdEventType2 = b[intValue];
                        if (!this.c.containsKey(moatAdEventType2)) {
                            this.c.put(moatAdEventType2, Integer.valueOf(1));
                            moatAdEventType = moatAdEventType2;
                        }
                    }
                    moatAdEventType2 = null;
                    moatAdEventType = moatAdEventType2;
                }
            } else if (this.f8533j != C3386k.PAUSED) {
                moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                this.f8533j = C3386k.PAUSED;
            }
            boolean z = moatAdEventType != null;
            if (!(z || Double.isNaN(this.f8535l) || Math.abs(this.f8535l - d2) <= 0.05d)) {
                moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                z = true;
            }
            if (z) {
                dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(intValue2), Double.valueOf(d2)));
            }
            this.f8535l = d2;
            this.f8538o = 0;
            return true;
        } catch (Exception e) {
            intValue = this.f8538o;
            this.f8538o = intValue + 1;
            return intValue < 5;
        }
    }
}
