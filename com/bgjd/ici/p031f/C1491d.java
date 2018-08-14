package com.bgjd.ici.p031f;

import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p028c.C1439f;
import com.bgjd.ici.p028c.C1440g;
import com.bgjd.ici.p028c.C1441h;
import com.bgjd.ici.p028c.C1442i;
import com.bgjd.ici.p029d.C1462m;
import com.bgjd.ici.p030e.C1481f;

public interface C1491d {
    @C1442i(query = "UPDATE TBL_COM_BGJD_ICI_PACKAGE SET status = 1 WHERE id=#{id}")
    long m3179a(@C1440g("id") int i);

    @C1442i(query = "UPDATE TBL_COM_BGJD_ICI_PACKAGE SET status = 2, logged=1   WHERE id<=#{maxid} AND installdate<=#{id}")
    long m3180a(@C1440g("maxid") int i, @C1440g("id") long j);

    @C1441h(query = "SELECT id,name,package,is_system,installdate,hassdk FROM TBL_COM_BGJD_ICI_PACKAGE WHERE status = 1 AND logged = 0 ORDER BY installdate ASC LIMIT 200")
    C1436e<C1481f> m3181a();

    @C1441h(query = "SELECT id,name,package,is_system,installdate,hassdk FROM TBL_COM_BGJD_ICI_PACKAGE WHERE status = 0 AND logged = 0 AND installdate > #{date} and package != '#{package}' ORDER BY installdate ASC LIMIT #{limit}")
    C1436e<C1481f> m3182a(@C1440g("date") long j, @C1440g("package") String str, @C1440g("limit") int i);

    @C1439f(query = "")
    boolean m3183a(C1462m c1462m);
}
