package com.yandex.metrica;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMetricaService extends IInterface {

    public static abstract class Stub extends Binder implements IMetricaService {

        private static class Proxy implements IMetricaService {
            private IBinder f11407a;

            Proxy(IBinder remote) {
                this.f11407a = remote;
            }

            public IBinder asBinder() {
                return this.f11407a;
            }

            public void reportEvent(String event, int type, String value, Bundle libCfg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.yandex.metrica.IMetricaService");
                    obtain.writeString(event);
                    obtain.writeInt(type);
                    obtain.writeString(value);
                    if (libCfg != null) {
                        obtain.writeInt(1);
                        libCfg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f11407a.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void reportData(Bundle data) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.yandex.metrica.IMetricaService");
                    if (data != null) {
                        obtain.writeInt(1);
                        data.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f11407a.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.yandex.metrica.IMetricaService");
        }

        public static IMetricaService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface queryLocalInterface = obj.queryLocalInterface("com.yandex.metrica.IMetricaService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMetricaService)) {
                return new Proxy(obj);
            }
            return (IMetricaService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle bundle = null;
            switch (code) {
                case 1:
                    data.enforceInterface("com.yandex.metrica.IMetricaService");
                    String readString = data.readString();
                    int readInt = data.readInt();
                    String readString2 = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    reportEvent(readString, readInt, readString2, bundle);
                    return true;
                case 2:
                    data.enforceInterface("com.yandex.metrica.IMetricaService");
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    reportData(bundle);
                    return true;
                case 1598968902:
                    reply.writeString("com.yandex.metrica.IMetricaService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void reportData(Bundle bundle) throws RemoteException;

    void reportEvent(String str, int i, String str2, Bundle bundle) throws RemoteException;
}
