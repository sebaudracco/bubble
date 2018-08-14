package com.vungle.publisher;

import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class lm implements OnAudioFocusChangeListener {
    @Inject
    AudioManager f3076a;
    private boolean f3077b = false;

    @Inject
    lm() {
    }

    public int m4339a() {
        return this.f3076a.getStreamVolume(3);
    }

    public float m4340b() {
        float streamVolume = (float) this.f3076a.getStreamVolume(3);
        int streamMaxVolume = this.f3076a.getStreamMaxVolume(3);
        return streamMaxVolume > 0 ? streamVolume / ((float) streamMaxVolume) : 0.0f;
    }

    public void m4341c() {
        Logger.v(Logger.DEVICE_TAG, "ad requests audio focus");
        if (this.f3077b) {
            Logger.v(Logger.DEVICE_TAG, "ad already has audio focus");
        } else if (this.f3076a.requestAudioFocus(this, 3, 3) == 1) {
            Logger.v(Logger.DEVICE_TAG, "audio focus request granted");
            this.f3077b = true;
        } else {
            Logger.v(Logger.DEVICE_TAG, "audio focus request rejected");
            this.f3077b = false;
        }
    }

    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case -3:
            case -2:
            case -1:
                this.f3077b = false;
                break;
            case 1:
                this.f3077b = true;
                break;
        }
        Logger.v(Logger.DEVICE_TAG, "audio focus changed to " + this.f3077b + ", with focusChange code " + focusChange);
    }

    public void m4342d() {
        Logger.v(Logger.DEVICE_TAG, "ad abandoning audio focus");
        this.f3076a.abandonAudioFocus(this);
        this.f3077b = false;
    }
}
