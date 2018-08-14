package com.appnext.ads.fullscreen;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appnext.C0889R;
import com.appnext.ads.C0893a;
import com.appnext.core.AppnextAd;
import com.appnext.core.C1128g;
import java.util.ArrayList;

public class C0931d extends Fragment {
    private ArrayList<AppnextAd> aL;
    private ImageView dP;
    private TextView dQ;
    private boolean dR = false;
    private C0905h dS;

    class C09231 implements OnClickListener {
        final /* synthetic */ C0931d dT;

        C09231(C0931d c0931d) {
            this.dT = c0931d;
        }

        public void onClick(View view) {
            this.dT.dS.privacyClicked();
        }
    }

    class C09242 implements OnClickListener {
        final /* synthetic */ C0931d dT;

        C09242(C0931d c0931d) {
            this.dT = c0931d;
        }

        public void onClick(View view) {
            this.dT.dS.closeClicked();
        }
    }

    class C09253 implements OnClickListener {
        final /* synthetic */ C0931d dT;

        C09253(C0931d c0931d) {
            this.dT = c0931d;
        }

        public void onClick(View view) {
            this.dT.dS.installClicked();
        }
    }

    class C09274 implements Runnable {
        final /* synthetic */ C0931d dT;

        C09274(C0931d c0931d) {
            this.dT = c0931d;
        }

        public void run() {
            final Bitmap aO = C1128g.aO(((AppnextAd) this.dT.aL.get(0)).getWideImageURL());
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ C09274 dV;

                public void run() {
                    this.dV.dT.dP.setImageBitmap(aO);
                }
            });
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.dS = (C0905h) activity;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.dS = (C0905h) context;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        try {
            RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(this.dS.getTemplate("S3"), viewGroup, false);
            this.aL = this.dS.getPostRollAds();
            ImageView imageView = (ImageView) relativeLayout.findViewById(C0889R.id.privacy);
            ImageView imageView2 = (ImageView) relativeLayout.findViewById(C0889R.id.close);
            View findViewById = relativeLayout.findViewById(C0889R.id.click);
            this.dP = (ImageView) relativeLayout.findViewById(C0889R.id.media);
            this.dQ = (TextView) relativeLayout.findViewById(C0889R.id.install);
            imageView.setOnClickListener(new C09231(this));
            imageView2.setOnClickListener(new C09242(this));
            findViewById.setOnClickListener(new C09253(this));
            this.dQ.setText(this.dS.getCtaText());
            new Thread(new C09274(this)).start();
            m1927a(relativeLayout, (AppnextAd) this.aL.get(0), true);
            View findViewById2 = relativeLayout.findViewById(C0889R.id.extra);
            if (findViewById2 != null) {
                if (this.aL.size() > 1) {
                    m1927a((RelativeLayout) findViewById2.findViewById(C0889R.id.item1), (AppnextAd) this.aL.get(1), false);
                } else {
                    findViewById2.findViewById(C0889R.id.item1).setVisibility(4);
                }
                if (this.aL.size() > 2) {
                    m1927a((RelativeLayout) findViewById2.findViewById(C0889R.id.item2), (AppnextAd) this.aL.get(2), false);
                } else {
                    findViewById2.findViewById(C0889R.id.item2).setVisibility(4);
                }
                if (findViewById2.findViewById(C0889R.id.item3) != null) {
                    if (this.aL.size() > 3) {
                        m1927a((RelativeLayout) findViewById2.findViewById(C0889R.id.item3), (AppnextAd) this.aL.get(3), false);
                    } else {
                        findViewById2.findViewById(C0889R.id.item3).setVisibility(4);
                    }
                }
            }
            report(C0893a.cW);
            return relativeLayout;
        } catch (Throwable th) {
            C1128g.m2351c(th);
            this.dS.closeClicked();
            return null;
        }
    }

    private void m1927a(final RelativeLayout relativeLayout, final AppnextAd appnextAd, final boolean z) {
        new Thread(new Runnable(this) {
            final /* synthetic */ C0931d dT;

            public void run() {
                final Bitmap aO = C1128g.aO(appnextAd.getImageURL());
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ C09295 dY;

                    public void run() {
                        ((ImageView) relativeLayout.findViewById(C0889R.id.icon)).setImageBitmap(aO);
                    }
                });
            }
        }).start();
        ((TextView) relativeLayout.findViewById(C0889R.id.title)).setText(appnextAd.getAdTitle());
        ((RatingBar) relativeLayout.findViewById(C0889R.id.rating)).setRating(Float.parseFloat(appnextAd.getStoreRating()));
        relativeLayout.findViewById(C0889R.id.click).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ C0931d dT;

            public void onClick(View view) {
                this.dT.dS.installClicked(appnextAd);
                this.dT.dR = true;
                if (z) {
                    this.dT.report(C0893a.cY);
                } else {
                    this.dT.report(C0893a.cZ);
                }
            }
        });
    }

    public void onDestroyView() {
        report(C0893a.cX);
        super.onDestroyView();
    }

    private void report(String str) {
        this.dS.report(str, "S3");
    }
}
