package com.fyber.utils.cookies;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/* compiled from: PersistentHttpCookieStore */
class a$a extends ObjectInputStream {
    final /* synthetic */ a f6584a;

    public a$a(a aVar, InputStream inputStream) throws IOException {
        this.f6584a = aVar;
        super(inputStream);
    }

    protected final ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        super.readClassDescriptor();
        return ObjectStreamClass.lookup(C2644b.class);
    }
}
