package com.fyber.utils.testsuite;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.fyber.C2409a;
import com.fyber.Fyber;
import com.fyber.ads.internal.C2426e;
import com.fyber.mediation.C2573a;
import com.fyber.mediation.MediationAdapterStarter;
import com.fyber.p086a.C2408a;
import com.fyber.utils.C2412c;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.testsuite.C2680c.C2679a;
import com.fyber.utils.testsuite.IntegrationReport.C2676a;
import com.fyber.utils.testsuite.MediationBundleInfo.C2677a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public abstract class IntegrationAnalyzer {
    protected static IntegrationReport f6647a;

    public enum FailReason {
        SDK_NOT_STARTED;
        
        private final String f6646a;

        private FailReason() {
            this.f6646a = r3;
        }

        public final String getMessage() {
            return this.f6646a;
        }
    }

    private IntegrationAnalyzer() {
    }

    public static void showTestSuite(Activity activity) {
        ((C2679a) new C2679a(C2426e.Interaction).m7860a("show")).m8580d().mo3907b();
        activity.startActivity(new Intent(activity.getApplicationContext(), TestSuiteActivity.class));
    }

    public static void analyze(@NonNull final IntegrationAnalysisListener integrationAnalysisListener) {
        if (!Fyber.getConfigs().m7607h()) {
            final FailReason failReason = FailReason.SDK_NOT_STARTED;
            Fyber.getConfigs();
            C2409a.m7595b(new C2412c() {
                public final void mo3844a() {
                    integrationAnalysisListener.onAnalysisFailed(failReason);
                }
            });
        } else if (f6647a != null) {
            m8551a(integrationAnalysisListener);
        } else {
            C2408a i = Fyber.getConfigs().m7608i();
            final C2676a c2676a = new C2676a();
            boolean b = m8558b();
            boolean a = m8555a();
            c2676a.m8559a(i.m7591a()).m8563b(i.m7593b()).m8565b(b).m8561a(a);
            if (a && b) {
                Map a2 = b.a();
                if (a2 != null) {
                    m8557b(c2676a, a2);
                    m8556b(integrationAnalysisListener, c2676a);
                    return;
                }
                Fyber.getConfigs().m7600a(new Runnable() {
                    public final void run() {
                        Exception e;
                        Map a;
                        try {
                            C2573a.f6454a.m8214a().get();
                        } catch (InterruptedException e2) {
                            e = e2;
                            FyberLogger.m8449e("IntegrationAnalyzer", "An error occurred while waiting for Mediation to start: " + e.getMessage());
                            a = b.a();
                            if (a != null) {
                                FyberLogger.m8451i("IntegrationAnalyzer", "You need at least one bundle integrated to obtain server side configurations");
                            } else {
                                IntegrationAnalyzer.m8557b(c2676a, a);
                            }
                            IntegrationAnalyzer.m8556b(integrationAnalysisListener, c2676a);
                        } catch (ExecutionException e3) {
                            e = e3;
                            FyberLogger.m8449e("IntegrationAnalyzer", "An error occurred while waiting for Mediation to start: " + e.getMessage());
                            a = b.a();
                            if (a != null) {
                                IntegrationAnalyzer.m8557b(c2676a, a);
                            } else {
                                FyberLogger.m8451i("IntegrationAnalyzer", "You need at least one bundle integrated to obtain server side configurations");
                            }
                            IntegrationAnalyzer.m8556b(integrationAnalysisListener, c2676a);
                        }
                        a = b.a();
                        if (a != null) {
                            IntegrationAnalyzer.m8557b(c2676a, a);
                        } else {
                            FyberLogger.m8451i("IntegrationAnalyzer", "You need at least one bundle integrated to obtain server side configurations");
                        }
                        IntegrationAnalyzer.m8556b(integrationAnalysisListener, c2676a);
                    }
                });
                return;
            }
            m8556b(integrationAnalysisListener, c2676a);
        }
    }

    private static void m8557b(C2676a c2676a, @NonNull Map<String, Map<String, Object>> map) {
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        m8554a(m8550a((Map) map), arrayList, arrayList2);
        c2676a.m8560a(arrayList).m8564b(arrayList2);
    }

    private static void m8556b(IntegrationAnalysisListener integrationAnalysisListener, C2676a c2676a) {
        f6647a = c2676a.m8562a();
        m8551a(integrationAnalysisListener);
    }

    private static void m8551a(final IntegrationAnalysisListener integrationAnalysisListener) {
        Fyber.getConfigs();
        C2409a.m7595b(new C2412c() {
            public final void mo3844a() {
                integrationAnalysisListener.onAnalysisSucceeded(IntegrationAnalyzer.f6647a);
            }
        });
    }

    private static List<MediationBundleInfo> m8550a(@NonNull Map<String, Map<String, Object>> map) {
        List<MediationBundleInfo> arrayList = new ArrayList();
        C2677a c2677a = new C2677a();
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Map map2 = (Map) entry.getValue();
            if (!str.equals("fyber")) {
                Object obj = map2.get(MediationAdapterStarter.FYBER_STARTED);
                c2677a.m8569b(str);
                if (obj != null) {
                    c2677a.m8566a().m8568a(obj.toString().equals(SchemaSymbols.ATTVAL_TRUE)).m8567a(map2.get(MediationAdapterStarter.ADAPTER_VERSION).toString());
                }
                arrayList.add(c2677a.m8570b());
                c2677a.m8571c();
            }
        }
        return arrayList;
    }

    private static boolean m8555a() {
        try {
            Class.forName("com.fyber.mediation.MediationAdapterStarter");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static boolean m8558b() {
        try {
            Class cls = Class.forName("com.fyber.mediation.AnnotationsBuildConfig");
            FyberLogger.m8451i("IntegrationAnalyzer", "fyber-annotations version: " + ((String) cls.getField("ANNOTATIONS_VERSION").get(null)));
            FyberLogger.m8451i("IntegrationAnalyzer", "fyber-annotations-compiler version: " + ((String) cls.getField("ANNOTATIONS_COMPILER_VERSION").get(null)));
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (NoSuchFieldException e2) {
            return false;
        } catch (IllegalAccessException e3) {
            return false;
        }
    }

    private static void m8554a(List<MediationBundleInfo> list, List<MediationBundleInfo> list2, List<MediationBundleInfo> list3) {
        for (MediationBundleInfo mediationBundleInfo : list) {
            if (mediationBundleInfo.isStarted()) {
                list2.add(mediationBundleInfo);
            } else {
                list3.add(mediationBundleInfo);
            }
        }
    }
}
