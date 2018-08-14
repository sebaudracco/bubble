package com.googlecode.mp4parser.boxes;

import com.googlecode.mp4parser.AbstractBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import com.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitWriterBuffer;
import java.nio.ByteBuffer;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.runtime.internal.Conversions;
import org.aspectj.runtime.reflect.Factory;

public class AC3SpecificBox extends AbstractBox {
    public static final String TYPE = "dac3";
    private static final /* synthetic */ StaticPart ajc$tjp_0 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_1 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_10 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_11 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_12 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_13 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_14 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_2 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_3 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_4 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_5 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_6 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_7 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_8 = null;
    private static final /* synthetic */ StaticPart ajc$tjp_9 = null;
    int acmod;
    int bitRateCode;
    int bsid;
    int bsmod;
    int fscod;
    int lfeon;
    int reserved;

    static {
        ajc$preClinit();
    }

    private static /* synthetic */ void ajc$preClinit() {
        Factory factory = new Factory("AC3SpecificBox.java", AC3SpecificBox.class);
        ajc$tjp_0 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getFscod", "com.googlecode.mp4parser.boxes.AC3SpecificBox", "", "", "", SchemaSymbols.ATTVAL_INT), 55);
        ajc$tjp_1 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setFscod", "com.googlecode.mp4parser.boxes.AC3SpecificBox", SchemaSymbols.ATTVAL_INT, "fscod", "", "void"), 59);
        ajc$tjp_10 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getBitRateCode", "com.googlecode.mp4parser.boxes.AC3SpecificBox", "", "", "", SchemaSymbols.ATTVAL_INT), 95);
        ajc$tjp_11 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setBitRateCode", "com.googlecode.mp4parser.boxes.AC3SpecificBox", SchemaSymbols.ATTVAL_INT, "bitRateCode", "", "void"), 99);
        ajc$tjp_12 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getReserved", "com.googlecode.mp4parser.boxes.AC3SpecificBox", "", "", "", SchemaSymbols.ATTVAL_INT), 103);
        ajc$tjp_13 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setReserved", "com.googlecode.mp4parser.boxes.AC3SpecificBox", SchemaSymbols.ATTVAL_INT, "reserved", "", "void"), 107);
        ajc$tjp_14 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "toString", "com.googlecode.mp4parser.boxes.AC3SpecificBox", "", "", "", "java.lang.String"), 112);
        ajc$tjp_2 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getBsid", "com.googlecode.mp4parser.boxes.AC3SpecificBox", "", "", "", SchemaSymbols.ATTVAL_INT), 63);
        ajc$tjp_3 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setBsid", "com.googlecode.mp4parser.boxes.AC3SpecificBox", SchemaSymbols.ATTVAL_INT, "bsid", "", "void"), 67);
        ajc$tjp_4 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getBsmod", "com.googlecode.mp4parser.boxes.AC3SpecificBox", "", "", "", SchemaSymbols.ATTVAL_INT), 71);
        ajc$tjp_5 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setBsmod", "com.googlecode.mp4parser.boxes.AC3SpecificBox", SchemaSymbols.ATTVAL_INT, "bsmod", "", "void"), 75);
        ajc$tjp_6 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getAcmod", "com.googlecode.mp4parser.boxes.AC3SpecificBox", "", "", "", SchemaSymbols.ATTVAL_INT), 79);
        ajc$tjp_7 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setAcmod", "com.googlecode.mp4parser.boxes.AC3SpecificBox", SchemaSymbols.ATTVAL_INT, "acmod", "", "void"), 83);
        ajc$tjp_8 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "getLfeon", "com.googlecode.mp4parser.boxes.AC3SpecificBox", "", "", "", SchemaSymbols.ATTVAL_INT), 87);
        ajc$tjp_9 = factory.makeSJP(JoinPoint.METHOD_EXECUTION, factory.makeMethodSig(SchemaSymbols.ATTVAL_TRUE_1, "setLfeon", "com.googlecode.mp4parser.boxes.AC3SpecificBox", SchemaSymbols.ATTVAL_INT, "lfeon", "", "void"), 91);
    }

    public AC3SpecificBox() {
        super(TYPE);
    }

    protected long getContentSize() {
        return 3;
    }

    public void _parseDetails(ByteBuffer content) {
        BitReaderBuffer brb = new BitReaderBuffer(content);
        this.fscod = brb.readBits(2);
        this.bsid = brb.readBits(5);
        this.bsmod = brb.readBits(3);
        this.acmod = brb.readBits(3);
        this.lfeon = brb.readBits(1);
        this.bitRateCode = brb.readBits(5);
        this.reserved = brb.readBits(5);
    }

    protected void getContent(ByteBuffer byteBuffer) {
        BitWriterBuffer bwb = new BitWriterBuffer(byteBuffer);
        bwb.writeBits(this.fscod, 2);
        bwb.writeBits(this.bsid, 5);
        bwb.writeBits(this.bsmod, 3);
        bwb.writeBits(this.acmod, 3);
        bwb.writeBits(this.lfeon, 1);
        bwb.writeBits(this.bitRateCode, 5);
        bwb.writeBits(this.reserved, 5);
    }

    public int getFscod() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.fscod;
    }

    public void setFscod(int fscod) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(fscod)));
        this.fscod = fscod;
    }

    public int getBsid() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.bsid;
    }

    public void setBsid(int bsid) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(bsid)));
        this.bsid = bsid;
    }

    public int getBsmod() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.bsmod;
    }

    public void setBsmod(int bsmod) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(bsmod)));
        this.bsmod = bsmod;
    }

    public int getAcmod() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.acmod;
    }

    public void setAcmod(int acmod) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(acmod)));
        this.acmod = acmod;
    }

    public int getLfeon() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.lfeon;
    }

    public void setLfeon(int lfeon) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, Conversions.intObject(lfeon)));
        this.lfeon = lfeon;
    }

    public int getBitRateCode() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return this.bitRateCode;
    }

    public void setBitRateCode(int bitRateCode) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, Conversions.intObject(bitRateCode)));
        this.bitRateCode = bitRateCode;
    }

    public int getReserved() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this));
        return this.reserved;
    }

    public void setReserved(int reserved) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, Conversions.intObject(reserved)));
        this.reserved = reserved;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_14, this, this));
        return "AC3SpecificBox{fscod=" + this.fscod + ", bsid=" + this.bsid + ", bsmod=" + this.bsmod + ", acmod=" + this.acmod + ", lfeon=" + this.lfeon + ", bitRateCode=" + this.bitRateCode + ", reserved=" + this.reserved + '}';
    }
}
