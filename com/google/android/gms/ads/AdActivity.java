package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzaap;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzkb;

@KeepForSdkWithMembers
public class AdActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzaap zzuj;

    private final void zzax() {
        if (this.zzuj != null) {
            try {
                this.zzuj.zzax();
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.zzuj.onActivityResult(i, i2, intent);
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        boolean z = true;
        try {
            if (this.zzuj != null) {
                z = this.zzuj.zznj();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        if (z) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            this.zzuj.zzo(ObjectWrapper.wrap(configuration));
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzuj = zzkb.zzig().zzb(this);
        if (this.zzuj == null) {
            zzane.zzd("#007 Could not call remote method.", null);
            finish();
            return;
        }
        try {
            this.zzuj.onCreate(bundle);
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
    }

    protected void onDestroy() {
        try {
            if (this.zzuj != null) {
                this.zzuj.onDestroy();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
        super.onDestroy();
    }

    protected void onPause() {
        try {
            if (this.zzuj != null) {
                this.zzuj.onPause();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
        super.onPause();
    }

    protected void onRestart() {
        super.onRestart();
        try {
            if (this.zzuj != null) {
                this.zzuj.onRestart();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
    }

    protected void onResume() {
        super.onResume();
        try {
            if (this.zzuj != null) {
                this.zzuj.onResume();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        try {
            if (this.zzuj != null) {
                this.zzuj.onSaveInstanceState(bundle);
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    protected void onStart() {
        super.onStart();
        try {
            if (this.zzuj != null) {
                this.zzuj.onStart();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
    }

    protected void onStop() {
        try {
            if (this.zzuj != null) {
                this.zzuj.onStop();
            }
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
            finish();
        }
        super.onStop();
    }

    public void setContentView(int i) {
        super.setContentView(i);
        zzax();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        zzax();
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        zzax();
    }
}
