package com.appsgeyser.sdk.vast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.appsgeyser.sdk.utils.vast.DefaultMediaPicker;
import com.appsgeyser.sdk.utils.vast.NetworkTools;
import com.appsgeyser.sdk.utils.vast.VASTLog;
import com.appsgeyser.sdk.vast.activity.VASTActivity;
import com.appsgeyser.sdk.vast.model.VASTModel;
import com.appsgeyser.sdk.vast.processor.VASTProcessor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class VASTPlayer {
    public static final int ERROR_EXCEEDED_WRAPPER_LIMIT = 6;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NO_NETWORK = 1;
    public static final int ERROR_POST_VALIDATION = 5;
    public static final int ERROR_SCHEMA_VALIDATION = 4;
    public static final int ERROR_VIDEO_PLAYBACK = 7;
    public static final int ERROR_XML_OPEN_OR_READ = 2;
    public static final int ERROR_XML_PARSE = 3;
    private static final String TAG = "VASTPlayer";
    public static final String VERSION = "1.3";
    public static VASTPlayerListener listener;
    private Context context;
    private VASTModel vastModel;

    public interface VASTPlayerListener {
        void vastClick();

        void vastComplete();

        void vastDismiss();

        void vastError(int i);

        void vastReady();
    }

    class C13203 implements Runnable {
        C13203() {
        }

        public void run() {
            VASTPlayer.listener.vastReady();
        }
    }

    public VASTPlayer(Context context, VASTPlayerListener listener) {
        this.context = context;
        listener = listener;
    }

    public void loadVideoWithUrl(final String urlString) {
        VASTLog.m2412d(TAG, "loadVideoWithUrl " + urlString);
        this.vastModel = null;
        if (NetworkTools.connectedToInternet(this.context)) {
            new Thread(new Runnable() {
                public void run() {
                    Exception e;
                    Throwable th;
                    BufferedReader bufferedReader = null;
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(urlString).openStream()));
                        try {
                            StringBuffer sb = new StringBuffer();
                            while (true) {
                                String line = in.readLine();
                                if (line == null) {
                                    break;
                                }
                                sb.append(line).append(System.getProperty("line.separator"));
                            }
                            if (in != null) {
                                try {
                                    in.close();
                                } catch (IOException e2) {
                                }
                            }
                            VASTPlayer.this.loadVideoWithData(sb.toString());
                            bufferedReader = in;
                        } catch (Exception e3) {
                            e = e3;
                            bufferedReader = in;
                            try {
                                VASTPlayer.this.sendError(2);
                                VASTLog.m2414e(VASTPlayer.TAG, e.getMessage(), e);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e4) {
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedReader = in;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            throw th;
                        }
                    } catch (Exception e6) {
                        e = e6;
                        VASTPlayer.this.sendError(2);
                        VASTLog.m2414e(VASTPlayer.TAG, e.getMessage(), e);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    }
                }
            }).start();
        } else {
            sendError(1);
        }
    }

    public void loadVideoWithData(final String xmlData) {
        VASTLog.m2416v(TAG, "loadVideoWithData\n" + xmlData);
        this.vastModel = null;
        if (NetworkTools.connectedToInternet(this.context)) {
            new Thread(new Runnable() {
                public void run() {
                    VASTProcessor processor = new VASTProcessor(new DefaultMediaPicker(VASTPlayer.this.context));
                    int error = processor.process(xmlData);
                    if (error == 0) {
                        VASTPlayer.this.vastModel = processor.getModel();
                        VASTPlayer.this.sendReady();
                        return;
                    }
                    VASTPlayer.this.sendError(error);
                }
            }).start();
        } else {
            sendError(1);
        }
    }

    public void play() {
        VASTLog.m2412d(TAG, "play");
        if (this.vastModel == null) {
            VASTLog.m2417w(TAG, "vastModel is null; nothing to play");
        } else if (NetworkTools.connectedToInternet(this.context)) {
            Intent vastPlayerIntent = new Intent(this.context, VASTActivity.class);
            vastPlayerIntent.putExtra("com.nexage.android.vast.player.vastModel", this.vastModel);
            this.context.startActivity(vastPlayerIntent);
        } else {
            sendError(1);
        }
    }

    private void sendReady() {
        VASTLog.m2412d(TAG, "sendReady");
        if (listener != null) {
            ((Activity) this.context).runOnUiThread(new C13203());
        }
    }

    private void sendError(final int error) {
        VASTLog.m2412d(TAG, "sendError");
        if (listener != null) {
            ((Activity) this.context).runOnUiThread(new Runnable() {
                public void run() {
                    VASTPlayer.listener.vastError(error);
                }
            });
        }
    }
}
