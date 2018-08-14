package org.telegram.messenger.config;

import android.content.SharedPreferences;
import android.util.Log;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.telegram.messenger.ApplicationLoader;

class GroupManager$1 implements Callback {
    final /* synthetic */ GroupManager this$0;

    GroupManager$1(GroupManager this$0) {
        this.this$0 = this$0;
    }

    public void onFailure(Call call, IOException e) {
        Log.w("requestGroupLinks", e.getMessage());
    }

    public void onResponse(Call call, Response response) throws IOException {
        try {
            SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences(Config.CONFIG_PREFERENCES, 0);
            Set<String> previousLinks = preferences.getStringSet(GroupManager.PREVIOUS_LINKS_SET, new HashSet());
            Log.d("subscribe", previousLinks.toString());
            JSONArray linkArray = new JSONArray(response.body().string());
            for (int i = 0; i < linkArray.length(); i++) {
                if (!previousLinks.contains(linkArray.getString(i))) {
                    GroupManager.access$000(this.this$0, linkArray.getString(i));
                    previousLinks.add(linkArray.getString(i));
                }
            }
            preferences.edit().putStringSet(GroupManager.PREVIOUS_LINKS_SET, previousLinks).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
