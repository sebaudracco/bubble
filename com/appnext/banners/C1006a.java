package com.appnext.banners;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;
import com.appnext.ads.C0893a;
import com.appnext.banners.C1006a.11.C09911;
import com.appnext.banners.a.AnonymousClass11;
import com.appnext.banners.a.AnonymousClass16;
import com.appnext.banners.a.AnonymousClass21;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.C0919c.C0914a;
import com.appnext.core.C0921q;
import com.appnext.core.C1123e.C0899a;
import com.appnext.core.C1128g;
import com.appnext.core.C1134l;
import com.appnext.core.C1149r;
import com.appnext.core.C1149r.C0897a;
import com.appnext.core.ECPM;
import com.appnext.core.callbacks.OnECPMLoaded;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.Locale;
import java.util.Random;

public class C1006a extends C1005d {
    private final int TICK = 330;
    private BannerAdRequest adRequest;
    private BannerAd bannerAd;
    private boolean clickEnabled = true;
    private BannerAdData currentAd;
    private int currentPosition = 0;
    private boolean finished = false;
    private int lastProgress = 0;
    private boolean loaded = false;
    private Handler mHandler;
    private MediaPlayer mediaPlayer;
    private boolean reportedImpression = false;
    private C1011g serviceHelper;
    private boolean started = false;
    private String template = "";
    private Runnable tick = new Runnable(this) {
        final /* synthetic */ C1006a fu;

        {
            this.fu = r1;
        }

        public void run() {
            try {
                this.fu.checkProgress();
                this.fu.currentPosition = this.fu.mediaPlayer.getCurrentPosition();
                if (this.fu.mediaPlayer.getCurrentPosition() < this.fu.mediaPlayer.getDuration() && !this.fu.finished) {
                    this.fu.mHandler.postDelayed(this.fu.tick, 330);
                }
            } catch (Throwable th) {
            }
        }
    };
    private C1149r userAction;
    private boolean userMute = true;
    private VideoView videoView;

    class C09931 implements C0897a {
        final /* synthetic */ C1006a fu;

        C09931(C1006a c1006a) {
            this.fu = c1006a;
        }

        public void report(String str) {
            this.fu.report(str);
        }

        public Ad ac() {
            return this.fu.bannerAd;
        }

        public AppnextAd ad() {
            return this.fu.currentAd;
        }

        public C0921q ae() {
            return C1008c.aF();
        }
    }

    class C09952 implements OnClickListener {
        final /* synthetic */ C1006a fu;

        C09952(C1006a c1006a) {
            this.fu = c1006a;
        }

        public void onClick(View view) {
            this.fu.report(C0893a.df);
            this.fu.click();
        }
    }

    class C09963 implements OnClickListener {
        final /* synthetic */ C1006a fu;

        C09963(C1006a c1006a) {
            this.fu = c1006a;
        }

        public void onClick(View view) {
            this.fu.openLink("https://www.appnext.com/privacy_policy/index.html?z=" + new Random().nextInt(10) + this.fu.currentAd.getZoneID() + new Random().nextInt(10));
        }
    }

    class C09974 implements Runnable {
        final /* synthetic */ C1006a fu;

        C09974(C1006a c1006a) {
            this.fu = c1006a;
        }

        public void run() {
            this.fu.userAction.m2398e(this.fu.currentAd);
        }
    }

    class C09985 implements Runnable {
        final /* synthetic */ C1006a fu;

        C09985(C1006a c1006a) {
            this.fu = c1006a;
        }

        public void run() {
            this.fu.userAction.m2395a(this.fu.currentAd, this.fu.currentAd.getAppURL(), null);
        }
    }

    class C09996 implements Runnable {
        final /* synthetic */ C1006a fu;

        C09996(C1006a c1006a) {
            this.fu = c1006a;
        }

        public void run() {
            try {
                C1128g.m2336a("https://www.fqtag.com/pixel.cgi?org=TkBXEI5C3FBIr4zXwnmK&p=" + this.fu.getPlacementId() + "&a=" + this.fu.currentAd.getBannerID() + "&cmp=" + this.fu.currentAd.getCampaignID() + "&fmt=banner&dmn=" + this.fu.currentAd.getAdPackage() + "&ad=&rt=displayImg&gid=" + C1128g.m2358u(this.fu.context) + "&aid=&applng=" + Locale.getDefault().toString() + "&app=" + this.fu.context.getPackageName() + "&c1=100&c2=" + this.fu.bannerAd.getTID() + "&c3=" + this.fu.bannerAd.getAUID() + "&c4=" + this.fu.bannerAd.getVID() + "&sl=1&fq=1", null);
            } catch (Throwable th) {
                C1128g.m2351c(th);
            }
        }
    }

    class C10007 implements C0899a {
        final /* synthetic */ C1006a fu;

        C10007(C1006a c1006a) {
            this.fu = c1006a;
        }

        public void onMarket(String str) {
            try {
                if (this.fu.mediaPlayer != null && this.fu.mediaPlayer.isPlaying()) {
                    this.fu.pause();
                    ((ImageView) this.fu.rootView.findViewById(C0990R.id.media).findViewById(C0990R.id.play)).setImageResource(C0990R.drawable.apnxt_banner_pause);
                    this.fu.rootView.findViewById(C0990R.id.media).findViewById(C0990R.id.play).setVisibility(0);
                }
            } catch (Throwable th) {
            }
        }

        public void error(String str) {
        }
    }

    public void init(ViewGroup viewGroup) {
        super.init(viewGroup);
        this.userAction = new C1149r(this.context, new C09931(this));
        this.mHandler = new Handler();
    }

    protected Ad createAd(Context context, String str) {
        String bannerSize = getBannerSize().toString();
        Object obj = -1;
        switch (bannerSize.hashCode()) {
            case -1966536496:
                if (bannerSize.equals("LARGE_BANNER")) {
                    obj = 1;
                    break;
                }
                break;
            case -96588539:
                if (bannerSize.equals("MEDIUM_RECTANGLE")) {
                    obj = 2;
                    break;
                }
                break;
            case 1951953708:
                if (bannerSize.equals("BANNER")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return new SmallBannerAd(context, str);
            case 1:
                return new LargeBannerAd(context, str);
            case 2:
                return new MediumRectangleAd(context, str);
            default:
                throw new IllegalArgumentException("Wrong banner size " + getBannerSize());
        }
    }

    public void loadAd(BannerAdRequest bannerAdRequest) {
        if (bannerAdRequest == null) {
            throw new IllegalStateException("BannerAdRequest cannot be null.");
        } else if (getPlacementId() == null) {
            throw new IllegalStateException("Missing placement id.");
        } else if (getBannerSize() == null) {
            throw new IllegalStateException("Missing banner size.");
        } else {
            if (this.bannerAd == null) {
                this.bannerAd = (BannerAd) createAd(this.context, getPlacementId());
            }
            this.bannerAd.setCategories(bannerAdRequest.getCategories());
            this.bannerAd.setPostback(bannerAdRequest.getPostback());
            this.adRequest = new BannerAdRequest(bannerAdRequest);
            setClickEnabled(bannerAdRequest.isClickEnabled());
            this.loaded = true;
            this.reportedImpression = false;
            if (C1128g.aP(C1128g.m2361x(this.context)) == 0) {
                this.adRequest.setCreativeType("static");
            }
            C1134l.db().m2371c(Integer.parseInt(C1008c.aF().get("banner_expiration_time")));
            C1007b.aE().m2044a(this.context, this.bannerAd, getPlacementId(), new C0914a(this) {
                final /* synthetic */ C1006a fu;

                {
                    this.fu = r1;
                }

                /* JADX WARNING: inconsistent code. */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public <T> void mo1971a(T r10) {
                    /*
                    r9 = this;
                    r4 = 2;
                    r2 = -1;
                    r3 = 1;
                    r1 = 0;
                    r0 = com.appnext.banners.C1007b.aE();
                    r5 = r9.fu;
                    r5 = r5.context;
                    r6 = r9.fu;
                    r6 = r6.bannerAd;
                    r7 = r9.fu;
                    r7 = r7.adRequest;
                    r7 = r7.getCreativeType();
                    r5 = r0.m2040a(r5, r6, r7);
                    if (r5 != 0) goto L_0x0044;
                L_0x0022:
                    r0 = r9.fu;
                    r1 = "error_no_ads";
                    r0.report(r1);
                    r0 = r9.fu;
                    r0 = r0.getBannerListener();
                    if (r0 == 0) goto L_0x0043;
                L_0x0032:
                    r0 = r9.fu;
                    r0 = r0.getBannerListener();
                    r1 = new com.appnext.core.AppnextError;
                    r2 = "No Ads";
                    r1.<init>(r2);
                    r0.onError(r1);
                L_0x0043:
                    return;
                L_0x0044:
                    r0 = r9.fu;
                    r0 = r0.rootView;
                    if (r0 == 0) goto L_0x0043;
                L_0x004a:
                    r0 = r9.fu;
                    r0 = r0.context;
                    if (r0 != 0) goto L_0x0058;
                L_0x0050:
                    r0 = r9.fu;
                    r0 = r0.rootView;
                    r0.removeAllViews();
                    goto L_0x0043;
                L_0x0058:
                    r0 = r9.fu;
                    r6 = new com.appnext.banners.BannerAdData;
                    r6.<init>(r5);
                    r0.currentAd = r6;
                    r0 = com.appnext.banners.C1008c.aF();
                    r6 = r9.fu;
                    r6 = r6.getBannerSize();
                    r6 = r6.toString();
                    r0 = r0.get(r6);
                    r0 = com.appnext.banners.C1012h.m2055L(r0);
                    r6 = r9.fu;
                    r7 = new java.lang.StringBuilder;
                    r7.<init>();
                    r8 = "apnxt_";
                    r7 = r7.append(r8);
                    r0 = r7.append(r0);
                    r0 = r0.toString();
                    r0 = r0.toLowerCase();
                    r6.template = r0;
                    r0 = r9.fu;
                    r0 = r0.rootView;
                    r0 = r0.getContext();
                    r0 = r0.getResources();
                    r6 = r9.fu;
                    r6 = r6.template;
                    r7 = "layout";
                    r8 = r9.fu;
                    r8 = r8.context;
                    r8 = r8.getPackageName();
                    r0 = r0.getIdentifier(r6, r7, r8);
                    if (r0 != 0) goto L_0x00cf;
                L_0x00b8:
                    r0 = r9.fu;
                    r0 = r0.getBannerSize();
                    r0 = r0.toString();
                    r6 = r0.hashCode();
                    switch(r6) {
                        case -1966536496: goto L_0x011b;
                        case -96588539: goto L_0x0126;
                        case 1951953708: goto L_0x0110;
                        default: goto L_0x00c9;
                    };
                L_0x00c9:
                    r0 = r2;
                L_0x00ca:
                    switch(r0) {
                        case 0: goto L_0x0131;
                        case 1: goto L_0x0134;
                        case 2: goto L_0x0137;
                        default: goto L_0x00cd;
                    };
                L_0x00cd:
                    r0 = com.appnext.banners.C0990R.layout.apnxt_banner_1;
                L_0x00cf:
                    r6 = r9.fu;
                    r6.loaded = r3;
                    r6 = r9.fu;
                    r6.reportedImpression = r1;
                    r6 = r9.fu;
                    r6.inflateView(r0, r5);
                    r0 = r9.fu;
                    r0 = r0.getBannerListener();
                    if (r0 == 0) goto L_0x00f3;
                L_0x00e6:
                    r0 = r9.fu;
                    r0 = r0.getBannerListener();
                    r5 = r5.getBannerID();
                    r0.onAdLoaded(r5);
                L_0x00f3:
                    r0 = r9.fu;
                    r0 = r0.getBannerSize();
                    r0 = r0.toString();
                    r5 = r0.hashCode();
                    switch(r5) {
                        case -1966536496: goto L_0x0145;
                        case -96588539: goto L_0x0150;
                        case 1951953708: goto L_0x013a;
                        default: goto L_0x0104;
                    };
                L_0x0104:
                    r0 = r2;
                L_0x0105:
                    switch(r0) {
                        case 0: goto L_0x015b;
                        case 1: goto L_0x0164;
                        case 2: goto L_0x016d;
                        default: goto L_0x0108;
                    };
                L_0x0108:
                    r0 = r9.fu;
                    r1 = 0;
                    r0.adRequest = r1;
                    goto L_0x0043;
                L_0x0110:
                    r6 = "BANNER";
                    r0 = r0.equals(r6);
                    if (r0 == 0) goto L_0x00c9;
                L_0x0119:
                    r0 = r1;
                    goto L_0x00ca;
                L_0x011b:
                    r6 = "LARGE_BANNER";
                    r0 = r0.equals(r6);
                    if (r0 == 0) goto L_0x00c9;
                L_0x0124:
                    r0 = r3;
                    goto L_0x00ca;
                L_0x0126:
                    r6 = "MEDIUM_RECTANGLE";
                    r0 = r0.equals(r6);
                    if (r0 == 0) goto L_0x00c9;
                L_0x012f:
                    r0 = r4;
                    goto L_0x00ca;
                L_0x0131:
                    r0 = com.appnext.banners.C0990R.layout.apnxt_banner_1;
                    goto L_0x00cf;
                L_0x0134:
                    r0 = com.appnext.banners.C0990R.layout.apnxt_large_banner_1;
                    goto L_0x00cf;
                L_0x0137:
                    r0 = com.appnext.banners.C0990R.layout.apnxt_medium_rectangle_1;
                    goto L_0x00cf;
                L_0x013a:
                    r3 = "BANNER";
                    r0 = r0.equals(r3);
                    if (r0 == 0) goto L_0x0104;
                L_0x0143:
                    r0 = r1;
                    goto L_0x0105;
                L_0x0145:
                    r1 = "LARGE_BANNER";
                    r0 = r0.equals(r1);
                    if (r0 == 0) goto L_0x0104;
                L_0x014e:
                    r0 = r3;
                    goto L_0x0105;
                L_0x0150:
                    r1 = "MEDIUM_RECTANGLE";
                    r0 = r0.equals(r1);
                    if (r0 == 0) goto L_0x0104;
                L_0x0159:
                    r0 = r4;
                    goto L_0x0105;
                L_0x015b:
                    r0 = r9.fu;
                    r1 = "loaded_banner";
                    r0.report(r1);
                    goto L_0x0108;
                L_0x0164:
                    r0 = r9.fu;
                    r1 = "loaded_large_banner";
                    r0.report(r1);
                    goto L_0x0108;
                L_0x016d:
                    r0 = r9.fu;
                    r1 = "loaded_medium_rectangle";
                    r0.report(r1);
                    goto L_0x0108;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.a.12.a(java.lang.Object):void");
                }

                public void error(String str) {
                    String str2 = "";
                    Object obj = -1;
                    switch (str.hashCode()) {
                        case -2026653947:
                            if (str.equals(AppnextError.INTERNAL_ERROR)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -1958363695:
                            if (str.equals(AppnextError.NO_ADS)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -1477010874:
                            if (str.equals(AppnextError.CONNECTION_ERROR)) {
                                obj = null;
                                break;
                            }
                            break;
                        case -507110949:
                            if (str.equals(AppnextError.NO_MARKET)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 350741825:
                            if (str.equals("Timeout")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 844170097:
                            if (str.equals(AppnextError.SLOW_CONNECTION)) {
                                obj = 4;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            str2 = C0893a.cy;
                            break;
                        case 1:
                            str2 = C0893a.cC;
                            break;
                        case 2:
                            str2 = C0893a.cB;
                            break;
                        case 3:
                            str2 = C0893a.cD;
                            break;
                        case 4:
                            str2 = C0893a.cz;
                            break;
                        case 5:
                            str2 = C0893a.cE;
                            break;
                    }
                    this.fu.report(str2);
                    if (this.fu.getBannerListener() != null) {
                        this.fu.getBannerListener().onError(new AppnextError(str));
                    }
                    this.fu.adRequest = null;
                }
            }, this.adRequest);
        }
    }

    public void getECPM(final BannerAdRequest bannerAdRequest, final OnECPMLoaded onECPMLoaded) {
        if (bannerAdRequest == null) {
            throw new IllegalStateException("BannerAdRequest cannot be null.");
        } else if (getPlacementId() == null) {
            throw new IllegalStateException("Missing placement id.");
        } else if (getBannerSize() == null) {
            throw new IllegalStateException("Missing banner size.");
        } else if (onECPMLoaded == null) {
            throw new IllegalStateException("callback cannot be null.");
        } else {
            if (this.bannerAd == null) {
                this.bannerAd = (BannerAd) createAd(this.context, getPlacementId());
            }
            this.bannerAd.setCategories(bannerAdRequest.getCategories());
            this.bannerAd.setPostback(bannerAdRequest.getPostback());
            C1007b.aE().m2044a(this.context, this.bannerAd, getPlacementId(), new C0914a(this) {
                final /* synthetic */ C1006a fu;

                public <T> void mo1971a(T t) {
                    AppnextAd a = C1007b.aE().m2040a(this.fu.context, this.fu.bannerAd, bannerAdRequest.getCreativeType());
                    if (a == null) {
                        this.fu.report(C0893a.cB);
                        if (onECPMLoaded != null) {
                            onECPMLoaded.error(AppnextError.NO_ADS);
                        }
                    } else if (onECPMLoaded != null) {
                        onECPMLoaded.ecpm(new ECPM(a.getECPM(), a.getPPR(), a.getBannerID()));
                    }
                }

                public void error(String str) {
                    if (onECPMLoaded != null) {
                        onECPMLoaded.error(str);
                    }
                }
            }, bannerAdRequest);
        }
    }

    private void inflateView(int i, final AppnextAd appnextAd) {
        CharSequence charSequence;
        View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(i, this.rootView, false);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ C1006a fu;

            {
                this.fu = r1;
            }

            public void onClick(View view) {
                this.fu.report(C0893a.dg);
                this.fu.click();
            }
        });
        final ImageView imageView = (ImageView) inflate.findViewById(C0990R.id.icon);
        if (imageView != null) {
            imageView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ C1006a fu;

                {
                    this.fu = r1;
                }

                public void onClick(View view) {
                    this.fu.report(C0893a.de);
                    this.fu.click();
                }
            });
            new Thread(new Runnable(this) {
                final /* synthetic */ C1006a fu;

                public void run() {
                    final Bitmap aO = C1128g.aO(appnextAd.getImageURL());
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass21 fD;

                        public void run() {
                            imageView.setImageBitmap(aO);
                        }
                    });
                }
            }).start();
        }
        TextView textView = (TextView) inflate.findViewById(C0990R.id.title);
        if (textView != null) {
            textView.setText(appnextAd.getAdTitle());
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ C1006a fu;

                {
                    this.fu = r1;
                }

                public void onClick(View view) {
                    this.fu.report(C0893a.dg);
                    this.fu.click();
                }
            });
        }
        RatingBar ratingBar = (RatingBar) inflate.findViewById(C0990R.id.ratingBar);
        if (ratingBar != null) {
            try {
                ratingBar.setRating(Float.parseFloat(appnextAd.getStoreRating()));
            } catch (Throwable th) {
                ratingBar.setVisibility(4);
            }
            ratingBar.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ C1006a fu;

                {
                    this.fu = r1;
                }

                public void onClick(View view) {
                    this.fu.report(C0893a.dg);
                    this.fu.click();
                }
            });
        }
        textView = (TextView) inflate.findViewById(C0990R.id.description);
        if (textView != null) {
            textView.setText(appnextAd.getAdDescription());
            textView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ C1006a fu;

                {
                    this.fu = r1;
                }

                public void onClick(View view) {
                    this.fu.report(C0893a.dg);
                    this.fu.click();
                }
            });
        }
        View findViewById = inflate.findViewById(C0990R.id.install);
        String buttonText = new BannerAdData(appnextAd).getButtonText();
        Object obj;
        if (appnextAd == null || !buttonText.equals("")) {
            obj = buttonText;
        } else if (C1128g.m2356h(this.context, this.currentAd.getAdPackage())) {
            charSequence = C1008c.aF().get("existing_button_text");
        } else {
            obj = C1008c.aF().get("new_button_text");
        }
        ((TextView) findViewById).setText(charSequence);
        findViewById.setOnClickListener(new C09952(this));
        View findViewById2 = inflate.findViewById(C0990R.id.media);
        if (findViewById2 != null) {
            if (this.adRequest.getCreativeType().equals(BannerAdRequest.TYPE_ALL)) {
                if (C1007b.hasVideo(this.currentAd)) {
                    createVideo((ViewGroup) findViewById2);
                } else {
                    createWideImage((ImageView) inflate.findViewById(C0990R.id.wide_image));
                }
            } else if (this.adRequest.getCreativeType().equals("video") && C1007b.hasVideo(this.currentAd)) {
                createVideo((ViewGroup) findViewById2);
            } else {
                createWideImage((ImageView) inflate.findViewById(C0990R.id.wide_image));
            }
        }
        ((ImageView) inflate.findViewById(C0990R.id.privacy)).setOnClickListener(new C09963(this));
        if (this.rootView.getChildCount() > 0) {
            this.rootView.removeViewAt(0);
        }
        this.rootView.addView(inflate);
    }

    public void impression() {
        if (this.loaded && !this.reportedImpression && getVisiblePercent(this.rootView) >= 50) {
            this.reportedImpression = true;
            if (this.currentAd != null) {
                this.mHandler.postDelayed(new C09974(this), (long) (Integer.parseInt(C1008c.aF().get("postpone_impression_sec")) * 1000));
                report(C0893a.cL);
                C1134l.db().m2373q(this.currentAd.getBannerID(), getPlacementId());
                if (Boolean.parseBoolean(C1008c.aF().get("pview"))) {
                    this.mHandler.postDelayed(new C09985(this), (long) (Integer.parseInt(C1008c.aF().get("postpone_vta_sec")) * 1000));
                }
                if (getBannerListener() != null) {
                    getBannerListener().adImpression();
                }
            }
            if (Boolean.parseBoolean(C1008c.aF().get("fq_control")) && this.bannerAd != null && this.bannerAd.fq_status) {
                new Thread(new C09996(this)).start();
            }
        }
    }

    public void click() {
        report(C0893a.da);
        if (getBannerListener() != null) {
            getBannerListener().onAdClicked();
        }
        this.userAction.m2396a(this.currentAd, new BannerAdData(this.currentAd).getAppURL(), getPlacementId(), new C10007(this));
    }

    protected void openLink(String str) {
        try {
            if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
                pause();
                ((ImageView) this.rootView.findViewById(C0990R.id.media).findViewById(C0990R.id.play)).setImageResource(C0990R.drawable.apnxt_banner_pause);
                this.rootView.findViewById(C0990R.id.media).findViewById(C0990R.id.play).setVisibility(0);
            }
        } catch (Throwable th) {
        }
        try {
            super.openLink(str);
        } catch (Throwable th2) {
            report(C0893a.cD);
        }
    }

    private void createWideImage(final ImageView imageView) {
        report(C0893a.dh);
        new Thread(new Runnable(this) {
            final /* synthetic */ C1006a fu;

            public void run() {
                final Bitmap aO = C1128g.aO(this.fu.currentAd.getWideImageURL());
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ C10038 fw;

                    class C10011 implements OnClickListener {
                        final /* synthetic */ C10021 fx;

                        C10011(C10021 c10021) {
                            this.fx = c10021;
                        }

                        public void onClick(View view) {
                            this.fx.fw.fu.report(C0893a.dg);
                            this.fx.fw.fu.click();
                        }
                    }

                    public void run() {
                        imageView.setImageBitmap(aO);
                        imageView.setVisibility(0);
                        imageView.setOnClickListener(new C10011(this));
                    }
                });
            }
        }).start();
    }

    private void createVideo(final ViewGroup viewGroup) {
        try {
            if (this.adRequest.isAutoPlay()) {
                report(C0893a.di);
            } else {
                report(C0893a.dj);
            }
            if (this.adRequest.isMute()) {
                report(C0893a.dk);
            } else {
                report(C0893a.dl);
            }
            this.userMute = this.adRequest.isMute();
            ((ImageView) viewGroup.findViewById(C0990R.id.mute)).setImageResource(this.userMute ? C0990R.drawable.apnxt_banner_unmute : C0990R.drawable.apnxt_banner_mute);
            viewGroup.findViewById(C0990R.id.mute).setVisibility(0);
            viewGroup.findViewById(C0990R.id.mute).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ C1006a fu;

                public void onClick(View view) {
                    this.fu.userMute = !this.fu.userMute;
                    if (this.fu.mediaPlayer != null) {
                        try {
                            float f;
                            MediaPlayer access$900 = this.fu.mediaPlayer;
                            if (this.fu.userMute) {
                                f = 0.0f;
                            } else {
                                f = 1.0f;
                            }
                            access$900.setVolume(f, this.fu.userMute ? 0.0f : 1.0f);
                            ((ImageView) viewGroup.findViewById(C0990R.id.mute)).setImageResource(this.fu.userMute ? C0990R.drawable.apnxt_banner_unmute : C0990R.drawable.apnxt_banner_mute);
                        } catch (Throwable th) {
                        }
                    }
                }
            });
            this.videoView = new VideoView(this.context.getApplicationContext());
        } catch (Throwable th) {
            C1128g.m2351c(th);
            return;
        }
        this.videoView.setLayoutParams(new LayoutParams(-1, -2));
        viewGroup.addView(this.videoView, 0);
        viewGroup.findViewById(C0990R.id.click).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ C1006a fu;

            public void onClick(View view) {
                if (this.fu.isClickEnabled() && this.fu.mediaPlayer != null && this.fu.mediaPlayer.isPlaying()) {
                    this.fu.click();
                    return;
                }
                ((ImageView) viewGroup.findViewById(C0990R.id.play)).setImageResource(C0990R.drawable.apnxt_banner_pause);
                viewGroup.findViewById(C0990R.id.play).setVisibility(0);
                this.fu.pause();
            }
        });
        this.videoView.setOnPreparedListener(new OnPreparedListener(this) {
            final /* synthetic */ C1006a fu;

            class C09911 implements OnSeekCompleteListener {
                final /* synthetic */ AnonymousClass11 fz;

                C09911(AnonymousClass11 anonymousClass11) {
                    this.fz = anonymousClass11;
                }

                public void onSeekComplete(MediaPlayer mediaPlayer) {
                    if (this.fz.fu.adRequest.isAutoPlay() && !this.fz.fu.finished && this.fz.fu.getVisiblePercent(this.fz.fu.rootView) > 90 && !this.fz.fu.mediaPlayer.isPlaying()) {
                        this.fz.fu.play();
                        try {
                            this.fz.fu.rootView.findViewById(C0990R.id.media).findViewById(C0990R.id.play).setVisibility(8);
                        } catch (Throwable th) {
                        }
                        if (!this.fz.fu.started) {
                            this.fz.fu.report(C0893a.cN);
                            this.fz.fu.started = true;
                        }
                    }
                }
            }

            {
                this.fu = r1;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                this.fu.mediaPlayer = mediaPlayer;
                this.fu.mediaPlayer.seekTo(this.fu.currentPosition);
                this.fu.mediaPlayer.setOnSeekCompleteListener(new C09911(this));
                if (this.fu.adRequest.isAutoPlay() && !this.fu.finished && this.fu.getVisiblePercent(this.fu.rootView) > 90) {
                    this.fu.play();
                    try {
                        this.fu.rootView.findViewById(C0990R.id.media).findViewById(C0990R.id.play).setVisibility(8);
                    } catch (Throwable th) {
                    }
                    if (!this.fu.started) {
                        this.fu.report(C0893a.cN);
                        this.fu.started = true;
                    }
                }
                this.fu.mHandler.postDelayed(this.fu.tick, 33);
                if (this.fu.userMute) {
                    this.fu.mediaPlayer.setVolume(0.0f, 0.0f);
                } else {
                    this.fu.mediaPlayer.setVolume(1.0f, 1.0f);
                }
            }
        });
        this.videoView.setOnCompletionListener(new OnCompletionListener(this) {
            final /* synthetic */ C1006a fu;

            {
                this.fu = r1;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                if (this.fu.mediaPlayer != null && this.fu.mediaPlayer.getCurrentPosition() != 0 && this.fu.mediaPlayer.getDuration() != 0 && !this.fu.finished) {
                    C1128g.m2333W("onCompletion. " + this.fu.mediaPlayer.getCurrentPosition() + BridgeUtil.SPLIT_MARK + this.fu.mediaPlayer.getDuration());
                    this.fu.finished = true;
                    this.fu.report(C0893a.cR);
                }
            }
        });
        this.videoView.setOnErrorListener(new OnErrorListener(this) {
            final /* synthetic */ C1006a fu;

            {
                this.fu = r1;
            }

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                if (!(i == -38 && i2 == 0)) {
                    C1128g.m2333W("mp error: what: " + i + " extra: " + i2);
                }
                return true;
            }
        });
        this.videoView.setVideoURI(Uri.parse(getVideoUrl(this.currentAd, this.adRequest.getVideoLength())));
        viewGroup.findViewById(C0990R.id.play).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ C1006a fu;

            public void onClick(View view) {
                viewGroup.findViewById(C0990R.id.wide_image).setVisibility(8);
                viewGroup.findViewById(C0990R.id.play).setVisibility(8);
                this.fu.play();
            }
        });
        if (!this.adRequest.isAutoPlay()) {
            viewGroup.findViewById(C0990R.id.play).setVisibility(0);
            new Thread(new Runnable(this) {
                final /* synthetic */ C1006a fu;

                public void run() {
                    final Bitmap aO = C1128g.aO(this.fu.currentAd.getWideImageURL());
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass16 fA;

                        public void run() {
                            ((ImageView) viewGroup.findViewById(C0990R.id.wide_image)).setImageBitmap(aO);
                            viewGroup.findViewById(C0990R.id.wide_image).setVisibility(0);
                        }
                    });
                }
            }).start();
        }
    }

    private String getVideoUrl(AppnextAd appnextAd, String str) {
        String videoUrl30Sec;
        if (str.equals("30")) {
            videoUrl30Sec = appnextAd.getVideoUrl30Sec();
            if (videoUrl30Sec.equals("")) {
                videoUrl30Sec = appnextAd.getVideoUrl();
            }
            if (videoUrl30Sec.equals("")) {
                videoUrl30Sec = appnextAd.getVideoUrlHigh30Sec();
            }
            if (videoUrl30Sec.equals("")) {
                return appnextAd.getVideoUrlHigh();
            }
            return videoUrl30Sec;
        }
        videoUrl30Sec = appnextAd.getVideoUrl();
        if (videoUrl30Sec.equals("")) {
            videoUrl30Sec = appnextAd.getVideoUrl30Sec();
        }
        if (videoUrl30Sec.equals("")) {
            videoUrl30Sec = appnextAd.getVideoUrlHigh();
        }
        if (videoUrl30Sec.equals("")) {
            return appnextAd.getVideoUrlHigh30Sec();
        }
        return videoUrl30Sec;
    }

    private void checkProgress() {
        try {
            if (this.mediaPlayer != null) {
                int currentPosition = (int) ((((float) this.mediaPlayer.getCurrentPosition()) / ((float) this.mediaPlayer.getDuration())) * 100.0f);
                if (currentPosition > 25 && this.lastProgress == 0) {
                    this.lastProgress = 25;
                    report(C0893a.cO);
                } else if (currentPosition > 50 && this.lastProgress == 25) {
                    this.lastProgress = 50;
                    report(C0893a.cP);
                } else if (currentPosition > 75 && this.lastProgress == 50) {
                    this.lastProgress = 75;
                    report(C0893a.cQ);
                }
            }
        } catch (Throwable th) {
        }
    }

    public void onScrollChanged(int i) {
        if (this.mediaPlayer != null && !this.finished) {
            if (this.mediaPlayer.isPlaying() && i < 90) {
                try {
                    this.rootView.findViewById(C0990R.id.media).findViewById(C0990R.id.play).setVisibility(0);
                } catch (Throwable th) {
                }
                try {
                    pause();
                } catch (Throwable th2) {
                    return;
                }
            }
            if (!this.mediaPlayer.isPlaying() && i > 90 && this.adRequest.isAutoPlay()) {
                C1128g.m2333W("start. " + this.mediaPlayer.getCurrentPosition() + BridgeUtil.SPLIT_MARK + this.mediaPlayer.getDuration());
                play();
                try {
                    this.rootView.findViewById(C0990R.id.media).findViewById(C0990R.id.play).setVisibility(8);
                } catch (Throwable th3) {
                }
                if (!this.started) {
                    report(C0893a.cN);
                    this.started = true;
                }
            }
        }
    }

    public void destroy() {
        super.destroy();
        this.userAction.destroy();
        try {
            if (this.videoView != null) {
                this.videoView.setOnCompletionListener(null);
                this.videoView.setOnErrorListener(null);
                this.videoView.setOnPreparedListener(null);
                this.videoView.suspend();
                this.videoView = null;
                this.mediaPlayer.release();
                this.mediaPlayer = null;
            }
        } catch (Throwable th) {
        }
        try {
            if (this.serviceHelper != null) {
                this.serviceHelper.mo1917d(this.context);
            }
            this.serviceHelper = null;
        } catch (Throwable th2) {
        }
        try {
            this.bannerAd.destroy();
            this.bannerAd = null;
        } catch (Throwable th3) {
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        destroy();
    }

    public void play() {
        if (this.mediaPlayer != null && !this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.start();
        }
    }

    public void pause() {
        if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
        }
    }

    private void report(String str) {
        try {
            if (this.bannerAd != null) {
                C1128g.m2341a(this.bannerAd.getTID(), this.bannerAd.getVID(), this.bannerAd.getAUID(), getPlacementId() == null ? "" : getPlacementId(), this.bannerAd.getSessionId(), str, this.template, this.currentAd != null ? this.currentAd.getBannerID() : "", this.currentAd != null ? this.currentAd.getCampaignID() : "");
            }
        } catch (Throwable th) {
        }
    }

    public boolean isClickEnabled() {
        return this.clickEnabled;
    }

    public void setClickEnabled(boolean z) {
        this.clickEnabled = z;
    }
}
