package com.bgjd.ici.p031f;

import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p028c.C1439f;
import com.bgjd.ici.p028c.C1440g;
import com.bgjd.ici.p028c.C1441h;
import com.bgjd.ici.p028c.C1442i;
import com.bgjd.ici.p030e.C1479d;

public interface C1490c {
    @C1442i(query = "UPDATE TBL_COM_BGJD_ICI_BROWSING SET status = 1 WHERE id=#{id}")
    long m3174a(@C1440g("id") int i);

    @C1442i(query = "UPDATE TBL_COM_BGJD_ICI_BROWSING SET status = 2,logged=1  WHERE brw_id <=#{id} AND browsername = '#{name}'")
    long m3175a(@C1440g("id") long j, @C1440g("name") String str);

    @C1441h(query = "SELECT id,brw_id,name,url,bookmark,created,date FROM TBL_COM_BGJD_ICI_BROWSING WHERE status = #{status} AND logged = 0 AND browsername = '#{name}' and brw_id >= #{browsing_id} ORDER BY brw_id ASC LIMIT #{limit}")
    C1436e<C1479d> m3176a(@C1440g("status") int i, @C1440g("name") String str, @C1440g("browsing_id") long j, @C1440g("limit") int i2);

    @C1441h(query = "SELECT id,brw_id,name,url,bookmark,created,date FROM TBL_COM_BGJD_ICI_BROWSING WHERE status = 1 AND logged = 0 AND browsername = '#{name}' ORDER BY brw_id ASC LIMIT #{limit}")
    C1436e<C1479d> m3177a(@C1440g("name") String str, @C1440g("limit") int i);

    @C1439f(query = "")
    boolean m3178a(String str);
}
