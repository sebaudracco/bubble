package android.support.v13.app;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.Arrays;

public class FragmentCompat {
    static final FragmentCompatImpl IMPL;

    interface FragmentCompatImpl {
        void requestPermissions(Fragment fragment, String[] strArr, int i);

        void setUserVisibleHint(Fragment fragment, boolean z);

        boolean shouldShowRequestPermissionRationale(Fragment fragment, String str);
    }

    static class FragmentCompatBaseImpl implements FragmentCompatImpl {
        FragmentCompatBaseImpl() {
        }

        public void setUserVisibleHint(Fragment f, boolean deferStart) {
        }

        public void requestPermissions(final Fragment fragment, final String[] permissions, final int requestCode) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    int[] grantResults = new int[permissions.length];
                    Context context = fragment.getActivity();
                    if (context != null) {
                        PackageManager packageManager = context.getPackageManager();
                        String packageName = context.getPackageName();
                        int permissionCount = permissions.length;
                        for (int i = 0; i < permissionCount; i++) {
                            grantResults[i] = packageManager.checkPermission(permissions[i], packageName);
                        }
                    } else {
                        Arrays.fill(grantResults, -1);
                    }
                    ((OnRequestPermissionsResultCallback) fragment).onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            });
        }

        public boolean shouldShowRequestPermissionRationale(Fragment fragment, String permission) {
            return false;
        }
    }

    @RequiresApi(15)
    static class FragmentCompatApi15Impl extends FragmentCompatBaseImpl {
        FragmentCompatApi15Impl() {
        }

        public void setUserVisibleHint(Fragment f, boolean deferStart) {
            f.setUserVisibleHint(deferStart);
        }
    }

    @RequiresApi(23)
    static class FragmentCompatApi23Impl extends FragmentCompatApi15Impl {
        FragmentCompatApi23Impl() {
        }

        public void requestPermissions(Fragment fragment, String[] permissions, int requestCode) {
            fragment.requestPermissions(permissions, requestCode);
        }

        public boolean shouldShowRequestPermissionRationale(Fragment fragment, String permission) {
            return fragment.shouldShowRequestPermissionRationale(permission);
        }
    }

    @RequiresApi(24)
    static class FragmentCompatApi24Impl extends FragmentCompatApi23Impl {
        FragmentCompatApi24Impl() {
        }

        public void setUserVisibleHint(Fragment f, boolean deferStart) {
            f.setUserVisibleHint(deferStart);
        }
    }

    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr);
    }

    static {
        if (VERSION.SDK_INT >= 24) {
            IMPL = new FragmentCompatApi24Impl();
        } else if (VERSION.SDK_INT >= 23) {
            IMPL = new FragmentCompatApi23Impl();
        } else if (VERSION.SDK_INT >= 15) {
            IMPL = new FragmentCompatApi15Impl();
        } else {
            IMPL = new FragmentCompatBaseImpl();
        }
    }

    @Deprecated
    public static void setMenuVisibility(Fragment f, boolean visible) {
        f.setMenuVisibility(visible);
    }

    public static void setUserVisibleHint(Fragment f, boolean deferStart) {
        IMPL.setUserVisibleHint(f, deferStart);
    }

    public static void requestPermissions(@NonNull Fragment fragment, @NonNull String[] permissions, int requestCode) {
        IMPL.requestPermissions(fragment, permissions, requestCode);
    }

    public static boolean shouldShowRequestPermissionRationale(@NonNull Fragment fragment, @NonNull String permission) {
        return IMPL.shouldShowRequestPermissionRationale(fragment, permission);
    }
}
