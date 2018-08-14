package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzcg<MessageType extends zzcg<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzas<MessageType, BuilderType> {
    private static Map<Object, zzcg<?, ?>> zzjr = new ConcurrentHashMap();
    protected zzey zzjp = zzey.zzea();
    private int zzjq = -1;

    public static abstract class zza<MessageType extends zzcg<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzat<MessageType, BuilderType> {
        private final MessageType zzjs;
        protected MessageType zzjt;
        protected boolean zzju = false;

        protected zza(MessageType messageType) {
            this.zzjs = messageType;
            this.zzjt = (zzcg) messageType.zza(zzg.zzkg, null, null);
        }

        private static void zza(MessageType messageType, MessageType messageType2) {
            zzea.zzcm().zzp(messageType).zzc(messageType, messageType2);
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza com_google_android_gms_internal_clearcut_zzcg_zza = (zza) this.zzjs.zza(zzg.zzkh, null, null);
            com_google_android_gms_internal_clearcut_zzcg_zza.zza((zzcg) zzbi());
            return com_google_android_gms_internal_clearcut_zzcg_zza;
        }

        public final boolean isInitialized() {
            return zzcg.zza(this.zzjt, false);
        }

        protected final /* synthetic */ zzat zza(zzas com_google_android_gms_internal_clearcut_zzas) {
            return zza((zzcg) com_google_android_gms_internal_clearcut_zzas);
        }

        public final BuilderType zza(MessageType messageType) {
            zzbf();
            zza(this.zzjt, messageType);
            return this;
        }

        public final /* synthetic */ zzdo zzbe() {
            return this.zzjs;
        }

        protected void zzbf() {
            if (this.zzju) {
                zzcg com_google_android_gms_internal_clearcut_zzcg = (zzcg) this.zzjt.zza(zzg.zzkg, null, null);
                zza(com_google_android_gms_internal_clearcut_zzcg, this.zzjt);
                this.zzjt = com_google_android_gms_internal_clearcut_zzcg;
                this.zzju = false;
            }
        }

        public MessageType zzbg() {
            if (this.zzju) {
                return this.zzjt;
            }
            zzcg com_google_android_gms_internal_clearcut_zzcg = this.zzjt;
            zzea.zzcm().zzp(com_google_android_gms_internal_clearcut_zzcg).zzc(com_google_android_gms_internal_clearcut_zzcg);
            this.zzju = true;
            return this.zzjt;
        }

        public final MessageType zzbh() {
            boolean z;
            zzcg com_google_android_gms_internal_clearcut_zzcg = (zzcg) zzbi();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) com_google_android_gms_internal_clearcut_zzcg.zza(zzg.zzkd, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                boolean zzo = zzea.zzcm().zzp(com_google_android_gms_internal_clearcut_zzcg).zzo(com_google_android_gms_internal_clearcut_zzcg);
                if (booleanValue) {
                    com_google_android_gms_internal_clearcut_zzcg.zza(zzg.zzke, zzo ? com_google_android_gms_internal_clearcut_zzcg : null, null);
                }
                z = zzo;
            }
            if (z) {
                return com_google_android_gms_internal_clearcut_zzcg;
            }
            throw new zzew(com_google_android_gms_internal_clearcut_zzcg);
        }

        public /* synthetic */ zzdo zzbi() {
            return zzbg();
        }

        public final /* synthetic */ zzdo zzbj() {
            boolean z;
            zzcg com_google_android_gms_internal_clearcut_zzcg = (zzcg) zzbi();
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) com_google_android_gms_internal_clearcut_zzcg.zza(zzg.zzkd, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                boolean zzo = zzea.zzcm().zzp(com_google_android_gms_internal_clearcut_zzcg).zzo(com_google_android_gms_internal_clearcut_zzcg);
                if (booleanValue) {
                    com_google_android_gms_internal_clearcut_zzcg.zza(zzg.zzke, zzo ? com_google_android_gms_internal_clearcut_zzcg : null, null);
                }
                z = zzo;
            }
            if (z) {
                return com_google_android_gms_internal_clearcut_zzcg;
            }
            throw new zzew(com_google_android_gms_internal_clearcut_zzcg);
        }

        public final /* synthetic */ zzat zzt() {
            return (zza) clone();
        }
    }

    public static class zzb<T extends zzcg<T, ?>> extends zzau<T> {
        private T zzjs;

        public zzb(T t) {
            this.zzjs = t;
        }
    }

    public static abstract class zzc<MessageType extends zzd<MessageType, BuilderType>, BuilderType extends zzc<MessageType, BuilderType>> extends zza<MessageType, BuilderType> implements zzdq {
        protected zzc(MessageType messageType) {
            super(messageType);
        }

        protected final void zzbf() {
            if (this.zzju) {
                super.zzbf();
                ((zzd) this.zzjt).zzjv = (zzby) ((zzd) this.zzjt).zzjv.clone();
            }
        }

        public final /* synthetic */ zzcg zzbg() {
            return (zzd) zzbi();
        }

        public final /* synthetic */ zzdo zzbi() {
            if (this.zzju) {
                return (zzd) this.zzjt;
            }
            ((zzd) this.zzjt).zzjv.zzv();
            return (zzd) super.zzbg();
        }
    }

    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType extends zzc<MessageType, BuilderType>> extends zzcg<MessageType, BuilderType> implements zzdq {
        protected zzby<zze> zzjv = zzby.zzar();
    }

    static final class zze implements zzca<zze> {
        final int number = 66321687;
        private final zzck<?> zzjw = null;
        final zzfl zzjx;
        final boolean zzjy;
        final boolean zzjz;

        zze(zzck<?> com_google_android_gms_internal_clearcut_zzck_, int i, zzfl com_google_android_gms_internal_clearcut_zzfl, boolean z, boolean z2) {
            this.zzjx = com_google_android_gms_internal_clearcut_zzfl;
            this.zzjy = false;
            this.zzjz = false;
        }

        public final /* synthetic */ int compareTo(Object obj) {
            return this.number - ((zze) obj).number;
        }

        public final zzdp zza(zzdp com_google_android_gms_internal_clearcut_zzdp, zzdo com_google_android_gms_internal_clearcut_zzdo) {
            return ((zza) com_google_android_gms_internal_clearcut_zzdp).zza((zzcg) com_google_android_gms_internal_clearcut_zzdo);
        }

        public final zzdv zza(zzdv com_google_android_gms_internal_clearcut_zzdv, zzdv com_google_android_gms_internal_clearcut_zzdv2) {
            throw new UnsupportedOperationException();
        }

        public final zzfl zzau() {
            return this.zzjx;
        }

        public final zzfq zzav() {
            return this.zzjx.zzek();
        }

        public final boolean zzaw() {
            return false;
        }

        public final boolean zzax() {
            return false;
        }

        public final int zzc() {
            return this.number;
        }
    }

    public static class zzf<ContainingType extends zzdo, Type> extends zzbr<ContainingType, Type> {
        private final Type zzdu;
        private final ContainingType zzka;
        private final zzdo zzkb;
        private final zze zzkc;

        zzf(ContainingType containingType, Type type, zzdo com_google_android_gms_internal_clearcut_zzdo, zze com_google_android_gms_internal_clearcut_zzcg_zze, Class cls) {
            if (containingType == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            } else if (com_google_android_gms_internal_clearcut_zzcg_zze.zzjx == zzfl.MESSAGE && com_google_android_gms_internal_clearcut_zzdo == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else {
                this.zzka = containingType;
                this.zzdu = type;
                this.zzkb = com_google_android_gms_internal_clearcut_zzdo;
                this.zzkc = com_google_android_gms_internal_clearcut_zzcg_zze;
            }
        }
    }

    public enum zzg {
        public static final int zzkd = 1;
        public static final int zzke = 2;
        public static final int zzkf = 3;
        public static final int zzkg = 4;
        public static final int zzkh = 5;
        public static final int zzki = 6;
        public static final int zzkj = 7;
        private static final /* synthetic */ int[] zzkk = new int[]{zzkd, zzke, zzkf, zzkg, zzkh, zzki, zzkj};
        public static final int zzkl = 1;
        public static final int zzkm = 2;
        private static final /* synthetic */ int[] zzkn = new int[]{zzkl, zzkm};
        public static final int zzko = 1;
        public static final int zzkp = 2;
        private static final /* synthetic */ int[] zzkq = new int[]{zzko, zzkp};

        public static int[] m8612x126d66cb() {
            return (int[]) zzkk.clone();
        }
    }

    public static <ContainingType extends zzdo, Type> zzf<ContainingType, Type> zza(ContainingType containingType, Type type, zzdo com_google_android_gms_internal_clearcut_zzdo, zzck<?> com_google_android_gms_internal_clearcut_zzck_, int i, zzfl com_google_android_gms_internal_clearcut_zzfl, Class cls) {
        return new zzf(containingType, type, com_google_android_gms_internal_clearcut_zzdo, new zze(null, 66321687, com_google_android_gms_internal_clearcut_zzfl, false, false), cls);
    }

    private static <T extends zzcg<T, ?>> T zza(T t, byte[] bArr) throws zzco {
        zzcg com_google_android_gms_internal_clearcut_zzcg = (zzcg) t.zza(zzg.zzkg, null, null);
        try {
            zzea.zzcm().zzp(com_google_android_gms_internal_clearcut_zzcg).zza(com_google_android_gms_internal_clearcut_zzcg, bArr, 0, bArr.length, new zzay());
            zzea.zzcm().zzp(com_google_android_gms_internal_clearcut_zzcg).zzc(com_google_android_gms_internal_clearcut_zzcg);
            if (com_google_android_gms_internal_clearcut_zzcg.zzex == 0) {
                return com_google_android_gms_internal_clearcut_zzcg;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzco) {
                throw ((zzco) e.getCause());
            }
            throw new zzco(e.getMessage()).zzg(com_google_android_gms_internal_clearcut_zzcg);
        } catch (IndexOutOfBoundsException e2) {
            throw zzco.zzbl().zzg(com_google_android_gms_internal_clearcut_zzcg);
        }
    }

    protected static Object zza(zzdo com_google_android_gms_internal_clearcut_zzdo, String str, Object[] objArr) {
        return new zzec(com_google_android_gms_internal_clearcut_zzdo, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        Throwable e;
        try {
            return method.invoke(obj, objArr);
        } catch (Throwable e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            e2 = e3.getCause();
            if (e2 instanceof RuntimeException) {
                throw ((RuntimeException) e2);
            } else if (e2 instanceof Error) {
                throw ((Error) e2);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", e2);
            }
        }
    }

    protected static <T extends zzcg<?, ?>> void zza(Class<T> cls, T t) {
        zzjr.put(cls, t);
    }

    protected static final <T extends zzcg<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzg.zzkd, null, null)).byteValue();
        return byteValue == (byte) 1 ? true : byteValue == (byte) 0 ? false : zzea.zzcm().zzp(t).zzo(t);
    }

    protected static zzcl zzaz() {
        return zzch.zzbk();
    }

    protected static <T extends zzcg<T, ?>> T zzb(T t, byte[] bArr) throws zzco {
        T zza = zza((zzcg) t, bArr);
        if (zza != null) {
            boolean z;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza.zza(zzg.zzkd, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                boolean zzo = zzea.zzcm().zzp(zza).zzo(zza);
                if (booleanValue) {
                    zza.zza(zzg.zzke, zzo ? zza : null, null);
                }
                z = zzo;
            }
            if (!z) {
                throw new zzco(new zzew(zza).getMessage()).zzg(zza);
            }
        }
        return zza;
    }

    protected static zzcm zzba() {
        return zzdc.zzbx();
    }

    protected static <E> zzcn<E> zzbb() {
        return zzeb.zzcn();
    }

    static <T extends zzcg<?, ?>> T zzc(Class<T> cls) {
        T t = (zzcg) zzjr.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzcg) zzjr.get(cls);
            } catch (Throwable e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String str = "Unable to get default instance for: ";
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !((zzcg) zza(zzg.zzki, null, null)).getClass().isInstance(obj) ? false : zzea.zzcm().zzp(this).equals(this, (zzcg) obj);
    }

    public int hashCode() {
        if (this.zzex != 0) {
            return this.zzex;
        }
        this.zzex = zzea.zzcm().zzp(this).hashCode(this);
        return this.zzex;
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zzg.zzkd, null, null)).byteValue();
        if (byteValue == (byte) 1) {
            return true;
        }
        if (byteValue == (byte) 0) {
            return false;
        }
        boolean zzo = zzea.zzcm().zzp(this).zzo(this);
        if (booleanValue) {
            zza(zzg.zzke, zzo ? this : null, null);
        }
        return zzo;
    }

    public String toString() {
        return zzdr.zza(this, super.toString());
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    public final int zzas() {
        if (this.zzjq == -1) {
            this.zzjq = zzea.zzcm().zzp(this).zzm(this);
        }
        return this.zzjq;
    }

    public final void zzb(zzbn com_google_android_gms_internal_clearcut_zzbn) throws IOException {
        zzea.zzcm().zze(getClass()).zza(this, zzbp.zza(com_google_android_gms_internal_clearcut_zzbn));
    }

    public final /* synthetic */ zzdp zzbc() {
        zza com_google_android_gms_internal_clearcut_zzcg_zza = (zza) zza(zzg.zzkh, null, null);
        com_google_android_gms_internal_clearcut_zzcg_zza.zza(this);
        return com_google_android_gms_internal_clearcut_zzcg_zza;
    }

    public final /* synthetic */ zzdp zzbd() {
        return (zza) zza(zzg.zzkh, null, null);
    }

    public final /* synthetic */ zzdo zzbe() {
        return (zzcg) zza(zzg.zzki, null, null);
    }

    final void zzf(int i) {
        this.zzjq = i;
    }

    final int zzs() {
        return this.zzjq;
    }
}
