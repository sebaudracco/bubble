package org.telegram.tgnet;

public abstract class TLRPC$DocumentAttribute extends TLObject {
    public String alt;
    public int duration;
    public String file_name;
    public int flags;
    public int f12629h;
    public boolean mask;
    public TLRPC$TL_maskCoords mask_coords;
    public String performer;
    public boolean round_message;
    public TLRPC$InputStickerSet stickerset;
    public String title;
    public boolean voice;
    public int f12630w;
    public byte[] waveform;

    public static TLRPC$DocumentAttribute TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        TLRPC$DocumentAttribute result = null;
        switch (constructor) {
            case -1744710921:
                result = new TLRPC$TL_documentAttributeHasStickers();
                break;
            case -1739392570:
                result = new TLRPC$TL_documentAttributeAudio();
                break;
            case -1723033470:
                result = new TLRPC$TL_documentAttributeSticker_old2();
                break;
            case -556656416:
                result = new TLRPC$TL_documentAttributeAudio_layer45();
                break;
            case -83208409:
                result = new TLRPC$TL_documentAttributeSticker_old();
                break;
            case 85215461:
                result = new TLRPC$TL_documentAttributeAudio_old();
                break;
            case 250621158:
                result = new TLRPC$TL_documentAttributeVideo();
                break;
            case 297109817:
                result = new TLRPC$TL_documentAttributeAnimated();
                break;
            case 358154344:
                result = new TLRPC$TL_documentAttributeFilename();
                break;
            case 978674434:
                result = new TLRPC$TL_documentAttributeSticker_layer55();
                break;
            case 1494273227:
                result = new TLRPC$TL_documentAttributeVideo_layer65();
                break;
            case 1662637586:
                result = new TLRPC$TL_documentAttributeSticker();
                break;
            case 1815593308:
                result = new TLRPC$TL_documentAttributeImageSize();
                break;
        }
        if (result == null && exception) {
            throw new RuntimeException(String.format("can't parse magic %x in DocumentAttribute", new Object[]{Integer.valueOf(constructor)}));
        }
        if (result != null) {
            result.readParams(stream, exception);
        }
        return result;
    }
}
