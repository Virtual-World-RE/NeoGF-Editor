package com.crystalpixel.neogfutils.utils.math.Mtx;

import com.crystalpixel.neogfutils.utils.math.vector.Vect3;

public class Mtx {
    private float[][] elements;
    private static final float EPSILON = 0.0000000001f;
    private static final float PI = 3.14159265358979323846f;

    public Mtx() {
        elements = new float[3][4];
    }

    public float getElement(int row, int col) {
        return elements[row][col];
    }

    public void setElement(int row, int col, float value) {
        elements[row][col] = value;
    }

    private static float calcDeterminantMTX(Mtx m) {
        return m.getElement(0, 0) * m.getElement(1, 1) * m.getElement(2, 2) +
                m.getElement(0, 1) * m.getElement(1, 2) * m.getElement(2, 0) +
                m.getElement(0, 2) * m.getElement(1, 0) * m.getElement(2, 1) -
                m.getElement(2, 0) * m.getElement(1, 1) * m.getElement(0, 2) -
                m.getElement(1, 0) * m.getElement(0, 1) * m.getElement(2, 2) -
                m.getElement(0, 0) * m.getElement(2, 1) * m.getElement(1, 2);
    }

    public static void invertMTX(Mtx src, Mtx dest) {
        float det = calcDeterminantMTX(src);

        if (Math.abs(det) < EPSILON) {
            PSMtxIdentity(dest);
        }

        float invDet = 1.0f / det;

        dest.setElement(0, 0,
                (src.getElement(1, 1) * src.getElement(2, 2) - src.getElement(2, 1) * src.getElement(1, 2)) * invDet);
        dest.setElement(0, 1,
                -((src.getElement(0, 1) * src.getElement(2, 2) - src.getElement(2, 1) * src.getElement(0, 2))
                        * invDet));
        dest.setElement(0, 2,
                (src.getElement(0, 1) * src.getElement(1, 2) - src.getElement(1, 1) * src.getElement(0, 2)) * invDet);
        dest.setElement(1, 0,
                -((src.getElement(1, 0) * src.getElement(2, 2) - src.getElement(2, 0) * src.getElement(1, 2))
                        * invDet));

        dest.setElement(1, 1,
                (src.getElement(0, 0) * src.getElement(2, 2) - src.getElement(2, 0) * src.getElement(0, 2)) * invDet);
        dest.setElement(1, 2,
                -((src.getElement(0, 0) * src.getElement(1, 2) - src.getElement(1, 0) * src.getElement(0, 2))
                        * invDet));
        dest.setElement(2, 0,
                (src.getElement(1, 0) * src.getElement(2, 1) - src.getElement(2, 0) * src.getElement(1, 1)) * invDet);
        dest.setElement(2, 1,
                -((src.getElement(0, 0) * src.getElement(2, 1) - src.getElement(2, 0) * src.getElement(0, 1))
                        * invDet));
        dest.setElement(2, 2,
                (src.getElement(0, 0) * src.getElement(1, 1) - src.getElement(1, 0) * src.getElement(0, 1)) * invDet);

        dest.setElement(0, 3, -((dest.getElement(0, 2) * src.getElement(2, 3) -
                (-dest.getElement(0, 0) * src.getElement(0, 3) - dest.getElement(0, 1) * src.getElement(1, 3)))));
        dest.setElement(1, 3, -((dest.getElement(1, 2) * src.getElement(2, 3) -
                (-dest.getElement(1, 0) * src.getElement(0, 3) - dest.getElement(1, 1) * src.getElement(1, 3)))));
        dest.setElement(2, 3, -((dest.getElement(2, 2) * src.getElement(2, 3) -
                (-dest.getElement(2, 0) * src.getElement(0, 3) - dest.getElement(2, 1) * src.getElement(1, 3)))));
    }

    public static void MTXInverseConcat(Mtx inv, Mtx src, Mtx dest) {
        float det = calcDeterminantMTX(inv);
        Mtx m = new Mtx();

        float temp1;
        float temp2;
        float temp3;
        float temp4;
        float temp5;
        float temp6;
        float temp7;
        float temp8;
        float temp9;
        float temp10;
        float temp11;
        float temp12;
        float new_var; // TODO: try to get rid of this

        if (Math.abs(det) < EPSILON) {
            if (src != dest) {
                PSMtxCopy(src, dest);
            }
        } else {
            float invDet = 1.0f / det;

            temp1 = (inv.getElement(1, 1) * inv.getElement(2, 2) - inv.getElement(2, 1) * inv.getElement(1, 2))
                    * invDet;
            temp2 = -((inv.getElement(0, 1) * inv.getElement(2, 2) - inv.getElement(2, 1) * inv.getElement(0, 2)))
                    * invDet;
            temp3 = -((inv.getElement(1, 0) * inv.getElement(2, 2) - inv.getElement(2, 0) * inv.getElement(1, 2)))
                    * invDet;
            temp7 = (inv.getElement(0, 1) * inv.getElement(1, 2) - inv.getElement(1, 1) * inv.getElement(0, 2))
                    * invDet;

            temp4 = (inv.getElement(0, 0) * inv.getElement(2, 2) - inv.getElement(2, 0) * inv.getElement(0, 2))
                    * invDet;
            temp8 = -((inv.getElement(0, 0) * inv.getElement(1, 2) - inv.getElement(1, 0) * inv.getElement(0, 2)))
                    * invDet;
            temp5 = (inv.getElement(1, 0) * inv.getElement(2, 1) - inv.getElement(2, 0) * inv.getElement(1, 1))
                    * invDet;
            temp6 = -((inv.getElement(0, 0) * inv.getElement(2, 1) - inv.getElement(2, 0) * inv.getElement(0, 1)))
                    * invDet;

            temp9 = (inv.getElement(0, 0) * inv.getElement(1, 1) - inv.getElement(1, 0) * inv.getElement(0, 1))
                    * invDet;
            temp10 = -((temp7 * inv.getElement(2, 3))
                    - ((-temp1) * inv.getElement(0, 3) - temp2 * inv.getElement(1, 3)));
            temp11 = -((temp8 * inv.getElement(2, 3))
                    - ((-temp3) * inv.getElement(0, 3) - temp4 * inv.getElement(1, 3)));
            temp12 = -((temp9 * inv.getElement(2, 3))
                    - ((-temp5) * (new_var = inv.getElement(0, 3)) - temp6 * inv.getElement(1, 3)));

            if (inv == dest || src == dest) {
                m.setElement(0, 0,
                        temp7 * src.getElement(2, 0) + (temp1 * src.getElement(0, 0) + temp2 * src.getElement(1, 0)));
                m.setElement(0, 1,
                        temp7 * src.getElement(2, 1) + (temp1 * src.getElement(0, 1) + temp2 * src.getElement(1, 1)));
                m.setElement(0, 2,
                        temp7 * src.getElement(2, 2) + (temp1 * src.getElement(0, 2) + temp2 * src.getElement(1, 2)));
                m.setElement(0, 3, temp7 * src.getElement(2, 3)
                        + (temp1 * src.getElement(0, 3) + temp2 * src.getElement(1, 3)) + temp10);

                m.setElement(1, 0,
                        temp8 * src.getElement(2, 0) + (temp3 * src.getElement(0, 0) + temp4 * src.getElement(1, 0)));
                m.setElement(1, 1,
                        temp8 * src.getElement(2, 1) + (temp3 * src.getElement(0, 1) + temp4 * src.getElement(1, 1)));
                m.setElement(1, 2,
                        temp8 * src.getElement(2, 2) + (temp3 * src.getElement(0, 2) + temp4 * src.getElement(1, 2)));
                m.setElement(1, 3, temp8 * src.getElement(2, 3)
                        + (temp3 * src.getElement(0, 3) + temp4 * src.getElement(1, 3)) + temp11);

                m.setElement(2, 0,
                        temp9 * src.getElement(2, 0) + (temp5 * src.getElement(0, 0) + temp6 * src.getElement(1, 0)));
                m.setElement(2, 1,
                        temp9 * src.getElement(2, 1) + (temp5 * src.getElement(0, 1) + temp6 * src.getElement(1, 1)));
                m.setElement(2, 2,
                        temp9 * src.getElement(2, 2) + (temp5 * src.getElement(0, 2) + temp6 * src.getElement(1, 2)));
                m.setElement(2, 3, temp9 * src.getElement(2, 3)
                        + (temp5 * src.getElement(0, 3) + temp6 * src.getElement(1, 3)) + temp12);

                PSMtxCopy(m, dest);
            } else {
                dest.setElement(0, 0,
                        temp7 * src.getElement(2, 0) + (temp1 * src.getElement(0, 0) + temp2 * src.getElement(1, 0)));
                dest.setElement(0, 1,
                        temp7 * src.getElement(2, 1) + (temp1 * src.getElement(0, 1) + temp2 * src.getElement(1, 1)));
                dest.setElement(0, 2,
                        temp7 * src.getElement(2, 2) + (temp1 * src.getElement(0, 2) + temp2 * src.getElement(1, 2)));
                dest.setElement(0, 3, temp7 * src.getElement(2, 3)
                        + (temp1 * src.getElement(0, 3) + temp2 * src.getElement(1, 3)) + temp10);

                dest.setElement(1, 0,
                        temp8 * src.getElement(2, 0) + (temp3 * src.getElement(0, 0) + temp4 * src.getElement(1, 0)));
                dest.setElement(1, 1,
                        temp8 * src.getElement(2, 1) + (temp3 * src.getElement(0, 1) + temp4 * src.getElement(1, 1)));
                dest.setElement(1, 2,
                        temp8 * src.getElement(2, 2) + (temp3 * src.getElement(0, 2) + temp4 * src.getElement(1, 2)));
                dest.setElement(1, 3, temp8 * src.getElement(2, 3)
                        + (temp3 * src.getElement(0, 3) + temp4 * src.getElement(1, 3)) + temp11);

                dest.setElement(2, 0,
                        temp9 * src.getElement(2, 0) + (temp5 * src.getElement(0, 0) + temp6 * src.getElement(1, 0)));
                dest.setElement(2, 1,
                        temp9 * src.getElement(2, 1) + (temp5 * src.getElement(0, 1) + temp6 * src.getElement(1, 1)));
                dest.setElement(2, 2,
                        temp9 * src.getElement(2, 2) + (temp5 * src.getElement(0, 2) + temp6 * src.getElement(1, 2)));
                dest.setElement(2, 3, temp9 * src.getElement(2, 3)
                        + (temp5 * src.getElement(0, 3) + temp6 * src.getElement(1, 3)) + temp12);
            }
        }
    }

    public static void MtxInverseTranspose(Mtx src, Mtx dest) {
        float det = calcDeterminantMTX(src);

        if (Math.abs(det) < EPSILON) {
            if (src != dest) {
                PSMtxCopy(src, dest);
            }
        } else {

            float invDet = 1.0f / det;

            dest.setElement(0, 0,
                    (((src.getElement(1, 1) * src.getElement(2, 2)) - (src.getElement(2, 1) * src.getElement(1, 2)))
                            * invDet));
            dest.setElement(1, 0,
                    -(((src.getElement(0, 1) * src.getElement(2, 2)) - (src.getElement(2, 1) * src.getElement(0, 2)))
                            * invDet));
            dest.setElement(2, 0,
                    (((src.getElement(0, 1) * src.getElement(1, 2)) - (src.getElement(1, 1) * src.getElement(0, 2)))
                            * invDet));

            dest.setElement(0, 1,
                    -(((src.getElement(1, 0) * src.getElement(2, 2)) - (src.getElement(2, 0) * src.getElement(1, 2)))
                            * invDet));
            dest.setElement(1, 1,
                    (((src.getElement(0, 0) * src.getElement(2, 2)) - (src.getElement(2, 0) * src.getElement(0, 2)))
                            * invDet));
            dest.setElement(2, 1,
                    -(((src.getElement(0, 0) * src.getElement(1, 2)) - (src.getElement(1, 0) * src.getElement(0, 2)))
                            * invDet));

            dest.setElement(0, 2,
                    (((src.getElement(1, 0) * src.getElement(2, 1)) - (src.getElement(2, 0) * src.getElement(1, 1)))
                            * invDet));
            dest.setElement(1, 2,
                    -(((src.getElement(0, 0) * src.getElement(2, 1)) - (src.getElement(2, 0) * src.getElement(0, 1)))
                            * invDet));
            dest.setElement(2, 2,
                    (((src.getElement(0, 0) * src.getElement(1, 1)) - (src.getElement(1, 0) * src.getElement(0, 1)))
                            * invDet));

            dest.setElement(0, 3, 0);
            dest.setElement(1, 3, 0);
            dest.setElement(2, 3, 0);
        }

    }

    private static float calcVal(float x, float y) {
        if (Math.abs(x) <= EPSILON) {
            if (y >= 0) {
                return PI / 2f;
            } else {
                return -PI /2f;
            }
        } else {
           return (float) Math.atan2(x, y);
        }
    }

    public static void MtxGetRotation(Mtx m, Vect3 vec) {
        float length0 = (float) Math.sqrt(m.getElement(0, 0) * m.getElement(0, 0) +
                                         m.getElement(1, 0) * m.getElement(1, 0) +
                                         m.getElement(2, 0) * m.getElement(2, 0));
    
        if (!(length0 < Float.MIN_VALUE)) {
            float length1 = (float) Math.sqrt(m.getElement(0, 1) * m.getElement(0, 1) +
                                              m.getElement(1, 1) * m.getElement(1, 1) +
                                              m.getElement(2, 1) * m.getElement(2, 1));
    
            if (!(length1 < Float.MIN_VALUE)) {
                float length2 = (float) Math.sqrt(m.getElement(0, 2) * m.getElement(0, 2) +
                                                  m.getElement(1, 2) * m.getElement(1, 2) +
                                                  m.getElement(2, 2) * m.getElement(2, 2));
    
                if (!(length2 < Float.MIN_VALUE)) {
                    float testVal_1 = -m.getElement(2, 0) / length0;
                    float val_01;
    
                    if (testVal_1 >= 1.0f) {
                        val_01 = (float) Math.PI / 2;
                    } else if (testVal_1 <= -1) {
                        val_01 = -(float) Math.PI / 2;
                    } else {
                        val_01 = (float) Math.asin(testVal_1);
                    }
    
                    vec.setY(val_01);
    
                    if (Math.cos(vec.getY()) >= Float.MIN_VALUE) {
                        float testVal_2_pre = m.getElement(2, 2) / length2;
                        float testVal_3_pre = m.getElement(2, 1) / length1;
    
                        vec.setX(calcVal(testVal_2_pre, testVal_3_pre));
                        vec.setZ(calcVal(m.getElement(0, 0), m.getElement(1, 0)));
                        return;
                    }
    
                    vec.setX(calcVal(m.getElement(1, 1), m.getElement(0, 1)));
                    vec.setZ(0);
                    return;
                }
            }
        }
    
        vec.setX(0);
        vec.setY(0);
        vec.setZ(0);
    }

    // These parameters may not be right
    public static void MtxGetTranslate(Mtx mat, Vect3 vec) {
        vec.setX(mat.getElement(0, 3));
        vec.setY(mat.getElement(1, 3));
        vec.setZ(mat.getElement(2, 3));
    }

    public static void MtxGetScale(Mtx mat, Vect3 vec) {
        double scale;

        Vect3 vec1 = new Vect3();
        Vect3 vec2 = new Vect3();
        Vect3 vec3 = new Vect3();
        Vect3 vec4 = new Vect3();

        vec1.setX(mat.getElement(0, 0));
        vec1.setY(mat.getElement(1, 0));
        vec1.setZ(mat.getElement(2, 0));

        vec.setX(PSVECMag(vec1));
        PSVECNormalize(vec1, vec1);

        vec2.setX(mat.getElement(0, 1));
        vec2.setY(mat.getElement(1, 1));
        vec2.setZ(mat.getElement(2, 1));

        PSVECScale(vec1, vec4, PSVECDotProduct(vec1, vec2));
        PSVECSubtract(vec2, vec4, vec2);
        vec.setY(PSVECMag(vec2));
        PSVECNormalize(vec2, vec2);

        vec3.setX(mat.getElement(0, 2));
        vec3.setY(mat.getElement(1, 2));
        vec3.setZ(mat.getElement(2, 2));

        PSVECScale(vec2, vec4, PSVECDotProduct(vec2, vec3));
        PSVECSubtract(vec3, vec4, vec3);
        PSVECScale(vec1, vec4, PSVECDotProduct(vec1, vec3));
        PSVECSubtract(vec3, vec4, vec3);
        vec.setZ(PSVECMag(vec3));
        PSVECNormalize(vec3, vec3);
        PSVECCrossProduct(vec2, vec3, vec4);

        if (PSVECDotProduct(vec1, vec4) < 0.0) {
                scale = -1.0;
                vec.setX(vec.getX() * (float) scale);
                vec.setY(vec.getY() * (float) scale);
                vec.setZ(vec.getZ() * (float) scale);
        } 
    }

    

    public static void PSVECCrossProduct(Vect3 a, Vect3 b, Vect3 axb) {
        axb.setX(a.getY() * b.getZ() - a.getZ() * b.getY());
        axb.setY(a.getZ() * b.getX() - a.getX() * b.getZ());
        axb.setZ(a.getX() * b.getY() - a.getY() * b.getX());
    }    

    private static void PSVECSubtract(final Vect3 a, final Vect3 b, Vect3 ab) {
        ab.setX(a.getX() - b.getX());
        ab.setY(a.getY() - b.getY());
        ab.setZ(a.getZ() - b.getZ());
    }

    private static float PSVECDotProduct(Vect3 a, Vect3 b) {
        return (a.getX() * b.getX()) + (a.getY() * b.getY()) + (a.getZ() * b.getZ());
    }    

    private static void PSVECScale(Vect3 src, Vect3 dst, float scale) {
        dst.setX(src.getX() * scale);
        dst.setY(src.getY() * scale);
        dst.setZ(src.getZ() * scale);
    }

    private static void PSVECNormalize(Vect3 vec, Vect3 result) {
        float m = vec.getX() * vec.getX() + vec.getY() * vec.getY() + vec.getZ() * vec.getZ();
        m = (float) (1 / Math.sqrt(m));
    
        result.setX(vec.getX() * m);
        result.setY(vec.getY() * m);
        result.setZ(vec.getZ() * m);
    }
    
    
    private static float PSVECMag(Vect3 vec) {
        return (float) Math.sqrt(vec.getX() * vec.getX() + vec.getY() * vec.getY() + vec.getZ() * vec.getZ());
    }

    private static void PSMtxCopy(Mtx src, Mtx dst) {
        if (src == dst)
            return;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                dst.setElement(i, j, src.getElement(i, j));
            }
        }
    }

    private static void PSMtxIdentity(Mtx m) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                m.setElement(i, j, (i == j) ? 1.0f : 0.0f);
            }
        }
    }
}
