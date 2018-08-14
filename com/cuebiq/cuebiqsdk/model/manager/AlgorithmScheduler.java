package com.cuebiq.cuebiqsdk.model.manager;

import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import com.cuebiq.cuebiqsdk.model.wrapper.SchedulerAlgorithmResult;
import com.cuebiq.cuebiqsdk.utils.InformationList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgorithmScheduler {
    private InformationList mInformationList;
    private Settings mSettings;

    public enum AlgorithmSchedulerStatus {
        DWELLING,
        HIGHWAY,
        NONE
    }

    public AlgorithmScheduler(InformationList informationList, Settings settings) {
        this.mInformationList = informationList;
        this.mSettings = settings;
    }

    private double getMedian(List<Double> list) {
        Collections.sort(list);
        if (list.size() % 2 != 0) {
            return ((Double) list.get(list.size() / 2)).doubleValue();
        }
        return (((Double) list.get((list.size() / 2) - 1)).doubleValue() + ((Double) list.get(list.size() / 2)).doubleValue()) / 2.0d;
    }

    private double getMedianOfDistancesFromFirstPoint(InformationList informationList) {
        List arrayList = new ArrayList();
        for (int i = 1; i < informationList.size(); i++) {
            arrayList.add(Double.valueOf(((Information) informationList.get(i)).getGeo().haversineDistance(((Information) informationList.get(0)).getGeo())));
        }
        return getMedian(arrayList);
    }

    public SchedulerAlgorithmResult process(int i, int i2) {
        if (this.mInformationList.size() < this.mSettings.getStbs()) {
            return new SchedulerAlgorithmResult(false, (this.mSettings.getAminar() * 60) * 1000, AlgorithmSchedulerStatus.NONE, i, i2);
        }
        if (this.mInformationList.getLast().getTimestamp().longValue() - this.mInformationList.getFirst().getTimestamp().longValue() > ((long) (this.mSettings.getStt() * 60))) {
            return new SchedulerAlgorithmResult(false, (this.mSettings.getAminar() * 60) * 1000, AlgorithmSchedulerStatus.NONE, 0, 0);
        }
        double medianOfDistancesFromFirstPoint = getMedianOfDistancesFromFirstPoint(this.mInformationList);
        if (medianOfDistancesFromFirstPoint < ((double) this.mSettings.sddt)) {
            return new SchedulerAlgorithmResult(true, (long) ((((Integer) this.mSettings.getSst().get(i)).intValue() * 60) * 1000), AlgorithmSchedulerStatus.DWELLING, i < this.mSettings.getSst().size() + -1 ? i + 1 : i, 0);
        } else if (medianOfDistancesFromFirstPoint <= ((double) this.mSettings.shdt)) {
            return new SchedulerAlgorithmResult(false, (this.mSettings.getAminar() * 60) * 1000, AlgorithmSchedulerStatus.NONE, 0, 0);
        } else {
            return new SchedulerAlgorithmResult(true, (long) ((((Integer) this.mSettings.getSst().get(i2)).intValue() * 60) * 1000), AlgorithmSchedulerStatus.HIGHWAY, 0, i2 < this.mSettings.getSst().size() + -1 ? i2 + 1 : i2);
        }
    }
}
