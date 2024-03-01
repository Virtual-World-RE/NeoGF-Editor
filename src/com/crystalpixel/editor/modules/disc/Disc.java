package com.crystalpixel.editor.modules.disc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.crystalpixel.editor.modules.fst.Fst;
import com.crystalpixel.neogfutils.utils.accesor.Accessor;

public class Disc extends Accessor {

    public Disc() {
        super();
        super.trimmedSize = 0x430;
        super.init();
    }

    public DiscType getType() {
        return DiscType.fromCode(this._s.getChar(0x0));
    }

    public void setType(DiscType type) {
        this._s.setChar(0x0, type.getCode());
    }

    public String getGameCode() {
        short code = this._s.getShort(0x1);
        return Character.toString((char) ((code >> 8) & 0xFF)) + Character.toString((char) (code & 0xFF));
    }

    public void setGameCode(String gameCode) {
        this._s.setString(0x1, gameCode);
    }

    public Region getRegion() {
        return Region.fromCode(this._s.getChar(0x3));
    }

    public void setRegion(Region region) {
        this._s.setChar(0x3, region.getCode());
    }

    public String getMakerCode() {
        short code = this._s.getShort(0x4);
        return Character.toString((char) ((code >> 8) & 0xFF)) + Character.toString((char) (code & 0xFF));
    }

    public void setMakerCode(String makerCode) {
        this._s.setString(0x4, makerCode);
    }

    public byte getDiscNumber() {
        return this._s.get(0x6);
    }

    public void setDiscNumber(byte discNumber) {
        this._s.set(0x6, discNumber);
    }

    public boolean isAudioStreaming() {
        return this._s.getBoolean(0x7);
    }

    public void setAudioStreaming(boolean audioStreaming) {
        this._s.setBoolean(0x7, audioStreaming);
    }

    public byte getStreamingBufferSize() {
        return this._s.get(0x8);
    }

    public void setStreamingBufferSize(byte streamingBufferSize) {
        this._s.set(0x8, streamingBufferSize);
    }

    public String getMagicWord() {
        return "0x" + Integer.toHexString(this._s.getInt(0x1C)).toUpperCase();
    }

    public void setMagicWord(int magicWord) {
        this._s.setInt(0x1C, magicWord);
    }

    public String getGameTitle() throws IOException {
        return new String(this._s.readBytesUntil(0x20, 0x0), StandardCharsets.UTF_8);
    }

    public void setGameTitle(String gameTitle) {
        this._s.setString(0x20, gameTitle);
    }

    public String getDebugMonitorOffset() throws IOException {
        return "0x" + Integer.toHexString(this._s.getInt(0x400)).toUpperCase();
    }

    public void setDebugMonitorOffset(int debugMonitorOffset) throws IOException {
        this._s.setInt(0x400, debugMonitorOffset);
    }

    public String getDebugMonitorLoadAddress() throws IOException {
        return "0x" + Integer.toHexString(this._s.getInt(0x404)).toUpperCase();
    }

    public void setDebugMonitorLoadAddress(int debugMonitorLoadAddress) throws IOException {
        this._s.setInt(0x408, debugMonitorLoadAddress);
    }

    public String getMainDolOffset() {
        return "0x" + Integer.toHexString(this._s.getInt(0x420)).toUpperCase();
    }

    public void setMainDolOffset(int mainDolOffset) {
        this._s.setInt(0x420, mainDolOffset);
    }

    public Fst geFst() {
        return this._s.getCreateReference(0x424, Fst.class);
    }
}