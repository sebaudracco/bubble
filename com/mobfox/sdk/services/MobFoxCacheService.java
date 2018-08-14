package com.mobfox.sdk.services;

import android.content.Context;
import android.util.Log;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.mobfox.sdk.constants.Constants;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class MobFoxCacheService extends MobFoxBaseService {
    public static final long EVENT_READY_INTERVAL = 200;
    List<String> downloadedFiles = new ArrayList();

    public interface Listener {
        void onDownloaded(String str);
    }

    public MobFoxCacheService(Context context) {
        super(context);
    }

    public void callback() {
    }

    public static boolean videoExists(String filepath) {
        try {
            if (new File(filepath).exists()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.d(Constants.MOBFOX_BANNER, "video exists");
            return false;
        } catch (Throwable th) {
            Log.d(Constants.MOBFOX_BANNER, "video exists");
            return false;
        }
    }

    public static final String md5(String s) {
        String MD5 = "MD5";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(aMessageDigest & 255);
                while (h.length() < 2) {
                    h = SchemaSymbols.ATTVAL_FALSE_0 + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.d(Constants.MOBFOX_BANNER, "md5");
            return "";
        }
    }

    public static String getLocalPath(Context context, String videoURL) {
        String filename = md5(videoURL);
        if (filename == null) {
            Log.d(Constants.MOBFOX_BANNER, "parse video url");
            return null;
        }
        String filepath = context.getCacheDir() + BridgeUtil.SPLIT_MARK + filename;
        if (videoExists(filepath)) {
            return filepath;
        }
        return null;
    }

    void addDownloaded(String path) {
        this.downloadedFiles.add(path);
    }
}
