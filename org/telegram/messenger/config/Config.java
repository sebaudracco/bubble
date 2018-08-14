package org.telegram.messenger.config;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.wBubble_7453037.R;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Config {
    public static final String CONFIG_PREFERENCES = "ConfigPref";
    private String appName;
    private Bitmap background;
    private CustomTheme defaultTheme;
    private String faqUrl;
    private List<String> groupList;
    private Drawable icon;
    private String inviteUrl;
    JSONObject settings;
    private List<String> stickerList;
    private List<CustomTheme> themeList;
    private boolean useDefaultWelcome;

    public static class ProxySettings {
        public static final String ADDRESS = "prx.appioapp.com";
        public static final String PASSWORD = "hop7oogeiboK";
        public static final int PORT = 1081;
        public static final String PROXY_PREF = "proxy_applied";
        public static final String[] TELEGRAM_HOSTS = new String[]{"https://telegram.me/", "https://telegram.org/", "https://core.telegram.org/", "https://desktop.telegram.org/", "https://macos.telegram.org/", "https://web.telegram.org/", "https://venus.web.telegram.org/", "https://pluto.web.telegram.org/", "https://flora.web.telegram.org/", "https://flora-1.web.telegram.org/"};
        public static final String USER = "teleuser1";
    }

    public Config(Context context) {
        try {
            this.settings = new JSONObject(loadSettings(context));
            this.appName = context.getString(R.string.app_name);
            this.faqUrl = this.settings.getString("faqUrl");
            this.inviteUrl = this.settings.getString("inviteUrl");
            this.useDefaultWelcome = this.settings.getBoolean("useDefaultWelcome");
            this.stickerList = readStickerList(this.settings);
            this.groupList = readGroupsList(this.settings);
            this.themeList = readThemeList(this.settings);
            this.defaultTheme = readTheme(this.settings, true);
            this.themeList.add(this.defaultTheme);
            String iconUrl = this.settings.getString("icon");
            if (!(iconUrl == null || iconUrl.equals(""))) {
                Bitmap b = BitmapFactory.decodeStream(context.getAssets().open(iconUrl));
                b.setDensity(0);
                this.icon = new BitmapDrawable(context.getResources(), b);
            }
            String backgroundUrl = this.settings.getString("backgroundImage");
            if (backgroundUrl != null && !backgroundUrl.equals("")) {
                this.background = BitmapFactory.decodeStream(context.getAssets().open(backgroundUrl));
            }
        } catch (JSONException e) {
            Log.e("Config", "Json parse error: " + e.getMessage());
        } catch (IOException e2) {
            Log.e("Config", "Json read error: " + e2.getMessage());
        }
    }

    private List<CustomTheme> readThemeList(JSONObject settings) throws JSONException {
        List<CustomTheme> customThemeList = new ArrayList();
        for (int i = 0; i < settings.getJSONArray("theme").length(); i++) {
            customThemeList.add(readTheme(settings.getJSONArray("theme").getJSONObject(i), false));
        }
        return customThemeList;
    }

    private CustomTheme readTheme(JSONObject jsonTheme, boolean defaultTheme) throws JSONException {
        CustomTheme customTheme = new CustomTheme();
        if (defaultTheme) {
            customTheme.setName(getAppName());
            customTheme.setApplyTheme(true);
        } else {
            customTheme.setName(jsonTheme.getString("themeName"));
        }
        customTheme.setActionBarColor(readColor(jsonTheme, "actionBarColor"));
        customTheme.setBackgroundColor(readColor(jsonTheme, "backgroundColor"));
        customTheme.setMessageBarColor(readColor(jsonTheme, "messageBarColor"));
        customTheme.setReceivedColor(readColor(jsonTheme, "receivedColor"));
        customTheme.setSentColor(readColor(jsonTheme, "sentColor"));
        customTheme.setTextColor(readColor(jsonTheme, "textColor"));
        customTheme.setActionColor(readColor(jsonTheme, "actionColor"));
        return customTheme;
    }

    private Integer readColor(JSONObject jsonTheme, String name) throws JSONException {
        String color = jsonTheme.getString(name);
        if (color == null || color.equals("")) {
            return null;
        }
        return Integer.valueOf(Color.parseColor("#" + color));
    }

    private List<String> readStickerList(JSONObject settings) throws JSONException {
        JSONArray jArray = settings.getJSONArray(StickerManager.STICKERS_PREF);
        List<String> resultList = new ArrayList();
        if (jArray != null) {
            for (int i = 0; i < jArray.length(); i++) {
                resultList.add(jArray.getJSONObject(i).getString("stickerUrl"));
            }
        }
        return resultList;
    }

    private List<String> readGroupsList(JSONObject settings) throws JSONException {
        JSONArray jArray = settings.getJSONArray(GroupManager.GROUPS_PREF);
        List<String> resultList = new ArrayList();
        if (jArray != null) {
            for (int i = 0; i < jArray.length(); i++) {
                resultList.add(jArray.getJSONObject(i).getString("groupsUrl"));
            }
        }
        return resultList;
    }

    public Bitmap getBackground() {
        return this.background;
    }

    public String loadSettings(Context context) throws IOException {
        try {
            InputStream is = context.getAssets().open("settings.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            return json;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<IntroPage> getIntroPages(Context context) {
        List<IntroPage> list = new ArrayList();
        try {
            JSONArray jsonArray = this.settings.getJSONArray("welcomeSteps");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String iconUrl = object.getString("stepImage");
                Drawable icon = null;
                if (!(iconUrl == null || iconUrl.equals(""))) {
                    Bitmap b = BitmapFactory.decodeStream(context.getAssets().open(iconUrl));
                    b.setDensity(0);
                    icon = new BitmapDrawable(context.getResources(), b);
                }
                list.add(new IntroPage(icon, object.getString("stepTitle"), object.getString("stepText")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return list;
    }

    public List<String> getGroupList() {
        return this.groupList;
    }

    public List<CustomTheme> getThemeList() {
        return this.themeList;
    }

    public List<String> getStickerList() {
        return this.stickerList;
    }

    public String getFaqUrl() {
        return this.faqUrl;
    }

    public String getInviteUrl() {
        return this.inviteUrl;
    }

    public String getAppName() {
        return this.appName;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public boolean isUseDefaultWelcome() {
        return this.useDefaultWelcome;
    }

    public CustomTheme getDefaultTheme() {
        return this.defaultTheme;
    }
}
