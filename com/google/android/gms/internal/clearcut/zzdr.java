package com.google.android.gms.internal.clearcut;

import com.appnext.base.p019a.p022c.C1028c;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.internal.clearcut.zzcg.zzd;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

final class zzdr {
    static String zza(zzdo com_google_android_gms_internal_clearcut_zzdo, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# ").append(str);
        zza(com_google_android_gms_internal_clearcut_zzdo, stringBuilder, 0);
        return stringBuilder.toString();
    }

    private static void zza(zzdo com_google_android_gms_internal_clearcut_zzdo, StringBuilder stringBuilder, int i) {
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        Set<String> treeSet = new TreeSet();
        for (Method method : com_google_android_gms_internal_clearcut_zzdo.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String valueOf;
            String valueOf2;
            Method method2;
            String replaceFirst = str.replaceFirst("get", "");
            if (!(!replaceFirst.endsWith("List") || replaceFirst.endsWith("OrBuilderList") || replaceFirst.equals("List"))) {
                valueOf = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                valueOf2 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 4));
                valueOf = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zza(stringBuilder, i, zzj(valueOf), zzcg.zza(method2, (Object) com_google_android_gms_internal_clearcut_zzdo, new Object[0]));
                }
            }
            if (replaceFirst.endsWith("Map") && !replaceFirst.equals("Map")) {
                valueOf = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                valueOf2 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 3));
                valueOf = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                method2 = (Method) hashMap.get(str);
                if (method2 != null && method2.getReturnType().equals(Map.class) && !method2.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method2.getModifiers())) {
                    zza(stringBuilder, i, zzj(valueOf), zzcg.zza(method2, (Object) com_google_android_gms_internal_clearcut_zzdo, new Object[0]));
                }
            }
            String str2 = "set";
            valueOf2 = String.valueOf(replaceFirst);
            if (((Method) hashMap2.get(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2))) != null) {
                if (replaceFirst.endsWith("Bytes")) {
                    str2 = "get";
                    valueOf2 = String.valueOf(replaceFirst.substring(0, replaceFirst.length() - 5));
                    if (hashMap.containsKey(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2))) {
                    }
                }
                str2 = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                valueOf2 = String.valueOf(replaceFirst.substring(1));
                String concat = valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2);
                str2 = "get";
                valueOf2 = String.valueOf(replaceFirst);
                method2 = (Method) hashMap.get(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
                valueOf = "has";
                str2 = String.valueOf(replaceFirst);
                Method method3 = (Method) hashMap.get(str2.length() != 0 ? valueOf.concat(str2) : new String(valueOf));
                if (method2 != null) {
                    boolean equals;
                    zzdo zza = zzcg.zza(method2, (Object) com_google_android_gms_internal_clearcut_zzdo, new Object[0]);
                    if (method3 == null) {
                        equals = zza instanceof Boolean ? !((Boolean) zza).booleanValue() : zza instanceof Integer ? ((Integer) zza).intValue() == 0 : zza instanceof Float ? ((Float) zza).floatValue() == 0.0f : zza instanceof Double ? ((Double) zza).doubleValue() == 0.0d : zza instanceof String ? zza.equals("") : zza instanceof zzbb ? zza.equals(zzbb.zzfi) : zza instanceof zzdo ? zza == ((zzdo) zza).zzbe() : zza instanceof Enum ? ((Enum) zza).ordinal() == 0 : false;
                        equals = !equals;
                    } else {
                        equals = ((Boolean) zzcg.zza(method3, (Object) com_google_android_gms_internal_clearcut_zzdo, new Object[0])).booleanValue();
                    }
                    if (equals) {
                        zza(stringBuilder, i, zzj(concat), zza);
                    }
                }
            }
        }
        if (com_google_android_gms_internal_clearcut_zzdo instanceof zzd) {
            Iterator it = ((zzd) com_google_android_gms_internal_clearcut_zzdo).zzjv.iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                zza(stringBuilder, i, "[" + ((zze) entry.getKey()).number + "]", entry.getValue());
            }
        }
        if (((zzcg) com_google_android_gms_internal_clearcut_zzdo).zzjp != null) {
            ((zzcg) com_google_android_gms_internal_clearcut_zzdo).zzjp.zza(stringBuilder, i);
        }
    }

    static final void zza(StringBuilder stringBuilder, int i, String str, Object obj) {
        int i2 = 0;
        if (obj instanceof List) {
            for (Object zza : (List) obj) {
                zza(stringBuilder, i, str, zza);
            }
        } else if (obj instanceof Map) {
            for (Entry zza2 : ((Map) obj).entrySet()) {
                zza(stringBuilder, i, str, zza2);
            }
        } else {
            stringBuilder.append('\n');
            for (int i3 = 0; i3 < i; i3++) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(str);
            if (obj instanceof String) {
                stringBuilder.append(": \"").append(zzet.zzc(zzbb.zzf((String) obj))).append('\"');
            } else if (obj instanceof zzbb) {
                stringBuilder.append(": \"").append(zzet.zzc((zzbb) obj)).append('\"');
            } else if (obj instanceof zzcg) {
                stringBuilder.append(" {");
                zza((zzcg) obj, stringBuilder, i + 2);
                stringBuilder.append("\n");
                while (i2 < i) {
                    stringBuilder.append(' ');
                    i2++;
                }
                stringBuilder.append("}");
            } else if (obj instanceof Entry) {
                stringBuilder.append(" {");
                Entry entry = (Entry) obj;
                zza(stringBuilder, i + 2, C1028c.gv, entry.getKey());
                zza(stringBuilder, i + 2, FirebaseAnalytics$Param.VALUE, entry.getValue());
                stringBuilder.append("\n");
                while (i2 < i) {
                    stringBuilder.append(' ');
                    i2++;
                }
                stringBuilder.append("}");
            } else {
                stringBuilder.append(": ").append(obj.toString());
            }
        }
    }

    private static final String zzj(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                stringBuilder.append(BridgeUtil.UNDERLINE_STR);
            }
            stringBuilder.append(Character.toLowerCase(charAt));
        }
        return stringBuilder.toString();
    }
}
