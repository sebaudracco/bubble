package com.facebook.ads.internal.view.p053e.p085c;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import com.facebook.ads.internal.view.p053e.p054b.C1841k;
import com.facebook.ads.internal.view.p053e.p054b.C1842i;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p083a.C2224c;
import java.lang.ref.WeakReference;

public class C2270b extends C2224c implements OnAudioFocusChangeListener {
    private WeakReference<OnAudioFocusChangeListener> f5492a = null;
    private final C1844c f5493b = new C22661(this);
    private final C1842i f5494c = new C22672(this);
    private final C1841k f5495d = new C22693(this);

    class C22661 extends C1844c {
        final /* synthetic */ C2270b f5488a;

        C22661(C2270b c2270b) {
            this.f5488a = c2270b;
        }

        public void m7159a(C2229b c2229b) {
            ((AudioManager) this.f5488a.getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.f5488a.f5492a == null ? null : (OnAudioFocusChangeListener) this.f5488a.f5492a.get());
        }
    }

    class C22672 extends C1842i {
        final /* synthetic */ C2270b f5489a;

        C22672(C2270b c2270b) {
            this.f5489a = c2270b;
        }

        public void m7161a(C2233h c2233h) {
            ((AudioManager) this.f5489a.getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.f5489a.f5492a == null ? null : (OnAudioFocusChangeListener) this.f5489a.f5492a.get());
        }
    }

    class C22693 extends C1841k {
        final /* synthetic */ C2270b f5491a;

        class C22681 implements OnAudioFocusChangeListener {
            final /* synthetic */ C22693 f5490a;

            C22681(C22693 c22693) {
                this.f5490a = c22693;
            }

            public void onAudioFocusChange(int i) {
                if (this.f5490a.f5491a.getVideoView() != null && i <= 0) {
                    this.f5490a.f5491a.getVideoView().m7107a(false);
                }
            }
        }

        C22693(C2270b c2270b) {
            this.f5491a = c2270b;
        }

        public void m7163a(C2234j c2234j) {
            if (this.f5491a.f5492a == null || this.f5491a.f5492a.get() == null) {
                this.f5491a.f5492a = new WeakReference(new C22681(this));
            }
            ((AudioManager) this.f5491a.getContext().getApplicationContext().getSystemService("audio")).requestAudioFocus((OnAudioFocusChangeListener) this.f5491a.f5492a.get(), 3, 1);
        }
    }

    public C2270b(Context context) {
        super(context);
    }

    protected void mo3787a() {
        super.mo3787a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6328a(this.f5495d, this.f5493b, this.f5494c);
        }
    }

    protected void mo3788b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().m6330b(this.f5494c, this.f5493b, this.f5495d);
        }
        super.mo3788b();
    }

    public void onAudioFocusChange(int i) {
        if (getVideoView() != null && i <= 0) {
            getVideoView().m7107a(false);
        }
    }

    protected void onDetachedFromWindow() {
        ((AudioManager) getContext().getApplicationContext().getSystemService("audio")).abandonAudioFocus(this.f5492a == null ? null : (OnAudioFocusChangeListener) this.f5492a.get());
        super.onDetachedFromWindow();
    }
}
