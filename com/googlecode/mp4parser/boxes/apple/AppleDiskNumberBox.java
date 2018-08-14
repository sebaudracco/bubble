package com.googlecode.mp4parser.boxes.apple;

import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;

public class AppleDiskNumberBox extends AppleDataBox {
    private static final /* synthetic */ StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_3 = null;
    int f6679a;
    short f6680b;

    static {
        ajc$preClinit();
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("AppleDiskNumberBox.java", AppleDiskNumberBox.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getA", "com.googlecode.mp4parser.boxes.apple.AppleDiskNumberBox", "", "", "", SchemaSymbols.ATTVAL_INT), 16);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setA", "com.googlecode.mp4parser.boxes.apple.AppleDiskNumberBox", SchemaSymbols.ATTVAL_INT, "a", "", "void"), 20);
        ajc$tjp_2 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getB", "com.googlecode.mp4parser.boxes.apple.AppleDiskNumberBox", "", "", "", SchemaSymbols.ATTVAL_SHORT), 24);
        ajc$tjp_3 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setB", "com.googlecode.mp4parser.boxes.apple.AppleDiskNumberBox", SchemaSymbols.ATTVAL_SHORT, "b", "", "void"), 28);
    }

    public AppleDiskNumberBox() {
        super("disk", 0);
    }

    public int getA() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.f6679a;
    }

    public void setA(int a) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(a)));
        this.f6679a = a;
    }

    public short getB() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.f6680b;
    }

    public void setB(short b) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.shortObject(b)));
        this.f6680b = b;
    }

    protected byte[] writeData() {
        ByteBuffer bb = ByteBuffer.allocate(6);
        bb.putInt(this.f6679a);
        bb.putShort(this.f6680b);
        return bb.array();
    }

    protected void parseData(ByteBuffer data) {
        this.f6679a = data.getInt();
        this.f6680b = data.getShort();
    }

    protected int getDataLength() {
        return 6;
    }
}
