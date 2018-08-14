package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

@zzadh
public final class zzarx {
    private static final Pattern zzdeo = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*\\s*<!DOCTYPE(\\s)+html(|(\\s)+[^>]*)>", 2);
    private static final Pattern zzdep = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*?\\s*<!DOCTYPE[^>]*>", 2);

    public static String zzb(@NonNull String str, @Nullable String... strArr) {
        int i = 0;
        if (strArr.length == 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Matcher matcher = zzdeo.matcher(str);
        int end;
        if (matcher.find()) {
            end = matcher.end();
            stringBuilder.append(str.substring(0, end));
            int length = strArr.length;
            while (i < length) {
                String str2 = strArr[i];
                if (str2 != null) {
                    stringBuilder.append(str2);
                }
                i++;
            }
            stringBuilder.append(str.substring(end));
        } else {
            if (!zzdep.matcher(str).find()) {
                end = strArr.length;
                while (i < end) {
                    String str3 = strArr[i];
                    if (str3 != null) {
                        stringBuilder.append(str3);
                    }
                    i++;
                }
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    public static String zzvp() {
        String str = (String) zzkb.zzik().zzd(zznk.zzawf);
        String str2 = "12.4.51-000";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", str);
            jSONObject.put("sdk", "Google Mobile Ads");
            jSONObject.put("sdkVersion", str2);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<script>");
            stringBuilder.append("Object.defineProperty(window,'MRAID_ENV',{get:function(){return ").append(jSONObject.toString()).append("}});");
            stringBuilder.append("</script>");
            return stringBuilder.toString();
        } catch (Throwable e) {
            zzane.zzc("Unable to build MRAID_ENV", e);
            return null;
        }
    }
}
