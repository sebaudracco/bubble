package com.facebook.ads.internal.view.p079a;

import android.text.TextUtils;

public class C2169d {
    private final C2174f f5228a;
    private boolean f5229b = true;

    public C2169d(C2174f c2174f) {
        this.f5228a = c2174f;
    }

    private static long m6949a(String str, String str2) {
        long j = -1;
        Object substring = str.substring(str2.length());
        if (!TextUtils.isEmpty(substring)) {
            try {
                Long valueOf = Long.valueOf(Long.parseLong(substring));
                if (valueOf.longValue() >= 0) {
                    j = valueOf.longValue();
                }
            } catch (NumberFormatException e) {
            }
        }
        return j;
    }

    public void m6950a() {
        if (!this.f5229b) {
            return;
        }
        if (this.f5228a.canGoBack() || this.f5228a.canGoForward()) {
            this.f5229b = false;
        } else {
            this.f5228a.m6966a("void((function() {try {  if (!window.performance || !window.performance.timing || !document ||       !document.body || document.body.scrollHeight <= 0 ||       !document.body.children || document.body.children.length < 1) {    return;  }  var nvtiming__an_t = window.performance.timing;  if (nvtiming__an_t.responseEnd > 0) {    console.log('ANNavResponseEnd:'+nvtiming__an_t.responseEnd);  }  if (nvtiming__an_t.domContentLoadedEventStart > 0) {    console.log('ANNavDomContentLoaded:' + nvtiming__an_t.domContentLoadedEventStart);  }  if (nvtiming__an_t.loadEventEnd > 0) {    console.log('ANNavLoadEventEnd:' + nvtiming__an_t.loadEventEnd);  }} catch(err) {  console.log('an_navigation_timing_error:' + err.message);}})());");
        }
    }

    public void m6951a(String str) {
        if (!this.f5229b) {
            return;
        }
        if (str.startsWith("ANNavResponseEnd:")) {
            this.f5228a.m6965a(C2169d.m6949a(str, "ANNavResponseEnd:"));
        } else if (str.startsWith("ANNavDomContentLoaded:")) {
            this.f5228a.m6968b(C2169d.m6949a(str, "ANNavDomContentLoaded:"));
        } else if (str.startsWith("ANNavLoadEventEnd:")) {
            this.f5228a.m6969c(C2169d.m6949a(str, "ANNavLoadEventEnd:"));
        }
    }

    public void m6952a(boolean z) {
        this.f5229b = z;
    }
}
