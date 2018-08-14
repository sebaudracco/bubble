package com.cuebiq.cuebiqsdk.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import com.cuebiq.cuebiqsdk.model.wrapper.BluetoothDevice;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.slf4j.Marker;

public class Utils {
    private static final Pattern IPV4_LOCAL_PATTERN = Pattern.compile(IPV4_LOCAL_REGEX);
    private static final String IPV4_LOCAL_REGEX = "((((127)|(10))\\.[0-9]+\\.[0-9]+\\.[0-9]+)|(((172\\.(1[6-9]|2[0-9]|3[0-1]))|(192\\.168))\\.[0-9]+\\.[0-9]+)|(^172\\.3[0-1]\\.)|(^fe80:(:[0-9a-fA-F]{0,4}){0,4}))$";
    private static final Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
    private static final String IPV4_REGEX = "(([0-9](?!\\d)|[1-9][0-9](?!\\d)|1[0-9]{2}(?!\\d)|2[0-4][0-9](?!\\d)|25[0-5](?!\\d))[.]?){4}";
    private static final Pattern IPV6_LOCAL_PATTERN = Pattern.compile(IPV6_LOCAL_REGEX);
    private static final String IPV6_LOCAL_REGEX = "(^(fe80)|(FE80):(:[0-9a-fA-F]{0,4}){0,4})$";
    private static final Pattern IPV6_PATTERN = Pattern.compile(IPV6_REGEX);
    private static final String IPV6_REGEX = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";

    public static float getBatteryLevel(Context context) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver == null) {
                return 50.0f;
            }
            int intExtra = registerReceiver.getIntExtra(FirebaseAnalytics$Param.LEVEL, -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            return (intExtra == -1 || intExtra2 == -1) ? 50.0f : (((float) intExtra) / ((float) intExtra2)) * 100.0f;
        } catch (Throwable th) {
            return 50.0f;
        }
    }

    public static String getIPAddressV4() {
        try {
            for (NetworkInterface inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress inetAddress : Collections.list(inetAddresses.getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String toUpperCase = inetAddress.getHostAddress().toUpperCase();
                        if (isIPv4(toUpperCase) && !IPV4_LOCAL_PATTERN.matcher(toUpperCase).find()) {
                            CuebiqSDKImpl.log("Utils -> IP Pubblico: " + toUpperCase);
                            return toUpperCase;
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String getIPAddressV6() {
        try {
            for (NetworkInterface inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress inetAddress : Collections.list(inetAddresses.getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String toUpperCase = inetAddress.getHostAddress().toUpperCase();
                        int indexOf = toUpperCase.indexOf(37);
                        if (indexOf >= 0) {
                            toUpperCase = toUpperCase.substring(0, indexOf);
                        }
                        if (isIPv6(toUpperCase) && !IPV6_LOCAL_PATTERN.matcher(toUpperCase).find()) {
                            CuebiqSDKImpl.log("Utils -> IP Pubblico: " + toUpperCase);
                            return toUpperCase;
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String[] getMCCandMNC(Context context) {
        String networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
        if (!(networkOperator == null || networkOperator.isEmpty())) {
            try {
                return new String[]{networkOperator.substring(0, 3), networkOperator.substring(3)};
            } catch (Throwable th) {
            }
        }
        return null;
    }

    public static List<BluetoothDevice> getPairedDevices() {
        List<BluetoothDevice> arrayList = new ArrayList();
        try {
            Set<android.bluetooth.BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
            if (bondedDevices != null) {
                for (android.bluetooth.BluetoothDevice bluetoothDevice : bondedDevices) {
                    BluetoothDevice bluetoothDevice2 = new BluetoothDevice();
                    if (bluetoothDevice.getName() != null) {
                        if (bluetoothDevice.getBluetoothClass() != null) {
                            bluetoothDevice2.setName(bluetoothDevice.getName());
                            if (VERSION.SDK_INT >= 18) {
                                bluetoothDevice2.setType(Integer.valueOf(bluetoothDevice.getType()));
                            }
                            bluetoothDevice2.setDeviceClass(Integer.valueOf(bluetoothDevice.getBluetoothClass().getDeviceClass()));
                        }
                        arrayList.add(bluetoothDevice2);
                    }
                }
            }
        } catch (Exception e) {
            CuebiqSDKImpl.log("Bluetooth permission is not enabled, skip paired devices.");
        }
        return arrayList;
    }

    public static boolean isAndroidVersionNotSupported(int i) {
        if (VERSION.SDK_INT >= i) {
            return false;
        }
        CuebiqSDKImpl.log("CuebiqSDK works only on Android API Level " + i + Marker.ANY_NON_NULL_MARKER);
        return true;
    }

    public static boolean isFlushCounterActive(Context context) {
        int nextFlushCounter = PersistenceManagerFactory.get().getNextFlushCounter(context);
        if (nextFlushCounter == 0) {
            return false;
        }
        if (nextFlushCounter < 0) {
            PersistenceManagerFactory.get().setNextFlushingContent(context, 0);
            return false;
        } else if (nextFlushCounter <= 0) {
            return false;
        } else {
            PersistenceManagerFactory.get().setNextFlushingContent(context, nextFlushCounter - 1);
            return true;
        }
    }

    public static boolean isIPv4(String str) {
        return (str == null || str.isEmpty()) ? false : IPV4_PATTERN.matcher(str).find();
    }

    public static boolean isIPv6(String str) {
        return (str == null || str.isEmpty()) ? false : IPV6_PATTERN.matcher(str).find();
    }

    public static boolean isLocationEnabled(Context context) {
        if (VERSION.SDK_INT <= 22 || context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            try {
                return ((LocationManager) context.getSystemService("location")).isProviderEnabled(CuebiqSDKImpl.getBeAudienceConfiguration(context).getAcc());
            } catch (Throwable th) {
                return false;
            }
        }
        CuebiqSDKImpl.log("CuebiqSDK -> Permission about LOCATION is not granted.");
        return false;
    }

    public static boolean isOptedOut(Context context) {
        return CuebiqSDKImpl.getBeAudienceConfiguration(context).getTase() != 1 && PersistenceManagerFactory.get().isGAIDDisabled(context);
    }

    public static boolean isWifiAlwaysScanning(Context context) {
        if (VERSION.SDK_INT > 22 && context.checkSelfPermission("android.permission.ACCESS_WIFI_STATE") != 0) {
            CuebiqSDKImpl.log("CuebiqSDK -> Permission about WIFI is not granted.");
            return false;
        } else if (context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", context.getPackageName()) != 0) {
            return false;
        } else {
            return VERSION.SDK_INT >= 18 && ((WifiManager) context.getApplicationContext().getSystemService("wifi")).isScanAlwaysAvailable();
        }
    }

    public static boolean isWifiEnabled(Context context) {
        if (VERSION.SDK_INT <= 22 || context.checkSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0) {
            return context.getPackageManager().checkPermission("android.permission.ACCESS_WIFI_STATE", context.getPackageName()) == 0 ? ((WifiManager) context.getApplicationContext().getSystemService("wifi")).isWifiEnabled() : false;
        } else {
            CuebiqSDKImpl.log("CuebiqSDK -> Permission about WIFI is not granted.");
            return false;
        }
    }
}
