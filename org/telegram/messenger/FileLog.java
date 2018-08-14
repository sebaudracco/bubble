package org.telegram.messenger;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;
import org.telegram.messenger.time.FastDateFormat;

public class FileLog {
    private static volatile FileLog Instance = null;
    private File currentFile = null;
    private FastDateFormat dateFormat = null;
    private DispatchQueue logQueue = null;
    private File networkFile = null;
    private OutputStreamWriter streamWriter = null;

    public static FileLog getInstance() {
        FileLog localInstance = Instance;
        if (localInstance == null) {
            synchronized (FileLog.class) {
                try {
                    localInstance = Instance;
                    if (localInstance == null) {
                        FileLog localInstance2 = new FileLog();
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

    public FileLog() {
        if (BuildVars.DEBUG_VERSION) {
            this.dateFormat = FastDateFormat.getInstance("dd_MM_yyyy_HH_mm_ss", Locale.US);
            try {
                File sdCard = ApplicationLoader.applicationContext.getExternalFilesDir(null);
                if (sdCard != null) {
                    File dir = new File(sdCard.getAbsolutePath() + "/logs");
                    dir.mkdirs();
                    this.currentFile = new File(dir, this.dateFormat.format(System.currentTimeMillis()) + ".txt");
                    try {
                        this.logQueue = new DispatchQueue("logQueue");
                        this.currentFile.createNewFile();
                        this.streamWriter = new OutputStreamWriter(new FileOutputStream(this.currentFile));
                        this.streamWriter.write("-----start log " + this.dateFormat.format(System.currentTimeMillis()) + "-----\n");
                        this.streamWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static String getNetworkLogPath() {
        if (!BuildVars.DEBUG_VERSION) {
            return "";
        }
        try {
            File sdCard = ApplicationLoader.applicationContext.getExternalFilesDir(null);
            if (sdCard == null) {
                return "";
            }
            File dir = new File(sdCard.getAbsolutePath() + "/logs");
            dir.mkdirs();
            getInstance().networkFile = new File(dir, getInstance().dateFormat.format(System.currentTimeMillis()) + "_net.txt");
            return getInstance().networkFile.getAbsolutePath();
        } catch (Throwable e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void m4943e(String message, Throwable exception) {
        if (BuildVars.DEBUG_VERSION) {
            Log.e("tmessages", message, exception);
            if (getInstance().streamWriter != null) {
                getInstance().logQueue.postRunnable(new 1(message, exception));
            }
        }
    }

    public static void m4942e(String message) {
        if (BuildVars.DEBUG_VERSION) {
            Log.e("tmessages", message);
            if (getInstance().streamWriter != null) {
                getInstance().logQueue.postRunnable(new 2(message));
            }
        }
    }

    public static void m4944e(Throwable e) {
        if (BuildVars.DEBUG_VERSION) {
            e.printStackTrace();
            if (getInstance().streamWriter != null) {
                getInstance().logQueue.postRunnable(new 3(e));
            } else {
                e.printStackTrace();
            }
        }
    }

    public static void m4941d(String message) {
        if (BuildVars.DEBUG_VERSION) {
            Log.d("tmessages", message);
            if (getInstance().streamWriter != null) {
                getInstance().logQueue.postRunnable(new 4(message));
            }
        }
    }

    public static void m4945w(String message) {
        if (BuildVars.DEBUG_VERSION) {
            Log.w("tmessages", message);
            if (getInstance().streamWriter != null) {
                getInstance().logQueue.postRunnable(new 5(message));
            }
        }
    }

    public static void cleanupLogs() {
        File sdCard = ApplicationLoader.applicationContext.getExternalFilesDir(null);
        if (sdCard != null) {
            File[] files = new File(sdCard.getAbsolutePath() + "/logs").listFiles();
            if (files != null) {
                for (File file : files) {
                    if ((getInstance().currentFile == null || !file.getAbsolutePath().equals(getInstance().currentFile.getAbsolutePath())) && (getInstance().networkFile == null || !file.getAbsolutePath().equals(getInstance().networkFile.getAbsolutePath()))) {
                        file.delete();
                    }
                }
            }
        }
    }
}
