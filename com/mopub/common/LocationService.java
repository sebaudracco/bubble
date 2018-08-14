package com.mopub.common;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.logging.MoPubLog;
import java.math.BigDecimal;

public class LocationService {
    private static volatile LocationService sInstance;
    @Nullable
    @VisibleForTesting
    Location mLastKnownLocation;
    @VisibleForTesting
    long mLocationLastUpdatedMillis;

    public enum LocationAwareness {
        NORMAL,
        TRUNCATED,
        DISABLED;

        @Deprecated
        public com.mopub.common.MoPub.LocationAwareness getNewLocationAwareness() {
            if (this == TRUNCATED) {
                return com.mopub.common.MoPub.LocationAwareness.TRUNCATED;
            }
            if (this == DISABLED) {
                return com.mopub.common.MoPub.LocationAwareness.DISABLED;
            }
            return com.mopub.common.MoPub.LocationAwareness.NORMAL;
        }

        @Deprecated
        public static LocationAwareness fromMoPubLocationAwareness(com.mopub.common.MoPub.LocationAwareness awareness) {
            if (awareness == com.mopub.common.MoPub.LocationAwareness.DISABLED) {
                return DISABLED;
            }
            if (awareness == com.mopub.common.MoPub.LocationAwareness.TRUNCATED) {
                return TRUNCATED;
            }
            return NORMAL;
        }
    }

    private LocationService() {
    }

    @NonNull
    @VisibleForTesting
    static LocationService getInstance() {
        LocationService locationService = sInstance;
        if (locationService == null) {
            synchronized (LocationService.class) {
                try {
                    locationService = sInstance;
                    if (locationService == null) {
                        LocationService locationService2 = new LocationService();
                        try {
                            sInstance = locationService2;
                            locationService = locationService2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            locationService = locationService2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return locationService;
    }

    @Nullable
    public static Location getLastKnownLocation(@NonNull Context context, int locationPrecision, @NonNull com.mopub.common.MoPub.LocationAwareness locationAwareness) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(locationAwareness);
        if (locationAwareness == com.mopub.common.MoPub.LocationAwareness.DISABLED) {
            return null;
        }
        LocationService locationService = getInstance();
        if (isLocationFreshEnough()) {
            return locationService.mLastKnownLocation;
        }
        Location result = getMostRecentValidLocation(getLocationFromProvider(context, ValidLocationProvider.GPS), getLocationFromProvider(context, ValidLocationProvider.NETWORK));
        if (locationAwareness == com.mopub.common.MoPub.LocationAwareness.TRUNCATED) {
            truncateLocationLatLon(result, locationPrecision);
        }
        locationService.mLastKnownLocation = result;
        locationService.mLocationLastUpdatedMillis = SystemClock.elapsedRealtime();
        return result;
    }

    @Nullable
    @VisibleForTesting
    static Location getLocationFromProvider(@NonNull Context context, @NonNull ValidLocationProvider provider) {
        Location location = null;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(provider);
        if (ValidLocationProvider.access$000(provider, context)) {
            try {
                location = ((LocationManager) context.getSystemService("location")).getLastKnownLocation(provider.toString());
            } catch (SecurityException e) {
                MoPubLog.d("Failed to retrieve location from " + provider.toString() + " provider: access appears to be disabled.");
            } catch (IllegalArgumentException e2) {
                MoPubLog.d("Failed to retrieve location: device has no " + provider.toString() + " location provider.");
            } catch (NullPointerException e3) {
                MoPubLog.d("Failed to retrieve location: device has no " + provider.toString() + " location provider.");
            }
        }
        return location;
    }

    @Nullable
    @VisibleForTesting
    static Location getMostRecentValidLocation(@Nullable Location a, @Nullable Location b) {
        if (a == null) {
            return b;
        }
        return (b == null || a.getTime() > b.getTime()) ? a : b;
    }

    @VisibleForTesting
    static void truncateLocationLatLon(@Nullable Location location, int precision) {
        if (location != null && precision >= 0) {
            location.setLatitude(BigDecimal.valueOf(location.getLatitude()).setScale(precision, 5).doubleValue());
            location.setLongitude(BigDecimal.valueOf(location.getLongitude()).setScale(precision, 5).doubleValue());
        }
    }

    private static boolean isLocationFreshEnough() {
        LocationService locationService = getInstance();
        if (locationService.mLastKnownLocation != null && SystemClock.elapsedRealtime() - locationService.mLocationLastUpdatedMillis <= MoPub.getMinimumLocationRefreshTimeMillis()) {
            return true;
        }
        return false;
    }

    @Deprecated
    @VisibleForTesting
    public static void clearLastKnownLocation() {
        getInstance().mLastKnownLocation = null;
    }
}
