package com.fyber.mediation;

import android.app.Activity;
import com.fyber.mediation.admob.AdMobMediationAdapter;
import com.fyber.mediation.facebook.FacebookMediationAdapter;
import com.fyber.mediation.vungle.VungleMediationAdapter;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.testsuite.b;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class MediationAdapterStarter {
    public static final String ADAPTER_VERSION = "ADAPTER_VERSION";
    public static final String FYBER_STARTED = "FYBER_STARTED";
    private static final String TAG = "MediationAdapterStarter";
    public static AdaptersListener adaptersListener;

    private static boolean startVungle(Activity activity, Map<String, Object> configs, Map<String, MediationAdapter> map) {
        boolean adapterStartedSuccessfully = false;
        try {
            MediationAdapter adapter = new VungleMediationAdapter();
            configs.put(ADAPTER_VERSION, "5.3.2-r1");
            FyberLogger.m8448d(TAG, "Starting adapter Vungle with version 5.3.2-r1");
            adapterStartedSuccessfully = adapter.startAdapter(activity, configs);
            if (adapterStartedSuccessfully) {
                FyberLogger.m8448d(TAG, "Adapter Vungle with version 5.3.2-r1 was started successfully");
                map.put("vungle", adapter);
            } else {
                FyberLogger.m8448d(TAG, "Adapter Vungle with version 5.3.2-r1 was not started successfully");
            }
        } catch (Throwable throwable) {
            FyberLogger.m8449e(TAG, "Exception occurred while loading adapter Vungle with version 5.3.2-r1 - " + throwable.getCause());
        }
        return adapterStartedSuccessfully;
    }

    private static boolean startFacebookAudienceNetwork(Activity activity, Map<String, Object> configs, Map<String, MediationAdapter> map) {
        boolean adapterStartedSuccessfully = false;
        try {
            MediationAdapter adapter = new FacebookMediationAdapter();
            configs.put(ADAPTER_VERSION, "4.27.1-r1");
            FyberLogger.m8448d(TAG, "Starting adapter FacebookAudienceNetwork with version 4.27.1-r1");
            adapterStartedSuccessfully = adapter.startAdapter(activity, configs);
            if (adapterStartedSuccessfully) {
                FyberLogger.m8448d(TAG, "Adapter FacebookAudienceNetwork with version 4.27.1-r1 was started successfully");
                map.put("facebookaudiencenetwork", adapter);
            } else {
                FyberLogger.m8448d(TAG, "Adapter FacebookAudienceNetwork with version 4.27.1-r1 was not started successfully");
            }
        } catch (Throwable throwable) {
            FyberLogger.m8449e(TAG, "Exception occurred while loading adapter FacebookAudienceNetwork with version 4.27.1-r1 - " + throwable.getCause());
        }
        return adapterStartedSuccessfully;
    }

    private static boolean startAdMob(Activity activity, Map<String, Object> configs, Map<String, MediationAdapter> map) {
        boolean adapterStartedSuccessfully = false;
        try {
            MediationAdapter adapter = new AdMobMediationAdapter();
            configs.put(ADAPTER_VERSION, "15.0.0-r1");
            FyberLogger.m8448d(TAG, "Starting adapter AdMob with version 15.0.0-r1");
            adapterStartedSuccessfully = adapter.startAdapter(activity, configs);
            if (adapterStartedSuccessfully) {
                FyberLogger.m8448d(TAG, "Adapter AdMob with version 15.0.0-r1 was started successfully");
                map.put("admob", adapter);
            } else {
                FyberLogger.m8448d(TAG, "Adapter AdMob with version 15.0.0-r1 was not started successfully");
            }
        } catch (Throwable throwable) {
            FyberLogger.m8449e(TAG, "Exception occurred while loading adapter AdMob with version 15.0.0-r1 - " + throwable.getCause());
        }
        return adapterStartedSuccessfully;
    }

    public static Map<String, MediationAdapter> startAdapters(Activity activity, Map<String, Map<String, Object>> configs) {
        Map<String, MediationAdapter> map = new HashMap();
        getConfigsForAdapter(configs, "Vungle").put(FYBER_STARTED, Boolean.valueOf(startVungle(activity, getConfigsForAdapter(configs, "Vungle"), map)));
        getConfigsForAdapter(configs, "FacebookAudienceNetwork").put(FYBER_STARTED, Boolean.valueOf(startFacebookAudienceNetwork(activity, getConfigsForAdapter(configs, "FacebookAudienceNetwork"), map)));
        getConfigsForAdapter(configs, "AdMob").put(FYBER_STARTED, Boolean.valueOf(startAdMob(activity, getConfigsForAdapter(configs, "AdMob"), map)));
        return map;
    }

    public static int getAdaptersCount() {
        return 3;
    }

    private static Map<String, Object> getConfigsForAdapter(Map<String, Map<String, Object>> configs, String adapter) {
        Map<String, Object> config = (Map) configs.get(adapter.toLowerCase());
        if (config != null) {
            return config;
        }
        config = new HashMap();
        configs.put(adapter.toLowerCase(), config);
        return config;
    }

    private static Map<String, Map<String, Object>> getConfigs(Future<Map<String, Map<String, Object>>> futureConfig) {
        Exception e;
        Map<String, Map<String, Object>> configs = mergeConfigs(MediationConfigProvider.getRuntimeConfigs(), MediationConfigProvider.getConfigs());
        if (futureConfig != null) {
            try {
                configs = mergeConfigs(configs, (Map) futureConfig.get());
            } catch (InterruptedException e2) {
                e = e2;
                FyberLogger.m8450e(TAG, "Exception occurred", e);
                return configs;
            } catch (ExecutionException e3) {
                e = e3;
                FyberLogger.m8450e(TAG, "Exception occurred", e);
                return configs;
            }
        }
        return configs;
    }

    private static Map<String, Map<String, Object>> mergeConfigs(Map<String, Map<String, Object>> fromConfigs, Map<String, Map<String, Object>> intoConfigs) {
        if (fromConfigs == null || fromConfigs.isEmpty()) {
            FyberLogger.m8448d(TAG, "There were no configurations to override");
        } else {
            for (Entry<String, Map<String, Object>> entry : fromConfigs.entrySet()) {
                String network = (String) entry.getKey();
                Map<String, Object> adapterFromConfigs = (Map) entry.getValue();
                Map<String, Object> adapterIntoConfigs = (Map) intoConfigs.get(network);
                if (adapterIntoConfigs == null) {
                    adapterIntoConfigs = new HashMap();
                }
                if (adapterFromConfigs != null) {
                    adapterIntoConfigs.putAll(adapterFromConfigs);
                }
                intoConfigs.put(network, adapterIntoConfigs);
            }
        }
        return intoConfigs;
    }

    public static Map<String, MediationAdapter> startAdapters(Activity activity, Future<Map<String, Map<String, Object>>> future) {
        Map configs = getConfigs(future);
        Map<String, MediationAdapter> adapters = startAdapters(activity, configs);
        if (adaptersListener != null) {
            adaptersListener.startedAdapters(adapters.keySet(), configs);
        }
        b.a(adapters, configs);
        return adapters;
    }
}
