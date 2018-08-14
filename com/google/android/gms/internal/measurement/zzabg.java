package com.google.android.gms.internal.measurement;

final class zzabg {
    static String zza(zzyw com_google_android_gms_internal_measurement_zzyw) {
        zzabi com_google_android_gms_internal_measurement_zzabh = new zzabh(com_google_android_gms_internal_measurement_zzyw);
        StringBuilder stringBuilder = new StringBuilder(com_google_android_gms_internal_measurement_zzabh.size());
        for (int i = 0; i < com_google_android_gms_internal_measurement_zzabh.size(); i++) {
            byte zzae = com_google_android_gms_internal_measurement_zzabh.zzae(i);
            switch (zzae) {
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
                    if (zzae >= (byte) 32 && zzae <= (byte) 126) {
                        stringBuilder.append((char) zzae);
                        break;
                    }
                    stringBuilder.append('\\');
                    stringBuilder.append((char) (((zzae >>> 6) & 3) + 48));
                    stringBuilder.append((char) (((zzae >>> 3) & 7) + 48));
                    stringBuilder.append((char) ((zzae & 7) + 48));
                    break;
            }
        }
        return stringBuilder.toString();
    }
}
