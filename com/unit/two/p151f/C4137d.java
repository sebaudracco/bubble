package com.unit.two.p151f;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.unit.two.p147c.C4096a;

final class C4137d implements IInterface {
    private IBinder f9668a;

    public C4137d(IBinder iBinder) {
        this.f9668a = iBinder;
    }

    public final String m12767a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(C4096a.bt);
            this.f9668a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            String readString = obtain2.readString();
            return readString;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final boolean m12768a(boolean z) {
        boolean z2 = true;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(C4096a.bu);
            obtain.writeInt(1);
            this.f9668a.transact(2, obtain, obtain2, 0);
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
        return this.f9668a;
    }
}
