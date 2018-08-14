package com.areametrics.areametricssdk;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.Locale;

final class C1373f {
    static String m2567a() {
        String str = "release".equals("debug") ? "release".equals(ImagesContract.LOCAL) ? "L" : "S" : " ";
        return String.format(Locale.US, "A%s.%d%s", new Object[]{"2.3", Integer.valueOf(3), str});
    }
}
