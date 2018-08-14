package com.googlecode.mp4parser.h264.write;

import com.googlecode.mp4parser.h264.Debug;
import com.mobfox.sdk.utils.Utils;
import java.io.IOException;
import java.io.OutputStream;

public class CAVLCWriter extends BitstreamWriter {
    public CAVLCWriter(OutputStream out) {
        super(out);
    }

    public void writeU(int value, int n, String string) throws IOException {
        Debug.print(new StringBuilder(String.valueOf(string)).append(Utils.FILE_SEPARATOR).toString());
        writeNBit((long) value, n);
        Debug.println(new StringBuilder(Utils.FILE_SEPARATOR).append(value).toString());
    }

    public void writeUE(int value) throws IOException {
        int bits = 0;
        int cumul = 0;
        int i = 0;
        while (i < 15) {
            if (value < (1 << i) + cumul) {
                bits = i;
                break;
            } else {
                cumul += 1 << i;
                i++;
            }
        }
        writeNBit(0, bits);
        write1Bit(1);
        writeNBit((long) (value - cumul), bits);
    }

    public void writeUE(int value, String string) throws IOException {
        Debug.print(new StringBuilder(String.valueOf(string)).append(Utils.FILE_SEPARATOR).toString());
        writeUE(value);
        Debug.println(new StringBuilder(Utils.FILE_SEPARATOR).append(value).toString());
    }

    public void writeSE(int value, String string) throws IOException {
        int i;
        int i2 = 1;
        Debug.print(new StringBuilder(String.valueOf(string)).append(Utils.FILE_SEPARATOR).toString());
        int i3 = value << 1;
        if (value < 0) {
            i = -1;
        } else {
            i = 1;
        }
        i *= i3;
        if (value <= 0) {
            i2 = 0;
        }
        writeUE(i2 + i);
        Debug.println(new StringBuilder(Utils.FILE_SEPARATOR).append(value).toString());
    }

    public void writeBool(boolean value, String string) throws IOException {
        Debug.print(new StringBuilder(String.valueOf(string)).append(Utils.FILE_SEPARATOR).toString());
        write1Bit(value ? 1 : 0);
        Debug.println(new StringBuilder(Utils.FILE_SEPARATOR).append(value).toString());
    }

    public void writeU(int i, int n) throws IOException {
        writeNBit((long) i, n);
    }

    public void writeNBit(long value, int n, String string) throws IOException {
        Debug.print(new StringBuilder(String.valueOf(string)).append(Utils.FILE_SEPARATOR).toString());
        for (int i = 0; i < n; i++) {
            write1Bit(((int) (value >> ((n - i) - 1))) & 1);
        }
        Debug.println(new StringBuilder(Utils.FILE_SEPARATOR).append(value).toString());
    }

    public void writeTrailingBits() throws IOException {
        write1Bit(1);
        writeRemainingZero();
        flush();
    }

    public void writeSliceTrailingBits() {
        throw new IllegalStateException("todo");
    }
}
