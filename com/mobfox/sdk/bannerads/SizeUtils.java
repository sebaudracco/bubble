package com.mobfox.sdk.bannerads;

import android.graphics.Point;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.ArrayList;
import java.util.List;

public class SizeUtils {
    public static final int DEFAULT_BANNER_HEIGHT = 50;
    public static final int DEFAULT_BANNER_WIDTH = 320;
    public static final int DEFAULT_INTERSTITIAL_HEIGHT = 480;
    public static final int DEFAULT_INTERSTITIAL_WIDTH = 320;
    private static final float MAX_DISTANCE = 1.5f;

    public static List<Point> SIZES() {
        List<Point> points = new ArrayList();
        points.add(0, new Point(HttpStatus.SC_MULTIPLE_CHOICES, 50));
        points.add(1, new Point(320, 50));
        points.add(2, new Point(HttpStatus.SC_MULTIPLE_CHOICES, 250));
        points.add(3, new Point(320, DEFAULT_INTERSTITIAL_HEIGHT));
        points.add(4, new Point(DEFAULT_INTERSTITIAL_HEIGHT, 320));
        points.add(5, new Point(728, 90));
        points.add(6, new Point(90, 728));
        points.add(7, new Point(Settings.MAX_DYNAMIC_ACQUISITION, 1024));
        points.add(8, new Point(1024, Settings.MAX_DYNAMIC_ACQUISITION));
        points.add(9, new Point(768, 1024));
        points.add(10, new Point(1024, 768));
        return points;
    }

    public static Point getNearestFitSupported(Point point) {
        Point winner = getNearestSupported(point);
        if (isTooFarOff(point, winner)) {
            return point;
        }
        return winner;
    }

    public static Point getNearestSupported(Point point) {
        return getNearest(point, SIZES());
    }

    public static Point getNearest(Point point, List<Point> points) {
        double minDist = -1.0d;
        Point min = null;
        for (Point thisPoint : points) {
            double dist = calcDist(point, thisPoint);
            if (minDist == -1.0d || (dist < minDist && isLarger(point, thisPoint))) {
                minDist = dist;
                min = thisPoint;
            }
        }
        return min;
    }

    public static double calcDist(Point point1, Point point2) {
        return Math.sqrt((double) (((point1.x - point2.x) * (point1.x - point2.x)) + ((point1.y - point2.y) * (point1.y - point2.y))));
    }

    public static boolean isLarger(Point point, Point nearPoint) {
        if (point.x < nearPoint.x || point.y < nearPoint.y) {
            return false;
        }
        return true;
    }

    private static boolean isTooFarOff(Point point, Point winner) {
        if (!isLarger(point, winner)) {
            return true;
        }
        float distY = (float) (point.y / winner.y);
        if (((float) (point.x / winner.x)) > MAX_DISTANCE || distY > MAX_DISTANCE) {
            return true;
        }
        return false;
    }
}
