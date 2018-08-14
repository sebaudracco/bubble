package com.google.android.gms.internal.ads;

import android.support.v4.view.PointerIconCompat;

public final class zzhu {

    public static final class zza extends zzbbo<zza, zza> implements zzbcw {
        private static final zza zzakg = new zza();
        private static volatile zzbdf<zza> zzakh;

        public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zza, zza> implements zzbcw {
            private zza() {
                super(zza.zzakg);
            }
        }

        public enum zzb implements zzbbr {
            UNKNOWN_EVENT_TYPE(0),
            AD_REQUEST(1),
            AD_LOADED(2),
            AD_FAILED_TO_LOAD(3),
            AD_FAILED_TO_LOAD_NO_FILL(4),
            AD_IMPRESSION(5),
            AD_FIRST_CLICK(6),
            AD_SUBSEQUENT_CLICK(7),
            REQUEST_WILL_START(8),
            REQUEST_DID_END(9),
            REQUEST_WILL_UPDATE_SIGNALS(10),
            REQUEST_DID_UPDATE_SIGNALS(11),
            REQUEST_WILL_BUILD_URL(12),
            REQUEST_DID_BUILD_URL(13),
            REQUEST_WILL_MAKE_NETWORK_REQUEST(14),
            REQUEST_DID_RECEIVE_NETWORK_RESPONSE(15),
            REQUEST_WILL_PROCESS_RESPONSE(16),
            REQUEST_DID_PROCESS_RESPONSE(17),
            REQUEST_WILL_RENDER(18),
            REQUEST_DID_RENDER(19),
            REQUEST_WILL_UPDATE_GMS_SIGNALS(1000),
            REQUEST_DID_UPDATE_GMS_SIGNALS(1001),
            REQUEST_FAILED_TO_UPDATE_GMS_SIGNALS(1002),
            REQUEST_FAILED_TO_BUILD_URL(PointerIconCompat.TYPE_HELP),
            REQUEST_FAILED_TO_MAKE_NETWORK_REQUEST(PointerIconCompat.TYPE_WAIT),
            REQUEST_FAILED_TO_PROCESS_RESPONSE(1005),
            REQUEST_FAILED_TO_UPDATE_SIGNALS(PointerIconCompat.TYPE_CELL),
            BANNER_SIZE_INVALID(10000),
            BANNER_SIZE_VALID(10001);
            
            private static final zzbbs<zzb> zzall = null;
            private final int value;

            static {
                zzall = new zzhw();
            }

            private zzb(int i) {
                this.value = i;
            }

            public static zzb zzp(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN_EVENT_TYPE;
                    case 1:
                        return AD_REQUEST;
                    case 2:
                        return AD_LOADED;
                    case 3:
                        return AD_FAILED_TO_LOAD;
                    case 4:
                        return AD_FAILED_TO_LOAD_NO_FILL;
                    case 5:
                        return AD_IMPRESSION;
                    case 6:
                        return AD_FIRST_CLICK;
                    case 7:
                        return AD_SUBSEQUENT_CLICK;
                    case 8:
                        return REQUEST_WILL_START;
                    case 9:
                        return REQUEST_DID_END;
                    case 10:
                        return REQUEST_WILL_UPDATE_SIGNALS;
                    case 11:
                        return REQUEST_DID_UPDATE_SIGNALS;
                    case 12:
                        return REQUEST_WILL_BUILD_URL;
                    case 13:
                        return REQUEST_DID_BUILD_URL;
                    case 14:
                        return REQUEST_WILL_MAKE_NETWORK_REQUEST;
                    case 15:
                        return REQUEST_DID_RECEIVE_NETWORK_RESPONSE;
                    case 16:
                        return REQUEST_WILL_PROCESS_RESPONSE;
                    case 17:
                        return REQUEST_DID_PROCESS_RESPONSE;
                    case 18:
                        return REQUEST_WILL_RENDER;
                    case 19:
                        return REQUEST_DID_RENDER;
                    case 1000:
                        return REQUEST_WILL_UPDATE_GMS_SIGNALS;
                    case 1001:
                        return REQUEST_DID_UPDATE_GMS_SIGNALS;
                    case 1002:
                        return REQUEST_FAILED_TO_UPDATE_GMS_SIGNALS;
                    case PointerIconCompat.TYPE_HELP /*1003*/:
                        return REQUEST_FAILED_TO_BUILD_URL;
                    case PointerIconCompat.TYPE_WAIT /*1004*/:
                        return REQUEST_FAILED_TO_MAKE_NETWORK_REQUEST;
                    case 1005:
                        return REQUEST_FAILED_TO_PROCESS_RESPONSE;
                    case PointerIconCompat.TYPE_CELL /*1006*/:
                        return REQUEST_FAILED_TO_UPDATE_SIGNALS;
                    case 10000:
                        return BANNER_SIZE_INVALID;
                    case 10001:
                        return BANNER_SIZE_VALID;
                    default:
                        return null;
                }
            }

            public final int zzhq() {
                return this.value;
            }
        }

        static {
            zzbbo.zza(zza.class, zzakg);
        }

        private zza() {
        }

        protected final Object zza(int i, Object obj, Object obj2) {
            switch (zzhv.zzakf[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new zza();
                case 3:
                    return zzbbo.zza(zzakg, "\u0001\u0000", null);
                case 4:
                    return zzakg;
                case 5:
                    zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                    if (com_google_android_gms_internal_ads_zzbdf != null) {
                        return com_google_android_gms_internal_ads_zzbdf;
                    }
                    Object obj3;
                    synchronized (zza.class) {
                        obj3 = zzakh;
                        if (obj3 == null) {
                            obj3 = new com.google.android.gms.internal.ads.zzbbo.zzb(zzakg);
                            zzakh = obj3;
                        }
                    }
                    return obj3;
                case 6:
                    return Byte.valueOf((byte) 1);
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }
}
