package org.altbeacon.beacon.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.logging.LogManager;

public class Stats {
    private static final Stats INSTANCE = new Stats();
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");
    private static final String TAG = "Stats";
    private boolean mEnableHistoricalLogging;
    private boolean mEnableLogging;
    private boolean mEnabled;
    private Sample mSample;
    private long mSampleIntervalMillis = 0;
    private ArrayList<Sample> mSamples;

    public static class Sample {
        public long detectionCount = 0;
        public Date firstDetectionTime;
        public Date lastDetectionTime;
        public long maxMillisBetweenDetections;
        public Date sampleStartTime;
        public Date sampleStopTime;
    }

    public static Stats getInstance() {
        return INSTANCE;
    }

    private Stats() {
        clearSamples();
    }

    public ArrayList<Sample> getSamples() {
        rollSampleIfNeeded();
        return this.mSamples;
    }

    public void setEnabled(boolean enabled) {
        this.mEnabled = enabled;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    public void setLoggingEnabled(boolean enabled) {
        this.mEnableLogging = enabled;
    }

    public void setHistoricalLoggingEnabled(boolean enabled) {
        this.mEnableHistoricalLogging = enabled;
    }

    public void setSampleIntervalMillis(long interval) {
        this.mSampleIntervalMillis = interval;
    }

    public void log(Beacon beacon) {
        rollSampleIfNeeded();
        Sample sample = this.mSample;
        sample.detectionCount++;
        if (this.mSample.firstDetectionTime == null) {
            this.mSample.firstDetectionTime = new Date();
        }
        if (this.mSample.lastDetectionTime != null) {
            long timeSinceLastDetection = new Date().getTime() - this.mSample.lastDetectionTime.getTime();
            if (timeSinceLastDetection > this.mSample.maxMillisBetweenDetections) {
                this.mSample.maxMillisBetweenDetections = timeSinceLastDetection;
            }
        }
        this.mSample.lastDetectionTime = new Date();
    }

    public void clearSample() {
        this.mSample = null;
    }

    public void newSampleInterval() {
        Date boundaryTime = new Date();
        if (this.mSample != null) {
            boundaryTime = new Date(this.mSample.sampleStartTime.getTime() + this.mSampleIntervalMillis);
            this.mSample.sampleStopTime = boundaryTime;
            if (!this.mEnableHistoricalLogging && this.mEnableLogging) {
                logSample(this.mSample, true);
            }
        }
        this.mSample = new Sample();
        this.mSample.sampleStartTime = boundaryTime;
        this.mSamples.add(this.mSample);
        if (this.mEnableHistoricalLogging) {
            logSamples();
        }
    }

    public void clearSamples() {
        this.mSamples = new ArrayList();
        newSampleInterval();
    }

    private void logSample(Sample sample, boolean showHeader) {
        if (showHeader) {
            LogManager.m16371d(TAG, "sample start time, sample stop time, first detection time, last detection time, max millis between detections, detection count", new Object[0]);
        }
        LogManager.m16371d(TAG, "%s, %s, %s, %s, %s, %s", formattedDate(sample.sampleStartTime), formattedDate(sample.sampleStopTime), formattedDate(sample.firstDetectionTime), formattedDate(sample.lastDetectionTime), Long.valueOf(sample.maxMillisBetweenDetections), Long.valueOf(sample.detectionCount));
    }

    private String formattedDate(Date d) {
        String formattedDate = "";
        if (d != null) {
            synchronized (SIMPLE_DATE_FORMAT) {
                formattedDate = SIMPLE_DATE_FORMAT.format(d);
            }
        }
        return formattedDate;
    }

    private void logSamples() {
        LogManager.m16371d(TAG, "--- Stats for %s samples", Integer.valueOf(this.mSamples.size()));
        boolean firstPass = true;
        Iterator it = this.mSamples.iterator();
        while (it.hasNext()) {
            logSample((Sample) it.next(), firstPass);
            firstPass = false;
        }
    }

    private void rollSampleIfNeeded() {
        if (this.mSample == null || (this.mSampleIntervalMillis > 0 && new Date().getTime() - this.mSample.sampleStartTime.getTime() >= this.mSampleIntervalMillis)) {
            newSampleInterval();
        }
    }
}
