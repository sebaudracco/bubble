package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Events;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: CalendarUtil */
public class C3219a {
    private static final SimpleDateFormat[] f8069a = new SimpleDateFormat[]{new SimpleDateFormat("yyyy-MM-dd'T'hh:mmZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US), new SimpleDateFormat("yyyy-MM-dd", Locale.US), new SimpleDateFormat("yyyy-MM", Locale.US), new SimpleDateFormat("yyyyMMddHHmmssZ", Locale.US), new SimpleDateFormat("yyyyMMddHHmm", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US), new SimpleDateFormat("yyyyMM", Locale.US), new SimpleDateFormat("yyyy", Locale.US)};
    private static String f8070b = C3219a.class.getSimpleName();

    @TargetApi(14)
    public static int m10742a(Context context) {
        Cursor query = context.getContentResolver().query(Events.CONTENT_URI, new String[]{"_id", "title"}, null, null, null);
        if (query == null || !query.moveToLast()) {
            return 0;
        }
        int columnIndex = query.getColumnIndex("title");
        int columnIndex2 = query.getColumnIndex("_id");
        String string = query.getString(columnIndex);
        String string2 = query.getString(columnIndex2);
        if (string != null) {
            columnIndex = Integer.parseInt(string2);
        } else {
            columnIndex = 0;
        }
        query.close();
        return columnIndex;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String m10743a(String str) {
        String str2 = null;
        int i = 0;
        if (str != null && !"".equals(str)) {
            Date parse;
            SimpleDateFormat[] simpleDateFormatArr = f8069a;
            int length = simpleDateFormatArr.length;
            int i2 = 0;
            while (i2 < length) {
                try {
                    parse = simpleDateFormatArr[i2].parse(str);
                    break;
                } catch (ParseException e) {
                    i2++;
                }
            }
            parse = null;
            if (parse != null) {
                DateFormat[] dateFormatArr = new DateFormat[]{new SimpleDateFormat("yyyyMMdd'T'HHmmssZ", Locale.US), new SimpleDateFormat("yyyyMMdd'T'HHmm", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US)};
                length = dateFormatArr.length;
                while (i < length) {
                    try {
                        str2 = dateFormatArr[i].format(Long.valueOf(parse.getTime()));
                        break;
                    } catch (IllegalArgumentException e2) {
                        i++;
                    }
                }
            }
        }
        return str2;
    }

    public static GregorianCalendar m10746b(String str) {
        SimpleDateFormat[] simpleDateFormatArr = f8069a;
        int length = simpleDateFormatArr.length;
        int i = 0;
        while (i < length) {
            SimpleDateFormat simpleDateFormat = simpleDateFormatArr[i];
            try {
                Date parse = simpleDateFormat.parse(str);
                Calendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(parse);
                Logger.m10359a(InternalLogLevel.INTERNAL, f8070b, "Date format: " + simpleDateFormat.toPattern());
                return (GregorianCalendar) gregorianCalendar;
            } catch (ParseException e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8070b, "Skipping format: " + simpleDateFormat.toPattern());
                i++;
            }
        }
        return null;
    }

    public static String m10745a(JSONArray jSONArray, int i, int i2) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i3 = 0;
        while (i3 < jSONArray.length()) {
            try {
                int i4 = jSONArray.getInt(i3);
                if (i4 < i || i4 > i2 || i4 == 0) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8070b, "Value not in range");
                } else {
                    stringBuilder.append(i4).append(",");
                }
                i3++;
            } catch (JSONException e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8070b, "Could not parse day " + e.getMessage());
                return null;
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        int length = stringBuilder2.length();
        if (length == 0) {
            return null;
        }
        if (stringBuilder2.charAt(length - 1) == ',') {
            return stringBuilder2.substring(0, length - 1);
        }
        return stringBuilder2;
    }

    public static String m10744a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                stringBuilder.append(jSONArray.get(i) + ",");
                i++;
            } catch (JSONException e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8070b, "Could not parse day object " + e.toString());
                return null;
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        int length = stringBuilder2.length();
        if (length == 0) {
            return null;
        }
        if (stringBuilder2.charAt(length - 1) == ',') {
            return stringBuilder2.substring(0, length - 1);
        }
        return stringBuilder2;
    }
}
