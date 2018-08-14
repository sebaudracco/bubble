package org.telegram.ui.Components;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.wBubble_7453037.R;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.support.widget.LinearLayoutManager;
import org.telegram.messenger.support.widget.RecyclerView.LayoutParams;
import org.telegram.messenger.support.widget.RecyclerView.ViewHolder;
import org.telegram.tgnet.TLRPC$StickerSetCovered;
import org.telegram.ui.ActionBar.AlertDialog.Builder;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ArchivedStickerSetCell;
import org.telegram.ui.Components.RecyclerListView.Holder;
import org.telegram.ui.Components.RecyclerListView.SelectionAdapter;
import org.telegram.ui.StickersActivity;

public class StickersArchiveAlert extends Builder {
    private int currentType;
    private boolean ignoreLayout;
    private BaseFragment parentFragment;
    private int reqId;
    private int scrollOffsetY;
    private ArrayList<TLRPC$StickerSetCovered> stickerSets;

    class C62941 implements OnClickListener {
        C62941() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    class C62952 implements OnClickListener {
        C62952() {
        }

        public void onClick(DialogInterface dialog, int which) {
            StickersArchiveAlert.this.parentFragment.presentFragment(new StickersActivity(StickersArchiveAlert.this.currentType));
            dialog.dismiss();
        }
    }

    private class ListAdapter extends SelectionAdapter {
        Context context;

        public ListAdapter(Context context) {
            this.context = context;
        }

        public int getItemCount() {
            return StickersArchiveAlert.this.stickerSets.size();
        }

        public boolean isEnabled(ViewHolder holder) {
            return false;
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new ArchivedStickerSetCell(this.context, false);
            view.setLayoutParams(new LayoutParams(-1, AndroidUtilities.dp(82.0f)));
            return new Holder(view);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            ((ArchivedStickerSetCell) holder.itemView).setStickersSet((TLRPC$StickerSetCovered) StickersArchiveAlert.this.stickerSets.get(position), position != StickersArchiveAlert.this.stickerSets.size() + -1);
        }
    }

    public StickersArchiveAlert(Context context, BaseFragment baseFragment, ArrayList<TLRPC$StickerSetCovered> sets) {
        super(context);
        TLRPC$StickerSetCovered set = (TLRPC$StickerSetCovered) sets.get(0);
        if (set.set.masks) {
            this.currentType = 1;
            setTitle(LocaleController.getString("ArchivedMasksAlertTitle", R.string.ArchivedMasksAlertTitle));
        } else {
            this.currentType = 0;
            setTitle(LocaleController.getString("ArchivedStickersAlertTitle", R.string.ArchivedStickersAlertTitle));
        }
        this.stickerSets = new ArrayList(sets);
        this.parentFragment = baseFragment;
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(1);
        setView(container);
        TextView textView = new TextView(context);
        textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        textView.setTextSize(1, 16.0f);
        textView.setPadding(AndroidUtilities.dp(23.0f), AndroidUtilities.dp(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT), AndroidUtilities.dp(23.0f), 0);
        if (set.set.masks) {
            textView.setText(LocaleController.getString("ArchivedMasksAlertInfo", R.string.ArchivedMasksAlertInfo));
        } else {
            textView.setText(LocaleController.getString("ArchivedStickersAlertInfo", R.string.ArchivedStickersAlertInfo));
        }
        container.addView(textView, LayoutHelper.createLinear(-2, -2));
        RecyclerListView listView = new RecyclerListView(context);
        listView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        listView.setAdapter(new ListAdapter(context));
        listView.setVerticalScrollBarEnabled(false);
        listView.setPadding(AndroidUtilities.dp(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT), 0, AndroidUtilities.dp(Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT), 0);
        listView.setGlowColor(-657673);
        container.addView(listView, LayoutHelper.createLinear(-1, -2, 0.0f, Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT, 0.0f, 0.0f));
        setNegativeButton(LocaleController.getString(HTTP.CONN_CLOSE, R.string.Close), new C62941());
        if (this.parentFragment != null) {
            setPositiveButton(LocaleController.getString("Settings", R.string.Settings), new C62952());
        }
    }
}
