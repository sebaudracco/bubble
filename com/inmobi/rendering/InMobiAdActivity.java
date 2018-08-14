package com.inmobi.rendering;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.inmobi.ads.AdContainer;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.AdUnit.AdMarkupType;
import com.inmobi.ads.C3046c;
import com.inmobi.ads.C3046c.C3040d;
import com.inmobi.ads.C3046c.C3042f;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.NativeStrandVideoView;
import com.inmobi.ads.NativeStrandVideoWrapper;
import com.inmobi.ads.NativeV2DataModel;
import com.inmobi.ads.ViewableAd;
import com.inmobi.ads.au;
import com.inmobi.ads.aw;
import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3152a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.rendering.CustomView.SwitchIconType;
import com.inmobi.rendering.RenderView.C2912a;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"ClickableViewAccessibility"})
@TargetApi(15)
public class InMobiAdActivity extends Activity {
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, C3178a> f7860a = new HashMap();
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, Intent> f7861b = new HashMap();
    public static Integer f7862c = Integer.valueOf(0);
    @SuppressLint({"UseSparseArrays"})
    public static Map<Integer, C3179b> f7863d = new HashMap();
    public static Integer f7864e = Integer.valueOf(0);
    private static final String f7865f = InMobiAdActivity.class.getSimpleName();
    private static Map<Integer, AdContainer> f7866g = new HashMap();
    private static RenderView f7867h;
    private AdContainer f7868i;
    private RenderView f7869j;
    private CustomView f7870k;
    private CustomView f7871l;
    private NativeStrandVideoView f7872m;
    private int f7873n;
    private int f7874o;
    private boolean f7875p = false;
    private boolean f7876q = false;
    private boolean f7877r = false;

    class C31722 implements OnTouchListener {
        final /* synthetic */ InMobiAdActivity f7854a;

        C31722(InMobiAdActivity inMobiAdActivity) {
            this.f7854a = inMobiAdActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                view.setBackgroundColor(-7829368);
                this.f7854a.f7875p = true;
                this.f7854a.finish();
            } else if (motionEvent.getAction() == 0) {
                view.setBackgroundColor(-16711681);
            }
            return true;
        }
    }

    class C31733 implements OnTouchListener {
        final /* synthetic */ InMobiAdActivity f7855a;

        C31733(InMobiAdActivity inMobiAdActivity) {
            this.f7855a = inMobiAdActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                view.setBackgroundColor(-7829368);
                this.f7855a.f7869j.reload();
            } else if (motionEvent.getAction() == 0) {
                view.setBackgroundColor(-16711681);
            }
            return true;
        }
    }

    class C31744 implements OnTouchListener {
        final /* synthetic */ InMobiAdActivity f7856a;

        C31744(InMobiAdActivity inMobiAdActivity) {
            this.f7856a = inMobiAdActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                view.setBackgroundColor(-7829368);
                if (this.f7856a.f7869j.canGoBack()) {
                    this.f7856a.f7869j.goBack();
                } else {
                    this.f7856a.f7875p = true;
                    this.f7856a.finish();
                }
            } else if (motionEvent.getAction() == 0) {
                view.setBackgroundColor(-16711681);
            }
            return true;
        }
    }

    class C31755 implements OnTouchListener {
        final /* synthetic */ InMobiAdActivity f7857a;

        C31755(InMobiAdActivity inMobiAdActivity) {
            this.f7857a = inMobiAdActivity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                view.setBackgroundColor(-7829368);
                if (this.f7857a.f7869j.canGoForward()) {
                    this.f7857a.f7869j.goForward();
                }
            } else if (motionEvent.getAction() == 0) {
                view.setBackgroundColor(-16711681);
            }
            return true;
        }
    }

    class C31766 implements OnClickListener {
        final /* synthetic */ InMobiAdActivity f7858a;

        C31766(InMobiAdActivity inMobiAdActivity) {
            this.f7858a = inMobiAdActivity;
        }

        public void onClick(View view) {
            this.f7858a.f7875p = true;
            try {
                this.f7858a.f7868i.mo6175b();
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, InMobiAdActivity.f7865f, "Encountered unexpected error in processing close request: " + e.getMessage());
                Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
            }
        }
    }

    class C31777 implements OnClickListener {
        final /* synthetic */ InMobiAdActivity f7859a;

        C31777(InMobiAdActivity inMobiAdActivity) {
            this.f7859a = inMobiAdActivity;
        }

        public void onClick(View view) {
            this.f7859a.f7875p = true;
            try {
                this.f7859a.f7868i.mo6175b();
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, InMobiAdActivity.f7865f, "Encountered unexpected error in processing close request: " + e.getMessage());
                Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
            }
        }
    }

    public interface C3178a {
        void mo6267a(int i, Intent intent);
    }

    public interface C3179b {
        void mo6262a(int i, String[] strArr, int[] iArr);
    }

    public static int m10547a(AdContainer adContainer) {
        int hashCode = adContainer.hashCode();
        f7866g.put(Integer.valueOf(hashCode), adContainer);
        return hashCode;
    }

    public static void m10553a(@NonNull Object obj) {
        if (f7866g.remove(Integer.valueOf(obj.hashCode())) == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "Failed to remove ad container with key:" + obj.hashCode());
        }
    }

    public static void m10552a(RenderView renderView) {
        f7867h = renderView;
    }

    public static int m10546a(Intent intent, C3178a c3178a) {
        Integer num = f7862c;
        f7862c = Integer.valueOf(f7862c.intValue() + 1);
        f7860a.put(f7862c, c3178a);
        f7861b.put(f7862c, intent);
        return f7862c.intValue();
    }

    public static void m10554a(String[] strArr, C3179b c3179b) {
        try {
            if (C3105a.m10076a() && strArr != null && strArr.length != 0) {
                int a = m10548a(c3179b);
                Intent intent = new Intent(C3105a.m10078b(), InMobiAdActivity.class);
                intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 104);
                intent.putExtra("id", a);
                intent.putExtra("permissions", strArr);
                C3105a.m10072a(C3105a.m10078b(), intent);
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "SDK encountered unexpected error while requesting permissions; " + e.getMessage());
        }
    }

    private static int m10548a(C3179b c3179b) {
        Integer num = f7864e;
        f7864e = Integer.valueOf(f7864e.intValue() + 1);
        f7863d.put(f7864e, c3179b);
        return f7864e.intValue();
    }

    protected void onResume() {
        super.onResume();
        if (!this.f7875p) {
            if (100 == this.f7873n) {
                if (!(this.f7869j == null || this.f7869j.getFullScreenEventsListener() == null)) {
                    try {
                        if (!this.f7876q) {
                            this.f7876q = true;
                            this.f7869j.getFullScreenEventsListener().mo6202a(this.f7869j);
                        }
                    } catch (Exception e) {
                    }
                }
            } else if (this.f7874o == 200 && 102 == this.f7873n) {
                if (!(this.f7868i == null || this.f7868i.getFullScreenEventsListener() == null)) {
                    try {
                        if (!this.f7876q) {
                            this.f7876q = true;
                            this.f7868i.getFullScreenEventsListener().mo6202a(null);
                        }
                    } catch (Exception e2) {
                    }
                }
            } else if (201 == this.f7874o && this.f7872m != null) {
                final aw awVar = (aw) this.f7872m.getTag();
                if (awVar != null) {
                    if (this.f7877r) {
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                            final /* synthetic */ InMobiAdActivity f7853b;

                            public void run() {
                                if (this.f7853b.f7868i.getRenderingProperties().m8613a() != PlacementType.PLACEMENT_TYPE_FULLSCREEN || !((Boolean) awVar.m9027v().get("didCompleteQ4")).booleanValue()) {
                                    this.f7853b.f7872m.start();
                                }
                            }
                        }, 50);
                    }
                    if (!(this.f7868i == null || this.f7868i.getFullScreenEventsListener() == null)) {
                        try {
                            if (!this.f7876q) {
                                this.f7876q = true;
                                this.f7868i.getFullScreenEventsListener().mo6202a(awVar);
                            }
                        } catch (Throwable e3) {
                            C3135c.m10255a().m10279a(new C3132b(e3));
                        }
                    }
                }
            }
            this.f7877r = false;
        }
    }

    protected void onStart() {
        super.onStart();
        if (!this.f7875p && 102 == this.f7873n && this.f7868i != null) {
            ViewableAd viewableAd = this.f7868i.getViewableAd();
            if (200 == this.f7874o) {
                if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == this.f7868i.getRenderingProperties().m8613a()) {
                    try {
                        C3045a c3046c = new C3046c();
                        C3121b.m10178a().m10190a(c3046c, null);
                        viewableAd.mo6228a(c3046c.m9728m(), this.f7870k, this.f7871l);
                    } catch (Exception e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "SDK encountered unexpected error in enabling impression tracking on this ad: " + e.getMessage());
                        if (this.f7868i.getFullScreenEventsListener() != null) {
                            this.f7868i.getFullScreenEventsListener().mo6201a();
                        }
                    }
                }
            } else if (201 == this.f7874o) {
                try {
                    C3045a c3046c2 = new C3046c();
                    C3121b.m10178a().m10190a(c3046c2, null);
                    if (viewableAd.mo6249b() != null && (this.f7868i instanceof au)) {
                        aw awVar = (aw) this.f7872m.getTag();
                        if (awVar != null) {
                            C3044h m = c3046c2.m9728m();
                            m.m9696a(awVar.m9444b(m.m9699d()));
                            viewableAd.mo6228a(c3046c2.m9728m(), new View[0]);
                        }
                    }
                } catch (Throwable e2) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "SDK encountered unexpected error in enabling impression tracking on this ad: " + e2.getMessage());
                    if (this.f7868i.getFullScreenEventsListener() != null) {
                        this.f7868i.getFullScreenEventsListener().mo6201a();
                    }
                    C3135c.m10255a().m10279a(new C3132b(e2));
                }
            }
        }
    }

    @TargetApi(23)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f7876q = false;
        this.f7873n = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
        if (this.f7873n == 100) {
            C2912a c2912a;
            C3042f c3042f;
            C3040d c3040d;
            String stringExtra = getIntent().getStringExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL");
            this.f7869j = new RenderView(this, new RenderingProperties(PlacementType.PLACEMENT_TYPE_FULLSCREEN), null, null);
            if (f7867h == null) {
                c2912a = RenderView.f7900a;
            } else {
                c2912a = f7867h.getListener();
            }
            if (f7867h == null) {
                c3042f = new C3042f();
            } else {
                c3042f = f7867h.getRenderingConfig();
            }
            if (f7867h == null) {
                c3040d = new C3040d();
            } else {
                c3040d = f7867h.getMraidConfig();
            }
            this.f7869j.setIsInAppBrowser(true);
            this.f7869j.m10627a(c2912a, c3042f, c3040d);
            m10557b();
            this.f7869j.loadUrl(stringExtra);
            this.f7869j.setFullScreenActivityContext(this);
        } else if (this.f7873n == 102) {
            if (getIntent().hasExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX")) {
                r1 = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", -1);
                this.f7868i = (AdContainer) f7866g.get(Integer.valueOf(r1));
                if (this.f7868i == null) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "Failed to find ad container with key:" + r1);
                    finish();
                    return;
                }
                this.f7874o = getIntent().getIntExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", 0);
                if (this.f7874o == 0) {
                    if (this.f7868i.getFullScreenEventsListener() != null) {
                        this.f7868i.getFullScreenEventsListener().mo6201a();
                    }
                    finish();
                    return;
                }
                if (getIntent().getBooleanExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", false)) {
                    requestWindowFeature(1);
                    getWindow().setFlags(1024, 1024);
                }
                if ((200 != this.f7874o || AdMarkupType.AD_MARKUP_TYPE_INM_HTML == this.f7868i.getMarkupType()) && (201 != this.f7874o || AdMarkupType.AD_MARKUP_TYPE_INM_JSON == this.f7868i.getMarkupType())) {
                    try {
                        this.f7868i.setFullScreenActivityContext(this);
                        m10559c();
                        return;
                    } catch (Throwable e) {
                        this.f7868i.setFullScreenActivityContext(null);
                        if (this.f7868i.getFullScreenEventsListener() != null) {
                            this.f7868i.getFullScreenEventsListener().mo6201a();
                        }
                        finish();
                        C3135c.m10255a().m10279a(new C3132b(e));
                        return;
                    }
                }
                if (this.f7868i.getFullScreenEventsListener() != null) {
                    this.f7868i.getFullScreenEventsListener().mo6201a();
                }
                finish();
            }
        } else if (this.f7873n == 103) {
            r1 = getIntent().getIntExtra("id", -1);
            if (r1 == -1) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "Invalid Request Code Supplied for ACTIVITY_TYPE_FOR_RESULT");
            } else {
                startActivityForResult((Intent) f7861b.get(Integer.valueOf(r1)), r1);
            }
        } else if (this.f7873n == 104) {
            int intExtra = getIntent().getIntExtra("id", -1);
            if (intExtra == -1) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "Invalid Request Code Supplied for ACTIVITY_TYPE_PERMISSIONS_DIALOG");
                return;
            }
            String[] stringArrayExtra = getIntent().getStringArrayExtra("permissions");
            if (stringArrayExtra != null && stringArrayExtra.length > 0 && VERSION.SDK_INT >= 23) {
                C3152a.m10383b();
                requestPermissions(stringArrayExtra, intExtra);
            }
        }
    }

    public void onStop() {
        super.onStop();
        if (!this.f7875p) {
            this.f7877r = true;
            if (this.f7872m != null) {
                this.f7872m.pause();
            }
        }
    }

    protected void onDestroy() {
        if (this.f7875p) {
            if (100 == this.f7873n) {
                if (!(this.f7869j == null || this.f7869j.getFullScreenEventsListener() == null)) {
                    try {
                        this.f7869j.getFullScreenEventsListener().mo6203b(this.f7869j);
                        this.f7869j.destroy();
                        this.f7869j = null;
                    } catch (Exception e) {
                    }
                }
            } else if (!(102 != this.f7873n || this.f7868i == null || this.f7868i.getFullScreenEventsListener() == null)) {
                if (200 == this.f7874o) {
                    try {
                        this.f7868i.getFullScreenEventsListener().mo6203b(null);
                    } catch (Exception e2) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "Encountered unexpected error in onAdScreenDismissed handler: " + e2.getMessage());
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                    }
                } else if (201 == this.f7874o && VERSION.SDK_INT >= 15 && (this.f7868i instanceof au)) {
                    NativeStrandVideoWrapper nativeStrandVideoWrapper = (NativeStrandVideoWrapper) ((au) this.f7868i).getVideoContainerView();
                    if (nativeStrandVideoWrapper != null) {
                        try {
                            this.f7868i.getFullScreenEventsListener().mo6203b((aw) nativeStrandVideoWrapper.getVideoView().getTag());
                        } catch (Throwable e3) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "Encountered unexpected error in onAdScreenDismissed handler: " + e3.getMessage());
                            Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error while finishing fullscreen view");
                            C3135c.m10255a().m10279a(new C3132b(e3));
                        }
                    }
                }
            }
            if (this.f7868i != null) {
                this.f7868i.destroy();
                this.f7868i = null;
            }
        } else if (!(100 == this.f7873n || 102 != this.f7873n || this.f7868i == null)) {
            if (200 == this.f7874o) {
                RenderView renderView = (RenderView) this.f7868i;
                renderView.setFullScreenActivityContext(null);
                try {
                    renderView.mo6175b();
                } catch (Exception e22) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "Encountered unexpected error in processing close request: " + e22.getMessage());
                    Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                }
            } else if (201 == this.f7874o && VERSION.SDK_INT >= 15) {
                au auVar = (au) this.f7868i;
                if (this.f7872m != null) {
                    aw awVar = (aw) this.f7872m.getTag();
                    if (awVar != null) {
                        if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == auVar.m9330d()) {
                            this.f7872m.m8908a();
                        }
                        if (this.f7868i.getFullScreenEventsListener() != null) {
                            this.f7868i.getFullScreenEventsListener().mo6203b(awVar);
                        }
                    }
                }
            }
            m10553a(this.f7868i);
            this.f7868i.destroy();
            this.f7868i = null;
        }
        super.onDestroy();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f7869j != null) {
            this.f7869j.m10658m();
        }
    }

    private void m10557b() {
        ViewGroup relativeLayout = new RelativeLayout(this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(10);
        layoutParams.addRule(2, 65533);
        relativeLayout.setBackgroundColor(-1);
        relativeLayout.addView(this.f7869j, layoutParams);
        m10551a(relativeLayout);
        setContentView(relativeLayout);
    }

    private void m10551a(ViewGroup viewGroup) {
        float c = DisplayInfo.m10420a().m10440c();
        View linearLayout = new LinearLayout(this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (48.0f * c));
        linearLayout.setOrientation(0);
        linearLayout.setId(65533);
        linearLayout.setWeightSum(100.0f);
        linearLayout.setBackgroundResource(17301658);
        linearLayout.setBackgroundColor(-7829368);
        layoutParams.addRule(12);
        viewGroup.addView(linearLayout, layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.weight = 25.0f;
        View customView = new CustomView(this, c, SwitchIconType.CLOSE_ICON);
        customView.setOnTouchListener(new C31722(this));
        linearLayout.addView(customView, layoutParams);
        customView = new CustomView(this, c, SwitchIconType.REFRESH);
        customView.setOnTouchListener(new C31733(this));
        linearLayout.addView(customView, layoutParams);
        customView = new CustomView(this, c, SwitchIconType.BACK);
        customView.setOnTouchListener(new C31744(this));
        linearLayout.addView(customView, layoutParams);
        customView = new CustomView(this, c, SwitchIconType.FORWARD_INACTIVE);
        customView.setOnTouchListener(new C31755(this));
        linearLayout.addView(customView, layoutParams);
    }

    private void m10559c() {
        FrameLayout frameLayout = (FrameLayout) findViewById(16908290);
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setId(65534);
        float c = DisplayInfo.m10420a().m10440c();
        if (AdMarkupType.AD_MARKUP_TYPE_INM_HTML == this.f7868i.getMarkupType()) {
            relativeLayout.setBackgroundColor(0);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(10);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams((int) (50.0f * c), (int) (50.0f * c));
            layoutParams2.addRule(11);
            this.f7870k = new CustomView(this, c, SwitchIconType.CLOSE_BUTTON);
            this.f7870k.setId(65532);
            this.f7870k.setOnClickListener(new C31766(this));
            this.f7871l = new CustomView(this, c, SwitchIconType.CLOSE_TRANSPARENT);
            this.f7871l.setId(65531);
            this.f7871l.setOnClickListener(new C31777(this));
            View a = this.f7868i.getViewableAd().mo6225a();
            if (a != null) {
                ViewGroup viewGroup = (ViewGroup) a.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(a);
                }
                relativeLayout.addView(a, layoutParams);
                relativeLayout.addView(this.f7870k, layoutParams2);
                relativeLayout.addView(this.f7871l, layoutParams2);
                ((RenderView) this.f7868i).m10640b(((RenderView) this.f7868i).m10654i());
                ((RenderView) this.f7868i).m10644c(((RenderView) this.f7868i).m10653h());
            }
        } else if (AdMarkupType.AD_MARKUP_TYPE_INM_JSON == this.f7868i.getMarkupType()) {
            PlacementType a2 = this.f7868i.getRenderingProperties().m8613a();
            relativeLayout.setBackgroundColor(-16777216);
            Point a3 = ((NativeV2DataModel) this.f7868i.getDataModel()).m9079b().m9001b().m8849a();
            C3121b.m10178a().m10190a(new C3046c(), null);
            View a4 = this.f7868i.getViewableAd().mo6226a(null, relativeLayout, false);
            NativeStrandVideoWrapper nativeStrandVideoWrapper = (NativeStrandVideoWrapper) this.f7868i.getVideoContainerView();
            if (nativeStrandVideoWrapper != null) {
                this.f7872m = nativeStrandVideoWrapper.getVideoView();
                this.f7872m.requestFocus();
                aw awVar = (aw) this.f7872m.getTag();
                if (awVar.m9014i() != null) {
                    awVar.m9442a((aw) awVar.m9014i());
                }
                if (PlacementType.PLACEMENT_TYPE_INLINE == a2) {
                    awVar.m9027v().put("placementType", PlacementType.PLACEMENT_TYPE_INLINE);
                } else {
                    awVar.m9027v().put("placementType", PlacementType.PLACEMENT_TYPE_FULLSCREEN);
                }
                if (a4 != null) {
                    relativeLayout.addView(a4, new RelativeLayout.LayoutParams(a3.x, a3.y));
                }
            }
            this.f7868i.setRequestedScreenOrientation();
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "Unknown markup type");
            if (this.f7868i.getFullScreenEventsListener() != null) {
                this.f7868i.getFullScreenEventsListener().mo6201a();
            }
            finish();
            return;
        }
        frameLayout.addView(relativeLayout, new RelativeLayout.LayoutParams(-1, -1));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        C3178a c3178a = (C3178a) f7860a.remove(Integer.valueOf(i));
        if (c3178a != null) {
            f7861b.remove(Integer.valueOf(i));
            c3178a.mo6267a(i2, intent);
            this.f7875p = true;
            finish();
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (!z) {
            if (this.f7869j != null) {
                this.f7869j.setOrientationProperties(this.f7869j.getOrientationProperties());
            }
            if (this.f7868i != null) {
                this.f7868i.setRequestedScreenOrientation();
            }
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        C3152a.m10385c();
        C3179b c3179b = (C3179b) f7863d.get(Integer.valueOf(i));
        f7863d.remove(Integer.valueOf(i));
        if (c3179b != null) {
            c3179b.mo6262a(i, strArr, iArr);
        }
        finish();
    }

    public void m10560a(boolean z) {
        this.f7875p = z;
    }

    public void onBackPressed() {
        if (this.f7873n == 102) {
            if (this.f7868i != null && !this.f7868i.mo6176c()) {
                if (200 == this.f7874o) {
                    RenderView renderView = (RenderView) this.f7868i;
                    if (renderView != null) {
                        renderView.m10657l();
                        if (!renderView.m10655j()) {
                            this.f7875p = true;
                            try {
                                renderView.mo6175b();
                                return;
                            } catch (Exception e) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "Encountered unexpected error in processing close request: " + e.getMessage());
                                Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in processing close request");
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                au auVar = (au) this.f7868i;
                if (auVar != null && !auVar.m9339k().m9084d()) {
                    this.f7875p = true;
                    if (this.f7872m != null) {
                        aw awVar = (aw) this.f7872m.getTag();
                        if (awVar != null) {
                            if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == auVar.m9330d()) {
                                this.f7872m.m8908a();
                            }
                            try {
                                if (((Boolean) awVar.m9027v().get("isFullScreen")).booleanValue()) {
                                    awVar.m9027v().put("seekPosition", Integer.valueOf(this.f7872m.getCurrentPosition()));
                                    auVar.m9419a(awVar);
                                    return;
                                }
                                return;
                            } catch (Throwable e2) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f7865f, "Encountered unexpected error in onVideoClosed handler: " + e2.getMessage());
                                Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in closing video");
                                C3135c.m10255a().m10279a(new C3132b(e2));
                                return;
                            }
                        }
                        return;
                    }
                    finish();
                }
            }
        } else if (this.f7873n == 100) {
            this.f7875p = true;
            finish();
        }
    }
}
