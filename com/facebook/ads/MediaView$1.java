package com.facebook.ads;

import com.facebook.ads.internal.view.C1838o;

class MediaView$1 implements C1838o {
    final /* synthetic */ MediaViewListener f4004a;
    final /* synthetic */ MediaView f4005b;

    MediaView$1(MediaView mediaView, MediaViewListener mediaViewListener) {
        this.f4005b = mediaView;
        this.f4004a = mediaViewListener;
    }

    public void mo3572a() {
        this.f4004a.onVolumeChange(this.f4005b, MediaView.a(this.f4005b).getVolume());
    }

    public void mo3573b() {
        this.f4004a.onPause(this.f4005b);
    }

    public void mo3574c() {
        this.f4004a.onPlay(this.f4005b);
    }

    public void mo3575d() {
        this.f4004a.onFullscreenBackground(this.f4005b);
    }

    public void mo3576e() {
        this.f4004a.onFullscreenForeground(this.f4005b);
    }

    public void mo3577f() {
        this.f4004a.onExitFullscreen(this.f4005b);
    }

    public void mo3578g() {
        this.f4004a.onEnterFullscreen(this.f4005b);
    }

    public void mo3579h() {
        this.f4004a.onComplete(this.f4005b);
    }
}
