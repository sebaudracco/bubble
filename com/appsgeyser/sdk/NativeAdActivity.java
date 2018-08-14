package com.appsgeyser.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import com.appsgeyser.sdk.ads.nativeAd.NativeAdHelper;

public class NativeAdActivity extends AppCompatActivity {
    private NativeAdHelper nativeAdHelper;
    private ProgressBar nativeAdProgressBar;
    private RecyclerView nativeAdRecyclerView;
    private Toolbar toolbar;

    class C11931 implements OnClickListener {
        C11931() {
        }

        public void onClick(View view) {
            NativeAdActivity.this.onBackPressed();
        }
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, NativeAdActivity.class));
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C1195R.layout.appsgeysersdk_native_ad_activity);
        findAllViews();
        this.nativeAdHelper = new NativeAdHelper(this, this.nativeAdRecyclerView, this.nativeAdProgressBar, true);
        prepareToolbar();
        this.nativeAdHelper.sendOfferWallImpression();
    }

    private void findAllViews() {
        this.nativeAdRecyclerView = (RecyclerView) findViewById(C1195R.id.appsgeysersdk_nativeAdRecycler);
        this.nativeAdProgressBar = (ProgressBar) findViewById(C1195R.id.appsgeysersdk_nativeAdProgressBar);
        this.toolbar = (Toolbar) findViewById(C1195R.id.toolbar);
    }

    private void prepareToolbar() {
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationIcon(C1195R.drawable.appsgeysersdk_icon_back);
        this.toolbar.setNavigationOnClickListener(new C11931());
    }

    protected void onDestroy() {
        super.onDestroy();
        this.nativeAdHelper.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        this.nativeAdHelper.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.nativeAdHelper.onResume();
    }
}
