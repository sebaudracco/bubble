package com.facebook.ads.internal.p067k;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.facebook.ads.internal.p056q.p077d.C2150a;

public class C2004a {
    private final Context f4704a;
    private final String f4705b;
    private final String f4706c;
    private boolean f4707d = false;
    private Messenger f4708e;
    private final ServiceConnection f4709f = new C20031(this);

    class C20031 implements ServiceConnection {
        final /* synthetic */ C2004a f4703a;

        C20031(C2004a c2004a) {
            this.f4703a = c2004a;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.f4703a.f4707d = true;
            this.f4703a.f4708e = new Messenger(iBinder);
            Message obtain = Message.obtain(null, 1);
            obtain.setData(this.f4703a.m6335b());
            try {
                this.f4703a.f4708e.send(obtain);
            } catch (Exception e) {
                C2150a.m6888a(e, this.f4703a.f4704a);
            }
            this.f4703a.f4704a.unbindService(this);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            try {
                this.f4703a.f4704a.unbindService(this);
            } catch (IllegalArgumentException e) {
            }
            this.f4703a.f4708e = null;
            this.f4703a.f4707d = false;
        }
    }

    public C2004a(Context context, String str, String str2) {
        this.f4704a = context;
        this.f4705b = str;
        this.f4706c = str2;
    }

    private Bundle m6335b() {
        Bundle bundle = new Bundle();
        bundle.putInt("PARAM_PROTOCOL_VERSION", 1);
        bundle.putString("PARAM_AN_UUID", this.f4706c);
        bundle.putString("PARAM_REQUEST_ID", this.f4705b);
        return bundle;
    }

    public void m6338a() {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.audiencenetwork.AudienceNetworkService");
        try {
            if (!this.f4704a.bindService(intent, this.f4709f, 1)) {
                this.f4704a.unbindService(this.f4709f);
            }
        } catch (Exception e) {
            C2150a.m6888a(e, this.f4704a);
        }
    }
}
