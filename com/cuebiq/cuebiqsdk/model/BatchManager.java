package com.cuebiq.cuebiqsdk.model;

import android.location.Location;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.wrapper.Geo;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;

public class BatchManager {
    public static InfoAnalysisResult analyzeRequest(TrackRequest trackRequest, TrackRequest trackRequest2, int i, Settings settings) {
        InfoAnalysisResult infoAnalysisResult = new InfoAnalysisResult();
        try {
            infoAnalysisResult = validate(trackRequest, trackRequest2, i, settings);
        } catch (Throwable th) {
            infoAnalysisResult.setTrackRequest(null);
            infoAnalysisResult.setNextAcquisitionMills((settings.getAminar() * 60) * 1000);
        }
        return infoAnalysisResult;
    }

    private static void changeAcquisitionIntervalBasedOnBattery(int i, Settings settings, InfoAnalysisResult infoAnalysisResult) {
        if (i > settings.getBtlt()) {
            infoAnalysisResult.setNextAcquisitionMills((settings.getAminar() * 60) * 1000);
        } else {
            infoAnalysisResult.setNextAcquisitionMills((settings.getMaxa() * 60) * 1000);
        }
    }

    public static boolean flush(TrackRequest trackRequest, Settings settings) {
        if (trackRequest == null || trackRequest.getInformation() == null) {
            return false;
        }
        if (trackRequest.getInformation().size() >= settings.getMinb()) {
            return true;
        }
        Information first = trackRequest.getInformation().getFirst();
        Information last = trackRequest.getInformation().getLast();
        return (first == null || last == null || last.getTimestamp() == null || first.getTimestamp() == null || last.getTimestamp().longValue() - first.getTimestamp().longValue() <= (settings.getMaxst() * 60) * 1000) ? false : true;
    }

    private static InfoAnalysisResult increaseLastSeenAndReturn(TrackRequest trackRequest, Settings settings, InfoAnalysisResult infoAnalysisResult, Information information, Information information2, int i) {
        information.setLastSeen(information2.getTimestamp());
        infoAnalysisResult.setTrackRequest(trackRequest);
        CuebiqSDKImpl.log("BatchManager -> Information equals...update lastseen and increase acquisition...");
        long longValue = (infoAnalysisResult.getTrackRequest().getInformation().getLast().getLastSeen().longValue() - infoAnalysisResult.getTrackRequest().getInformation().getLast().getTimestamp().longValue()) / 60;
        long max = (long) (Math.max(2.0d, 2.0d * Math.ceil(((double) (longValue - ((long) settings.getOffda()))) / ((double) settings.getSlopeda()))) * 60.0d);
        if (max >= ((long) settings.getMdyna())) {
            max = (long) settings.getMdyna();
        }
        CuebiqSDKImpl.log("CuebiqSDK -> DwellTime: " + longValue);
        CuebiqSDKImpl.log("CuebiqSDK -> NextAcquisitionSecs: " + max);
        infoAnalysisResult.setNextAcquisitionMills(max * 1000);
        if (i <= settings.getBtlt()) {
            infoAnalysisResult.setNextAcquisitionMills((settings.getMaxa() * 60) * 1000);
        }
        return infoAnalysisResult;
    }

    private static boolean informationContainsCustomEvent(Information information, Information information2) {
        return information.equals(information2);
    }

    private static boolean informationIsEqualToLast(Information information, Information information2, Settings settings) {
        if (!informationContainsCustomEvent(information, information2)) {
            return false;
        }
        Geo geo = information.getGeo();
        Geo geo2 = information2.getGeo();
        if (geo == null || geo2 == null) {
            return false;
        }
        if (geo.equals(geo2)) {
            float floatValue = geo.getHaccuracy().floatValue();
            float floatValue2 = geo2.getHaccuracy().floatValue();
            if (floatValue2 < floatValue) {
                geo.setHaccuracy(Float.valueOf(floatValue2));
            }
            CuebiqSDKImpl.log("BatchManager -> GEO is identical, increase last seen immediately!");
            return true;
        } else if (locationAreDifferent(geo.toLocation(settings.getAcc()), geo2.toLocation(settings.getAcc()), settings)) {
            return false;
        } else {
            CuebiqSDKImpl.log("BatchManager -> User is dwelling.");
            return true;
        }
    }

    private static boolean locationAreDifferent(Location location, Location location2, Settings settings) {
        float distanceTo = location.distanceTo(location2);
        CuebiqSDKImpl.log("Geo -> Meters: " + distanceTo + " Greater than " + settings.getTr() + ": " + (distanceTo > ((float) settings.getTr())));
        return distanceTo > ((float) settings.getTr());
    }

    private static InfoAnalysisResult validate(TrackRequest trackRequest, TrackRequest trackRequest2, int i, Settings settings) {
        InfoAnalysisResult infoAnalysisResult = new InfoAnalysisResult();
        return validateNullability(trackRequest, trackRequest2, settings, infoAnalysisResult) ? infoAnalysisResult : validateEquality(trackRequest, trackRequest2, i, settings, infoAnalysisResult);
    }

    private static InfoAnalysisResult validateEquality(TrackRequest trackRequest, TrackRequest trackRequest2, int i, Settings settings, InfoAnalysisResult infoAnalysisResult) {
        Information last = trackRequest.getInformation().getLast();
        Information first = trackRequest2.getInformation().getFirst();
        if (informationIsEqualToLast(last, first, settings)) {
            return increaseLastSeenAndReturn(trackRequest, settings, infoAnalysisResult, last, first, i);
        }
        if (trackRequest.getInformation().size() >= settings.getMaxb()) {
            trackRequest.getInformation().removeFirst();
        }
        trackRequest.getInformation().add(trackRequest2.getInformation().getFirst());
        infoAnalysisResult.setTrackRequest(trackRequest);
        changeAcquisitionIntervalBasedOnBattery(i, settings, infoAnalysisResult);
        return infoAnalysisResult;
    }

    private static boolean validateNullability(TrackRequest trackRequest, TrackRequest trackRequest2, Settings settings, InfoAnalysisResult infoAnalysisResult) {
        if (trackRequest2 == null || trackRequest2.equals(trackRequest)) {
            infoAnalysisResult.setTrackRequest(null);
            infoAnalysisResult.setNextAcquisitionMills((settings.getAminar() * 60) * 1000);
            return true;
        } else if (trackRequest == null) {
            infoAnalysisResult.setTrackRequest(trackRequest2);
            infoAnalysisResult.setNextAcquisitionMills((settings.getAminar() * 60) * 1000);
            return true;
        } else if (trackRequest.getInformation() == null && trackRequest2.getInformation() == null) {
            infoAnalysisResult.setTrackRequest(null);
            infoAnalysisResult.setNextAcquisitionMills((settings.getAminar() * 60) * 1000);
            return true;
        } else if (trackRequest.getInformation() == null) {
            infoAnalysisResult.setTrackRequest(trackRequest2);
            infoAnalysisResult.setNextAcquisitionMills((settings.getAminar() * 60) * 1000);
            return true;
        } else if (trackRequest2.getInformation() != null) {
            return false;
        } else {
            infoAnalysisResult.setTrackRequest(trackRequest);
            infoAnalysisResult.setNextAcquisitionMills((settings.getAminar() * 60) * 1000);
            return true;
        }
    }
}
