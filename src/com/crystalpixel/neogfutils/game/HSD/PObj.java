package com.crystalpixel.neogfutils.game.HSD;

import java.util.ArrayList;
import java.util.List;

import com.crystalpixel.neogfutils.utils.accesor.ListAccessor;

public class PObj extends ListAccessor<PObj> {

    public PObj() {
        super(PObj.class);
    }

    public PObjFlags[] getFlags() {
        int value = _s.getInt(0x0C);
        List<PObjFlags> flagsList = new ArrayList<>();

        for (PObjFlags flag : PObjFlags.values()) {
            if ((value & flag.getValue()) != 0) {
                flagsList.add(flag);
            }
        }

        return flagsList.toArray(new PObjFlags[0]);
    }

    public void setFlags(PObjFlags... value) {
        int combinedValue = 0;
        for (PObjFlags flag : value) {
            combinedValue |= flag.getValue();
        }
        this._s.setInt(0x0C, combinedValue);
    }

}
