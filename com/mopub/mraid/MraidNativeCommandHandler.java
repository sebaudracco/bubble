package com.mopub.mraid;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.appnext.base.p023b.C1042c;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.plus.PlusShare;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Intents;
import com.mopub.common.util.Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MraidNativeCommandHandler {
    public static final String ANDROID_CALENDAR_CONTENT_TYPE = "vnd.android.cursor.item/event";
    private static final String[] DATE_FORMATS = new String[]{"yyyy-MM-dd'T'HH:mm:ssZZZZZ", "yyyy-MM-dd'T'HH:mmZZZZZ"};
    private static final int MAX_NUMBER_DAYS_IN_MONTH = 31;
    @VisibleForTesting
    static final String MIME_TYPE_HEADER = "Content-Type";

    interface MraidCommandFailureListener {
        void onFailure(MraidCommandException mraidCommandException);
    }

    private static class MoPubMediaScannerConnectionClient implements MediaScannerConnectionClient {
        private final String mFilename;
        private MediaScannerConnection mMediaScannerConnection;
        private final String mMimeType;

        private MoPubMediaScannerConnectionClient(String filename, String mimeType) {
            this.mFilename = filename;
            this.mMimeType = mimeType;
        }

        private void setMediaScannerConnection(MediaScannerConnection connection) {
            this.mMediaScannerConnection = connection;
        }

        public void onMediaScannerConnected() {
            if (this.mMediaScannerConnection != null) {
                this.mMediaScannerConnection.scanFile(this.mFilename, this.mMimeType);
            }
        }

        public void onScanCompleted(String path, Uri uri) {
            if (this.mMediaScannerConnection != null) {
                this.mMediaScannerConnection.disconnect();
            }
        }
    }

    void createCalendarEvent(Context context, Map<String, String> params) throws MraidCommandException {
        if (isCalendarAvailable(context)) {
            try {
                Map<String, Object> calendarParams = translateJSParamsToAndroidCalendarEventMapping(params);
                Intent intent = new Intent("android.intent.action.INSERT").setType(ANDROID_CALENDAR_CONTENT_TYPE);
                for (String key : calendarParams.keySet()) {
                    Object value = calendarParams.get(key);
                    if (value instanceof Long) {
                        intent.putExtra(key, ((Long) value).longValue());
                    } else if (value instanceof Integer) {
                        intent.putExtra(key, ((Integer) value).intValue());
                    } else {
                        intent.putExtra(key, (String) value);
                    }
                }
                intent.setFlags(ErrorDialogData.BINDER_CRASH);
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                MoPubLog.m12061d("no calendar app installed");
                throw new MraidCommandException("Action is unsupported on this device - no calendar app installed");
            } catch (Throwable e2) {
                MoPubLog.m12061d("create calendar: invalid parameters " + e2.getMessage());
                throw new MraidCommandException(e2);
            } catch (Throwable e22) {
                MoPubLog.m12061d("could not create calendar event");
                throw new MraidCommandException(e22);
            }
        }
        MoPubLog.m12061d("unsupported action createCalendarEvent for devices pre-ICS");
        throw new MraidCommandException("Action is unsupported on this device (need Android version Ice Cream Sandwich or above)");
    }

    void storePicture(@NonNull Context context, @NonNull String imageUrl, @NonNull MraidCommandFailureListener failureListener) throws MraidCommandException {
        if (!isStorePictureSupported(context)) {
            MoPubLog.m12061d("Error downloading file - the device does not have an SD card mounted, or the Android permission is not granted.");
            throw new MraidCommandException("Error downloading file  - the device does not have an SD card mounted, or the Android permission is not granted.");
        } else if (context instanceof Activity) {
            showUserDialog(context, imageUrl, failureListener);
        } else {
            Toast.makeText(context, "Downloading image to Picture gallery...", 0).show();
            downloadImage(context, imageUrl, failureListener);
        }
    }

    boolean isTelAvailable(Context context) {
        Intent telIntent = new Intent("android.intent.action.DIAL");
        telIntent.setData(Uri.parse("tel:"));
        return Intents.deviceCanHandleIntent(context, telIntent);
    }

    boolean isSmsAvailable(Context context) {
        Intent smsIntent = new Intent("android.intent.action.VIEW");
        smsIntent.setData(Uri.parse("sms:"));
        return Intents.deviceCanHandleIntent(context, smsIntent);
    }

    public static boolean isStorePictureSupported(Context context) {
        return "mounted".equals(Environment.getExternalStorageState()) && DeviceUtils.isPermissionGranted(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    static boolean isCalendarAvailable(Context context) {
        return Intents.deviceCanHandleIntent(context, new Intent("android.intent.action.INSERT").setType(ANDROID_CALENDAR_CONTENT_TYPE));
    }

    boolean isInlineVideoAvailable(@NonNull Activity activity, @NonNull View view) {
        View tempView = view;
        while (tempView.isHardwareAccelerated() && !Utils.bitMaskContainsFlag(tempView.getLayerType(), 1)) {
            if (tempView.getParent() instanceof View) {
                tempView = (View) tempView.getParent();
            } else {
                Window window = activity.getWindow();
                if (window == null || !Utils.bitMaskContainsFlag(window.getAttributes().flags, 16777216)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private Map<String, Object> translateJSParamsToAndroidCalendarEventMapping(Map<String, String> params) {
        Map<String, Object> validatedParamsMapping = new HashMap();
        if (params.containsKey(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION) && params.containsKey("start")) {
            validatedParamsMapping.put("title", params.get(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION));
            if (!params.containsKey("start") || params.get("start") == null) {
                throw new IllegalArgumentException("Invalid calendar event: start is null.");
            }
            Date startDateTime = parseDate((String) params.get("start"));
            if (startDateTime != null) {
                validatedParamsMapping.put("beginTime", Long.valueOf(startDateTime.getTime()));
                if (params.containsKey("end") && params.get("end") != null) {
                    Date endDateTime = parseDate((String) params.get("end"));
                    if (endDateTime != null) {
                        validatedParamsMapping.put("endTime", Long.valueOf(endDateTime.getTime()));
                    } else {
                        throw new IllegalArgumentException("Invalid calendar event: end time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
                    }
                }
                if (params.containsKey("location")) {
                    validatedParamsMapping.put("eventLocation", params.get("location"));
                }
                if (params.containsKey("summary")) {
                    validatedParamsMapping.put(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, params.get("summary"));
                }
                if (params.containsKey("transparency")) {
                    validatedParamsMapping.put("availability", Integer.valueOf(((String) params.get("transparency")).equals("transparent") ? 1 : 0));
                }
                validatedParamsMapping.put("rrule", parseRecurrenceRule(params));
                return validatedParamsMapping;
            }
            throw new IllegalArgumentException("Invalid calendar event: start time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
        }
        throw new IllegalArgumentException("Missing start and description fields");
    }

    private Date parseDate(String dateTime) {
        Date result = null;
        String[] strArr = DATE_FORMATS;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            try {
                result = new SimpleDateFormat(strArr[i], Locale.US).parse(dateTime);
                if (result != null) {
                    break;
                }
                i++;
            } catch (ParseException e) {
            }
        }
        return result;
    }

    private String parseRecurrenceRule(Map<String, String> params) throws IllegalArgumentException {
        StringBuilder rule = new StringBuilder();
        if (params.containsKey("frequency")) {
            String frequency = (String) params.get("frequency");
            int interval = -1;
            if (params.containsKey(C1042c.jE)) {
                interval = Integer.parseInt((String) params.get(C1042c.jE));
            }
            if ("daily".equals(frequency)) {
                rule.append("FREQ=DAILY;");
                if (interval != -1) {
                    rule.append("INTERVAL=" + interval + ";");
                }
            } else if ("weekly".equals(frequency)) {
                rule.append("FREQ=WEEKLY;");
                if (interval != -1) {
                    rule.append("INTERVAL=" + interval + ";");
                }
                if (params.containsKey("daysInWeek")) {
                    String weekdays = translateWeekIntegersToDays((String) params.get("daysInWeek"));
                    if (weekdays == null) {
                        throw new IllegalArgumentException("invalid ");
                    }
                    rule.append("BYDAY=" + weekdays + ";");
                }
            } else if ("monthly".equals(frequency)) {
                rule.append("FREQ=MONTHLY;");
                if (interval != -1) {
                    rule.append("INTERVAL=" + interval + ";");
                }
                if (params.containsKey("daysInMonth")) {
                    String monthDays = translateMonthIntegersToDays((String) params.get("daysInMonth"));
                    if (monthDays == null) {
                        throw new IllegalArgumentException();
                    }
                    rule.append("BYMONTHDAY=" + monthDays + ";");
                }
            } else {
                throw new IllegalArgumentException("frequency is only supported for daily, weekly, and monthly.");
            }
        }
        return rule.toString();
    }

    private String translateWeekIntegersToDays(String expression) throws IllegalArgumentException {
        StringBuilder daysResult = new StringBuilder();
        boolean[] daysAlreadyCounted = new boolean[7];
        String[] days = expression.split(",");
        for (String day : days) {
            int dayNumber = Integer.parseInt(day);
            if (dayNumber == 7) {
                dayNumber = 0;
            }
            if (!daysAlreadyCounted[dayNumber]) {
                daysResult.append(dayNumberToDayOfWeekString(dayNumber) + ",");
                daysAlreadyCounted[dayNumber] = true;
            }
        }
        if (days.length == 0) {
            throw new IllegalArgumentException("must have at least 1 day of the week if specifying repeating weekly");
        }
        daysResult.deleteCharAt(daysResult.length() - 1);
        return daysResult.toString();
    }

    private String translateMonthIntegersToDays(String expression) throws IllegalArgumentException {
        StringBuilder daysResult = new StringBuilder();
        boolean[] daysAlreadyCounted = new boolean[63];
        String[] days = expression.split(",");
        for (String day : days) {
            int dayNumber = Integer.parseInt(day);
            if (!daysAlreadyCounted[dayNumber + 31]) {
                daysResult.append(dayNumberToDayOfMonthString(dayNumber) + ",");
                daysAlreadyCounted[dayNumber + 31] = true;
            }
        }
        if (days.length == 0) {
            throw new IllegalArgumentException("must have at least 1 day of the month if specifying repeating weekly");
        }
        daysResult.deleteCharAt(daysResult.length() - 1);
        return daysResult.toString();
    }

    private String dayNumberToDayOfWeekString(int number) throws IllegalArgumentException {
        switch (number) {
            case 0:
                return "SU";
            case 1:
                return "MO";
            case 2:
                return "TU";
            case 3:
                return "WE";
            case 4:
                return "TH";
            case 5:
                return "FR";
            case 6:
                return "SA";
            default:
                throw new IllegalArgumentException("invalid day of week " + number);
        }
    }

    private String dayNumberToDayOfMonthString(int number) throws IllegalArgumentException {
        if (number != 0 && number >= -31 && number <= 31) {
            return "" + number;
        }
        throw new IllegalArgumentException("invalid day of month " + number);
    }

    void downloadImage(final Context context, String uriString, final MraidCommandFailureListener failureListener) {
        AsyncTasks.safeExecuteOnExecutor(new DownloadImageAsyncTask(context, new C3713xe6534241() {
            public void onSuccess() {
                MoPubLog.m12061d("Image successfully saved.");
            }

            public void onFailure() {
                Toast.makeText(context, "Image failed to download.", 0).show();
                MoPubLog.m12061d("Error downloading and saving image file.");
                failureListener.onFailure(new MraidCommandException("Error downloading and saving image file."));
            }
        }), uriString);
    }

    private void showUserDialog(final Context context, final String imageUrl, final MraidCommandFailureListener failureListener) {
        new Builder(context).setTitle("Save Image").setMessage("Download image to Picture gallery?").setNegativeButton("Cancel", null).setPositiveButton("Okay", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MraidNativeCommandHandler.this.downloadImage(context, imageUrl, failureListener);
            }
        }).setCancelable(true).show();
    }
}
