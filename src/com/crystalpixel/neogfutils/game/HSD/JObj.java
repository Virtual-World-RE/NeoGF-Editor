package com.crystalpixel.neogfutils.game.HSD;

import com.crystalpixel.neogfutils.utils.math.Quaternion;
import com.crystalpixel.neogfutils.utils.math.Mtx.Matrix4f;
import com.crystalpixel.neogfutils.utils.math.vector.Vector3f;
import com.crystalpixel.neogfutils.utils.math.vector.Vector4f;

public class JObj {
    Obj object;
    JObj  next;
    JObj parent;
    JObj child;
    int flags;
    JObjData data;
    Quaternion rotation;
    Vector3f scale;
    Vector3f translation;
    Matrix4f mtx;
    Vector3f scl;
    Matrix4f envelopemtx;
    AObj aobj;
    RObj robj;
    int id;

    public JObj(Obj object, JObj next, JObj parent, JObj child, int flags,
                JObjData data, Quaternion rotation, Vector3f scale, Vector3f translation,
                Matrix4f mtx, Vector3f scl, Matrix4f envelopemtx, AObj aobj, RObj robj, int id) {
        this.object = object;
        this.next = next;
        this.parent = parent;
        this.child = child;
        this.flags = flags;
        this.data = data;
        this.rotation = rotation;
        this.scale = scale;
        this.translation = translation;
        this.mtx = mtx;
        this.scl = scl;
        this.envelopemtx = envelopemtx;
        this.aobj = aobj;
        this.robj = robj;
        this.id = id;
    }

    protected void JObjCheckDepend() {

        if (!HSD_JObjMtxIsDirty(this)) {
            if ((this.flags & JObjFlags.USER_DEFINED_MTX.getValue()) != 0) {
                if ((this.flags & JObjFlags.MTX_INDEPEND_PARENT.getValue()) == 0 &&
                    this.parent != null && HSD_JObjMtxIsDirty(this.parent))
                {
                    this.flags |= JObjFlags.MTX_DIRTY.getValue();
                }
            } else if ((this.parent != null &&
                        (this.parent.flags & JObjFlags.MTX_DIRTY.getValue()) != 0) ||
                       (this.flags & JObjFlags.EFFECTOR.getValue()) == JObjFlags.JOINT1.getValue() ||
                       (this.flags & JObjFlags.EFFECTOR.getValue()) == JObjFlags.JOINT2.getValue() ||
                       (this.flags & JObjFlags.EFFECTOR.getValue()) == JObjFlags.EFFECTOR.getValue() ||
                       this.robj != null)
            {
                this.flags |= JObjFlags.MTX_DIRTY.getValue();
            }
        }
    }

    public static Vector4f toVector4f(GXColor4 v) {
        return new Vector4f(v.R / 255f, v.G / 255f, v.B / 255f, v.A / 255f);
    }

    private boolean HSD_JObjMtxIsDirty(JObj jObj) {
        return false;
    }
}
