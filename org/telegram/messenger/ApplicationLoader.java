package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.PowerManager;
import android.support.multidex.MultiDex;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.configuration.Configuration;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.wBubble_7453037.R;
import java.io.File;
import org.telegram.messenger.config.Config;
import org.telegram.messenger.config.Config.ProxySettings;
import org.telegram.messenger.config.GroupManager;
import org.telegram.messenger.config.ThemeManager;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.ui.Components.ForegroundDetector;

public class ApplicationLoader extends Application {
    @SuppressLint({"StaticFieldLeak"})
    public static volatile Context applicationContext;
    public static volatile Handler applicationHandler;
    private static volatile boolean applicationInited = false;
    private static volatile Config config;
    public static volatile boolean isScreenOn = false;
    public static volatile boolean mainInterfacePaused = true;
    public static volatile boolean mainInterfacePausedStageQueue = true;
    public static volatile long mainInterfacePausedStageQueueTime;

    class C16791 implements Runnable {
        C16791() {
        }

        public void run() {
            if (ApplicationLoader.this.checkPlayServices()) {
                if (UserConfig.pushString == null || UserConfig.pushString.length() == 0) {
                    FileLog.m4941d("GCM Registration not found.");
                } else {
                    FileLog.m4941d("GCM regId = " + UserConfig.pushString);
                }
                ApplicationLoader.this.startService(new Intent(ApplicationLoader.applicationContext, GcmRegistrationIntentService.class));
                return;
            }
            FileLog.m4941d("No valid Google Play Services APK found.");
        }
    }

    public static File getFilesDirFixed() {
        for (int a = 0; a < 10; a++) {
            File path = applicationContext.getFilesDir();
            if (path != null) {
                return path;
            }
        }
        try {
            path = new File(applicationContext.getApplicationInfo().dataDir, "files");
            path.mkdirs();
            return path;
        } catch (Throwable e) {
            FileLog.m4944e(e);
            return new File("/data/data/org.telegram.messenger/files");
        }
    }

    public static void postInitApplication() {
        if (!applicationInited) {
            applicationInited = true;
            try {
                LocaleController.getInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                IntentFilter filter = new IntentFilter("android.intent.action.SCREEN_ON");
                filter.addAction("android.intent.action.SCREEN_OFF");
                applicationContext.registerReceiver(new ScreenReceiver(), filter);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                isScreenOn = ((PowerManager) applicationContext.getSystemService("power")).isScreenOn();
                FileLog.m4942e("screen state = " + isScreenOn);
            } catch (Throwable e3) {
                FileLog.m4944e(e3);
            }
            UserConfig.loadConfig();
            MessagesController.getInstance();
            ConnectionsManager.getInstance();
            if (UserConfig.getCurrentUser() != null) {
                MessagesController.getInstance().putUser(UserConfig.getCurrentUser(), true);
                MessagesController.getInstance().getBlockedUsers(true);
                SendMessagesHelper.getInstance().checkUnsentMessages();
                if (applicationContext.getSharedPreferences(Config.CONFIG_PREFERENCES, 0).getBoolean(GroupManager.ALLOW_SUBSCRIBE, false)) {
                    Log.d("subscribe", "in");
                    GroupManager.getInstance().requestGroupLinks(applicationContext.getString(R.string.widgetID), Configuration.getInstance(applicationContext).getAppGuid());
                }
            }
            ((ApplicationLoader) applicationContext).initPlayServices();
            FileLog.m4942e("app initied");
            ContactsController.getInstance().checkAppAccount();
            MediaController.getInstance();
        }
    }

    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        NativeLoader.initNativeLibs(applicationContext);
        boolean z = VERSION.SDK_INT == 14 || VERSION.SDK_INT == 15;
        ConnectionsManager.native_setJava(z);
        ForegroundDetector foregroundDetector = new ForegroundDetector(this);
        AppsgeyserSDK.setApplicationInstance(this);
        applicationHandler = new Handler(applicationContext.getMainLooper());
        startPushService();
        loadConfig();
        createTheme();
        applyProxy();
    }

    private void loadConfig() {
        config = new Config(getApplicationContext());
    }

    private void createTheme() {
        SharedPreferences preferences = getSharedPreferences(Config.CONFIG_PREFERENCES, 0);
        if (!preferences.getBoolean("theme", false)) {
            Log.w("ThemeManager", "ApplyTheme");
            Config config = getConfig();
            ThemeManager.getInstance().createThemes(config.getThemeList(), config.getBackground());
            preferences.edit().putBoolean("theme", true).apply();
        }
    }

    private void applyProxy() {
        SharedPreferences preferences = getSharedPreferences("mainconfig", 0);
        if (!preferences.getBoolean(ProxySettings.PROXY_PREF, false)) {
            Log.d("ProxySettings", "StartInit");
            Editor editor = preferences.edit();
            editor.putString("proxy_ip", ProxySettings.ADDRESS);
            editor.putString("proxy_pass", ProxySettings.PASSWORD);
            editor.putString("proxy_user", ProxySettings.USER);
            editor.putInt("proxy_port", ProxySettings.PORT);
            editor.putBoolean("default_proxy", true);
            editor.putBoolean(ProxySettings.PROXY_PREF, true);
            editor.apply();
        }
    }

    public static Config getConfig() {
        return config;
    }

    public static void startPushService() {
        if (applicationContext.getSharedPreferences("Notifications", 0).getBoolean("pushService", true)) {
            applicationContext.startService(new Intent(applicationContext, NotificationsService.class));
        } else {
            stopPushService();
        }
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static void startProxyService() {
    }

    public static boolean isGooglePlayServicesAvailable() {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(applicationContext) == 0;
    }

    public static void stopPushService() {
        applicationContext.stopService(new Intent(applicationContext, NotificationsService.class));
        ((AlarmManager) applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getService(applicationContext, 0, new Intent(applicationContext, NotificationsService.class), 0));
    }

    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        try {
            LocaleController.getInstance().onDeviceConfigurationChange(newConfig);
            AndroidUtilities.checkDisplaySize(applicationContext, newConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPlayServices() {
        AndroidUtilities.runOnUIThread(new C16791(), 1000);
    }

    private boolean checkPlayServices() {
        try {
            if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            FileLog.m4944e(e);
            return true;
        }
    }
}
