package com.fyber.utils.testsuite;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.fyber.Fyber;
import com.fyber.Fyber$Settings.UIStringIdentifier;
import com.fyber.utils.StringUtils;
import com.fyber.utils.testsuite.IntegrationAnalyzer.FailReason;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuiteActivity extends Activity implements IntegrationAnalysisListener {
    private LinearLayout f6668a;
    private ProgressBar f6669b;
    private LinearLayout f6670c;
    private IntegrationReport f6671d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        this.f6668a = new LinearLayout(getBaseContext());
        this.f6668a.setLayoutParams(new LayoutParams(-1, -1));
        this.f6668a.setBackgroundColor(-1);
        this.f6668a.setOrientation(1);
        View linearLayout = new LinearLayout(getBaseContext());
        TypedArray obtainStyledAttributes = getBaseContext().getTheme().obtainStyledAttributes(new int[]{16843499});
        int dimension = (int) obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
        linearLayout.setLayoutParams(new LayoutParams(-1, dimension));
        int a = m8572a(5.0f);
        linearLayout.setPadding(a, a, a, a);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setBackgroundColor(-16737058);
        View linearLayout2 = new LinearLayout(getBaseContext());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        linearLayout2.setLayoutParams(layoutParams);
        linearLayout2.setBackgroundColor(0);
        View textView = new TextView(getBaseContext());
        textView.setContentDescription("Test Suite Info");
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(String.format(m8573a(UIStringIdentifier.TEST_SUITE_VERSION), new Object[]{"1.0.0"}));
        textView.setTextColor(-1);
        View textView2 = new TextView(getBaseContext());
        textView2.setContentDescription("SDK Info");
        textView2.setLayoutParams(new LayoutParams(-2, -2));
        textView2.setText(String.format(m8573a(UIStringIdentifier.SDK_VERSION), new Object[]{Fyber.RELEASE_VERSION_STRING}));
        textView2.setTextColor(-1);
        linearLayout2.addView(textView);
        linearLayout2.addView(textView2);
        linearLayout.addView(linearLayout2);
        this.f6668a.addView(linearLayout);
        linearLayout = new ScrollView(getBaseContext());
        linearLayout.setFillViewport(true);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        this.f6670c = new LinearLayout(getBaseContext());
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, 0);
        layoutParams2.weight = 1.0f;
        this.f6670c.setLayoutParams(layoutParams2);
        this.f6670c.setOrientation(1);
        linearLayout.addView(this.f6670c);
        this.f6668a.addView(linearLayout);
        this.f6670c.setGravity(17);
        this.f6669b = new ProgressBar(getBaseContext());
        ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        layoutParams3.gravity = 17;
        this.f6669b.setLayoutParams(layoutParams3);
        this.f6670c.addView(this.f6669b);
        setContentView(this.f6668a);
        IntegrationAnalyzer.analyze(this);
    }

    public void onAnalysisSucceeded(IntegrationReport integrationReport) {
        this.f6671d = integrationReport;
        this.f6669b.setVisibility(8);
        this.f6670c.setGravity(49);
        if (!integrationReport.isAnnotationsCorrectlyIntegrated() || !integrationReport.isAnnotationsCompatible()) {
            m8575a(m8573a(UIStringIdentifier.ANNOTATIONS_PROBLEM_DESCRIPTION), -9079435, "Sub Hint Text Info");
            m8575a(m8573a(UIStringIdentifier.ANNOTATIONS_PROBLEM), -2937041, "Hint Text Info");
        } else if (StringUtils.nullOrEmpty(Fyber.getConfigs().m7608i().m7594c())) {
            m8575a(m8573a(UIStringIdentifier.TOKEN_MISSING), -141259, "Hint Text Info");
        } else if (integrationReport.getStartedBundles().isEmpty() && integrationReport.getUnstartedBundles().isEmpty()) {
            m8575a(m8573a(UIStringIdentifier.NO_BUNDLES), -141259, "Hint Text Info");
        }
        List startedBundles = this.f6671d.getStartedBundles();
        if (!startedBundles.isEmpty()) {
            Collections.sort(startedBundles, new C2678a());
            m8574a(m8573a(UIStringIdentifier.STARTED_BUNDLES_TITLE), m8573a(UIStringIdentifier.STARTED_BUNDLES_MESSAGE), startedBundles);
        }
        startedBundles = this.f6671d.getUnstartedBundles();
        if (!startedBundles.isEmpty()) {
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            Collections.sort(startedBundles, new C2678a());
            m8576a(startedBundles, arrayList, arrayList2);
            if (!arrayList.isEmpty()) {
                m8574a(m8573a(UIStringIdentifier.BUNDLES_NOT_STARTED_TITLE), m8573a(UIStringIdentifier.BUNDLES_NOT_STARTED_MESSAGE), arrayList);
            }
            if (!arrayList2.isEmpty()) {
                m8574a(m8573a(UIStringIdentifier.MISSING_BUNDLES_TITLE), m8573a(UIStringIdentifier.MISSING_BUNDLES_MESSAGE), arrayList2);
            }
        }
    }

    public void onAnalysisFailed(FailReason failReason) {
        this.f6669b.setVisibility(8);
        this.f6670c.setGravity(49);
        m8575a(m8573a(UIStringIdentifier.SDK_NOT_STARTED), -2937041, "Hint Text Info");
    }

    private void m8575a(String str, int i, String str2) {
        View linearLayout = new LinearLayout(getBaseContext());
        linearLayout.setLayoutParams(new LayoutParams(-1, -2));
        int a = m8572a(8.0f);
        int a2 = m8572a(3.0f);
        linearLayout.setPadding(a, a2, a, a2);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        View textView = new TextView(getBaseContext());
        textView.setContentDescription(str2);
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setTextColor(-1);
        textView.setGravity(17);
        linearLayout.setBackgroundColor(i);
        textView.setText(str);
        linearLayout.addView(textView);
        this.f6670c.addView(linearLayout);
    }

    private void m8574a(CharSequence charSequence, CharSequence charSequence2, List<MediationBundleInfo> list) {
        View linearLayout = new LinearLayout(getBaseContext());
        int a = m8572a(7.0f);
        linearLayout.setPadding(a, a, a, a);
        linearLayout.setOrientation(1);
        View textView = new TextView(getBaseContext());
        textView.setLayoutParams(new LayoutParams(-1, -2));
        textView.setGravity(19);
        textView.setTextColor(-9079435);
        textView.setText(charSequence);
        View linearLayout2 = new LinearLayout(getBaseContext());
        linearLayout2.setPadding(m8572a(5.0f), 0, 0, 0);
        linearLayout2.setOrientation(1);
        linearLayout.addView(textView);
        textView = new View(getBaseContext());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, m8572a(2.0f));
        layoutParams.setMargins(0, 0, 0, m8572a(2.0f));
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundColor(-9079435);
        linearLayout.addView(textView);
        for (MediationBundleInfo mediationBundleInfo : list) {
            CharSequence charSequence3;
            CharSequence charSequence4;
            View linearLayout3 = new LinearLayout(getBaseContext());
            linearLayout3.setGravity(16);
            linearLayout3.setLayoutParams(new LayoutParams(-1, m8572a(50.0f)));
            linearLayout3.setOrientation(0);
            View view = new View(getBaseContext());
            view.setLayoutParams(new LayoutParams(m8572a(5.0f), -1));
            if (mediationBundleInfo.isStarted()) {
                view.setBackgroundColor(-11751600);
                charSequence3 = "Started Bundle";
            } else if (mediationBundleInfo.isIntegrated()) {
                view.setBackgroundColor(-769226);
                charSequence3 = "Not Started Bundle";
            } else {
                view.setBackgroundColor(-5317);
                charSequence3 = "Missing Bundle";
            }
            linearLayout3.addView(view);
            view = new TextView(getBaseContext());
            view.setContentDescription(charSequence3);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            layoutParams2.setMargins(m8572a(3.0f), 0, 0, 0);
            view.setLayoutParams(layoutParams2);
            view.setTextColor(-16777216);
            String networkName = mediationBundleInfo.getNetworkName();
            networkName = Character.toUpperCase(networkName.charAt(0)) + networkName.substring(1);
            String version = mediationBundleInfo.getVersion();
            if (StringUtils.nullOrEmpty(version)) {
                charSequence4 = networkName;
            } else {
                charSequence4 = networkName + " - " + version;
            }
            view.setText(charSequence4);
            linearLayout3.addView(view);
            linearLayout2.addView(linearLayout3);
            textView = new View(getBaseContext());
            textView.setLayoutParams(new LayoutParams(-1, m8572a(1.0f)));
            textView.setBackgroundColor(-2039584);
            linearLayout2.addView(textView);
        }
        textView = new TextView(getBaseContext());
        textView.setLayoutParams(new LayoutParams(-1, -2));
        textView.setGravity(17);
        textView.setTextColor(-9079435);
        textView.setText(charSequence2);
        linearLayout2.addView(textView);
        linearLayout.addView(linearLayout2);
        this.f6670c.addView(linearLayout);
    }

    private static void m8576a(List<MediationBundleInfo> list, List<MediationBundleInfo> list2, List<MediationBundleInfo> list3) {
        for (MediationBundleInfo mediationBundleInfo : list) {
            if (mediationBundleInfo.isIntegrated()) {
                list2.add(mediationBundleInfo);
            } else {
                list3.add(mediationBundleInfo);
            }
        }
    }

    private int m8572a(float f) {
        return (int) TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    private static String m8573a(UIStringIdentifier uIStringIdentifier) {
        return Fyber.getConfigs().m7601b().getUIString(uIStringIdentifier);
    }
}
