package com.cuebiq.cuebiqsdk.model.comparator;

import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import java.util.Comparator;

public class InformationComparator implements Comparator<Information> {
    public int compare(Information information, Information information2) {
        return information.getTimestamp().longValue() < information2.getTimestamp().longValue() ? -1 : information.getTimestamp().longValue() > information2.getTimestamp().longValue() ? 1 : 0;
    }
}
