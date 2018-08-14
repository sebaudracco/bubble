package com.bgjd.ici.p031f;

import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p028c.C1439f;
import com.bgjd.ici.p028c.C1440g;
import com.bgjd.ici.p028c.C1441h;
import com.bgjd.ici.p028c.C1442i;
import com.bgjd.ici.p030e.C1486i;

public interface C1493f {
    @C1442i(query = "UPDATE TBL_COM_BGJD_ICI_PROCESS SET status = 2,logged=1  WHERE id <= #{id}")
    long m3189a(@C1440g("id") long j);

    @C1441h(query = "SELECT id,processname,importance FROM TBL_COM_BGJD_ICI_PROCESS WHERE status = #{status} AND logged = 0 ORDER BY id ASC")
    C1436e<C1486i> m3190a(@C1440g("status") int i);

    @C1439f(query = "")
    boolean m3191a(String str);

    @C1442i(query = "UPDATE TBL_COM_BGJD_ICI_PROCESS SET status = 1 WHERE id=#{id}")
    long m3192b(@C1440g("id") int i);
}
