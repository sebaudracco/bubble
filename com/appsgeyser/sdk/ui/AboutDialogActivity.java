package com.appsgeyser.sdk.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class AboutDialogActivity extends Activity {
    private static final String APPSGEYSER_DESCRIPTION_CUSTOM = "custom";
    private static final String APPSGEYSER_DESCRIPTION_LOGO = "appsgeyser_logo";
    private static final String APPSGEYSER_DESCRIPTION_TEXT = "appsgeyser_text";
    private static final String CONFIG_PHP_KEY = "config_php_key";
    private TextView appName;
    private TextView appVersion;
    private ImageView appsgeyserDescriptionLogoImageView;
    private ImageView closeScreenImageView;
    private Configuration config;
    private ConfigPhp configPhp;
    private String description;
    private TextView descriptionTextView;
    private String descriptionType;
    private ImageView logoImageView;
    private Button privacy;
    private TextView templateVersion;
    private Button tos;

    class C13092 implements OnClickListener {
        C13092() {
        }

        public void onClick(View v) {
            AboutDialogActivity.this.finish();
        }
    }

    class C13103 implements OnClickListener {
        C13103() {
        }

        public void onClick(View v) {
            Intent intent = new Intent("android.intent.action.VIEW").setData(Uri.parse("http://www.appsgeyser.com/tos/?pn=" + AboutDialogActivity.this.getPackageName()));
            intent.setFlags(ErrorDialogData.BINDER_CRASH);
            AboutDialogActivity.this.startActivity(intent);
        }
    }

    class C13114 implements OnClickListener {
        C13114() {
        }

        public void onClick(View v) {
            Intent intent = new Intent("android.intent.action.VIEW").setData(Uri.parse("http://www.appsgeyser.com/privacy/app/?pn=" + AboutDialogActivity.this.getPackageName()));
            intent.setFlags(ErrorDialogData.BINDER_CRASH);
            AboutDialogActivity.this.startActivity(intent);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C1195R.layout.appsgeysersdk_about_dialog);
        if (savedInstanceState != null) {
            this.configPhp = (ConfigPhp) savedInstanceState.getParcelable(CONFIG_PHP_KEY);
        } else {
            Intent intent = getIntent();
            if (intent != null) {
                this.configPhp = (ConfigPhp) intent.getParcelableExtra(CONFIG_PHP_KEY);
            }
        }
        if (this.configPhp != null) {
            this.descriptionType = this.configPhp.getAboutScreenDescriptionType();
            this.description = this.configPhp.getAboutScreenDescription();
        }
        init();
    }

    private void init() {
        try {
            this.config = Configuration.getInstance(this);
            String appId = this.config.getApplicationId();
            this.logoImageView = (ImageView) findViewById(C1195R.id.logo);
            this.appsgeyserDescriptionLogoImageView = (ImageView) findViewById(C1195R.id.appsgeysersdk_about_appsgeyser_logo);
            this.closeScreenImageView = (ImageView) findViewById(C1195R.id.close_about_screen);
            this.descriptionTextView = (TextView) findViewById(C1195R.id.description);
            this.appName = (TextView) findViewById(C1195R.id.about_app_name);
            this.appVersion = (TextView) findViewById(C1195R.id.app_version);
            this.templateVersion = (TextView) findViewById(C1195R.id.template_version);
            this.tos = (Button) findViewById(C1195R.id.app_tos);
            this.privacy = (Button) findViewById(C1195R.id.app_privacy);
            try {
                PackageManager packageManager = getPackageManager();
                String appLabel = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(getPackageName(), 128));
                this.appVersion.setText("Version " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
                this.appName.setText(appLabel);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
            final String link = "http://www.appsgeyser.com?pn=" + getPackageName();
            this.logoImageView.setImageDrawable(getPackageManager().getApplicationIcon(getPackageName()));
            this.logoImageView.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(link));
                    intent.setFlags(ErrorDialogData.BINDER_CRASH);
                    AboutDialogActivity.this.startActivity(intent);
                    AppsgeyserServerClient.getInstance().sendAboutDialogVisitSite(AboutDialogActivity.this.getApplicationContext());
                }
            });
            this.closeScreenImageView.setOnClickListener(new C13092());
            this.templateVersion.setText(getString(C1195R.string.appsgeysersdk_build, new Object[]{Constants.PLATFORM_VERSION}));
            String templateDescription = getResources().getString(C1195R.string.appsgeysersdk_description_with_publish_name);
            if (this.config.getPublisherName().length() == 0) {
                templateDescription = getResources().getString(C1195R.string.appsgeysersdk_description_without_publish_name);
            }
            if (this.descriptionType != null) {
                String str = this.descriptionType;
                Object obj = -1;
                switch (str.hashCode()) {
                    case -1349088399:
                        if (str.equals("custom")) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 1502878931:
                        if (str.equals(APPSGEYSER_DESCRIPTION_LOGO)) {
                            int i = 1;
                            break;
                        }
                        break;
                    case 1503108181:
                        if (str.equals(APPSGEYSER_DESCRIPTION_TEXT)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        this.appsgeyserDescriptionLogoImageView.setVisibility(8);
                        this.descriptionTextView.setVisibility(0);
                        break;
                    case 1:
                        this.appsgeyserDescriptionLogoImageView.setVisibility(0);
                        this.descriptionTextView.setVisibility(8);
                        break;
                    case 2:
                        templateDescription = this.description;
                        this.appsgeyserDescriptionLogoImageView.setVisibility(8);
                        this.descriptionTextView.setVisibility(0);
                        break;
                    default:
                        this.appsgeyserDescriptionLogoImageView.setVisibility(8);
                        this.descriptionTextView.setVisibility(0);
                        break;
                }
            }
            this.descriptionTextView.setText(Html.fromHtml(templateDescription.replace("PUB_NAME", this.config.getPublisherName()).replace("APPSGEYSER_URL", link).replace("APP_VERSION", "").replace("APP_ID", appId)));
            this.descriptionTextView.setLinksClickable(true);
            this.descriptionTextView.setMovementMethod(LinkMovementMethod.getInstance());
            this.tos.setOnClickListener(new C13103());
            this.privacy.setOnClickListener(new C13114());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(CONFIG_PHP_KEY, this.configPhp);
        super.onSaveInstanceState(savedInstanceState);
    }
}
