package org.telegram.messenger.config;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.util.List;
import org.telegram.ui.ActionBar.Theme;

public class ThemeManager {
    private static volatile ThemeManager Instance = null;
    public static final String THEME_PREF = "theme";

    public static ThemeManager getInstance() {
        ThemeManager localInstance = Instance;
        if (localInstance == null) {
            synchronized (ThemeManager.class) {
                try {
                    localInstance = Instance;
                    if (localInstance == null) {
                        ThemeManager localInstance2 = new ThemeManager();
                        try {
                            Instance = localInstance2;
                            localInstance = localInstance2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            localInstance = localInstance2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return localInstance;
    }

    public void createThemes(List<CustomTheme> customThemeList, Bitmap background) {
        if (customThemeList != null) {
            for (CustomTheme customTheme : customThemeList) {
                String name = customTheme.getName();
                if (!(name == null || name.equals(""))) {
                    Theme.saveCurrentTheme(name, true);
                    if (customTheme.getActionBarColor() != null) {
                        Theme.setColor(Theme.key_actionBarDefault, customTheme.getActionBarColor().intValue(), false);
                        Theme.setColor(Theme.key_actionBarDefaultSelector, customTheme.getActionBarColor().intValue(), false);
                        Theme.setColor(Theme.key_avatar_backgroundActionBarBlue, customTheme.getActionBarColor().intValue(), false);
                        Theme.setColor(Theme.key_avatar_actionBarSelectorBlue, customTheme.getActionBarColor().intValue(), false);
                    }
                    if (customTheme.getActionColor() != null) {
                        Theme.setColor(Theme.key_chats_actionBackground, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_dialogButton, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_dialogInputFieldActivated, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_windowBackgroundWhiteInputFieldActivated, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_chat_emojiPanelIconSelected, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_chat_emojiPanelIconSelector, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_chat_messagePanelSend, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_dialogRadioBackgroundChecked, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_groupcreate_cursor, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_windowBackgroundWhiteBlueHeader, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_switchThumb, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_switchThumbChecked, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_avatar_backgroundBlue, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_avatar_backgroundInProfileBlue, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_chats_menuCloudBackgroundCats, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_chat_messagePanelVoiceBackground, customTheme.getActionColor().intValue(), false);
                        Theme.setColor(Theme.key_switchTrackChecked, manipulateColor(customTheme.getActionColor().intValue(), 1.4f), false);
                    }
                    if (customTheme.getTextColor() != null) {
                        Theme.setColor(Theme.key_chats_name, customTheme.getTextColor().intValue(), false);
                        Theme.setColor(Theme.key_windowBackgroundWhiteBlackText, customTheme.getTextColor().intValue(), false);
                        Theme.setColor(Theme.key_windowBackgroundWhiteGrayIcon, customTheme.getTextColor().intValue(), false);
                        Theme.setColor(Theme.key_chats_menuItemText, customTheme.getTextColor().intValue(), false);
                        Theme.setColor(Theme.key_chats_menuItemIcon, customTheme.getTextColor().intValue(), false);
                        Theme.setColor(Theme.key_chat_messagePanelText, customTheme.getTextColor().intValue(), false);
                        Theme.setColor(Theme.key_chat_messageTextIn, customTheme.getTextColor().intValue(), false);
                        Theme.setColor(Theme.key_chat_messageTextOut, customTheme.getTextColor().intValue(), false);
                        Theme.setColor(Theme.key_dialogBadgeText, customTheme.getTextColor().intValue(), false);
                        Theme.setColor(Theme.key_dialogTextBlack, customTheme.getTextColor().intValue(), false);
                        Theme.setColor(Theme.key_chats_nameIcon, customTheme.getTextColor().intValue(), false);
                    }
                    if (customTheme.getMessageBarColor() != null) {
                        Theme.setColor(Theme.key_chat_messagePanelBackground, customTheme.getMessageBarColor().intValue(), false);
                    }
                    if (customTheme.getReceivedColor() != null) {
                        Theme.setColor(Theme.key_chat_inBubble, customTheme.getReceivedColor().intValue(), false);
                        Theme.setColor(Theme.key_chat_outBubble, customTheme.getSentColor().intValue(), false);
                        Theme.setColor(Theme.key_chat_inBubbleSelected, manipulateColor(customTheme.getReceivedColor().intValue(), 1.3f), false);
                        Theme.setColor(Theme.key_chat_outBubbleSelected, manipulateColor(customTheme.getSentColor().intValue(), 1.3f), false);
                        Theme.setColor(Theme.key_chat_selectedBackground, adjustAlpha(customTheme.getBackgroundColor().intValue(), 0.6f), false);
                    }
                    if (customTheme.getBackgroundColor() != null) {
                        Theme.setColor(Theme.key_windowBackgroundWhite, customTheme.getBackgroundColor().intValue(), false);
                        Theme.setColor(Theme.key_windowBackgroundGray, manipulateColor(customTheme.getBackgroundColor().intValue(), 0.7f), false);
                        Theme.setColor(Theme.key_graySection, manipulateColor(customTheme.getBackgroundColor().intValue(), 0.7f), false);
                        Theme.setColor(Theme.key_divider, manipulateColor(customTheme.getBackgroundColor().intValue(), 0.7f), false);
                        Theme.setColor(Theme.key_chats_menuBackground, customTheme.getBackgroundColor().intValue(), false);
                        Theme.setColor(Theme.key_dialogBackground, customTheme.getBackgroundColor().intValue(), false);
                    }
                    if (background != null) {
                        Theme.setThemeWallpaper(name, background, null);
                    }
                    Theme.saveCurrentTheme(name, true);
                    if (customTheme.isApplyTheme()) {
                        Theme.applyTheme(Theme.getCurrentTheme());
                    }
                }
            }
        }
    }

    public static int manipulateColor(int color, float factor) {
        return Color.argb(Color.alpha(color), Math.min(Math.round(((float) Color.red(color)) * factor), 255), Math.min(Math.round(((float) Color.green(color)) * factor), 255), Math.min(Math.round(((float) Color.blue(color)) * factor), 255));
    }

    public int adjustAlpha(int color, float factor) {
        return Color.argb(Math.round(((float) Color.alpha(color)) * factor), Color.red(color), Color.green(color), Color.blue(color));
    }

    private float interpolate(float a, float b, float proportion) {
        return ((b - a) * proportion) + a;
    }

    private int interpolateColor(int a, int b, float proportion) {
        float[] hsva = new float[3];
        float[] hsvb = new float[3];
        Color.colorToHSV(a, hsva);
        Color.colorToHSV(b, hsvb);
        for (int i = 0; i < 3; i++) {
            hsvb[i] = interpolate(hsva[i], hsvb[i], proportion);
        }
        return Color.HSVToColor(hsvb);
    }
}
