package com.facebook.ads.internal.p056q.p057a;

import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.view.InputDevice;
import android.view.InputDevice.MotionRange;
import android.view.MotionEvent;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

public class C2130s {
    private static final String f5063a = C2130s.class.getSimpleName();
    private boolean f5064b;
    private int f5065c = -1;
    private int f5066d = -1;
    private int f5067e = -1;
    private int f5068f = -1;
    private long f5069g = -1;
    private int f5070h = -1;
    private long f5071i = -1;
    private long f5072j = -1;
    private int f5073k = -1;
    private int f5074l = -1;
    private int f5075m = -1;
    private int f5076n = -1;
    private float f5077o = -1.0f;
    private float f5078p = -1.0f;
    private float f5079q = -1.0f;
    @Nullable
    private View f5080r;
    @Nullable
    private View f5081s;

    private C2118i m6820f() {
        if (this.f5080r == null || this.f5081s == null) {
            return C2118i.INTERNAL_NULL_VIEW;
        }
        if (this.f5080r != this.f5081s) {
            return C2118i.INTERNAL_NO_CLICK;
        }
        if (VERSION.SDK_INT < 4) {
            return C2118i.INTERNAL_API_TOO_LOW;
        }
        Object tag = this.f5080r.getTag(C2118i.f5042o);
        return tag == null ? C2118i.INTERNAL_NO_TAG : !(tag instanceof C2118i) ? C2118i.INTERNAL_WRONG_TAG_CLASS : (C2118i) tag;
    }

    public void m6821a() {
        this.f5069g = System.currentTimeMillis();
    }

    public void m6822a(MotionEvent motionEvent, View view, View view2) {
        if (!this.f5064b) {
            this.f5064b = true;
            InputDevice device = motionEvent.getDevice();
            if (device != null) {
                MotionRange motionRange = device.getMotionRange(0);
                MotionRange motionRange2 = device.getMotionRange(1);
                if (!(motionRange == null || motionRange2 == null)) {
                    this.f5079q = Math.min(motionRange.getRange(), motionRange2.getRange());
                }
            }
            if (this.f5079q <= 0.0f) {
                this.f5079q = (float) Math.min(view.getMeasuredWidth(), view.getMeasuredHeight());
            }
        }
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view2.getLocationInWindow(iArr2);
        switch (motionEvent.getAction()) {
            case 0:
                this.f5065c = (int) (((float) iArr[0]) / C2133v.f5083b);
                this.f5066d = (int) (((float) iArr[1]) / C2133v.f5083b);
                this.f5067e = (int) (((float) view.getWidth()) / C2133v.f5083b);
                this.f5068f = (int) (((float) view.getHeight()) / C2133v.f5083b);
                this.f5070h = 1;
                this.f5071i = System.currentTimeMillis();
                this.f5073k = (int) (((float) ((((int) (motionEvent.getX() + 0.5f)) + iArr2[0]) - iArr[0])) / C2133v.f5083b);
                this.f5074l = (int) (((float) ((iArr2[1] + ((int) (motionEvent.getY() + 0.5f))) - iArr[1])) / C2133v.f5083b);
                this.f5077o = motionEvent.getPressure();
                this.f5078p = motionEvent.getSize();
                this.f5080r = view2;
                return;
            case 1:
            case 3:
                this.f5072j = System.currentTimeMillis();
                this.f5075m = (int) (((float) ((((int) (motionEvent.getX() + 0.5f)) + iArr2[0]) - iArr[0])) / C2133v.f5083b);
                this.f5076n = (int) (((float) ((iArr2[1] + ((int) (motionEvent.getY() + 0.5f))) - iArr[1])) / C2133v.f5083b);
                this.f5081s = view2;
                return;
            case 2:
                this.f5077o -= this.f5077o / ((float) this.f5070h);
                this.f5077o += motionEvent.getPressure() / ((float) this.f5070h);
                this.f5078p -= this.f5078p / ((float) this.f5070h);
                this.f5078p += motionEvent.getSize() / ((float) this.f5070h);
                this.f5070h++;
                return;
            default:
                return;
        }
    }

    public boolean m6823b() {
        return this.f5069g != -1;
    }

    public long m6824c() {
        return m6823b() ? System.currentTimeMillis() - this.f5069g : -1;
    }

    public boolean m6825d() {
        return this.f5064b;
    }

    public Map<String, String> m6826e() {
        if (!this.f5064b) {
            return null;
        }
        String valueOf = String.valueOf((this.f5078p * this.f5079q) / 2.0f);
        long j = (this.f5069g <= 0 || this.f5072j <= this.f5069g) ? -1 : this.f5072j - this.f5069g;
        Map<String, String> hashMap = new HashMap();
        hashMap.put("adPositionX", String.valueOf(this.f5065c));
        hashMap.put("adPositionY", String.valueOf(this.f5066d));
        hashMap.put("width", String.valueOf(this.f5067e));
        hashMap.put("height", String.valueOf(this.f5068f));
        hashMap.put("clickDelayTime", String.valueOf(j));
        hashMap.put("startTime", String.valueOf(this.f5071i));
        hashMap.put("endTime", String.valueOf(this.f5072j));
        hashMap.put("startX", String.valueOf(this.f5073k));
        hashMap.put("startY", String.valueOf(this.f5074l));
        hashMap.put("clickX", String.valueOf(this.f5075m));
        hashMap.put("clickY", String.valueOf(this.f5076n));
        hashMap.put("endX", String.valueOf(this.f5075m));
        hashMap.put("endY", String.valueOf(this.f5076n));
        hashMap.put("force", String.valueOf(this.f5077o));
        hashMap.put("radiusX", valueOf);
        hashMap.put("radiusY", valueOf);
        hashMap.put("clickedViewTag", String.valueOf(m6820f().m6797a()));
        return hashMap;
    }
}
