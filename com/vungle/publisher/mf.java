package com.vungle.publisher;

import android.app.AlertDialog;
import android.app.Fragment;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class mf extends Fragment {
    AlertDialog f3097a;
    p f3098b;
    boolean f3099c;
    @Inject
    mq f3100d;

    public abstract void mo3001a();

    public abstract String mo3003b();

    public boolean m4362a(int i, KeyEvent keyEvent) {
        return false;
    }

    public void mo3002a(boolean z) {
        Logger.v(Logger.AD_TAG, (z ? "gained" : "lost") + " window focus");
    }

    void m4360a(ImageView imageView) {
        imageView.setScaleType(ScaleType.FIT_CENTER);
    }
}
