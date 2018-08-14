package com.google.android.gms.internal.ads;

final class zzbea {
    static String zzaq(zzbah com_google_android_gms_internal_ads_zzbah) {
        zzbec com_google_android_gms_internal_ads_zzbeb = new zzbeb(com_google_android_gms_internal_ads_zzbah);
        StringBuilder stringBuilder = new StringBuilder(com_google_android_gms_internal_ads_zzbeb.size());
        for (int i = 0; i < com_google_android_gms_internal_ads_zzbeb.size(); i++) {
            byte zzbn = com_google_android_gms_internal_ads_zzbeb.zzbn(i);
            switch (zzbn) {
                case (byte) 7:
                    stringBuilder.append("\\a");
                    break;
                case (byte) 8:
                    stringBuilder.append("\\b");
                    break;
                case (byte) 9:
                    stringBuilder.append("\\t");
                    break;
                case (byte) 10:
                    stringBuilder.append("\\n");
                    break;
                case (byte) 11:
                    stringBuilder.append("\\v");
                    break;
                case (byte) 12:
                    stringBuilder.append("\\f");
                    break;
                case (byte) 13:
                    stringBuilder.append("\\r");
                    break;
                case (byte) 34:
                    stringBuilder.append("\\\"");
                    break;
                case (byte) 39:
                    stringBuilder.append("\\'");
                    break;
                case (byte) 92:
                    stringBuilder.append("\\\\");
                    break;
                default:
                    if (zzbn >= (byte) 32 && zzbn <= (byte) 126) {
                        stringBuilder.append((char) zzbn);
                        break;
                    }
                    stringBuilder.append('\\');
                    stringBuilder.append((char) (((zzbn >>> 6) & 3) + 48));
                    stringBuilder.append((char) (((zzbn >>> 3) & 7) + 48));
                    stringBuilder.append((char) ((zzbn & 7) + 48));
                    break;
            }
        }
        return stringBuilder.toString();
    }
}
