package com.processes.oneaudience.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProcFile extends File implements Parcelable {
    public static final Creator<ProcFile> CREATOR = new C39361();
    public final String f9311b;

    static class C39361 implements Creator<ProcFile> {
        C39361() {
        }

        public ProcFile m12414a(Parcel parcel) {
            return new ProcFile(parcel);
        }

        public ProcFile[] m12415a(int i) {
            return new ProcFile[i];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m12414a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m12415a(i);
        }
    }

    protected ProcFile(Parcel parcel) {
        super(parcel.readString());
        this.f9311b = parcel.readString();
    }

    protected ProcFile(String str) {
        super(str);
        this.f9311b = m12409b(str);
    }

    static String m12409b(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new FileReader(str));
            try {
                String str2 = "";
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    stringBuilder.append(str2).append(readLine);
                    str2 = "\n";
                }
                str2 = stringBuilder.toString();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                    }
                }
                return str2;
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    public int describeContents() {
        return 0;
    }

    public long length() {
        return (long) this.f9311b.length();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getAbsolutePath());
        parcel.writeString(this.f9311b);
    }
}
