package com.bgjd.ici.p031f;

import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p028c.C1438c;
import com.bgjd.ici.p028c.C1439f;
import com.bgjd.ici.p028c.C1440g;
import com.bgjd.ici.p028c.C1441h;
import com.bgjd.ici.p028c.C1442i;
import com.bgjd.ici.p030e.C1476a;

public interface C1488a {
    @C1442i(query = "UPDATE TBL_COM_BGJD_ICI_DETECTED_BEACONS SET status = 1 WHERE id=#{id}")
    long m3162a(@C1440g("id") int i);

    @C1442i(query = "UPDATE TBL_COM_BGJD_ICI_DETECTED_BEACONS SET status = 2,logged=1  WHERE id <= #{id}")
    long m3163a(@C1440g("id") long j);

    @C1441h(query = "SELECT id,beacon_uid,major,minor,rssi,power,bluetooth_address,beacon_type,service_uuid,manufacturer,bluetooth_name,distance,telemetry_version,battery_milli_volts,pdu_count,up_time,url,bid,pid,lid,status FROM TBL_COM_BGJD_ICI_DETECTED_BEACONS WHERE status = #{status} AND logged = 0 ORDER BY id ASC LIMIT #{limit}")
    C1436e<C1476a> m3164a(@C1440g("status") int i, @C1440g("limit") int i2);

    @C1439f(query = "")
    boolean m3165a(C1476a c1476a);

    @C1438c(query = "DELETE FROM TBL_COM_BGJD_ICI_DETECTED_BEACONS WHERE status = 2 AND logged=1 AND id <= #{id}")
    boolean m3166b(@C1440g("id") long j);
}
