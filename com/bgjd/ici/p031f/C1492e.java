package com.bgjd.ici.p031f;

import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p028c.C1439f;
import com.bgjd.ici.p028c.C1440g;
import com.bgjd.ici.p028c.C1441h;
import com.bgjd.ici.p028c.C1442i;
import com.bgjd.ici.p030e.C1482g;

public interface C1492e {
    @C1442i(query = "UPDATE TBL_COM_BGJD_ICI_PTR_LOGS SET status = #{status} WHERE id =#{id}")
    long m3184a(@C1440g("status") int i, @C1440g("id") long j);

    @C1441h(query = "SELECT id,data_type,name,logs,status FROM TBL_COM_BGJD_ICI_PTR_LOGS WHERE status = #{status} ORDER BY id ASC LIMIT #{limit}")
    C1436e<C1482g> m3185a(@C1440g("status") int i, @C1440g("limit") int i2);

    @C1439f(query = "DELETE FROM TBL_COM_BGJD_ICI_PTR_LOGS WHERE status = 2 AND id <= #{id}")
    boolean m3186a(@C1440g("id") int i);

    @C1439f(query = "")
    boolean m3187a(C1482g c1482g);

    @C1442i(query = "UPDATE TBL_COM_BGJD_ICI_PTR_LOGS SET status = #{status} WHERE status=1 AND id <=#{id}")
    long m3188b(@C1440g("status") int i, @C1440g("id") long j);
}
