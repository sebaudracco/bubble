package com.mobfox.sdk.services;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.mobfox.sdk.runnables.MobFoxRunnable;

public class MobFoxLocationService extends MobFoxBaseService implements LocationListener {
    public static final String DEFAULT_ERROR = "location not available";
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    final int MIN_DIST = 10;
    final int MIN_TIME = 50;
    Context context;
    String err = "";
    Listener listener;
    Location location = null;
    LocationManager locationManager;
    Handler mainHandler;
    MobFoxLocationService self;
    AsyncTask<Void, Void, Void> task;

    public interface Listener {
        void onError(String str);

        void onLocation(Location location);
    }

    public void callback() {
        if (this.location != null) {
            this.listener.onLocation(this.location);
        } else if (this.err.length() > 0) {
            this.listener.onError(this.err);
        } else {
            this.listener.onError(DEFAULT_ERROR);
        }
    }

    public MobFoxLocationService(Listener listener, final Context context) {
        super(context);
        this.listener = listener;
        this.context = context;
        this.self = this;
        this.mainHandler = new Handler(context.getMainLooper());
        this.task = new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... params) {
                MobFoxLocationService.this.locationManager = (LocationManager) context.getSystemService("location");
                if (MobFoxLocationService.this.locationManager == null) {
                    MobFoxLocationService.this.onFailed("location manager not available");
                } else {
                    Location location = MobFoxLocationService.this.locationManager.getLastKnownLocation("gps");
                    if (location != null) {
                        MobFoxLocationService.this.onSuccess(location);
                    } else if (MobFoxLocationService.this.locationManager.isProviderEnabled("gps")) {
                        new Handler(context.getMainLooper()).post(new MobFoxRunnable(context) {
                            public void mobFoxRun() {
                                if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                                    MobFoxLocationService.this.locationManager.requestLocationUpdates("network", 50, Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT, MobFoxLocationService.this.self);
                                }
                            }
                        });
                        new Handler(context.getMainLooper()).post(new MobFoxRunnable(context) {
                            public void mobFoxRun() {
                                if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                                    MobFoxLocationService.this.locationManager.requestLocationUpdates("gps", 50, Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT, MobFoxLocationService.this.self);
                                }
                            }
                        });
                    } else {
                        MobFoxLocationService.this.onFailed("gps not enabled");
                    }
                }
                return null;
            }
        };
    }

    public void execute() {
        if (ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            if (this.task != null && this.task.getStatus() == Status.PENDING) {
                this.task.execute(new Void[0]);
            }
        } else if (VERSION.SDK_INT >= 23) {
            initLocationDialog();
        } else {
            onFailed("please add location permission in manifest");
        }
    }

    protected void initLocationDialog() {
        try {
            ActivityCompat.requestPermissions((Activity) this.context, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
        } catch (Exception e) {
            onFailed("dialog failed");
        } catch (Throwable th) {
            onFailed("dialog failed");
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length <= 0 || grantResults[0] != 0) {
                    onFailed("location dialog not confirmed");
                    return;
                } else if (this.task != null && this.task.getStatus() == Status.PENDING) {
                    this.task.execute(new Void[0]);
                    return;
                } else {
                    return;
                }
            default:
                try {
                    onFailed("location dialog error");
                    return;
                } catch (Throwable th) {
                    onFailed("failed with location");
                }
        }
        onFailed("failed with location");
    }

    public void onLocationChanged(Location location) {
        this.locationManager.removeUpdates(this.self);
        if (location != null) {
            onSuccess(location);
        } else {
            onFailed(DEFAULT_ERROR);
        }
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        onFailed("onStatusChanged");
    }

    public void onProviderEnabled(String provider) {
        onFailed("onProviderEnabled");
    }

    public void onProviderDisabled(String provider) {
        onFailed("onProviderDisabled");
    }

    private void onSuccess(Location location) {
        this.location = location;
        run();
    }

    private void onFailed(String err) {
        this.err = err;
        run();
    }
}
