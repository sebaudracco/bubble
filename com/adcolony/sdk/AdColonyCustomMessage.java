package com.adcolony.sdk;

import android.support.annotation.NonNull;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONObject;

public class AdColonyCustomMessage {
    String f397a;
    String f398b;

    class C05901 implements Runnable {
        final /* synthetic */ AdColonyCustomMessage f396a;

        C05901(AdColonyCustomMessage adColonyCustomMessage) {
            this.f396a = adColonyCustomMessage;
        }

        public void run() {
            AdColony.m536a();
            JSONObject a = C0802y.m1453a();
            C0802y.m1462a(a, "type", this.f396a.f397a);
            C0802y.m1462a(a, "message", this.f396a.f398b);
            new af("CustomMessage.native_send", 1, a).m695b();
        }
    }

    public AdColonyCustomMessage(@NonNull String type, @NonNull String message) {
        if (az.m894d(type) || az.m894d(message)) {
            this.f397a = type;
            this.f398b = message;
        }
    }

    public String getMessage() {
        return this.f398b;
    }

    public String getType() {
        return this.f397a;
    }

    public AdColonyCustomMessage set(String type, String message) {
        this.f397a = type;
        this.f398b = message;
        return this;
    }

    public void send() {
        try {
            AdColony.f368a.execute(new C05901(this));
        } catch (RejectedExecutionException e) {
        }
    }
}
