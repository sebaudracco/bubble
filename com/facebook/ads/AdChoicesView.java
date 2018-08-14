package com.facebook.ads;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.facebook.ads.internal.p056q.p057a.C2118i;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p056q.p076c.C2149g;

public class AdChoicesView extends RelativeLayout {
    private final Context f3948a;
    private final NativeAd f3949b;
    private final float f3950c;
    private boolean f3951d;
    private TextView f3952e;
    private String f3953f;

    public AdChoicesView(Context context, NativeAd nativeAd) {
        this(context, nativeAd, false);
    }

    public AdChoicesView(Context context, final NativeAd nativeAd, boolean z) {
        super(context);
        this.f3951d = false;
        this.f3948a = context;
        this.f3949b = nativeAd;
        this.f3950c = C2133v.f5083b;
        if (!this.f3949b.isAdLoaded() || this.f3949b.a().mo3648h()) {
            this.f3953f = this.f3949b.getAdChoicesText();
            if (TextUtils.isEmpty(this.f3953f)) {
                this.f3953f = "AdChoices";
            }
            NativeAd$Image adChoicesIcon = this.f3949b.getAdChoicesIcon();
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ AdChoicesView f3939b;

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() != 0) {
                        return false;
                    }
                    if (!this.f3939b.f3951d) {
                        this.f3939b.m5349a();
                    } else if (!TextUtils.isEmpty(this.f3939b.f3949b.getAdChoicesLinkUrl())) {
                        C2149g.m6880a(new C2149g(), this.f3939b.f3948a, Uri.parse(this.f3939b.f3949b.getAdChoicesLinkUrl()), nativeAd.g());
                    }
                    return true;
                }
            });
            this.f3952e = new TextView(this.f3948a);
            addView(this.f3952e);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            if (!z || adChoicesIcon == null) {
                this.f3951d = true;
            } else {
                layoutParams2.addRule(11, m5348a(adChoicesIcon).getId());
                layoutParams2.width = 0;
                layoutParams.width = Math.round(((float) (adChoicesIcon.getWidth() + 4)) * this.f3950c);
                layoutParams.height = Math.round(((float) (adChoicesIcon.getHeight() + 2)) * this.f3950c);
                this.f3951d = false;
            }
            setLayoutParams(layoutParams);
            layoutParams2.addRule(15, -1);
            this.f3952e.setLayoutParams(layoutParams2);
            this.f3952e.setSingleLine();
            this.f3952e.setText(this.f3953f);
            this.f3952e.setTextSize(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT);
            this.f3952e.setTextColor(-4341303);
            C2118i.m6796a(this, C2118i.INTERNAL_AD_CHOICES_ICON);
            C2118i.m6796a(this.f3952e, C2118i.INTERNAL_AD_CHOICES_ICON);
            return;
        }
        setVisibility(8);
    }

    private ImageView m5348a(NativeAd$Image nativeAd$Image) {
        View imageView = new ImageView(this.f3948a);
        addView(imageView);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(((float) nativeAd$Image.getWidth()) * this.f3950c), Math.round(((float) nativeAd$Image.getHeight()) * this.f3950c));
        layoutParams.addRule(9);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(Math.round(4.0f * this.f3950c), Math.round(this.f3950c * 2.0f), Math.round(this.f3950c * 2.0f), Math.round(this.f3950c * 2.0f));
        imageView.setLayoutParams(layoutParams);
        NativeAd.downloadAndDisplayImage(nativeAd$Image, imageView);
        return imageView;
    }

    private void m5349a() {
        Paint paint = new Paint();
        paint.setTextSize(this.f3952e.getTextSize());
        int round = Math.round(paint.measureText(this.f3953f) + (4.0f * this.f3950c));
        final int width = getWidth();
        round += width;
        this.f3951d = true;
        Animation c18242 = new Animation(this) {
            final /* synthetic */ AdChoicesView f3942c;

            protected void applyTransformation(float f, Transformation transformation) {
                int i = (int) (((float) width) + (((float) (round - width)) * f));
                this.f3942c.getLayoutParams().width = i;
                this.f3942c.requestLayout();
                this.f3942c.f3952e.getLayoutParams().width = i - width;
                this.f3942c.f3952e.requestLayout();
            }

            public boolean willChangeBounds() {
                return true;
            }
        };
        c18242.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ AdChoicesView f3947c;

            class C18261 implements Runnable {
                final /* synthetic */ C18273 f3944a;

                class C18251 extends Animation {
                    final /* synthetic */ C18261 f3943a;

                    C18251(C18261 c18261) {
                        this.f3943a = c18261;
                    }

                    protected void applyTransformation(float f, Transformation transformation) {
                        int i = (int) (((float) round) + (((float) (width - round)) * f));
                        this.f3943a.f3944a.f3947c.getLayoutParams().width = i;
                        this.f3943a.f3944a.f3947c.requestLayout();
                        this.f3943a.f3944a.f3947c.f3952e.getLayoutParams().width = i - width;
                        this.f3943a.f3944a.f3947c.f3952e.requestLayout();
                    }

                    public boolean willChangeBounds() {
                        return true;
                    }
                }

                C18261(C18273 c18273) {
                    this.f3944a = c18273;
                }

                public void run() {
                    if (this.f3944a.f3947c.f3951d) {
                        this.f3944a.f3947c.f3951d = false;
                        Animation c18251 = new C18251(this);
                        c18251.setDuration(300);
                        c18251.setFillAfter(true);
                        this.f3944a.f3947c.startAnimation(c18251);
                    }
                }
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new C18261(this), 3000);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        c18242.setDuration(300);
        c18242.setFillAfter(true);
        startAnimation(c18242);
    }
}
