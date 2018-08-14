package com.fyber.utils.testsuite;

import android.support.annotation.NonNull;

public class MediationBundleInfo {
    private boolean f6664a;
    private boolean f6665b;
    private String f6666c;
    private String f6667d;

    static class C2677a {
        private boolean f6660a = false;
        private boolean f6661b = false;
        private String f6662c = "";
        private String f6663d = "";

        C2677a() {
        }

        final C2677a m8568a(boolean z) {
            this.f6660a = z;
            if (z) {
                this.f6661b = true;
            }
            return this;
        }

        final C2677a m8566a() {
            this.f6661b = true;
            return this;
        }

        final C2677a m8567a(String str) {
            this.f6662c = str;
            return this;
        }

        final C2677a m8569b(String str) {
            if ("Applifier".equalsIgnoreCase(str)) {
                this.f6663d = "UnityAds";
            } else {
                this.f6663d = str;
            }
            return this;
        }

        final MediationBundleInfo m8570b() {
            return new MediationBundleInfo(this.f6660a, this.f6661b, this.f6662c, this.f6663d);
        }

        final void m8571c() {
            this.f6660a = false;
            this.f6661b = false;
            this.f6662c = "";
            this.f6663d = "";
        }
    }

    private MediationBundleInfo(boolean z, boolean z2, String str, @NonNull String str2) {
        this.f6664a = z;
        this.f6665b = z2;
        this.f6666c = str;
        this.f6667d = str2;
    }

    public String getNetworkName() {
        return this.f6667d;
    }

    public boolean isStarted() {
        return this.f6664a;
    }

    public boolean isIntegrated() {
        return this.f6665b;
    }

    public String getVersion() {
        return this.f6666c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f6667d.equalsIgnoreCase(((MediationBundleInfo) obj).f6667d);
    }

    public int hashCode() {
        return this.f6667d.hashCode();
    }
}
