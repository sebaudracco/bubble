package com.googlecode.mp4parser.boxes.apple;

import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;

public class AppleTrackNumberBox extends AppleDataBox {
    private static final /* synthetic */ StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_3 = null;
    int f6681a;
    int f6682b;

    static {
        ajc$preClinit();
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("AppleTrackNumberBox.java", AppleTrackNumberBox.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getA", "com.googlecode.mp4parser.boxes.apple.AppleTrackNumberBox", "", "", "", SchemaSymbols.ATTVAL_INT), 16);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setA", "com.googlecode.mp4parser.boxes.apple.AppleTrackNumberBox", SchemaSymbols.ATTVAL_INT, "a", "", "void"), 20);
        ajc$tjp_2 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getB", "com.googlecode.mp4parser.boxes.apple.AppleTrackNumberBox", "", "", "", SchemaSymbols.ATTVAL_INT), 24);
        ajc$tjp_3 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setB", "com.googlecode.mp4parser.boxes.apple.AppleTrackNumberBox", SchemaSymbols.ATTVAL_INT, "b", "", "void"), 28);
    }

    public AppleTrackNumberBox() {
        super("trkn", 0);
    }

    public int getA() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.f6681a;
    }

    public void setA(int a) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(a)));
        this.f6681a = a;
    }

    public int getB() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.f6682b;
    }

    public void setB(int b) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(b)));
        this.f6682b = b;
    }

    protected byte[] writeData() {
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putInt(this.f6681a);
        bb.putInt(this.f6682b);
        return bb.array();
    }

    protected void parseData(ByteBuffer data) {
        this.f6681a = data.getInt();
        this.f6682b = data.getInt();
    }

    protected int getDataLength() {
        return 8;
    }
}
