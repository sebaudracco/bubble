package com.adcolony.sdk;

import android.support.annotation.NonNull;
import org.json.JSONObject;

public class AdColonyAdOptions {
    boolean f369a;
    boolean f370b;
    AdColonyUserMetadata f371c;
    JSONObject f372d = C0802y.m1453a();

    public AdColonyAdOptions enableConfirmationDialog(boolean confirmation_enabled) {
        this.f369a = confirmation_enabled;
        C0802y.m1465a(this.f372d, "confirmation_enabled", true);
        return this;
    }

    public AdColonyAdOptions enableResultsDialog(boolean results_enabled) {
        this.f370b = results_enabled;
        C0802y.m1465a(this.f372d, "results_enabled", true);
        return this;
    }

    public AdColonyAdOptions setOption(@NonNull String key, boolean value) {
        if (az.m894d(key)) {
            C0802y.m1465a(this.f372d, key, value);
        }
        return this;
    }

    public Object getOption(@NonNull String key) {
        return C0802y.m1450a(this.f372d, key);
    }

    public AdColonyAdOptions setOption(@NonNull String key, double value) {
        if (az.m894d(key)) {
            C0802y.m1460a(this.f372d, key, value);
        }
        return this;
    }

    public AdColonyAdOptions setOption(@NonNull String key, @NonNull String value) {
        if (key != null && az.m894d(key) && az.m894d(value)) {
            C0802y.m1462a(this.f372d, key, value);
        }
        return this;
    }

    public AdColonyAdOptions setUserMetadata(@NonNull AdColonyUserMetadata metadata) {
        this.f371c = metadata;
        C0802y.m1464a(this.f372d, "user_metadata", metadata.f449c);
        return this;
    }

    public AdColonyUserMetadata getUserMetadata() {
        return this.f371c;
    }
}
