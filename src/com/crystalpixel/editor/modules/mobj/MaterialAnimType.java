package com.crystalpixel.editor.modules.mobj;

public enum MaterialAnimType {
    A_M_AMBIENT_R(1), 
    A_M_AMBIENT_G(2), 
    A_M_AMBIENT_B(3), 
    A_M_DIFFUSE_R(4), 
    A_M_DIFFUSE_G(5), 
    A_M_DIFFUSE_B(6), 
    A_M_SPECULAR_R(7),
    A_M_SPECULAR_G(8),
    A_M_SPECULAR_B(9),
    A_M_ALPHA(10),              
    A_M_PE_REF0(11),    
    A_M_PE_REF1(12),    
    A_M_PE_DSTALPHA(13);

    private final int value;

    MaterialAnimType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

