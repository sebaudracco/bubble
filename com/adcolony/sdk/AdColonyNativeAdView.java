package com.adcolony.sdk;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.adcolony.sdk.aa.C0595a;
import org.json.JSONObject;

public class AdColonyNativeAdView extends bc {
    private EngagementButton f437c;
    private boolean f438d;
    private String f439e;
    private String f440f;
    private String f441g;

    class C05911 implements OnClickListener {
        final /* synthetic */ AdColonyNativeAdView f416a;

        C05911(AdColonyNativeAdView adColonyNativeAdView) {
            this.f416a = adColonyNativeAdView;
        }

        public void onClick(View view) {
            if (this.f416a.m593c()) {
                new C0595a().m622a("Ignoring engagement click as view has been destroyed.").m624a(aa.f481e);
                return;
            }
            JSONObject a = C0802y.m1453a();
            C0802y.m1462a(a, "id", this.f416a.getAdSessionId());
            new af("AdSession.on_native_engagement", this.f416a.getContainer().m1057c(), a).m695b();
        }
    }

    public class EngagementButton extends Button {
        boolean f417a;
        OnClickListener f418b;
        final /* synthetic */ AdColonyNativeAdView f419c;

        EngagementButton(AdColonyNativeAdView this$0, Context context) {
            this.f419c = this$0;
            super(context);
        }

        public void setOnClickListener(OnClickListener listener) {
            if (!this.f417a) {
                super.setOnClickListener(listener);
                this.f418b = listener;
                this.f417a = true;
            }
        }

        public OnClickListener getOnClickListener() {
            return this.f418b;
        }
    }

    public /* bridge */ /* synthetic */ boolean destroy() {
        return super.destroy();
    }

    public /* bridge */ /* synthetic */ String getZoneID() {
        return super.getZoneID();
    }

    public /* bridge */ /* synthetic */ boolean pause() {
        return super.pause();
    }

    public /* bridge */ /* synthetic */ boolean resume() {
        return super.resume();
    }

    public /* bridge */ /* synthetic */ boolean setMuted(boolean z) {
        return super.setMuted(z);
    }

    public /* bridge */ /* synthetic */ boolean setVolume(float f) {
        return super.setVolume(f);
    }

    AdColonyNativeAdView(Context context, af message, C0592e listener) {
        super(context, message, listener);
        JSONObject c = message.m698c();
        setNative(true);
        this.f438d = C0802y.m1477d(c, "engagement_enabled");
        this.f439e = C0802y.m1468b(c, "engagement_click_action");
        this.f440f = C0802y.m1468b(c, "engagement_click_action_type");
        this.f441g = C0802y.m1468b(c, "engagement_text");
        if (this.f438d) {
            this.f437c = new EngagementButton(this, context);
            this.f437c.setText(this.f441g);
            this.f437c.setOnClickListener(new C05911(this));
        }
    }

    public String getAdvertiserName() {
        if (!m593c()) {
            return super.getAdvertiserName();
        }
        new C0595a().m622a("Ignoring call to getAdvertiserName() as view has been destroyed").m624a(aa.f481e);
        return "";
    }

    public ImageView getIcon() {
        ImageView icon = super.getIcon();
        if (icon == null) {
            return null;
        }
        if (!m593c()) {
            return icon;
        }
        new C0595a().m622a("Ignoring call to getIcon() as view has been destroyed").m624a(aa.f481e);
        return null;
    }

    public String getTitle() {
        if (!m593c()) {
            return super.getTitle();
        }
        new C0595a().m622a("Ignoring call to getTitle() as view has been destroyed").m624a(aa.f481e);
        return "";
    }

    public String getDescription() {
        if (!m593c()) {
            return super.getDescription();
        }
        new C0595a().m622a("Ignoring call to getDescription() as view has been destroyed").m624a(aa.f481e);
        return "";
    }

    public boolean isEngagementEnabled() {
        if (!m593c()) {
            return this.f438d;
        }
        new C0595a().m622a("Ignoring call to isEngagementEnabled() as view has been destroyed").m624a(aa.f481e);
        return false;
    }

    public EngagementButton getEngagementButton() {
        if (!m593c()) {
            return this.f437c;
        }
        new C0595a().m622a("Ignoring call to getEngagementButton() as view has been destroyed").m624a(aa.f481e);
        return null;
    }
}
