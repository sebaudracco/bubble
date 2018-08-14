package com.cuebiq.cuebiqsdk.model.comparator;

import com.cuebiq.cuebiqsdk.model.wrapper.Wifi;
import java.util.Comparator;

public class WifiComparator implements Comparator<Wifi> {
    public int compare(Wifi wifi, Wifi wifi2) {
        return wifi.getSsid().compareTo(wifi2.getSsid());
    }
}
