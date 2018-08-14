package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings.System;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.mraid.C3225d.C3216b;
import org.telegram.messenger.voip.VoIPBaseService;

@SuppressLint({"ClickableViewAccessibility"})
public final class MraidMediaProcessor {
    private static final String f8063a = MraidMediaProcessor.class.getSimpleName();
    private RenderView f8064b;
    private C3225d f8065c;
    private RingerModeChangeReceiver f8066d;
    private C3218a f8067e;
    private HeadphonesPluggedChangeReceiver f8068f;

    class C32141 implements OnTouchListener {
        final /* synthetic */ MraidMediaProcessor f8052a;

        C32141(MraidMediaProcessor mraidMediaProcessor) {
            this.f8052a = mraidMediaProcessor;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    class C32152 implements OnKeyListener {
        final /* synthetic */ MraidMediaProcessor f8053a;

        C32152(MraidMediaProcessor mraidMediaProcessor) {
            this.f8053a = mraidMediaProcessor;
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (4 != i || keyEvent.getAction() != 0) {
                return false;
            }
            this.f8053a.f8065c.m10768b();
            return true;
        }
    }

    class C32173 implements C3216b {
        final /* synthetic */ MraidMediaProcessor f8054a;

        C32173(MraidMediaProcessor mraidMediaProcessor) {
            this.f8054a = mraidMediaProcessor;
        }

        public void mo6265a(C3225d c3225d) {
            Logger.m10359a(InternalLogLevel.INTERNAL, MraidMediaProcessor.f8063a, ">>> onPlayerCompleted");
            this.f8054a.f8064b.setAdActiveFlag(false);
            View c = c3225d.m10769c();
            if (c != null) {
                ((ViewGroup) c.getParent()).removeView(c);
            }
            c3225d.m10765a(null);
        }

        public void mo6266b(C3225d c3225d) {
            Logger.m10359a(InternalLogLevel.INTERNAL, MraidMediaProcessor.f8063a, ">>> onPlayerPrepared");
        }
    }

    public final class HeadphonesPluggedChangeReceiver extends BroadcastReceiver {
        final /* synthetic */ MraidMediaProcessor f8055a;
        private String f8056b;

        public HeadphonesPluggedChangeReceiver(MraidMediaProcessor mraidMediaProcessor, String str) {
            this.f8055a = mraidMediaProcessor;
            this.f8056b = str;
        }

        public void onReceive(Context context, Intent intent) {
            boolean z = true;
            if (intent != null && VoIPBaseService.ACTION_HEADSET_PLUG.equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("state", 0);
                Logger.m10359a(InternalLogLevel.INTERNAL, MraidMediaProcessor.f8063a, "Headphone plugged state changed: " + intExtra);
                MraidMediaProcessor mraidMediaProcessor = this.f8055a;
                String str = this.f8056b;
                if (1 != intExtra) {
                    z = false;
                }
                mraidMediaProcessor.m10729b(str, z);
            }
        }
    }

    public final class RingerModeChangeReceiver extends BroadcastReceiver {
        final /* synthetic */ MraidMediaProcessor f8057a;
        private String f8058b;

        public RingerModeChangeReceiver(MraidMediaProcessor mraidMediaProcessor, String str) {
            this.f8057a = mraidMediaProcessor;
            this.f8058b = str;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.media.RINGER_MODE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.media.EXTRA_RINGER_MODE", 2);
                Logger.m10359a(InternalLogLevel.INTERNAL, MraidMediaProcessor.f8063a, "Ringer mode action changed: " + intExtra);
                this.f8057a.m10726a(this.f8058b, 2 != intExtra);
            }
        }
    }

    public final class C3218a extends ContentObserver {
        final /* synthetic */ MraidMediaProcessor f8059a;
        private Context f8060b;
        private int f8061c = -1;
        private String f8062d;

        public C3218a(MraidMediaProcessor mraidMediaProcessor, String str, Context context, Handler handler) {
            this.f8059a = mraidMediaProcessor;
            super(handler);
            this.f8062d = str;
            this.f8060b = context;
        }

        public void onChange(boolean z) {
            super.onChange(z);
            if (this.f8060b != null) {
                int streamVolume = ((AudioManager) this.f8060b.getSystemService("audio")).getStreamVolume(3);
                if (streamVolume != this.f8061c) {
                    this.f8061c = streamVolume;
                    this.f8059a.m10725a(this.f8062d, streamVolume);
                }
            }
        }
    }

    public MraidMediaProcessor(RenderView renderView) {
        this.f8064b = renderView;
    }

    public void m10733a(String str, Activity activity) {
        this.f8065c = new C3225d(activity);
        this.f8065c.m10767a(str);
        ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.f8065c.setLayoutParams(layoutParams);
        ViewGroup relativeLayout = new RelativeLayout(activity);
        relativeLayout.setOnTouchListener(new C32141(this));
        relativeLayout.setBackgroundColor(-16777216);
        relativeLayout.addView(this.f8065c);
        viewGroup.addView(relativeLayout, new LayoutParams(-1, -1));
        this.f8065c.m10765a(relativeLayout);
        this.f8065c.requestFocus();
        this.f8065c.setOnKeyListener(new C32152(this));
        this.f8065c.m10766a(new C32173(this));
        this.f8065c.m10763a();
    }

    public void m10731a() {
        if (this.f8065c != null) {
            this.f8065c.m10768b();
            this.f8065c = null;
        }
    }

    public boolean m10735b() {
        Context b = C3105a.m10078b();
        if (b == null) {
            return false;
        }
        return 2 != ((AudioManager) b.getSystemService("audio")).getRingerMode();
    }

    public void m10732a(String str) {
        Context b = C3105a.m10078b();
        if (b != null && this.f8066d == null) {
            this.f8066d = new RingerModeChangeReceiver(this, str);
            b.registerReceiver(this.f8066d, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
        }
    }

    public void m10736c() {
        Context b = C3105a.m10078b();
        if (b != null && this.f8066d != null) {
            b.unregisterReceiver(this.f8066d);
            this.f8066d = null;
        }
    }

    public void m10734b(String str) {
        Context b = C3105a.m10078b();
        if (b != null && this.f8067e == null) {
            this.f8067e = new C3218a(this, str, b, new Handler());
            b.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.f8067e);
        }
    }

    public void m10738d() {
        Context b = C3105a.m10078b();
        if (b != null && this.f8067e != null) {
            b.getContentResolver().unregisterContentObserver(this.f8067e);
            this.f8067e = null;
        }
    }

    public int m10739e() {
        Context b = C3105a.m10078b();
        if (b == null) {
            return -1;
        }
        return ((AudioManager) b.getSystemService("audio")).getStreamVolume(3);
    }

    public boolean m10740f() {
        Context b = C3105a.m10078b();
        if (b == null) {
            return false;
        }
        return ((AudioManager) b.getSystemService("audio")).isWiredHeadsetOn();
    }

    public void m10737c(String str) {
        Context b = C3105a.m10078b();
        if (b != null && this.f8068f == null) {
            this.f8068f = new HeadphonesPluggedChangeReceiver(this, str);
            b.registerReceiver(this.f8068f, new IntentFilter(VoIPBaseService.ACTION_HEADSET_PLUG));
        }
    }

    public void m10741g() {
        Context b = C3105a.m10078b();
        if (b != null && this.f8068f != null) {
            b.unregisterReceiver(this.f8068f);
            this.f8068f = null;
        }
    }

    private void m10726a(String str, boolean z) {
        if (this.f8064b != null) {
            this.f8064b.m10630a(str, "fireDeviceMuteChangeEvent(" + z + ");");
        }
    }

    private void m10725a(String str, int i) {
        if (this.f8064b != null) {
            this.f8064b.m10630a(str, "fireDeviceVolumeChangeEvent(" + i + ");");
        }
    }

    private void m10729b(String str, boolean z) {
        if (this.f8064b != null) {
            this.f8064b.m10630a(str, "fireHeadphonePluggedEvent(" + z + ");");
        }
    }
}
