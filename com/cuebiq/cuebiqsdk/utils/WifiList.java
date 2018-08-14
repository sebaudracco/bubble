package com.cuebiq.cuebiqsdk.utils;

import com.cuebiq.cuebiqsdk.model.comparator.WifiComparator;
import com.cuebiq.cuebiqsdk.model.wrapper.Wifi;
import java.util.ArrayList;
import java.util.Collections;

public class WifiList extends ArrayList<Wifi> {
    public boolean add(Wifi wifi) {
        boolean add = super.add(wifi);
        Collections.sort(this, new WifiComparator());
        return add;
    }
}
