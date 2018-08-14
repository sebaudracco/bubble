package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Environment;
import android.os.StatFs;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;
import com.wBubble_7453037.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView;
import org.telegram.messenger.support.widget.RecyclerView.Adapter;
import org.telegram.messenger.support.widget.RecyclerView.LayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.OnScrollListener;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.SharedDocumentCell;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.OnItemClickListener;
import org.telegram.ui.Components.RecyclerListView.OnItemLongClickListener;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;

public class DocumentSelectActivity extends BaseFragment {
    private static final int done = 3;
    private ArrayList<View> actionModeViews = new ArrayList();
    private File currentDir;
    private DocumentSelectActivityDelegate delegate;
    private EmptyTextProgressView emptyView;
    private ArrayList<HistoryEntry> history = new ArrayList();
    private ArrayList<ListItem> items = new ArrayList();
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private BroadcastReceiver receiver = new C63991();
    private boolean receiverRegistered = false;
    private ArrayList<ListItem> recentItems = new ArrayList();
    private boolean scrolling;
    private HashMap<String, ListItem> selectedFiles = new HashMap();
    private NumberTextView selectedMessagesCountTextView;
    private long sizeLimit = 1610612736;

    public interface DocumentSelectActivityDelegate {
        void didSelectFiles(DocumentSelectActivity documentSelectActivity, ArrayList<String> arrayList);

        void startDocumentSelectActivity();
    }

    class C63991 extends BroadcastReceiver {

        class C63981 implements Runnable {
            C63981() {
            }

            public void run() {
                try {
                    if (DocumentSelectActivity.this.currentDir == null) {
                        DocumentSelectActivity.this.listRoots();
                    } else {
                        DocumentSelectActivity.this.listFiles(DocumentSelectActivity.this.currentDir);
                    }
                } catch (Exception e) {
                    FileLog.e(e);
                }
            }
        }

        C63991() {
        }

        public void onReceive(Context arg0, Intent intent) {
            Runnable r = new C63981();
            if ("android.intent.action.MEDIA_UNMOUNTED".equals(intent.getAction())) {
                DocumentSelectActivity.this.listView.postDelayed(r, 1000);
            } else {
                r.run();
            }
        }
    }

    class C64002 extends ActionBarMenuOnItemClick {
        C64002() {
        }

        public void onItemClick(int id) {
            if (id == -1) {
                if (DocumentSelectActivity.this.actionBar.isActionModeShowed()) {
                    DocumentSelectActivity.this.selectedFiles.clear();
                    DocumentSelectActivity.this.actionBar.hideActionMode();
                    int count = DocumentSelectActivity.this.listView.getChildCount();
                    for (int a = 0; a < count; a++) {
                        View child = DocumentSelectActivity.this.listView.getChildAt(a);
                        if (child instanceof SharedDocumentCell) {
                            ((SharedDocumentCell) child).setChecked(false, true);
                        }
                    }
                    return;
                }
                DocumentSelectActivity.this.finishFragment();
            } else if (id == 3 && DocumentSelectActivity.this.delegate != null) {
                ArrayList<String> files = new ArrayList();
                files.addAll(DocumentSelectActivity.this.selectedFiles.keySet());
                DocumentSelectActivity.this.delegate.didSelectFiles(DocumentSelectActivity.this, files);
                for (ListItem item : DocumentSelectActivity.this.selectedFiles.values()) {
                    item.date = System.currentTimeMillis();
                }
            }
        }
    }

    class C64013 implements OnTouchListener {
        C64013() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    class C64024 extends OnScrollListener {
        C64024() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            DocumentSelectActivity.this.scrolling = newState != 0;
        }
    }

    class C64035 implements OnItemLongClickListener {
        C64035() {
        }

        public boolean onItemClick(View view, int position) {
            if (DocumentSelectActivity.this.actionBar.isActionModeShowed()) {
                return false;
            }
            ListItem item = DocumentSelectActivity.this.listAdapter.getItem(position);
            if (item == null) {
                return false;
            }
            File file = item.file;
            if (!(file == null || file.isDirectory())) {
                if (!file.canRead()) {
                    DocumentSelectActivity.this.showErrorBox(LocaleController.getString("AccessError", R.string.AccessError));
                    return false;
                } else if (DocumentSelectActivity.this.sizeLimit != 0 && file.length() > DocumentSelectActivity.this.sizeLimit) {
                    DocumentSelectActivity.this.showErrorBox(LocaleController.formatString("FileUploadLimit", R.string.FileUploadLimit, new Object[]{AndroidUtilities.formatFileSize(DocumentSelectActivity.this.sizeLimit)}));
                    return false;
                } else if (file.length() == 0) {
                    return false;
                } else {
                    DocumentSelectActivity.this.selectedFiles.put(file.toString(), item);
                    DocumentSelectActivity.this.selectedMessagesCountTextView.setNumber(1, false);
                    AnimatorSet animatorSet = new AnimatorSet();
                    ArrayList<Animator> animators = new ArrayList();
                    for (int a = 0; a < DocumentSelectActivity.this.actionModeViews.size(); a++) {
                        View view2 = (View) DocumentSelectActivity.this.actionModeViews.get(a);
                        AndroidUtilities.clearDrawableAnimation(view2);
                        animators.add(ObjectAnimator.ofFloat(view2, "scaleY", new float[]{0.1f, 1.0f}));
                    }
                    animatorSet.playTogether(animators);
                    animatorSet.setDuration(250);
                    animatorSet.start();
                    DocumentSelectActivity.this.scrolling = false;
                    if (view instanceof SharedDocumentCell) {
                        ((SharedDocumentCell) view).setChecked(true, true);
                    }
                    DocumentSelectActivity.this.actionBar.showActionMode();
                }
            }
            return true;
        }
    }

    class C64046 implements OnItemClickListener {
        C64046() {
        }

        public void onItemClick(View view, int position) {
            ListItem item = DocumentSelectActivity.this.listAdapter.getItem(position);
            if (item != null) {
                File file = item.file;
                HistoryEntry he;
                if (file == null) {
                    if (item.icon == R.drawable.ic_storage_gallery) {
                        if (DocumentSelectActivity.this.delegate != null) {
                            DocumentSelectActivity.this.delegate.startDocumentSelectActivity();
                        }
                        DocumentSelectActivity.this.finishFragment(false);
                        return;
                    }
                    he = (HistoryEntry) DocumentSelectActivity.this.history.remove(DocumentSelectActivity.this.history.size() - 1);
                    DocumentSelectActivity.this.actionBar.setTitle(he.title);
                    if (he.dir != null) {
                        DocumentSelectActivity.this.listFiles(he.dir);
                    } else {
                        DocumentSelectActivity.this.listRoots();
                    }
                    DocumentSelectActivity.this.layoutManager.scrollToPositionWithOffset(he.scrollItem, he.scrollOffset);
                } else if (file.isDirectory()) {
                    he = new HistoryEntry();
                    he.scrollItem = DocumentSelectActivity.this.layoutManager.findLastVisibleItemPosition();
                    View topView = DocumentSelectActivity.this.layoutManager.findViewByPosition(he.scrollItem);
                    if (topView != null) {
                        he.scrollOffset = topView.getTop();
                    }
                    he.dir = DocumentSelectActivity.this.currentDir;
                    he.title = DocumentSelectActivity.this.actionBar.getTitle();
                    DocumentSelectActivity.this.history.add(he);
                    if (DocumentSelectActivity.this.listFiles(file)) {
                        DocumentSelectActivity.this.actionBar.setTitle(item.title);
                    } else {
                        DocumentSelectActivity.this.history.remove(he);
                    }
                } else {
                    if (!file.canRead()) {
                        DocumentSelectActivity.this.showErrorBox(LocaleController.getString("AccessError", R.string.AccessError));
                        file = new File("/mnt/sdcard");
                    }
                    if (DocumentSelectActivity.this.sizeLimit != 0 && file.length() > DocumentSelectActivity.this.sizeLimit) {
                        DocumentSelectActivity.this.showErrorBox(LocaleController.formatString("FileUploadLimit", R.string.FileUploadLimit, new Object[]{AndroidUtilities.formatFileSize(DocumentSelectActivity.this.sizeLimit)}));
                    } else if (file.length() == 0) {
                    } else {
                        if (DocumentSelectActivity.this.actionBar.isActionModeShowed()) {
                            if (DocumentSelectActivity.this.selectedFiles.containsKey(file.toString())) {
                                DocumentSelectActivity.this.selectedFiles.remove(file.toString());
                            } else {
                                DocumentSelectActivity.this.selectedFiles.put(file.toString(), item);
                            }
                            if (DocumentSelectActivity.this.selectedFiles.isEmpty()) {
                                DocumentSelectActivity.this.actionBar.hideActionMode();
                            } else {
                                DocumentSelectActivity.this.selectedMessagesCountTextView.setNumber(DocumentSelectActivity.this.selectedFiles.size(), true);
                            }
                            DocumentSelectActivity.this.scrolling = false;
                            if (view instanceof SharedDocumentCell) {
                                ((SharedDocumentCell) view).setChecked(DocumentSelectActivity.this.selectedFiles.containsKey(item.file.toString()), true);
                            }
                        } else if (DocumentSelectActivity.this.delegate != null) {
                            ArrayList<String> files = new ArrayList();
                            files.add(file.getAbsolutePath());
                            DocumentSelectActivity.this.delegate.didSelectFiles(DocumentSelectActivity.this, files);
                        }
                    }
                }
            }
        }
    }

    class C64057 implements Comparator<ListItem> {
        C64057() {
        }

        public int compare(ListItem o1, ListItem o2) {
            long lm = o1.file.lastModified();
            long rm = o2.file.lastModified();
            if (lm == rm) {
                return 0;
            }
            if (lm > rm) {
                return -1;
            }
            return 1;
        }
    }

    class C64068 implements OnPreDrawListener {
        C64068() {
        }

        public boolean onPreDraw() {
            DocumentSelectActivity.this.listView.getViewTreeObserver().removeOnPreDrawListener(this);
            DocumentSelectActivity.this.fixLayoutInternal();
            return true;
        }
    }

    class C64079 implements Comparator<File> {
        C64079() {
        }

        public int compare(File lhs, File rhs) {
            if (lhs.isDirectory() != rhs.isDirectory()) {
                return lhs.isDirectory() ? -1 : 1;
            } else {
                return lhs.getName().compareToIgnoreCase(rhs.getName());
            }
        }
    }

    private class HistoryEntry {
        File dir;
        int scrollItem;
        int scrollOffset;
        String title;

        private HistoryEntry() {
        }
    }

    private class ListAdapter extends SelectionAdapter {
        private Context mContext;

        public ListAdapter(Context context) {
            this.mContext = context;
        }

        public boolean isEnabled(ViewHolder holder) {
            return holder.getItemViewType() != 0;
        }

        public int getItemCount() {
            int count = DocumentSelectActivity.this.items.size();
            if (!DocumentSelectActivity.this.history.isEmpty() || DocumentSelectActivity.this.recentItems.isEmpty()) {
                return count;
            }
            return count + (DocumentSelectActivity.this.recentItems.size() + 1);
        }

        public ListItem getItem(int position) {
            if (position < DocumentSelectActivity.this.items.size()) {
                return (ListItem) DocumentSelectActivity.this.items.get(position);
            }
            if (!(!DocumentSelectActivity.this.history.isEmpty() || DocumentSelectActivity.this.recentItems.isEmpty() || position == DocumentSelectActivity.this.items.size())) {
                position -= DocumentSelectActivity.this.items.size() + 1;
                if (position < DocumentSelectActivity.this.recentItems.size()) {
                    return (ListItem) DocumentSelectActivity.this.recentItems.get(position);
                }
            }
            return null;
        }

        public int getItemViewType(int position) {
            return getItem(position) != null ? 1 : 0;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            switch (viewType) {
                case 0:
                    view = new GraySectionCell(this.mContext);
                    ((GraySectionCell) view).setText(LocaleController.getString("Recent", R.string.Recent).toUpperCase());
                    break;
                default:
                    view = new SharedDocumentCell(this.mContext);
                    break;
            }
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            boolean z = true;
            if (holder.getItemViewType() == 1) {
                ListItem item = getItem(position);
                SharedDocumentCell documentCell = holder.itemView;
                if (item.icon != 0) {
                    documentCell.setTextAndValueAndTypeAndThumb(item.title, item.subtitle, null, null, item.icon);
                } else {
                    documentCell.setTextAndValueAndTypeAndThumb(item.title, item.subtitle, item.ext.toUpperCase().substring(0, Math.min(item.ext.length(), 4)), item.thumb, 0);
                }
                if (item.file == null || !DocumentSelectActivity.this.actionBar.isActionModeShowed()) {
                    if (DocumentSelectActivity.this.scrolling) {
                        z = false;
                    }
                    documentCell.setChecked(false, z);
                    return;
                }
                boolean z2;
                boolean containsKey = DocumentSelectActivity.this.selectedFiles.containsKey(item.file.toString());
                if (DocumentSelectActivity.this.scrolling) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                documentCell.setChecked(containsKey, z2);
            }
        }
    }

    private class ListItem {
        long date;
        String ext;
        File file;
        int icon;
        String subtitle;
        String thumb;
        String title;

        private ListItem() {
            this.subtitle = "";
            this.ext = "";
        }
    }

    public boolean onFragmentCreate() {
        loadRecentFiles();
        return super.onFragmentCreate();
    }

    public void onFragmentDestroy() {
        try {
            if (this.receiverRegistered) {
                ApplicationLoader.applicationContext.unregisterReceiver(this.receiver);
            }
        } catch (Exception e) {
            FileLog.e(e);
        }
        super.onFragmentDestroy();
    }

    public View createView(Context context) {
        if (!this.receiverRegistered) {
            this.receiverRegistered = true;
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
            filter.addAction("android.intent.action.MEDIA_CHECKING");
            filter.addAction("android.intent.action.MEDIA_EJECT");
            filter.addAction("android.intent.action.MEDIA_MOUNTED");
            filter.addAction("android.intent.action.MEDIA_NOFS");
            filter.addAction("android.intent.action.MEDIA_REMOVED");
            filter.addAction("android.intent.action.MEDIA_SHARED");
            filter.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
            filter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            filter.addDataScheme("file");
            ApplicationLoader.applicationContext.registerReceiver(this.receiver, filter);
        }
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString("SelectFile", R.string.SelectFile));
        this.actionBar.setActionBarMenuOnItemClick(new C64002());
        this.selectedFiles.clear();
        this.actionModeViews.clear();
        ActionBarMenu actionMode = this.actionBar.createActionMode();
        this.selectedMessagesCountTextView = new NumberTextView(actionMode.getContext());
        this.selectedMessagesCountTextView.setTextSize(18);
        this.selectedMessagesCountTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        this.selectedMessagesCountTextView.setTextColor(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon));
        this.selectedMessagesCountTextView.setOnTouchListener(new C64013());
        actionMode.addView(this.selectedMessagesCountTextView, LayoutHelper.createLinear(0, -1, 1.0f, 65, 0, 0, 0));
        this.actionModeViews.add(actionMode.addItemWithWidth(3, R.drawable.ic_ab_done, AndroidUtilities.dp(54.0f)));
        this.fragmentView = new FrameLayout(context);
        FrameLayout frameLayout = this.fragmentView;
        this.emptyView = new EmptyTextProgressView(context);
        this.emptyView.showTextView();
        frameLayout.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView = new RecyclerListView(context);
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView recyclerListView = this.listView;
        LayoutManager linearLayoutManager = new LinearLayoutManager(context, 1, false);
        this.layoutManager = linearLayoutManager;
        recyclerListView.setLayoutManager(linearLayoutManager);
        this.listView.setEmptyView(this.emptyView);
        RecyclerListView recyclerListView2 = this.listView;
        Adapter listAdapter = new ListAdapter(context);
        this.listAdapter = listAdapter;
        recyclerListView2.setAdapter(listAdapter);
        frameLayout.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnScrollListener(new C64024());
        this.listView.setOnItemLongClickListener(new C64035());
        this.listView.setOnItemClickListener(new C64046());
        listRoots();
        return this.fragmentView;
    }

    public void loadRecentFiles() {
        try {
            File[] files = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).listFiles();
            for (File file : files) {
                if (!file.isDirectory()) {
                    ListItem item = new ListItem();
                    item.title = file.getName();
                    item.file = file;
                    String fname = file.getName();
                    String[] sp = fname.split("\\.");
                    item.ext = sp.length > 1 ? sp[sp.length - 1] : "?";
                    item.subtitle = AndroidUtilities.formatFileSize(file.length());
                    fname = fname.toLowerCase();
                    if (fname.endsWith(".jpg") || fname.endsWith(".png") || fname.endsWith(".gif") || fname.endsWith(".jpeg")) {
                        item.thumb = file.getAbsolutePath();
                    }
                    this.recentItems.add(item);
                }
            }
            Collections.sort(this.recentItems, new C64057());
        } catch (Exception e) {
            FileLog.e(e);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
        fixLayoutInternal();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.listView != null) {
            this.listView.getViewTreeObserver().addOnPreDrawListener(new C64068());
        }
    }

    private void fixLayoutInternal() {
        if (this.selectedMessagesCountTextView != null) {
            if (AndroidUtilities.isTablet() || ApplicationLoader.applicationContext.getResources().getConfiguration().orientation != 2) {
                this.selectedMessagesCountTextView.setTextSize(20);
            } else {
                this.selectedMessagesCountTextView.setTextSize(18);
            }
        }
    }

    public boolean onBackPressed() {
        if (this.history.size() <= 0) {
            return super.onBackPressed();
        }
        HistoryEntry he = (HistoryEntry) this.history.remove(this.history.size() - 1);
        this.actionBar.setTitle(he.title);
        if (he.dir != null) {
            listFiles(he.dir);
        } else {
            listRoots();
        }
        this.layoutManager.scrollToPositionWithOffset(he.scrollItem, he.scrollOffset);
        return false;
    }

    public void setDelegate(DocumentSelectActivityDelegate delegate) {
        this.delegate = delegate;
    }

    private boolean listFiles(File dir) {
        if (dir.canRead()) {
            try {
                File[] files = dir.listFiles();
                if (files == null) {
                    showErrorBox(LocaleController.getString("UnknownError", R.string.UnknownError));
                    return false;
                }
                ListItem item;
                this.currentDir = dir;
                this.items.clear();
                Arrays.sort(files, new C64079());
                for (File file : files) {
                    if (file.getName().indexOf(46) != 0) {
                        item = new ListItem();
                        item.title = file.getName();
                        item.file = file;
                        if (file.isDirectory()) {
                            item.icon = R.drawable.ic_directory;
                            item.subtitle = LocaleController.getString("Folder", R.string.Folder);
                        } else {
                            String fname = file.getName();
                            String[] sp = fname.split("\\.");
                            item.ext = sp.length > 1 ? sp[sp.length - 1] : "?";
                            item.subtitle = AndroidUtilities.formatFileSize(file.length());
                            fname = fname.toLowerCase();
                            if (fname.endsWith(".jpg") || fname.endsWith(".png") || fname.endsWith(".gif") || fname.endsWith(".jpeg")) {
                                item.thumb = file.getAbsolutePath();
                            }
                        }
                        this.items.add(item);
                    }
                }
                item = new ListItem();
                item.title = "..";
                if (this.history.size() > 0) {
                    HistoryEntry entry = (HistoryEntry) this.history.get(this.history.size() - 1);
                    if (entry.dir == null) {
                        item.subtitle = LocaleController.getString("Folder", R.string.Folder);
                    } else {
                        item.subtitle = entry.dir.toString();
                    }
                } else {
                    item.subtitle = LocaleController.getString("Folder", R.string.Folder);
                }
                item.icon = R.drawable.ic_directory;
                item.file = null;
                this.items.add(0, item);
                AndroidUtilities.clearDrawableAnimation(this.listView);
                this.scrolling = true;
                this.listAdapter.notifyDataSetChanged();
                return true;
            } catch (Exception e) {
                showErrorBox(e.getLocalizedMessage());
                return false;
            }
        } else if ((!dir.getAbsolutePath().startsWith(Environment.getExternalStorageDirectory().toString()) && !dir.getAbsolutePath().startsWith("/sdcard") && !dir.getAbsolutePath().startsWith("/mnt/sdcard")) || Environment.getExternalStorageState().equals("mounted") || Environment.getExternalStorageState().equals("mounted_ro")) {
            showErrorBox(LocaleController.getString("AccessError", R.string.AccessError));
            return false;
        } else {
            this.currentDir = dir;
            this.items.clear();
            if ("shared".equals(Environment.getExternalStorageState())) {
                this.emptyView.setText(LocaleController.getString("UsbActive", R.string.UsbActive));
            } else {
                this.emptyView.setText(LocaleController.getString("NotMounted", R.string.NotMounted));
            }
            AndroidUtilities.clearDrawableAnimation(this.listView);
            this.scrolling = true;
            this.listAdapter.notifyDataSetChanged();
            return true;
        }
    }

    private void showErrorBox(String error) {
        if (getParentActivity() != null) {
            new Builder(getParentActivity()).setTitle(ApplicationLoader.getConfig().getAppName()).setMessage(error).setPositiveButton(LocaleController.getString("OK", R.string.OK), null).show();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"NewApi"})
    private void listRoots() {
        /*
        r23 = this;
        r21 = 0;
        r0 = r21;
        r1 = r23;
        r1.currentDir = r0;
        r0 = r23;
        r0 = r0.items;
        r21 = r0;
        r21.clear();
        r17 = new java.util.HashSet;
        r17.<init>();
        r21 = android.os.Environment.getExternalStorageDirectory();
        r5 = r21.getPath();
        r12 = android.os.Environment.isExternalStorageRemovable();
        r6 = android.os.Environment.getExternalStorageState();
        r21 = "mounted";
        r0 = r21;
        r21 = r6.equals(r0);
        if (r21 != 0) goto L_0x003c;
    L_0x0031:
        r21 = "mounted_ro";
        r0 = r21;
        r21 = r6.equals(r0);
        if (r21 == 0) goto L_0x0084;
    L_0x003c:
        r8 = new org.telegram.ui.DocumentSelectActivity$ListItem;
        r21 = 0;
        r0 = r23;
        r1 = r21;
        r8.<init>();
        r21 = android.os.Environment.isExternalStorageRemovable();
        if (r21 == 0) goto L_0x02b4;
    L_0x004d:
        r21 = "SdCard";
        r22 = 2131559862; // 0x7f0d05b6 float:1.874508E38 double:1.0531305E-314;
        r21 = org.telegram.messenger.LocaleController.getString(r21, r22);
        r0 = r21;
        r8.title = r0;
        r21 = 2131231037; // 0x7f08013d float:1.8078144E38 double:1.0529680387E-314;
        r0 = r21;
        r8.icon = r0;
    L_0x0062:
        r0 = r23;
        r21 = r0.getRootSubtitle(r5);
        r0 = r21;
        r8.subtitle = r0;
        r21 = android.os.Environment.getExternalStorageDirectory();
        r0 = r21;
        r8.file = r0;
        r0 = r23;
        r0 = r0.items;
        r21 = r0;
        r0 = r21;
        r0.add(r8);
        r0 = r17;
        r0.add(r5);
    L_0x0084:
        r3 = 0;
        r4 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0308 }
        r21 = new java.io.FileReader;	 Catch:{ Exception -> 0x0308 }
        r22 = "/proc/mounts";
        r21.<init>(r22);	 Catch:{ Exception -> 0x0308 }
        r0 = r21;
        r4.<init>(r0);	 Catch:{ Exception -> 0x0308 }
    L_0x0094:
        r14 = r4.readLine();	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r14 == 0) goto L_0x02e3;
    L_0x009a:
        r21 = "vfat";
        r0 = r21;
        r21 = r14.contains(r0);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 != 0) goto L_0x00b0;
    L_0x00a5:
        r21 = "/mnt";
        r0 = r21;
        r21 = r14.contains(r0);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 == 0) goto L_0x0094;
    L_0x00b0:
        org.telegram.messenger.FileLog.e(r14);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r19 = new java.util.StringTokenizer;	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r21 = " ";
        r0 = r19;
        r1 = r21;
        r0.<init>(r14, r1);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r20 = r19.nextToken();	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r16 = r19.nextToken();	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r0 = r17;
        r1 = r16;
        r21 = r0.contains(r1);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 != 0) goto L_0x0094;
    L_0x00d1:
        r21 = "/dev/block/vold";
        r0 = r21;
        r21 = r14.contains(r0);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 == 0) goto L_0x0094;
    L_0x00dc:
        r21 = "/mnt/secure";
        r0 = r21;
        r21 = r14.contains(r0);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 != 0) goto L_0x0094;
    L_0x00e7:
        r21 = "/mnt/asec";
        r0 = r21;
        r21 = r14.contains(r0);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 != 0) goto L_0x0094;
    L_0x00f2:
        r21 = "/mnt/obb";
        r0 = r21;
        r21 = r14.contains(r0);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 != 0) goto L_0x0094;
    L_0x00fd:
        r21 = "/dev/mapper";
        r0 = r21;
        r21 = r14.contains(r0);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 != 0) goto L_0x0094;
    L_0x0108:
        r21 = "tmpfs";
        r0 = r21;
        r21 = r14.contains(r0);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 != 0) goto L_0x0094;
    L_0x0113:
        r21 = new java.io.File;	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r0 = r21;
        r1 = r16;
        r0.<init>(r1);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r21 = r21.isDirectory();	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 != 0) goto L_0x015f;
    L_0x0122:
        r21 = 47;
        r0 = r16;
        r1 = r21;
        r11 = r0.lastIndexOf(r1);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r21 = -1;
        r0 = r21;
        if (r11 == r0) goto L_0x015f;
    L_0x0132:
        r21 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r21.<init>();	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r22 = "/storage/";
        r21 = r21.append(r22);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r22 = r11 + 1;
        r0 = r16;
        r1 = r22;
        r22 = r0.substring(r1);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r21 = r21.append(r22);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r15 = r21.toString();	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r21 = new java.io.File;	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r0 = r21;
        r0.<init>(r15);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r21 = r21.isDirectory();	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        if (r21 == 0) goto L_0x015f;
    L_0x015d:
        r16 = r15;
    L_0x015f:
        r0 = r17;
        r1 = r16;
        r0.add(r1);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        r13 = new org.telegram.ui.DocumentSelectActivity$ListItem;	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r21 = 0;
        r0 = r23;
        r1 = r21;
        r13.<init>();	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r21 = r16.toLowerCase();	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r22 = "sd";
        r21 = r21.contains(r22);	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        if (r21 == 0) goto L_0x02cb;
    L_0x017e:
        r21 = "SdCard";
        r22 = 2131559862; // 0x7f0d05b6 float:1.874508E38 double:1.0531305E-314;
        r21 = org.telegram.messenger.LocaleController.getString(r21, r22);	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r0 = r21;
        r13.title = r0;	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
    L_0x018c:
        r21 = 2131231037; // 0x7f08013d float:1.8078144E38 double:1.0529680387E-314;
        r0 = r21;
        r13.icon = r0;	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r0 = r23;
        r1 = r16;
        r21 = r0.getRootSubtitle(r1);	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r0 = r21;
        r13.subtitle = r0;	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r21 = new java.io.File;	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r0 = r21;
        r1 = r16;
        r0.<init>(r1);	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r0 = r21;
        r13.file = r0;	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r0 = r23;
        r0 = r0.items;	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r21 = r0;
        r0 = r21;
        r0.add(r13);	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        goto L_0x0094;
    L_0x01b9:
        r7 = move-exception;
        org.telegram.messenger.FileLog.e(r7);	 Catch:{ Exception -> 0x01bf, all -> 0x02db }
        goto L_0x0094;
    L_0x01bf:
        r7 = move-exception;
        r3 = r4;
    L_0x01c1:
        org.telegram.messenger.FileLog.e(r7);	 Catch:{ all -> 0x0306 }
        if (r3 == 0) goto L_0x01c9;
    L_0x01c6:
        r3.close();	 Catch:{ Exception -> 0x02f2 }
    L_0x01c9:
        r9 = new org.telegram.ui.DocumentSelectActivity$ListItem;
        r21 = 0;
        r0 = r23;
        r1 = r21;
        r9.<init>();
        r21 = "/";
        r0 = r21;
        r9.title = r0;
        r21 = "SystemRoot";
        r22 = 2131560015; // 0x7f0d064f float:1.874539E38 double:1.0531305755E-314;
        r21 = org.telegram.messenger.LocaleController.getString(r21, r22);
        r0 = r21;
        r9.subtitle = r0;
        r21 = 2131231035; // 0x7f08013b float:1.807814E38 double:1.0529680377E-314;
        r0 = r21;
        r9.icon = r0;
        r21 = new java.io.File;
        r22 = "/";
        r21.<init>(r22);
        r0 = r21;
        r9.file = r0;
        r0 = r23;
        r0 = r0.items;
        r21 = r0;
        r0 = r21;
        r0.add(r9);
        r18 = new java.io.File;	 Catch:{ Exception -> 0x02fd }
        r21 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x02fd }
        r22 = org.telegram.messenger.ApplicationLoader.getConfig();	 Catch:{ Exception -> 0x02fd }
        r22 = r22.getAppName();	 Catch:{ Exception -> 0x02fd }
        r0 = r18;
        r1 = r21;
        r2 = r22;
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x02fd }
        r21 = r18.exists();	 Catch:{ Exception -> 0x02fd }
        if (r21 == 0) goto L_0x025a;
    L_0x0224:
        r10 = new org.telegram.ui.DocumentSelectActivity$ListItem;	 Catch:{ Exception -> 0x02fd }
        r21 = 0;
        r0 = r23;
        r1 = r21;
        r10.<init>();	 Catch:{ Exception -> 0x02fd }
        r21 = org.telegram.messenger.ApplicationLoader.getConfig();	 Catch:{ Exception -> 0x0303 }
        r21 = r21.getAppName();	 Catch:{ Exception -> 0x0303 }
        r0 = r21;
        r10.title = r0;	 Catch:{ Exception -> 0x0303 }
        r21 = r18.toString();	 Catch:{ Exception -> 0x0303 }
        r0 = r21;
        r10.subtitle = r0;	 Catch:{ Exception -> 0x0303 }
        r21 = 2131231035; // 0x7f08013b float:1.807814E38 double:1.0529680377E-314;
        r0 = r21;
        r10.icon = r0;	 Catch:{ Exception -> 0x0303 }
        r0 = r18;
        r10.file = r0;	 Catch:{ Exception -> 0x0303 }
        r0 = r23;
        r0 = r0.items;	 Catch:{ Exception -> 0x0303 }
        r21 = r0;
        r0 = r21;
        r0.add(r10);	 Catch:{ Exception -> 0x0303 }
        r9 = r10;
    L_0x025a:
        r9 = new org.telegram.ui.DocumentSelectActivity$ListItem;
        r21 = 0;
        r0 = r23;
        r1 = r21;
        r9.<init>();
        r21 = "Gallery";
        r22 = 2131559171; // 0x7f0d0303 float:1.8743679E38 double:1.0531301585E-314;
        r21 = org.telegram.messenger.LocaleController.getString(r21, r22);
        r0 = r21;
        r9.title = r0;
        r21 = "GalleryInfo";
        r22 = 2131559172; // 0x7f0d0304 float:1.874368E38 double:1.053130159E-314;
        r21 = org.telegram.messenger.LocaleController.getString(r21, r22);
        r0 = r21;
        r9.subtitle = r0;
        r21 = 2131231089; // 0x7f080171 float:1.807825E38 double:1.0529680644E-314;
        r0 = r21;
        r9.icon = r0;
        r21 = 0;
        r0 = r21;
        r9.file = r0;
        r0 = r23;
        r0 = r0.items;
        r21 = r0;
        r0 = r21;
        r0.add(r9);
        r0 = r23;
        r0 = r0.listView;
        r21 = r0;
        org.telegram.messenger.AndroidUtilities.clearDrawableAnimation(r21);
        r21 = 1;
        r0 = r21;
        r1 = r23;
        r1.scrolling = r0;
        r0 = r23;
        r0 = r0.listAdapter;
        r21 = r0;
        r21.notifyDataSetChanged();
        return;
    L_0x02b4:
        r21 = "InternalStorage";
        r22 = 2131559227; // 0x7f0d033b float:1.8743792E38 double:1.053130186E-314;
        r21 = org.telegram.messenger.LocaleController.getString(r21, r22);
        r0 = r21;
        r8.title = r0;
        r21 = 2131231088; // 0x7f080170 float:1.8078247E38 double:1.052968064E-314;
        r0 = r21;
        r8.icon = r0;
        goto L_0x0062;
    L_0x02cb:
        r21 = "ExternalStorage";
        r22 = 2131559072; // 0x7f0d02a0 float:1.8743478E38 double:1.0531301096E-314;
        r21 = org.telegram.messenger.LocaleController.getString(r21, r22);	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        r0 = r21;
        r13.title = r0;	 Catch:{ Exception -> 0x01b9, all -> 0x02db }
        goto L_0x018c;
    L_0x02db:
        r21 = move-exception;
        r3 = r4;
    L_0x02dd:
        if (r3 == 0) goto L_0x02e2;
    L_0x02df:
        r3.close();	 Catch:{ Exception -> 0x02f8 }
    L_0x02e2:
        throw r21;
    L_0x02e3:
        if (r4 == 0) goto L_0x030b;
    L_0x02e5:
        r4.close();	 Catch:{ Exception -> 0x02eb }
        r3 = r4;
        goto L_0x01c9;
    L_0x02eb:
        r7 = move-exception;
        org.telegram.messenger.FileLog.e(r7);
        r3 = r4;
        goto L_0x01c9;
    L_0x02f2:
        r7 = move-exception;
        org.telegram.messenger.FileLog.e(r7);
        goto L_0x01c9;
    L_0x02f8:
        r7 = move-exception;
        org.telegram.messenger.FileLog.e(r7);
        goto L_0x02e2;
    L_0x02fd:
        r7 = move-exception;
    L_0x02fe:
        org.telegram.messenger.FileLog.e(r7);
        goto L_0x025a;
    L_0x0303:
        r7 = move-exception;
        r9 = r10;
        goto L_0x02fe;
    L_0x0306:
        r21 = move-exception;
        goto L_0x02dd;
    L_0x0308:
        r7 = move-exception;
        goto L_0x01c1;
    L_0x030b:
        r3 = r4;
        goto L_0x01c9;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.ui.DocumentSelectActivity.listRoots():void");
    }

    private String getRootSubtitle(String path) {
        try {
            StatFs stat = new StatFs(path);
            long free = ((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize());
            if (((long) stat.getBlockCount()) * ((long) stat.getBlockSize()) == 0) {
                return "";
            }
            return LocaleController.formatString("FreeOfTotal", R.string.FreeOfTotal, new Object[]{AndroidUtilities.formatFileSize(free), AndroidUtilities.formatFileSize(((long) stat.getBlockCount()) * ((long) stat.getBlockSize()))});
        } catch (Exception e) {
            FileLog.e(e);
            return path;
        }
    }

    public ThemeDescription[] getThemeDescriptions() {
        ThemeDescription[] themeDescriptionArr = new ThemeDescription[22];
        themeDescriptionArr[0] = new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite);
        themeDescriptionArr[1] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault);
        themeDescriptionArr[2] = new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault);
        themeDescriptionArr[3] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon);
        themeDescriptionArr[4] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle);
        themeDescriptionArr[5] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector);
        themeDescriptionArr[6] = new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector);
        themeDescriptionArr[7] = new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder);
        themeDescriptionArr[8] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarActionModeDefaultIcon);
        themeDescriptionArr[9] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_BACKGROUND, null, null, null, null, Theme.key_actionBarActionModeDefault);
        themeDescriptionArr[10] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_TOPBACKGROUND, null, null, null, null, Theme.key_actionBarActionModeDefaultTop);
        themeDescriptionArr[11] = new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_AM_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarActionModeDefaultSelector);
        themeDescriptionArr[12] = new ThemeDescription(this.selectedMessagesCountTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_actionBarActionModeDefaultIcon);
        themeDescriptionArr[13] = new ThemeDescription(this.listView, 0, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2);
        themeDescriptionArr[14] = new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GraySectionCell.class}, null, null, null, Theme.key_graySection);
        themeDescriptionArr[15] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"nameTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText);
        themeDescriptionArr[16] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"dateTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText3);
        themeDescriptionArr[17] = new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOX, new Class[]{SharedDocumentCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkbox);
        themeDescriptionArr[18] = new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{SharedDocumentCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkboxCheck);
        themeDescriptionArr[19] = new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"thumbImageView"}, null, null, null, Theme.key_files_folderIcon);
        themeDescriptionArr[20] = new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR | ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{SharedDocumentCell.class}, new String[]{"thumbImageView"}, null, null, null, Theme.key_files_folderIconBackground);
        themeDescriptionArr[21] = new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"extTextView"}, null, null, null, Theme.key_files_iconText);
        return themeDescriptionArr;
    }
}
