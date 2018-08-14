package com.mopub.mobileads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.CloseableLayout.OnCloseListener;
import com.mopub.common.DataKeys;

abstract class BaseInterstitialActivity extends Activity {
    @Nullable
    protected AdReport mAdReport;
    @Nullable
    private Long mBroadcastIdentifier;
    @Nullable
    private CloseableLayout mCloseableLayout;

    class C36501 implements OnCloseListener {
        C36501() {
        }

        public void onClose() {
            BaseInterstitialActivity.this.finish();
        }
    }

    public abstract View getAdView();

    BaseInterstitialActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.mBroadcastIdentifier = getBroadcastIdentifierFromIntent(intent);
        this.mAdReport = getAdReportFromIntent(intent);
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        View adView = getAdView();
        this.mCloseableLayout = new CloseableLayout(this);
        this.mCloseableLayout.setOnCloseListener(new C36501());
        this.mCloseableLayout.addView(adView, new LayoutParams(-1, -1));
        setContentView(this.mCloseableLayout);
    }

    protected void onDestroy() {
        if (this.mCloseableLayout != null) {
            this.mCloseableLayout.removeAllViews();
        }
        super.onDestroy();
    }

    @Nullable
    protected CloseableLayout getCloseableLayout() {
        return this.mCloseableLayout;
    }

    @Nullable
    Long getBroadcastIdentifier() {
        return this.mBroadcastIdentifier;
    }

    protected void showInterstitialCloseButton() {
        if (this.mCloseableLayout != null) {
            this.mCloseableLayout.setCloseVisible(true);
        }
    }

    protected void hideInterstitialCloseButton() {
        if (this.mCloseableLayout != null) {
            this.mCloseableLayout.setCloseVisible(false);
        }
    }

    protected static Long getBroadcastIdentifierFromIntent(Intent intent) {
        if (intent.hasExtra(DataKeys.BROADCAST_IDENTIFIER_KEY)) {
            return Long.valueOf(intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1));
        }
        return null;
    }

    @Nullable
    protected static AdReport getAdReportFromIntent(Intent intent) {
        try {
            return (AdReport) intent.getSerializableExtra(DataKeys.AD_REPORT_KEY);
        } catch (ClassCastException e) {
            return null;
        }
    }
}
