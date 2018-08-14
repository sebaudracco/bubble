package com.jaredrummler.android.processes.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProcFile extends File implements Parcelable {
    public static final Creator<ProcFile> CREATOR = new C33481();
    public final String content;

    static class C33481 implements Creator<ProcFile> {
        C33481() {
        }

        public ProcFile createFromParcel(Parcel in) {
            return new ProcFile(in);
        }

        public ProcFile[] newArray(int size) {
            return new ProcFile[size];
        }
    }

    protected static String readFile(String path) throws IOException {
        Throwable th;
        BufferedReader reader = null;
        try {
            StringBuilder output = new StringBuilder();
            BufferedReader reader2 = new BufferedReader(new FileReader(path));
            try {
                String newLine = "";
                for (String line = reader2.readLine(); line != null; line = reader2.readLine()) {
                    output.append(newLine).append(line);
                    newLine = "\n";
                }
                String stringBuilder = output.toString();
                if (reader2 != null) {
                    reader2.close();
                }
                return stringBuilder;
            } catch (Throwable th2) {
                th = th2;
                reader = reader2;
                if (reader != null) {
                    reader.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (reader != null) {
                reader.close();
            }
            throw th;
        }
    }

    protected ProcFile(String path) throws IOException {
        super(path);
        this.content = readFile(path);
    }

    protected ProcFile(Parcel in) {
        super(in.readString());
        this.content = in.readString();
    }

    public long length() {
        return (long) this.content.length();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getAbsolutePath());
        dest.writeString(this.content);
    }
}
