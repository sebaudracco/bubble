package com.google.android.gms.internal.wearable;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

@VisibleForTesting
public final class zze {
    public static zzf zza(DataMap dataMap) {
        zzg com_google_android_gms_internal_wearable_zzg = new zzg();
        List arrayList = new ArrayList();
        TreeSet treeSet = new TreeSet(dataMap.keySet());
        zzh[] com_google_android_gms_internal_wearable_zzhArr = new zzh[treeSet.size()];
        Iterator it = treeSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            Object obj = dataMap.get(str);
            com_google_android_gms_internal_wearable_zzhArr[i] = new zzh();
            com_google_android_gms_internal_wearable_zzhArr[i].name = str;
            com_google_android_gms_internal_wearable_zzhArr[i].zzga = zza(arrayList, obj);
            i++;
        }
        com_google_android_gms_internal_wearable_zzg.zzfy = com_google_android_gms_internal_wearable_zzhArr;
        return new zzf(com_google_android_gms_internal_wearable_zzg, arrayList);
    }

    private static zzi zza(List<Asset> list, Object obj) {
        zzi com_google_android_gms_internal_wearable_zzi = new zzi();
        if (obj == null) {
            com_google_android_gms_internal_wearable_zzi.type = 14;
            return com_google_android_gms_internal_wearable_zzi;
        }
        com_google_android_gms_internal_wearable_zzi.zzgc = new zzj();
        if (obj instanceof String) {
            com_google_android_gms_internal_wearable_zzi.type = 2;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzge = (String) obj;
        } else if (obj instanceof Integer) {
            com_google_android_gms_internal_wearable_zzi.type = 6;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgi = ((Integer) obj).intValue();
        } else if (obj instanceof Long) {
            com_google_android_gms_internal_wearable_zzi.type = 5;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgh = ((Long) obj).longValue();
        } else if (obj instanceof Double) {
            com_google_android_gms_internal_wearable_zzi.type = 3;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgf = ((Double) obj).doubleValue();
        } else if (obj instanceof Float) {
            com_google_android_gms_internal_wearable_zzi.type = 4;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgg = ((Float) obj).floatValue();
        } else if (obj instanceof Boolean) {
            com_google_android_gms_internal_wearable_zzi.type = 8;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgk = ((Boolean) obj).booleanValue();
        } else if (obj instanceof Byte) {
            com_google_android_gms_internal_wearable_zzi.type = 7;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgj = ((Byte) obj).byteValue();
        } else if (obj instanceof byte[]) {
            com_google_android_gms_internal_wearable_zzi.type = 1;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgd = (byte[]) obj;
        } else if (obj instanceof String[]) {
            com_google_android_gms_internal_wearable_zzi.type = 11;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgn = (String[]) obj;
        } else if (obj instanceof long[]) {
            com_google_android_gms_internal_wearable_zzi.type = 12;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgo = (long[]) obj;
        } else if (obj instanceof float[]) {
            com_google_android_gms_internal_wearable_zzi.type = 15;
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgp = (float[]) obj;
        } else if (obj instanceof Asset) {
            com_google_android_gms_internal_wearable_zzi.type = 13;
            zzj com_google_android_gms_internal_wearable_zzj = com_google_android_gms_internal_wearable_zzi.zzgc;
            list.add((Asset) obj);
            com_google_android_gms_internal_wearable_zzj.zzgq = (long) (list.size() - 1);
        } else if (obj instanceof DataMap) {
            com_google_android_gms_internal_wearable_zzi.type = 9;
            DataMap dataMap = (DataMap) obj;
            TreeSet treeSet = new TreeSet(dataMap.keySet());
            zzh[] com_google_android_gms_internal_wearable_zzhArr = new zzh[treeSet.size()];
            Iterator it = treeSet.iterator();
            r1 = 0;
            while (it.hasNext()) {
                r0 = (String) it.next();
                com_google_android_gms_internal_wearable_zzhArr[r1] = new zzh();
                com_google_android_gms_internal_wearable_zzhArr[r1].name = r0;
                com_google_android_gms_internal_wearable_zzhArr[r1].zzga = zza(list, dataMap.get(r0));
                r1++;
            }
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgl = com_google_android_gms_internal_wearable_zzhArr;
        } else if (obj instanceof ArrayList) {
            com_google_android_gms_internal_wearable_zzi.type = 10;
            ArrayList arrayList = (ArrayList) obj;
            zzi[] com_google_android_gms_internal_wearable_zziArr = new zzi[arrayList.size()];
            Object obj2 = null;
            int size = arrayList.size();
            int i = 0;
            int i2 = 14;
            while (i < size) {
                Object obj3 = arrayList.get(i);
                zzi zza = zza(list, obj3);
                if (zza.type == 14 || zza.type == 2 || zza.type == 6 || zza.type == 9) {
                    if (i2 == 14 && zza.type != 14) {
                        r1 = zza.type;
                    } else if (zza.type != i2) {
                        String valueOf = String.valueOf(obj2.getClass());
                        r0 = String.valueOf(obj3.getClass());
                        throw new IllegalArgumentException(new StringBuilder((String.valueOf(valueOf).length() + 80) + String.valueOf(r0).length()).append("ArrayList elements must all be of the sameclass, but this one contains a ").append(valueOf).append(" and a ").append(r0).toString());
                    } else {
                        obj3 = obj2;
                        r1 = i2;
                    }
                    com_google_android_gms_internal_wearable_zziArr[i] = zza;
                    i++;
                    i2 = r1;
                    obj2 = obj3;
                } else {
                    r0 = String.valueOf(obj3.getClass());
                    throw new IllegalArgumentException(new StringBuilder(String.valueOf(r0).length() + 130).append("The only ArrayList element types supported by DataBundleUtil are String, Integer, Bundle, and null, but this ArrayList contains a ").append(r0).toString());
                }
            }
            com_google_android_gms_internal_wearable_zzi.zzgc.zzgm = com_google_android_gms_internal_wearable_zziArr;
        } else {
            String str = "newFieldValueFromValue: unexpected value ";
            r0 = String.valueOf(obj.getClass().getSimpleName());
            throw new RuntimeException(r0.length() != 0 ? str.concat(r0) : new String(str));
        }
        return com_google_android_gms_internal_wearable_zzi;
    }

    public static DataMap zza(zzf com_google_android_gms_internal_wearable_zzf) {
        DataMap dataMap = new DataMap();
        for (zzh com_google_android_gms_internal_wearable_zzh : com_google_android_gms_internal_wearable_zzf.zzfw.zzfy) {
            zza(com_google_android_gms_internal_wearable_zzf.zzfx, dataMap, com_google_android_gms_internal_wearable_zzh.name, com_google_android_gms_internal_wearable_zzh.zzga);
        }
        return dataMap;
    }

    private static void zza(List<Asset> list, DataMap dataMap, String str, zzi com_google_android_gms_internal_wearable_zzi) {
        int i = com_google_android_gms_internal_wearable_zzi.type;
        if (i == 14) {
            dataMap.putString(str, null);
            return;
        }
        zzj com_google_android_gms_internal_wearable_zzj = com_google_android_gms_internal_wearable_zzi.zzgc;
        if (i == 1) {
            dataMap.putByteArray(str, com_google_android_gms_internal_wearable_zzj.zzgd);
        } else if (i == 11) {
            dataMap.putStringArray(str, com_google_android_gms_internal_wearable_zzj.zzgn);
        } else if (i == 12) {
            dataMap.putLongArray(str, com_google_android_gms_internal_wearable_zzj.zzgo);
        } else if (i == 15) {
            dataMap.putFloatArray(str, com_google_android_gms_internal_wearable_zzj.zzgp);
        } else if (i == 2) {
            dataMap.putString(str, com_google_android_gms_internal_wearable_zzj.zzge);
        } else if (i == 3) {
            dataMap.putDouble(str, com_google_android_gms_internal_wearable_zzj.zzgf);
        } else if (i == 4) {
            dataMap.putFloat(str, com_google_android_gms_internal_wearable_zzj.zzgg);
        } else if (i == 5) {
            dataMap.putLong(str, com_google_android_gms_internal_wearable_zzj.zzgh);
        } else if (i == 6) {
            dataMap.putInt(str, com_google_android_gms_internal_wearable_zzj.zzgi);
        } else if (i == 7) {
            dataMap.putByte(str, (byte) com_google_android_gms_internal_wearable_zzj.zzgj);
        } else if (i == 8) {
            dataMap.putBoolean(str, com_google_android_gms_internal_wearable_zzj.zzgk);
        } else if (i == 13) {
            if (list == null) {
                String str2 = "populateBundle: unexpected type for: ";
                String valueOf = String.valueOf(str);
                throw new RuntimeException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            dataMap.putAsset(str, (Asset) list.get((int) com_google_android_gms_internal_wearable_zzj.zzgq));
        } else if (i == 9) {
            DataMap dataMap2 = new DataMap();
            for (zzh com_google_android_gms_internal_wearable_zzh : com_google_android_gms_internal_wearable_zzj.zzgl) {
                zza(list, dataMap2, com_google_android_gms_internal_wearable_zzh.name, com_google_android_gms_internal_wearable_zzh.zzga);
            }
            dataMap.putDataMap(str, dataMap2);
        } else if (i == 10) {
            i = 14;
            for (zzi com_google_android_gms_internal_wearable_zzi2 : com_google_android_gms_internal_wearable_zzj.zzgm) {
                if (i == 14) {
                    if (com_google_android_gms_internal_wearable_zzi2.type == 9 || com_google_android_gms_internal_wearable_zzi2.type == 2 || com_google_android_gms_internal_wearable_zzi2.type == 6) {
                        i = com_google_android_gms_internal_wearable_zzi2.type;
                    } else if (com_google_android_gms_internal_wearable_zzi2.type != 14) {
                        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 48).append("Unexpected TypedValue type: ").append(com_google_android_gms_internal_wearable_zzi2.type).append(" for key ").append(str).toString());
                    }
                } else if (com_google_android_gms_internal_wearable_zzi2.type != i) {
                    throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 126).append("The ArrayList elements should all be the same type, but ArrayList with key ").append(str).append(" contains items of type ").append(i).append(" and ").append(com_google_android_gms_internal_wearable_zzi2.type).toString());
                }
            }
            ArrayList arrayList = new ArrayList(com_google_android_gms_internal_wearable_zzj.zzgm.length);
            for (zzi com_google_android_gms_internal_wearable_zzi3 : com_google_android_gms_internal_wearable_zzj.zzgm) {
                if (com_google_android_gms_internal_wearable_zzi3.type == 14) {
                    arrayList.add(null);
                } else if (i == 9) {
                    DataMap dataMap3 = new DataMap();
                    for (zzh com_google_android_gms_internal_wearable_zzh2 : com_google_android_gms_internal_wearable_zzi3.zzgc.zzgl) {
                        zza(list, dataMap3, com_google_android_gms_internal_wearable_zzh2.name, com_google_android_gms_internal_wearable_zzh2.zzga);
                    }
                    arrayList.add(dataMap3);
                } else if (i == 2) {
                    arrayList.add(com_google_android_gms_internal_wearable_zzi3.zzgc.zzge);
                } else if (i == 6) {
                    arrayList.add(Integer.valueOf(com_google_android_gms_internal_wearable_zzi3.zzgc.zzgi));
                } else {
                    throw new IllegalArgumentException("Unexpected typeOfArrayList: " + i);
                }
            }
            if (i == 14) {
                dataMap.putStringArrayList(str, arrayList);
            } else if (i == 9) {
                dataMap.putDataMapArrayList(str, arrayList);
            } else if (i == 2) {
                dataMap.putStringArrayList(str, arrayList);
            } else if (i == 6) {
                dataMap.putIntegerArrayList(str, arrayList);
            } else {
                throw new IllegalStateException("Unexpected typeOfArrayList: " + i);
            }
        } else {
            throw new RuntimeException("populateBundle: unexpected type " + i);
        }
    }
}
