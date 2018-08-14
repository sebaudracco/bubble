package com.vungle.publisher;

import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.vungle.publisher.log.Logger;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.IOException;
import javax.inject.Inject;
import org.json.JSONException;

/* compiled from: vungle */
public abstract class tx {
    @Inject
    protected uj f3347a;

    protected void m4652a(ub ubVar, tw twVar) {
        try {
            if (m4654a(twVar.m4688b())) {
                try {
                    m4657c(ubVar, twVar);
                    return;
                } catch (Throwable e) {
                    Logger.d(Logger.NETWORK_TAG, e);
                    twVar.m4687a(603);
                } catch (Throwable e2) {
                    Logger.e(Logger.NETWORK_TAG, "IOException while handling response: " + twVar, e2);
                    twVar.m4687a(Settings.MAX_DYNAMIC_ACQUISITION);
                } catch (Throwable e22) {
                    Logger.e(Logger.NETWORK_TAG, "JSONException while handling response: " + twVar, e22);
                    twVar.m4687a(604);
                }
            }
            mo3017b(ubVar, twVar);
        } catch (Exception e3) {
            m4653a(ubVar, twVar, e3);
        }
    }

    protected void mo3017b(ub ubVar, tw twVar) {
        m4658d(ubVar, twVar);
    }

    protected void m4657c(ub ubVar, tw twVar) throws IOException, JSONException {
    }

    protected void m4653a(ub ubVar, tw twVar, Exception exception) {
        Logger.e(Logger.NETWORK_TAG, "error while handling response: " + twVar, exception);
        m4658d(ubVar, twVar);
    }

    protected void m4658d(ub ubVar, tw twVar) {
        Logger.w(Logger.NETWORK_TAG, ubVar.m4698a() + " failed permanently with response code " + twVar.m4688b());
    }

    protected boolean m4654a(int i) {
        return i == 200;
    }

    protected boolean m4656b(int i) {
        return (i == HttpStatus.SC_REQUEST_TIMEOUT || i == 603) ? false : true;
    }
}
