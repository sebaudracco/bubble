package com.fyber.utils.cookies;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpCookie;

/* compiled from: SerializableHttpCookie */
public class C2644b implements Serializable {
    private static final long serialVersionUID = -6051428667568260064L;
    private transient HttpCookie f6585a;

    public final HttpCookie m8468a() {
        return this.f6585a;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.f6585a.getName());
        objectOutputStream.writeObject(this.f6585a.getValue());
        objectOutputStream.writeObject(this.f6585a.getComment());
        objectOutputStream.writeObject(this.f6585a.getCommentURL());
        objectOutputStream.writeBoolean(this.f6585a.getDiscard());
        objectOutputStream.writeObject(this.f6585a.getDomain());
        objectOutputStream.writeLong(this.f6585a.getMaxAge());
        objectOutputStream.writeObject(this.f6585a.getPath());
        objectOutputStream.writeObject(this.f6585a.getPortlist());
        objectOutputStream.writeBoolean(this.f6585a.getSecure());
        objectOutputStream.writeInt(this.f6585a.getVersion());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.f6585a = new HttpCookie((String) objectInputStream.readObject(), (String) objectInputStream.readObject());
        this.f6585a.setComment((String) objectInputStream.readObject());
        this.f6585a.setCommentURL((String) objectInputStream.readObject());
        this.f6585a.setDiscard(objectInputStream.readBoolean());
        this.f6585a.setDomain((String) objectInputStream.readObject());
        this.f6585a.setMaxAge(objectInputStream.readLong());
        this.f6585a.setPath((String) objectInputStream.readObject());
        this.f6585a.setPortlist((String) objectInputStream.readObject());
        this.f6585a.setSecure(objectInputStream.readBoolean());
        this.f6585a.setVersion(objectInputStream.readInt());
    }
}
