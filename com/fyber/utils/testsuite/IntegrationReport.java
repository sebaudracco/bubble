package com.fyber.utils.testsuite;

import com.fyber.Fyber;
import java.util.Collections;
import java.util.List;

public class IntegrationReport {
    private boolean f6654a;
    private boolean f6655b;
    private String f6656c;
    private String f6657d;
    private List<MediationBundleInfo> f6658e;
    private List<MediationBundleInfo> f6659f;

    static class C2676a {
        private boolean f6648a;
        private boolean f6649b;
        private String f6650c;
        private String f6651d;
        private List<MediationBundleInfo> f6652e = Collections.emptyList();
        private List<MediationBundleInfo> f6653f = Collections.emptyList();

        C2676a() {
        }

        final C2676a m8561a(boolean z) {
            this.f6648a = z;
            return this;
        }

        final C2676a m8565b(boolean z) {
            this.f6649b = z;
            return this;
        }

        final C2676a m8559a(String str) {
            this.f6650c = str;
            return this;
        }

        final C2676a m8563b(String str) {
            this.f6651d = str;
            return this;
        }

        final C2676a m8560a(List<MediationBundleInfo> list) {
            this.f6652e = list;
            return this;
        }

        final C2676a m8564b(List<MediationBundleInfo> list) {
            this.f6653f = list;
            return this;
        }

        final IntegrationReport m8562a() {
            return new IntegrationReport(this.f6648a, this.f6649b, this.f6650c, this.f6651d, this.f6652e, this.f6653f);
        }
    }

    public String getFyberSdkVersion() {
        return Fyber.RELEASE_VERSION_STRING;
    }

    public String getTestSuiteVersion() {
        return "1.0.0";
    }

    public String getAppID() {
        return this.f6656c;
    }

    public String getUserID() {
        return this.f6657d;
    }

    public List<MediationBundleInfo> getStartedBundles() {
        return this.f6658e;
    }

    public List<MediationBundleInfo> getUnstartedBundles() {
        return this.f6659f;
    }

    public boolean isAnnotationsCorrectlyIntegrated() {
        return this.f6654a;
    }

    public boolean isAnnotationsCompatible() {
        return this.f6655b;
    }

    private IntegrationReport(boolean z, boolean z2, String str, String str2, List<MediationBundleInfo> list, List<MediationBundleInfo> list2) {
        this.f6654a = z;
        this.f6655b = z2;
        this.f6656c = str;
        this.f6657d = str2;
        this.f6658e = list;
        this.f6659f = list2;
    }
}
