package com.adcolony.sdk;

import com.adcolony.sdk.aa.C0595a;
import org.json.JSONObject;

class C0742m implements ah {
    C0742m() {
        C0594a.m609a("CustomMessage.controller_send", (ah) this);
    }

    public void mo1863a(af afVar) {
        JSONObject c = afVar.m698c();
        final String b = C0802y.m1468b(c, "type");
        final String b2 = C0802y.m1468b(c, "message");
        az.m880a(new Runnable(this) {
            final /* synthetic */ C0742m f1320c;

            public void run() {
                new C0595a().m622a("Received custom message ").m622a(b2).m622a(" of type ").m622a(b).m624a(aa.f480d);
                try {
                    AdColonyCustomMessageListener adColonyCustomMessageListener = (AdColonyCustomMessageListener) C0594a.m605a().m1296z().get(b);
                    if (adColonyCustomMessageListener != null) {
                        adColonyCustomMessageListener.onAdColonyCustomMessage(new AdColonyCustomMessage(b, b2));
                    }
                } catch (RuntimeException e) {
                }
            }
        });
    }
}
