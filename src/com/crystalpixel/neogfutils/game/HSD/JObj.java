package com.crystalpixel.neogfutils.game.HSD;

import com.crystalpixel.neogfutils.utils.math.Quaternion;
import com.crystalpixel.neogfutils.utils.math.Mtx.Mtx;
import com.crystalpixel.neogfutils.utils.math.vector.Vect3;

public class JObj {
    Obj object;
    JObjFlags flags;
    JObj  childOffset;
    JObj nextOffset;
    JObj dobjOffset;
    Quaternion rotation;
    Vect3 scale;
    Vect3 translation;
    Mtx mtx;
    int robjOffset;

    public JObj() {
        this(JObjFlags.NULL);
    }

    public JObj(JObjFlags flags) {
        this.flags = flags;
    }

    public void setJObjFlag(JObjFlags flag) {
        this.flags = flag;
    }

    public JObjFlags getJObjFlags() {
        return this.flags;
    }
}
