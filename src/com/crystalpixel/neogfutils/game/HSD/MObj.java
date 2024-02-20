package com.crystalpixel.neogfutils.game.HSD;

import java.util.ArrayList;
import java.util.List;

import com.crystalpixel.neogfutils.utils.accesor.Accessor;

public class MObj extends Accessor {

    public RenderMode[] getFlags() {
        int value = _s.getInt(0x04);
        List<RenderMode> flagsList = new ArrayList<>();

        for (RenderMode flag : RenderMode.values()) {
            if ((value & flag.getValue()) != 0) {
                flagsList.add(flag);
            }
        }

        return flagsList.toArray(new RenderMode[0]);
    }

    public void setFlags(RenderMode... value) {
        int combinedValue = 0;
        for (RenderMode flag : value) {
            combinedValue |= flag.getValue();
        }
        this._s.setInt(0x04, combinedValue);
    }

}
