package com.appnext.ads.fullscreen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.appnext.C0889R;
import com.appnext.ads.C0893a;
import com.appnext.core.C1128g;
import com.github.lzyzsd.jsbridge.BridgeUtil;

public class C0954g extends Fragment {
    private final int TICK = 330;
    private int currentPosition = 0;
    private Circle dw;
    Runnable eA = new C09422(this);
    private ImageView es;
    private Button et;
    private TextView eu;
    private ImageView ev;
    private Animation ew;
    private ImageView ex;
    private boolean ey = false;
    private C0907j ez;
    private boolean finished = false;
    private int lastProgress = 0;
    private Handler mHandler = new Handler();
    private MediaPlayer mediaPlayer;
    private boolean started = false;
    @SuppressLint({"SetTextI18n"})
    Runnable tick = new Runnable(this) {
        final /* synthetic */ C0954g eB;

        {
            this.eB = r1;
        }

        public void run() {
            C1128g.m2333W("tick");
            if (this.eB.videoView != null) {
                C1128g.m2333W("" + this.eB.videoView.getCurrentPosition() + " of " + this.eB.videoView.getDuration());
                if (this.eB.videoView.getDuration() == -1) {
                    this.eB.am();
                    return;
                }
                this.eB.checkProgress();
                if (this.eB.dw.getVisibility() == 0) {
                    Animation c0917a = new C0917a(this.eB.dw, 360.0f - ((((float) (this.eB.videoView.getCurrentPosition() + 1)) / ((float) this.eB.videoView.getDuration())) * 360.0f));
                    c0917a.setDuration(330);
                    this.eB.dw.startAnimation(c0917a);
                }
                if (this.eB.videoView.getCurrentPosition() < this.eB.videoView.getDuration() && !this.eB.finished) {
                    this.eB.mHandler.postDelayed(this.eB.tick, 330);
                }
            }
        }
    };
    private VideoView videoView;

    class C09411 implements OnClickListener {
        final /* synthetic */ C0954g eB;

        C09411(C0954g c0954g) {
            this.eB = c0954g;
        }

        public void onClick(View view) {
            this.eB.ez.privacyClicked();
        }
    }

    class C09422 implements Runnable {
        final /* synthetic */ C0954g eB;

        C09422(C0954g c0954g) {
            this.eB = c0954g;
        }

        public void run() {
            this.eB.eu.setAlpha(1.0f);
            this.eB.eu.animate().alpha(0.0f).setDuration(1000);
        }
    }

    class C09433 implements AnimationListener {
        final /* synthetic */ C0954g eB;

        C09433(C0954g c0954g) {
            this.eB = c0954g;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            if (this.eB.ev.getVisibility() != 8) {
                this.eB.ev.startAnimation(this.eB.ew);
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    class C09444 implements Runnable {
        final /* synthetic */ C0954g eB;

        C09444(C0954g c0954g) {
            this.eB = c0954g;
        }

        public void run() {
            this.eB.ex.setVisibility(0);
        }
    }

    class C09455 implements OnClickListener {
        final /* synthetic */ C0954g eB;

        C09455(C0954g c0954g) {
            this.eB = c0954g;
        }

        public void onClick(View view) {
            this.eB.ez.closeClicked();
        }
    }

    class C09476 implements OnClickListener {
        final /* synthetic */ C0954g eB;

        class C09461 implements AnimationListener {
            final /* synthetic */ C09476 eC;

            C09461(C09476 c09476) {
                this.eC = c09476;
            }

            public void onAnimationStart(Animation animation) {
                this.eC.eB.et.setText("");
            }

            public void onAnimationEnd(Animation animation) {
                this.eC.eB.es.setVisibility(0);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        }

        C09476(C0954g c0954g) {
            this.eB = c0954g;
        }

        public void onClick(View view) {
            this.eB.ez.installClicked();
            Animation c0953a = new C0953a(this.eB, this.eB.et, C1128g.m2334a(this.eB.getActivity(), 40.0f), this.eB.et.getMeasuredWidth());
            c0953a.setDuration(300);
            c0953a.setInterpolator(new AccelerateInterpolator());
            c0953a.setAnimationListener(new C09461(this));
            if (!this.eB.et.getText().equals("")) {
                this.eB.et.startAnimation(c0953a);
            }
            this.eB.report(C0893a.cS);
        }
    }

    class C09487 implements OnClickListener {
        final /* synthetic */ C0954g eB;

        C09487(C0954g c0954g) {
            this.eB = c0954g;
        }

        public void onClick(View view) {
            this.eB.eu.setVisibility(0);
            this.eB.eu.setAlpha(0.0f);
            this.eB.eu.animate().alpha(1.0f).setDuration(1000);
            if (!this.eB.ey) {
                this.eB.ey = true;
                this.eB.report(C0893a.cT);
            }
            int captionTextTime = this.eB.ez.getCaptionTextTime();
            if (captionTextTime == -2) {
                captionTextTime = Integer.parseInt(this.eB.ez.getConfigManager().get("caption_text_time"));
            }
            if (captionTextTime > 0) {
                this.eB.mHandler.postDelayed(this.eB.eA, (long) (captionTextTime * 1000));
            }
        }
    }

    class C09529 implements OnCompletionListener {
        final /* synthetic */ C0954g eB;

        C09529(C0954g c0954g) {
            this.eB = c0954g;
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            this.eB.am();
        }
    }

    private class C0953a extends Animation {
        final /* synthetic */ C0954g eB;
        final int eF;
        int eG;
        View view;

        public C0953a(C0954g c0954g, View view, int i, int i2) {
            this.eB = c0954g;
            this.view = view;
            this.eF = i;
            this.eG = i2;
        }

        protected void applyTransformation(float f, Transformation transformation) {
            this.view.getLayoutParams().width = (int) (((float) this.eG) + (((float) (this.eF - this.eG)) * f));
            this.view.requestLayout();
        }

        public boolean willChangeBounds() {
            return true;
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.ez = (C0907j) activity;
        an();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.ez = (C0907j) context;
        an();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, final Bundle bundle) {
        try {
            RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(this.ez.getTemplate("S2"), viewGroup, false);
            ImageView imageView = (ImageView) relativeLayout.findViewById(C0889R.id.privacy);
            this.ex = (ImageView) relativeLayout.findViewById(C0889R.id.close);
            this.es = (ImageView) relativeLayout.findViewById(C0889R.id.v_view);
            this.et = (Button) relativeLayout.findViewById(C0889R.id.install);
            this.dw = (Circle) relativeLayout.findViewById(C0889R.id.circle);
            this.eu = (TextView) relativeLayout.findViewById(C0889R.id.click_txt);
            this.eu.setText("You will be redirected to " + (this.ez.isInstalled() ? "app" : "Google Play") + " once the ad will finish");
            this.ev = (ImageView) relativeLayout.findViewById(C0889R.id.loader);
            imageView.setOnClickListener(new C09411(this));
            this.ex.setVisibility(8);
            if (this.ez.showClose()) {
                this.mHandler.postDelayed(new C09444(this), this.ez.closeDelay());
            }
            this.ex.setOnClickListener(new C09455(this));
            if (!(getArguments() == null || !getArguments().containsKey("showCta") || getArguments().getBoolean("showCta"))) {
                this.et.setVisibility(8);
            }
            this.et.setText(this.ez.getCtaText());
            this.et.setOnClickListener(new C09476(this));
            this.es.setOnClickListener(new C09487(this));
            try {
                this.videoView = new VideoView(getActivity().getApplicationContext());
            } catch (Throwable th) {
                C1128g.m2351c(th);
            }
            this.videoView.setLayoutParams(new LayoutParams(-1, -2));
            ((ViewGroup) relativeLayout.findViewById(C0889R.id.media)).addView(this.videoView, 0);
            this.videoView.setOnPreparedListener(new OnPreparedListener(this) {
                final /* synthetic */ C0954g eB;

                class C09491 implements OnBufferingUpdateListener {
                    final /* synthetic */ C09518 eE;

                    C09491(C09518 c09518) {
                        this.eE = c09518;
                    }

                    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                        if (i < 100) {
                            this.eE.eB.ev.setVisibility(0);
                            this.eE.eB.ev.startAnimation(this.eE.eB.ew);
                            this.eE.eB.mediaPlayer.pause();
                            return;
                        }
                        this.eE.eB.ev.clearAnimation();
                        this.eE.eB.ev.setVisibility(8);
                        this.eE.eB.mediaPlayer.start();
                    }
                }

                class C09502 implements OnSeekCompleteListener {
                    final /* synthetic */ C09518 eE;

                    C09502(C09518 c09518) {
                        this.eE = c09518;
                    }

                    public void onSeekComplete(MediaPlayer mediaPlayer) {
                        this.eE.eB.mediaPlayer.start();
                    }
                }

                public void onPrepared(MediaPlayer mediaPlayer) {
                    this.eB.mediaPlayer = mediaPlayer;
                    this.eB.mediaPlayer.seekTo(this.eB.currentPosition);
                    this.eB.mediaPlayer.setOnBufferingUpdateListener(new C09491(this));
                    this.eB.mediaPlayer.setOnSeekCompleteListener(new C09502(this));
                    this.eB.mediaPlayer.start();
                    if ((bundle == null || !bundle.getBoolean("started")) && this.eB.ez != null) {
                        this.eB.started = true;
                        this.eB.ez.videoStarted();
                        this.eB.report(C0893a.cN);
                    }
                    this.eB.mHandler.postDelayed(this.eB.tick, 33);
                    if (this.eB.ez.getMute()) {
                        this.eB.mediaPlayer.setVolume(0.0f, 0.0f);
                    } else {
                        this.eB.mediaPlayer.setVolume(1.0f, 1.0f);
                    }
                }
            });
            this.videoView.setOnCompletionListener(new C09529(this));
            this.videoView.setOnErrorListener(new OnErrorListener(this) {
                final /* synthetic */ C0954g eB;

                {
                    this.eB = r1;
                }

                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    if (!(i == -38 && i2 == 0)) {
                        C1128g.m2333W("mp error: what: " + i + " extra: " + i2);
                    }
                    return true;
                }
            });
            this.videoView.setVideoURI(this.ez.getSelectedVideoUri());
            report(C0893a.cK);
            return relativeLayout;
        } catch (Throwable th2) {
            this.ez.closeClicked();
            return null;
        }
    }

    private void am() {
        try {
            if (this.mediaPlayer != null && this.mediaPlayer.getCurrentPosition() != 0 && this.mediaPlayer.getDuration() != 0 && !this.finished) {
                C1128g.m2333W("onCompletion. " + this.mediaPlayer.getCurrentPosition() + BridgeUtil.SPLIT_MARK + this.mediaPlayer.getDuration());
                this.finished = true;
                if (this.ez != null) {
                    this.ez.videoEnded();
                }
                report(C0893a.cR);
            }
        } catch (Throwable th) {
        }
    }

    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacks(this.tick);
        if (this.videoView != null) {
            this.videoView.pause();
            this.currentPosition = this.videoView.getCurrentPosition();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.videoView != null && !this.finished) {
            try {
                this.mediaPlayer.seekTo(this.currentPosition);
                this.mediaPlayer.start();
                this.mHandler.postDelayed(this.tick, 33);
            } catch (Throwable th) {
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("currentPosition", this.currentPosition);
        bundle.putBoolean("started", this.started);
        super.onSaveInstanceState(bundle);
    }

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.currentPosition = bundle.getInt("currentPosition");
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        try {
            if (this.videoView != null) {
                this.videoView.setOnCompletionListener(null);
                this.videoView.setOnErrorListener(null);
                this.videoView.setOnPreparedListener(null);
                this.videoView.suspend();
                this.videoView = null;
            }
        } catch (Throwable th) {
        }
        try {
            if (this.mediaPlayer != null) {
                this.mediaPlayer.release();
                this.mediaPlayer = null;
            }
        } catch (Throwable th2) {
        }
    }

    private void checkProgress() {
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

    private void an() {
        this.ew = AnimationUtils.loadAnimation(getActivity(), C0889R.anim.apnxt_stream_loader);
        this.ew.setAnimationListener(new C09433(this));
        this.ew.setRepeatCount(-1);
        this.ew.setRepeatMode(1);
    }

    private void report(String str) {
        this.ez.report(str, "S2");
    }
}
