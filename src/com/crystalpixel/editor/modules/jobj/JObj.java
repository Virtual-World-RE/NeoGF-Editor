package com.crystalpixel.editor.modules.jobj;

import java.util.ArrayList;
import java.util.List;

import com.crystalpixel.editor.modules.mobj.MObj;
import com.crystalpixel.editor.modules.mobj.RenderMode;
import com.crystalpixel.neogfutils.game.HSD.DObj;
import com.crystalpixel.neogfutils.game.HSD.Matrix4x3;
import com.crystalpixel.neogfutils.game.HSD.PObj;
import com.crystalpixel.neogfutils.game.HSD.PObjFlags;
import com.crystalpixel.neogfutils.game.HSD.ParticleJoint;
import com.crystalpixel.neogfutils.game.HSD.RObj;
import com.crystalpixel.neogfutils.game.HSD.Spline;
import com.crystalpixel.neogfutils.utils.accesor.TreeAccessor;

public class JObj extends TreeAccessor<JObj> {

    public JObj() {
        super(JObj.class);
        super.trimmedSize = 0x40;
        super.init();
    }

    public String getClassName() {
        return this._s.getString(0x00);
    }

    public void setClassName(String value) {
        this._s.setString(0x00, value);
    }

    public JObjFlags[] getFlags() {
        int value = _s.getInt(0x04);
        List<JObjFlags> flagsList = new ArrayList<>();

        if ((value & JObjFlags.BILLBOARD.getValue()) != 0 && (value & JObjFlags.VBILLBOARD.getValue()) != 0) {
            flagsList.add(JObjFlags.HBILLBOARD);
        } else if ((value & JObjFlags.BILLBOARD.getValue()) != 0) {
            flagsList.add(JObjFlags.BILLBOARD);
        } else if ((value & JObjFlags.VBILLBOARD.getValue()) != 0) {
            flagsList.add(JObjFlags.VBILLBOARD);
        }

        if ((value & JObjFlags.JOINT1.getValue()) != 0 && (value & JObjFlags.JOINT2.getValue()) != 0) {
            flagsList.add(JObjFlags.EFFECTOR);
        } else {
            if ((value & JObjFlags.JOINT1.getValue()) != 0) {
                flagsList.add(JObjFlags.JOINT1);
            }
            if ((value & JObjFlags.JOINT2.getValue()) != 0) {
                flagsList.add(JObjFlags.JOINT2);
            }
        }

        for (JObjFlags flag : JObjFlags.values()) {
            if (flag != JObjFlags.BILLBOARD && flag != JObjFlags.VBILLBOARD && flag != JObjFlags.HBILLBOARD &&
                    flag != JObjFlags.EFFECTOR && flag != JObjFlags.JOINT1 && flag != JObjFlags.JOINT2) {
                if ((value & flag.getValue()) != 0) {
                    flagsList.add(flag);
                }
            }
        }

        return flagsList.toArray(new JObjFlags[0]);
    }

    public void setFlags(JObjFlags... value) {
        int combinedValue = 0;
        for (JObjFlags flag : value) {
            combinedValue |= flag.getValue();
        }
        this._s.setInt(0x04, combinedValue);
    }

    public JObj getChild() {
        return this._s.getReference(0x08, this.getClass());
    }

    public JObj getNext() {
        return this._s.getReference(0x0C, this.getClass());
    }

    public void setNext(JObj value) {
        this._s.setReference(0x0C, value);
    }

    public DObj getDObj() {
        if (!JObjFlags.contains(this.getFlags(), JObjFlags.SPLINE, JObjFlags.PTCL)) {
            return this._s.getReference(0x10, DObj.class);
        }
        return null;
    }

    public void setDObj(DObj value) {
        _s.setReference(0x10, value);
        JObjFlags.remove(this.getFlags(), JObjFlags.SPLINE);
        JObjFlags.remove(this.getFlags(), JObjFlags.PTCL);
    }

    public Spline getSpline() {
        if (JObjFlags.contains(this.getFlags(), JObjFlags.SPLINE)) {
            return _s.getReference(0x10, Spline.class);
        }
        return null;
    }

    public void setSpline(Spline value) {
        _s.setReference(0x10, value);
        JObjFlags.add(this.getFlags(), JObjFlags.SPLINE);
    }

    public ParticleJoint getParticleJoint() {
        if (JObjFlags.contains(this.getFlags(), JObjFlags.PTCL)) {
            return _s.getReference(0x10, ParticleJoint.class);
        }
        return null;
    }

    public void setParticleJoint(ParticleJoint value) {
        _s.setReference(0x10, value);
        JObjFlags.add(this.getFlags(), JObjFlags.PTCL);
    }

    public float getRX() {
        return _s.getFloat(0x14);
    }

    public void setRX(float value) {
        _s.setFloat(0x14, value);
    }

    public float getRY() {
        return _s.getFloat(0x18);
    }

    public void setRY(float value) {
        _s.setFloat(0x18, value);
    }

    public float getRZ() {
        return _s.getFloat(0x1C);
    }

    public void setRZ(float value) {
        _s.setFloat(0x1C, value);
    }

    public float getSX() {
        return _s.getFloat(0x20);
    }

    public void setSX(float value) {
        _s.setFloat(0x20, value);
    }

    public float getSY() {
        return _s.getFloat(0x24);
    }

    public void setSY(float value) {
        _s.setFloat(0x24, value);
    }

    public float getSZ() {
        return _s.getFloat(0x28);
    }

    public void setSZ(float value) {
        _s.setFloat(0x28, value);
    }

    public float getTX() {
        return _s.getFloat(0x2C);
    }

    public void setTX(float value) {
        _s.setFloat(0x2C, value);
    }

    public float getTY() {
        return _s.getFloat(0x30);
    }

    public void setTY(float value) {
        _s.setFloat(0x30, value);
    }

    public float getTZ() {
        return _s.getFloat(0x34);
    }

    public void setTZ(float value) {
        _s.setFloat(0x34, value);
    }

    public Matrix4x3 getInverseWorldTransform() {
        return _s.getReference(0x38, Matrix4x3.class);
    }

    public void setInverseWorldTransform(Matrix4x3 value) {
        _s.setReference(0x38, value);
    }

    public RObj getRObj() {
        return _s.getReference(0x3C, RObj.class);
    }

    public void setRObj(RObj value) {
        _s.setReference(0x3C, value);
    }

    public float getDefaultValue(JointTrackType type) {
        switch (type) {
            case A_J_TRAX:
                return getTX();
            case A_J_TRAY:
                return getTY();
            case A_J_TRAZ:
                return getTZ();
            case A_J_ROTX:
                return getRX();
            case A_J_ROTY:
                return getRY();
            case A_J_ROTZ:
                return getRZ();
            case A_J_SCAX:
                return getSX();
            case A_J_SCAY:
                return getSY();
            case A_J_SCAZ:
                return getSZ();
            default:
                break;
        }
        return 0;
    }

    public void updateFlags() {
        updateFlags(true);
    }

    private void updateFlags(boolean isRoot) {
        if (Child != null) {
            Child.updateFlags(false);
        }

        if (Next != null) {
            Next.updateFlags(false);
        }

        if (getDObj() != null) {
            boolean[] flags = new boolean[5];

            for (DObj dobj : getDObj().getList()) {
                MObj mobj = dobj.getMObj();
                if (mobj != null) {
                    flags[0] |= RenderMode.contains(mobj.getFlags(), RenderMode.XLU);
                    flags[1] |= !RenderMode.contains(mobj.getFlags(), RenderMode.XLU);
                    flags[2] |= RenderMode.contains(mobj.getFlags(), RenderMode.DIFFUSE);
                    flags[3] |= RenderMode.contains(mobj.getFlags(), RenderMode.SPECULAR);
                }

                if (dobj.getPObj() != null) {
                    for (PObj pObj : dobj.getPObj().getList()) {
                        flags[4] |= PObjFlags.contains(pObj.getFlags(), PObjFlags.ENVELOPE);
                    }
                }
            }

            setFlag(JObjFlags.OPA, flags[1]);
            setFlag(JObjFlags.valueOf(JObjFlags.XLU.getValue() | JObjFlags.TEXEDGE.getValue()), flags[0]);
            setFlag(JObjFlags.SPECULAR, flags[3]);
            setFlag(JObjFlags.LIGHTING, flags[2]);
            setFlag(JObjFlags.ENVELOPE_MODEL, flags[4]);
        }

        setFlag(JObjFlags.SKELETON, getInverseWorldTransform() != null);
        setFlag(JObjFlags.ROOT_XLU, childHasFlag(Child, JObjFlags.XLU));
        setFlag(JObjFlags.ROOT_OPA, childHasFlag(Child, JObjFlags.OPA));
        setFlag(JObjFlags.ROOT_TEXEDGE, childHasFlag(Child, JObjFlags.TEXEDGE));
        setFlag(JObjFlags.SKELETON_ROOT, isRoot && childHasFlag(Child, JObjFlags.SKELETON));
    }

    private static boolean childHasFlag(JObj jobj, JObjFlags flag) {
        if (jobj == null) {
            return false;
        }
        if (JObjFlags.contains(jobj.getFlags(), flag)) {
            return true;
        }
        if (jobj.Child != null && childHasFlag(jobj.Child, flag)) {
            return true;
        }
        if (jobj.Next != null && childHasFlag(jobj.Next, flag)) {
            return true;
        }
        return false;
    }

    private void setFlag(JObjFlags flag, boolean value) {
        if (value) {
            JObjFlags.add(this.getFlags(), flag);
        } else {
            JObjFlags.remove(this.getFlags(), flag);
        }
    }

    @Override
    protected int trim() {
        return super.trim();
    }
}
