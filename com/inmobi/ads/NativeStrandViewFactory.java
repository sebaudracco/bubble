package com.inmobi.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.inmobi.ads.NativeStrandAssetStyle.C2932a;
import com.inmobi.ads.NativeStrandAssetStyle.C2933b;
import com.inmobi.ads.NativeStrandAssetStyle.ContentMode;
import com.inmobi.ads.NativeStrandContainerLayout.C2934a;
import com.inmobi.ads.NativeV2ScrollableContainer.TYPE;
import com.inmobi.ads.al.C3007a;
import com.inmobi.ads.as.C3006a;
import com.inmobi.ads.as.C3006a.C3014b;
import com.inmobi.ads.at.C3015a;
import com.inmobi.commons.core.p113a.C3108a;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.rendering.CustomView;
import com.inmobi.rendering.CustomView.SwitchIconType;
import com.squareup.picasso.Callback;
import com.stepleaderdigital.reveal.Reveal;
import cz.msebera.android.httpclient.HttpStatus;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

class NativeStrandViewFactory {
    private static final String f6889a = NativeStrandViewFactory.class.getSimpleName();
    @NonNull
    private static final Map<Class, C2960d> f6890c = new HashMap();
    @Nullable
    private static volatile WeakReference<NativeStrandViewFactory> f6891e;
    private int f6892b;
    @NonNull
    private Map<C2960d, C2948c> f6893d = new HashMap();
    private WeakReference<Context> f6894f;

    private abstract class C2948c {
        @NonNull
        private LinkedList<View> f6855a = new LinkedList();
        final /* synthetic */ NativeStrandViewFactory f6856b;
        private int f6857c = 0;
        private int f6858d = 0;

        protected abstract View mo6133a(@NonNull Context context);

        protected void mo6134a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
            view.setVisibility(nativeV2Asset.m9016k().getId().intValue());
            view.setOnClickListener(null);
        }

        public C2948c(NativeStrandViewFactory nativeStrandViewFactory) {
            this.f6856b = nativeStrandViewFactory;
        }

        public boolean mo6135a(@NonNull View view) {
            NativeStrandViewFactory.m8982d(view);
            view.setOnClickListener(null);
            this.f6855a.addLast(view);
            this.f6856b.f6892b = this.f6856b.f6892b + 1;
            return true;
        }

        public View m8921a(@NonNull Context context, NativeV2Asset nativeV2Asset) {
            View a;
            this.f6856b.f6894f = new WeakReference(context);
            if (this.f6855a.isEmpty()) {
                this.f6857c++;
                a = mo6133a(context);
            } else {
                this.f6858d++;
                a = (View) this.f6855a.removeFirst();
                this.f6856b.f6892b = this.f6856b.f6892b - 1;
            }
            mo6134a(a, nativeV2Asset);
            return a;
        }

        public int m8919a() {
            return this.f6855a.size();
        }

        public void m8924b() {
            if (this.f6855a.size() > 0) {
                this.f6855a.removeFirst();
            }
        }

        public String toString() {
            return "Size:" + this.f6855a.size() + " Miss Count:" + this.f6857c + " Hit Count:" + this.f6858d;
        }
    }

    class C29491 extends C2948c {
        final /* synthetic */ NativeStrandViewFactory f6861a;

        C29491(NativeStrandViewFactory nativeStrandViewFactory) {
            this.f6861a = nativeStrandViewFactory;
            super(nativeStrandViewFactory);
        }

        protected View mo6133a(@NonNull Context context) {
            return new ag(context.getApplicationContext());
        }

        protected void mo6134a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
            super.mo6134a(view, nativeV2Asset);
            NativeStrandViewFactory.m8960a(view, nativeV2Asset.m9001b());
        }
    }

    class C29524 extends C2948c {
        final /* synthetic */ NativeStrandViewFactory f6868a;

        C29524(NativeStrandViewFactory nativeStrandViewFactory) {
            this.f6868a = nativeStrandViewFactory;
            super(nativeStrandViewFactory);
        }

        protected View mo6133a(@NonNull Context context) {
            return new NativeStrandContainerLayout(context.getApplicationContext());
        }

        protected void mo6134a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
            super.mo6134a(view, nativeV2Asset);
            NativeStrandViewFactory.m8960a(view, nativeV2Asset.m9001b());
        }
    }

    class C29535 extends C2948c {
        final /* synthetic */ NativeStrandViewFactory f6869a;

        C29535(NativeStrandViewFactory nativeStrandViewFactory) {
            this.f6869a = nativeStrandViewFactory;
            super(nativeStrandViewFactory);
        }

        protected View mo6133a(@NonNull Context context) {
            return new bh(context.getApplicationContext(), TYPE.TYPE_PAGED);
        }

        protected void mo6134a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
            super.mo6134a(view, nativeV2Asset);
            NativeStrandViewFactory.m8960a(view, nativeV2Asset.m9001b());
        }

        public boolean mo6135a(@NonNull View view) {
            ((bh) view).m9516a();
            return super.mo6135a(view);
        }
    }

    class C29546 extends C2948c {
        final /* synthetic */ NativeStrandViewFactory f6870a;

        C29546(NativeStrandViewFactory nativeStrandViewFactory) {
            this.f6870a = nativeStrandViewFactory;
            super(nativeStrandViewFactory);
        }

        protected View mo6133a(@NonNull Context context) {
            return new bg(context.getApplicationContext(), TYPE.TYPE_FREE);
        }

        protected void mo6134a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
            super.mo6134a(view, nativeV2Asset);
            NativeStrandViewFactory.m8960a(view, nativeV2Asset.m9001b());
        }

        public boolean mo6135a(@NonNull View view) {
            return super.mo6135a(view);
        }
    }

    class C29557 extends C2948c {
        final /* synthetic */ NativeStrandViewFactory f6871a;

        C29557(NativeStrandViewFactory nativeStrandViewFactory) {
            this.f6871a = nativeStrandViewFactory;
            super(nativeStrandViewFactory);
        }

        protected View mo6133a(@NonNull Context context) {
            return new ImageView(context.getApplicationContext());
        }

        protected void mo6134a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
            super.mo6134a(view, nativeV2Asset);
            this.f6871a.m8962a((ImageView) view, nativeV2Asset);
        }

        public boolean mo6135a(@NonNull View view) {
            if (!(view instanceof ImageView)) {
                return false;
            }
            ((ImageView) view).setImageDrawable(null);
            return super.mo6135a(view);
        }
    }

    class C29568 extends C2948c {
        final /* synthetic */ NativeStrandViewFactory f6872a;

        C29568(NativeStrandViewFactory nativeStrandViewFactory) {
            this.f6872a = nativeStrandViewFactory;
            super(nativeStrandViewFactory);
        }

        protected View mo6133a(@NonNull Context context) {
            return new NativeStrandVideoWrapper(context.getApplicationContext());
        }

        protected void mo6134a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
            super.mo6134a(view, nativeV2Asset);
            this.f6872a.m8968a((NativeStrandVideoWrapper) view, nativeV2Asset);
        }

        @TargetApi(15)
        public boolean mo6135a(@NonNull View view) {
            if (!(view instanceof NativeStrandVideoWrapper)) {
                return false;
            }
            ((NativeStrandVideoWrapper) view).getProgressBar().setVisibility(8);
            ((NativeStrandVideoWrapper) view).getPoster().setImageBitmap(null);
            ((NativeStrandVideoWrapper) view).getVideoView().m8908a();
            return super.mo6135a(view);
        }
    }

    class C29579 extends C2948c {
        final /* synthetic */ NativeStrandViewFactory f6873a;

        C29579(NativeStrandViewFactory nativeStrandViewFactory) {
            this.f6873a = nativeStrandViewFactory;
            super(nativeStrandViewFactory);
        }

        protected View mo6133a(@NonNull Context context) {
            return new C2959b(context);
        }

        protected void mo6134a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
            super.mo6134a(view, nativeV2Asset);
            this.f6873a.m8964a((TextView) view, nativeV2Asset);
        }

        public boolean mo6135a(@NonNull View view) {
            if (!(view instanceof TextView)) {
                return false;
            }
            NativeStrandViewFactory.m8979b((TextView) view);
            return super.mo6135a(view);
        }
    }

    static class PicassoCallback implements Callback {
        private WeakReference<Context> f6874a;
        private WeakReference<ImageView> f6875b;
        private NativeV2Asset f6876c;

        PicassoCallback(Context context, ImageView imageView, NativeV2Asset nativeV2Asset) {
            this.f6874a = new WeakReference(context);
            this.f6875b = new WeakReference(imageView);
            this.f6876c = nativeV2Asset;
        }

        public void onSuccess() {
        }

        public void onError() {
            NativeStrandViewFactory.m8977b((Context) this.f6874a.get(), (ImageView) this.f6875b.get(), this.f6876c);
        }
    }

    private static final class C2958a implements Runnable {
        private WeakReference<Context> f6877a;
        private WeakReference<ImageView> f6878b;

        C2958a(Context context, ImageView imageView) {
            this.f6877a = new WeakReference(context);
            this.f6878b = new WeakReference(imageView);
        }

        public void run() {
            Context context = (Context) this.f6877a.get();
            ImageView imageView = (ImageView) this.f6878b.get();
            if (context != null && imageView != null) {
                NativeStrandViewFactory.m8976b(context, imageView);
            }
        }
    }

    private static final class C2959b extends TextView {
        public C2959b(Context context) {
            super(context);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            int lineHeight = i2 / getLineHeight();
            if (lineHeight > 0) {
                setSingleLine(false);
                setLines(lineHeight);
            }
            if (lineHeight == 1) {
                setSingleLine();
            }
        }
    }

    private enum C2960d {
        ROOT_CONTAINER,
        SCROLLABLE_DECK_HORIZONTAL_PAGED,
        SCROLLABLE_DECK_HORIZONTAL_FREE,
        CONTAINER,
        TEXT,
        BUTTON,
        IMAGE,
        VIDEO,
        TIMER
    }

    static {
        f6890c.put(ag.class, C2960d.ROOT_CONTAINER);
        f6890c.put(bh.class, C2960d.SCROLLABLE_DECK_HORIZONTAL_PAGED);
        f6890c.put(bg.class, C2960d.SCROLLABLE_DECK_HORIZONTAL_FREE);
        f6890c.put(NativeStrandContainerLayout.class, C2960d.CONTAINER);
        f6890c.put(ImageView.class, C2960d.IMAGE);
        f6890c.put(NativeStrandVideoWrapper.class, C2960d.VIDEO);
        f6890c.put(C2959b.class, C2960d.TEXT);
        f6890c.put(Button.class, C2960d.BUTTON);
        f6890c.put(NativeStrandTimerView.class, C2960d.TIMER);
    }

    private NativeStrandViewFactory() {
        this.f6893d.put(C2960d.ROOT_CONTAINER, new C29491(this));
        this.f6893d.put(C2960d.CONTAINER, new C29524(this));
        this.f6893d.put(C2960d.SCROLLABLE_DECK_HORIZONTAL_PAGED, new C29535(this));
        this.f6893d.put(C2960d.SCROLLABLE_DECK_HORIZONTAL_FREE, new C29546(this));
        this.f6893d.put(C2960d.IMAGE, new C29557(this));
        this.f6893d.put(C2960d.VIDEO, new C29568(this));
        this.f6893d.put(C2960d.TEXT, new C29579(this));
        this.f6893d.put(C2960d.BUTTON, new C2948c(this) {
            final /* synthetic */ NativeStrandViewFactory f6859a;

            {
                this.f6859a = r1;
            }

            protected View mo6133a(@NonNull Context context) {
                return new Button(context.getApplicationContext());
            }

            protected void mo6134a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
                super.mo6134a(view, nativeV2Asset);
                NativeStrandViewFactory.m8974b((Button) view, nativeV2Asset);
            }

            public boolean mo6135a(@NonNull View view) {
                if (!(view instanceof Button)) {
                    return false;
                }
                NativeStrandViewFactory.m8979b((Button) view);
                return super.mo6135a(view);
            }
        });
        this.f6893d.put(C2960d.TIMER, new C2948c(this) {
            final /* synthetic */ NativeStrandViewFactory f6860a;

            {
                this.f6860a = r1;
            }

            protected View mo6133a(@NonNull Context context) {
                return new NativeStrandTimerView(context.getApplicationContext());
            }

            protected void mo6134a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
                super.mo6134a(view, nativeV2Asset);
                this.f6860a.m8967a((NativeStrandTimerView) view, nativeV2Asset);
            }

            public boolean mo6135a(@NonNull View view) {
                if (view instanceof NativeStrandTimerView) {
                    return super.mo6135a(view);
                }
                return false;
            }
        });
    }

    public static NativeStrandViewFactory m8955a(@NonNull Context context) {
        NativeStrandViewFactory nativeStrandViewFactory;
        if (f6891e == null) {
            nativeStrandViewFactory = null;
        } else {
            nativeStrandViewFactory = (NativeStrandViewFactory) f6891e.get();
        }
        if (nativeStrandViewFactory == null) {
            synchronized (NativeStrandViewFactory.class) {
                nativeStrandViewFactory = f6891e == null ? null : (NativeStrandViewFactory) f6891e.get();
                if (nativeStrandViewFactory == null) {
                    nativeStrandViewFactory = new NativeStrandViewFactory();
                    f6891e = new WeakReference(nativeStrandViewFactory);
                }
            }
        }
        return nativeStrandViewFactory;
    }

    @Nullable
    public View m8983a(@NonNull Context context, @NonNull NativeV2Asset nativeV2Asset) {
        C2960d a = m8954a(nativeV2Asset);
        if (a != null) {
            return ((C2948c) this.f6893d.get(a)).m8921a(context, nativeV2Asset);
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "Unsupported AssetType:" + nativeV2Asset.m8986a().toString() + " failed to instantiate view ");
        return null;
    }

    public void m8985a(@NonNull ViewGroup viewGroup) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            viewGroup.removeViewAt(childCount);
            m8984a(childAt);
        }
    }

    public void m8984a(@NonNull View view) {
        if ((view instanceof ag) || (view instanceof NativeStrandContainerLayout)) {
            NativeStrandContainerLayout nativeStrandContainerLayout = (NativeStrandContainerLayout) view;
            if (nativeStrandContainerLayout.getChildCount() == 0) {
                m8981c(view);
                return;
            }
            Stack stack = new Stack();
            stack.push(nativeStrandContainerLayout);
            while (!stack.isEmpty()) {
                View view2 = (NativeStrandContainerLayout) stack.pop();
                for (int childCount = view2.getChildCount() - 1; childCount >= 0; childCount--) {
                    View childAt = view2.getChildAt(childCount);
                    view2.removeViewAt(childCount);
                    if (childAt instanceof NativeStrandContainerLayout) {
                        stack.push((NativeStrandContainerLayout) childAt);
                    } else {
                        m8981c(childAt);
                    }
                }
                m8981c(view2);
            }
            return;
        }
        m8981c(view);
    }

    @Nullable
    private C2960d m8954a(NativeV2Asset nativeV2Asset) {
        if (nativeV2Asset instanceof ak) {
            ak akVar = (ak) nativeV2Asset;
            if (akVar.m9359B()) {
                return C2960d.ROOT_CONTAINER;
            }
            if (!akVar.m9360C()) {
                return C2960d.CONTAINER;
            }
            switch (akVar.m9365z()) {
                case CARD_SCROLLABLE_TYPE_FREE:
                    return C2960d.SCROLLABLE_DECK_HORIZONTAL_FREE;
                default:
                    return C2960d.SCROLLABLE_DECK_HORIZONTAL_PAGED;
            }
        }
        switch (nativeV2Asset.m8986a()) {
            case ASSET_TYPE_TEXT:
                return C2960d.TEXT;
            case ASSET_TYPE_IMAGE:
            case ASSET_TYPE_ICON:
                return C2960d.IMAGE;
            case ASSET_TYPE_VIDEO:
                return C2960d.VIDEO;
            case ASSET_TYPE_CTA:
                return C2960d.BUTTON;
            case ASSET_TYPE_TIMER:
                return C2960d.TIMER;
            default:
                return null;
        }
    }

    private void m8981c(@NonNull View view) {
        C2960d c2960d = (C2960d) f6890c.get(view.getClass());
        if (c2960d == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "View type unknown, ignoring recycle:" + view);
            return;
        }
        C2948c c2948c = (C2948c) this.f6893d.get(c2960d);
        if (c2948c == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "Unsupported AssetType:" + c2960d + " failed to recycle view");
            return;
        }
        if (this.f6892b >= HttpStatus.SC_MULTIPLE_CHOICES) {
            m8957a();
        }
        c2948c.mo6135a(view);
    }

    private void m8957a() {
        C2948c b = m8975b();
        if (b != null) {
            b.m8924b();
        }
    }

    @Nullable
    private C2948c m8975b() {
        int i = 0;
        C2948c c2948c = null;
        for (Entry entry : this.f6893d.entrySet()) {
            C2948c c2948c2;
            int a;
            if (((C2948c) entry.getValue()).m8919a() > i) {
                c2948c2 = (C2948c) entry.getValue();
                a = c2948c2.m8919a();
            } else {
                c2948c2 = c2948c;
                a = i;
            }
            c2948c = c2948c2;
            i = a;
        }
        return c2948c;
    }

    private void m8967a(final NativeStrandTimerView nativeStrandTimerView, NativeV2Asset nativeV2Asset) {
        long a;
        nativeStrandTimerView.setVisibility(4);
        at atVar = (at) nativeV2Asset;
        C3015a z = atVar.m9403z();
        C3015a A = atVar.m9400A();
        if (z != null) {
            try {
                long a2 = z.m9399a();
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "Error while converting timer duration to long");
                C3135c.m10255a().m10279a(new C3132b(e));
                return;
            }
        }
        a2 = 0;
        if (A != null) {
            a = A.m9399a();
        } else {
            a = 0;
        }
        if (a > 0 && atVar.m9402y()) {
            nativeStrandTimerView.setTimerValue(a);
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ NativeStrandViewFactory f6863b;

                public void run() {
                    if (this.f6863b.f6894f.get() != null) {
                        nativeStrandTimerView.setVisibility(0);
                        nativeStrandTimerView.m8862a();
                    }
                }
            }, 1000 * a2);
        }
    }

    private void m8962a(@NonNull ImageView imageView, @NonNull NativeV2Asset nativeV2Asset) {
        String str = (String) nativeV2Asset.m9007d();
        if (str != null) {
            int i = nativeV2Asset.m9001b().m8849a().x;
            int i2 = nativeV2Asset.m9001b().m8849a().y;
            ContentMode h = nativeV2Asset.m9001b().m8856h();
            if (h == ContentMode.CONTENT_MODE_ASPECT_FIT) {
                imageView.setScaleType(ScaleType.FIT_CENTER);
            } else if (h == ContentMode.CONTENT_MODE_ASPECT_FILL) {
                imageView.setScaleType(ScaleType.CENTER_CROP);
            } else {
                imageView.setScaleType(ScaleType.FIT_XY);
            }
            Context context = (Context) this.f6894f.get();
            if (context != null && i > 0 && i2 > 0 && str.trim().length() != 0) {
                C3108a.m10103a(context).load(str).into(imageView, new PicassoCallback(context, imageView, nativeV2Asset));
                if ("cross_button".equalsIgnoreCase(nativeV2Asset.m9005c()) && nativeV2Asset.m9023r().length() == 0) {
                    new Handler().postDelayed(new C2958a(context, imageView), Reveal.CHECK_DELAY);
                }
            }
            m8961a((View) imageView, nativeV2Asset);
            m8960a((View) imageView, nativeV2Asset.m9001b());
        }
    }

    private static Bitmap m8951a(View view, float f) {
        view.layout(0, 0, (int) (40.0f * f), (int) (40.0f * f));
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    private static void m8976b(Context context, ImageView imageView) {
        if (imageView.getDrawable() == null) {
            float c = DisplayInfo.m10420a().m10440c();
            imageView.setImageBitmap(m8951a(new CustomView(context, c, SwitchIconType.CLOSE_BUTTON), c));
        }
    }

    private static void m8977b(Context context, ImageView imageView, NativeV2Asset nativeV2Asset) {
        nativeV2Asset.m9022q();
        String r = nativeV2Asset.m9023r();
        String c = nativeV2Asset.m9005c();
        if (context != null && imageView != null && "cross_button".equalsIgnoreCase(c) && r.trim().length() == 0) {
            m8976b(context, imageView);
        }
    }

    @TargetApi(15)
    private void m8968a(@NonNull NativeStrandVideoWrapper nativeStrandVideoWrapper, NativeV2Asset nativeV2Asset) {
        if (VERSION.SDK_INT >= 15) {
            m8960a((View) nativeStrandVideoWrapper, nativeV2Asset.m9001b());
            if (nativeV2Asset.m9028w() != null) {
                nativeStrandVideoWrapper.setPosterImage((Bitmap) nativeV2Asset.m9028w());
            }
            nativeStrandVideoWrapper.getProgressBar().setVisibility(0);
        }
    }

    @TargetApi(17)
    private void m8964a(@NonNull TextView textView, NativeV2Asset nativeV2Asset) {
        NativeStrandAssetStyle nativeStrandAssetStyle = (C3006a) nativeV2Asset.m9001b();
        textView.setLayoutParams(new LayoutParams(nativeStrandAssetStyle.m8849a().x, nativeStrandAssetStyle.m8849a().y));
        textView.setText((CharSequence) nativeV2Asset.m9007d());
        textView.setTypeface(Typeface.DEFAULT);
        m8965a(textView, (C3006a) nativeStrandAssetStyle);
        textView.setTextSize(1, (float) nativeStrandAssetStyle.m9367i());
        int parseColor = Color.parseColor("#ff000000");
        try {
            parseColor = Color.parseColor(nativeStrandAssetStyle.m9368j());
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "Error parsing color for text asset! Will fallback to default text color");
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        textView.setTextColor(parseColor);
        parseColor = Color.parseColor("#00000000");
        try {
            parseColor = Color.parseColor(nativeStrandAssetStyle.mo6187g());
        } catch (Throwable e2) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "Error parsing background color for text asset! Will fallback to default background color");
            C3135c.m10255a().m10279a(new C3132b(e2));
        }
        textView.setBackgroundColor(parseColor);
        if (VERSION.SDK_INT >= 17) {
            textView.setTextAlignment(1);
        }
        m8966a(textView, nativeStrandAssetStyle.m9369k());
        textView.setEllipsize(TruncateAt.END);
        textView.setHorizontallyScrolling(true);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        m8960a((View) textView, nativeStrandAssetStyle);
    }

    private void m8965a(TextView textView, C3006a c3006a) {
        switch (c3006a.m9370l()) {
            case TEXT_RIGHT_ALIGNED:
                textView.setGravity(8388629);
                return;
            case TEXT_CENTER_ALIGNED:
                textView.setGravity(17);
                return;
            default:
                textView.setGravity(8388627);
                return;
        }
    }

    private static void m8966a(@NonNull TextView textView, C3014b[] c3014bArr) {
        int paintFlags = textView.getPaintFlags();
        int i = paintFlags;
        paintFlags = 0;
        int i2 = i;
        for (C3014b c3014b : c3014bArr) {
            switch (c3014b) {
                case TEXT_STYLE_BOLD:
                    paintFlags |= 1;
                    break;
                case TEXT_STYLE_ITALICISED:
                    paintFlags |= 2;
                    break;
                case TEXT_STYLE_STRIKE_THRU:
                    i2 |= 16;
                    break;
                case TEXT_STYLE_UNDERLINE:
                    i2 |= 8;
                    break;
                default:
                    break;
            }
        }
        textView.setTypeface(Typeface.DEFAULT, paintFlags);
        textView.setPaintFlags(i2);
    }

    @TargetApi(17)
    private static Button m8974b(@NonNull Button button, @NonNull NativeV2Asset nativeV2Asset) {
        NativeStrandAssetStyle nativeStrandAssetStyle = (C3007a) nativeV2Asset.m9001b();
        button.setLayoutParams(new LayoutParams(nativeStrandAssetStyle.m8849a().x, nativeStrandAssetStyle.m8849a().y));
        button.setText((CharSequence) nativeV2Asset.m9007d());
        button.setTextSize(1, (float) nativeStrandAssetStyle.m9367i());
        int parseColor = Color.parseColor("#ff000000");
        try {
            parseColor = Color.parseColor(nativeStrandAssetStyle.m9368j());
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "Error parsing color for CTA asset! Will fallback to default text color");
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        button.setTextColor(parseColor);
        parseColor = Color.parseColor("#00000000");
        try {
            parseColor = Color.parseColor(nativeStrandAssetStyle.mo6187g());
        } catch (Throwable e2) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "Error parsing background color for CTA asset! Will fallback to default background color");
            C3135c.m10255a().m10279a(new C3132b(e2));
        }
        button.setBackgroundColor(parseColor);
        if (VERSION.SDK_INT >= 17) {
            button.setTextAlignment(4);
        }
        button.setGravity(17);
        m8966a((TextView) button, nativeStrandAssetStyle.m9369k());
        m8960a((View) button, nativeStrandAssetStyle);
        return button;
    }

    private void m8961a(@NonNull View view, @NonNull NativeV2Asset nativeV2Asset) {
        int i;
        int i2;
        int i3 = 1;
        NativeV2Asset u = nativeV2Asset.m9026u();
        int i4 = 0;
        if (u == null || C2933b.BORDER_STROKE_STYLE_LINE != u.m9001b().m8851c()) {
            i3 = 0;
            i = 0;
            i2 = 0;
        } else {
            int i5;
            if (u.m9001b().m8850b().x == nativeV2Asset.m9001b().m8850b().x) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (u.m9001b().m8849a().x == nativeV2Asset.m9001b().m8849a().x + nativeV2Asset.m9001b().m8850b().x) {
                i5 = 1;
            } else {
                i5 = 0;
            }
            if (u.m9001b().m8850b().y == nativeV2Asset.m9001b().m8850b().y) {
                i = 1;
            } else {
                i = 0;
            }
            if (u.m9001b().m8849a().y == nativeV2Asset.m9001b().m8849a().y + nativeV2Asset.m9001b().m8850b().y) {
                i4 = 1;
            }
            if (u.m9001b().m8849a().x == nativeV2Asset.m9001b().m8849a().x) {
                i2 = 1;
            } else {
                i3 = i5;
            }
        }
        if (VERSION.SDK_INT < 17) {
            view.setPadding(i2, i, i3, i4);
        } else {
            view.setPaddingRelative(i2, i, i3, i4);
        }
    }

    @TargetApi(21)
    static void m8960a(View view, @NonNull NativeStrandAssetStyle nativeStrandAssetStyle) {
        int parseColor = Color.parseColor("#00000000");
        try {
            parseColor = Color.parseColor(nativeStrandAssetStyle.mo6187g());
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "Error parsing background color for container! Will fallback to default background color");
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        view.setBackgroundColor(parseColor);
        if (C2933b.BORDER_STROKE_STYLE_LINE == nativeStrandAssetStyle.m8851c()) {
            Drawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(parseColor);
            if (C2932a.BORDER_CORNER_STYLE_CURVED == nativeStrandAssetStyle.m8852d()) {
                gradientDrawable.setCornerRadius(nativeStrandAssetStyle.m8853e());
            }
            parseColor = Color.parseColor("#ff000000");
            try {
                parseColor = Color.parseColor(nativeStrandAssetStyle.m8854f());
            } catch (Throwable e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "Error parsing border color for container! Will fallback to default border color");
                C3135c.m10255a().m10279a(new C3132b(e2));
            }
            gradientDrawable.setStroke(1, parseColor);
            if (VERSION.SDK_INT < 16) {
                view.setBackgroundDrawable(gradientDrawable);
            } else {
                view.setBackground(gradientDrawable);
            }
        }
    }

    private static void m8979b(@NonNull TextView textView) {
        textView.setTypeface(Typeface.DEFAULT, 0);
        textView.setPaintFlags(textView.getPaintFlags() & -17);
        textView.setPaintFlags(textView.getPaintFlags() & -9);
    }

    private static void m8982d(@NonNull View view) {
        if (VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(null);
        } else {
            view.setBackground(null);
        }
    }

    public static LayoutParams m8952a(@NonNull NativeV2Asset nativeV2Asset, @NonNull ViewGroup viewGroup) {
        Point a = nativeV2Asset.m9001b().m8849a();
        Point b = nativeV2Asset.m9001b().m8850b();
        LayoutParams layoutParams = new LayoutParams(a.x, a.y);
        if (viewGroup instanceof NativeStrandContainerLayout) {
            layoutParams = new C2934a(a.x, a.y);
            ((C2934a) layoutParams).m8857a(b.x, b.y);
            return layoutParams;
        } else if (viewGroup instanceof LinearLayout) {
            layoutParams = new LinearLayout.LayoutParams(a.x, a.y);
            ((LinearLayout.LayoutParams) layoutParams).setMargins(b.x, b.y, 0, 0);
            return layoutParams;
        } else if (viewGroup instanceof AbsListView) {
            return new AbsListView.LayoutParams(a.x, a.y);
        } else {
            if (viewGroup instanceof FrameLayout) {
                layoutParams = new FrameLayout.LayoutParams(a.x, a.y);
                ((FrameLayout.LayoutParams) layoutParams).setMargins(b.x, b.y, 0, 0);
                return layoutParams;
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f6889a, "Could not set layout params for Parent:" + viewGroup.getClass().getSimpleName());
            return layoutParams;
        }
    }
}
