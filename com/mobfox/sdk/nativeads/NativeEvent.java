package com.mobfox.sdk.nativeads;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.customevents.CustomEventNative;
import com.mobfox.sdk.customevents.CustomEventNativeListener;
import java.util.List;
import java.util.Map;

public class NativeEvent implements CustomEventNative {
    NativeAd ad;
    Context f9034c;
    CustomEventNativeListener listener;

    class C35851 implements OnClickListener {
        C35851() {
        }

        public void onClick(View v) {
            try {
                Intent launchBrowser = new Intent("android.intent.action.VIEW", Uri.parse(NativeEvent.this.ad.getLink()));
                launchBrowser.setFlags(ErrorDialogData.BINDER_CRASH);
                NativeEvent.this.f9034c.startActivity(launchBrowser);
            } catch (Exception e) {
                Log.d(Constants.MOBFOX_NATIVE, "browser activity failed");
            } catch (Throwable th) {
                Log.d(Constants.MOBFOX_NATIVE, "browser activity failed");
            }
            NativeEvent.this.listener.onNativeClicked(NativeEvent.this);
        }
    }

    public NativeEvent(NativeAd ad) {
        this.ad = ad;
    }

    public void load(Context context, CustomEventNativeListener listener, String networkID, List<Tracker> list, Map<String, Object> map) {
        this.f9034c = context;
        this.listener = listener;
        if (this.ad == null) {
            listener.onNativeError(new Exception("no ad"));
        } else {
            listener.onNativeReady(this, this.ad);
        }
    }

    public void registerViewForInteraction(View layout) {
        if (layout != null) {
            layout.setOnClickListener(new C35851());
        } else if (this.listener != null) {
            this.listener.onNativeError(new Exception("layout is null"));
        }
    }
}
