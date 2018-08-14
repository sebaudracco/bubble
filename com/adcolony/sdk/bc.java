package com.adcolony.sdk;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.adcolony.sdk.aa.C0595a;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

class bc extends FrameLayout {
    public static final int ADCOLONY_IAP_ENGAGEMENT_END_CARD = 0;
    public static final int ADCOLONY_IAP_ENGAGEMENT_OVERLAY = 1;
    float f420a = 1.0f;
    int f421b = 2;
    private C0673c f422c;
    private C0673c f423d;
    private C0592e f424e;
    private String f425f;
    private String f426g;
    private boolean f427h;
    private boolean f428i;
    private boolean f429j;
    private boolean f430k;
    private ImageView f431l;
    private String f432m = "";
    private String f433n = "";
    private String f434o = "";
    private String f435p = "";
    private ExecutorService f436q = Executors.newSingleThreadExecutor();

    class C06611 implements Runnable {
        final /* synthetic */ bc f846a;

        C06611(bc bcVar) {
            this.f846a = bcVar;
        }

        public void run() {
            JSONObject a = C0802y.m1453a();
            C0802y.m1462a(a, "id", this.f846a.f425f);
            while (!this.f846a.f428i) {
                boolean z;
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                this.f846a.getLocalVisibleRect(rect);
                this.f846a.getGlobalVisibleRect(rect2);
                ViewParent parent = this.f846a.getParent();
                if (parent != null) {
                    parent.getChildVisibleRect(this.f846a, rect2, null);
                }
                boolean z2 = rect.bottom - rect.top > this.f846a.f422c.m1083p() / 2;
                boolean z3;
                if ((rect2.bottom - rect2.top < this.f846a.f422c.m1083p() / 2 || rect2.bottom - rect2.top >= this.f846a.f422c.m1083p()) && this.f846a.f430k) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (rect.bottom > this.f846a.f422c.m1083p() || rect.bottom < 0 || rect.top < 0 || rect2.top <= 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z && !r3 && z2 && !this.f846a.f427h) {
                    String str;
                    this.f846a.f430k = true;
                    this.f846a.f427h = true;
                    if (this.f846a.f429j) {
                        str = "AdSession.on_native_ad_view_visible";
                    } else {
                        str = "AdSession.on_ad_view_visible";
                    }
                    new af(str, this.f846a.f422c.m1057c(), a).m695b();
                } else if ((!z2 || (z2 && z)) && this.f846a.f427h) {
                    this.f846a.f427h = false;
                    new af(this.f846a.f429j ? "AdSession.on_native_ad_view_hidden" : "AdSession.on_ad_view_hidden", this.f846a.f422c.m1057c(), a).m695b();
                    new C0595a().m622a("AdColonyAdView has been hidden.").m624a(aa.f480d);
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    bc(Context context, af afVar, C0592e c0592e) {
        super(context);
        this.f424e = c0592e;
        this.f435p = c0592e.f442a;
        this.f425f = C0802y.m1468b(afVar.m698c(), "id");
        new C0595a().m622a("Retrieving container tied to ad session id: ").m622a(this.f425f).m624a(aa.f478b);
        this.f422c = (C0673c) C0594a.m605a().m1283m().m1153b().get(this.f425f);
        setLayoutParams(new LayoutParams(this.f422c.m1084q(), this.f422c.m1083p()));
        addView(this.f422c);
        m587d();
    }

    public boolean destroy() {
        if (this.f428i) {
            new C0595a().m622a("Ignoring subsequent call to destroy()").m624a(aa.f481e);
            return false;
        } else if (!C0594a.m612b()) {
            return false;
        } else {
            C0740l a = C0594a.m605a();
            this.f428i = true;
            JSONObject a2 = C0802y.m1453a();
            C0802y.m1462a(a2, "id", this.f425f);
            af afVar = this.f429j ? new af("AdSession.on_native_ad_view_destroyed", this.f422c.m1057c(), a2) : new af("AdSession.on_ad_view_destroyed", this.f422c.m1057c(), a2);
            if (a.m1295y()) {
                a.m1269c(afVar);
            } else {
                afVar.m695b();
            }
            return true;
        }
    }

    boolean m591a() {
        C0690d m = C0594a.m605a().m1283m();
        m.m1147a(this.f422c);
        if (this.f423d != null) {
            m.m1147a(this.f423d);
        }
        C0691f c0691f = (C0691f) m.m1160g().remove(this.f425f);
        if (c0691f != null) {
            for (MediaPlayer mediaPlayer : c0691f.m1165c().m688c().values()) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
            }
            c0691f.m1167d().m774a().autoPause();
            c0691f.m1167d().m774a().release();
        }
        m.m1159f().remove(this.f425f);
        this.f422c = null;
        this.f424e = null;
        removeAllViews();
        this.f436q.shutdown();
        return true;
    }

    public boolean setVolume(float volume) {
        if (((double) volume) < 0.0d || ((double) volume) > MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) {
            return false;
        }
        if (this.f428i) {
            new C0595a().m622a("Ignoring call to setVolume as view has been destroyed.").m624a(aa.f481e);
            return false;
        }
        if (((double) volume) > 0.0d) {
            this.f420a = volume;
        }
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "id", this.f425f);
        C0802y.m1460a(a, "volume", (double) volume);
        new af(this.f429j ? "AdSession.on_native_ad_view_set_volume" : "AdSession.on_ad_view_destroyed", this.f422c.m1057c(), a).m695b();
        return true;
    }

    public boolean setMuted(boolean muted) {
        if (this.f428i) {
            new C0595a().m622a("Ignoring call to setMuted() as view has been destroyed").m624a(aa.f481e);
            return false;
        } else if (muted) {
            return setVolume(0.0f);
        } else {
            return setVolume(this.f420a);
        }
    }

    public String getZoneID() {
        if (!this.f428i) {
            return this.f435p;
        }
        new C0595a().m622a("Ignoring call to getZoneID() as view has been destroyed").m624a(aa.f481e);
        return "";
    }

    public boolean pause() {
        if (this.f428i) {
            new C0595a().m622a("Ignoring call to pause() as view has been destroyed").m624a(aa.f481e);
            return false;
        }
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "id", this.f425f);
        new af("AdSession.on_manual_pause", this.f422c.m1057c(), a).m695b();
        return true;
    }

    public boolean resume() {
        if (this.f428i) {
            new C0595a().m622a("Ignoring call to resume() as view has been destroyed").m624a(aa.f481e);
            return false;
        }
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "id", this.f425f);
        new af("AdSession.on_manual_resume", this.f422c.m1057c(), a).m695b();
        return true;
    }

    boolean m592b() {
        if (this.f426g.equals("") || !C0594a.m614d()) {
            return false;
        }
        this.f431l = new ImageView(C0594a.m613c());
        this.f431l.setImageBitmap(BitmapFactory.decodeFile(this.f426g));
        return true;
    }

    private void m587d() {
        try {
            this.f436q.submit(new C06611(this));
        } catch (RejectedExecutionException e) {
            JSONObject a = C0802y.m1453a();
            C0802y.m1462a(a, "id", this.f425f);
            new af("AdSession.on_error", this.f422c.m1057c(), a).m695b();
        }
    }

    void setNative(boolean isNative) {
        this.f429j = isNative;
    }

    void setAdvertiserName(String advertiserName) {
        this.f432m = advertiserName;
    }

    void setTitle(String title) {
        this.f433n = title;
    }

    void setDescription(String description) {
        this.f434o = description;
    }

    void setImageFilepath(String imageFilepath) {
        this.f426g = imageFilepath;
    }

    void setExpandedContainer(C0673c expandedContainer) {
        this.f423d = expandedContainer;
    }

    boolean m593c() {
        return this.f428i;
    }

    String getAdSessionId() {
        return this.f425f;
    }

    C0673c getContainer() {
        return this.f422c;
    }

    C0673c getExpandedContainer() {
        return this.f423d;
    }

    String getAdvertiserName() {
        return this.f432m;
    }

    ImageView getIcon() {
        return this.f431l;
    }

    String getTitle() {
        return this.f433n;
    }

    String getDescription() {
        return this.f434o;
    }

    C0592e getListener() {
        return this.f424e;
    }
}
