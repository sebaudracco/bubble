package com.facebook.ads.internal.view.p053e.p084d;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;

public interface C2330c {
    void mo3789a(int i);

    void mo3790a(C2222a c2222a);

    void mo3791a(boolean z);

    void mo3792b();

    void mo3793b(boolean z);

    void mo3794c();

    boolean mo3795d();

    void mo3796e();

    int getCurrentPosition();

    int getDuration();

    long getInitialBufferTime();

    C2222a getStartReason();

    C2336d getState();

    int getVideoHeight();

    int getVideoWidth();

    View getView();

    float getVolume();

    void setBackgroundPlaybackEnabled(boolean z);

    void setControlsAnchorView(View view);

    void setFullScreen(boolean z);

    void setRequestedVolume(float f);

    void setVideoMPD(@Nullable String str);

    void setVideoStateChangeListener(C2248e c2248e);

    void setup(Uri uri);
}
