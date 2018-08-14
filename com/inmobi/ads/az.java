package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.bv.C3025c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: NativeV2VisibilityTracker */
public class az implements ActivityLifecycleCallbacks {
    private static final String f7206b = az.class.getSimpleName();
    private boolean f7207a;
    private final Map<Context, bv> f7208c = new WeakHashMap();

    void m9458a(@NonNull Context context, @NonNull View view, @NonNull ai aiVar, @NonNull C3044h c3044h) {
        m9456a(context, aiVar, c3044h).m9491a(view, aiVar, 67);
    }

    @TargetApi(15)
    private bv m9456a(@NonNull Context context, @NonNull final ai aiVar, @NonNull C3044h c3044h) {
        bv bvVar = (bv) this.f7208c.get(context);
        if (bvVar == null) {
            if (context instanceof Activity) {
                bvVar = new C3076p(ay.m9452a(), (Activity) context);
            } else {
                bvVar = new bd(ay.m9452a(), c3044h);
            }
            bvVar.m9492a(new C3025c(this) {
                final /* synthetic */ az f7205b;

                public void mo6210a(List<View> list, List<View> list2) {
                    for (View view : list) {
                        NativeStrandVideoView nativeStrandVideoView;
                        if (aiVar instanceof au) {
                            nativeStrandVideoView = (NativeStrandVideoView) view.findViewById(Integer.MAX_VALUE);
                            if (nativeStrandVideoView != null) {
                                ((au) aiVar).m9434y().mo6204a(nativeStrandVideoView, true);
                            }
                        }
                    }
                    for (View view2 : list2) {
                        if (aiVar instanceof au) {
                            nativeStrandVideoView = (NativeStrandVideoView) view2.findViewById(Integer.MAX_VALUE);
                            if (nativeStrandVideoView != null) {
                                ((au) aiVar).m9434y().mo6204a(nativeStrandVideoView, false);
                            }
                        }
                    }
                }
            });
            this.f7208c.put(context, bvVar);
            if ((context instanceof Activity) && VERSION.SDK_INT >= 15 && !this.f7207a) {
                ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
                this.f7207a = true;
            }
        }
        return bvVar;
    }

    void m9459a(@NonNull Context context, @NonNull ai aiVar) {
        bv bvVar = (bv) this.f7208c.get(context);
        if (bvVar != null) {
            bvVar.m9488a((Object) aiVar);
            if (!bvVar.m9499h()) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7206b, "Impression tracker is free, removing it");
                m9457a(context);
            }
        }
    }

    @TargetApi(15)
    private void m9457a(@NonNull Context context) {
        bv bvVar = (bv) this.f7208c.remove(context);
        if (bvVar != null) {
            bvVar.mo6248e();
        }
        if ((context instanceof Activity) && VERSION.SDK_INT >= 15 && this.f7208c.isEmpty() && this.f7207a) {
            ((Activity) context).getApplication().unregisterActivityLifecycleCallbacks(this);
            this.f7207a = false;
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        bv bvVar = (bv) this.f7208c.get(activity);
        if (bvVar != null) {
            bvVar.mo6247d();
        }
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
        bv bvVar = (bv) this.f7208c.get(activity);
        if (bvVar != null) {
            bvVar.mo6246c();
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7206b, "Activity destroyed, removing visibility tracker");
        m9457a(activity);
    }
}
