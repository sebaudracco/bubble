package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.view.View;
import com.facebook.ads.internal.n.e;
import com.facebook.ads.internal.p033n.C2020c;
import com.facebook.ads.internal.p033n.C2026f;
import com.facebook.ads.internal.p033n.C2027g;
import com.facebook.ads.internal.p033n.C2028h;
import com.facebook.ads.internal.p033n.C2030j;
import com.facebook.ads.internal.p033n.e$d;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.List;
import java.util.Map;

public abstract class ab implements AdAdapter {
    public abstract String mo3629A();

    public abstract List<e> mo3630B();

    public abstract int mo3631C();

    public abstract int mo3632D();

    public abstract C2020c mo3633E();

    public String mo3689G() {
        return mo3658q();
    }

    public abstract void mo3635a(int i);

    public abstract void mo3636a(Context context, ac acVar, C2012c c2012c, Map<String, Object> map, e$d com_facebook_ads_internal_n_e_d);

    public abstract void mo3637a(View view, List<View> list);

    public abstract void mo3638a(ac acVar);

    public abstract void mo3639a(Map<String, String> map);

    public boolean a_() {
        return true;
    }

    public abstract void mo3640b(Map<String, String> map);

    public abstract void b_();

    public abstract String mo3642c();

    public abstract boolean c_();

    public abstract boolean mo3644d();

    public abstract boolean mo3645e();

    public abstract boolean mo3646f();

    public abstract boolean mo3647g();

    public final AdPlacementType getPlacementType() {
        return AdPlacementType.NATIVE;
    }

    public abstract boolean mo3648h();

    public abstract int mo3649i();

    public abstract int mo3650j();

    public abstract int mo3651k();

    public abstract C2026f mo3652l();

    public abstract C2026f mo3653m();

    public abstract C2028h mo3654n();

    public abstract String mo3655o();

    public abstract String mo3657p();

    public abstract String mo3658q();

    public abstract String mo3659r();

    public abstract String mo3660s();

    public abstract C2027g mo3661t();

    public abstract C2026f mo3662u();

    public abstract String mo3663v();

    public abstract String mo3664w();

    public abstract String mo3665x();

    public abstract String mo3666y();

    public abstract C2030j mo3667z();
}
