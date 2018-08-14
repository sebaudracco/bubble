package com.mopub.common.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.os.Build.VERSION;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.mopub.common.CreativeOrientation;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Reflection.MethodBuilder;
import java.io.File;
import java.net.SocketException;

public class DeviceUtils {
    private static final int MAX_DISK_CACHE_SIZE = 104857600;
    private static final int MAX_MEMORY_CACHE_SIZE = 31457280;
    private static final int MIN_DISK_CACHE_SIZE = 31457280;

    @Deprecated
    public enum IP {
        IPv4,
        IPv6
    }

    private DeviceUtils() {
    }

    public static boolean isNetworkAvailable(@Nullable Context context) {
        boolean z = false;
        if (context == null || !isPermissionGranted(context, "android.permission.INTERNET")) {
            return z;
        }
        if (!isPermissionGranted(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().isConnected();
        } catch (NullPointerException e) {
            return z;
        }
    }

    public static int memoryCacheSizeBytes(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(C1404b.aw);
        long memoryClass = (long) activityManager.getMemoryClass();
        try {
            if (Utils.bitMaskContainsFlag(context.getApplicationInfo().flags, ApplicationInfo.class.getDeclaredField("FLAG_LARGE_HEAP").getInt(null))) {
                memoryClass = (long) ((Integer) new MethodBuilder(activityManager, "getLargeMemoryClass").execute()).intValue();
            }
        } catch (Exception e) {
            MoPubLog.d("Unable to reflectively determine large heap size.");
        }
        return (int) Math.min(31457280, ((memoryClass / 8) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) * PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
    }

    public static long diskCacheSizeBytes(File dir, long minSize) {
        long size = minSize;
        try {
            StatFs statFs = new StatFs(dir.getAbsolutePath());
            size = (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / 50;
        } catch (IllegalArgumentException e) {
            MoPubLog.d("Unable to calculate 2% of available disk space, defaulting to minimum");
        }
        return Math.max(Math.min(size, 104857600), 31457280);
    }

    public static long diskCacheSizeBytes(File dir) {
        return diskCacheSizeBytes(dir, 31457280);
    }

    public static int getScreenOrientation(@NonNull Activity activity) {
        return getScreenOrientationFromRotationAndOrientation(activity.getWindowManager().getDefaultDisplay().getRotation(), activity.getResources().getConfiguration().orientation);
    }

    static int getScreenOrientationFromRotationAndOrientation(int rotation, int orientation) {
        if (1 == orientation) {
            switch (rotation) {
                case 1:
                case 2:
                    return 9;
                default:
                    return 1;
            }
        } else if (2 == orientation) {
            switch (rotation) {
                case 2:
                case 3:
                    return 8;
                default:
                    return 0;
            }
        } else {
            MoPubLog.d("Unknown screen orientation. Defaulting to portrait.");
            return 9;
        }
    }

    public static void lockOrientation(@NonNull Activity activity, @NonNull CreativeOrientation creativeOrientation) {
        if (NoThrow.checkNotNull(creativeOrientation) && NoThrow.checkNotNull(activity)) {
            int requestedOrientation;
            int currentOrientation = getScreenOrientationFromRotationAndOrientation(((WindowManager) activity.getSystemService("window")).getDefaultDisplay().getRotation(), activity.getResources().getConfiguration().orientation);
            if (CreativeOrientation.PORTRAIT == creativeOrientation) {
                if (9 == currentOrientation) {
                    requestedOrientation = 9;
                } else {
                    requestedOrientation = 1;
                }
            } else if (CreativeOrientation.LANDSCAPE != creativeOrientation) {
                return;
            } else {
                if (8 == currentOrientation) {
                    requestedOrientation = 8;
                } else {
                    requestedOrientation = 0;
                }
            }
            activity.setRequestedOrientation(requestedOrientation);
        }
    }

    @TargetApi(17)
    public static Point getDeviceDimensions(@NonNull Context context) {
        Integer bestWidthPixels = null;
        Integer bestHeightPixels = null;
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT >= 17) {
            Point screenSize = new Point();
            display.getRealSize(screenSize);
            bestWidthPixels = Integer.valueOf(screenSize.x);
            bestHeightPixels = Integer.valueOf(screenSize.y);
        } else {
            try {
                bestWidthPixels = (Integer) new MethodBuilder(display, "getRawWidth").execute();
                bestHeightPixels = (Integer) new MethodBuilder(display, "getRawHeight").execute();
            } catch (Exception e) {
                MoPubLog.v("Display#getRawWidth/Height failed.", e);
            }
        }
        if (bestWidthPixels == null || bestHeightPixels == null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            bestWidthPixels = Integer.valueOf(displayMetrics.widthPixels);
            bestHeightPixels = Integer.valueOf(displayMetrics.heightPixels);
        }
        return new Point(bestWidthPixels.intValue(), bestHeightPixels.intValue());
    }

    public static boolean isPermissionGranted(@NonNull Context context, @NonNull String permission) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(permission);
        return ContextCompat.checkSelfPermission(context, permission) == 0;
    }

    @Nullable
    @Deprecated
    public static String getIpAddress(IP ip) throws SocketException {
        return null;
    }

    @Nullable
    @Deprecated
    public static String getHashedUdid(Context context) {
        return null;
    }
}
