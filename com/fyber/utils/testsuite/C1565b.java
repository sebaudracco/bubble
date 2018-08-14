package com.fyber.utils.testsuite;

import com.fyber.annotations.FyberTestSuite;
import com.fyber.mediation.MediationAdapter;
import java.util.Collections;
import java.util.Map;

/* compiled from: IntegrationAnalyzerBridge */
public final class C1565b {
    private static Map<String, Map<String, Object>> f2640a;

    @FyberTestSuite(features = {})
    public static void m3426a(Map<String, MediationAdapter> map, Map<String, Map<String, Object>> map2) {
        if (map2 == null) {
            map2 = Collections.emptyMap();
        }
        f2640a = map2;
    }

    public static Map<String, Map<String, Object>> m3425a() {
        return f2640a;
    }
}
