package com.cuebiq.cuebiqsdk.model.wrapper;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.Locale;
import java.util.TimeZone;

public class Device {
    private Boolean bluetoothActive;
    private String brand;
    private String carrierCode;
    private String carrierName;
    private String deviceType = "ANDROID";
    private Boolean isGoogleAdvIDDisabled;
    private String locale;
    private String manufacturer;
    private String model;
    private Integer osv;
    private String product;
    private String screenSize;
    private String timezone;
    private Integer timezoneOffset;
    private String userAgent;

    public static Device build(Context context) {
        Device device = new Device();
        try {
            device.setOsv(Integer.valueOf(VERSION.SDK_INT));
            device.setManufacturer(Build.MANUFACTURER);
            device.setBrand(Build.BRAND);
            device.setModel(Build.MODEL);
            device.setProduct(Build.PRODUCT);
            device.setLocale(Locale.getDefault().getLanguage() + BridgeUtil.UNDERLINE_STR + Locale.getDefault().getCountry());
            device.setTimezone(TimeZone.getDefault().getID());
            device.setTimezoneOffset(Integer.valueOf(TimeZone.getDefault().getRawOffset() / 1000));
            device.setScreenSize(context.getResources().getDisplayMetrics().widthPixels + "x" + context.getResources().getDisplayMetrics().heightPixels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (VERSION.SDK_INT >= 17) {
                device.setUserAgent(WebSettings.getDefaultUserAgent(context));
            } else {
                device.setUserAgent(new WebView(context).getSettings().getUserAgentString());
            }
        } catch (Throwable th) {
        }
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null) {
                device.setBluetoothActive(Boolean.valueOf(false));
            } else if (defaultAdapter.isEnabled()) {
                device.setBluetoothActive(Boolean.valueOf(true));
            } else {
                device.setBluetoothActive(Boolean.valueOf(false));
            }
        } catch (Throwable th2) {
            device.setBluetoothActive(Boolean.valueOf(false));
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            device.setCarrierCode(telephonyManager.getNetworkOperator());
            device.setCarrierName(telephonyManager.getNetworkOperatorName());
        } catch (Throwable th3) {
            th3.printStackTrace();
        }
        return device;
    }

    public Boolean getBluetoothActive() {
        return this.bluetoothActive;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getCarrierCode() {
        return this.carrierCode;
    }

    public String getCarrierName() {
        return this.carrierName;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public Boolean getIsGoogleAdvIDDisabled() {
        return this.isGoogleAdvIDDisabled;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public Integer getOsv() {
        return this.osv;
    }

    public String getProduct() {
        return this.product;
    }

    public String getScreenSize() {
        return this.screenSize;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public Integer getTimezoneOffset() {
        return this.timezoneOffset;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setBluetoothActive(Boolean bool) {
        this.bluetoothActive = bool;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public void setCarrierCode(String str) {
        this.carrierCode = str;
    }

    public void setCarrierName(String str) {
        this.carrierName = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setIsGoogleAdvIDDisabled(Boolean bool) {
        this.isGoogleAdvIDDisabled = bool;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setOsv(Integer num) {
        this.osv = num;
    }

    public void setProduct(String str) {
        this.product = str;
    }

    public void setScreenSize(String str) {
        this.screenSize = str;
    }

    public void setTimezone(String str) {
        this.timezone = str;
    }

    public void setTimezoneOffset(Integer num) {
        this.timezoneOffset = num;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
