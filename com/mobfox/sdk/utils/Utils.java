package com.mobfox.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebView;
import com.Decoder.BASE64Encoder;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.dmp.DMPManager;
import com.mobfox.sdk.dmp.DMPManager.DMPCallback;
import com.mobfox.sdk.services.MobFoxService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
    public static final String FILE_SEPARATOR = "\t";
    public static final String NEW_LINE = "\n";

    static class C36001 implements DMPCallback {
        C36001() {
        }

        public void onPostCompleted() {
            Log.d(Constants.MOBFOX_BANNER, "dmp post completed");
        }

        public void onPostError() {
            Log.d(Constants.MOBFOX_BANNER, "dmp post failed");
        }
    }

    public static String read(Context c, String name) {
        Throwable th;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(c.openFileInput(name)));
            try {
                String ls = System.getProperty("line.separator");
                while (true) {
                    String line = bufReader.readLine();
                    if (line == null) {
                        break;
                    }
                    stringBuilder.append(line);
                    if (bufReader.ready()) {
                        stringBuilder.append(ls);
                    }
                }
                if (bufReader != null) {
                    try {
                        bufReader.close();
                    } catch (IOException e) {
                        bufferedReader = bufReader;
                        return null;
                    }
                }
                if (stringBuilder.toString().length() == 0) {
                    bufferedReader = bufReader;
                    return null;
                }
                bufferedReader = bufReader;
                return stringBuilder.toString();
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufReader;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            Log.d(Constants.MOBFOX_UTILS, "read setting");
            if (bufferedReader != null) {
                return null;
            }
            bufferedReader.close();
            return null;
        }
    }

    public static void write(Context c, String name, String data) {
        write(c, name, data, 0);
    }

    public static void write(Context c, String name, String data, int mode) {
        Throwable th;
        BufferedWriter bufWriter = null;
        try {
            BufferedWriter bufWriter2 = new BufferedWriter(new OutputStreamWriter(c.openFileOutput(name, mode)));
            try {
                bufWriter2.write(data);
                bufWriter2.flush();
                if (bufWriter2 != null) {
                    try {
                        bufWriter2.close();
                    } catch (IOException e) {
                        Log.d(Constants.MOBFOX_UTILS, "write buffer closed");
                        bufWriter = bufWriter2;
                        return;
                    }
                }
                bufWriter = bufWriter2;
            } catch (Throwable th2) {
                th = th2;
                bufWriter = bufWriter2;
                if (bufWriter != null) {
                    bufWriter.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            Log.d(Constants.MOBFOX_UTILS, "write buffer setting");
            if (bufWriter != null) {
                bufWriter.close();
            }
        }
    }

    public static long getFileSize(File file) {
        return file.length() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String encodeXor(String s, String k) {
        String result = null;
        try {
            result = new BASE64Encoder().encode(xorWithKey(s.getBytes(), k.getBytes())).replaceAll("\\s", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i % key.length]);
        }
        return out;
    }

    public static String getMediaUrl(JSONObject adResp) {
        String url = "";
        try {
            JSONObject creative;
            JSONArray vasts = adResp.getJSONArray("vasts");
            JSONObject creatives = vasts.getJSONObject(vasts.length() - 1).getJSONObject("VAST").getJSONObject("Ad").getJSONObject("InLine").getJSONObject("Creatives");
            try {
                creative = creatives.getJSONObject("Creative");
            } catch (Exception e) {
                creative = creatives.getJSONArray("Creative").getJSONObject(0);
            }
            JSONObject media = creative.getJSONObject("Linear").getJSONObject("MediaFiles").getJSONObject("MediaFile");
            if (media.has("__text")) {
                return media.getString("__text");
            }
            if (media.has("__cdata")) {
                return media.getString("__cdata");
            }
            return url;
        } catch (JSONException e2) {
            Log.d(Constants.MOBFOX_BANNER, "video url json exception");
            return url;
        }
    }

    public static JSONObject replaceToCached(String replace, JSONObject adResp) {
        try {
            adResp.getJSONArray("vasts").getJSONObject(0).getJSONObject("VAST").getJSONObject("Ad").getJSONObject("InLine").getJSONObject("Creatives").getJSONObject("Creative").getJSONObject("Linear").getJSONObject("MediaFiles").getJSONObject("MediaFile").put("__cdata", replace);
        } catch (Exception e) {
            try {
                adResp.getJSONArray("vasts").getJSONObject(0).getJSONObject("VAST").getJSONObject("Ad").getJSONObject("InLine").getJSONObject("Creatives").getJSONArray("Creative").getJSONObject(0).getJSONObject("Linear").getJSONObject("MediaFiles").getJSONObject("MediaFile").put("__cdata", replace);
            } catch (JSONException e2) {
                Log.d(Constants.MOBFOX_BANNER, "replace with cached exception");
            }
        }
        return adResp;
    }

    public static JSONObject replaceAudioCached(String replace, JSONObject adResp) {
        try {
            adResp.getJSONArray("vasts").getJSONObject(1).getJSONObject("VAST").getJSONObject("Ad").getJSONObject("InLine").getJSONObject("Creatives").getJSONObject("Creative").getJSONObject("Linear").getJSONObject("MediaFiles").getJSONObject("MediaFile").put("__text", replace);
            return adResp;
        } catch (Exception e) {
            Log.d(Constants.MOBFOX_BANNER, "replace with cached exception");
            return null;
        }
    }

    public static String getClickVideoResp(JSONObject adResp) {
        try {
            return adResp.getJSONArray("vasts").getJSONObject(0).getJSONObject("VAST").getJSONObject("Ad").getJSONObject("InLine").getJSONObject("Creatives").getJSONObject("Creative").getJSONObject("Linear").getJSONObject("VideoClicks").getString("ClickThrough");
        } catch (Exception e) {
            try {
                return adResp.getJSONArray("vasts").getJSONObject(1).getJSONObject("VAST").getJSONObject("Ad").getJSONObject("InLine").getJSONObject("Creatives").getJSONObject("Creative").getJSONObject("Linear").getJSONObject("VideoClicks").getString("ClickThrough");
            } catch (JSONException e2) {
                Log.d(Constants.MOBFOX_BANNER, "get click url from video json exception");
                return "";
            }
        }
    }

    public static String getIPAddress(boolean useIPv4) {
        try {
            for (NetworkInterface intf : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress addr : Collections.list(intf.getInetAddresses())) {
                    if (!addr.isLoopbackAddress()) {
                        boolean isIPv4;
                        String sAddr = addr.getHostAddress();
                        if (sAddr.indexOf(58) < 0) {
                            isIPv4 = true;
                        } else {
                            isIPv4 = false;
                        }
                        if (useIPv4) {
                            if (isIPv4) {
                                return sAddr;
                            }
                        } else if (!isIPv4) {
                            int delim = sAddr.indexOf(37);
                            return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public static void postDMP(Context context, WebView view) {
        try {
            DMPManager.updateDMP(context, getIPAddress(true), view.getSettings().getUserAgentString());
            DMPManager.postDMP(context, new C36001());
        } catch (Throwable th) {
            Log.d(Constants.MOBFOX_BANNER, "post dmp exception");
        }
    }

    public static void startPreCaching(Context context) {
        try {
            if (VERSION.SDK_INT < 24) {
                context.startService(new Intent(context, MobFoxService.class));
            }
        } catch (Exception e) {
            Log.d(Constants.MOBFOX_BANNER, "start uam exception");
        }
    }

    public static String getBundleId(Context context) {
        String bundle_id = "";
        try {
            bundle_id = context.getPackageName();
        } catch (Throwable th) {
            Log.d(Constants.MOBFOX_BANNER, "bundle error");
        }
        return bundle_id;
    }
}
