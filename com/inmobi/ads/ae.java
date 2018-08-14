package com.inmobi.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.inmobi.ads.NativeStrandTimerView.C2936a;
import com.inmobi.ads.NativeStrandVideoView.C2946a;
import com.inmobi.ads.NativeStrandVideoView.OnPlaybackEventListener.PlaybackEvent;
import com.inmobi.ads.NativeStrandVideoView.OnQuartileCompletedListener.Quartile;
import com.inmobi.ads.NativeV2Asset.AssetType;
import com.inmobi.ads.NativeV2ScrollableContainer.C2963a;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* compiled from: NativeStrandLayoutInflater */
final class ae implements C2963a {
    private static final String f7052a = ae.class.getSimpleName();
    private static Handler f7053k = new Handler(Looper.getMainLooper());
    @NonNull
    private final WeakReference<Context> f7054b;
    @NonNull
    private final NativeV2DataModel f7055c;
    @NonNull
    private C2998c f7056d;
    @NonNull
    private C2996a f7057e;
    @Nullable
    private ba f7058f;
    @Nullable
    private C2997b f7059g;
    private aq f7060h;
    private int f7061i = 0;
    @NonNull
    private NativeStrandViewFactory f7062j;
    private boolean f7063l = false;

    /* compiled from: NativeStrandLayoutInflater */
    interface C2996a {
        void mo6189a(NativeV2Asset nativeV2Asset);
    }

    /* compiled from: NativeStrandLayoutInflater */
    interface C2997b {
        void mo6199a(at atVar);
    }

    /* compiled from: NativeStrandLayoutInflater */
    interface C2998c {
        void mo6188a(int i, NativeV2Asset nativeV2Asset);
    }

    ae(@NonNull Context context, @NonNull NativeV2DataModel nativeV2DataModel, @NonNull C2998c c2998c, @NonNull C2996a c2996a, @NonNull C2997b c2997b) {
        this.f7054b = new WeakReference(context);
        this.f7055c = nativeV2DataModel;
        this.f7062j = NativeStrandViewFactory.m8955a(context);
        this.f7056d = c2998c;
        this.f7057e = c2996a;
        this.f7059g = c2997b;
    }

    public Context m9269a() {
        return (Context) this.f7054b.get();
    }

    public void m9274a(@Nullable ba baVar) {
        this.f7058f = baVar;
    }

    public ag m9272a(@Nullable ag agVar, @NonNull ViewGroup viewGroup) {
        ViewGroup c = m9263c(agVar, viewGroup);
        if (!this.f7063l) {
            m9270a(c, viewGroup, this.f7055c.m9079b());
        }
        return c;
    }

    public ag m9276b(@Nullable ag agVar, @NonNull final ViewGroup viewGroup) {
        final ag c = m9263c(agVar, viewGroup);
        f7053k.post(new Runnable(this) {
            final /* synthetic */ ae f7038c;

            public void run() {
                if (!this.f7038c.f7063l) {
                    this.f7038c.m9270a(c, viewGroup, this.f7038c.f7055c.m9079b());
                }
            }
        });
        return c;
    }

    private ag m9263c(@Nullable ag agVar, @NonNull ViewGroup viewGroup) {
        View view = agVar == null ? (ag) this.f7062j.m8983a(m9269a(), this.f7055c.m9079b()) : agVar;
        if (view.getChildCount() > 0) {
            NativeStrandViewFactory.m8955a(m9269a()).m8985a((ViewGroup) view);
            NativeStrandViewFactory.m8960a(view, this.f7055c.m9079b().m9001b());
        }
        view.setLayoutParams(NativeStrandViewFactory.m8952a(this.f7055c.m9079b(), viewGroup));
        return view;
    }

    public ViewGroup m9270a(@NonNull ViewGroup viewGroup, @NonNull ViewGroup viewGroup2, @NonNull ak akVar) {
        return m9260b(viewGroup, viewGroup2, akVar);
    }

    public ViewGroup m9271a(@NonNull ViewGroup viewGroup, @NonNull ak akVar) {
        ViewGroup viewGroup2 = (ViewGroup) this.f7062j.m8983a(m9269a(), (NativeV2Asset) akVar);
        if (viewGroup2 != null) {
            viewGroup2.setLayoutParams(NativeStrandViewFactory.m8952a((NativeV2Asset) akVar, viewGroup));
        }
        return viewGroup2;
    }

    public int mo6172a(int i) {
        this.f7061i = i;
        if (this.f7056d != null) {
            this.f7056d.mo6188a(i, this.f7055c.m9080b(i));
        }
        return m9266e();
    }

    private void m9257a(final at atVar, NativeStrandTimerView nativeStrandTimerView) {
        nativeStrandTimerView.setTimerEventsListener(new C2936a(this) {
            final /* synthetic */ ae f7040b;

            public void mo6168a() {
                if (this.f7040b.f7059g != null) {
                    this.f7040b.f7059g.mo6199a(atVar);
                }
            }
        });
    }

    private ViewGroup m9260b(@NonNull ViewGroup viewGroup, @NonNull ViewGroup viewGroup2, @NonNull ak akVar) {
        Iterator it = akVar.iterator();
        while (it.hasNext()) {
            NativeV2Asset nativeV2Asset = (NativeV2Asset) it.next();
            if (AssetType.ASSET_TYPE_CONTAINER != nativeV2Asset.m8986a()) {
                final View a = NativeStrandViewFactory.m8955a(m9269a()).m8983a(m9269a(), nativeV2Asset);
                if (a != null) {
                    if (nativeV2Asset.m9021p() != -1) {
                        a.setVisibility(4);
                        f7053k.postDelayed(new Runnable(this) {
                            final /* synthetic */ ae f7042b;

                            public void run() {
                                a.setVisibility(0);
                            }
                        }, (long) (nativeV2Asset.m9021p() * 1000));
                    }
                    viewGroup.addView(a, NativeStrandViewFactory.m8952a(nativeV2Asset, viewGroup));
                    if (VERSION.SDK_INT >= 15 && AssetType.ASSET_TYPE_VIDEO == nativeV2Asset.m8986a()) {
                        m9258a((aw) nativeV2Asset, ((NativeStrandVideoWrapper) a).getVideoView());
                    }
                    m9256a(nativeV2Asset, a);
                    if (AssetType.ASSET_TYPE_TIMER == nativeV2Asset.m8986a()) {
                        a.setTag("timerView");
                        m9257a((at) nativeV2Asset, (NativeStrandTimerView) a);
                    }
                    if (VERSION.SDK_INT >= 15 && AssetType.ASSET_TYPE_VIDEO == nativeV2Asset.m8986a()) {
                        ((NativeStrandVideoWrapper) a).setVideoEventListener(this.f7058f);
                        ((NativeStrandVideoWrapper) a).m8918a();
                    }
                }
            } else if (nativeV2Asset.m9005c().equalsIgnoreCase("card_scrollable")) {
                NativeV2ScrollableContainer nativeV2ScrollableContainer = (NativeV2ScrollableContainer) this.f7062j.m8983a(m9269a(), nativeV2Asset);
                if (nativeV2ScrollableContainer != null) {
                    this.f7060h = ar.m9398a(nativeV2ScrollableContainer.getType(), this.f7055c, this);
                    if (this.f7060h != null) {
                        nativeV2ScrollableContainer.mo6215a((ak) nativeV2Asset, this.f7060h, this.f7061i, m9266e(), this);
                        viewGroup.addView(nativeV2ScrollableContainer, NativeStrandViewFactory.m8952a(nativeV2Asset, viewGroup));
                    }
                }
            } else {
                View view = (ViewGroup) NativeStrandViewFactory.m8955a(m9269a()).m8983a(m9269a(), nativeV2Asset);
                if (view != null) {
                    view = m9260b(view, viewGroup, (ak) nativeV2Asset);
                    viewGroup.addView(view, NativeStrandViewFactory.m8952a(nativeV2Asset, viewGroup));
                }
                m9256a(nativeV2Asset, view);
            }
        }
        return viewGroup;
    }

    private void m9256a(final NativeV2Asset nativeV2Asset, View view) {
        if (nativeV2Asset.m9013h()) {
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ae f7044b;

                public void onClick(View view) {
                    this.f7044b.f7057e.mo6189a(nativeV2Asset);
                }
            });
        }
    }

    @TargetApi(15)
    private void m9258a(@NonNull final aw awVar, @NonNull NativeStrandVideoView nativeStrandVideoView) {
        if (VERSION.SDK_INT >= 15) {
            ak akVar = (ak) awVar.m9026u();
            long currentTimeMillis = System.currentTimeMillis();
            if (!(akVar == null || 0 == akVar.m9364y())) {
                currentTimeMillis = akVar.m9364y();
            }
            if (akVar != null) {
                akVar.m9361a(currentTimeMillis);
            }
            nativeStrandVideoView.setId(Integer.MAX_VALUE);
            nativeStrandVideoView.m8911a(awVar);
            nativeStrandVideoView.setQuartileCompletedListener(new OnQuartileCompletedListener(this) {
                final /* synthetic */ ae f7046b;

                public void mo6169a(Quartile quartile) {
                    if (this.f7046b.f7058f != null) {
                        this.f7046b.f7058f.mo6193a(awVar, quartile);
                        if (Quartile.Q4 == quartile) {
                            try {
                                this.f7046b.f7058f.mo6197d(awVar);
                            } catch (Throwable e) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, ae.f7052a, "SDK encountered unexpected error in handling the onVideoCompleted event; " + e.getMessage());
                                C3135c.m10255a().m10279a(new C3132b(e));
                            }
                        }
                    }
                }
            });
            nativeStrandVideoView.setPlaybackEventListener(new OnPlaybackEventListener(this) {
                final /* synthetic */ ae f7048b;

                public void mo6170a(PlaybackEvent playbackEvent) {
                    if (this.f7048b.f7058f != null) {
                        switch (playbackEvent) {
                            case PLAYBACK_EVENT_PREPARED:
                                try {
                                    this.f7048b.f7058f.mo6190a();
                                    return;
                                } catch (Throwable e) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, ae.f7052a, "SDK encountered unexpected error in handling onVideoPrepared event; " + e.getMessage());
                                    C3135c.m10255a().m10279a(new C3132b(e));
                                    return;
                                }
                            case PLAYBACK_EVENT_PLAY:
                                try {
                                    this.f7048b.f7058f.mo6191a(awVar);
                                    return;
                                } catch (Throwable e2) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, ae.f7052a, "SDK encountered unexpected error in handling onVideoPlayed event; " + e2.getMessage());
                                    C3135c.m10255a().m10279a(new C3132b(e2));
                                    return;
                                }
                            case PLAYBACK_EVENT_PAUSE:
                                try {
                                    this.f7048b.f7058f.mo6195b(awVar);
                                    return;
                                } catch (Throwable e22) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, ae.f7052a, "SDK encountered unexpected error in handling onVideoPaused event; " + e22.getMessage());
                                    C3135c.m10255a().m10279a(new C3132b(e22));
                                    return;
                                }
                            case PLAYBACK_EVENT_RESUME:
                                try {
                                    this.f7048b.f7058f.mo6196c(awVar);
                                    return;
                                } catch (Throwable e222) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, ae.f7052a, "SDK encountered unexpected error in handling onVideoResumed event; " + e222.getMessage());
                                    C3135c.m10255a().m10279a(new C3132b(e222));
                                    return;
                                }
                            default:
                                return;
                        }
                    }
                }
            });
            nativeStrandVideoView.setMediaErrorListener(new C2946a(this) {
                final /* synthetic */ ae f7050b;

                public void mo6171a(int i) {
                    if (this.f7050b.f7058f != null) {
                        try {
                            this.f7050b.f7058f.mo6192a(awVar, i);
                        } catch (Throwable e) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, ae.f7052a, "SDK encountered unexpected error in handling the onVideoError event; " + e.getMessage());
                            C3135c.m10255a().m10279a(new C3132b(e));
                        }
                    }
                }
            });
            if (this.f7058f != null) {
                try {
                    this.f7058f.mo6194a(awVar, nativeStrandVideoView);
                } catch (Throwable e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7052a, "SDK encountered unexpected error in handling the onVideoViewCreated event; " + e.getMessage());
                    C3135c.m10255a().m10279a(new C3132b(e));
                }
            }
        }
    }

    private int m9266e() {
        if (this.f7061i == 0) {
            return GravityCompat.START;
        }
        if (this.f7055c.m9090j() - 1 == this.f7061i) {
            return GravityCompat.END;
        }
        return 1;
    }

    int m9275b() {
        return this.f7061i;
    }

    void m9273a(@NonNull View view) {
        this.f7062j.m8984a(view);
    }

    void m9277c() {
        this.f7063l = true;
        this.f7054b.clear();
        if (this.f7060h != null) {
            this.f7060h.destroy();
        }
    }
}
