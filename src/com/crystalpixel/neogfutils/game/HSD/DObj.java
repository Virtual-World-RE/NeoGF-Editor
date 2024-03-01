package com.crystalpixel.neogfutils.game.HSD;

import com.crystalpixel.editor.modules.mobj.MObj;
import com.crystalpixel.neogfutils.utils.accesor.ListAccessor;

public class DObj extends ListAccessor<DObj> {

    public DObj() {
        super(DObj.class);
    }

    public DObj getNext() {
        return _s.getReference(0x04, this.getClass());
    }

    public void setNext(DObj value) {
        _s.setReference(0x04, value);
    }

    public MObj getMObj() {
        return _s.getReference(0x08, MObj.class);
    }

    public void setMObj(MObj value) {
        _s.setReference(0x08, value);
    }

    public PObj getPObj() {
        return _s.getReference(0x0C, PObj.class);
    }

    public void setPObj(PObj value) {
        _s.setReference(0x0C, value);
    }

}
