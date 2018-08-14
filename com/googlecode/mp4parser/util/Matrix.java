package com.googlecode.mp4parser.util;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.nio.ByteBuffer;

public class Matrix {
    public static final Matrix ROTATE_0 = new Matrix(MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, 0.0d, 0.0d, MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, 0.0d, 0.0d, MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, 0.0d, 0.0d);
    public static final Matrix ROTATE_180 = new Matrix(-1.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, 0.0d, 0.0d);
    public static final Matrix ROTATE_270 = new Matrix(0.0d, -1.0d, MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, 0.0d, 0.0d, 0.0d, MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, 0.0d, 0.0d);
    public static final Matrix ROTATE_90 = new Matrix(0.0d, MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, -1.0d, 0.0d, 0.0d, 0.0d, MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE, 0.0d, 0.0d);
    double f6683a;
    double f6684b;
    double f6685c;
    double f6686d;
    double tx;
    double ty;
    double f6687u;
    double f6688v;
    double f6689w;

    public Matrix(double a, double b, double c, double d, double u, double v, double w, double tx, double ty) {
        this.f6687u = u;
        this.f6688v = v;
        this.f6689w = w;
        this.f6683a = a;
        this.f6684b = b;
        this.f6685c = c;
        this.f6686d = d;
        this.tx = tx;
        this.ty = ty;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Matrix matrix = (Matrix) o;
        if (Double.compare(matrix.f6683a, this.f6683a) != 0) {
            return false;
        }
        if (Double.compare(matrix.f6684b, this.f6684b) != 0) {
            return false;
        }
        if (Double.compare(matrix.f6685c, this.f6685c) != 0) {
            return false;
        }
        if (Double.compare(matrix.f6686d, this.f6686d) != 0) {
            return false;
        }
        if (Double.compare(matrix.tx, this.tx) != 0) {
            return false;
        }
        if (Double.compare(matrix.ty, this.ty) != 0) {
            return false;
        }
        if (Double.compare(matrix.f6687u, this.f6687u) != 0) {
            return false;
        }
        if (Double.compare(matrix.f6688v, this.f6688v) != 0) {
            return false;
        }
        if (Double.compare(matrix.f6689w, this.f6689w) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long temp = Double.doubleToLongBits(this.f6687u);
        int result = (int) ((temp >>> 32) ^ temp);
        temp = Double.doubleToLongBits(this.f6688v);
        result = (result * 31) + ((int) ((temp >>> 32) ^ temp));
        temp = Double.doubleToLongBits(this.f6689w);
        result = (result * 31) + ((int) ((temp >>> 32) ^ temp));
        temp = Double.doubleToLongBits(this.f6683a);
        result = (result * 31) + ((int) ((temp >>> 32) ^ temp));
        temp = Double.doubleToLongBits(this.f6684b);
        result = (result * 31) + ((int) ((temp >>> 32) ^ temp));
        temp = Double.doubleToLongBits(this.f6685c);
        result = (result * 31) + ((int) ((temp >>> 32) ^ temp));
        temp = Double.doubleToLongBits(this.f6686d);
        result = (result * 31) + ((int) ((temp >>> 32) ^ temp));
        temp = Double.doubleToLongBits(this.tx);
        result = (result * 31) + ((int) ((temp >>> 32) ^ temp));
        temp = Double.doubleToLongBits(this.ty);
        return (result * 31) + ((int) ((temp >>> 32) ^ temp));
    }

    public String toString() {
        if (equals(ROTATE_0)) {
            return "Rotate 0°";
        }
        if (equals(ROTATE_90)) {
            return "Rotate 90°";
        }
        if (equals(ROTATE_180)) {
            return "Rotate 180°";
        }
        if (equals(ROTATE_270)) {
            return "Rotate 270°";
        }
        return "Matrix{u=" + this.f6687u + ", v=" + this.f6688v + ", w=" + this.f6689w + ", a=" + this.f6683a + ", b=" + this.f6684b + ", c=" + this.f6685c + ", d=" + this.f6686d + ", tx=" + this.tx + ", ty=" + this.ty + '}';
    }

    public static Matrix fromFileOrder(double a, double b, double u, double c, double d, double v, double tx, double ty, double w) {
        return new Matrix(a, b, c, d, u, v, w, tx, ty);
    }

    public static Matrix fromByteBuffer(ByteBuffer byteBuffer) {
        return fromFileOrder(IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint0230(byteBuffer), IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint0230(byteBuffer), IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint0230(byteBuffer));
    }

    public void getContent(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.f6683a);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.f6684b);
        IsoTypeWriter.writeFixedPoint0230(byteBuffer, this.f6687u);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.f6685c);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.f6686d);
        IsoTypeWriter.writeFixedPoint0230(byteBuffer, this.f6688v);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.tx);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.ty);
        IsoTypeWriter.writeFixedPoint0230(byteBuffer, this.f6689w);
    }
}
