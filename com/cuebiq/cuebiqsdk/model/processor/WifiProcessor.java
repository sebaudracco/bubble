package com.cuebiq.cuebiqsdk.model.processor;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.listener.ProcessorCompletedListener;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import com.cuebiq.cuebiqsdk.model.wrapper.Wifi;
import com.cuebiq.cuebiqsdk.utils.WifiList;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.List;
import java.util.Random;

public class WifiProcessor extends AbstractProcessor {
    private final WifiList mWifiList = new WifiList();

    public WifiProcessor() {
        super(ProcessorType.WIFI_PROCESSOR);
    }

    public void collectWifi(Information information, List<ScanResult> list, WifiInfo wifiInfo) {
        Wifi wifi = new Wifi();
        if (!(wifiInfo == null || wifiInfo.getSSID() == null || wifiInfo.getBSSID() == null || "00:00:00:00:00:00".equals(wifiInfo.getBSSID()))) {
            wifi.setSsid(wifiInfo.getSSID().replace("\"", ""));
            wifi.setLinkSpeed(Integer.valueOf(wifiInfo.getLinkSpeed()));
            wifi.setRssi(Integer.valueOf(wifiInfo.getRssi()));
            wifi.setBssid(wifiInfo.getBSSID());
            this.mWifiList.add(wifi);
        }
        if (list != null && list.size() > 0) {
            for (ScanResult scanResult : list) {
                if (!(scanResult.SSID == null || "".equals(scanResult.SSID) || scanResult.BSSID == null || "".equals(scanResult.BSSID) || "00:00:00:00:00:00".equals(scanResult.BSSID))) {
                    Wifi wifi2 = new Wifi();
                    wifi2.setSsid(scanResult.SSID);
                    wifi2.setRssi(Integer.valueOf(scanResult.level));
                    wifi2.setBssid(scanResult.BSSID);
                    if (!this.mWifiList.contains(wifi2)) {
                        this.mWifiList.add(wifi2);
                    }
                }
            }
            CuebiqSDKImpl.log("WiFi Processor -> WiFi found: #" + this.mWifiList.size());
            if (this.mWifiList.size() == 0) {
                information.setWifis(null);
            } else {
                information.setWifis(this.mWifiList);
            }
        }
    }

    public void gather(Context context, Information information, ProcessorCompletedListener processorCompletedListener) {
        try {
            List list;
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            int nextInt = new Random().nextInt(100);
            if (nextInt >= CuebiqSDKImpl.getBeAudienceConfiguration(context).getWst()) {
                CuebiqSDKImpl.log("WiFi Processor -> Skipping WiFi Scan: " + nextInt + BridgeUtil.SPLIT_MARK + CuebiqSDKImpl.getBeAudienceConfiguration(context).getWst());
                list = null;
            } else if (VERSION.SDK_INT > 22) {
                if (context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                    list = wifiManager.getScanResults();
                }
                list = null;
            } else {
                list = wifiManager.getScanResults();
            }
            collectWifi(information, list, connectionInfo);
        } catch (SecurityException e) {
            CuebiqSDKImpl.log(e.getMessage());
        } catch (Throwable th) {
        }
        if (processorCompletedListener != null) {
            processorCompletedListener.onProcessorCompleted(getType());
        }
    }

    public WifiList getWifiList() {
        return this.mWifiList;
    }
}
