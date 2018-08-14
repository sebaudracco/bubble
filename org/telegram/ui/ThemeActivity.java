package org.telegram.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Vibrator;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.RewardedVideoListener;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.wBubble_7453037.R;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map.Entry;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet.Builder;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.Theme.ThemeInfo;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Cells.ThemeCell;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import org.telegram.ui.Components.ThemeEditorView;

public class ThemeActivity extends BaseFragment {
    private ListAdapter listAdapter;
    private RecyclerListView listView;

    class C68851 extends ActionBarMenuOnItemClick {
        C68851() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                ThemeActivity.this.finishFragment();
            }
        }
    }

    private class ListAdapter extends SelectionAdapter {
        private Context mContext;

        class C68971 implements OnClickListener {
            C68971() {
            }

            public void onClick(View v) {
                final ThemeInfo themeInfo = ((ThemeCell) v.getParent()).getCurrentThemeInfo();
                if (ThemeActivity.this.getParentActivity() != null) {
                    Builder builder = new Builder(ThemeActivity.this.getParentActivity());
                    builder.setItems(themeInfo.pathToFile == null ? new CharSequence[]{LocaleController.getString("ShareFile", R.string.ShareFile)} : new CharSequence[]{LocaleController.getString("ShareFile", R.string.ShareFile), LocaleController.getString("Edit", R.string.Edit), LocaleController.getString("Delete", R.string.Delete)}, new DialogInterface.OnClickListener() {

                        class C68951 implements DialogInterface.OnClickListener {
                            C68951() {
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (Theme.deleteTheme(themeInfo)) {
                                    ThemeActivity.this.parentLayout.rebuildAllFragmentViews(true, true);
                                }
                                if (ThemeActivity.this.listAdapter != null) {
                                    ThemeActivity.this.listAdapter.notifyDataSetChanged();
                                }
                            }
                        }

                        public void onClick(DialogInterface dialog, int which) {
                            File currentFile;
                            Exception e;
                            File finalFile;
                            Throwable th;
                            if (which == 0) {
                                Intent intent;
                                if (themeInfo.pathToFile == null && themeInfo.assetName == null) {
                                    StringBuilder result = new StringBuilder();
                                    for (Entry<String, Integer> entry : Theme.getDefaultColors().entrySet()) {
                                        result.append((String) entry.getKey()).append("=").append(entry.getValue()).append("\n");
                                    }
                                    currentFile = new File(ApplicationLoader.getFilesDirFixed(), "default_theme.attheme");
                                    FileOutputStream stream = null;
                                    try {
                                        FileOutputStream stream2 = new FileOutputStream(currentFile);
                                        try {
                                            stream2.write(result.toString().getBytes());
                                            if (stream2 != null) {
                                                try {
                                                    stream2.close();
                                                } catch (Exception e2) {
                                                    FileLog.e("tmessage", e2);
                                                    stream = stream2;
                                                }
                                            }
                                            stream = stream2;
                                        } catch (Exception e3) {
                                            e2 = e3;
                                            stream = stream2;
                                            try {
                                                FileLog.e(e2);
                                                if (stream != null) {
                                                    try {
                                                        stream.close();
                                                    } catch (Exception e22) {
                                                        FileLog.e("tmessage", e22);
                                                    }
                                                }
                                                finalFile = new File(FileLoader.getInstance().getDirectory(4), currentFile.getName());
                                                if (!AndroidUtilities.copyFile(currentFile, finalFile)) {
                                                    intent = new Intent("android.intent.action.SEND");
                                                    intent.setType("text/xml");
                                                    if (VERSION.SDK_INT >= 24) {
                                                        try {
                                                            intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(ThemeActivity.this.getParentActivity(), "org.telegram.messenger.provider", finalFile));
                                                            intent.setFlags(1);
                                                        } catch (Exception e4) {
                                                            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(finalFile));
                                                        }
                                                    } else {
                                                        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(finalFile));
                                                    }
                                                    ThemeActivity.this.startActivityForResult(Intent.createChooser(intent, LocaleController.getString("ShareFile", R.string.ShareFile)), HttpStatus.SC_INTERNAL_SERVER_ERROR);
                                                }
                                            } catch (Throwable th2) {
                                                th = th2;
                                                if (stream != null) {
                                                    try {
                                                        stream.close();
                                                    } catch (Exception e222) {
                                                        FileLog.e("tmessage", e222);
                                                    }
                                                }
                                                throw th;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            stream = stream2;
                                            if (stream != null) {
                                                stream.close();
                                            }
                                            throw th;
                                        }
                                    } catch (Exception e5) {
                                        e222 = e5;
                                        FileLog.e(e222);
                                        if (stream != null) {
                                            stream.close();
                                        }
                                        finalFile = new File(FileLoader.getInstance().getDirectory(4), currentFile.getName());
                                        if (!AndroidUtilities.copyFile(currentFile, finalFile)) {
                                            intent = new Intent("android.intent.action.SEND");
                                            intent.setType("text/xml");
                                            if (VERSION.SDK_INT >= 24) {
                                                intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(finalFile));
                                            } else {
                                                intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(ThemeActivity.this.getParentActivity(), "org.telegram.messenger.provider", finalFile));
                                                intent.setFlags(1);
                                            }
                                            ThemeActivity.this.startActivityForResult(Intent.createChooser(intent, LocaleController.getString("ShareFile", R.string.ShareFile)), HttpStatus.SC_INTERNAL_SERVER_ERROR);
                                        }
                                    }
                                }
                                currentFile = themeInfo.assetName != null ? Theme.getAssetFile(themeInfo.assetName) : new File(themeInfo.pathToFile);
                                finalFile = new File(FileLoader.getInstance().getDirectory(4), currentFile.getName());
                                try {
                                    if (!AndroidUtilities.copyFile(currentFile, finalFile)) {
                                        intent = new Intent("android.intent.action.SEND");
                                        intent.setType("text/xml");
                                        if (VERSION.SDK_INT >= 24) {
                                            intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(ThemeActivity.this.getParentActivity(), "org.telegram.messenger.provider", finalFile));
                                            intent.setFlags(1);
                                        } else {
                                            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(finalFile));
                                        }
                                        ThemeActivity.this.startActivityForResult(Intent.createChooser(intent, LocaleController.getString("ShareFile", R.string.ShareFile)), HttpStatus.SC_INTERNAL_SERVER_ERROR);
                                    }
                                } catch (Exception e2222) {
                                    FileLog.e(e2222);
                                }
                            } else if (which == 1) {
                                if (ThemeActivity.this.parentLayout != null) {
                                    Theme.applyTheme(themeInfo);
                                    ThemeActivity.this.parentLayout.rebuildAllFragmentViews(true, true);
                                    new ThemeEditorView().show(ThemeActivity.this.getParentActivity(), themeInfo.name);
                                }
                            } else if (ThemeActivity.this.getParentActivity() != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ThemeActivity.this.getParentActivity());
                                builder.setMessage(LocaleController.getString("DeleteThemeAlert", R.string.DeleteThemeAlert));
                                builder.setTitle(ApplicationLoader.getConfig().getAppName());
                                builder.setPositiveButton(LocaleController.getString("Delete", R.string.Delete), new C68951());
                                builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                                ThemeActivity.this.showDialog(builder.create());
                            }
                        }
                    });
                    ThemeActivity.this.showDialog(builder.create());
                }
            }
        }

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public int getItemCount() {
            return Theme.themes.size() + 3;
        }

        public boolean isEnabled(ViewHolder holder) {
            int type = holder.getItemViewType();
            if (type == 0 || type == 1) {
                return true;
            }
            return false;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            switch (viewType) {
                case 0:
                    view = new ThemeCell(this.mContext);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    ((ThemeCell) view).setOnOptionsClick(new C68971());
                    break;
                case 1:
                    view = new TextSettingsCell(this.mContext);
                    ((TextSettingsCell) view).setText(LocaleController.getString("CreateNewTheme", R.string.CreateNewTheme), false);
                    view.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
                    break;
                case 2:
                    view = new TextInfoPrivacyCell(this.mContext);
                    ((TextInfoPrivacyCell) view).setText(LocaleController.formatString("CreateNewThemeInfo", R.string.CreateNewThemeInfo, new Object[]{ApplicationLoader.getConfig().getAppName()}));
                    view.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
                    break;
                default:
                    view = new ShadowSectionCell(this.mContext);
                    view.setBackgroundDrawable(Theme.getThemedDrawable(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            if (holder.getItemViewType() == 0) {
                position -= 2;
                ((ThemeCell) holder.itemView).setTheme((ThemeInfo) Theme.themes.get(position), position != Theme.themes.size() + -1);
            }
        }

        public int getItemViewType(int i) {
            if (i == 0) {
                return 1;
            }
            if (i == 1) {
                return 2;
            }
            if (i == Theme.themes.size() + 2) {
                return 3;
            }
            return 0;
        }
    }

    public View createView(final Context context) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setTitle(LocaleController.getString("Theme", R.string.Theme));
        this.actionBar.setActionBarMenuOnItemClick(new C68851());
        this.listAdapter = new ListAdapter(context);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.fragmentView = frameLayout;
        this.listView = new RecyclerListView(context);
        this.listView.setLayoutManager(new LinearLayoutManager(context, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setAdapter(this.listAdapter);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnItemClickListener(new OnItemClickListener() {

            class C68861 implements DialogInterface.OnClickListener {
                C68861() {
                }

                public void onClick(DialogInterface dialog, int which) {
                }
            }

            class C68872 implements OnEditorActionListener {
                C68872() {
                }

                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    AndroidUtilities.hideKeyboard(textView);
                    return false;
                }
            }

            class C68936 implements DialogInterface.OnClickListener {
                C68936() {
                }

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }

            public void onItemClick(View view, int position) {
                AlertDialog.Builder builder;
                if (position != 0) {
                    position -= 2;
                    if (position >= 0 && position < Theme.themes.size()) {
                        builder = new AlertDialog.Builder(context);
                        builder.setMessage(context.getString(R.string.RewardedVideoTheme));
                        final int finalPosition = position;
                        builder.setPositiveButton(context.getString(17039370), new DialogInterface.OnClickListener() {

                            class C68911 implements RewardedVideoListener {
                                boolean isVideoFinished;

                                C68911() {
                                }

                                public void onVideoOpened() {
                                }

                                public void onVideoClicked() {
                                }

                                public void onVideoClosed() {
                                    if (this.isVideoFinished) {
                                        Theme.applyTheme((ThemeInfo) Theme.themes.get(finalPosition));
                                        if (ThemeActivity.this.parentLayout != null) {
                                            ThemeActivity.this.parentLayout.rebuildAllFragmentViews(false, false);
                                        }
                                        ThemeActivity.this.finishFragment();
                                    }
                                }

                                public void onVideoError(String s) {
                                    Log.w("rewarded video error", s);
                                    Theme.applyTheme((ThemeInfo) Theme.themes.get(finalPosition));
                                    if (ThemeActivity.this.parentLayout != null) {
                                        ThemeActivity.this.parentLayout.rebuildAllFragmentViews(false, false);
                                    }
                                    ThemeActivity.this.finishFragment();
                                }

                                public void onVideoFinished() {
                                    this.isVideoFinished = true;
                                }
                            }

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                AppsgeyserSDK.getFastTrackAdsController().showRewardedVideo(new C68911());
                            }
                        });
                        builder.setNegativeButton(context.getString(17039360), new C68936());
                        builder.show();
                    }
                } else if (ThemeActivity.this.getParentActivity() != null) {
                    final EditTextBoldCursor editText = new EditTextBoldCursor(ThemeActivity.this.getParentActivity());
                    editText.setBackgroundDrawable(Theme.createEditTextDrawable(ThemeActivity.this.getParentActivity(), true));
                    builder = new AlertDialog.Builder(ThemeActivity.this.getParentActivity());
                    builder.setTitle(LocaleController.getString("NewTheme", R.string.NewTheme));
                    builder.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
                    builder.setPositiveButton(LocaleController.getString("OK", R.string.OK), new C68861());
                    LinearLayout linearLayout = new LinearLayout(ThemeActivity.this.getParentActivity());
                    linearLayout.setOrientation(1);
                    builder.setView(linearLayout);
                    TextView message = new TextView(ThemeActivity.this.getParentActivity());
                    message.setText(LocaleController.formatString("EnterThemeName", R.string.EnterThemeName, new Object[0]));
                    message.setTextSize(16.0f);
                    message.setPadding(AndroidUtilities.dp(23.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(23.0f), AndroidUtilities.dp(6.0f));
                    message.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
                    linearLayout.addView(message, LayoutHelper.createLinear(-1, -2));
                    editText.setTextSize(1, 16.0f);
                    editText.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
                    editText.setMaxLines(1);
                    editText.setLines(1);
                    editText.setInputType(16385);
                    editText.setGravity(51);
                    editText.setSingleLine(true);
                    editText.setImeOptions(6);
                    editText.setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
                    editText.setCursorSize(AndroidUtilities.dp(CloseButton.TEXT_SIZE_SP));
                    editText.setCursorWidth(1.5f);
                    editText.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
                    linearLayout.addView(editText, LayoutHelper.createLinear(-1, 36, 51, 24, 6, 24, 0));
                    editText.setOnEditorActionListener(new C68872());
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.setOnShowListener(new OnShowListener() {

                        class C68881 implements Runnable {
                            C68881() {
                            }

                            public void run() {
                                editText.requestFocus();
                                AndroidUtilities.showKeyboard(editText);
                            }
                        }

                        public void onShow(DialogInterface dialog) {
                            AndroidUtilities.runOnUIThread(new C68881());
                        }
                    });
                    ThemeActivity.this.showDialog(alertDialog);
                    alertDialog.getButton(-1).setOnClickListener(new OnClickListener() {
                        public void onClick(View v) {
                            if (editText.length() == 0) {
                                Vibrator vibrator = (Vibrator) ApplicationLoader.applicationContext.getSystemService("vibrator");
                                if (vibrator != null) {
                                    vibrator.vibrate(200);
                                }
                                AndroidUtilities.shakeView(editText, 2.0f, 0);
                                return;
                            }
                            ThemeEditorView themeEditorView = new ThemeEditorView();
                            String name = editText.getText().toString() + ".attheme";
                            themeEditorView.show(ThemeActivity.this.getParentActivity(), name);
                            Theme.saveCurrentTheme(name, true);
                            ThemeActivity.this.listAdapter.notifyDataSetChanged();
                            alertDialog.dismiss();
                            SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
                            if (!preferences.getBoolean("themehint", false)) {
                                preferences.edit().putBoolean("themehint", true).commit();
                                try {
                                    Toast.makeText(ThemeActivity.this.getParentActivity(), LocaleController.getString("CreateNewThemeHelp", R.string.CreateNewThemeHelp), 1).show();
                                } catch (Exception e) {
                                    FileLog.e(e);
                                }
                            }
                        }
                    });
                }
            }
        });
        return this.fragmentView;
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }

    public ThemeDescription[] getThemeDescriptions() {
        ThemeDescription[] themeDescriptionArr = new ThemeDescription[15];
        themeDescriptionArr[0] = new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite);
        themeDescriptionArr[1] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault);
        themeDescriptionArr[2] = new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault);
        themeDescriptionArr[3] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon);
        themeDescriptionArr[4] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle);
        themeDescriptionArr[5] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector);
        themeDescriptionArr[6] = new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector);
        themeDescriptionArr[7] = new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider);
        themeDescriptionArr[8] = new ThemeDescription(this.listView, 0, new Class[]{ThemeCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText);
        themeDescriptionArr[9] = new ThemeDescription(this.listView, 0, new Class[]{ThemeCell.class}, new String[]{"checkImage"}, null, null, null, Theme.key_featuredStickers_addedIcon);
        themeDescriptionArr[10] = new ThemeDescription(this.listView, 0, new Class[]{ThemeCell.class}, new String[]{"optionsButton"}, null, null, null, Theme.key_stickers_menu);
        themeDescriptionArr[11] = new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow);
        themeDescriptionArr[12] = new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow);
        themeDescriptionArr[13] = new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4);
        themeDescriptionArr[14] = new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText);
        return themeDescriptionArr;
    }
}