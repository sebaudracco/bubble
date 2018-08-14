package org.telegram.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager.TaskDescription;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.stepleaderdigital.reveal.Reveal;
import com.wBubble_7453037.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Timer;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.ContactsController$Contact;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocaleController$LocaleInfo;
import org.telegram.messenger.LocationController.SharingLocationInfo;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationCenter.NotificationCenterDelegate;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SendMessagesHelper$SendingMediaInfo;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.camera.CameraController;
import org.telegram.messenger.config.Config;
import org.telegram.messenger.query.DraftQuery;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC$Chat;
import org.telegram.tgnet.TLRPC$ChatInvite;
import org.telegram.tgnet.TLRPC$LangPackString;
import org.telegram.tgnet.TLRPC$MessageMedia;
import org.telegram.tgnet.TLRPC$TL_contacts_resolveUsername;
import org.telegram.tgnet.TLRPC$TL_contacts_resolvedPeer;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_inputGameShortName;
import org.telegram.tgnet.TLRPC$TL_inputMediaGame;
import org.telegram.tgnet.TLRPC$TL_inputStickerSetShortName;
import org.telegram.tgnet.TLRPC$TL_langpack_getStrings;
import org.telegram.tgnet.TLRPC$TL_messages_checkChatInvite;
import org.telegram.tgnet.TLRPC$TL_messages_importChatInvite;
import org.telegram.tgnet.TLRPC$TL_webPage;
import org.telegram.tgnet.TLRPC$Updates;
import org.telegram.tgnet.TLRPC$Vector;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.ActionBarLayout;
import org.telegram.ui.ActionBar.ActionBarLayout.ActionBarLayoutDelegate;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.DrawerLayoutContainer;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.DrawerLayoutAdapter;
import org.telegram.ui.Cells.LanguageCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.EmbedBottomSheet;
import org.telegram.ui.Components.JoinGroupAlert;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PasscodeView;
import org.telegram.ui.Components.PasscodeView.PasscodeViewDelegate;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.SharingLocationsAlert.SharingLocationsAlertDelegate;
import org.telegram.ui.Components.StickersAlert;
import org.telegram.ui.Components.ThemeEditorView;
import org.telegram.ui.DialogsActivity.DialogsActivityDelegate;
import org.telegram.ui.LocationActivity.LocationActivityDelegate;

public class LaunchActivity extends Activity implements ActionBarLayoutDelegate, NotificationCenterDelegate, DialogsActivityDelegate {
    private static ArrayList<BaseFragment> layerFragmentsStack = new ArrayList();
    private static ArrayList<BaseFragment> mainFragmentsStack = new ArrayList();
    private static ArrayList<BaseFragment> rightFragmentsStack = new ArrayList();
    private ActionBarLayout actionBarLayout;
    private View backgroundTablet;
    private Config config;
    private ArrayList<User> contactsToSend;
    private int currentConnectionState;
    private String documentsMimeType;
    private ArrayList<String> documentsOriginalPathsArray;
    private ArrayList<String> documentsPathsArray;
    private ArrayList<Uri> documentsUrisArray;
    private DrawerLayoutAdapter drawerLayoutAdapter;
    protected DrawerLayoutContainer drawerLayoutContainer;
    private HashMap<String, String> englishLocaleStrings;
    private boolean finished;
    private ActionBarLayout layersActionBarLayout;
    private boolean loadingLocaleDialog;
    private AlertDialog localeDialog;
    private Runnable lockRunnable;
    private OnGlobalLayoutListener onGlobalLayoutListener;
    private Intent passcodeSaveIntent;
    private boolean passcodeSaveIntentIsNew;
    private boolean passcodeSaveIntentIsRestore;
    private PasscodeView passcodeView;
    private ArrayList<SendMessagesHelper$SendingMediaInfo> photoPathsArray;
    private ProgressDialog progressDialog;
    private ActionBarLayout rightActionBarLayout;
    private String sendingText;
    private FrameLayout shadowTablet;
    private FrameLayout shadowTabletSide;
    private RecyclerListView sideMenu;
    private HashMap<String, String> systemLocaleStrings;
    private boolean tabletFullSize;
    private Timer timer;
    private String videoPath;
    private AlertDialog visibleDialog;

    class C65062 implements OnTouchListener {
        C65062() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            if (LaunchActivity.this.actionBarLayout.fragmentsStack.isEmpty() || event.getAction() != 1) {
                return false;
            }
            float x = event.getX();
            float y = event.getY();
            int[] location = new int[2];
            LaunchActivity.this.layersActionBarLayout.getLocationOnScreen(location);
            int viewX = location[0];
            int viewY = location[1];
            if (LaunchActivity.this.layersActionBarLayout.checkTransitionAnimation() || (x > ((float) viewX) && x < ((float) (LaunchActivity.this.layersActionBarLayout.getWidth() + viewX)) && y > ((float) viewY) && y < ((float) (LaunchActivity.this.layersActionBarLayout.getHeight() + viewY)))) {
                return false;
            }
            if (!LaunchActivity.this.layersActionBarLayout.fragmentsStack.isEmpty()) {
                int a = 0;
                while (LaunchActivity.this.layersActionBarLayout.fragmentsStack.size() - 1 > 0) {
                    LaunchActivity.this.layersActionBarLayout.removeFragmentFromStack((BaseFragment) LaunchActivity.this.layersActionBarLayout.fragmentsStack.get(0));
                    a = (a - 1) + 1;
                }
                LaunchActivity.this.layersActionBarLayout.closeLastFragment(true);
            }
            return true;
        }
    }

    class C65083 implements OnClickListener {
        C65083() {
        }

        public void onClick(View v) {
        }
    }

    class C65094 implements OnItemClickListener {
        C65094() {
        }

        public void onItemClick(View view, int position) {
            int id = LaunchActivity.this.drawerLayoutAdapter.getId(position);
            Bundle args;
            if (position == 0) {
                args = new Bundle();
                args.putInt("user_id", UserConfig.getClientUserId());
                LaunchActivity.this.presentFragment(new ChatActivity(args));
                LaunchActivity.this.drawerLayoutContainer.closeDrawer(false);
            } else if (id == 2) {
                if (MessagesController.isFeatureEnabled("chat_create", (BaseFragment) LaunchActivity.this.actionBarLayout.fragmentsStack.get(LaunchActivity.this.actionBarLayout.fragmentsStack.size() - 1))) {
                    LaunchActivity.this.presentFragment(new GroupCreateActivity());
                    LaunchActivity.this.drawerLayoutContainer.closeDrawer(false);
                }
            } else if (id == 3) {
                args = new Bundle();
                args.putBoolean("onlyUsers", true);
                args.putBoolean("destroyAfterSelect", true);
                args.putBoolean("createSecretChat", true);
                args.putBoolean("allowBots", false);
                LaunchActivity.this.presentFragment(new ContactsActivity(args));
                LaunchActivity.this.drawerLayoutContainer.closeDrawer(false);
            } else if (id == 4) {
                if (MessagesController.isFeatureEnabled("broadcast_create", (BaseFragment) LaunchActivity.this.actionBarLayout.fragmentsStack.get(LaunchActivity.this.actionBarLayout.fragmentsStack.size() - 1))) {
                    SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
                    if (BuildVars.DEBUG_VERSION || !preferences.getBoolean("channel_intro", false)) {
                        LaunchActivity.this.presentFragment(new ChannelIntroActivity());
                        preferences.edit().putBoolean("channel_intro", true).commit();
                    } else {
                        args = new Bundle();
                        args.putInt("step", 0);
                        LaunchActivity.this.presentFragment(new ChannelCreateActivity(args));
                    }
                    LaunchActivity.this.drawerLayoutContainer.closeDrawer(false);
                }
            } else if (id == 6) {
                LaunchActivity.this.presentFragment(new ContactsActivity(null));
                LaunchActivity.this.drawerLayoutContainer.closeDrawer(false);
            } else if (id == 7) {
                LaunchActivity.this.presentFragment(new InviteContactsActivity());
            } else if (id == 8) {
                LaunchActivity.this.presentFragment(new SettingsActivity());
                LaunchActivity.this.drawerLayoutContainer.closeDrawer(false);
            } else if (id == 9) {
                Browser.openUrl(LaunchActivity.this, LocaleController.getString("TelegramFaqUrl", R.string.TelegramFaqUrl));
                LaunchActivity.this.drawerLayoutContainer.closeDrawer(false);
            } else if (id == 10) {
                LaunchActivity.this.presentFragment(new CallLogActivity());
                LaunchActivity.this.drawerLayoutContainer.closeDrawer(false);
            } else if (id == 11) {
                args = new Bundle();
                args.putInt("user_id", UserConfig.getClientUserId());
                LaunchActivity.this.presentFragment(new ChatActivity(args));
                LaunchActivity.this.drawerLayoutContainer.closeDrawer(false);
            } else if (id == 12) {
                LaunchActivity.this.presentFragment(new ThemeActivity());
                LaunchActivity.this.drawerLayoutContainer.closeDrawer(false);
            }
        }
    }

    class C65116 implements PasscodeViewDelegate {
        C65116() {
        }

        public void didAcceptedPassword() {
            UserConfig.isWaitingForPasscodeEnter = false;
            if (LaunchActivity.this.passcodeSaveIntent != null) {
                LaunchActivity.this.handleIntent(LaunchActivity.this.passcodeSaveIntent, LaunchActivity.this.passcodeSaveIntentIsNew, LaunchActivity.this.passcodeSaveIntentIsRestore, true);
                LaunchActivity.this.passcodeSaveIntent = null;
            }
            LaunchActivity.this.drawerLayoutContainer.setAllowOpenDrawer(true, false);
            LaunchActivity.this.actionBarLayout.showLastFragment();
            if (AndroidUtilities.isTablet()) {
                LaunchActivity.this.layersActionBarLayout.showLastFragment();
                LaunchActivity.this.rightActionBarLayout.showLastFragment();
            }
        }
    }

    class C65148 implements SharingLocationsAlertDelegate {
        C65148() {
        }

        public void didSelectLocation(SharingLocationInfo info) {
            LocationActivity locationActivity = new LocationActivity(2);
            locationActivity.setMessageObject(info.messageObject);
            final long dialog_id = info.messageObject.getDialogId();
            locationActivity.setDelegate(new LocationActivityDelegate() {
                public void didSelectLocation(TLRPC$MessageMedia location, int live) {
                    SendMessagesHelper.getInstance().sendMessage(location, dialog_id, null, null, null);
                }
            });
            LaunchActivity.this.presentFragment(locationActivity);
        }
    }

    private class VcardData {
        String name;
        ArrayList<String> phones;

        private VcardData() {
            this.phones = new ArrayList();
        }
    }

    public void showProgress() {
        if (!isFinishing() && this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(this);
            this.progressDialog.setMessage(LocaleController.getString("Loading", R.string.Loading));
            this.progressDialog.setCanceledOnTouchOutside(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.show();
        }
    }

    public void hideProgress() {
        if (this.progressDialog != null) {
            try {
                this.progressDialog.dismiss();
            } catch (Exception e) {
                FileLog.e("tmessages", e);
            }
            this.progressDialog = null;
        }
    }

    public void loadBanner(@NonNull String tags) {
        AppsgeyserSDK.getFastTrackAdsController().showFullscreen(tags, this);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onCreate(android.os.Bundle r38) {
        /*
        r37 = this;
        org.telegram.messenger.ApplicationLoader.postInitApplication();
        org.telegram.messenger.NativeCrashManager.handleDumpFiles(r37);
        r32 = r37.getResources();
        r32 = r32.getConfiguration();
        r0 = r37;
        r1 = r32;
        org.telegram.messenger.AndroidUtilities.checkDisplaySize(r0, r1);
        r32 = org.telegram.messenger.ApplicationLoader.getConfig();
        r0 = r32;
        r1 = r37;
        r1.config = r0;
        r32 = 2131560428; // 0x7f0d07ec float:1.8746228E38 double:1.0531307795E-314;
        r0 = r37;
        r1 = r32;
        r32 = r0.getString(r1);
        r33 = 2131560251; // 0x7f0d073b float:1.874587E38 double:1.053130692E-314;
        r0 = r37;
        r1 = r33;
        r33 = r0.getString(r1);
        r34 = 2131560426; // 0x7f0d07ea float:1.8746224E38 double:1.0531307785E-314;
        r0 = r37;
        r1 = r34;
        r34 = r0.getString(r1);
        r0 = r37;
        r1 = r32;
        r2 = r33;
        r3 = r34;
        com.appsgeyser.sdk.AppsgeyserSDK.takeOff(r0, r1, r2, r3);
        r32 = org.telegram.messenger.UserConfig.isClientActivated();
        if (r32 != 0) goto L_0x010a;
    L_0x0051:
        r19 = r37.getIntent();
        if (r19 == 0) goto L_0x007e;
    L_0x0057:
        r32 = r19.getAction();
        if (r32 == 0) goto L_0x007e;
    L_0x005d:
        r32 = "android.intent.action.SEND";
        r33 = r19.getAction();
        r32 = r32.equals(r33);
        if (r32 != 0) goto L_0x0077;
    L_0x006a:
        r32 = r19.getAction();
        r33 = "android.intent.action.SEND_MULTIPLE";
        r32 = r32.equals(r33);
        if (r32 == 0) goto L_0x007e;
    L_0x0077:
        super.onCreate(r38);
        r37.finish();
    L_0x007d:
        return;
    L_0x007e:
        r32 = org.telegram.messenger.ApplicationLoader.applicationContext;
        r33 = "mainconfig";
        r34 = 0;
        r25 = r32.getSharedPreferences(r33, r34);
        r32 = "intro_crashed_time";
        r34 = 0;
        r0 = r25;
        r1 = r32;
        r2 = r34;
        r10 = r0.getLong(r1, r2);
        r32 = "fromIntro";
        r33 = 0;
        r0 = r19;
        r1 = r32;
        r2 = r33;
        r17 = r0.getBooleanExtra(r1, r2);
        if (r17 == 0) goto L_0x00b9;
    L_0x00a9:
        r32 = r25.edit();
        r33 = "intro_crashed_time";
        r34 = 0;
        r32 = r32.putLong(r33, r34);
        r32.commit();
    L_0x00b9:
        r32 = java.lang.System.currentTimeMillis();
        r32 = r10 - r32;
        r32 = java.lang.Math.abs(r32);
        r34 = 120000; // 0x1d4c0 float:1.68156E-40 double:5.9288E-319;
        r32 = (r32 > r34 ? 1 : (r32 == r34 ? 0 : -1));
        if (r32 < 0) goto L_0x010a;
    L_0x00ca:
        if (r19 == 0) goto L_0x010a;
    L_0x00cc:
        if (r17 != 0) goto L_0x010a;
    L_0x00ce:
        r32 = org.telegram.messenger.ApplicationLoader.applicationContext;
        r33 = "logininfo2";
        r34 = 0;
        r25 = r32.getSharedPreferences(r33, r34);
        r30 = r25.getAll();
        r32 = r30.isEmpty();
        if (r32 == 0) goto L_0x010a;
    L_0x00e3:
        r20 = new android.content.Intent;
        r32 = org.telegram.ui.IntroActivity.class;
        r0 = r20;
        r1 = r37;
        r2 = r32;
        r0.<init>(r1, r2);
        r32 = r19.getData();
        r0 = r20;
        r1 = r32;
        r0.setData(r1);
        r0 = r37;
        r1 = r20;
        r0.startActivity(r1);
        super.onCreate(r38);
        r37.finish();
        goto L_0x007d;
    L_0x010a:
        r32 = 1;
        r0 = r37;
        r1 = r32;
        r0.requestWindowFeature(r1);
        r32 = 2131624261; // 0x7f0e0145 float:1.8875697E38 double:1.053162317E-314;
        r0 = r37;
        r1 = r32;
        r0.setTheme(r1);
        r32 = android.os.Build.VERSION.SDK_INT;
        r33 = 21;
        r0 = r32;
        r1 = r33;
        if (r0 < r1) goto L_0x0142;
    L_0x0127:
        r32 = new android.app.ActivityManager$TaskDescription;	 Catch:{ Exception -> 0x0937 }
        r33 = 0;
        r34 = 0;
        r35 = "actionBarDefault";
        r35 = org.telegram.ui.ActionBar.Theme.getColor(r35);	 Catch:{ Exception -> 0x0937 }
        r36 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r35 = r35 | r36;
        r32.<init>(r33, r34, r35);	 Catch:{ Exception -> 0x0937 }
        r0 = r37;
        r1 = r32;
        r0.setTaskDescription(r1);	 Catch:{ Exception -> 0x0937 }
    L_0x0142:
        r32 = r37.getWindow();
        r33 = 2131231346; // 0x7f080272 float:1.807877E38 double:1.0529681914E-314;
        r32.setBackgroundDrawableResource(r33);
        r32 = org.telegram.messenger.UserConfig.passcodeHash;
        r32 = r32.length();
        if (r32 <= 0) goto L_0x0163;
    L_0x0154:
        r32 = org.telegram.messenger.UserConfig.allowScreenCapture;
        if (r32 != 0) goto L_0x0163;
    L_0x0158:
        r32 = r37.getWindow();	 Catch:{ Exception -> 0x0682 }
        r33 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r34 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r32.setFlags(r33, r34);	 Catch:{ Exception -> 0x0682 }
    L_0x0163:
        super.onCreate(r38);
        r32 = android.os.Build.VERSION.SDK_INT;
        r33 = 24;
        r0 = r32;
        r1 = r33;
        if (r0 < r1) goto L_0x0176;
    L_0x0170:
        r32 = r37.isInMultiWindowMode();
        org.telegram.messenger.AndroidUtilities.isInMultiwindow = r32;
    L_0x0176:
        r32 = 0;
        r0 = r37;
        r1 = r32;
        org.telegram.ui.ActionBar.Theme.createChatResources(r0, r1);
        r32 = org.telegram.messenger.UserConfig.passcodeHash;
        r32 = r32.length();
        if (r32 == 0) goto L_0x0195;
    L_0x0187:
        r32 = org.telegram.messenger.UserConfig.appLocked;
        if (r32 == 0) goto L_0x0195;
    L_0x018b:
        r32 = org.telegram.tgnet.ConnectionsManager.getInstance();
        r32 = r32.getCurrentTime();
        org.telegram.messenger.UserConfig.lastPauseTime = r32;
    L_0x0195:
        r32 = r37.getResources();
        r33 = "status_bar_height";
        r34 = "dimen";
        r35 = "android";
        r27 = r32.getIdentifier(r33, r34, r35);
        if (r27 <= 0) goto L_0x01b6;
    L_0x01a8:
        r32 = r37.getResources();
        r0 = r32;
        r1 = r27;
        r32 = r0.getDimensionPixelSize(r1);
        org.telegram.messenger.AndroidUtilities.statusBarHeight = r32;
    L_0x01b6:
        r32 = new org.telegram.ui.ActionBar.ActionBarLayout;
        r0 = r32;
        r1 = r37;
        r0.<init>(r1);
        r0 = r32;
        r1 = r37;
        r1.actionBarLayout = r0;
        r32 = new org.telegram.ui.ActionBar.DrawerLayoutContainer;
        r0 = r32;
        r1 = r37;
        r0.<init>(r1);
        r0 = r32;
        r1 = r37;
        r1.drawerLayoutContainer = r0;
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r32 = r0;
        r33 = new android.view.ViewGroup$LayoutParams;
        r34 = -1;
        r35 = -1;
        r33.<init>(r34, r35);
        r0 = r37;
        r1 = r32;
        r2 = r33;
        r0.setContentView(r1, r2);
        r32 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r32 == 0) goto L_0x0690;
    L_0x01f2:
        r32 = r37.getWindow();
        r33 = 16;
        r32.setSoftInputMode(r33);
        r21 = new org.telegram.ui.LaunchActivity$1;
        r0 = r21;
        r1 = r37;
        r2 = r37;
        r0.<init>(r2);
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r32 = r0;
        r33 = -1;
        r34 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r33 = org.telegram.ui.Components.LayoutHelper.createFrame(r33, r34);
        r0 = r32;
        r1 = r21;
        r2 = r33;
        r0.addView(r1, r2);
        r32 = new android.view.View;
        r0 = r32;
        r1 = r37;
        r0.<init>(r1);
        r0 = r32;
        r1 = r37;
        r1.backgroundTablet = r0;
        r32 = r37.getResources();
        r33 = 2131230918; // 0x7f0800c6 float:1.8077902E38 double:1.05296798E-314;
        r13 = r32.getDrawable(r33);
        r13 = (android.graphics.drawable.BitmapDrawable) r13;
        r32 = android.graphics.Shader.TileMode.REPEAT;
        r33 = android.graphics.Shader.TileMode.REPEAT;
        r0 = r32;
        r1 = r33;
        r13.setTileModeXY(r0, r1);
        r0 = r37;
        r0 = r0.backgroundTablet;
        r32 = r0;
        r0 = r32;
        r0.setBackgroundDrawable(r13);
        r0 = r37;
        r0 = r0.backgroundTablet;
        r32 = r0;
        r33 = -1;
        r34 = -1;
        r33 = org.telegram.ui.Components.LayoutHelper.createRelative(r33, r34);
        r0 = r21;
        r1 = r32;
        r2 = r33;
        r0.addView(r1, r2);
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r0 = r21;
        r1 = r32;
        r0.addView(r1);
        r32 = new org.telegram.ui.ActionBar.ActionBarLayout;
        r0 = r32;
        r1 = r37;
        r0.<init>(r1);
        r0 = r32;
        r1 = r37;
        r1.rightActionBarLayout = r0;
        r0 = r37;
        r0 = r0.rightActionBarLayout;
        r32 = r0;
        r33 = rightFragmentsStack;
        r32.init(r33);
        r0 = r37;
        r0 = r0.rightActionBarLayout;
        r32 = r0;
        r0 = r32;
        r1 = r37;
        r0.setDelegate(r1);
        r0 = r37;
        r0 = r0.rightActionBarLayout;
        r32 = r0;
        r0 = r21;
        r1 = r32;
        r0.addView(r1);
        r32 = new android.widget.FrameLayout;
        r0 = r32;
        r1 = r37;
        r0.<init>(r1);
        r0 = r32;
        r1 = r37;
        r1.shadowTabletSide = r0;
        r0 = r37;
        r0 = r0.shadowTabletSide;
        r32 = r0;
        r33 = 1076449908; // 0x40295274 float:2.6456575 double:5.31836919E-315;
        r32.setBackgroundColor(r33);
        r0 = r37;
        r0 = r0.shadowTabletSide;
        r32 = r0;
        r0 = r21;
        r1 = r32;
        r0.addView(r1);
        r32 = new android.widget.FrameLayout;
        r0 = r32;
        r1 = r37;
        r0.<init>(r1);
        r0 = r32;
        r1 = r37;
        r1.shadowTablet = r0;
        r0 = r37;
        r0 = r0.shadowTablet;
        r33 = r0;
        r32 = layerFragmentsStack;
        r32 = r32.isEmpty();
        if (r32 == 0) goto L_0x0688;
    L_0x02ec:
        r32 = 8;
    L_0x02ee:
        r0 = r33;
        r1 = r32;
        r0.setVisibility(r1);
        r0 = r37;
        r0 = r0.shadowTablet;
        r32 = r0;
        r33 = 2130706432; // 0x7f000000 float:1.7014118E38 double:1.0527088494E-314;
        r32.setBackgroundColor(r33);
        r0 = r37;
        r0 = r0.shadowTablet;
        r32 = r0;
        r0 = r21;
        r1 = r32;
        r0.addView(r1);
        r0 = r37;
        r0 = r0.shadowTablet;
        r32 = r0;
        r33 = new org.telegram.ui.LaunchActivity$2;
        r0 = r33;
        r1 = r37;
        r0.<init>();
        r32.setOnTouchListener(r33);
        r0 = r37;
        r0 = r0.shadowTablet;
        r32 = r0;
        r33 = new org.telegram.ui.LaunchActivity$3;
        r0 = r33;
        r1 = r37;
        r0.<init>();
        r32.setOnClickListener(r33);
        r32 = new org.telegram.ui.ActionBar.ActionBarLayout;
        r0 = r32;
        r1 = r37;
        r0.<init>(r1);
        r0 = r32;
        r1 = r37;
        r1.layersActionBarLayout = r0;
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r33 = 1;
        r32.setRemoveActionBarExtraHeight(r33);
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r0 = r37;
        r0 = r0.shadowTablet;
        r33 = r0;
        r32.setBackgroundView(r33);
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r33 = 1;
        r32.setUseAlphaAnimations(r33);
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r33 = 2131230909; // 0x7f0800bd float:1.8077884E38 double:1.0529679755E-314;
        r32.setBackgroundResource(r33);
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r33 = layerFragmentsStack;
        r32.init(r33);
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r0 = r32;
        r1 = r37;
        r0.setDelegate(r1);
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r33 = r0;
        r32.setDrawerLayoutContainer(r33);
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r33 = r0;
        r32 = layerFragmentsStack;
        r32 = r32.isEmpty();
        if (r32 == 0) goto L_0x068c;
    L_0x03a6:
        r32 = 8;
    L_0x03a8:
        r0 = r33;
        r1 = r32;
        r0.setVisibility(r1);
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r0 = r21;
        r1 = r32;
        r0.addView(r1);
    L_0x03bc:
        r32 = new org.telegram.ui.Components.RecyclerListView;
        r0 = r32;
        r1 = r37;
        r0.<init>(r1);
        r0 = r32;
        r1 = r37;
        r1.sideMenu = r0;
        r0 = r37;
        r0 = r0.sideMenu;
        r32 = r0;
        r33 = "chats_menuBackground";
        r33 = org.telegram.ui.ActionBar.Theme.getColor(r33);
        r32.setBackgroundColor(r33);
        r0 = r37;
        r0 = r0.sideMenu;
        r32 = r0;
        r33 = new org.telegram.messenger.support.widget.LinearLayoutManager;
        r34 = 1;
        r35 = 0;
        r0 = r33;
        r1 = r37;
        r2 = r34;
        r3 = r35;
        r0.<init>(r1, r2, r3);
        r32.setLayoutManager(r33);
        r0 = r37;
        r0 = r0.sideMenu;
        r32 = r0;
        r33 = new org.telegram.ui.Adapters.DrawerLayoutAdapter;
        r0 = r33;
        r1 = r37;
        r0.<init>(r1);
        r0 = r33;
        r1 = r37;
        r1.drawerLayoutAdapter = r0;
        r32.setAdapter(r33);
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r32 = r0;
        r0 = r37;
        r0 = r0.sideMenu;
        r33 = r0;
        r32.setDrawerLayout(r33);
        r0 = r37;
        r0 = r0.sideMenu;
        r32 = r0;
        r22 = r32.getLayoutParams();
        r22 = (android.widget.FrameLayout.LayoutParams) r22;
        r28 = org.telegram.messenger.AndroidUtilities.getRealScreenSize();
        r32 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r32 == 0) goto L_0x06aa;
    L_0x0432:
        r32 = 1134559232; // 0x43a00000 float:320.0 double:5.605467397E-315;
        r32 = org.telegram.messenger.AndroidUtilities.dp(r32);
    L_0x0438:
        r0 = r32;
        r1 = r22;
        r1.width = r0;
        r32 = -1;
        r0 = r32;
        r1 = r22;
        r1.height = r0;
        r0 = r37;
        r0 = r0.sideMenu;
        r32 = r0;
        r0 = r32;
        r1 = r22;
        r0.setLayoutParams(r1);
        r0 = r37;
        r0 = r0.sideMenu;
        r32 = r0;
        r33 = new org.telegram.ui.LaunchActivity$4;
        r0 = r33;
        r1 = r37;
        r0.<init>();
        r32.setOnItemClickListener(r33);
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r32 = r0;
        r0 = r37;
        r0 = r0.actionBarLayout;
        r33 = r0;
        r32.setParentActionBarLayout(r33);
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r33 = r0;
        r32.setDrawerLayoutContainer(r33);
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r33 = mainFragmentsStack;
        r32.init(r33);
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r0 = r32;
        r1 = r37;
        r0.setDelegate(r1);
        org.telegram.ui.ActionBar.Theme.loadWallpaper();
        r32 = new org.telegram.ui.Components.PasscodeView;
        r0 = r32;
        r1 = r37;
        r0.<init>(r1);
        r0 = r32;
        r1 = r37;
        r1.passcodeView = r0;
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r32 = r0;
        r0 = r37;
        r0 = r0.passcodeView;
        r33 = r0;
        r34 = -1;
        r35 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r34 = org.telegram.ui.Components.LayoutHelper.createFrame(r34, r35);
        r32.addView(r33, r34);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.closeOtherAppActivities;
        r34 = 1;
        r0 = r34;
        r0 = new java.lang.Object[r0];
        r34 = r0;
        r35 = 0;
        r34[r35] = r37;
        r32.postNotificationName(r33, r34);
        r32 = org.telegram.tgnet.ConnectionsManager.getInstance();
        r32 = r32.getConnectionState();
        r0 = r32;
        r1 = r37;
        r1.currentConnectionState = r0;
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.appDidLogout;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.mainUserInfoChanged;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.closeOtherAppActivities;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.didUpdatedConnectionState;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.needShowAlert;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.wasUnableToFindCurrentLocation;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.didSetNewWallpapper;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.didSetPasscode;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.reloadInterface;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.suggestedLangpack;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.openArticle;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.hasNewContactsToImport;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r32 = org.telegram.messenger.NotificationCenter.getInstance();
        r33 = org.telegram.messenger.NotificationCenter.didSetNewTheme;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.addObserver(r1, r2);
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r0 = r32;
        r0 = r0.fragmentsStack;
        r32 = r0;
        r32 = r32.isEmpty();
        if (r32 == 0) goto L_0x0851;
    L_0x05bc:
        r32 = org.telegram.messenger.UserConfig.isClientActivated();
        if (r32 != 0) goto L_0x06ce;
    L_0x05c2:
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r33 = new org.telegram.ui.LoginActivity;
        r33.<init>();
        r32.addFragmentToStack(r33);
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r32 = r0;
        r33 = 0;
        r34 = 0;
        r32.setAllowOpenDrawer(r33, r34);
    L_0x05dd:
        if (r38 == 0) goto L_0x0603;
    L_0x05df:
        r32 = "fragment";
        r0 = r38;
        r1 = r32;
        r16 = r0.getString(r1);	 Catch:{ Exception -> 0x0799 }
        if (r16 == 0) goto L_0x0603;
    L_0x05ec:
        r32 = "args";
        r0 = r38;
        r1 = r32;
        r7 = r0.getBundle(r1);	 Catch:{ Exception -> 0x0799 }
        r32 = -1;
        r33 = r16.hashCode();	 Catch:{ Exception -> 0x0799 }
        switch(r33) {
            case -1529105743: goto L_0x076c;
            case -1349522494: goto L_0x075b;
            case 3052376: goto L_0x0706;
            case 3108362: goto L_0x074a;
            case 98629247: goto L_0x0728;
            case 738950403: goto L_0x0739;
            case 1434631203: goto L_0x0717;
            default: goto L_0x0600;
        };
    L_0x0600:
        switch(r32) {
            case 0: goto L_0x077d;
            case 1: goto L_0x079f;
            case 2: goto L_0x07ba;
            case 3: goto L_0x07dc;
            case 4: goto L_0x07f8;
            case 5: goto L_0x0814;
            case 6: goto L_0x0836;
            default: goto L_0x0603;
        };
    L_0x0603:
        r37.checkLayout();
        r33 = r37.getIntent();
        r34 = 0;
        if (r38 == 0) goto L_0x0923;
    L_0x060e:
        r32 = 1;
    L_0x0610:
        r35 = 0;
        r0 = r37;
        r1 = r33;
        r2 = r34;
        r3 = r32;
        r4 = r35;
        r0.handleIntent(r1, r2, r3, r4);
        r23 = android.os.Build.DISPLAY;	 Catch:{ Exception -> 0x0931 }
        r24 = android.os.Build.USER;	 Catch:{ Exception -> 0x0931 }
        if (r23 == 0) goto L_0x0927;
    L_0x0625:
        r23 = r23.toLowerCase();	 Catch:{ Exception -> 0x0931 }
    L_0x0629:
        if (r24 == 0) goto L_0x092c;
    L_0x062b:
        r24 = r23.toLowerCase();	 Catch:{ Exception -> 0x0931 }
    L_0x062f:
        r32 = "flyme";
        r0 = r23;
        r1 = r32;
        r32 = r0.contains(r1);	 Catch:{ Exception -> 0x0931 }
        if (r32 != 0) goto L_0x0649;
    L_0x063c:
        r32 = "flyme";
        r0 = r24;
        r1 = r32;
        r32 = r0.contains(r1);	 Catch:{ Exception -> 0x0931 }
        if (r32 == 0) goto L_0x0671;
    L_0x0649:
        r32 = 1;
        org.telegram.messenger.AndroidUtilities.incorrectDisplaySizeFix = r32;	 Catch:{ Exception -> 0x0931 }
        r32 = r37.getWindow();	 Catch:{ Exception -> 0x0931 }
        r32 = r32.getDecorView();	 Catch:{ Exception -> 0x0931 }
        r31 = r32.getRootView();	 Catch:{ Exception -> 0x0931 }
        r32 = r31.getViewTreeObserver();	 Catch:{ Exception -> 0x0931 }
        r33 = new org.telegram.ui.LaunchActivity$5;	 Catch:{ Exception -> 0x0931 }
        r0 = r33;
        r1 = r37;
        r2 = r31;
        r0.<init>(r2);	 Catch:{ Exception -> 0x0931 }
        r0 = r33;
        r1 = r37;
        r1.onGlobalLayoutListener = r0;	 Catch:{ Exception -> 0x0931 }
        r32.addOnGlobalLayoutListener(r33);	 Catch:{ Exception -> 0x0931 }
    L_0x0671:
        r32 = org.telegram.messenger.MediaController.getInstance();
        r33 = 1;
        r0 = r32;
        r1 = r37;
        r2 = r33;
        r0.setBaseActivity(r1, r2);
        goto L_0x007d;
    L_0x0682:
        r14 = move-exception;
        org.telegram.messenger.FileLog.e(r14);
        goto L_0x0163;
    L_0x0688:
        r32 = 0;
        goto L_0x02ee;
    L_0x068c:
        r32 = 0;
        goto L_0x03a8;
    L_0x0690:
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r32 = r0;
        r0 = r37;
        r0 = r0.actionBarLayout;
        r33 = r0;
        r34 = new android.view.ViewGroup$LayoutParams;
        r35 = -1;
        r36 = -1;
        r34.<init>(r35, r36);
        r32.addView(r33, r34);
        goto L_0x03bc;
    L_0x06aa:
        r32 = 1134559232; // 0x43a00000 float:320.0 double:5.605467397E-315;
        r32 = org.telegram.messenger.AndroidUtilities.dp(r32);
        r0 = r28;
        r0 = r0.x;
        r33 = r0;
        r0 = r28;
        r0 = r0.y;
        r34 = r0;
        r33 = java.lang.Math.min(r33, r34);
        r34 = 1113587712; // 0x42600000 float:56.0 double:5.50185432E-315;
        r34 = org.telegram.messenger.AndroidUtilities.dp(r34);
        r33 = r33 - r34;
        r32 = java.lang.Math.min(r32, r33);
        goto L_0x0438;
    L_0x06ce:
        r12 = new org.telegram.ui.DialogsActivity;
        r32 = 0;
        r0 = r32;
        r12.<init>(r0);
        r0 = r37;
        r0 = r0.sideMenu;
        r32 = r0;
        r0 = r32;
        r12.setSideMenu(r0);
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r0 = r32;
        r0.addFragmentToStack(r12);
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r32 = r0;
        r33 = 1;
        r34 = 0;
        r32.setAllowOpenDrawer(r33, r34);
        r32 = "on_start";
        r0 = r37;
        r1 = r32;
        r0.loadBanner(r1);
        goto L_0x05dd;
    L_0x0706:
        r33 = "chat";
        r0 = r16;
        r1 = r33;
        r33 = r0.equals(r1);	 Catch:{ Exception -> 0x0799 }
        if (r33 == 0) goto L_0x0600;
    L_0x0713:
        r32 = 0;
        goto L_0x0600;
    L_0x0717:
        r33 = "settings";
        r0 = r16;
        r1 = r33;
        r33 = r0.equals(r1);	 Catch:{ Exception -> 0x0799 }
        if (r33 == 0) goto L_0x0600;
    L_0x0724:
        r32 = 1;
        goto L_0x0600;
    L_0x0728:
        r33 = "group";
        r0 = r16;
        r1 = r33;
        r33 = r0.equals(r1);	 Catch:{ Exception -> 0x0799 }
        if (r33 == 0) goto L_0x0600;
    L_0x0735:
        r32 = 2;
        goto L_0x0600;
    L_0x0739:
        r33 = "channel";
        r0 = r16;
        r1 = r33;
        r33 = r0.equals(r1);	 Catch:{ Exception -> 0x0799 }
        if (r33 == 0) goto L_0x0600;
    L_0x0746:
        r32 = 3;
        goto L_0x0600;
    L_0x074a:
        r33 = "edit";
        r0 = r16;
        r1 = r33;
        r33 = r0.equals(r1);	 Catch:{ Exception -> 0x0799 }
        if (r33 == 0) goto L_0x0600;
    L_0x0757:
        r32 = 4;
        goto L_0x0600;
    L_0x075b:
        r33 = "chat_profile";
        r0 = r16;
        r1 = r33;
        r33 = r0.equals(r1);	 Catch:{ Exception -> 0x0799 }
        if (r33 == 0) goto L_0x0600;
    L_0x0768:
        r32 = 5;
        goto L_0x0600;
    L_0x076c:
        r33 = "wallpapers";
        r0 = r16;
        r1 = r33;
        r33 = r0.equals(r1);	 Catch:{ Exception -> 0x0799 }
        if (r33 == 0) goto L_0x0600;
    L_0x0779:
        r32 = 6;
        goto L_0x0600;
    L_0x077d:
        if (r7 == 0) goto L_0x0603;
    L_0x077f:
        r9 = new org.telegram.ui.ChatActivity;	 Catch:{ Exception -> 0x0799 }
        r9.<init>(r7);	 Catch:{ Exception -> 0x0799 }
        r0 = r37;
        r0 = r0.actionBarLayout;	 Catch:{ Exception -> 0x0799 }
        r32 = r0;
        r0 = r32;
        r32 = r0.addFragmentToStack(r9);	 Catch:{ Exception -> 0x0799 }
        if (r32 == 0) goto L_0x0603;
    L_0x0792:
        r0 = r38;
        r9.restoreSelfArgs(r0);	 Catch:{ Exception -> 0x0799 }
        goto L_0x0603;
    L_0x0799:
        r14 = move-exception;
        org.telegram.messenger.FileLog.e(r14);
        goto L_0x0603;
    L_0x079f:
        r29 = new org.telegram.ui.SettingsActivity;	 Catch:{ Exception -> 0x0799 }
        r29.<init>();	 Catch:{ Exception -> 0x0799 }
        r0 = r37;
        r0 = r0.actionBarLayout;	 Catch:{ Exception -> 0x0799 }
        r32 = r0;
        r0 = r32;
        r1 = r29;
        r0.addFragmentToStack(r1);	 Catch:{ Exception -> 0x0799 }
        r0 = r29;
        r1 = r38;
        r0.restoreSelfArgs(r1);	 Catch:{ Exception -> 0x0799 }
        goto L_0x0603;
    L_0x07ba:
        if (r7 == 0) goto L_0x0603;
    L_0x07bc:
        r18 = new org.telegram.ui.GroupCreateFinalActivity;	 Catch:{ Exception -> 0x0799 }
        r0 = r18;
        r0.<init>(r7);	 Catch:{ Exception -> 0x0799 }
        r0 = r37;
        r0 = r0.actionBarLayout;	 Catch:{ Exception -> 0x0799 }
        r32 = r0;
        r0 = r32;
        r1 = r18;
        r32 = r0.addFragmentToStack(r1);	 Catch:{ Exception -> 0x0799 }
        if (r32 == 0) goto L_0x0603;
    L_0x07d3:
        r0 = r18;
        r1 = r38;
        r0.restoreSelfArgs(r1);	 Catch:{ Exception -> 0x0799 }
        goto L_0x0603;
    L_0x07dc:
        if (r7 == 0) goto L_0x0603;
    L_0x07de:
        r8 = new org.telegram.ui.ChannelCreateActivity;	 Catch:{ Exception -> 0x0799 }
        r8.<init>(r7);	 Catch:{ Exception -> 0x0799 }
        r0 = r37;
        r0 = r0.actionBarLayout;	 Catch:{ Exception -> 0x0799 }
        r32 = r0;
        r0 = r32;
        r32 = r0.addFragmentToStack(r8);	 Catch:{ Exception -> 0x0799 }
        if (r32 == 0) goto L_0x0603;
    L_0x07f1:
        r0 = r38;
        r8.restoreSelfArgs(r0);	 Catch:{ Exception -> 0x0799 }
        goto L_0x0603;
    L_0x07f8:
        if (r7 == 0) goto L_0x0603;
    L_0x07fa:
        r8 = new org.telegram.ui.ChannelEditActivity;	 Catch:{ Exception -> 0x0799 }
        r8.<init>(r7);	 Catch:{ Exception -> 0x0799 }
        r0 = r37;
        r0 = r0.actionBarLayout;	 Catch:{ Exception -> 0x0799 }
        r32 = r0;
        r0 = r32;
        r32 = r0.addFragmentToStack(r8);	 Catch:{ Exception -> 0x0799 }
        if (r32 == 0) goto L_0x0603;
    L_0x080d:
        r0 = r38;
        r8.restoreSelfArgs(r0);	 Catch:{ Exception -> 0x0799 }
        goto L_0x0603;
    L_0x0814:
        if (r7 == 0) goto L_0x0603;
    L_0x0816:
        r26 = new org.telegram.ui.ProfileActivity;	 Catch:{ Exception -> 0x0799 }
        r0 = r26;
        r0.<init>(r7);	 Catch:{ Exception -> 0x0799 }
        r0 = r37;
        r0 = r0.actionBarLayout;	 Catch:{ Exception -> 0x0799 }
        r32 = r0;
        r0 = r32;
        r1 = r26;
        r32 = r0.addFragmentToStack(r1);	 Catch:{ Exception -> 0x0799 }
        if (r32 == 0) goto L_0x0603;
    L_0x082d:
        r0 = r26;
        r1 = r38;
        r0.restoreSelfArgs(r1);	 Catch:{ Exception -> 0x0799 }
        goto L_0x0603;
    L_0x0836:
        r29 = new org.telegram.ui.WallpapersActivity;	 Catch:{ Exception -> 0x0799 }
        r29.<init>();	 Catch:{ Exception -> 0x0799 }
        r0 = r37;
        r0 = r0.actionBarLayout;	 Catch:{ Exception -> 0x0799 }
        r32 = r0;
        r0 = r32;
        r1 = r29;
        r0.addFragmentToStack(r1);	 Catch:{ Exception -> 0x0799 }
        r0 = r29;
        r1 = r38;
        r0.restoreSelfArgs(r1);	 Catch:{ Exception -> 0x0799 }
        goto L_0x0603;
    L_0x0851:
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r0 = r32;
        r0 = r0.fragmentsStack;
        r32 = r0;
        r33 = 0;
        r15 = r32.get(r33);
        r15 = (org.telegram.ui.ActionBar.BaseFragment) r15;
        r0 = r15 instanceof org.telegram.ui.DialogsActivity;
        r32 = r0;
        if (r32 == 0) goto L_0x0878;
    L_0x086b:
        r15 = (org.telegram.ui.DialogsActivity) r15;
        r0 = r37;
        r0 = r0.sideMenu;
        r32 = r0;
        r0 = r32;
        r15.setSideMenu(r0);
    L_0x0878:
        r6 = 1;
        r32 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r32 == 0) goto L_0x08dd;
    L_0x087f:
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r0 = r32;
        r0 = r0.fragmentsStack;
        r32 = r0;
        r32 = r32.size();
        r33 = 1;
        r0 = r32;
        r1 = r33;
        if (r0 > r1) goto L_0x0921;
    L_0x0897:
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r0 = r32;
        r0 = r0.fragmentsStack;
        r32 = r0;
        r32 = r32.isEmpty();
        if (r32 == 0) goto L_0x0921;
    L_0x08a9:
        r6 = 1;
    L_0x08aa:
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r0 = r32;
        r0 = r0.fragmentsStack;
        r32 = r0;
        r32 = r32.size();
        r33 = 1;
        r0 = r32;
        r1 = r33;
        if (r0 != r1) goto L_0x08dd;
    L_0x08c2:
        r0 = r37;
        r0 = r0.layersActionBarLayout;
        r32 = r0;
        r0 = r32;
        r0 = r0.fragmentsStack;
        r32 = r0;
        r33 = 0;
        r32 = r32.get(r33);
        r0 = r32;
        r0 = r0 instanceof org.telegram.ui.LoginActivity;
        r32 = r0;
        if (r32 == 0) goto L_0x08dd;
    L_0x08dc:
        r6 = 0;
    L_0x08dd:
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r0 = r32;
        r0 = r0.fragmentsStack;
        r32 = r0;
        r32 = r32.size();
        r33 = 1;
        r0 = r32;
        r1 = r33;
        if (r0 != r1) goto L_0x0910;
    L_0x08f5:
        r0 = r37;
        r0 = r0.actionBarLayout;
        r32 = r0;
        r0 = r32;
        r0 = r0.fragmentsStack;
        r32 = r0;
        r33 = 0;
        r32 = r32.get(r33);
        r0 = r32;
        r0 = r0 instanceof org.telegram.ui.LoginActivity;
        r32 = r0;
        if (r32 == 0) goto L_0x0910;
    L_0x090f:
        r6 = 0;
    L_0x0910:
        r0 = r37;
        r0 = r0.drawerLayoutContainer;
        r32 = r0;
        r33 = 0;
        r0 = r32;
        r1 = r33;
        r0.setAllowOpenDrawer(r6, r1);
        goto L_0x0603;
    L_0x0921:
        r6 = 0;
        goto L_0x08aa;
    L_0x0923:
        r32 = 0;
        goto L_0x0610;
    L_0x0927:
        r23 = "";
        goto L_0x0629;
    L_0x092c:
        r24 = "";
        goto L_0x062f;
    L_0x0931:
        r14 = move-exception;
        org.telegram.messenger.FileLog.e(r14);
        goto L_0x0671;
    L_0x0937:
        r32 = move-exception;
        goto L_0x0142;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.ui.LaunchActivity.onCreate(android.os.Bundle):void");
    }

    private void checkLayout() {
        int i = 0;
        int i2 = 8;
        if (AndroidUtilities.isTablet() && this.rightActionBarLayout != null) {
            int a;
            BaseFragment chatFragment;
            if (AndroidUtilities.isInMultiwindow || (AndroidUtilities.isSmallTablet() && getResources().getConfiguration().orientation != 2)) {
                this.tabletFullSize = true;
                if (!this.rightActionBarLayout.fragmentsStack.isEmpty()) {
                    a = 0;
                    while (this.rightActionBarLayout.fragmentsStack.size() > 0) {
                        chatFragment = (BaseFragment) this.rightActionBarLayout.fragmentsStack.get(a);
                        if (chatFragment instanceof ChatActivity) {
                            ((ChatActivity) chatFragment).setIgnoreAttachOnPause(true);
                        }
                        chatFragment.onPause();
                        this.rightActionBarLayout.fragmentsStack.remove(a);
                        this.actionBarLayout.fragmentsStack.add(chatFragment);
                        a = (a - 1) + 1;
                    }
                    if (this.passcodeView.getVisibility() != 0) {
                        this.actionBarLayout.showLastFragment();
                    }
                }
                this.shadowTabletSide.setVisibility(8);
                this.rightActionBarLayout.setVisibility(8);
                View view = this.backgroundTablet;
                if (this.actionBarLayout.fragmentsStack.isEmpty()) {
                    i2 = 0;
                }
                view.setVisibility(i2);
                return;
            }
            int i3;
            this.tabletFullSize = false;
            if (this.actionBarLayout.fragmentsStack.size() >= 2) {
                for (a = 1; a < this.actionBarLayout.fragmentsStack.size(); a = (a - 1) + 1) {
                    chatFragment = (BaseFragment) this.actionBarLayout.fragmentsStack.get(a);
                    if (chatFragment instanceof ChatActivity) {
                        ((ChatActivity) chatFragment).setIgnoreAttachOnPause(true);
                    }
                    chatFragment.onPause();
                    this.actionBarLayout.fragmentsStack.remove(a);
                    this.rightActionBarLayout.fragmentsStack.add(chatFragment);
                }
                if (this.passcodeView.getVisibility() != 0) {
                    this.actionBarLayout.showLastFragment();
                    this.rightActionBarLayout.showLastFragment();
                }
            }
            ActionBarLayout actionBarLayout = this.rightActionBarLayout;
            if (this.rightActionBarLayout.fragmentsStack.isEmpty()) {
                i3 = 8;
            } else {
                i3 = 0;
            }
            actionBarLayout.setVisibility(i3);
            View view2 = this.backgroundTablet;
            if (this.rightActionBarLayout.fragmentsStack.isEmpty()) {
                i3 = 0;
            } else {
                i3 = 8;
            }
            view2.setVisibility(i3);
            FrameLayout frameLayout = this.shadowTabletSide;
            if (this.actionBarLayout.fragmentsStack.isEmpty()) {
                i = 8;
            }
            frameLayout.setVisibility(i);
        }
    }

    private void showPasscodeActivity() {
        if (this.passcodeView != null) {
            UserConfig.appLocked = true;
            if (SecretMediaViewer.getInstance().isVisible()) {
                SecretMediaViewer.getInstance().closePhoto(false, false);
            } else if (PhotoViewer.getInstance().isVisible()) {
                PhotoViewer.getInstance().closePhoto(false, true);
            } else if (ArticleViewer.getInstance().isVisible()) {
                ArticleViewer.getInstance().close(false, true);
            }
            this.passcodeView.onShow();
            UserConfig.isWaitingForPasscodeEnter = true;
            this.drawerLayoutContainer.setAllowOpenDrawer(false, false);
            this.passcodeView.setDelegate(new C65116());
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean handleIntent(android.content.Intent r83, boolean r84, boolean r85, boolean r86) {
        /*
        r82 = this;
        r4 = org.telegram.messenger.AndroidUtilities.handleProxyIntent(r82, r83);
        if (r4 == 0) goto L_0x0009;
    L_0x0006:
        r58 = 1;
    L_0x0008:
        return r58;
    L_0x0009:
        r41 = r83.getFlags();
        if (r86 != 0) goto L_0x0036;
    L_0x000f:
        r4 = 1;
        r4 = org.telegram.messenger.AndroidUtilities.needShowPasscode(r4);
        if (r4 != 0) goto L_0x001a;
    L_0x0016:
        r4 = org.telegram.messenger.UserConfig.isWaitingForPasscodeEnter;
        if (r4 == 0) goto L_0x0036;
    L_0x001a:
        r82.showPasscodeActivity();
        r0 = r83;
        r1 = r82;
        r1.passcodeSaveIntent = r0;
        r0 = r84;
        r1 = r82;
        r1.passcodeSaveIntentIsNew = r0;
        r0 = r85;
        r1 = r82;
        r1.passcodeSaveIntentIsRestore = r0;
        r4 = 0;
        org.telegram.messenger.UserConfig.saveConfig(r4);
        r58 = 0;
        goto L_0x0008;
    L_0x0036:
        r58 = 0;
        r4 = 0;
        r62 = java.lang.Integer.valueOf(r4);
        r4 = 0;
        r59 = java.lang.Integer.valueOf(r4);
        r4 = 0;
        r60 = java.lang.Integer.valueOf(r4);
        r4 = 0;
        r61 = java.lang.Integer.valueOf(r4);
        r4 = 0;
        r50 = java.lang.Integer.valueOf(r4);
        r4 = 0;
        r49 = java.lang.Integer.valueOf(r4);
        if (r83 == 0) goto L_0x01bb;
    L_0x0058:
        r4 = r83.getExtras();
        if (r4 == 0) goto L_0x01bb;
    L_0x005e:
        r4 = r83.getExtras();
        r15 = "dialogId";
        r16 = 0;
        r0 = r16;
        r34 = r4.getLong(r15, r0);
    L_0x006d:
        r66 = 0;
        r68 = 0;
        r67 = 0;
        r4 = 0;
        r0 = r82;
        r0.photoPathsArray = r4;
        r4 = 0;
        r0 = r82;
        r0.videoPath = r4;
        r4 = 0;
        r0 = r82;
        r0.sendingText = r4;
        r4 = 0;
        r0 = r82;
        r0.documentsPathsArray = r4;
        r4 = 0;
        r0 = r82;
        r0.documentsOriginalPathsArray = r4;
        r4 = 0;
        r0 = r82;
        r0.documentsMimeType = r4;
        r4 = 0;
        r0 = r82;
        r0.documentsUrisArray = r4;
        r4 = 0;
        r0 = r82;
        r0.contactsToSend = r4;
        r4 = org.telegram.messenger.UserConfig.isClientActivated();
        if (r4 == 0) goto L_0x00bc;
    L_0x00a1:
        r4 = r83.getAction();
        if (r4 == 0) goto L_0x00b4;
    L_0x00a7:
        r4 = r83.getAction();
        r15 = "com.tmessages.openchat";
        r4 = r4.startsWith(r15);
        if (r4 != 0) goto L_0x00bc;
    L_0x00b4:
        r4 = "on_start";
        r0 = r82;
        r0.loadBanner(r4);
    L_0x00bc:
        r4 = org.telegram.messenger.UserConfig.isClientActivated();
        if (r4 == 0) goto L_0x027f;
    L_0x00c2:
        r4 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        r4 = r4 & r41;
        if (r4 != 0) goto L_0x027f;
    L_0x00c8:
        if (r83 == 0) goto L_0x027f;
    L_0x00ca:
        r4 = r83.getAction();
        if (r4 == 0) goto L_0x027f;
    L_0x00d0:
        if (r85 != 0) goto L_0x027f;
    L_0x00d2:
        r4 = "android.intent.action.SEND";
        r15 = r83.getAction();
        r4 = r4.equals(r15);
        if (r4 == 0) goto L_0x057d;
    L_0x00df:
        r40 = 0;
        r73 = r83.getType();
        if (r73 == 0) goto L_0x040b;
    L_0x00e7:
        r4 = "text/x-vcard";
        r0 = r73;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x040b;
    L_0x00f2:
        r4 = r83.getExtras();	 Catch:{ Exception -> 0x026a }
        r15 = "android.intent.extra.STREAM";
        r74 = r4.get(r15);	 Catch:{ Exception -> 0x026a }
        r74 = (android.net.Uri) r74;	 Catch:{ Exception -> 0x026a }
        if (r74 == 0) goto L_0x0407;
    L_0x0101:
        r29 = r82.getContentResolver();	 Catch:{ Exception -> 0x026a }
        r0 = r29;
        r1 = r74;
        r69 = r0.openInputStream(r1);	 Catch:{ Exception -> 0x026a }
        r81 = new java.util.ArrayList;	 Catch:{ Exception -> 0x026a }
        r81.<init>();	 Catch:{ Exception -> 0x026a }
        r30 = 0;
        r25 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x026a }
        r4 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x026a }
        r15 = "UTF-8";
        r0 = r69;
        r4.<init>(r0, r15);	 Catch:{ Exception -> 0x026a }
        r0 = r25;
        r0.<init>(r4);	 Catch:{ Exception -> 0x026a }
    L_0x0125:
        r45 = r25.readLine();	 Catch:{ Exception -> 0x026a }
        if (r45 == 0) goto L_0x0381;
    L_0x012b:
        org.telegram.messenger.FileLog.e(r45);	 Catch:{ Exception -> 0x026a }
        r4 = ":";
        r0 = r45;
        r22 = r0.split(r4);	 Catch:{ Exception -> 0x026a }
        r0 = r22;
        r4 = r0.length;	 Catch:{ Exception -> 0x026a }
        r15 = 2;
        if (r4 != r15) goto L_0x0125;
    L_0x013d:
        r4 = 0;
        r4 = r22[r4];	 Catch:{ Exception -> 0x026a }
        r15 = "BEGIN";
        r4 = r4.equals(r15);	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x01bf;
    L_0x0149:
        r4 = 1;
        r4 = r22[r4];	 Catch:{ Exception -> 0x026a }
        r15 = "VCARD";
        r4 = r4.equals(r15);	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x01bf;
    L_0x0155:
        r30 = new org.telegram.ui.LaunchActivity$VcardData;	 Catch:{ Exception -> 0x026a }
        r4 = 0;
        r0 = r30;
        r1 = r82;
        r0.<init>();	 Catch:{ Exception -> 0x026a }
        r0 = r81;
        r1 = r30;
        r0.add(r1);	 Catch:{ Exception -> 0x026a }
    L_0x0166:
        if (r30 == 0) goto L_0x0125;
    L_0x0168:
        r4 = 0;
        r4 = r22[r4];	 Catch:{ Exception -> 0x026a }
        r15 = "FN";
        r4 = r4.startsWith(r15);	 Catch:{ Exception -> 0x026a }
        if (r4 != 0) goto L_0x018a;
    L_0x0174:
        r4 = 0;
        r4 = r22[r4];	 Catch:{ Exception -> 0x026a }
        r15 = "ORG";
        r4 = r4.startsWith(r15);	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x035c;
    L_0x0180:
        r0 = r30;
        r4 = r0.name;	 Catch:{ Exception -> 0x026a }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x035c;
    L_0x018a:
        r48 = 0;
        r47 = 0;
        r4 = 0;
        r4 = r22[r4];	 Catch:{ Exception -> 0x026a }
        r15 = ";";
        r53 = r4.split(r15);	 Catch:{ Exception -> 0x026a }
        r0 = r53;
        r15 = r0.length;	 Catch:{ Exception -> 0x026a }
        r4 = 0;
    L_0x019c:
        if (r4 >= r15) goto L_0x01fe;
    L_0x019e:
        r52 = r53[r4];	 Catch:{ Exception -> 0x026a }
        r16 = "=";
        r0 = r52;
        r1 = r16;
        r23 = r0.split(r1);	 Catch:{ Exception -> 0x026a }
        r0 = r23;
        r0 = r0.length;	 Catch:{ Exception -> 0x026a }
        r16 = r0;
        r17 = 2;
        r0 = r16;
        r1 = r17;
        if (r0 == r1) goto L_0x01da;
    L_0x01b8:
        r4 = r4 + 1;
        goto L_0x019c;
    L_0x01bb:
        r34 = 0;
        goto L_0x006d;
    L_0x01bf:
        r4 = 0;
        r4 = r22[r4];	 Catch:{ Exception -> 0x026a }
        r15 = "END";
        r4 = r4.equals(r15);	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x0166;
    L_0x01cb:
        r4 = 1;
        r4 = r22[r4];	 Catch:{ Exception -> 0x026a }
        r15 = "VCARD";
        r4 = r4.equals(r15);	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x0166;
    L_0x01d7:
        r30 = 0;
        goto L_0x0166;
    L_0x01da:
        r16 = 0;
        r16 = r23[r16];	 Catch:{ Exception -> 0x026a }
        r17 = "CHARSET";
        r16 = r16.equals(r17);	 Catch:{ Exception -> 0x026a }
        if (r16 == 0) goto L_0x01ec;
    L_0x01e7:
        r16 = 1;
        r47 = r23[r16];	 Catch:{ Exception -> 0x026a }
        goto L_0x01b8;
    L_0x01ec:
        r16 = 0;
        r16 = r23[r16];	 Catch:{ Exception -> 0x026a }
        r17 = "ENCODING";
        r16 = r16.equals(r17);	 Catch:{ Exception -> 0x026a }
        if (r16 == 0) goto L_0x01b8;
    L_0x01f9:
        r16 = 1;
        r48 = r23[r16];	 Catch:{ Exception -> 0x026a }
        goto L_0x01b8;
    L_0x01fe:
        r4 = 1;
        r4 = r22[r4];	 Catch:{ Exception -> 0x026a }
        r0 = r30;
        r0.name = r4;	 Catch:{ Exception -> 0x026a }
        if (r48 == 0) goto L_0x0125;
    L_0x0207:
        r4 = "QUOTED-PRINTABLE";
        r0 = r48;
        r4 = r0.equalsIgnoreCase(r4);	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x0125;
    L_0x0212:
        r0 = r30;
        r4 = r0.name;	 Catch:{ Exception -> 0x026a }
        r15 = "=";
        r4 = r4.endsWith(r15);	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x0242;
    L_0x021f:
        if (r48 == 0) goto L_0x0242;
    L_0x0221:
        r0 = r30;
        r4 = r0.name;	 Catch:{ Exception -> 0x026a }
        r15 = 0;
        r0 = r30;
        r0 = r0.name;	 Catch:{ Exception -> 0x026a }
        r16 = r0;
        r16 = r16.length();	 Catch:{ Exception -> 0x026a }
        r16 = r16 + -1;
        r0 = r16;
        r4 = r4.substring(r15, r0);	 Catch:{ Exception -> 0x026a }
        r0 = r30;
        r0.name = r4;	 Catch:{ Exception -> 0x026a }
        r45 = r25.readLine();	 Catch:{ Exception -> 0x026a }
        if (r45 != 0) goto L_0x033f;
    L_0x0242:
        r0 = r30;
        r4 = r0.name;	 Catch:{ Exception -> 0x026a }
        r4 = r4.getBytes();	 Catch:{ Exception -> 0x026a }
        r26 = org.telegram.messenger.AndroidUtilities.decodeQuotedPrintable(r4);	 Catch:{ Exception -> 0x026a }
        if (r26 == 0) goto L_0x0125;
    L_0x0250:
        r0 = r26;
        r4 = r0.length;	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x0125;
    L_0x0255:
        r33 = new java.lang.String;	 Catch:{ Exception -> 0x026a }
        r0 = r33;
        r1 = r26;
        r2 = r47;
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x026a }
        if (r33 == 0) goto L_0x0125;
    L_0x0262:
        r0 = r33;
        r1 = r30;
        r1.name = r0;	 Catch:{ Exception -> 0x026a }
        goto L_0x0125;
    L_0x026a:
        r38 = move-exception;
        org.telegram.messenger.FileLog.e(r38);
        r40 = 1;
    L_0x0270:
        if (r40 == 0) goto L_0x027f;
    L_0x0272:
        r4 = "Unsupported content";
        r15 = 0;
        r0 = r82;
        r4 = android.widget.Toast.makeText(r0, r4, r15);
        r4.show();
    L_0x027f:
        r4 = r62.intValue();
        if (r4 == 0) goto L_0x0c8c;
    L_0x0285:
        r22 = new android.os.Bundle;
        r22.<init>();
        r4 = "user_id";
        r15 = r62.intValue();
        r0 = r22;
        r0.putInt(r4, r15);
        r4 = r61.intValue();
        if (r4 == 0) goto L_0x02a8;
    L_0x029c:
        r4 = "message_id";
        r15 = r61.intValue();
        r0 = r22;
        r0.putInt(r4, r15);
    L_0x02a8:
        r4 = mainFragmentsStack;
        r4 = r4.isEmpty();
        if (r4 != 0) goto L_0x02c8;
    L_0x02b0:
        r4 = mainFragmentsStack;
        r15 = mainFragmentsStack;
        r15 = r15.size();
        r15 = r15 + -1;
        r4 = r4.get(r15);
        r4 = (org.telegram.ui.ActionBar.BaseFragment) r4;
        r0 = r22;
        r4 = org.telegram.messenger.MessagesController.checkCanOpenChat(r0, r4);
        if (r4 == 0) goto L_0x02e8;
    L_0x02c8:
        r42 = new org.telegram.ui.ChatActivity;
        r0 = r42;
        r1 = r22;
        r0.<init>(r1);
        r0 = r82;
        r4 = r0.actionBarLayout;
        r15 = 0;
        r16 = 1;
        r17 = 1;
        r0 = r42;
        r1 = r16;
        r2 = r17;
        r4 = r4.presentFragment(r0, r15, r1, r2);
        if (r4 == 0) goto L_0x02e8;
    L_0x02e6:
        r58 = 1;
    L_0x02e8:
        if (r58 != 0) goto L_0x0337;
    L_0x02ea:
        if (r84 != 0) goto L_0x0337;
    L_0x02ec:
        r4 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r4 == 0) goto L_0x1093;
    L_0x02f2:
        r4 = org.telegram.messenger.UserConfig.isClientActivated();
        if (r4 != 0) goto L_0x1057;
    L_0x02f8:
        r0 = r82;
        r4 = r0.layersActionBarLayout;
        r4 = r4.fragmentsStack;
        r4 = r4.isEmpty();
        if (r4 == 0) goto L_0x031c;
    L_0x0304:
        r0 = r82;
        r4 = r0.layersActionBarLayout;
        r15 = new org.telegram.ui.LoginActivity;
        r15.<init>();
        r4.addFragmentToStack(r15);
        r0 = r82;
        r4 = r0.drawerLayoutContainer;
        r15 = 0;
        r16 = 0;
        r0 = r16;
        r4.setAllowOpenDrawer(r15, r0);
    L_0x031c:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4.showLastFragment();
        r4 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r4 == 0) goto L_0x0337;
    L_0x0329:
        r0 = r82;
        r4 = r0.layersActionBarLayout;
        r4.showLastFragment();
        r0 = r82;
        r4 = r0.rightActionBarLayout;
        r4.showLastFragment();
    L_0x0337:
        r4 = 0;
        r0 = r83;
        r0.setAction(r4);
        goto L_0x0008;
    L_0x033f:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x026a }
        r4.<init>();	 Catch:{ Exception -> 0x026a }
        r0 = r30;
        r15 = r0.name;	 Catch:{ Exception -> 0x026a }
        r4 = r4.append(r15);	 Catch:{ Exception -> 0x026a }
        r0 = r45;
        r4 = r4.append(r0);	 Catch:{ Exception -> 0x026a }
        r4 = r4.toString();	 Catch:{ Exception -> 0x026a }
        r0 = r30;
        r0.name = r4;	 Catch:{ Exception -> 0x026a }
        goto L_0x0212;
    L_0x035c:
        r4 = 0;
        r4 = r22[r4];	 Catch:{ Exception -> 0x026a }
        r15 = "TEL";
        r4 = r4.startsWith(r15);	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x0125;
    L_0x0368:
        r4 = 1;
        r4 = r22[r4];	 Catch:{ Exception -> 0x026a }
        r15 = 1;
        r56 = org.telegram.PhoneFormat.PhoneFormat.stripExceptNumbers(r4, r15);	 Catch:{ Exception -> 0x026a }
        r4 = r56.length();	 Catch:{ Exception -> 0x026a }
        if (r4 <= 0) goto L_0x0125;
    L_0x0376:
        r0 = r30;
        r4 = r0.phones;	 Catch:{ Exception -> 0x026a }
        r0 = r56;
        r4.add(r0);	 Catch:{ Exception -> 0x026a }
        goto L_0x0125;
    L_0x0381:
        r25.close();	 Catch:{ Exception -> 0x03ff }
        r69.close();	 Catch:{ Exception -> 0x03ff }
    L_0x0387:
        r21 = 0;
    L_0x0389:
        r4 = r81.size();	 Catch:{ Exception -> 0x026a }
        r0 = r21;
        if (r0 >= r4) goto L_0x0270;
    L_0x0391:
        r0 = r81;
        r1 = r21;
        r80 = r0.get(r1);	 Catch:{ Exception -> 0x026a }
        r80 = (org.telegram.ui.LaunchActivity.VcardData) r80;	 Catch:{ Exception -> 0x026a }
        r0 = r80;
        r4 = r0.name;	 Catch:{ Exception -> 0x026a }
        if (r4 == 0) goto L_0x0404;
    L_0x03a1:
        r0 = r80;
        r4 = r0.phones;	 Catch:{ Exception -> 0x026a }
        r4 = r4.isEmpty();	 Catch:{ Exception -> 0x026a }
        if (r4 != 0) goto L_0x0404;
    L_0x03ab:
        r0 = r82;
        r4 = r0.contactsToSend;	 Catch:{ Exception -> 0x026a }
        if (r4 != 0) goto L_0x03ba;
    L_0x03b1:
        r4 = new java.util.ArrayList;	 Catch:{ Exception -> 0x026a }
        r4.<init>();	 Catch:{ Exception -> 0x026a }
        r0 = r82;
        r0.contactsToSend = r4;	 Catch:{ Exception -> 0x026a }
    L_0x03ba:
        r24 = 0;
    L_0x03bc:
        r0 = r80;
        r4 = r0.phones;	 Catch:{ Exception -> 0x026a }
        r4 = r4.size();	 Catch:{ Exception -> 0x026a }
        r0 = r24;
        if (r0 >= r4) goto L_0x0404;
    L_0x03c8:
        r0 = r80;
        r4 = r0.phones;	 Catch:{ Exception -> 0x026a }
        r0 = r24;
        r56 = r4.get(r0);	 Catch:{ Exception -> 0x026a }
        r56 = (java.lang.String) r56;	 Catch:{ Exception -> 0x026a }
        r77 = new org.telegram.tgnet.TLRPC$TL_userContact_old2;	 Catch:{ Exception -> 0x026a }
        r77.<init>();	 Catch:{ Exception -> 0x026a }
        r0 = r56;
        r1 = r77;
        r1.phone = r0;	 Catch:{ Exception -> 0x026a }
        r0 = r80;
        r4 = r0.name;	 Catch:{ Exception -> 0x026a }
        r0 = r77;
        r0.first_name = r4;	 Catch:{ Exception -> 0x026a }
        r4 = "";
        r0 = r77;
        r0.last_name = r4;	 Catch:{ Exception -> 0x026a }
        r4 = 0;
        r0 = r77;
        r0.id = r4;	 Catch:{ Exception -> 0x026a }
        r0 = r82;
        r4 = r0.contactsToSend;	 Catch:{ Exception -> 0x026a }
        r0 = r77;
        r4.add(r0);	 Catch:{ Exception -> 0x026a }
        r24 = r24 + 1;
        goto L_0x03bc;
    L_0x03ff:
        r38 = move-exception;
        org.telegram.messenger.FileLog.e(r38);	 Catch:{ Exception -> 0x026a }
        goto L_0x0387;
    L_0x0404:
        r21 = r21 + 1;
        goto L_0x0389;
    L_0x0407:
        r40 = 1;
        goto L_0x0270;
    L_0x040b:
        r4 = "android.intent.extra.TEXT";
        r0 = r83;
        r71 = r0.getStringExtra(r4);
        if (r71 != 0) goto L_0x0425;
    L_0x0416:
        r4 = "android.intent.extra.TEXT";
        r0 = r83;
        r72 = r0.getCharSequenceExtra(r4);
        if (r72 == 0) goto L_0x0425;
    L_0x0421:
        r71 = r72.toString();
    L_0x0425:
        r4 = "android.intent.extra.SUBJECT";
        r0 = r83;
        r70 = r0.getStringExtra(r4);
        if (r71 == 0) goto L_0x04e4;
    L_0x0430:
        r4 = r71.length();
        if (r4 == 0) goto L_0x04e4;
    L_0x0436:
        r4 = "http://";
        r0 = r71;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x044c;
    L_0x0441:
        r4 = "https://";
        r0 = r71;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x0470;
    L_0x044c:
        if (r70 == 0) goto L_0x0470;
    L_0x044e:
        r4 = r70.length();
        if (r4 == 0) goto L_0x0470;
    L_0x0454:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = r70;
        r4 = r4.append(r0);
        r15 = "\n";
        r4 = r4.append(r15);
        r0 = r71;
        r4 = r4.append(r0);
        r71 = r4.toString();
    L_0x0470:
        r0 = r71;
        r1 = r82;
        r1.sendingText = r0;
    L_0x0476:
        r4 = "android.intent.extra.STREAM";
        r0 = r83;
        r54 = r0.getParcelableExtra(r4);
        if (r54 == 0) goto L_0x0573;
    L_0x0481:
        r0 = r54;
        r4 = r0 instanceof android.net.Uri;
        if (r4 != 0) goto L_0x048f;
    L_0x0487:
        r4 = r54.toString();
        r54 = android.net.Uri.parse(r4);
    L_0x048f:
        r74 = r54;
        r74 = (android.net.Uri) r74;
        if (r74 == 0) goto L_0x049d;
    L_0x0495:
        r4 = org.telegram.messenger.AndroidUtilities.isInternalUri(r74);
        if (r4 == 0) goto L_0x049d;
    L_0x049b:
        r40 = 1;
    L_0x049d:
        if (r40 != 0) goto L_0x0270;
    L_0x049f:
        if (r74 == 0) goto L_0x04f3;
    L_0x04a1:
        if (r73 == 0) goto L_0x04ae;
    L_0x04a3:
        r4 = "image/";
        r0 = r73;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x04bf;
    L_0x04ae:
        r4 = r74.toString();
        r4 = r4.toLowerCase();
        r15 = ".jpg";
        r4 = r4.endsWith(r15);
        if (r4 == 0) goto L_0x04f3;
    L_0x04bf:
        r0 = r82;
        r4 = r0.photoPathsArray;
        if (r4 != 0) goto L_0x04ce;
    L_0x04c5:
        r4 = new java.util.ArrayList;
        r4.<init>();
        r0 = r82;
        r0.photoPathsArray = r4;
    L_0x04ce:
        r44 = new org.telegram.messenger.SendMessagesHelper$SendingMediaInfo;
        r44.<init>();
        r0 = r74;
        r1 = r44;
        r1.uri = r0;
        r0 = r82;
        r4 = r0.photoPathsArray;
        r0 = r44;
        r4.add(r0);
        goto L_0x0270;
    L_0x04e4:
        if (r70 == 0) goto L_0x0476;
    L_0x04e6:
        r4 = r70.length();
        if (r4 <= 0) goto L_0x0476;
    L_0x04ec:
        r0 = r70;
        r1 = r82;
        r1.sendingText = r0;
        goto L_0x0476;
    L_0x04f3:
        r55 = org.telegram.messenger.AndroidUtilities.getPath(r74);
        if (r55 == 0) goto L_0x0553;
    L_0x04f9:
        r4 = "file:";
        r0 = r55;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x0510;
    L_0x0504:
        r4 = "file://";
        r15 = "";
        r0 = r55;
        r55 = r0.replace(r4, r15);
    L_0x0510:
        if (r73 == 0) goto L_0x0525;
    L_0x0512:
        r4 = "video/";
        r0 = r73;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x0525;
    L_0x051d:
        r0 = r55;
        r1 = r82;
        r1.videoPath = r0;
        goto L_0x0270;
    L_0x0525:
        r0 = r82;
        r4 = r0.documentsPathsArray;
        if (r4 != 0) goto L_0x053d;
    L_0x052b:
        r4 = new java.util.ArrayList;
        r4.<init>();
        r0 = r82;
        r0.documentsPathsArray = r4;
        r4 = new java.util.ArrayList;
        r4.<init>();
        r0 = r82;
        r0.documentsOriginalPathsArray = r4;
    L_0x053d:
        r0 = r82;
        r4 = r0.documentsPathsArray;
        r0 = r55;
        r4.add(r0);
        r0 = r82;
        r4 = r0.documentsOriginalPathsArray;
        r15 = r74.toString();
        r4.add(r15);
        goto L_0x0270;
    L_0x0553:
        r0 = r82;
        r4 = r0.documentsUrisArray;
        if (r4 != 0) goto L_0x0562;
    L_0x0559:
        r4 = new java.util.ArrayList;
        r4.<init>();
        r0 = r82;
        r0.documentsUrisArray = r4;
    L_0x0562:
        r0 = r82;
        r4 = r0.documentsUrisArray;
        r0 = r74;
        r4.add(r0);
        r0 = r73;
        r1 = r82;
        r1.documentsMimeType = r0;
        goto L_0x0270;
    L_0x0573:
        r0 = r82;
        r4 = r0.sendingText;
        if (r4 != 0) goto L_0x0270;
    L_0x0579:
        r40 = 1;
        goto L_0x0270;
    L_0x057d:
        r4 = r83.getAction();
        r15 = "android.intent.action.SEND_MULTIPLE";
        r4 = r4.equals(r15);
        if (r4 == 0) goto L_0x06ef;
    L_0x058a:
        r40 = 0;
        r4 = "android.intent.extra.STREAM";
        r0 = r83;
        r75 = r0.getParcelableArrayListExtra(r4);	 Catch:{ Exception -> 0x06d5 }
        r73 = r83.getType();	 Catch:{ Exception -> 0x06d5 }
        if (r75 == 0) goto L_0x05df;
    L_0x059b:
        r21 = 0;
    L_0x059d:
        r4 = r75.size();	 Catch:{ Exception -> 0x06d5 }
        r0 = r21;
        if (r0 >= r4) goto L_0x05d7;
    L_0x05a5:
        r0 = r75;
        r1 = r21;
        r54 = r0.get(r1);	 Catch:{ Exception -> 0x06d5 }
        r54 = (android.os.Parcelable) r54;	 Catch:{ Exception -> 0x06d5 }
        r0 = r54;
        r4 = r0 instanceof android.net.Uri;	 Catch:{ Exception -> 0x06d5 }
        if (r4 != 0) goto L_0x05bd;
    L_0x05b5:
        r4 = r54.toString();	 Catch:{ Exception -> 0x06d5 }
        r54 = android.net.Uri.parse(r4);	 Catch:{ Exception -> 0x06d5 }
    L_0x05bd:
        r0 = r54;
        r0 = (android.net.Uri) r0;	 Catch:{ Exception -> 0x06d5 }
        r74 = r0;
        if (r74 == 0) goto L_0x05d4;
    L_0x05c5:
        r4 = org.telegram.messenger.AndroidUtilities.isInternalUri(r74);	 Catch:{ Exception -> 0x06d5 }
        if (r4 == 0) goto L_0x05d4;
    L_0x05cb:
        r0 = r75;
        r1 = r21;
        r0.remove(r1);	 Catch:{ Exception -> 0x06d5 }
        r21 = r21 + -1;
    L_0x05d4:
        r21 = r21 + 1;
        goto L_0x059d;
    L_0x05d7:
        r4 = r75.isEmpty();	 Catch:{ Exception -> 0x06d5 }
        if (r4 == 0) goto L_0x05df;
    L_0x05dd:
        r75 = 0;
    L_0x05df:
        if (r75 == 0) goto L_0x06ec;
    L_0x05e1:
        if (r73 == 0) goto L_0x063c;
    L_0x05e3:
        r4 = "image/";
        r0 = r73;
        r4 = r0.startsWith(r4);	 Catch:{ Exception -> 0x06d5 }
        if (r4 == 0) goto L_0x063c;
    L_0x05ee:
        r21 = 0;
    L_0x05f0:
        r4 = r75.size();	 Catch:{ Exception -> 0x06d5 }
        r0 = r21;
        if (r0 >= r4) goto L_0x06db;
    L_0x05f8:
        r0 = r75;
        r1 = r21;
        r54 = r0.get(r1);	 Catch:{ Exception -> 0x06d5 }
        r54 = (android.os.Parcelable) r54;	 Catch:{ Exception -> 0x06d5 }
        r0 = r54;
        r4 = r0 instanceof android.net.Uri;	 Catch:{ Exception -> 0x06d5 }
        if (r4 != 0) goto L_0x0610;
    L_0x0608:
        r4 = r54.toString();	 Catch:{ Exception -> 0x06d5 }
        r54 = android.net.Uri.parse(r4);	 Catch:{ Exception -> 0x06d5 }
    L_0x0610:
        r0 = r54;
        r0 = (android.net.Uri) r0;	 Catch:{ Exception -> 0x06d5 }
        r74 = r0;
        r0 = r82;
        r4 = r0.photoPathsArray;	 Catch:{ Exception -> 0x06d5 }
        if (r4 != 0) goto L_0x0625;
    L_0x061c:
        r4 = new java.util.ArrayList;	 Catch:{ Exception -> 0x06d5 }
        r4.<init>();	 Catch:{ Exception -> 0x06d5 }
        r0 = r82;
        r0.photoPathsArray = r4;	 Catch:{ Exception -> 0x06d5 }
    L_0x0625:
        r44 = new org.telegram.messenger.SendMessagesHelper$SendingMediaInfo;	 Catch:{ Exception -> 0x06d5 }
        r44.<init>();	 Catch:{ Exception -> 0x06d5 }
        r0 = r74;
        r1 = r44;
        r1.uri = r0;	 Catch:{ Exception -> 0x06d5 }
        r0 = r82;
        r4 = r0.photoPathsArray;	 Catch:{ Exception -> 0x06d5 }
        r0 = r44;
        r4.add(r0);	 Catch:{ Exception -> 0x06d5 }
        r21 = r21 + 1;
        goto L_0x05f0;
    L_0x063c:
        r21 = 0;
    L_0x063e:
        r4 = r75.size();	 Catch:{ Exception -> 0x06d5 }
        r0 = r21;
        if (r0 >= r4) goto L_0x06db;
    L_0x0646:
        r0 = r75;
        r1 = r21;
        r54 = r0.get(r1);	 Catch:{ Exception -> 0x06d5 }
        r54 = (android.os.Parcelable) r54;	 Catch:{ Exception -> 0x06d5 }
        r0 = r54;
        r4 = r0 instanceof android.net.Uri;	 Catch:{ Exception -> 0x06d5 }
        if (r4 != 0) goto L_0x065e;
    L_0x0656:
        r4 = r54.toString();	 Catch:{ Exception -> 0x06d5 }
        r54 = android.net.Uri.parse(r4);	 Catch:{ Exception -> 0x06d5 }
    L_0x065e:
        r0 = r54;
        r0 = (android.net.Uri) r0;	 Catch:{ Exception -> 0x06d5 }
        r74 = r0;
        r55 = org.telegram.messenger.AndroidUtilities.getPath(r74);	 Catch:{ Exception -> 0x06d5 }
        r51 = r54.toString();	 Catch:{ Exception -> 0x06d5 }
        if (r51 != 0) goto L_0x0670;
    L_0x066e:
        r51 = r55;
    L_0x0670:
        if (r55 == 0) goto L_0x06b6;
    L_0x0672:
        r4 = "file:";
        r0 = r55;
        r4 = r0.startsWith(r4);	 Catch:{ Exception -> 0x06d5 }
        if (r4 == 0) goto L_0x0689;
    L_0x067d:
        r4 = "file://";
        r15 = "";
        r0 = r55;
        r55 = r0.replace(r4, r15);	 Catch:{ Exception -> 0x06d5 }
    L_0x0689:
        r0 = r82;
        r4 = r0.documentsPathsArray;	 Catch:{ Exception -> 0x06d5 }
        if (r4 != 0) goto L_0x06a1;
    L_0x068f:
        r4 = new java.util.ArrayList;	 Catch:{ Exception -> 0x06d5 }
        r4.<init>();	 Catch:{ Exception -> 0x06d5 }
        r0 = r82;
        r0.documentsPathsArray = r4;	 Catch:{ Exception -> 0x06d5 }
        r4 = new java.util.ArrayList;	 Catch:{ Exception -> 0x06d5 }
        r4.<init>();	 Catch:{ Exception -> 0x06d5 }
        r0 = r82;
        r0.documentsOriginalPathsArray = r4;	 Catch:{ Exception -> 0x06d5 }
    L_0x06a1:
        r0 = r82;
        r4 = r0.documentsPathsArray;	 Catch:{ Exception -> 0x06d5 }
        r0 = r55;
        r4.add(r0);	 Catch:{ Exception -> 0x06d5 }
        r0 = r82;
        r4 = r0.documentsOriginalPathsArray;	 Catch:{ Exception -> 0x06d5 }
        r0 = r51;
        r4.add(r0);	 Catch:{ Exception -> 0x06d5 }
    L_0x06b3:
        r21 = r21 + 1;
        goto L_0x063e;
    L_0x06b6:
        r0 = r82;
        r4 = r0.documentsUrisArray;	 Catch:{ Exception -> 0x06d5 }
        if (r4 != 0) goto L_0x06c5;
    L_0x06bc:
        r4 = new java.util.ArrayList;	 Catch:{ Exception -> 0x06d5 }
        r4.<init>();	 Catch:{ Exception -> 0x06d5 }
        r0 = r82;
        r0.documentsUrisArray = r4;	 Catch:{ Exception -> 0x06d5 }
    L_0x06c5:
        r0 = r82;
        r4 = r0.documentsUrisArray;	 Catch:{ Exception -> 0x06d5 }
        r0 = r74;
        r4.add(r0);	 Catch:{ Exception -> 0x06d5 }
        r0 = r73;
        r1 = r82;
        r1.documentsMimeType = r0;	 Catch:{ Exception -> 0x06d5 }
        goto L_0x06b3;
    L_0x06d5:
        r38 = move-exception;
        org.telegram.messenger.FileLog.e(r38);
        r40 = 1;
    L_0x06db:
        if (r40 == 0) goto L_0x027f;
    L_0x06dd:
        r4 = "Unsupported content";
        r15 = 0;
        r0 = r82;
        r4 = android.widget.Toast.makeText(r0, r4, r15);
        r4.show();
        goto L_0x027f;
    L_0x06ec:
        r40 = 1;
        goto L_0x06db;
    L_0x06ef:
        r4 = "android.intent.action.VIEW";
        r15 = r83.getAction();
        r4 = r4.equals(r15);
        if (r4 == 0) goto L_0x0bc2;
    L_0x06fc:
        r32 = r83.getData();
        if (r32 == 0) goto L_0x027f;
    L_0x0702:
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r14 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r56 = 0;
        r13 = 0;
        r57 = 0;
        r12 = 0;
        r11 = 0;
        r64 = r32.getScheme();
        if (r64 == 0) goto L_0x078b;
    L_0x0716:
        r4 = "http";
        r0 = r64;
        r4 = r0.equals(r4);
        if (r4 != 0) goto L_0x072c;
    L_0x0721:
        r4 = "https";
        r0 = r64;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x091e;
    L_0x072c:
        r4 = r32.getHost();
        r43 = r4.toLowerCase();
        r4 = "telegram.me";
        r0 = r43;
        r4 = r0.equals(r4);
        if (r4 != 0) goto L_0x0760;
    L_0x073f:
        r4 = "t.me";
        r0 = r43;
        r4 = r0.equals(r4);
        if (r4 != 0) goto L_0x0760;
    L_0x074a:
        r4 = "telegram.dog";
        r0 = r43;
        r4 = r0.equals(r4);
        if (r4 != 0) goto L_0x0760;
    L_0x0755:
        r4 = "telesco.pe";
        r0 = r43;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x078b;
    L_0x0760:
        r55 = r32.getPath();
        if (r55 == 0) goto L_0x078b;
    L_0x0766:
        r4 = r55.length();
        r15 = 1;
        if (r4 <= r15) goto L_0x078b;
    L_0x076d:
        r4 = 1;
        r0 = r55;
        r55 = r0.substring(r4);
        r4 = "joinchat/";
        r0 = r55;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x07d5;
    L_0x077f:
        r4 = "joinchat/";
        r15 = "";
        r0 = r55;
        r6 = r0.replace(r4, r15);
    L_0x078b:
        if (r10 == 0) goto L_0x07aa;
    L_0x078d:
        r4 = "@";
        r4 = r10.startsWith(r4);
        if (r4 == 0) goto L_0x07aa;
    L_0x0796:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r15 = " ";
        r4 = r4.append(r15);
        r4 = r4.append(r10);
        r10 = r4.toString();
    L_0x07aa:
        if (r56 != 0) goto L_0x07ae;
    L_0x07ac:
        if (r57 == 0) goto L_0x0b61;
    L_0x07ae:
        r22 = new android.os.Bundle;
        r22.<init>();
        r4 = "phone";
        r0 = r22;
        r1 = r56;
        r0.putString(r4, r1);
        r4 = "hash";
        r0 = r22;
        r1 = r57;
        r0.putString(r4, r1);
        r4 = new org.telegram.ui.LaunchActivity$7;
        r0 = r82;
        r1 = r22;
        r4.<init>(r1);
        org.telegram.messenger.AndroidUtilities.runOnUIThread(r4);
        goto L_0x027f;
    L_0x07d5:
        r4 = "addstickers/";
        r0 = r55;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x07ed;
    L_0x07e0:
        r4 = "addstickers/";
        r15 = "";
        r0 = r55;
        r7 = r0.replace(r4, r15);
        goto L_0x078b;
    L_0x07ed:
        r4 = "iv/";
        r0 = r55;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x0825;
    L_0x07f8:
        r4 = 0;
        r15 = "url";
        r0 = r32;
        r15 = r0.getQueryParameter(r15);
        r14[r4] = r15;
        r4 = 1;
        r15 = "rhash";
        r0 = r32;
        r15 = r0.getQueryParameter(r15);
        r14[r4] = r15;
        r4 = 0;
        r4 = r14[r4];
        r4 = android.text.TextUtils.isEmpty(r4);
        if (r4 != 0) goto L_0x0822;
    L_0x0819:
        r4 = 1;
        r4 = r14[r4];
        r4 = android.text.TextUtils.isEmpty(r4);
        if (r4 == 0) goto L_0x078b;
    L_0x0822:
        r14 = 0;
        goto L_0x078b;
    L_0x0825:
        r4 = "msg/";
        r0 = r55;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x083b;
    L_0x0830:
        r4 = "share/";
        r0 = r55;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x08ad;
    L_0x083b:
        r4 = "url";
        r0 = r32;
        r10 = r0.getQueryParameter(r4);
        if (r10 != 0) goto L_0x0849;
    L_0x0846:
        r10 = "";
    L_0x0849:
        r4 = "text";
        r0 = r32;
        r4 = r0.getQueryParameter(r4);
        if (r4 == 0) goto L_0x0889;
    L_0x0854:
        r4 = r10.length();
        if (r4 <= 0) goto L_0x086f;
    L_0x085a:
        r11 = 1;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4 = r4.append(r10);
        r15 = "\n";
        r4 = r4.append(r15);
        r10 = r4.toString();
    L_0x086f:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4 = r4.append(r10);
        r15 = "text";
        r0 = r32;
        r15 = r0.getQueryParameter(r15);
        r4 = r4.append(r15);
        r10 = r4.toString();
    L_0x0889:
        r4 = r10.length();
        r15 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        if (r4 <= r15) goto L_0x0898;
    L_0x0891:
        r4 = 0;
        r15 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        r10 = r10.substring(r4, r15);
    L_0x0898:
        r4 = "\n";
        r4 = r10.endsWith(r4);
        if (r4 == 0) goto L_0x078b;
    L_0x08a1:
        r4 = 0;
        r15 = r10.length();
        r15 = r15 + -1;
        r10 = r10.substring(r4, r15);
        goto L_0x0898;
    L_0x08ad:
        r4 = "confirmphone";
        r0 = r55;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x08cc;
    L_0x08b8:
        r4 = "phone";
        r0 = r32;
        r56 = r0.getQueryParameter(r4);
        r4 = "hash";
        r0 = r32;
        r57 = r0.getQueryParameter(r4);
        goto L_0x078b;
    L_0x08cc:
        r4 = r55.length();
        r15 = 1;
        if (r4 < r15) goto L_0x078b;
    L_0x08d3:
        r65 = r32.getPathSegments();
        r4 = r65.size();
        if (r4 <= 0) goto L_0x0901;
    L_0x08dd:
        r4 = 0;
        r0 = r65;
        r5 = r0.get(r4);
        r5 = (java.lang.String) r5;
        r4 = r65.size();
        r15 = 1;
        if (r4 <= r15) goto L_0x0901;
    L_0x08ed:
        r4 = 1;
        r0 = r65;
        r4 = r0.get(r4);
        r4 = (java.lang.String) r4;
        r12 = org.telegram.messenger.Utilities.parseInt(r4);
        r4 = r12.intValue();
        if (r4 != 0) goto L_0x0901;
    L_0x0900:
        r12 = 0;
    L_0x0901:
        r4 = "start";
        r0 = r32;
        r8 = r0.getQueryParameter(r4);
        r4 = "startgroup";
        r0 = r32;
        r9 = r0.getQueryParameter(r4);
        r4 = "game";
        r0 = r32;
        r13 = r0.getQueryParameter(r4);
        goto L_0x078b;
    L_0x091e:
        r4 = "tg";
        r0 = r64;
        r4 = r0.equals(r4);
        if (r4 == 0) goto L_0x078b;
    L_0x0929:
        r76 = r32.toString();
        r4 = "tg:resolve";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x0943;
    L_0x0938:
        r4 = "tg://resolve";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x0999;
    L_0x0943:
        r4 = "tg:resolve";
        r15 = "tg://telegram.org";
        r0 = r76;
        r4 = r0.replace(r4, r15);
        r15 = "tg://resolve";
        r16 = "tg://telegram.org";
        r0 = r16;
        r76 = r4.replace(r15, r0);
        r32 = android.net.Uri.parse(r76);
        r4 = "domain";
        r0 = r32;
        r5 = r0.getQueryParameter(r4);
        r4 = "start";
        r0 = r32;
        r8 = r0.getQueryParameter(r4);
        r4 = "startgroup";
        r0 = r32;
        r9 = r0.getQueryParameter(r4);
        r4 = "game";
        r0 = r32;
        r13 = r0.getQueryParameter(r4);
        r4 = "post";
        r0 = r32;
        r4 = r0.getQueryParameter(r4);
        r12 = org.telegram.messenger.Utilities.parseInt(r4);
        r4 = r12.intValue();
        if (r4 != 0) goto L_0x078b;
    L_0x0996:
        r12 = 0;
        goto L_0x078b;
    L_0x0999:
        r4 = "tg:join";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x09af;
    L_0x09a4:
        r4 = "tg://join";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x09d6;
    L_0x09af:
        r4 = "tg:join";
        r15 = "tg://telegram.org";
        r0 = r76;
        r4 = r0.replace(r4, r15);
        r15 = "tg://join";
        r16 = "tg://telegram.org";
        r0 = r16;
        r76 = r4.replace(r15, r0);
        r32 = android.net.Uri.parse(r76);
        r4 = "invite";
        r0 = r32;
        r6 = r0.getQueryParameter(r4);
        goto L_0x078b;
    L_0x09d6:
        r4 = "tg:addstickers";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x09ec;
    L_0x09e1:
        r4 = "tg://addstickers";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x0a13;
    L_0x09ec:
        r4 = "tg:addstickers";
        r15 = "tg://telegram.org";
        r0 = r76;
        r4 = r0.replace(r4, r15);
        r15 = "tg://addstickers";
        r16 = "tg://telegram.org";
        r0 = r16;
        r76 = r4.replace(r15, r0);
        r32 = android.net.Uri.parse(r76);
        r4 = "set";
        r0 = r32;
        r7 = r0.getQueryParameter(r4);
        goto L_0x078b;
    L_0x0a13:
        r4 = "tg:msg";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x0a3f;
    L_0x0a1e:
        r4 = "tg://msg";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x0a3f;
    L_0x0a29:
        r4 = "tg://share";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x0a3f;
    L_0x0a34:
        r4 = "tg:share";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x0ae5;
    L_0x0a3f:
        r4 = "tg:msg";
        r15 = "tg://telegram.org";
        r0 = r76;
        r4 = r0.replace(r4, r15);
        r15 = "tg://msg";
        r16 = "tg://telegram.org";
        r0 = r16;
        r4 = r4.replace(r15, r0);
        r15 = "tg://share";
        r16 = "tg://telegram.org";
        r0 = r16;
        r4 = r4.replace(r15, r0);
        r15 = "tg:share";
        r16 = "tg://telegram.org";
        r0 = r16;
        r76 = r4.replace(r15, r0);
        r32 = android.net.Uri.parse(r76);
        r4 = "url";
        r0 = r32;
        r10 = r0.getQueryParameter(r4);
        if (r10 != 0) goto L_0x0a81;
    L_0x0a7e:
        r10 = "";
    L_0x0a81:
        r4 = "text";
        r0 = r32;
        r4 = r0.getQueryParameter(r4);
        if (r4 == 0) goto L_0x0ac1;
    L_0x0a8c:
        r4 = r10.length();
        if (r4 <= 0) goto L_0x0aa7;
    L_0x0a92:
        r11 = 1;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4 = r4.append(r10);
        r15 = "\n";
        r4 = r4.append(r15);
        r10 = r4.toString();
    L_0x0aa7:
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r4 = r4.append(r10);
        r15 = "text";
        r0 = r32;
        r15 = r0.getQueryParameter(r15);
        r4 = r4.append(r15);
        r10 = r4.toString();
    L_0x0ac1:
        r4 = r10.length();
        r15 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        if (r4 <= r15) goto L_0x0ad0;
    L_0x0ac9:
        r4 = 0;
        r15 = 16384; // 0x4000 float:2.2959E-41 double:8.0948E-320;
        r10 = r10.substring(r4, r15);
    L_0x0ad0:
        r4 = "\n";
        r4 = r10.endsWith(r4);
        if (r4 == 0) goto L_0x078b;
    L_0x0ad9:
        r4 = 0;
        r15 = r10.length();
        r15 = r15 + -1;
        r10 = r10.substring(r4, r15);
        goto L_0x0ad0;
    L_0x0ae5:
        r4 = "tg:confirmphone";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x0afb;
    L_0x0af0:
        r4 = "tg://confirmphone";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x0b0f;
    L_0x0afb:
        r4 = "phone";
        r0 = r32;
        r56 = r0.getQueryParameter(r4);
        r4 = "hash";
        r0 = r32;
        r57 = r0.getQueryParameter(r4);
        goto L_0x078b;
    L_0x0b0f:
        r4 = "tg:openmessage";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 != 0) goto L_0x0b25;
    L_0x0b1a:
        r4 = "tg://openmessage";
        r0 = r76;
        r4 = r0.startsWith(r4);
        if (r4 == 0) goto L_0x078b;
    L_0x0b25:
        r4 = "user_id";
        r0 = r32;
        r78 = r0.getQueryParameter(r4);
        r4 = "chat_id";
        r0 = r32;
        r27 = r0.getQueryParameter(r4);
        r4 = "message_id";
        r0 = r32;
        r46 = r0.getQueryParameter(r4);
        if (r78 == 0) goto L_0x0b56;
    L_0x0b42:
        r4 = java.lang.Integer.parseInt(r78);	 Catch:{ NumberFormatException -> 0x10f5 }
        r62 = java.lang.Integer.valueOf(r4);	 Catch:{ NumberFormatException -> 0x10f5 }
    L_0x0b4a:
        if (r46 == 0) goto L_0x078b;
    L_0x0b4c:
        r4 = java.lang.Integer.parseInt(r46);	 Catch:{ NumberFormatException -> 0x10ef }
        r61 = java.lang.Integer.valueOf(r4);	 Catch:{ NumberFormatException -> 0x10ef }
        goto L_0x078b;
    L_0x0b56:
        if (r27 == 0) goto L_0x0b4a;
    L_0x0b58:
        r4 = java.lang.Integer.parseInt(r27);	 Catch:{ NumberFormatException -> 0x10f2 }
        r59 = java.lang.Integer.valueOf(r4);	 Catch:{ NumberFormatException -> 0x10f2 }
        goto L_0x0b4a;
    L_0x0b61:
        if (r5 != 0) goto L_0x0b6d;
    L_0x0b63:
        if (r6 != 0) goto L_0x0b6d;
    L_0x0b65:
        if (r7 != 0) goto L_0x0b6d;
    L_0x0b67:
        if (r10 != 0) goto L_0x0b6d;
    L_0x0b69:
        if (r13 != 0) goto L_0x0b6d;
    L_0x0b6b:
        if (r14 == 0) goto L_0x0b75;
    L_0x0b6d:
        r15 = 0;
        r4 = r82;
        r4.runLinkRequest(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15);
        goto L_0x027f;
    L_0x0b75:
        r15 = r82.getContentResolver();	 Catch:{ Exception -> 0x0bbc }
        r16 = r83.getData();	 Catch:{ Exception -> 0x0bbc }
        r17 = 0;
        r18 = 0;
        r19 = 0;
        r20 = 0;
        r31 = r15.query(r16, r17, r18, r19, r20);	 Catch:{ Exception -> 0x0bbc }
        if (r31 == 0) goto L_0x027f;
    L_0x0b8b:
        r4 = r31.moveToFirst();	 Catch:{ Exception -> 0x0bbc }
        if (r4 == 0) goto L_0x0bb7;
    L_0x0b91:
        r4 = "DATA4";
        r0 = r31;
        r4 = r0.getColumnIndex(r4);	 Catch:{ Exception -> 0x0bbc }
        r0 = r31;
        r79 = r0.getInt(r4);	 Catch:{ Exception -> 0x0bbc }
        r4 = org.telegram.messenger.NotificationCenter.getInstance();	 Catch:{ Exception -> 0x0bbc }
        r15 = org.telegram.messenger.NotificationCenter.closeChats;	 Catch:{ Exception -> 0x0bbc }
        r16 = 0;
        r0 = r16;
        r0 = new java.lang.Object[r0];	 Catch:{ Exception -> 0x0bbc }
        r16 = r0;
        r0 = r16;
        r4.postNotificationName(r15, r0);	 Catch:{ Exception -> 0x0bbc }
        r62 = java.lang.Integer.valueOf(r79);	 Catch:{ Exception -> 0x0bbc }
    L_0x0bb7:
        r31.close();	 Catch:{ Exception -> 0x0bbc }
        goto L_0x027f;
    L_0x0bbc:
        r38 = move-exception;
        org.telegram.messenger.FileLog.e(r38);
        goto L_0x027f;
    L_0x0bc2:
        r4 = r83.getAction();
        r15 = "org.telegram.messenger.OPEN_ACCOUNT";
        r4 = r4.equals(r15);
        if (r4 == 0) goto L_0x0bd6;
    L_0x0bcf:
        r4 = 1;
        r50 = java.lang.Integer.valueOf(r4);
        goto L_0x027f;
    L_0x0bd6:
        r4 = r83.getAction();
        r15 = "new_dialog";
        r4 = r4.equals(r15);
        if (r4 == 0) goto L_0x0bea;
    L_0x0be3:
        r4 = 1;
        r49 = java.lang.Integer.valueOf(r4);
        goto L_0x027f;
    L_0x0bea:
        r4 = r83.getAction();
        r15 = "com.tmessages.openchat";
        r4 = r4.startsWith(r15);
        if (r4 == 0) goto L_0x0c6a;
    L_0x0bf7:
        r4 = "chatId";
        r15 = 0;
        r0 = r83;
        r28 = r0.getIntExtra(r4, r15);
        r4 = "userId";
        r15 = 0;
        r0 = r83;
        r79 = r0.getIntExtra(r4, r15);
        r4 = "encId";
        r15 = 0;
        r0 = r83;
        r39 = r0.getIntExtra(r4, r15);
        if (r28 == 0) goto L_0x0c30;
    L_0x0c17:
        r4 = org.telegram.messenger.NotificationCenter.getInstance();
        r15 = org.telegram.messenger.NotificationCenter.closeChats;
        r16 = 0;
        r0 = r16;
        r0 = new java.lang.Object[r0];
        r16 = r0;
        r0 = r16;
        r4.postNotificationName(r15, r0);
        r59 = java.lang.Integer.valueOf(r28);
        goto L_0x027f;
    L_0x0c30:
        if (r79 == 0) goto L_0x0c4b;
    L_0x0c32:
        r4 = org.telegram.messenger.NotificationCenter.getInstance();
        r15 = org.telegram.messenger.NotificationCenter.closeChats;
        r16 = 0;
        r0 = r16;
        r0 = new java.lang.Object[r0];
        r16 = r0;
        r0 = r16;
        r4.postNotificationName(r15, r0);
        r62 = java.lang.Integer.valueOf(r79);
        goto L_0x027f;
    L_0x0c4b:
        if (r39 == 0) goto L_0x0c66;
    L_0x0c4d:
        r4 = org.telegram.messenger.NotificationCenter.getInstance();
        r15 = org.telegram.messenger.NotificationCenter.closeChats;
        r16 = 0;
        r0 = r16;
        r0 = new java.lang.Object[r0];
        r16 = r0;
        r0 = r16;
        r4.postNotificationName(r15, r0);
        r60 = java.lang.Integer.valueOf(r39);
        goto L_0x027f;
    L_0x0c66:
        r66 = 1;
        goto L_0x027f;
    L_0x0c6a:
        r4 = r83.getAction();
        r15 = "com.tmessages.openplayer";
        r4 = r4.equals(r15);
        if (r4 == 0) goto L_0x0c7b;
    L_0x0c77:
        r68 = 1;
        goto L_0x027f;
    L_0x0c7b:
        r4 = r83.getAction();
        r15 = "org.tmessages.openlocations";
        r4 = r4.equals(r15);
        if (r4 == 0) goto L_0x027f;
    L_0x0c88:
        r67 = 1;
        goto L_0x027f;
    L_0x0c8c:
        r4 = r59.intValue();
        if (r4 == 0) goto L_0x0cf7;
    L_0x0c92:
        r22 = new android.os.Bundle;
        r22.<init>();
        r4 = "chat_id";
        r15 = r59.intValue();
        r0 = r22;
        r0.putInt(r4, r15);
        r4 = r61.intValue();
        if (r4 == 0) goto L_0x0cb5;
    L_0x0ca9:
        r4 = "message_id";
        r15 = r61.intValue();
        r0 = r22;
        r0.putInt(r4, r15);
    L_0x0cb5:
        r4 = mainFragmentsStack;
        r4 = r4.isEmpty();
        if (r4 != 0) goto L_0x0cd5;
    L_0x0cbd:
        r4 = mainFragmentsStack;
        r15 = mainFragmentsStack;
        r15 = r15.size();
        r15 = r15 + -1;
        r4 = r4.get(r15);
        r4 = (org.telegram.ui.ActionBar.BaseFragment) r4;
        r0 = r22;
        r4 = org.telegram.messenger.MessagesController.checkCanOpenChat(r0, r4);
        if (r4 == 0) goto L_0x02e8;
    L_0x0cd5:
        r42 = new org.telegram.ui.ChatActivity;
        r0 = r42;
        r1 = r22;
        r0.<init>(r1);
        r0 = r82;
        r4 = r0.actionBarLayout;
        r15 = 0;
        r16 = 1;
        r17 = 1;
        r0 = r42;
        r1 = r16;
        r2 = r17;
        r4 = r4.presentFragment(r0, r15, r1, r2);
        if (r4 == 0) goto L_0x02e8;
    L_0x0cf3:
        r58 = 1;
        goto L_0x02e8;
    L_0x0cf7:
        r4 = r60.intValue();
        if (r4 == 0) goto L_0x0d30;
    L_0x0cfd:
        r22 = new android.os.Bundle;
        r22.<init>();
        r4 = "enc_id";
        r15 = r60.intValue();
        r0 = r22;
        r0.putInt(r4, r15);
        r42 = new org.telegram.ui.ChatActivity;
        r0 = r42;
        r1 = r22;
        r0.<init>(r1);
        r0 = r82;
        r4 = r0.actionBarLayout;
        r15 = 0;
        r16 = 1;
        r17 = 1;
        r0 = r42;
        r1 = r16;
        r2 = r17;
        r4 = r4.presentFragment(r0, r15, r1, r2);
        if (r4 == 0) goto L_0x02e8;
    L_0x0d2c:
        r58 = 1;
        goto L_0x02e8;
    L_0x0d30:
        if (r66 == 0) goto L_0x0d86;
    L_0x0d32:
        r4 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r4 != 0) goto L_0x0d45;
    L_0x0d38:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4.removeAllFragments();
    L_0x0d3f:
        r58 = 0;
        r84 = 0;
        goto L_0x02e8;
    L_0x0d45:
        r0 = r82;
        r4 = r0.layersActionBarLayout;
        r4 = r4.fragmentsStack;
        r4 = r4.isEmpty();
        if (r4 != 0) goto L_0x0d3f;
    L_0x0d51:
        r21 = 0;
    L_0x0d53:
        r0 = r82;
        r4 = r0.layersActionBarLayout;
        r4 = r4.fragmentsStack;
        r4 = r4.size();
        r4 = r4 + -1;
        if (r4 <= 0) goto L_0x0d7d;
    L_0x0d61:
        r0 = r82;
        r15 = r0.layersActionBarLayout;
        r0 = r82;
        r4 = r0.layersActionBarLayout;
        r4 = r4.fragmentsStack;
        r16 = 0;
        r0 = r16;
        r4 = r4.get(r0);
        r4 = (org.telegram.ui.ActionBar.BaseFragment) r4;
        r15.removeFragmentFromStack(r4);
        r21 = r21 + -1;
        r21 = r21 + 1;
        goto L_0x0d53;
    L_0x0d7d:
        r0 = r82;
        r4 = r0.layersActionBarLayout;
        r15 = 0;
        r4.closeLastFragment(r15);
        goto L_0x0d3f;
    L_0x0d86:
        if (r68 == 0) goto L_0x0db1;
    L_0x0d88:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4 = r4.fragmentsStack;
        r4 = r4.isEmpty();
        if (r4 != 0) goto L_0x0dad;
    L_0x0d94:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4 = r4.fragmentsStack;
        r15 = 0;
        r42 = r4.get(r15);
        r42 = (org.telegram.ui.ActionBar.BaseFragment) r42;
        r4 = new org.telegram.ui.Components.AudioPlayerAlert;
        r0 = r82;
        r4.<init>(r0);
        r0 = r42;
        r0.showDialog(r4);
    L_0x0dad:
        r58 = 0;
        goto L_0x02e8;
    L_0x0db1:
        if (r67 == 0) goto L_0x0de3;
    L_0x0db3:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4 = r4.fragmentsStack;
        r4 = r4.isEmpty();
        if (r4 != 0) goto L_0x0ddf;
    L_0x0dbf:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4 = r4.fragmentsStack;
        r15 = 0;
        r42 = r4.get(r15);
        r42 = (org.telegram.ui.ActionBar.BaseFragment) r42;
        r4 = new org.telegram.ui.Components.SharingLocationsAlert;
        r15 = new org.telegram.ui.LaunchActivity$8;
        r0 = r82;
        r15.<init>();
        r0 = r82;
        r4.<init>(r0, r15);
        r0 = r42;
        r0.showDialog(r4);
    L_0x0ddf:
        r58 = 0;
        goto L_0x02e8;
    L_0x0de3:
        r0 = r82;
        r4 = r0.videoPath;
        if (r4 != 0) goto L_0x0e07;
    L_0x0de9:
        r0 = r82;
        r4 = r0.photoPathsArray;
        if (r4 != 0) goto L_0x0e07;
    L_0x0def:
        r0 = r82;
        r4 = r0.sendingText;
        if (r4 != 0) goto L_0x0e07;
    L_0x0df5:
        r0 = r82;
        r4 = r0.documentsPathsArray;
        if (r4 != 0) goto L_0x0e07;
    L_0x0dfb:
        r0 = r82;
        r4 = r0.contactsToSend;
        if (r4 != 0) goto L_0x0e07;
    L_0x0e01:
        r0 = r82;
        r4 = r0.documentsUrisArray;
        if (r4 == 0) goto L_0x0fa9;
    L_0x0e07:
        r4 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r4 != 0) goto L_0x0e20;
    L_0x0e0d:
        r4 = org.telegram.messenger.NotificationCenter.getInstance();
        r15 = org.telegram.messenger.NotificationCenter.closeChats;
        r16 = 0;
        r0 = r16;
        r0 = new java.lang.Object[r0];
        r16 = r0;
        r0 = r16;
        r4.postNotificationName(r15, r0);
    L_0x0e20:
        r16 = 0;
        r4 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1));
        if (r4 != 0) goto L_0x0f8c;
    L_0x0e26:
        r22 = new android.os.Bundle;
        r22.<init>();
        r4 = "onlySelect";
        r15 = 1;
        r0 = r22;
        r0.putBoolean(r4, r15);
        r4 = "dialogsType";
        r15 = 3;
        r0 = r22;
        r0.putInt(r4, r15);
        r0 = r82;
        r4 = r0.contactsToSend;
        if (r4 == 0) goto L_0x0ef7;
    L_0x0e43:
        r4 = "selectAlertString";
        r15 = "SendContactTo";
        r16 = 2131559912; // 0x7f0d05e8 float:1.8745181E38 double:1.0531305246E-314;
        r15 = org.telegram.messenger.LocaleController.getString(r15, r16);
        r0 = r22;
        r0.putString(r4, r15);
        r4 = "selectAlertStringGroup";
        r15 = "SendContactToGroup";
        r16 = 2131559899; // 0x7f0d05db float:1.8745155E38 double:1.053130518E-314;
        r15 = org.telegram.messenger.LocaleController.getString(r15, r16);
        r0 = r22;
        r0.putString(r4, r15);
    L_0x0e67:
        r42 = new org.telegram.ui.DialogsActivity;
        r0 = r42;
        r1 = r22;
        r0.<init>(r1);
        r0 = r42;
        r1 = r82;
        r0.setDelegate(r1);
        r4 = "on_start";
        r0 = r82;
        r0.loadBanner(r4);
        r4 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r4 == 0) goto L_0x0f20;
    L_0x0e85:
        r0 = r82;
        r4 = r0.layersActionBarLayout;
        r4 = r4.fragmentsStack;
        r4 = r4.size();
        if (r4 <= 0) goto L_0x0f1d;
    L_0x0e91:
        r0 = r82;
        r4 = r0.layersActionBarLayout;
        r4 = r4.fragmentsStack;
        r0 = r82;
        r15 = r0.layersActionBarLayout;
        r15 = r15.fragmentsStack;
        r15 = r15.size();
        r15 = r15 + -1;
        r4 = r4.get(r15);
        r4 = r4 instanceof org.telegram.ui.DialogsActivity;
        if (r4 == 0) goto L_0x0f1d;
    L_0x0eab:
        r63 = 1;
    L_0x0ead:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r15 = 1;
        r16 = 1;
        r0 = r42;
        r1 = r63;
        r2 = r16;
        r4.presentFragment(r0, r1, r15, r2);
        r58 = 1;
        r4 = org.telegram.ui.SecretMediaViewer.getInstance();
        r4 = r4.isVisible();
        if (r4 == 0) goto L_0x0f4e;
    L_0x0ec9:
        r4 = org.telegram.ui.SecretMediaViewer.getInstance();
        r15 = 0;
        r16 = 0;
        r0 = r16;
        r4.closePhoto(r15, r0);
    L_0x0ed5:
        r0 = r82;
        r4 = r0.drawerLayoutContainer;
        r15 = 0;
        r16 = 0;
        r0 = r16;
        r4.setAllowOpenDrawer(r15, r0);
        r4 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r4 == 0) goto L_0x0f7e;
    L_0x0ee7:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4.showLastFragment();
        r0 = r82;
        r4 = r0.rightActionBarLayout;
        r4.showLastFragment();
        goto L_0x02e8;
    L_0x0ef7:
        r4 = "selectAlertString";
        r15 = "SendMessagesTo";
        r16 = 2131559912; // 0x7f0d05e8 float:1.8745181E38 double:1.0531305246E-314;
        r15 = org.telegram.messenger.LocaleController.getString(r15, r16);
        r0 = r22;
        r0.putString(r4, r15);
        r4 = "selectAlertStringGroup";
        r15 = "SendMessagesToGroup";
        r16 = 2131559913; // 0x7f0d05e9 float:1.8745183E38 double:1.053130525E-314;
        r15 = org.telegram.messenger.LocaleController.getString(r15, r16);
        r0 = r22;
        r0.putString(r4, r15);
        goto L_0x0e67;
    L_0x0f1d:
        r63 = 0;
        goto L_0x0ead;
    L_0x0f20:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4 = r4.fragmentsStack;
        r4 = r4.size();
        r15 = 1;
        if (r4 <= r15) goto L_0x0f4b;
    L_0x0f2d:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4 = r4.fragmentsStack;
        r0 = r82;
        r15 = r0.actionBarLayout;
        r15 = r15.fragmentsStack;
        r15 = r15.size();
        r15 = r15 + -1;
        r4 = r4.get(r15);
        r4 = r4 instanceof org.telegram.ui.DialogsActivity;
        if (r4 == 0) goto L_0x0f4b;
    L_0x0f47:
        r63 = 1;
    L_0x0f49:
        goto L_0x0ead;
    L_0x0f4b:
        r63 = 0;
        goto L_0x0f49;
    L_0x0f4e:
        r4 = org.telegram.ui.PhotoViewer.getInstance();
        r4 = r4.isVisible();
        if (r4 == 0) goto L_0x0f66;
    L_0x0f58:
        r4 = org.telegram.ui.PhotoViewer.getInstance();
        r15 = 0;
        r16 = 1;
        r0 = r16;
        r4.closePhoto(r15, r0);
        goto L_0x0ed5;
    L_0x0f66:
        r4 = org.telegram.ui.ArticleViewer.getInstance();
        r4 = r4.isVisible();
        if (r4 == 0) goto L_0x0ed5;
    L_0x0f70:
        r4 = org.telegram.ui.ArticleViewer.getInstance();
        r15 = 0;
        r16 = 1;
        r0 = r16;
        r4.close(r15, r0);
        goto L_0x0ed5;
    L_0x0f7e:
        r0 = r82;
        r4 = r0.drawerLayoutContainer;
        r15 = 1;
        r16 = 0;
        r0 = r16;
        r4.setAllowOpenDrawer(r15, r0);
        goto L_0x02e8;
    L_0x0f8c:
        r37 = new java.util.ArrayList;
        r37.<init>();
        r4 = java.lang.Long.valueOf(r34);
        r0 = r37;
        r0.add(r4);
        r4 = 0;
        r15 = 0;
        r16 = 0;
        r0 = r82;
        r1 = r37;
        r2 = r16;
        r0.didSelectDialogs(r4, r1, r15, r2);
        goto L_0x02e8;
    L_0x0fa9:
        r4 = r50.intValue();
        if (r4 == 0) goto L_0x0ff8;
    L_0x0faf:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r15 = new org.telegram.ui.SettingsActivity;
        r15.<init>();
        r16 = 0;
        r17 = 1;
        r18 = 1;
        r0 = r16;
        r1 = r17;
        r2 = r18;
        r4.presentFragment(r15, r0, r1, r2);
        r4 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r4 == 0) goto L_0x0feb;
    L_0x0fcd:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4.showLastFragment();
        r0 = r82;
        r4 = r0.rightActionBarLayout;
        r4.showLastFragment();
        r0 = r82;
        r4 = r0.drawerLayoutContainer;
        r15 = 0;
        r16 = 0;
        r0 = r16;
        r4.setAllowOpenDrawer(r15, r0);
    L_0x0fe7:
        r58 = 1;
        goto L_0x02e8;
    L_0x0feb:
        r0 = r82;
        r4 = r0.drawerLayoutContainer;
        r15 = 1;
        r16 = 0;
        r0 = r16;
        r4.setAllowOpenDrawer(r15, r0);
        goto L_0x0fe7;
    L_0x0ff8:
        r4 = r49.intValue();
        if (r4 == 0) goto L_0x02e8;
    L_0x0ffe:
        r22 = new android.os.Bundle;
        r22.<init>();
        r4 = "destroyAfterSelect";
        r15 = 1;
        r0 = r22;
        r0.putBoolean(r4, r15);
        r0 = r82;
        r4 = r0.actionBarLayout;
        r15 = new org.telegram.ui.ContactsActivity;
        r0 = r22;
        r15.<init>(r0);
        r16 = 0;
        r17 = 1;
        r18 = 1;
        r0 = r16;
        r1 = r17;
        r2 = r18;
        r4.presentFragment(r15, r0, r1, r2);
        r4 = org.telegram.messenger.AndroidUtilities.isTablet();
        if (r4 == 0) goto L_0x104a;
    L_0x102c:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4.showLastFragment();
        r0 = r82;
        r4 = r0.rightActionBarLayout;
        r4.showLastFragment();
        r0 = r82;
        r4 = r0.drawerLayoutContainer;
        r15 = 0;
        r16 = 0;
        r0 = r16;
        r4.setAllowOpenDrawer(r15, r0);
    L_0x1046:
        r58 = 1;
        goto L_0x02e8;
    L_0x104a:
        r0 = r82;
        r4 = r0.drawerLayoutContainer;
        r15 = 1;
        r16 = 0;
        r0 = r16;
        r4.setAllowOpenDrawer(r15, r0);
        goto L_0x1046;
    L_0x1057:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4 = r4.fragmentsStack;
        r4 = r4.isEmpty();
        if (r4 == 0) goto L_0x031c;
    L_0x1063:
        r36 = new org.telegram.ui.DialogsActivity;
        r4 = 0;
        r0 = r36;
        r0.<init>(r4);
        r0 = r82;
        r4 = r0.sideMenu;
        r0 = r36;
        r0.setSideMenu(r4);
        r0 = r82;
        r4 = r0.actionBarLayout;
        r0 = r36;
        r4.addFragmentToStack(r0);
        r0 = r82;
        r4 = r0.drawerLayoutContainer;
        r15 = 1;
        r16 = 0;
        r0 = r16;
        r4.setAllowOpenDrawer(r15, r0);
        r4 = "on_start";
        r0 = r82;
        r0.loadBanner(r4);
        goto L_0x031c;
    L_0x1093:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r4 = r4.fragmentsStack;
        r4 = r4.isEmpty();
        if (r4 == 0) goto L_0x031c;
    L_0x109f:
        r4 = org.telegram.messenger.UserConfig.isClientActivated();
        if (r4 != 0) goto L_0x10bf;
    L_0x10a5:
        r0 = r82;
        r4 = r0.actionBarLayout;
        r15 = new org.telegram.ui.LoginActivity;
        r15.<init>();
        r4.addFragmentToStack(r15);
        r0 = r82;
        r4 = r0.drawerLayoutContainer;
        r15 = 0;
        r16 = 0;
        r0 = r16;
        r4.setAllowOpenDrawer(r15, r0);
        goto L_0x031c;
    L_0x10bf:
        r36 = new org.telegram.ui.DialogsActivity;
        r4 = 0;
        r0 = r36;
        r0.<init>(r4);
        r0 = r82;
        r4 = r0.sideMenu;
        r0 = r36;
        r0.setSideMenu(r4);
        r0 = r82;
        r4 = r0.actionBarLayout;
        r0 = r36;
        r4.addFragmentToStack(r0);
        r0 = r82;
        r4 = r0.drawerLayoutContainer;
        r15 = 1;
        r16 = 0;
        r0 = r16;
        r4.setAllowOpenDrawer(r15, r0);
        r4 = "on_start";
        r0 = r82;
        r0.loadBanner(r4);
        goto L_0x031c;
    L_0x10ef:
        r4 = move-exception;
        goto L_0x078b;
    L_0x10f2:
        r4 = move-exception;
        goto L_0x0b4a;
    L_0x10f5:
        r4 = move-exception;
        goto L_0x0b4a;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.ui.LaunchActivity.handleIntent(android.content.Intent, boolean, boolean, boolean):boolean");
    }

    private void runLinkRequest(String username, String group, String sticker, String botUser, String botChat, String message, boolean hasUrl, Integer messageId, String game, String[] instantView, int state) {
        final AlertDialog progressDialog = new AlertDialog(this, 1);
        progressDialog.setMessage(LocaleController.getString("Loading", R.string.Loading));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        int requestId = 0;
        TLObject req;
        final String str;
        final String str2;
        final String str3;
        if (username != null) {
            req = new TLRPC$TL_contacts_resolveUsername();
            req.username = username;
            str = game;
            str2 = botChat;
            str3 = botUser;
            final Integer num = messageId;
            requestId = ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
                public void run(final TLObject response, final TLRPC$TL_error error) {
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        public void run() {
                            if (!LaunchActivity.this.isFinishing()) {
                                try {
                                    progressDialog.dismiss();
                                } catch (Exception e) {
                                    FileLog.e(e);
                                }
                                final TLRPC$TL_contacts_resolvedPeer res = response;
                                if (error != null || LaunchActivity.this.actionBarLayout == null || (str != null && (str == null || res.users.isEmpty()))) {
                                    try {
                                        Toast.makeText(LaunchActivity.this, LocaleController.getAppNameString("NoUsernameFound", R.string.NoUsernameFound), 0).show();
                                        return;
                                    } catch (Exception e2) {
                                        FileLog.e(e2);
                                        return;
                                    }
                                }
                                MessagesController.getInstance().putUsers(res.users, false);
                                MessagesController.getInstance().putChats(res.chats, false);
                                MessagesStorage.getInstance().putUsersAndChats(res.users, res.chats, false, true);
                                Bundle args;
                                DialogsActivity fragment;
                                if (str != null) {
                                    args = new Bundle();
                                    args.putBoolean("onlySelect", true);
                                    args.putBoolean("cantSendToChannels", true);
                                    args.putInt("dialogsType", 1);
                                    args.putString("selectAlertString", LocaleController.getString("SendGameTo", R.string.SendGameTo));
                                    args.putString("selectAlertStringGroup", LocaleController.getString("SendGameToGroup", R.string.SendGameToGroup));
                                    fragment = new DialogsActivity(args);
                                    fragment.setDelegate(new DialogsActivityDelegate() {
                                        public void didSelectDialogs(DialogsActivity fragment, ArrayList<Long> dids, CharSequence message, boolean param) {
                                            long did = ((Long) dids.get(0)).longValue();
                                            TLRPC$TL_inputMediaGame inputMediaGame = new TLRPC$TL_inputMediaGame();
                                            inputMediaGame.id = new TLRPC$TL_inputGameShortName();
                                            inputMediaGame.id.short_name = str;
                                            inputMediaGame.id.bot_id = MessagesController.getInputUser((User) res.users.get(0));
                                            SendMessagesHelper.getInstance().sendGame(MessagesController.getInputPeer((int) did), inputMediaGame, 0, 0);
                                            Bundle args = new Bundle();
                                            args.putBoolean("scrollToTopOnResume", true);
                                            int lower_part = (int) did;
                                            int high_id = (int) (did >> 32);
                                            if (lower_part == 0) {
                                                args.putInt("enc_id", high_id);
                                            } else if (high_id == 1) {
                                                args.putInt("chat_id", lower_part);
                                            } else if (lower_part > 0) {
                                                args.putInt("user_id", lower_part);
                                            } else if (lower_part < 0) {
                                                args.putInt("chat_id", -lower_part);
                                            }
                                            if (MessagesController.checkCanOpenChat(args, fragment)) {
                                                NotificationCenter.getInstance().postNotificationName(NotificationCenter.closeChats, new Object[0]);
                                                LaunchActivity.this.actionBarLayout.presentFragment(new ChatActivity(args), true, false, true);
                                            }
                                        }
                                    });
                                    boolean removeLast = AndroidUtilities.isTablet() ? LaunchActivity.this.layersActionBarLayout.fragmentsStack.size() > 0 && (LaunchActivity.this.layersActionBarLayout.fragmentsStack.get(LaunchActivity.this.layersActionBarLayout.fragmentsStack.size() - 1) instanceof DialogsActivity) : LaunchActivity.this.actionBarLayout.fragmentsStack.size() > 1 && (LaunchActivity.this.actionBarLayout.fragmentsStack.get(LaunchActivity.this.actionBarLayout.fragmentsStack.size() - 1) instanceof DialogsActivity);
                                    LaunchActivity.this.actionBarLayout.presentFragment(fragment, removeLast, true, true);
                                    if (SecretMediaViewer.getInstance().isVisible()) {
                                        SecretMediaViewer.getInstance().closePhoto(false, false);
                                    } else if (PhotoViewer.getInstance().isVisible()) {
                                        PhotoViewer.getInstance().closePhoto(false, true);
                                    } else if (ArticleViewer.getInstance().isVisible()) {
                                        ArticleViewer.getInstance().close(false, true);
                                    }
                                    LaunchActivity.this.drawerLayoutContainer.setAllowOpenDrawer(false, false);
                                    if (AndroidUtilities.isTablet()) {
                                        LaunchActivity.this.actionBarLayout.showLastFragment();
                                        LaunchActivity.this.rightActionBarLayout.showLastFragment();
                                        return;
                                    }
                                    LaunchActivity.this.drawerLayoutContainer.setAllowOpenDrawer(true, false);
                                } else if (str2 != null) {
                                    final User user = !res.users.isEmpty() ? (User) res.users.get(0) : null;
                                    if (user == null || (user.bot && user.bot_nochats)) {
                                        try {
                                            Toast.makeText(LaunchActivity.this, LocaleController.getString("BotCantJoinGroups", R.string.BotCantJoinGroups), 0).show();
                                            return;
                                        } catch (Exception e22) {
                                            FileLog.e(e22);
                                            return;
                                        }
                                    }
                                    args = new Bundle();
                                    args.putBoolean("onlySelect", true);
                                    args.putInt("dialogsType", 2);
                                    args.putString("addToGroupAlertString", LocaleController.formatString("AddToTheGroupTitle", R.string.AddToTheGroupTitle, new Object[]{UserObject.getUserName(user), "%1$s"}));
                                    fragment = new DialogsActivity(args);
                                    fragment.setDelegate(new DialogsActivityDelegate() {
                                        public void didSelectDialogs(DialogsActivity fragment, ArrayList<Long> dids, CharSequence message, boolean param) {
                                            long did = ((Long) dids.get(0)).longValue();
                                            Bundle args = new Bundle();
                                            args.putBoolean("scrollToTopOnResume", true);
                                            args.putInt("chat_id", -((int) did));
                                            if (LaunchActivity.mainFragmentsStack.isEmpty() || MessagesController.checkCanOpenChat(args, (BaseFragment) LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1))) {
                                                NotificationCenter.getInstance().postNotificationName(NotificationCenter.closeChats, new Object[0]);
                                                MessagesController.getInstance().addUserToChat(-((int) did), user, null, 0, str2, null);
                                                LaunchActivity.this.actionBarLayout.presentFragment(new ChatActivity(args), true, false, true);
                                            }
                                        }
                                    });
                                    LaunchActivity.this.presentFragment(fragment);
                                } else {
                                    boolean isBot = false;
                                    args = new Bundle();
                                    long dialog_id;
                                    if (res.chats.isEmpty()) {
                                        args.putInt("user_id", ((User) res.users.get(0)).id);
                                        dialog_id = (long) ((User) res.users.get(0)).id;
                                    } else {
                                        args.putInt("chat_id", ((TLRPC$Chat) res.chats.get(0)).id);
                                        dialog_id = (long) (-((TLRPC$Chat) res.chats.get(0)).id);
                                    }
                                    if (str3 != null && res.users.size() > 0 && ((User) res.users.get(0)).bot) {
                                        args.putString("botUser", str3);
                                        isBot = true;
                                    }
                                    if (num != null) {
                                        args.putInt("message_id", num.intValue());
                                    }
                                    BaseFragment lastFragment = !LaunchActivity.mainFragmentsStack.isEmpty() ? (BaseFragment) LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1) : null;
                                    if (lastFragment != null && !MessagesController.checkCanOpenChat(args, lastFragment)) {
                                        return;
                                    }
                                    if (isBot && lastFragment != null && (lastFragment instanceof ChatActivity) && ((ChatActivity) lastFragment).getDialogId() == dialog_id) {
                                        ((ChatActivity) lastFragment).setBotUser(str3);
                                        return;
                                    }
                                    ChatActivity fragment2 = new ChatActivity(args);
                                    NotificationCenter.getInstance().postNotificationName(NotificationCenter.closeChats, new Object[0]);
                                    LaunchActivity.this.actionBarLayout.presentFragment(fragment2, false, true, true);
                                }
                            }
                        }
                    });
                }
            });
        } else if (group != null) {
            if (state == 0) {
                req = new TLRPC$TL_messages_checkChatInvite();
                req.hash = group;
                str = group;
                str2 = username;
                str3 = sticker;
                final String str4 = botUser;
                final String str5 = botChat;
                final String str6 = message;
                final boolean z = hasUrl;
                final Integer num2 = messageId;
                final String str7 = game;
                final String[] strArr = instantView;
                requestId = ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
                    public void run(final TLObject response, final TLRPC$TL_error error) {
                        AndroidUtilities.runOnUIThread(new Runnable() {

                            class C64981 implements DialogInterface.OnClickListener {
                                C64981() {
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    LaunchActivity.this.runLinkRequest(str2, str, str3, str4, str5, str6, z, num2, str7, strArr, 1);
                                }
                            }

                            public void run() {
                                if (!LaunchActivity.this.isFinishing()) {
                                    try {
                                        progressDialog.dismiss();
                                    } catch (Exception e) {
                                        FileLog.e(e);
                                    }
                                    Builder builder;
                                    if (error != null || LaunchActivity.this.actionBarLayout == null) {
                                        builder = new Builder(LaunchActivity.this);
                                        builder.setTitle(ApplicationLoader.getConfig().getAppName());
                                        if (error.text.startsWith("FLOOD_WAIT")) {
                                            builder.setMessage(LocaleController.getString("FloodWait", R.string.FloodWait));
                                        } else {
                                            builder.setMessage(LocaleController.getString("JoinToGroupErrorNotExist", R.string.JoinToGroupErrorNotExist));
                                        }
                                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                                        LaunchActivity.this.showAlertDialog(builder);
                                        return;
                                    }
                                    TLRPC$ChatInvite invite = response;
                                    if (invite.chat != null && !ChatObject.isLeftFromChat(invite.chat)) {
                                        MessagesController.getInstance().putChat(invite.chat, false);
                                        ArrayList<TLRPC$Chat> chats = new ArrayList();
                                        chats.add(invite.chat);
                                        MessagesStorage.getInstance().putUsersAndChats(null, chats, false, true);
                                        Bundle args = new Bundle();
                                        args.putInt("chat_id", invite.chat.id);
                                        if (LaunchActivity.mainFragmentsStack.isEmpty() || MessagesController.checkCanOpenChat(args, (BaseFragment) LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1))) {
                                            ChatActivity fragment = new ChatActivity(args);
                                            NotificationCenter.getInstance().postNotificationName(NotificationCenter.closeChats, new Object[0]);
                                            LaunchActivity.this.actionBarLayout.presentFragment(fragment, false, true, true);
                                        }
                                    } else if (((invite.chat != null || (invite.channel && !invite.megagroup)) && (invite.chat == null || (ChatObject.isChannel(invite.chat) && !invite.chat.megagroup))) || LaunchActivity.mainFragmentsStack.isEmpty()) {
                                        builder = new Builder(LaunchActivity.this);
                                        builder.setTitle(ApplicationLoader.getConfig().getAppName());
                                        String str = "ChannelJoinTo";
                                        Object[] objArr = new Object[1];
                                        objArr[0] = invite.chat != null ? invite.chat.title : invite.title;
                                        builder.setMessage(LocaleController.formatString(str, R.string.ChannelJoinTo, objArr));
                                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C64981());
                                        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                                        LaunchActivity.this.showAlertDialog(builder);
                                    } else {
                                        BaseFragment fragment2 = (BaseFragment) LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1);
                                        fragment2.showDialog(new JoinGroupAlert(LaunchActivity.this, invite, str, fragment2));
                                    }
                                }
                            }
                        });
                    }
                }, 2);
            } else if (state == 1) {
                req = new TLRPC$TL_messages_importChatInvite();
                req.hash = group;
                ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
                    public void run(final TLObject response, final TLRPC$TL_error error) {
                        if (error == null) {
                            MessagesController.getInstance().processUpdates((TLRPC$Updates) response, false);
                        }
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            public void run() {
                                if (!LaunchActivity.this.isFinishing()) {
                                    try {
                                        progressDialog.dismiss();
                                    } catch (Exception e) {
                                        FileLog.e(e);
                                    }
                                    if (error != null) {
                                        Builder builder = new Builder(LaunchActivity.this);
                                        builder.setTitle(ApplicationLoader.getConfig().getAppName());
                                        if (error.text.startsWith("FLOOD_WAIT")) {
                                            builder.setMessage(LocaleController.getString("FloodWait", R.string.FloodWait));
                                        } else if (error.text.equals("USERS_TOO_MUCH")) {
                                            builder.setMessage(LocaleController.getString("JoinToGroupErrorFull", R.string.JoinToGroupErrorFull));
                                        } else {
                                            builder.setMessage(LocaleController.getString("JoinToGroupErrorNotExist", R.string.JoinToGroupErrorNotExist));
                                        }
                                        builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                                        LaunchActivity.this.showAlertDialog(builder);
                                    } else if (LaunchActivity.this.actionBarLayout != null) {
                                        TLRPC$Updates updates = response;
                                        if (!updates.chats.isEmpty()) {
                                            TLRPC$Chat chat = (TLRPC$Chat) updates.chats.get(0);
                                            chat.left = false;
                                            chat.kicked = false;
                                            MessagesController.getInstance().putUsers(updates.users, false);
                                            MessagesController.getInstance().putChats(updates.chats, false);
                                            Bundle args = new Bundle();
                                            args.putInt("chat_id", chat.id);
                                            if (LaunchActivity.mainFragmentsStack.isEmpty() || MessagesController.checkCanOpenChat(args, (BaseFragment) LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1))) {
                                                ChatActivity fragment = new ChatActivity(args);
                                                NotificationCenter.getInstance().postNotificationName(NotificationCenter.closeChats, new Object[0]);
                                                LaunchActivity.this.actionBarLayout.presentFragment(fragment, false, true, true);
                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                }, 2);
            }
        } else if (sticker != null) {
            if (!mainFragmentsStack.isEmpty()) {
                TLRPC$TL_inputStickerSetShortName stickerset = new TLRPC$TL_inputStickerSetShortName();
                stickerset.short_name = sticker;
                BaseFragment fragment = (BaseFragment) mainFragmentsStack.get(mainFragmentsStack.size() - 1);
                fragment.showDialog(new StickersAlert(this, fragment, stickerset, null, null));
                return;
            }
            return;
        } else if (message != null) {
            Bundle args = new Bundle();
            args.putBoolean("onlySelect", true);
            DialogsActivity fragment2 = new DialogsActivity(args);
            final boolean z2 = hasUrl;
            final String str8 = message;
            fragment2.setDelegate(new DialogsActivityDelegate() {
                public void didSelectDialogs(DialogsActivity fragment, ArrayList<Long> dids, CharSequence m, boolean param) {
                    long did = ((Long) dids.get(0)).longValue();
                    Bundle args = new Bundle();
                    args.putBoolean("scrollToTopOnResume", true);
                    args.putBoolean("hasUrl", z2);
                    int lower_part = (int) did;
                    int high_id = (int) (did >> 32);
                    if (lower_part == 0) {
                        args.putInt("enc_id", high_id);
                    } else if (high_id == 1) {
                        args.putInt("chat_id", lower_part);
                    } else if (lower_part > 0) {
                        args.putInt("user_id", lower_part);
                    } else if (lower_part < 0) {
                        args.putInt("chat_id", -lower_part);
                    }
                    if (MessagesController.checkCanOpenChat(args, fragment)) {
                        NotificationCenter.getInstance().postNotificationName(NotificationCenter.closeChats, new Object[0]);
                        DraftQuery.saveDraft(did, str8, null, null, true);
                        LaunchActivity.this.actionBarLayout.presentFragment(new ChatActivity(args), true, false, true);
                    }
                }
            });
            presentFragment(fragment2, false, true);
        } else if (instantView != null) {
        }
        if (requestId != 0) {
            final int i = requestId;
            progressDialog.setButton(-2, LocaleController.getString("Cancel", R.string.Cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ConnectionsManager.getInstance().cancelRequest(i, true);
                    try {
                        dialog.dismiss();
                    } catch (Exception e) {
                        FileLog.e(e);
                    }
                }
            });
            try {
                progressDialog.show();
            } catch (Exception e) {
            }
        }
    }

    public AlertDialog showAlertDialog(Builder builder) {
        AlertDialog alertDialog = null;
        try {
            if (this.visibleDialog != null) {
                this.visibleDialog.dismiss();
                this.visibleDialog = null;
            }
        } catch (Exception e) {
            FileLog.e(e);
        }
        try {
            this.visibleDialog = builder.show();
            this.visibleDialog.setCanceledOnTouchOutside(true);
            this.visibleDialog.setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    if (LaunchActivity.this.visibleDialog != null && LaunchActivity.this.visibleDialog == LaunchActivity.this.localeDialog) {
                        try {
                            Toast.makeText(LaunchActivity.this, LaunchActivity.this.getStringForLanguageAlert(LocaleController.getInstance().getCurrentLocaleInfo().shortName.equals("en") ? LaunchActivity.this.englishLocaleStrings : LaunchActivity.this.systemLocaleStrings, "ChangeLanguageLater", R.string.ChangeLanguageLater), 1).show();
                        } catch (Exception e) {
                            FileLog.e("tmessages", e);
                        }
                        LaunchActivity.this.localeDialog = null;
                    }
                    LaunchActivity.this.visibleDialog = null;
                }
            });
            return this.visibleDialog;
        } catch (Exception e2) {
            FileLog.e(e2);
            return alertDialog;
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent, true, false, false);
    }

    public void didSelectDialogs(DialogsActivity dialogsFragment, ArrayList<Long> dids, CharSequence message, boolean param) {
        long did = ((Long) dids.get(0)).longValue();
        int lower_part = (int) did;
        int high_id = (int) (did >> 32);
        Bundle args = new Bundle();
        args.putBoolean("scrollToTopOnResume", true);
        if (!AndroidUtilities.isTablet()) {
            NotificationCenter.getInstance().postNotificationName(NotificationCenter.closeChats, new Object[0]);
        }
        if (lower_part == 0) {
            args.putInt("enc_id", high_id);
        } else if (high_id == 1) {
            args.putInt("chat_id", lower_part);
        } else if (lower_part > 0) {
            args.putInt("user_id", lower_part);
        } else if (lower_part < 0) {
            args.putInt("chat_id", -lower_part);
        }
        if (MessagesController.checkCanOpenChat(args, dialogsFragment)) {
            boolean z;
            boolean z2;
            ChatActivity fragment = new ChatActivity(args);
            ActionBarLayout actionBarLayout = this.actionBarLayout;
            if (dialogsFragment != null) {
                z = true;
            } else {
                z = false;
            }
            if (dialogsFragment == null) {
                z2 = true;
            } else {
                z2 = false;
            }
            actionBarLayout.presentFragment(fragment, z, z2, true);
            if (this.videoPath != null) {
                fragment.openVideoEditor(this.videoPath, this.sendingText);
                this.sendingText = null;
            }
            if (this.photoPathsArray != null) {
                if (this.sendingText != null && this.sendingText.length() <= 200 && this.photoPathsArray.size() == 1) {
                    ((SendMessagesHelper$SendingMediaInfo) this.photoPathsArray.get(0)).caption = this.sendingText;
                }
                SendMessagesHelper.prepareSendingMedia(this.photoPathsArray, did, null, null, false, false);
            }
            if (this.sendingText != null) {
                SendMessagesHelper.prepareSendingText(this.sendingText, did);
            }
            if (!(this.documentsPathsArray == null && this.documentsUrisArray == null)) {
                SendMessagesHelper.prepareSendingDocuments(this.documentsPathsArray, this.documentsOriginalPathsArray, this.documentsUrisArray, this.documentsMimeType, did, null, null);
            }
            if (!(this.contactsToSend == null || this.contactsToSend.isEmpty())) {
                Iterator it = this.contactsToSend.iterator();
                while (it.hasNext()) {
                    SendMessagesHelper.getInstance().sendMessage((User) it.next(), did, null, null, null);
                }
            }
            this.photoPathsArray = null;
            this.videoPath = null;
            this.sendingText = null;
            this.documentsPathsArray = null;
            this.documentsOriginalPathsArray = null;
            this.contactsToSend = null;
        }
    }

    private void onFinish() {
        if (!this.finished) {
            this.finished = true;
            if (this.lockRunnable != null) {
                AndroidUtilities.cancelRunOnUIThread(this.lockRunnable);
                this.lockRunnable = null;
            }
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.appDidLogout);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.mainUserInfoChanged);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.closeOtherAppActivities);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didUpdatedConnectionState);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.needShowAlert);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.wasUnableToFindCurrentLocation);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didSetNewWallpapper);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didSetPasscode);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.reloadInterface);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.suggestedLangpack);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.openArticle);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.hasNewContactsToImport);
            NotificationCenter.getInstance().removeObserver(this, NotificationCenter.didSetNewTheme);
        }
    }

    public void presentFragment(BaseFragment fragment) {
        this.actionBarLayout.presentFragment(fragment);
    }

    public boolean presentFragment(BaseFragment fragment, boolean removeLast, boolean forceWithoutAnimation) {
        return this.actionBarLayout.presentFragment(fragment, removeLast, forceWithoutAnimation, true);
    }

    public ActionBarLayout getActionBarLayout() {
        return this.actionBarLayout;
    }

    public ActionBarLayout getLayersActionBarLayout() {
        return this.layersActionBarLayout;
    }

    public ActionBarLayout getRightActionBarLayout() {
        return this.rightActionBarLayout;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!(UserConfig.passcodeHash.length() == 0 || UserConfig.lastPauseTime == 0)) {
            UserConfig.lastPauseTime = 0;
            UserConfig.saveConfig(false);
        }
        super.onActivityResult(requestCode, resultCode, data);
        ThemeEditorView editorView = ThemeEditorView.getInstance();
        if (editorView != null) {
            editorView.onActivityResult(requestCode, resultCode, data);
        }
        if (this.actionBarLayout.fragmentsStack.size() != 0) {
            ((BaseFragment) this.actionBarLayout.fragmentsStack.get(this.actionBarLayout.fragmentsStack.size() - 1)).onActivityResultFragment(requestCode, resultCode, data);
        }
        if (AndroidUtilities.isTablet()) {
            if (this.rightActionBarLayout.fragmentsStack.size() != 0) {
                ((BaseFragment) this.rightActionBarLayout.fragmentsStack.get(this.rightActionBarLayout.fragmentsStack.size() - 1)).onActivityResultFragment(requestCode, resultCode, data);
            }
            if (this.layersActionBarLayout.fragmentsStack.size() != 0) {
                ((BaseFragment) this.layersActionBarLayout.fragmentsStack.get(this.layersActionBarLayout.fragmentsStack.size() - 1)).onActivityResultFragment(requestCode, resultCode, data);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 3 || requestCode == 4 || requestCode == 5 || requestCode == 19 || requestCode == 20) {
            boolean showAlert = true;
            if (grantResults.length > 0 && grantResults[0] == 0) {
                if (requestCode == 4) {
                    ImageLoader.getInstance().checkMediaPaths();
                    return;
                } else if (requestCode == 5) {
                    ContactsController.getInstance().forceImportContacts();
                    return;
                } else if (requestCode == 3) {
                    if (MediaController.getInstance().canInAppCamera()) {
                        CameraController.getInstance().initCamera();
                        return;
                    }
                    return;
                } else if (requestCode == 19 || requestCode == 20) {
                    showAlert = false;
                }
            }
            if (showAlert) {
                Builder builder = new Builder(this);
                builder.setTitle(ApplicationLoader.getConfig().getAppName());
                if (requestCode == 3) {
                    builder.setMessage(LocaleController.getAppNameString("PermissionNoAudio", R.string.PermissionNoAudio));
                } else if (requestCode == 4) {
                    builder.setMessage(LocaleController.getAppNameString("PermissionStorage", R.string.PermissionStorage));
                } else if (requestCode == 5) {
                    builder.setMessage(LocaleController.getAppNameString("PermissionContacts", R.string.PermissionContacts));
                } else if (requestCode == 19 || requestCode == 20) {
                    builder.setMessage(LocaleController.getAppNameString("PermissionNoCamera", R.string.PermissionNoCamera));
                }
                builder.setNegativeButton(LocaleController.getString("PermissionOpenSettings", R.string.PermissionOpenSettings), new DialogInterface.OnClickListener() {
                    @TargetApi(9)
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));
                            LaunchActivity.this.startActivity(intent);
                        } catch (Exception e) {
                            FileLog.e(e);
                        }
                    }
                });
                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
                builder.show();
                return;
            }
        } else if (requestCode == 2 && grantResults.length > 0 && grantResults[0] == 0) {
            NotificationCenter.getInstance().postNotificationName(NotificationCenter.locationPermissionGranted, new Object[0]);
        }
        if (this.actionBarLayout.fragmentsStack.size() != 0) {
            ((BaseFragment) this.actionBarLayout.fragmentsStack.get(this.actionBarLayout.fragmentsStack.size() - 1)).onRequestPermissionsResultFragment(requestCode, permissions, grantResults);
        }
        if (AndroidUtilities.isTablet()) {
            if (this.rightActionBarLayout.fragmentsStack.size() != 0) {
                ((BaseFragment) this.rightActionBarLayout.fragmentsStack.get(this.rightActionBarLayout.fragmentsStack.size() - 1)).onRequestPermissionsResultFragment(requestCode, permissions, grantResults);
            }
            if (this.layersActionBarLayout.fragmentsStack.size() != 0) {
                ((BaseFragment) this.layersActionBarLayout.fragmentsStack.get(this.layersActionBarLayout.fragmentsStack.size() - 1)).onRequestPermissionsResultFragment(requestCode, permissions, grantResults);
            }
        }
    }

    protected void onPause() {
        super.onPause();
        AppsgeyserSDK.onPause(this);
        UserConfig.lastAppPauseTime = System.currentTimeMillis();
        ApplicationLoader.mainInterfacePaused = true;
        Utilities.stageQueue.postRunnable(new Runnable() {
            public void run() {
                ApplicationLoader.mainInterfacePausedStageQueue = true;
                ApplicationLoader.mainInterfacePausedStageQueueTime = 0;
            }
        });
        onPasscodePause();
        this.actionBarLayout.onPause();
        if (AndroidUtilities.isTablet()) {
            this.rightActionBarLayout.onPause();
            this.layersActionBarLayout.onPause();
        }
        if (this.passcodeView != null) {
            this.passcodeView.onPause();
        }
        ConnectionsManager.getInstance().setAppPaused(true, false);
        AndroidUtilities.unregisterUpdates();
        if (PhotoViewer.getInstance().isVisible()) {
            PhotoViewer.getInstance().onPause();
        }
    }

    protected void onStart() {
        super.onStart();
        Browser.bindCustomTabsService(this);
    }

    protected void onStop() {
        super.onStop();
        Browser.unbindCustomTabsService(this);
    }

    protected void onDestroy() {
        PhotoViewer.getInstance().destroyPhotoViewer();
        SecretMediaViewer.getInstance().destroyPhotoViewer();
        ArticleViewer.getInstance().destroyArticleViewer();
        StickerPreviewViewer.getInstance().destroy();
        PipRoundVideoView pipRoundVideoView = PipRoundVideoView.getInstance();
        MediaController.getInstance().setBaseActivity(this, false);
        if (pipRoundVideoView != null) {
            pipRoundVideoView.close(false);
        }
        Theme.destroyResources();
        EmbedBottomSheet embedBottomSheet = EmbedBottomSheet.getInstance();
        if (embedBottomSheet != null) {
            embedBottomSheet.destroy();
        }
        ThemeEditorView editorView = ThemeEditorView.getInstance();
        if (editorView != null) {
            editorView.destroy();
        }
        try {
            if (this.visibleDialog != null) {
                this.visibleDialog.dismiss();
                this.visibleDialog = null;
            }
        } catch (Exception e) {
            FileLog.e(e);
        }
        try {
            if (this.onGlobalLayoutListener != null) {
                getWindow().getDecorView().getRootView().getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
            }
        } catch (Exception e2) {
            FileLog.e(e2);
        }
        super.onDestroy();
        onFinish();
    }

    protected void onResume() {
        super.onResume();
        AppsgeyserSDK.onResume(this);
        showLanguageAlert(false);
        ApplicationLoader.mainInterfacePaused = false;
        Utilities.stageQueue.postRunnable(new Runnable() {
            public void run() {
                ApplicationLoader.mainInterfacePausedStageQueue = false;
                ApplicationLoader.mainInterfacePausedStageQueueTime = System.currentTimeMillis();
            }
        });
        checkFreeDiscSpace();
        MediaController.checkGallery();
        onPasscodeResume();
        if (this.passcodeView.getVisibility() != 0) {
            this.actionBarLayout.onResume();
            if (AndroidUtilities.isTablet()) {
                this.rightActionBarLayout.onResume();
                this.layersActionBarLayout.onResume();
            }
        } else {
            this.actionBarLayout.dismissDialogs();
            if (AndroidUtilities.isTablet()) {
                this.rightActionBarLayout.dismissDialogs();
                this.layersActionBarLayout.dismissDialogs();
            }
            this.passcodeView.onResume();
        }
        AndroidUtilities.checkForCrashes(this);
        AndroidUtilities.checkForUpdates(this);
        ConnectionsManager.getInstance().setAppPaused(false, false);
        updateCurrentConnectionState();
        if (PhotoViewer.getInstance().isVisible()) {
            PhotoViewer.getInstance().onResume();
        }
        if (PipRoundVideoView.getInstance() != null && MediaController.getInstance().isMessagePaused()) {
            MessageObject messageObject = MediaController.getInstance().getPlayingMessageObject();
            if (messageObject != null) {
                MediaController.getInstance().seekToProgress(messageObject, messageObject.audioProgress);
            }
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        AndroidUtilities.checkDisplaySize(this, newConfig);
        super.onConfigurationChanged(newConfig);
        checkLayout();
        PipRoundVideoView pipRoundVideoView = PipRoundVideoView.getInstance();
        if (pipRoundVideoView != null) {
            pipRoundVideoView.onConfigurationChanged();
        }
        EmbedBottomSheet embedBottomSheet = EmbedBottomSheet.getInstance();
        if (embedBottomSheet != null) {
            embedBottomSheet.onConfigurationChanged(newConfig);
        }
        ThemeEditorView editorView = ThemeEditorView.getInstance();
        if (editorView != null) {
            editorView.onConfigurationChanged();
        }
    }

    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        AndroidUtilities.isInMultiwindow = isInMultiWindowMode;
        checkLayout();
    }

    public void didReceivedNotification(int id, Object... args) {
        if (id == NotificationCenter.appDidLogout) {
            if (this.drawerLayoutAdapter != null) {
                this.drawerLayoutAdapter.notifyDataSetChanged();
            }
            Iterator it = this.actionBarLayout.fragmentsStack.iterator();
            while (it.hasNext()) {
                ((BaseFragment) it.next()).onFragmentDestroy();
            }
            this.actionBarLayout.fragmentsStack.clear();
            if (AndroidUtilities.isTablet()) {
                it = this.layersActionBarLayout.fragmentsStack.iterator();
                while (it.hasNext()) {
                    ((BaseFragment) it.next()).onFragmentDestroy();
                }
                this.layersActionBarLayout.fragmentsStack.clear();
                it = this.rightActionBarLayout.fragmentsStack.iterator();
                while (it.hasNext()) {
                    ((BaseFragment) it.next()).onFragmentDestroy();
                }
                this.rightActionBarLayout.fragmentsStack.clear();
            }
            startActivity(new Intent(this, IntroActivity.class));
            onFinish();
            finish();
        } else if (id == NotificationCenter.closeOtherAppActivities) {
            if (args[0] != this) {
                onFinish();
                finish();
            }
        } else if (id == NotificationCenter.didUpdatedConnectionState) {
            int state = ConnectionsManager.getInstance().getConnectionState();
            if (this.currentConnectionState != state) {
                FileLog.d("switch to state " + state);
                this.currentConnectionState = state;
                updateCurrentConnectionState();
            }
        } else if (id == NotificationCenter.mainUserInfoChanged) {
            this.drawerLayoutAdapter.notifyDataSetChanged();
        } else if (id == NotificationCenter.needShowAlert) {
            Integer reason = args[0];
            builder = new Builder(this);
            builder.setTitle(ApplicationLoader.getConfig().getAppName());
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
            if (reason.intValue() != 2) {
                builder.setNegativeButton(LocaleController.getString("MoreInfo", R.string.MoreInfo), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!LaunchActivity.mainFragmentsStack.isEmpty()) {
                            MessagesController.openByUserName("spambot", (BaseFragment) LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1), 1);
                        }
                    }
                });
            }
            if (reason.intValue() == 0) {
                builder.setMessage(LocaleController.getString("NobodyLikesSpam1", R.string.NobodyLikesSpam1));
            } else if (reason.intValue() == 1) {
                builder.setMessage(LocaleController.getString("NobodyLikesSpam2", R.string.NobodyLikesSpam2));
            } else if (reason.intValue() == 2) {
                builder.setMessage((String) args[1]);
            }
            if (!mainFragmentsStack.isEmpty()) {
                ((BaseFragment) mainFragmentsStack.get(mainFragmentsStack.size() - 1)).showDialog(builder.create());
            }
        } else if (id == NotificationCenter.wasUnableToFindCurrentLocation) {
            final HashMap<String, MessageObject> waitingForLocation = args[0];
            builder = new Builder(this);
            builder.setTitle(ApplicationLoader.getConfig().getAppName());
            builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), null);
            builder.setNegativeButton(LocaleController.getString("ShareYouLocationUnableManually", R.string.ShareYouLocationUnableManually), new DialogInterface.OnClickListener() {

                class C65011 implements LocationActivityDelegate {
                    C65011() {
                    }

                    public void didSelectLocation(TLRPC$MessageMedia location, int live) {
                        for (Entry<String, MessageObject> entry : waitingForLocation.entrySet()) {
                            MessageObject messageObject = (MessageObject) entry.getValue();
                            SendMessagesHelper.getInstance().sendMessage(location, messageObject.getDialogId(), messageObject, null, null);
                        }
                    }
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!LaunchActivity.mainFragmentsStack.isEmpty() && AndroidUtilities.isGoogleMapsInstalled((BaseFragment) LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1))) {
                        LocationActivity fragment = new LocationActivity(0);
                        fragment.setDelegate(new C65011());
                        LaunchActivity.this.presentFragment(fragment);
                    }
                }
            });
            builder.setMessage(LocaleController.getString("ShareYouLocationUnable", R.string.ShareYouLocationUnable));
            if (!mainFragmentsStack.isEmpty()) {
                ((BaseFragment) mainFragmentsStack.get(mainFragmentsStack.size() - 1)).showDialog(builder.create());
            }
        } else if (id == NotificationCenter.didSetNewWallpapper) {
            if (this.sideMenu != null) {
                View child = this.sideMenu.getChildAt(0);
                if (child != null) {
                    child.invalidate();
                }
            }
        } else if (id == NotificationCenter.didSetPasscode) {
            if (UserConfig.passcodeHash.length() <= 0 || UserConfig.allowScreenCapture) {
                try {
                    getWindow().clearFlags(8192);
                    return;
                } catch (Exception e) {
                    FileLog.e(e);
                    return;
                }
            }
            try {
                getWindow().setFlags(8192, 8192);
            } catch (Exception e2) {
                FileLog.e(e2);
            }
        } else if (id == NotificationCenter.reloadInterface) {
            rebuildAllFragments(true);
        } else if (id == NotificationCenter.suggestedLangpack) {
            showLanguageAlert(false);
        } else if (id == NotificationCenter.openArticle) {
            if (!mainFragmentsStack.isEmpty()) {
                ArticleViewer.getInstance().setParentActivity(this, (BaseFragment) mainFragmentsStack.get(mainFragmentsStack.size() - 1));
                ArticleViewer.getInstance().open((TLRPC$TL_webPage) args[0], (String) args[1]);
            }
        } else if (id == NotificationCenter.hasNewContactsToImport) {
            if (this.actionBarLayout != null && !this.actionBarLayout.fragmentsStack.isEmpty()) {
                int type = ((Integer) args[0]).intValue();
                final HashMap<String, ContactsController$Contact> contactHashMap = args[1];
                final boolean first = ((Boolean) args[2]).booleanValue();
                final boolean schedule = ((Boolean) args[3]).booleanValue();
                BaseFragment fragment = (BaseFragment) this.actionBarLayout.fragmentsStack.get(this.actionBarLayout.fragmentsStack.size() - 1);
                builder = new Builder(this);
                builder.setTitle(LocaleController.getString("UpdateContactsTitle", R.string.UpdateContactsTitle));
                builder.setMessage(LocaleController.formatString("UpdateContactsMessage", R.string.UpdateContactsMessage, new Object[]{ApplicationLoader.getConfig().getAppName()}));
                builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContactsController.getInstance().syncPhoneBookByAlert(contactHashMap, first, schedule, false);
                    }
                });
                builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ContactsController.getInstance().syncPhoneBookByAlert(contactHashMap, first, schedule, true);
                    }
                });
                builder.setOnBackButtonListener(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContactsController.getInstance().syncPhoneBookByAlert(contactHashMap, first, schedule, true);
                    }
                });
                AlertDialog dialog = builder.create();
                fragment.showDialog(dialog);
                dialog.setCanceledOnTouchOutside(false);
            }
        } else if (id == NotificationCenter.didSetNewTheme) {
            if (this.sideMenu != null) {
                this.sideMenu.setBackgroundColor(Theme.getColor(Theme.key_chats_menuBackground));
                this.sideMenu.setGlowColor(Theme.getColor(Theme.key_chats_menuBackground));
                this.sideMenu.getAdapter().notifyDataSetChanged();
            }
            if (VERSION.SDK_INT >= 21) {
                try {
                    setTaskDescription(new TaskDescription(null, null, Theme.getColor(Theme.key_actionBarDefault) | -16777216));
                } catch (Exception e3) {
                }
            }
        }
    }

    private String getStringForLanguageAlert(HashMap<String, String> map, String key, int intKey) {
        String value = (String) map.get(key);
        if (value == null) {
            return LocaleController.getString(key, intKey);
        }
        return value;
    }

    private void checkFreeDiscSpace() {
        if (VERSION.SDK_INT < 26) {
            Utilities.globalQueue.postRunnable(new Runnable() {

                class C65031 implements Runnable {
                    C65031() {
                    }

                    public void run() {
                        try {
                            AlertsCreator.createFreeSpaceDialog(LaunchActivity.this).show();
                        } catch (Throwable th) {
                        }
                    }
                }

                public void run() {
                    if (UserConfig.isClientActivated()) {
                        try {
                            SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
                            if (Math.abs(preferences.getLong("last_space_check", 0) - System.currentTimeMillis()) >= 259200000) {
                                File path = FileLoader.getInstance().getDirectory(4);
                                if (path != null) {
                                    long freeSpace;
                                    StatFs statFs = new StatFs(path.getAbsolutePath());
                                    if (VERSION.SDK_INT < 18) {
                                        freeSpace = (long) Math.abs(statFs.getAvailableBlocks() * statFs.getBlockSize());
                                    } else {
                                        freeSpace = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
                                    }
                                    preferences.edit().putLong("last_space_check", System.currentTimeMillis()).commit();
                                    if (freeSpace < 104857600) {
                                        AndroidUtilities.runOnUIThread(new C65031());
                                    }
                                }
                            }
                        } catch (Throwable th) {
                        }
                    }
                }
            }, Reveal.CHECK_DELAY);
        }
    }

    private void showLanguageAlertInternal(LocaleController$LocaleInfo systemInfo, LocaleController$LocaleInfo englishInfo, String systemLang) {
        try {
            LocaleController$LocaleInfo localeController$LocaleInfo;
            this.loadingLocaleDialog = false;
            boolean firstSystem = systemInfo.builtIn || LocaleController.getInstance().isCurrentLocalLocale();
            Builder builder = new Builder(this);
            builder.setTitle(getStringForLanguageAlert(this.systemLocaleStrings, "ChooseYourLanguage", R.string.ChooseYourLanguage));
            builder.setSubtitle(getStringForLanguageAlert(this.englishLocaleStrings, "ChooseYourLanguage", R.string.ChooseYourLanguage));
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(1);
            final LanguageCell[] cells = new LanguageCell[2];
            final LocaleController$LocaleInfo[] selectedLanguage = new LocaleController$LocaleInfo[1];
            LocaleController$LocaleInfo[] locales = new LocaleController$LocaleInfo[2];
            String englishName = getStringForLanguageAlert(this.systemLocaleStrings, "English", R.string.English);
            if (firstSystem) {
                localeController$LocaleInfo = systemInfo;
            } else {
                localeController$LocaleInfo = englishInfo;
            }
            locales[0] = localeController$LocaleInfo;
            if (firstSystem) {
                localeController$LocaleInfo = englishInfo;
            } else {
                localeController$LocaleInfo = systemInfo;
            }
            locales[1] = localeController$LocaleInfo;
            if (!firstSystem) {
                systemInfo = englishInfo;
            }
            selectedLanguage[0] = systemInfo;
            int a = 0;
            while (a < 2) {
                cells[a] = new LanguageCell(this, true);
                cells[a].setLanguage(locales[a], locales[a] == englishInfo ? englishName : null, true);
                cells[a].setTag(Integer.valueOf(a));
                cells[a].setBackgroundDrawable(Theme.createSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector), 2));
                cells[a].setLanguageSelected(a == 0);
                linearLayout.addView(cells[a], LayoutHelper.createLinear(-1, 48));
                cells[a].setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Integer tag = (Integer) v.getTag();
                        selectedLanguage[0] = ((LanguageCell) v).getCurrentLocale();
                        for (int a = 0; a < cells.length; a++) {
                            boolean z;
                            LanguageCell languageCell = cells[a];
                            if (a == tag.intValue()) {
                                z = true;
                            } else {
                                z = false;
                            }
                            languageCell.setLanguageSelected(z);
                        }
                    }
                });
                a++;
            }
            LanguageCell cell = new LanguageCell(this, true);
            cell.setValue(getStringForLanguageAlert(this.systemLocaleStrings, "ChooseYourLanguageOther", R.string.ChooseYourLanguageOther), getStringForLanguageAlert(this.englishLocaleStrings, "ChooseYourLanguageOther", R.string.ChooseYourLanguageOther));
            cell.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    LaunchActivity.this.localeDialog = null;
                    LaunchActivity.this.drawerLayoutContainer.closeDrawer(true);
                    LaunchActivity.this.presentFragment(new LanguageSelectActivity());
                    if (LaunchActivity.this.visibleDialog != null) {
                        LaunchActivity.this.visibleDialog.dismiss();
                        LaunchActivity.this.visibleDialog = null;
                    }
                }
            });
            linearLayout.addView(cell, LayoutHelper.createLinear(-1, 48));
            builder.setView(linearLayout);
            builder.setNegativeButton(LocaleController.getString("OK", R.string.OK), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    LocaleController.getInstance().applyLanguage(selectedLanguage[0], true, false);
                    LaunchActivity.this.rebuildAllFragments(true);
                }
            });
            this.localeDialog = showAlertDialog(builder);
            ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit().putString("language_showed2", systemLang).commit();
        } catch (Exception e) {
            FileLog.e(e);
        }
    }

    private void showLanguageAlert(boolean force) {
        try {
            if (!this.loadingLocaleDialog) {
                String showedLang = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).getString("language_showed2", "");
                final String systemLang = LocaleController.getSystemLocaleStringIso639().toLowerCase();
                if (force || !showedLang.equals(systemLang)) {
                    String arg;
                    final LocaleController$LocaleInfo[] infos = new LocaleController$LocaleInfo[2];
                    if (systemLang.contains("-")) {
                        arg = systemLang.split("-")[0];
                    } else {
                        arg = systemLang;
                    }
                    String alias;
                    if ("in".equals(arg)) {
                        alias = "id";
                    } else if ("iw".equals(arg)) {
                        alias = "he";
                    } else if ("jw".equals(arg)) {
                        alias = "jv";
                    } else {
                        alias = null;
                    }
                    for (int a = 0; a < LocaleController.getInstance().languages.size(); a++) {
                        LocaleController$LocaleInfo info = (LocaleController$LocaleInfo) LocaleController.getInstance().languages.get(a);
                        if (info.shortName.equals("en")) {
                            infos[0] = info;
                        }
                        if (info.shortName.replace(BridgeUtil.UNDERLINE_STR, "-").equals(systemLang) || info.shortName.equals(arg) || (alias != null && info.shortName.equals(alias))) {
                            infos[1] = info;
                        }
                        if (infos[0] != null && infos[1] != null) {
                            break;
                        }
                    }
                    if (infos[0] != null && infos[1] != null && infos[0] != infos[1]) {
                        FileLog.d("show lang alert for " + infos[0].getKey() + " and " + infos[1].getKey());
                        this.systemLocaleStrings = null;
                        this.englishLocaleStrings = null;
                        this.loadingLocaleDialog = true;
                        TLRPC$TL_langpack_getStrings req = new TLRPC$TL_langpack_getStrings();
                        req.lang_code = infos[1].shortName.replace(BridgeUtil.UNDERLINE_STR, "-");
                        req.keys.add("English");
                        req.keys.add("ChooseYourLanguage");
                        req.keys.add("ChooseYourLanguageOther");
                        req.keys.add("ChangeLanguageLater");
                        ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
                            public void run(TLObject response, TLRPC$TL_error error) {
                                final HashMap<String, String> keys = new HashMap();
                                if (response != null) {
                                    TLRPC$Vector vector = (TLRPC$Vector) response;
                                    for (int a = 0; a < vector.objects.size(); a++) {
                                        TLRPC$LangPackString string = (TLRPC$LangPackString) vector.objects.get(a);
                                        keys.put(string.key, string.value);
                                    }
                                }
                                AndroidUtilities.runOnUIThread(new Runnable() {
                                    public void run() {
                                        LaunchActivity.this.systemLocaleStrings = keys;
                                        if (LaunchActivity.this.englishLocaleStrings != null && LaunchActivity.this.systemLocaleStrings != null) {
                                            LaunchActivity.this.showLanguageAlertInternal(infos[1], infos[0], systemLang);
                                        }
                                    }
                                });
                            }
                        }, 8);
                        req = new TLRPC$TL_langpack_getStrings();
                        req.lang_code = infos[0].shortName.replace(BridgeUtil.UNDERLINE_STR, "-");
                        req.keys.add("English");
                        req.keys.add("ChooseYourLanguage");
                        req.keys.add("ChooseYourLanguageOther");
                        req.keys.add("ChangeLanguageLater");
                        ConnectionsManager.getInstance().sendRequest(req, new RequestDelegate() {
                            public void run(TLObject response, TLRPC$TL_error error) {
                                final HashMap<String, String> keys = new HashMap();
                                if (response != null) {
                                    TLRPC$Vector vector = (TLRPC$Vector) response;
                                    for (int a = 0; a < vector.objects.size(); a++) {
                                        TLRPC$LangPackString string = (TLRPC$LangPackString) vector.objects.get(a);
                                        keys.put(string.key, string.value);
                                    }
                                }
                                AndroidUtilities.runOnUIThread(new Runnable() {
                                    public void run() {
                                        LaunchActivity.this.englishLocaleStrings = keys;
                                        if (LaunchActivity.this.englishLocaleStrings != null && LaunchActivity.this.systemLocaleStrings != null) {
                                            LaunchActivity.this.showLanguageAlertInternal(infos[1], infos[0], systemLang);
                                        }
                                    }
                                });
                            }
                        }, 8);
                        return;
                    }
                    return;
                }
                FileLog.d("alert already showed for " + showedLang);
            }
        } catch (Exception e) {
            FileLog.e(e);
        }
    }

    private void onPasscodePause() {
        if (this.lockRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(this.lockRunnable);
            this.lockRunnable = null;
        }
        if (UserConfig.passcodeHash.length() != 0) {
            UserConfig.lastPauseTime = ConnectionsManager.getInstance().getCurrentTime();
            this.lockRunnable = new Runnable() {
                public void run() {
                    if (LaunchActivity.this.lockRunnable == this) {
                        if (AndroidUtilities.needShowPasscode(true)) {
                            FileLog.e("lock app");
                            LaunchActivity.this.showPasscodeActivity();
                        } else {
                            FileLog.e("didn't pass lock check");
                        }
                        LaunchActivity.this.lockRunnable = null;
                    }
                }
            };
            if (UserConfig.appLocked) {
                AndroidUtilities.runOnUIThread(this.lockRunnable, 1000);
            } else if (UserConfig.autoLockIn != 0) {
                AndroidUtilities.runOnUIThread(this.lockRunnable, (((long) UserConfig.autoLockIn) * 1000) + 1000);
            }
        } else {
            UserConfig.lastPauseTime = 0;
        }
        UserConfig.saveConfig(false);
    }

    private void onPasscodeResume() {
        if (this.lockRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(this.lockRunnable);
            this.lockRunnable = null;
        }
        if (AndroidUtilities.needShowPasscode(true)) {
            showPasscodeActivity();
        }
        if (UserConfig.lastPauseTime != 0) {
            UserConfig.lastPauseTime = 0;
            UserConfig.saveConfig(false);
        }
    }

    private void updateCurrentConnectionState() {
        String title = null;
        String subtitle = null;
        Runnable action = null;
        if (this.currentConnectionState == 2) {
            title = LocaleController.getString("WaitingForNetwork", R.string.WaitingForNetwork);
        } else if (this.currentConnectionState == 1) {
            title = LocaleController.getString("Connecting", R.string.Connecting);
            action = new Runnable() {
                public void run() {
                    if (AndroidUtilities.isTablet()) {
                        if (!LaunchActivity.layerFragmentsStack.isEmpty() && (LaunchActivity.layerFragmentsStack.get(LaunchActivity.layerFragmentsStack.size() - 1) instanceof ProxySettingsActivity)) {
                            return;
                        }
                    } else if (!LaunchActivity.mainFragmentsStack.isEmpty() && (LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1) instanceof ProxySettingsActivity)) {
                        return;
                    }
                    LaunchActivity.this.presentFragment(new ProxySettingsActivity());
                }
            };
        } else if (this.currentConnectionState == 5) {
            title = LocaleController.getString("Updating", R.string.Updating);
        } else if (this.currentConnectionState == 4) {
            title = LocaleController.getString("ConnectingToProxy", R.string.ConnectingToProxy);
            subtitle = LocaleController.getString("ConnectingToProxyTapToDisable", R.string.ConnectingToProxyTapToDisable);
            action = new Runnable() {

                class C65071 implements DialogInterface.OnClickListener {
                    C65071() {
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        Editor editor = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0).edit();
                        editor.putBoolean("proxy_enabled", false);
                        editor.commit();
                        ConnectionsManager.native_setProxySettings("", 0, "", "");
                        NotificationCenter.getInstance().postNotificationName(NotificationCenter.proxySettingsChanged, new Object[0]);
                    }
                }

                public void run() {
                    if (LaunchActivity.this.actionBarLayout != null && !LaunchActivity.this.actionBarLayout.fragmentsStack.isEmpty()) {
                        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
                        BaseFragment fragment = (BaseFragment) LaunchActivity.this.actionBarLayout.fragmentsStack.get(LaunchActivity.this.actionBarLayout.fragmentsStack.size() - 1);
                        Builder builder = new Builder(LaunchActivity.this);
                        builder.setTitle(LocaleController.getString("Proxy", R.string.Proxy));
                        builder.setMessage(LocaleController.formatString("ConnectingToProxyDisableAlert", R.string.ConnectingToProxyDisableAlert, new Object[]{preferences.getString("proxy_ip", "")}));
                        builder.setPositiveButton(LocaleController.getString("ConnectingToProxyDisable", R.string.ConnectingToProxyDisable), new C65071());
                        builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                        fragment.showDialog(builder.create());
                    }
                }
            };
        }
        this.actionBarLayout.setTitleOverlayText(title, subtitle, action);
    }

    protected void onSaveInstanceState(Bundle outState) {
        try {
            super.onSaveInstanceState(outState);
            BaseFragment lastFragment = null;
            if (AndroidUtilities.isTablet()) {
                if (!this.layersActionBarLayout.fragmentsStack.isEmpty()) {
                    lastFragment = (BaseFragment) this.layersActionBarLayout.fragmentsStack.get(this.layersActionBarLayout.fragmentsStack.size() - 1);
                } else if (!this.rightActionBarLayout.fragmentsStack.isEmpty()) {
                    lastFragment = (BaseFragment) this.rightActionBarLayout.fragmentsStack.get(this.rightActionBarLayout.fragmentsStack.size() - 1);
                } else if (!this.actionBarLayout.fragmentsStack.isEmpty()) {
                    lastFragment = (BaseFragment) this.actionBarLayout.fragmentsStack.get(this.actionBarLayout.fragmentsStack.size() - 1);
                }
            } else if (!this.actionBarLayout.fragmentsStack.isEmpty()) {
                lastFragment = (BaseFragment) this.actionBarLayout.fragmentsStack.get(this.actionBarLayout.fragmentsStack.size() - 1);
            }
            if (lastFragment != null) {
                Bundle args = lastFragment.getArguments();
                if ((lastFragment instanceof ChatActivity) && args != null) {
                    outState.putBundle("args", args);
                    outState.putString("fragment", "chat");
                } else if (lastFragment instanceof SettingsActivity) {
                    outState.putString("fragment", C1404b.f2107K);
                } else if ((lastFragment instanceof GroupCreateFinalActivity) && args != null) {
                    outState.putBundle("args", args);
                    outState.putString("fragment", "group");
                } else if (lastFragment instanceof WallpapersActivity) {
                    outState.putString("fragment", "wallpapers");
                } else if ((lastFragment instanceof ProfileActivity) && ((ProfileActivity) lastFragment).isChat() && args != null) {
                    outState.putBundle("args", args);
                    outState.putString("fragment", "chat_profile");
                } else if ((lastFragment instanceof ChannelCreateActivity) && args != null && args.getInt("step") == 0) {
                    outState.putBundle("args", args);
                    outState.putString("fragment", "channel");
                } else if ((lastFragment instanceof ChannelEditActivity) && args != null) {
                    outState.putBundle("args", args);
                    outState.putString("fragment", "edit");
                }
                lastFragment.saveSelfArgs(outState);
            }
        } catch (Exception e) {
            FileLog.e(e);
        }
    }

    public void onBackPressed() {
        if (this.passcodeView.getVisibility() == 0) {
            finish();
        } else if (SecretMediaViewer.getInstance().isVisible()) {
            SecretMediaViewer.getInstance().closePhoto(true, false);
        } else if (PhotoViewer.getInstance().isVisible()) {
            PhotoViewer.getInstance().closePhoto(true, false);
        } else if (ArticleViewer.getInstance().isVisible()) {
            ArticleViewer.getInstance().close(true, false);
        } else if (this.drawerLayoutContainer.isDrawerOpened()) {
            this.drawerLayoutContainer.closeDrawer(false);
        } else if (!AndroidUtilities.isTablet()) {
            this.actionBarLayout.onBackPressed();
        } else if (this.layersActionBarLayout.getVisibility() == 0) {
            this.layersActionBarLayout.onBackPressed();
        } else {
            boolean cancel = false;
            if (this.rightActionBarLayout.getVisibility() == 0 && !this.rightActionBarLayout.fragmentsStack.isEmpty()) {
                cancel = !((BaseFragment) this.rightActionBarLayout.fragmentsStack.get(this.rightActionBarLayout.fragmentsStack.size() + -1)).onBackPressed();
            }
            if (!cancel) {
                this.actionBarLayout.onBackPressed();
            }
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.actionBarLayout.onLowMemory();
        if (AndroidUtilities.isTablet()) {
            this.rightActionBarLayout.onLowMemory();
            this.layersActionBarLayout.onLowMemory();
        }
    }

    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        try {
            Menu menu = mode.getMenu();
            if (!(menu == null || this.actionBarLayout.extendActionMode(menu) || !AndroidUtilities.isTablet() || this.rightActionBarLayout.extendActionMode(menu))) {
                this.layersActionBarLayout.extendActionMode(menu);
            }
        } catch (Exception e) {
            FileLog.e(e);
        }
        if (VERSION.SDK_INT < 23 || mode.getType() != 1) {
            this.actionBarLayout.onActionModeStarted(mode);
            if (AndroidUtilities.isTablet()) {
                this.rightActionBarLayout.onActionModeStarted(mode);
                this.layersActionBarLayout.onActionModeStarted(mode);
            }
        }
    }

    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        if (VERSION.SDK_INT < 23 || mode.getType() != 1) {
            this.actionBarLayout.onActionModeFinished(mode);
            if (AndroidUtilities.isTablet()) {
                this.rightActionBarLayout.onActionModeFinished(mode);
                this.layersActionBarLayout.onActionModeFinished(mode);
            }
        }
    }

    public boolean onPreIme() {
        if (SecretMediaViewer.getInstance().isVisible()) {
            SecretMediaViewer.getInstance().closePhoto(true, false);
            return true;
        } else if (PhotoViewer.getInstance().isVisible()) {
            PhotoViewer.getInstance().closePhoto(true, false);
            return true;
        } else if (!ArticleViewer.getInstance().isVisible()) {
            return false;
        } else {
            ArticleViewer.getInstance().close(true, false);
            return true;
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 82 && !UserConfig.isWaitingForPasscodeEnter) {
            if (PhotoViewer.getInstance().isVisible()) {
                return super.onKeyUp(keyCode, event);
            }
            if (ArticleViewer.getInstance().isVisible()) {
                return super.onKeyUp(keyCode, event);
            }
            if (AndroidUtilities.isTablet()) {
                if (this.layersActionBarLayout.getVisibility() == 0 && !this.layersActionBarLayout.fragmentsStack.isEmpty()) {
                    this.layersActionBarLayout.onKeyUp(keyCode, event);
                } else if (this.rightActionBarLayout.getVisibility() != 0 || this.rightActionBarLayout.fragmentsStack.isEmpty()) {
                    this.actionBarLayout.onKeyUp(keyCode, event);
                } else {
                    this.rightActionBarLayout.onKeyUp(keyCode, event);
                }
            } else if (this.actionBarLayout.fragmentsStack.size() != 1) {
                this.actionBarLayout.onKeyUp(keyCode, event);
            } else if (this.drawerLayoutContainer.isDrawerOpened()) {
                this.drawerLayoutContainer.closeDrawer(false);
            } else {
                if (getCurrentFocus() != null) {
                    AndroidUtilities.hideKeyboard(getCurrentFocus());
                }
                this.drawerLayoutContainer.openDrawer(false);
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    public boolean needPresentFragment(BaseFragment fragment, boolean removeLast, boolean forceWithoutAnimation, ActionBarLayout layout) {
        boolean z = true;
        if (ArticleViewer.getInstance().isVisible()) {
            ArticleViewer.getInstance().close(false, true);
        }
        boolean z2;
        if (AndroidUtilities.isTablet()) {
            DrawerLayoutContainer drawerLayoutContainer = this.drawerLayoutContainer;
            z2 = ((fragment instanceof LoginActivity) || (fragment instanceof CountrySelectActivity) || this.layersActionBarLayout.getVisibility() == 0) ? false : true;
            drawerLayoutContainer.setAllowOpenDrawer(z2, true);
            if ((fragment instanceof DialogsActivity) && ((DialogsActivity) fragment).isMainDialogList() && layout != this.actionBarLayout) {
                this.actionBarLayout.removeAllFragments();
                this.actionBarLayout.presentFragment(fragment, removeLast, forceWithoutAnimation, false);
                this.layersActionBarLayout.removeAllFragments();
                this.layersActionBarLayout.setVisibility(8);
                this.drawerLayoutContainer.setAllowOpenDrawer(true, false);
                if (this.tabletFullSize) {
                    return false;
                }
                this.shadowTabletSide.setVisibility(0);
                if (!this.rightActionBarLayout.fragmentsStack.isEmpty()) {
                    return false;
                }
                this.backgroundTablet.setVisibility(0);
                return false;
            } else if (fragment instanceof ChatActivity) {
                int a;
                ActionBarLayout actionBarLayout;
                if ((!this.tabletFullSize && layout == this.rightActionBarLayout) || (this.tabletFullSize && layout == this.actionBarLayout)) {
                    boolean result;
                    if (this.tabletFullSize && layout == this.actionBarLayout && this.actionBarLayout.fragmentsStack.size() == 1) {
                        result = false;
                    } else {
                        result = true;
                    }
                    if (!this.layersActionBarLayout.fragmentsStack.isEmpty()) {
                        a = 0;
                        while (this.layersActionBarLayout.fragmentsStack.size() - 1 > 0) {
                            this.layersActionBarLayout.removeFragmentFromStack((BaseFragment) this.layersActionBarLayout.fragmentsStack.get(0));
                            a = (a - 1) + 1;
                        }
                        actionBarLayout = this.layersActionBarLayout;
                        if (forceWithoutAnimation) {
                            z = false;
                        }
                        actionBarLayout.closeLastFragment(z);
                    }
                    if (!result) {
                        this.actionBarLayout.presentFragment(fragment, false, forceWithoutAnimation, false);
                    }
                    return result;
                } else if (!this.tabletFullSize && layout != this.rightActionBarLayout) {
                    this.rightActionBarLayout.setVisibility(0);
                    this.backgroundTablet.setVisibility(8);
                    this.rightActionBarLayout.removeAllFragments();
                    this.rightActionBarLayout.presentFragment(fragment, removeLast, true, false);
                    if (this.layersActionBarLayout.fragmentsStack.isEmpty()) {
                        return false;
                    }
                    a = 0;
                    while (this.layersActionBarLayout.fragmentsStack.size() - 1 > 0) {
                        this.layersActionBarLayout.removeFragmentFromStack((BaseFragment) this.layersActionBarLayout.fragmentsStack.get(0));
                        a = (a - 1) + 1;
                    }
                    actionBarLayout = this.layersActionBarLayout;
                    if (forceWithoutAnimation) {
                        z = false;
                    }
                    actionBarLayout.closeLastFragment(z);
                    return false;
                } else if (!this.tabletFullSize || layout == this.actionBarLayout) {
                    if (!this.layersActionBarLayout.fragmentsStack.isEmpty()) {
                        a = 0;
                        while (this.layersActionBarLayout.fragmentsStack.size() - 1 > 0) {
                            this.layersActionBarLayout.removeFragmentFromStack((BaseFragment) this.layersActionBarLayout.fragmentsStack.get(0));
                            a = (a - 1) + 1;
                        }
                        r6 = this.layersActionBarLayout;
                        if (forceWithoutAnimation) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        r6.closeLastFragment(z2);
                    }
                    actionBarLayout = this.actionBarLayout;
                    if (this.actionBarLayout.fragmentsStack.size() <= 1) {
                        z = false;
                    }
                    actionBarLayout.presentFragment(fragment, z, forceWithoutAnimation, false);
                    return false;
                } else {
                    r6 = this.actionBarLayout;
                    if (this.actionBarLayout.fragmentsStack.size() > 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    r6.presentFragment(fragment, z2, forceWithoutAnimation, false);
                    if (this.layersActionBarLayout.fragmentsStack.isEmpty()) {
                        return false;
                    }
                    a = 0;
                    while (this.layersActionBarLayout.fragmentsStack.size() - 1 > 0) {
                        this.layersActionBarLayout.removeFragmentFromStack((BaseFragment) this.layersActionBarLayout.fragmentsStack.get(0));
                        a = (a - 1) + 1;
                    }
                    actionBarLayout = this.layersActionBarLayout;
                    if (forceWithoutAnimation) {
                        z = false;
                    }
                    actionBarLayout.closeLastFragment(z);
                    return false;
                }
            } else if (layout == this.layersActionBarLayout) {
                return true;
            } else {
                this.layersActionBarLayout.setVisibility(0);
                this.drawerLayoutContainer.setAllowOpenDrawer(false, true);
                if (fragment instanceof LoginActivity) {
                    this.backgroundTablet.setVisibility(0);
                    this.shadowTabletSide.setVisibility(8);
                    this.shadowTablet.setBackgroundColor(0);
                } else {
                    this.shadowTablet.setBackgroundColor(Theme.ACTION_BAR_PHOTO_VIEWER_COLOR);
                }
                this.layersActionBarLayout.presentFragment(fragment, removeLast, forceWithoutAnimation, false);
                return false;
            }
        }
        drawerLayoutContainer = this.drawerLayoutContainer;
        if ((fragment instanceof LoginActivity) || (fragment instanceof CountrySelectActivity)) {
            z2 = false;
        } else {
            z2 = true;
        }
        drawerLayoutContainer.setAllowOpenDrawer(z2, false);
        return true;
    }

    public boolean needAddFragmentToStack(BaseFragment fragment, ActionBarLayout layout) {
        if (AndroidUtilities.isTablet()) {
            DrawerLayoutContainer drawerLayoutContainer = this.drawerLayoutContainer;
            boolean z = ((fragment instanceof LoginActivity) || (fragment instanceof CountrySelectActivity) || this.layersActionBarLayout.getVisibility() == 0) ? false : true;
            drawerLayoutContainer.setAllowOpenDrawer(z, true);
            if (fragment instanceof DialogsActivity) {
                if (((DialogsActivity) fragment).isMainDialogList() && layout != this.actionBarLayout) {
                    this.actionBarLayout.removeAllFragments();
                    this.actionBarLayout.addFragmentToStack(fragment);
                    this.layersActionBarLayout.removeAllFragments();
                    this.layersActionBarLayout.setVisibility(8);
                    this.drawerLayoutContainer.setAllowOpenDrawer(true, false);
                    if (this.tabletFullSize) {
                        return false;
                    }
                    this.shadowTabletSide.setVisibility(0);
                    if (!this.rightActionBarLayout.fragmentsStack.isEmpty()) {
                        return false;
                    }
                    this.backgroundTablet.setVisibility(0);
                    return false;
                }
            } else if (fragment instanceof ChatActivity) {
                int a;
                if (!this.tabletFullSize && layout != this.rightActionBarLayout) {
                    this.rightActionBarLayout.setVisibility(0);
                    this.backgroundTablet.setVisibility(8);
                    this.rightActionBarLayout.removeAllFragments();
                    this.rightActionBarLayout.addFragmentToStack(fragment);
                    if (this.layersActionBarLayout.fragmentsStack.isEmpty()) {
                        return false;
                    }
                    a = 0;
                    while (this.layersActionBarLayout.fragmentsStack.size() - 1 > 0) {
                        this.layersActionBarLayout.removeFragmentFromStack((BaseFragment) this.layersActionBarLayout.fragmentsStack.get(0));
                        a = (a - 1) + 1;
                    }
                    this.layersActionBarLayout.closeLastFragment(true);
                    return false;
                } else if (this.tabletFullSize && layout != this.actionBarLayout) {
                    this.actionBarLayout.addFragmentToStack(fragment);
                    if (this.layersActionBarLayout.fragmentsStack.isEmpty()) {
                        return false;
                    }
                    a = 0;
                    while (this.layersActionBarLayout.fragmentsStack.size() - 1 > 0) {
                        this.layersActionBarLayout.removeFragmentFromStack((BaseFragment) this.layersActionBarLayout.fragmentsStack.get(0));
                        a = (a - 1) + 1;
                    }
                    this.layersActionBarLayout.closeLastFragment(true);
                    return false;
                }
            } else if (layout != this.layersActionBarLayout) {
                this.layersActionBarLayout.setVisibility(0);
                this.drawerLayoutContainer.setAllowOpenDrawer(false, true);
                if (fragment instanceof LoginActivity) {
                    this.backgroundTablet.setVisibility(0);
                    this.shadowTabletSide.setVisibility(8);
                    this.shadowTablet.setBackgroundColor(0);
                } else {
                    this.shadowTablet.setBackgroundColor(Theme.ACTION_BAR_PHOTO_VIEWER_COLOR);
                }
                this.layersActionBarLayout.addFragmentToStack(fragment);
                return false;
            }
            return true;
        }
        drawerLayoutContainer = this.drawerLayoutContainer;
        if ((fragment instanceof LoginActivity) || (fragment instanceof CountrySelectActivity)) {
            z = false;
        } else {
            z = true;
        }
        drawerLayoutContainer.setAllowOpenDrawer(z, false);
        return true;
    }

    public boolean needCloseLastFragment(ActionBarLayout layout) {
        if (AndroidUtilities.isTablet()) {
            if (layout == this.actionBarLayout && layout.fragmentsStack.size() <= 1) {
                onFinish();
                finish();
                return false;
            } else if (layout == this.rightActionBarLayout) {
                if (!this.tabletFullSize) {
                    this.backgroundTablet.setVisibility(0);
                }
            } else if (layout == this.layersActionBarLayout && this.actionBarLayout.fragmentsStack.isEmpty() && this.layersActionBarLayout.fragmentsStack.size() == 1) {
                onFinish();
                finish();
                return false;
            }
        } else if (layout.fragmentsStack.size() <= 1) {
            onFinish();
            finish();
            return false;
        } else if (layout.fragmentsStack.size() >= 2 && !(layout.fragmentsStack.get(0) instanceof LoginActivity)) {
            this.drawerLayoutContainer.setAllowOpenDrawer(true, false);
        }
        return true;
    }

    public void rebuildAllFragments(boolean last) {
        if (this.layersActionBarLayout != null) {
            this.layersActionBarLayout.rebuildAllFragmentViews(last, true);
        } else {
            this.actionBarLayout.rebuildAllFragmentViews(last, true);
        }
    }

    public void onRebuildAllFragments(ActionBarLayout layout) {
        if (AndroidUtilities.isTablet() && layout == this.layersActionBarLayout) {
            this.rightActionBarLayout.rebuildAllFragmentViews(true, true);
            this.actionBarLayout.rebuildAllFragmentViews(true, true);
        }
        this.drawerLayoutAdapter.notifyDataSetChanged();
    }
}
