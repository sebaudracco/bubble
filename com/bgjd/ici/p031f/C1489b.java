package com.bgjd.ici.p031f;

import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p028c.C1439f;
import com.bgjd.ici.p028c.C1440g;
import com.bgjd.ici.p028c.C1441h;
import com.bgjd.ici.p030e.C1477b;
import com.bgjd.ici.p030e.C1478c;

public interface C1489b {
    @C1441h(query = "SELECT id,bid,pid,lid,uuid,lat,lon,radius FROM TBL_COM_BGJD_ICI_BEACONS LIMIT #{limit}")
    C1436e<C1477b> m3167a(@C1440g("limit") int i);

    @C1439f(query = "")
    boolean m3168a(C1477b c1477b);

    @C1439f(query = "INSERT OR IGNORE INTO TBL_COM_BGJD_ICI_BEACON_LAYOUT (beacon_layout) VALUES('#{beacon_layout}')")
    boolean m3169a(@C1440g("beacon_layout") String str);

    @C1441h(query = "SELECT id,bid,pid,lid,uuid,lat,lon,radius FROM TBL_COM_BGJD_ICI_BEACONS WHERE uuid='#{uuid}'")
    C1436e<C1477b> m3170b(@C1440g("uuid") String str);

    @C1439f(query = "DELETE FROM TBL_COM_BGJD_ICI_BEACONS WHERE id >= #{id}")
    boolean m3171b(@C1440g("id") int i);

    @C1441h(query = "SELECT id,beacon_layout FROM TBL_COM_BGJD_ICI_BEACON_LAYOUT LIMIT #{limit}")
    C1436e<C1478c> m3172c(@C1440g("limit") int i);

    @C1439f(query = "DELETE FROM TBL_COM_BGJD_ICI_BEACON_LAYOUT WHERE id >= #{id}")
    boolean m3173d(@C1440g("id") int i);
}
