package com.facebook.ads.internal.view.p034b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.p055d.C1961c;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p056q.p075b.C2140e;
import java.lang.ref.WeakReference;

public class C2186d extends AsyncTask<String, Void, Bitmap[]> {
    private static final String f5290b = C2186d.class.getSimpleName();
    public boolean f5291a = false;
    private final WeakReference<Context> f5292c;
    private final int f5293d;
    @Nullable
    private final WeakReference<ImageView> f5294e;
    @Nullable
    private final WeakReference<C2183b> f5295f;
    @Nullable
    private final WeakReference<ViewGroup> f5296g;
    private C1897e f5297h;
    private int f5298i = -1;
    private int f5299j = -1;

    public C2186d(ViewGroup viewGroup, int i) {
        this.f5292c = new WeakReference(viewGroup.getContext());
        this.f5295f = null;
        this.f5294e = null;
        this.f5296g = new WeakReference(viewGroup);
        this.f5293d = i;
    }

    public C2186d(ImageView imageView) {
        this.f5292c = new WeakReference(imageView.getContext());
        this.f5295f = null;
        this.f5294e = new WeakReference(imageView);
        this.f5296g = null;
        this.f5293d = 0;
    }

    public C2186d(C2183b c2183b) {
        this.f5292c = new WeakReference(c2183b.getContext());
        this.f5295f = new WeakReference(c2183b);
        this.f5294e = null;
        this.f5296g = null;
        this.f5293d = 0;
    }

    public C2186d m7000a() {
        this.f5298i = -1;
        this.f5299j = -1;
        return this;
    }

    public C2186d m7001a(int i, int i2) {
        this.f5298i = i;
        this.f5299j = i2;
        return this;
    }

    public C2186d m7002a(C1897e c1897e) {
        this.f5297h = c1897e;
        return this;
    }

    public C2186d m7003a(boolean z) {
        this.f5291a = z;
        return this;
    }

    public void m7004a(String str) {
        if (!TextUtils.isEmpty(str)) {
            executeOnExecutor(THREAD_POOL_EXECUTOR, new String[]{str});
        }
    }

    protected void m7005a(Bitmap[] bitmapArr) {
        if (this.f5294e != null) {
            ImageView imageView = (ImageView) this.f5294e.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmapArr[0]);
            }
        }
        if (this.f5295f != null) {
            C2183b c2183b = (C2183b) this.f5295f.get();
            if (c2183b != null) {
                c2183b.m6986a(bitmapArr[0], bitmapArr[1]);
            }
        }
        if (!(this.f5296g == null || this.f5296g.get() == null)) {
            C2133v.m6834a((View) this.f5296g.get(), new BitmapDrawable(((Context) this.f5292c.get()).getResources(), bitmapArr[1]));
        }
        if (this.f5297h != null) {
            this.f5297h.mo3668a();
        }
    }

    protected Bitmap[] m7006a(String... strArr) {
        Throwable th;
        String str = strArr[0];
        Context context = (Context) this.f5292c.get();
        if (context == null) {
            return new Bitmap[]{null, null};
        }
        Bitmap a;
        Bitmap bitmap;
        try {
            a = C1961c.m6174a(context).m6181a(str, this.f5298i, this.f5299j);
            try {
                int i = (this.f5295f == null || this.f5295f.get() == null) ? 0 : 1;
                int i2 = (this.f5296g == null || this.f5296g.get() == null) ? 0 : 1;
                if ((i == 0 && i2 == 0) || a == null) {
                    bitmap = null;
                    return new Bitmap[]{a, bitmap};
                }
                try {
                    if (this.f5291a) {
                        bitmap = a;
                    } else {
                        C2140e c2140e = new C2140e(a);
                        c2140e.m6852a(this.f5293d != 0 ? this.f5293d : Math.round(((float) a.getWidth()) / 40.0f));
                        bitmap = c2140e.m6851a();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bitmap = a;
                    Log.e(f5290b, "Error downloading image: " + str, th);
                    C1999b.m6321a(C1998a.m6318a(th, null));
                    return new Bitmap[]{a, bitmap};
                }
                return new Bitmap[]{a, bitmap};
            } catch (Throwable th22) {
                th = th22;
                bitmap = null;
                Log.e(f5290b, "Error downloading image: " + str, th);
                C1999b.m6321a(C1998a.m6318a(th, null));
                return new Bitmap[]{a, bitmap};
            }
        } catch (Throwable th222) {
            th = th222;
            a = null;
            bitmap = null;
            Log.e(f5290b, "Error downloading image: " + str, th);
            C1999b.m6321a(C1998a.m6318a(th, null));
            return new Bitmap[]{a, bitmap};
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m7006a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m7005a((Bitmap[]) obj);
    }
}
