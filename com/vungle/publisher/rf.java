package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
public class rf extends mj {
    rg f10962b;
    rr f10963c;

    rf(Context context) {
        super(context);
    }

    public boolean m13870a() {
        if (m13871b()) {
            this.f10963c.b(this);
        } else if (!this.f10962b.b()) {
            int historyIndex = getHistoryIndex();
            Logger.m13644v(Logger.AD_TAG, "back pressed at index: " + historyIndex);
            if (historyIndex > 0) {
                goBack();
            }
        }
        return true;
    }

    int getHistoryIndex() {
        return copyBackForwardList().getCurrentIndex();
    }

    public boolean m13871b() {
        return this.f10962b.a();
    }
}
