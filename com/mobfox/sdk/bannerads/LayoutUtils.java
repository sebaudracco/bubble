package com.mobfox.sdk.bannerads;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout.LayoutParams;
import com.mobfox.sdk.constants.Constants;

public class LayoutUtils {
    public static void setSmartDimensions(Context context, View view, int height) {
        setDimensions(context, view, getScreenWidth(context), height);
    }

    public static void setDimensions(Context context, View view, int width, int height) {
        try {
            LayoutParams layout_params = (LayoutParams) view.getLayoutParams();
            layout_params.width = convertDpToPixel((float) width, context);
            layout_params.height = convertDpToPixel((float) height, context);
            view.setLayoutParams(layout_params);
        } catch (Exception e) {
            Log.d(Constants.MOBFOX_BANNER, "smart params error");
        }
    }

    public static Point getScreenSize(Context context) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return new Point((int) convertPixelToDP((float) size.x, context), (int) convertPixelToDP((float) size.y, context));
    }

    public static int getScreenWidth(Context context) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return (int) convertPixelToDP((float) size.x, context);
    }

    public static int getScreenHeight(Context context) {
        Display display = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static boolean aboveTheFold(Context context, View view) {
        int screenHeight = getScreenHeight(context);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int bannerTop = location[1];
        if (bannerTop >= 0 && bannerTop < screenHeight) {
            return true;
        }
        return false;
    }

    public static int convertDpToPixel(float dp, Context context) {
        return (int) (dp * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    public static float convertPixelToDP(float px, Context context) {
        return px / (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }
}
