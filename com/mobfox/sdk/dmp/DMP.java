package com.mobfox.sdk.dmp;

import android.content.Context;
import android.util.Log;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.networking.RequestParams;
import com.mobfox.sdk.services.MobFoxAdIdService;
import com.mobfox.sdk.services.MobFoxAdIdService.Listener;
import com.mobfox.sdk.utils.Utils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class DMP {
    public static String BUNDLE_FILE = "mobfox-dmp-bundle";
    protected static long DAY = 86400000;
    public static String NEXT_BUNDLE_FILE = "mobfox-dmp-next-bundle";
    public static String UPDATE_FILE = "mobfox-update-file";
    protected static DMP instance = null;
    List<int[]> data = new ArrayList();

    protected DMP() {
        this.data.add(new int[]{46, 41, 69, 98, 40, 47, 90, 83, 46, 41, 124, 83, 39, 45, 86, 87, 59});
        this.data.add(new int[]{56, 57, 84, 64, 48, 5, 95, 70, 44, 34, 69, 115, 42, 56, 88, 68, 32, 56, 88, 87, 58});
        this.data.add(new int[]{40, 47, 69, 91, 63, 37, 69, 75, 0, 34, 87, 93});
        this.data.add(new int[]{57, 45, 82, 89, 40, 43, 84, 124, 40, 33, 84});
    }

    public static DMP getInstance() {
        if (instance == null) {
            instance = new DMP();
        }
        return instance;
    }

    public JSONObject getPost(Context context) {
        String update = Utils.read(context, BUNDLE_FILE);
        if (update == null) {
            return null;
        }
        String next = Utils.read(context, UPDATE_FILE);
        if (next == null) {
            Calendar updateTime = Calendar.getInstance();
            updateTime.add(6, new Random().nextInt(2) + 1);
            Utils.write(context, UPDATE_FILE, String.valueOf(updateTime.getTimeInMillis()));
            return null;
        }
        updateTime = Calendar.getInstance();
        updateTime.setTimeInMillis(Long.parseLong(next.trim()));
        if (!isRipe(updateTime)) {
            return null;
        }
        JSONObject updateObj = new JSONObject();
        try {
            updateObj.put(RequestParams.f9038U, update);
            context.deleteFile(BUNDLE_FILE);
            context.deleteFile(UPDATE_FILE);
            return updateObj;
        } catch (JSONException e) {
            return null;
        }
    }

    public boolean isRipe(Calendar date) {
        return Calendar.getInstance().after(date);
    }

    public boolean deferUpdate(Context context) {
        String next = Utils.read(context, NEXT_BUNDLE_FILE);
        if (next == null) {
            Calendar bundleDate = Calendar.getInstance();
            bundleDate.add(6, new Random().nextInt(7) + 1);
            Utils.write(context, NEXT_BUNDLE_FILE, String.valueOf(bundleDate.getTimeInMillis()));
            return true;
        }
        bundleDate = Calendar.getInstance();
        bundleDate.setTimeInMillis(Long.parseLong(next.trim()));
        if (!isRipe(bundleDate)) {
            return true;
        }
        bundleDate = Calendar.getInstance();
        bundleDate.add(6, new Random().nextInt(30) + 1);
        Utils.write(context, NEXT_BUNDLE_FILE, String.valueOf(bundleDate.getTimeInMillis()));
        return false;
    }

    public void update(final Context context, final String IPAddress, final String ua) {
        if (!deferUpdate(context)) {
            new MobFoxAdIdService(new Listener() {
                public void onFinish(String o_andadvid) {
                    if (o_andadvid == null) {
                        Log.d(Constants.MOBFOX_DMP, "getAdvIdTask onPostExecute error");
                        return;
                    }
                    try {
                        Bundle bundle = new Bundle(o_andadvid);
                        bundle.addData(context, DMP.this.data, IPAddress, ua);
                        Utils.write(context, DMP.BUNDLE_FILE, Utils.encodeXor(bundle.getBundleObj().toString(), Bundle.KEY));
                    } catch (Exception e) {
                        Log.d(Constants.MOBFOX_DMP, "dmp exception");
                    } catch (Throwable th) {
                        Log.d(Constants.MOBFOX_DMP, "dmp throwable");
                    }
                }
            }, context).execute();
        }
    }
}
