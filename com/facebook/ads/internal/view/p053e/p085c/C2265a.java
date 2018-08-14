package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
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
import com.facebook.ads.internal.p056q.p075b.C2136b;
import com.facebook.ads.internal.p056q.p075b.C2137c;
import com.facebook.ads.internal.p056q.p076c.C2149g;
import com.facebook.ads.internal.view.p053e.p083a.C2224c;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;

public class C2265a extends C2224c {
    private final C2264a f5487a;

    public static class C2264a extends RelativeLayout {
        private final String f5480a;
        private final String f5481b;
        private final String f5482c;
        private final DisplayMetrics f5483d;
        private ImageView f5484e;
        private TextView f5485f;
        private boolean f5486g = false;

        class C22591 implements OnTouchListener {
            final /* synthetic */ C2264a f5471a;

            C22591(C2264a c2264a) {
                this.f5471a = c2264a;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return false;
                }
                if (!this.f5471a.f5486g) {
                    this.f5471a.m7155d();
                } else if (!TextUtils.isEmpty(this.f5471a.f5481b)) {
                    C2149g.m6880a(new C2149g(), this.f5471a.getContext(), Uri.parse(this.f5471a.f5481b), this.f5471a.f5482c);
                }
                return true;
            }
        }

        public C2264a(Context context, String str, String str2, float[] fArr, String str3) {
            super(context);
            this.f5480a = str;
            this.f5481b = str2;
            this.f5482c = str3;
            this.f5483d = context.getResources().getDisplayMetrics();
            Drawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(-16777216);
            gradientDrawable.setAlpha(178);
            gradientDrawable.setCornerRadii(new float[]{fArr[0] * this.f5483d.density, fArr[0] * this.f5483d.density, fArr[1] * this.f5483d.density, fArr[1] * this.f5483d.density, fArr[2] * this.f5483d.density, fArr[2] * this.f5483d.density, fArr[3] * this.f5483d.density, fArr[3] * this.f5483d.density});
            if (VERSION.SDK_INT >= 16) {
                setBackground(gradientDrawable);
            } else {
                setBackgroundDrawable(gradientDrawable);
            }
            m7148a();
            m7152b();
            m7154c();
            setMinimumWidth(Math.round(CloseButton.TEXT_SIZE_SP * this.f5483d.density));
            setMinimumHeight(Math.round(RadialCountdown.TEXT_SIZE_SP * this.f5483d.density));
        }

        private void m7148a() {
            setOnTouchListener(new C22591(this));
        }

        private void m7152b() {
            this.f5484e = new ImageView(getContext());
            this.f5484e.setImageBitmap(C2137c.m6843a(C2136b.IC_AD_CHOICES));
            addView(this.f5484e);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(this.f5483d.density * 16.0f), Math.round(this.f5483d.density * 16.0f));
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            layoutParams.setMargins(Math.round(4.0f * this.f5483d.density), Math.round(this.f5483d.density * 2.0f), Math.round(this.f5483d.density * 2.0f), Math.round(this.f5483d.density * 2.0f));
            this.f5484e.setLayoutParams(layoutParams);
        }

        private void m7154c() {
            this.f5485f = new TextView(getContext());
            addView(this.f5485f);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.width = 0;
            layoutParams.leftMargin = (int) (CloseButton.TEXT_SIZE_SP * this.f5483d.density);
            layoutParams.addRule(9);
            layoutParams.addRule(15, -1);
            this.f5485f.setLayoutParams(layoutParams);
            this.f5485f.setSingleLine();
            this.f5485f.setText(this.f5480a);
            this.f5485f.setTextSize(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT);
            this.f5485f.setTextColor(-4341303);
        }

        private void m7155d() {
            Paint paint = new Paint();
            paint.setTextSize(this.f5485f.getTextSize());
            int round = Math.round(paint.measureText(this.f5480a) + (4.0f * this.f5483d.density));
            final int width = getWidth();
            round += width;
            this.f5486g = true;
            Animation c22602 = new Animation(this) {
                final /* synthetic */ C2264a f5474c;

                protected void applyTransformation(float f, Transformation transformation) {
                    int i = (int) (((float) width) + (((float) (round - width)) * f));
                    this.f5474c.getLayoutParams().width = i;
                    this.f5474c.requestLayout();
                    this.f5474c.f5485f.getLayoutParams().width = i - width;
                    this.f5474c.f5485f.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            };
            c22602.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ C2264a f5479c;

                class C22621 implements Runnable {
                    final /* synthetic */ C22633 f5476a;

                    class C22611 extends Animation {
                        final /* synthetic */ C22621 f5475a;

                        C22611(C22621 c22621) {
                            this.f5475a = c22621;
                        }

                        protected void applyTransformation(float f, Transformation transformation) {
                            int i = (int) (((float) round) + (((float) (width - round)) * f));
                            this.f5475a.f5476a.f5479c.getLayoutParams().width = i;
                            this.f5475a.f5476a.f5479c.requestLayout();
                            this.f5475a.f5476a.f5479c.f5485f.getLayoutParams().width = i - width;
                            this.f5475a.f5476a.f5479c.f5485f.requestLayout();
                        }

                        public boolean willChangeBounds() {
                            return true;
                        }
                    }

                    C22621(C22633 c22633) {
                        this.f5476a = c22633;
                    }

                    public void run() {
                        if (this.f5476a.f5479c.f5486g) {
                            this.f5476a.f5479c.f5486g = false;
                            Animation c22611 = new C22611(this);
                            c22611.setDuration(300);
                            c22611.setFillAfter(true);
                            this.f5476a.f5479c.startAnimation(c22611);
                        }
                    }
                }

                public void onAnimationEnd(Animation animation) {
                    new Handler().postDelayed(new C22621(this), 3000);
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
            c22602.setDuration(300);
            c22602.setFillAfter(true);
            startAnimation(c22602);
        }
    }

    public C2265a(Context context, String str, String str2, float[] fArr) {
        super(context);
        this.f5487a = new C2264a(context, "AdChoices", str, fArr, str2);
        addView(this.f5487a);
    }
}
