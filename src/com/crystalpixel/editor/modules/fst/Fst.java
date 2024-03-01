package com.crystalpixel.editor.modules.fst;

import com.crystalpixel.neogfutils.utils.accesor.Accessor;

public class Fst extends Accessor {
    
    public Fst() {
        super.trimmedSize = 0xC;
        super.init();
    }

    public String getFSTOffset() {
        return "0x" + Integer.toHexString(this._s.getInt(0x0)).toUpperCase();
    }

    public void setFSTOffset(int fstOffset) {
        this._s.setInt(0x0, fstOffset);
    }

    public int getFSTSize() {
        return this._s.getInt(0x4);
    }

    public void setFSTSize(long fstSize) {
        this._s.setUInt(0x4, fstSize);
    }

    public int getMaxFSTSize() {
        return this._s.getInt(0x8);
    }

    public void setMaxFSTSize(int maxFSTSize) {
        this._s.setInt(0x8, maxFSTSize);
    }
}
