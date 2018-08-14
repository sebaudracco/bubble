package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class qo {
    @Inject
    Provider<String> f3270a;
    @Inject
    Provider<String> f3271b;

    @Inject
    qo() {
    }

    public void m4572a() {
        Logger.d(Logger.FILE_TAG, "deleting ad temp directory");
        m4573a((String) this.f3270a.get());
    }

    public void m4574b() {
        Logger.d(Logger.FILE_TAG, "deleting old ad temp directory");
        m4573a((String) this.f3271b.get());
    }

    void m4573a(String str) {
        try {
            if (new File(str).exists()) {
                qr.a(str);
            } else {
                Logger.v(Logger.FILE_TAG, "ad temp directory does not exist " + str);
            }
        } catch (Exception e) {
            Logger.d(Logger.FILE_TAG, "error deleting ad temp directory " + str);
        }
    }
}
