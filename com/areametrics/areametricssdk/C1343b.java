package com.areametrics.areametricssdk;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest.Builder;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;

class C1343b {
    private static final String f1856a = ("AMS-" + C1343b.class.getSimpleName());
    private boolean f1857b = false;
    private Context f1858c;
    private C1378g f1859d;
    private PendingIntent f1860e;

    class C13413 implements OnFailureListener {
        final /* synthetic */ C1343b f1855a;

        C13413(C1343b c1343b) {
            this.f1855a = c1343b;
        }

        public final void onFailure(@NonNull Exception exception) {
            this.f1855a.f1857b = false;
        }
    }

    interface C1342a {
        void mo2164a(Location location);
    }

    C1343b() {
    }

    static /* synthetic */ void m2437a(C1343b c1343b, LocationRequest locationRequest) {
        LocationRequest b = c1343b.m2439b();
        if (c1343b.m2440c() == null || b == null || locationRequest == null || b.getPriority() != locationRequest.getPriority()) {
            c1343b.f1857b = false;
            return;
        }
        c1343b.f1857b = true;
        Intent intent = new Intent(c1343b.m2441d(), AMLocationUpdatesBroadcastReceiver.class);
        intent.setAction("com.areametrics.areametricssdk.AMLocationUpdatesBroadcastReceiver.PROCESS_UPDATES");
        c1343b.f1860e = PendingIntent.getBroadcast(c1343b.m2441d(), 0, intent, 134217728);
        AreaMetricsSDK.INSTANCE.getLocProviderClient().requestLocationUpdates(locationRequest, c1343b.f1860e).addOnFailureListener(new C13413(c1343b));
    }

    private LocationRequest m2439b() {
        LocationRequest create = LocationRequest.create();
        String string = m2442e().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getString("lc_priority", "bp");
        int i = -1;
        switch (string.hashCode()) {
            case 3150:
                if (string.equals("bp")) {
                    i = 3;
                    break;
                }
                break;
            case 3336:
                if (string.equals("hp")) {
                    i = 0;
                    break;
                }
                break;
            case 3460:
                if (string.equals("lp")) {
                    i = 1;
                    break;
                }
                break;
            case 3522:
                if (string.equals("np")) {
                    i = 2;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                i = 100;
                break;
            case 1:
                i = 104;
                break;
            case 2:
                i = 105;
                break;
            default:
                i = 102;
                break;
        }
        create.setPriority(i);
        create.setFastestInterval((long) m2442e().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("lc_fastest_interval", 300000));
        create.setInterval((long) m2442e().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("lc_interval", 600000));
        create.setMaxWaitTime((long) m2442e().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("lc_max_wait", 1800000));
        create.setSmallestDisplacement(m2442e().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getFloat("lc_smallest_displacement", CloseButton.TEXT_SIZE_SP));
        return create;
    }

    private String m2440c() {
        Object obj = 1;
        try {
            if (!AreaMetricsSDK.INSTANCE.isGooglePlayServicesAvailable(m2441d())) {
                return null;
            }
            Object obj2 = ContextCompat.checkSelfPermission(m2441d(), "android.permission.ACCESS_FINE_LOCATION") == 0 ? 1 : null;
            if (ContextCompat.checkSelfPermission(m2441d(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                obj = null;
            }
            return obj2 != null ? "android.permission.ACCESS_FINE_LOCATION" : obj != null ? "android.permission.ACCESS_COARSE_LOCATION" : null;
        } catch (RuntimeException e) {
            return null;
        }
    }

    private Context m2441d() {
        return this.f1858c != null ? this.f1858c : AreaMetricsSDK.INSTANCE.getContext();
    }

    private C1378g m2442e() {
        return this.f1859d != null ? this.f1859d : AreaMetricsSDK.INSTANCE.getUserData();
    }

    final void m2443a() {
        if (!this.f1857b) {
            this.f1857b = true;
            if (this.f1860e != null) {
                AreaMetricsSDK.INSTANCE.getLocProviderClient().removeLocationUpdates(this.f1860e);
            }
            final LocationRequest b = m2439b();
            Task checkLocationSettings = LocationServices.getSettingsClient(m2441d()).checkLocationSettings(new Builder().addLocationRequest(b).build());
            if (checkLocationSettings == null) {
                this.f1857b = false;
            } else {
                checkLocationSettings.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>(this) {
                    final /* synthetic */ C1343b f1854b;

                    public final void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                        try {
                            task.getResult(ApiException.class);
                            try {
                                C1343b.m2437a(this.f1854b, b);
                            } catch (NullPointerException e) {
                                this.f1854b.f1857b = false;
                            }
                        } catch (ApiException e2) {
                            e2.getMessage();
                            this.f1854b.f1857b = false;
                        }
                    }
                });
            }
        }
    }

    final void m2444a(final C1342a c1342a) {
        if (m2440c() != null) {
            AreaMetricsSDK.INSTANCE.getLocProviderClient().getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>(this) {
                final /* synthetic */ C1343b f1852b;

                public final void onComplete(@NonNull Task<Location> task) {
                    c1342a.mo2164a((Location) task.getResult());
                }
            });
        } else {
            c1342a.mo2164a(null);
        }
    }
}
