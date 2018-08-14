package com.vungle.publisher;

import com.vungle.publisher.eb.b;
import com.vungle.publisher.log.Logger;
import java.util.List;

/* compiled from: vungle */
class eb$b$3 extends eb$b$a {
    final /* synthetic */ List f9975a;
    final /* synthetic */ b f9976b;

    eb$b$3(b bVar, List list) {
        this.f9976b = bVar;
        this.f9975a = list;
        super(bVar);
    }

    int mo6934a(eb<?, ?, ?> ebVar) {
        Logger.v(Logger.PREPARE_TAG, "DeleteCommand : cleanUpInactivePlacements");
        return ebVar.b(this.f9975a);
    }
}
