package org.telegram.ui.Components.Paint;

import android.graphics.PointF;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;

public class Point {
    public boolean edge;
    public double f12660x;
    public double f12661y;
    public double f12662z;

    public Point(double x, double y, double z) {
        this.f12660x = x;
        this.f12661y = y;
        this.f12662z = z;
    }

    public Point(Point point) {
        this.f12660x = point.f12660x;
        this.f12661y = point.f12661y;
        this.f12662z = point.f12662z;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        if (!(this.f12660x == other.f12660x && this.f12661y == other.f12661y && this.f12662z == other.f12662z)) {
            z = false;
        }
        return z;
    }

    Point multiplySum(Point point, double scalar) {
        return new Point((this.f12660x + point.f12660x) * scalar, (this.f12661y + point.f12661y) * scalar, (this.f12662z + point.f12662z) * scalar);
    }

    Point multiplyAndAdd(double scalar, Point point) {
        return new Point((this.f12660x * scalar) + point.f12660x, (this.f12661y * scalar) + point.f12661y, (this.f12662z * scalar) + point.f12662z);
    }

    void alteringAddMultiplication(Point point, double scalar) {
        this.f12660x += point.f12660x * scalar;
        this.f12661y += point.f12661y * scalar;
        this.f12662z += point.f12662z * scalar;
    }

    Point add(Point point) {
        return new Point(this.f12660x + point.f12660x, this.f12661y + point.f12661y, this.f12662z + point.f12662z);
    }

    Point substract(Point point) {
        return new Point(this.f12660x - point.f12660x, this.f12661y - point.f12661y, this.f12662z - point.f12662z);
    }

    Point multiplyByScalar(double scalar) {
        return new Point(this.f12660x * scalar, this.f12661y * scalar, this.f12662z * scalar);
    }

    Point getNormalized() {
        return multiplyByScalar(MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE / getMagnitude());
    }

    private double getMagnitude() {
        return Math.sqrt(((this.f12660x * this.f12660x) + (this.f12661y * this.f12661y)) + (this.f12662z * this.f12662z));
    }

    float getDistanceTo(Point point) {
        return (float) Math.sqrt((Math.pow(this.f12660x - point.f12660x, 2.0d) + Math.pow(this.f12661y - point.f12661y, 2.0d)) + Math.pow(this.f12662z - point.f12662z, 2.0d));
    }

    PointF toPointF() {
        return new PointF((float) this.f12660x, (float) this.f12661y);
    }
}
