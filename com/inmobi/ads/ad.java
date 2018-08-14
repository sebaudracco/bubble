package com.inmobi.ads;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.HashMap;
import java.util.Map;

/* compiled from: NativeStrandDataSource */
final class ad extends PagerAdapter implements aq {
    private static final String f7030a = ad.class.getSimpleName();
    private static Handler f7031e = new Handler(Looper.getMainLooper());
    private boolean f7032b;
    @NonNull
    private final NativeV2DataModel f7033c;
    private ae f7034d;
    @NonNull
    private Map<Integer, Runnable> f7035f = new HashMap();

    ad(@NonNull NativeV2DataModel nativeV2DataModel, @NonNull ae aeVar) {
        this.f7033c = nativeV2DataModel;
        this.f7034d = aeVar;
    }

    public int getItemPosition(Object obj) {
        Object tag = obj == null ? null : ((View) obj).getTag();
        if (tag == null || !(tag instanceof Integer)) {
            return -2;
        }
        return ((Integer) tag).intValue();
    }

    public int getCount() {
        return this.f7033c.m9090j();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }

    @TargetApi(21)
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7030a, "Inflating card at index:" + i);
        NativeV2Asset b = this.f7033c.m9080b(i);
        if (b == null) {
            return null;
        }
        Object a = m9248a(i, viewGroup, b);
        a.setLayoutParams(NativeStrandViewFactory.m8952a(b, viewGroup));
        a.setTag(Integer.valueOf(i));
        viewGroup.addView(a);
        return a;
    }

    public void destroyItem(ViewGroup viewGroup, int i, final Object obj) {
        viewGroup.removeView((View) obj);
        Runnable runnable = (Runnable) this.f7035f.get(Integer.valueOf(i));
        if (runnable != null) {
            f7031e.removeCallbacks(runnable);
            Logger.m10359a(InternalLogLevel.INTERNAL, NativeStrandViewFactory.class.getSimpleName(), "Cleared pending task at position:" + i);
        }
        f7031e.post(new Runnable(this) {
            final /* synthetic */ ad f7024b;

            public void run() {
                this.f7024b.f7034d.m9273a((View) obj);
            }
        });
    }

    public ViewGroup m9248a(int i, @NonNull ViewGroup viewGroup, @NonNull ak akVar) {
        final ViewGroup a = this.f7034d.m9271a(viewGroup, akVar);
        int abs = Math.abs(this.f7034d.m9275b() - i);
        final int i2 = i;
        final ViewGroup viewGroup2 = viewGroup;
        final ak akVar2 = akVar;
        Runnable c29872 = new Runnable(this) {
            final /* synthetic */ ad f7029e;

            public void run() {
                if (!this.f7029e.f7032b) {
                    this.f7029e.f7035f.remove(Integer.valueOf(i2));
                    this.f7029e.f7034d.m9270a(a, viewGroup2, akVar2);
                }
            }
        };
        this.f7035f.put(Integer.valueOf(i), c29872);
        f7031e.postDelayed(c29872, (long) (abs * 50));
        return a;
    }

    public void destroy() {
        this.f7032b = true;
        for (Runnable removeCallbacks : this.f7035f.values()) {
            f7031e.removeCallbacks(removeCallbacks);
        }
        this.f7035f.clear();
    }
}
