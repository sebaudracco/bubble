package org.telegram.messenger;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.stats.LoggingConstants;
import java.lang.reflect.Array;

public class StatsController {
    private static volatile StatsController Instance = null;
    private static final int TYPES_COUNT = 7;
    public static final int TYPE_AUDIOS = 3;
    public static final int TYPE_CALLS = 0;
    public static final int TYPE_FILES = 5;
    public static final int TYPE_MESSAGES = 1;
    public static final int TYPE_MOBILE = 0;
    public static final int TYPE_PHOTOS = 4;
    public static final int TYPE_ROAMING = 2;
    public static final int TYPE_TOTAL = 6;
    public static final int TYPE_VIDEOS = 2;
    public static final int TYPE_WIFI = 1;
    private static final ThreadLocal<Long> lastStatsSaveTime = new C51971();
    private int[] callsTotalTime = new int[3];
    private Editor editor;
    private long[][] receivedBytes = ((long[][]) Array.newInstance(Long.TYPE, new int[]{3, 7}));
    private int[][] receivedItems = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{3, 7}));
    private long[] resetStatsDate = new long[3];
    private long[][] sentBytes = ((long[][]) Array.newInstance(Long.TYPE, new int[]{3, 7}));
    private int[][] sentItems = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{3, 7}));
    private DispatchQueue statsSaveQueue = new DispatchQueue("statsSaveQueue");

    static class C51971 extends ThreadLocal<Long> {
        C51971() {
        }

        protected Long initialValue() {
            return Long.valueOf(System.currentTimeMillis() - 1000);
        }
    }

    class C51982 implements Runnable {
        C51982() {
        }

        public void run() {
            for (int networkType = 0; networkType < 3; networkType++) {
                for (int a = 0; a < 7; a++) {
                    StatsController.this.editor.putInt("receivedItems" + networkType + BridgeUtil.UNDERLINE_STR + a, StatsController.this.receivedItems[networkType][a]);
                    StatsController.this.editor.putInt("sentItems" + networkType + BridgeUtil.UNDERLINE_STR + a, StatsController.this.sentItems[networkType][a]);
                    StatsController.this.editor.putLong("receivedBytes" + networkType + BridgeUtil.UNDERLINE_STR + a, StatsController.this.receivedBytes[networkType][a]);
                    StatsController.this.editor.putLong("sentBytes" + networkType + BridgeUtil.UNDERLINE_STR + a, StatsController.this.sentBytes[networkType][a]);
                }
                StatsController.this.editor.putInt("callsTotalTime" + networkType, StatsController.this.callsTotalTime[networkType]);
                StatsController.this.editor.putLong("resetStatsDate" + networkType, StatsController.this.resetStatsDate[networkType]);
            }
            try {
                StatsController.this.editor.apply();
            } catch (Exception e) {
                FileLog.e(e);
            }
        }
    }

    public static StatsController getInstance() {
        StatsController localInstance = Instance;
        if (localInstance == null) {
            synchronized (StatsController.class) {
                try {
                    localInstance = Instance;
                    if (localInstance == null) {
                        StatsController localInstance2 = new StatsController();
                        try {
                            Instance = localInstance2;
                            localInstance = localInstance2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            localInstance = localInstance2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return localInstance;
    }

    private StatsController() {
        SharedPreferences sharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences(LoggingConstants.LOG_FILE_PREFIX, 0);
        boolean save = false;
        this.editor = sharedPreferences.edit();
        for (int a = 0; a < 3; a++) {
            this.callsTotalTime[a] = sharedPreferences.getInt("callsTotalTime" + a, 0);
            this.resetStatsDate[a] = sharedPreferences.getLong("resetStatsDate" + a, 0);
            for (int b = 0; b < 7; b++) {
                this.sentBytes[a][b] = sharedPreferences.getLong("sentBytes" + a + BridgeUtil.UNDERLINE_STR + b, 0);
                this.receivedBytes[a][b] = sharedPreferences.getLong("receivedBytes" + a + BridgeUtil.UNDERLINE_STR + b, 0);
                this.sentItems[a][b] = sharedPreferences.getInt("sentItems" + a + BridgeUtil.UNDERLINE_STR + b, 0);
                this.receivedItems[a][b] = sharedPreferences.getInt("receivedItems" + a + BridgeUtil.UNDERLINE_STR + b, 0);
            }
            if (this.resetStatsDate[a] == 0) {
                save = true;
                this.resetStatsDate[a] = System.currentTimeMillis();
            }
        }
        if (save) {
            saveStats();
        }
    }

    public void incrementReceivedItemsCount(int networkType, int dataType, int value) {
        int[] iArr = this.receivedItems[networkType];
        iArr[dataType] = iArr[dataType] + value;
        saveStats();
    }

    public void incrementSentItemsCount(int networkType, int dataType, int value) {
        int[] iArr = this.sentItems[networkType];
        iArr[dataType] = iArr[dataType] + value;
        saveStats();
    }

    public void incrementReceivedBytesCount(int networkType, int dataType, long value) {
        long[] jArr = this.receivedBytes[networkType];
        jArr[dataType] = jArr[dataType] + value;
        saveStats();
    }

    public void incrementSentBytesCount(int networkType, int dataType, long value) {
        long[] jArr = this.sentBytes[networkType];
        jArr[dataType] = jArr[dataType] + value;
        saveStats();
    }

    public void incrementTotalCallsTime(int networkType, int value) {
        int[] iArr = this.callsTotalTime;
        iArr[networkType] = iArr[networkType] + value;
        saveStats();
    }

    public int getRecivedItemsCount(int networkType, int dataType) {
        return this.receivedItems[networkType][dataType];
    }

    public int getSentItemsCount(int networkType, int dataType) {
        return this.sentItems[networkType][dataType];
    }

    public long getSentBytesCount(int networkType, int dataType) {
        if (dataType == 1) {
            return (((this.sentBytes[networkType][6] - this.sentBytes[networkType][5]) - this.sentBytes[networkType][3]) - this.sentBytes[networkType][2]) - this.sentBytes[networkType][4];
        }
        return this.sentBytes[networkType][dataType];
    }

    public long getReceivedBytesCount(int networkType, int dataType) {
        if (dataType == 1) {
            return (((this.receivedBytes[networkType][6] - this.receivedBytes[networkType][5]) - this.receivedBytes[networkType][3]) - this.receivedBytes[networkType][2]) - this.receivedBytes[networkType][4];
        }
        return this.receivedBytes[networkType][dataType];
    }

    public int getCallsTotalTime(int networkType) {
        return this.callsTotalTime[networkType];
    }

    public long getResetStatsDate(int networkType) {
        return this.resetStatsDate[networkType];
    }

    public void resetStats(int networkType) {
        this.resetStatsDate[networkType] = System.currentTimeMillis();
        for (int a = 0; a < 7; a++) {
            this.sentBytes[networkType][a] = 0;
            this.receivedBytes[networkType][a] = 0;
            this.sentItems[networkType][a] = 0;
            this.receivedItems[networkType][a] = 0;
        }
        this.callsTotalTime[networkType] = 0;
        saveStats();
    }

    private void saveStats() {
        long newTime = System.currentTimeMillis();
        if (Math.abs(newTime - ((Long) lastStatsSaveTime.get()).longValue()) >= 1000) {
            lastStatsSaveTime.set(Long.valueOf(newTime));
            this.statsSaveQueue.postRunnable(new C51982());
        }
    }
}