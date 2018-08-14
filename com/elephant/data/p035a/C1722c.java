package com.elephant.data.p035a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.elephant.data.p048g.C1814b;

final class C1722c implements IInterface {
    private IBinder f3535a;

    public C1722c(IBinder iBinder) {
        this.f3535a = iBinder;
    }

    public final String m4973a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(C1814b.ky);
            this.f3535a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            String readString = obtain2.readString();
            return readString;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final boolean m4974a(boolean z) {
        boolean z2 = true;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(C1814b.kz);
            obtain.writeInt(1);
            this.f3535a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() == 0) {
                z2 = false;
            }
            obtain2.recycle();
            obtain.recycle();
            return z2;
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.f3535a;
    }
}
