package com.vungle.publisher.inject;

import com.vungle.publisher.VungleAdActivity;
import com.vungle.publisher.VunglePubBase;
import com.vungle.publisher.env.AndroidDevice;
import com.vungle.publisher.my;
import com.vungle.publisher.ob;
import com.vungle.publisher.op;
import com.vungle.publisher.qw;
import com.vungle.publisher.sv;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {C1638a.class, EndpointModule.class, C1639t.class})
/* compiled from: vungle */
public interface C1640w {
    void m4198a(VungleAdActivity vungleAdActivity);

    void m4199a(VunglePubBase vunglePubBase);

    void m4200a(AndroidDevice androidDevice);

    void m4201a(my myVar);

    void m4202a(ob obVar);

    void m4203a(op opVar);

    void m4204a(qw qwVar);

    void m4205a(sv svVar);
}
