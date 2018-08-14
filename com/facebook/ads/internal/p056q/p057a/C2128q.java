package com.facebook.ads.internal.p056q.p057a;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.stepleaderdigital.reveal.Reveal;
import org.telegram.tgnet.ConnectionsManager;

public class C2128q implements OnSystemUiVisibilityChangeListener {
    private final View f5058a;
    private int f5059b;
    @Nullable
    private Window f5060c;
    private C2127a f5061d = C2127a.DEFAULT;
    private final Runnable f5062e = new C21251(this);

    class C21251 implements Runnable {
        final /* synthetic */ C2128q f5053a;

        C21251(C2128q c2128q) {
            this.f5053a = c2128q;
        }

        public void run() {
            this.f5053a.m6814a(false);
        }
    }

    public enum C2127a {
        DEFAULT,
        FULL_SCREEN
    }

    public C2128q(View view) {
        this.f5058a = view;
        this.f5058a.setOnSystemUiVisibilityChangeListener(this);
    }

    private void m6812a(int i, boolean z) {
        if (this.f5060c != null) {
            LayoutParams attributes = this.f5060c.getAttributes();
            if (z) {
                attributes.flags |= i;
            } else {
                attributes.flags &= i ^ -1;
            }
            this.f5060c.setAttributes(attributes);
        }
    }

    private void m6814a(boolean z) {
        if (!C2127a.DEFAULT.equals(this.f5061d)) {
            int i = 3840;
            if (!z) {
                i = 3847;
            }
            Handler handler = this.f5058a.getHandler();
            if (handler != null && z) {
                handler.removeCallbacks(this.f5062e);
                handler.postDelayed(this.f5062e, Reveal.CHECK_DELAY);
            }
            this.f5058a.setSystemUiVisibility(i);
        }
    }

    public void m6815a() {
        this.f5060c = null;
    }

    public void m6816a(Window window) {
        this.f5060c = window;
    }

    public void m6817a(C2127a c2127a) {
        this.f5061d = c2127a;
        switch (this.f5061d) {
            case FULL_SCREEN:
                m6812a((int) ConnectionsManager.FileTypeFile, true);
                m6812a(134217728, true);
                m6814a(false);
                return;
            default:
                m6812a((int) ConnectionsManager.FileTypeFile, false);
                m6812a(134217728, false);
                this.f5058a.setSystemUiVisibility(0);
                return;
        }
    }

    public void onSystemUiVisibilityChange(int i) {
        int i2 = this.f5059b ^ i;
        this.f5059b = i;
        if ((i2 & 2) != 0 && (i & 2) == 0) {
            m6814a(true);
        }
    }
}
