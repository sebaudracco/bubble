package com.moat.analytics.mobile.mpub;

import android.support.annotation.CallSuper;
import android.view.View;
import java.util.Map;
import org.json.JSONObject;

abstract class C3426i extends C3414b {
    private int f8716 = C3425c.f8714;
    private int f8717 = Integer.MIN_VALUE;
    private int f8718 = Integer.MIN_VALUE;
    private double f8719 = Double.NaN;
    private int f8720 = Integer.MIN_VALUE;
    private int f8721 = 0;

    class C34245 implements Runnable {
        private /* synthetic */ C3426i f8710;

        C34245(C3426i c3426i) {
            this.f8710 = c3426i;
        }

        public final void run() {
            try {
                if (!this.f8710.mo6520() || this.f8710.m11653()) {
                    this.f8710.m11656();
                } else if (Boolean.valueOf(this.f8710.m11693()).booleanValue()) {
                    this.f8710.ᐝ.postDelayed(this, 200);
                } else {
                    this.f8710.m11656();
                }
            } catch (Exception e) {
                this.f8710.m11656();
                C3442o.m11756(e);
            }
        }
    }

    enum C3425c {
        ;
        
        public static final int f8711 = 0;
        public static final int f8712 = 0;
        public static final int f8713 = 0;
        public static final int f8714 = 0;
        public static final int f8715 = 0;

        static {
            f8714 = 1;
            f8711 = 2;
            f8713 = 3;
            f8712 = 4;
            f8715 = 5;
            int[] iArr = new int[]{1, 2, 3, 4, 5};
        }
    }

    abstract boolean mo6520();

    abstract Integer mo6521();

    abstract boolean mo6522();

    abstract Integer mo6523();

    C3426i(String str) {
        super(str);
    }

    public final boolean mo6518(Map<String, String> map, View view) {
        try {
            boolean ॱ = super.mo6518(map, view);
            if (!ॱ) {
                return ॱ;
            }
            this.ᐝ.postDelayed(new C34245(this), 200);
            return ॱ;
        } catch (Exception e) {
            C3412a.m11642(3, "IntervalVideoTracker", this, "Problem with video loop");
            ॱ("trackVideoAd", e);
            return false;
        }
    }

    public void stopTracking() {
        try {
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_COMPLETE));
            super.stopTracking();
        } catch (Exception e) {
            C3442o.m11756(e);
        }
    }

    public void setPlayerVolume(Double d) {
        super.setPlayerVolume(d);
        this.f8719 = m11651().doubleValue();
    }

    final JSONObject mo6517(MoatAdEvent moatAdEvent) {
        Integer ॱˋ;
        if (moatAdEvent.f8642.equals(MoatAdEvent.f8638)) {
            try {
                ॱˋ = mo6521();
            } catch (Exception e) {
                ॱˋ = Integer.valueOf(this.f8717);
            }
            moatAdEvent.f8642 = ॱˋ;
        } else {
            ॱˋ = moatAdEvent.f8642;
        }
        if (moatAdEvent.f8642.intValue() < 0 || (moatAdEvent.f8642.intValue() == 0 && moatAdEvent.f8643 == MoatAdEventType.AD_EVT_COMPLETE && this.f8717 > 0)) {
            ॱˋ = Integer.valueOf(this.f8717);
            moatAdEvent.f8642 = ॱˋ;
        }
        if (moatAdEvent.f8643 == MoatAdEventType.AD_EVT_COMPLETE) {
            if (ॱˋ.intValue() == Integer.MIN_VALUE || this.f8718 == Integer.MIN_VALUE || !C3414b.m11649(ॱˋ, Integer.valueOf(this.f8718))) {
                this.f8716 = C3425c.f8712;
                moatAdEvent.f8643 = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.f8716 = C3425c.f8715;
            }
        }
        return super.mo6517(moatAdEvent);
    }

    @CallSuper
    final boolean m11693() throws C3442o {
        if (!mo6520() || m11653()) {
            return false;
        }
        int intValue;
        try {
            int intValue2 = mo6521().intValue();
            if (this.f8717 >= 0 && intValue2 < 0) {
                return false;
            }
            this.f8717 = intValue2;
            if (intValue2 == 0) {
                return true;
            }
            MoatAdEventType moatAdEventType;
            Object obj;
            intValue = mo6523().intValue();
            boolean ॱˎ = mo6522();
            double d = ((double) intValue) / 4.0d;
            double doubleValue = m11651().doubleValue();
            if (intValue2 > this.f8720) {
                this.f8720 = intValue2;
            }
            if (this.f8718 == Integer.MIN_VALUE) {
                this.f8718 = intValue;
            }
            if (!ॱˎ) {
                if (this.f8716 != C3425c.f8711) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                    this.f8716 = C3425c.f8711;
                }
                moatAdEventType = null;
            } else if (this.f8716 == C3425c.f8714) {
                moatAdEventType = MoatAdEventType.AD_EVT_START;
                this.f8716 = C3425c.f8713;
            } else if (this.f8716 == C3425c.f8711) {
                moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                this.f8716 = C3425c.f8713;
            } else {
                intValue = ((int) Math.floor(((double) intValue2) / d)) - 1;
                if (intValue >= 0 && intValue < 3) {
                    moatAdEventType = ʻ[intValue];
                    if (!this.ʼ.containsKey(moatAdEventType)) {
                        this.ʼ.put(moatAdEventType, Integer.valueOf(1));
                    }
                }
                moatAdEventType = null;
            }
            if (moatAdEventType != null) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null && !Double.isNaN(this.f8719) && Math.abs(this.f8719 - doubleValue) > 0.05d) {
                moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                obj = 1;
            }
            if (obj != null) {
                dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(intValue2), m11658()));
            }
            this.f8719 = doubleValue;
            this.f8721 = 0;
            return true;
        } catch (Exception e) {
            intValue = this.f8721;
            this.f8721 = intValue + 1;
            if (intValue < 5) {
                return true;
            }
            return false;
        }
    }
}
