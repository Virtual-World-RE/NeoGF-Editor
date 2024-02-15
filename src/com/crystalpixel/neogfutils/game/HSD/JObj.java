package com.crystalpixel.neogfutils.game.HSD;

import com.crystalpixel.neogfutils.utils.accesor.TreeAccessor;

public class JObj extends TreeAccessor<JObj> {

    public JObj() {
        super(JObj.class);
        super.trimmedSize = 0x40;
    }

    public String getClassName() {
        return this._s.getString(0x00);
    }

    public void setClassName(String value) {
        this._s.setString(0x00, value);
    }

    public JObjFlags getFlags() {
        return JObjFlags.valueOf(_s.getInt(0x04));
    }

    public void setFlags(JObjFlags value) {
        this._s.setInt(0x04, value.ordinal());
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
        if (!this.getFlags().contains(JObjFlags.SPLINE) && !this.getFlags().contains(JObjFlags.PTCL)) {
            return this._s.getReference(0x10, DObj.class);
        }
        return null;
    }

    public void setDObj(DObj value) {
        _s.setReference(0x10, value);
        this.getFlags().remove(JObjFlags.SPLINE);
        this.getFlags().remove(JObjFlags.PTCL);
    }

    public Spline getSpline() {
        if (this.getFlags().contains(JObjFlags.SPLINE)) {
            return _s.getReference(0x10, Spline.class);
        }
        return null;
    }

    public void setSpline(Spline value) {
        _s.setReference(0x10, value);
        this.getFlags().add(JObjFlags.SPLINE);
    }

    public ParticleJoint getParticleJoint() {
        if (this.getFlags().contains(JObjFlags.PTCL)) {
            return _s.getReference(0x10, ParticleJoint.class);
        }
        return null;
    }

    public void setParticleJoint(ParticleJoint value) {
        _s.setReference(0x10, value);
        this.getFlags().add(JObjFlags.PTCL);
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
}
