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

public class C0939e extends Fragment {
    private C0906i ea;
    private TextView eb;
    private int ec = 0;
    private ArrayList<AppnextAd> ed;
    private Handler handler;
    Runnable tick = new C09385(this);

    class C09321 implements OnClickListener {
        final /* synthetic */ C0939e ee;

        C09321(C0939e c0939e) {
            this.ee = c0939e;
        }

        public void onClick(View view) {
            this.ee.ea.privacyClicked();
        }
    }

    class C09385 implements Runnable {
        final /* synthetic */ C0939e ee;

        C09385(C0939e c0939e) {
            this.ee = c0939e;
        }

        public void run() {
            this.ee.handler.removeCallbacks(this);
            if (C0939e.m1936c(this.ee) == 0) {
                this.ee.ea.videoSelected((AppnextAd) this.ee.ed.get(0));
                this.ee.report(C0893a.cJ);
                return;
            }
            if (this.ee.eb != null) {
                this.ee.eb.setText("" + this.ee.ec + " sec");
            }
            this.ee.handler.postDelayed(this.ee.tick, 1000);
        }
    }

    static /* synthetic */ int m1936c(C0939e c0939e) {
        int i = c0939e.ec - 1;
        c0939e.ec = i;
        return i;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null && getArguments().containsKey("time")) {
            this.ec = getArguments().getInt("time");
        }
        if (bundle != null) {
            this.ec = bundle.getInt("time");
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.ea = (C0906i) activity;
        this.handler = new Handler();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.ea = (C0906i) context;
        this.handler = new Handler();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        try {
            RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(this.ea.getTemplate("S1"), viewGroup, false);
            View findViewById = relativeLayout.findViewById(C0889R.id.item1);
            View findViewById2 = relativeLayout.findViewById(C0889R.id.item2);
            ((TextView) relativeLayout.findViewById(C0889R.id.title)).setText(this.ea.getConfigManager().get("pre_title_string1"));
            ((TextView) relativeLayout.findViewById(C0889R.id.secondTile)).setText(this.ea.getConfigManager().get("pre_title_string2"));
            this.eb = (TextView) relativeLayout.findViewById(C0889R.id.timer);
            this.ed = this.ea.getPreRollAds();
            if (this.ed.size() < 2) {
                this.ea.videoSelected((AppnextAd) this.ed.get(0));
                return null;
            }
            m1933a((ViewGroup) findViewById, (AppnextAd) this.ed.get(0), 0);
            m1933a((ViewGroup) findViewById2, (AppnextAd) this.ed.get(1), 1);
            relativeLayout.findViewById(C0889R.id.privacy).setOnClickListener(new C09321(this));
            this.eb.setText("" + this.ec + " sec");
            report(C0893a.cG);
            return relativeLayout;
        } catch (Throwable th) {
            this.ea.closeClicked();
            return null;
        }
    }

    private void m1933a(ViewGroup viewGroup, final AppnextAd appnextAd, final int i) {
        TextView textView = (TextView) viewGroup.findViewById(C0889R.id.title);
        final ImageView imageView = (ImageView) viewGroup.findViewById(C0889R.id.icon);
        final ImageView imageView2 = (ImageView) viewGroup.findViewById(C0889R.id.background_image);
        RatingBar ratingBar = (RatingBar) viewGroup.findViewById(C0889R.id.ratingBar);
        if (viewGroup.findViewById(C0889R.id.playGameTextView) != null) {
            ((TextView) viewGroup.findViewById(C0889R.id.playGameTextView)).setText(this.ea.getConfigManager().get("pre_cta_string"));
        }
        textView.setText(appnextAd.getAdTitle());
        ratingBar.setRating(Float.parseFloat(appnextAd.getStoreRating()));
        viewGroup.findViewById(C0889R.id.click).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ C0939e ee;

            public void onClick(View view) {
                this.ee.ea.videoSelected(appnextAd);
                if (i == 0) {
                    this.ee.report(C0893a.cH);
                } else if (i == 1) {
                    this.ee.report(C0893a.cI);
                }
            }
        });
        if (imageView2 != null) {
            new Thread(new Runnable(this) {
                final /* synthetic */ C0939e ee;

                public void run() {
                    final Bitmap aO = C1128g.aO(appnextAd.getWideImageURL());
                    final Bitmap aO2 = C1128g.aO(appnextAd.getImageURL());
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ C09353 ek;

                        public void run() {
                            imageView2.setImageBitmap(aO);
                            imageView.setImageBitmap(aO2);
                        }
                    });
                }
            }).start();
        }
        new Thread(new Runnable(this) {
            final /* synthetic */ C0939e ee;

            public void run() {
                Bitmap bitmap = null;
                if (imageView2 != null) {
                    bitmap = C1128g.aO(appnextAd.getWideImageURL());
                }
                final Bitmap aO = C1128g.aO(appnextAd.getImageURL());
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ C09374 el;

                    public void run() {
                        if (imageView2 != null) {
                            imageView2.setImageBitmap(bitmap);
                        }
                        imageView.setImageBitmap(aO);
                    }
                });
            }
        }).start();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.handler.removeCallbacksAndMessages(null);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("time", this.ec);
        super.onSaveInstanceState(bundle);
    }

    public void onPause() {
        super.onPause();
        this.handler.removeCallbacks(this.tick);
    }

    public void onResume() {
        super.onResume();
        this.handler.postDelayed(this.tick, 1000);
    }

    private void report(String str) {
        this.ea.report(str, "S1");
    }
}
