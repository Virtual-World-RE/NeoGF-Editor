package com.crystalpixel.neogfutils.utils.math.Mtx;

public class Mtx {
    private float[][] elements;
    private static final float EPSILON = 0.0000000001f;

    public Mtx() {
        elements = new float[3][4];
    }

    public float getElement(int row, int col) {
        return elements[row][col];
    }

    public void setElement(int row, int col, float value) {
        elements[row][col] = value;
    }

    private static float calcDeterminantMatrix3x4(Mtx m) {
        return m.getElement(0, 0) * m.getElement(1, 1) * m.getElement(2, 2) +
               m.getElement(0, 1) * m.getElement(1, 2) * m.getElement(2, 0) +
               m.getElement(0, 2) * m.getElement(1, 0) * m.getElement(2, 1) -
               m.getElement(2, 0) * m.getElement(1, 1) * m.getElement(0, 2) -
               m.getElement(1, 0) * m.getElement(0, 1) * m.getElement(2, 2) -
               m.getElement(0, 0) * m.getElement(2, 1) * m.getElement(1, 2);
    }

    public static void invertMatrix(Mtx src, Mtx dest) {
        float det = calcDeterminantMatrix3x4(src);
        Mtx tempMatrix = new Mtx();

        if (Math.abs(det) < EPSILON) {
            PXMtxIdentity(dest);
        }

        if (src != dest) {
            PXMtxCopy(src, tempMatrix);
        }

        float invDet = 1.0f / det;

        dest.setElement(0, 0, (src.getElement(1, 1) * src.getElement(2, 2) - src.getElement(2, 1) * src.getElement(1, 2)) * invDet);
        dest.setElement(0, 1, -((src.getElement(0, 1) * src.getElement(2, 2) - src.getElement(2, 1) * src.getElement(0, 2)) * invDet));
        dest.setElement(0, 2, (src.getElement(0, 1) * src.getElement(1, 2) - src.getElement(1, 1) * src.getElement(0, 2)) * invDet);
        dest.setElement(1, 0, -((src.getElement(1, 0) * src.getElement(2, 2) - src.getElement(2, 0) * src.getElement(1, 2)) * invDet));

        dest.setElement(1, 1, (src.getElement(0, 0) * src.getElement(2, 2) - src.getElement(2, 0) * src.getElement(0, 2)) * invDet);
        dest.setElement(1, 2, -((src.getElement(0, 0) * src.getElement(1, 2) - src.getElement(1, 0) * src.getElement(0, 2)) * invDet));
        dest.setElement(2, 0, (src.getElement(1, 0) * src.getElement(2, 1) - src.getElement(2, 0) * src.getElement(1, 1)) * invDet);
        dest.setElement(2, 1, -((src.getElement(0, 0) * src.getElement(2, 1) - src.getElement(2, 0) * src.getElement(0, 1)) * invDet));
        dest.setElement(2, 2, (src.getElement(0, 0) * src.getElement(1, 1) - src.getElement(1, 0) * src.getElement(0, 1)) * invDet);

        dest.setElement(0, 3, -((dest.getElement(0, 2) * src.getElement(2, 3) -
                          (-dest.getElement(0, 0) * src.getElement(0, 3) - dest.getElement(0, 1) * src.getElement(1, 3)))));
        dest.setElement(1, 3, -((dest.getElement(1, 2) * src.getElement(2, 3) -
                          (-dest.getElement(1, 0) * src.getElement(0, 3) - dest.getElement(1, 1) * src.getElement(1, 3)))));
        dest.setElement(2, 3, -((dest.getElement(2, 2) * src.getElement(2, 3) -
                          (-dest.getElement(2, 0) * src.getElement(0, 3) - dest.getElement(2, 1) * src.getElement(1, 3)))));
    }

    public static void matrixInverseConcat(Mtx inv, Mtx src, Mtx dest) {
        float det = calcDeterminantMatrix3x4(inv);
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
                PXMtxCopy(src, dest);
            }
        } else {
            float invDet = 1.0f / det;

            temp1 = (inv.getElement(1, 1) * inv.getElement(2, 2) - inv.getElement(2, 1) * inv.getElement(1, 2)) * invDet;
            temp2 = -((inv.getElement(0, 1) * inv.getElement(2, 2) - inv.getElement(2, 1) * inv.getElement(0, 2))) * invDet;
            temp3 = -((inv.getElement(1, 0) * inv.getElement(2, 2) - inv.getElement(2, 0) * inv.getElement(1, 2))) * invDet;
            temp7 = (inv.getElement(0, 1) * inv.getElement(1, 2) - inv.getElement(1, 1) * inv.getElement(0, 2)) * invDet;

            temp4 = (inv.getElement(0, 0) * inv.getElement(2, 2) - inv.getElement(2, 0) * inv.getElement(0, 2)) * invDet;
            temp8 = -((inv.getElement(0, 0) * inv.getElement(1, 2) - inv.getElement(1, 0) * inv.getElement(0, 2))) * invDet;
            temp5 = (inv.getElement(1, 0) * inv.getElement(2, 1) - inv.getElement(2, 0) * inv.getElement(1, 1)) * invDet;
            temp6 = -((inv.getElement(0, 0) * inv.getElement(2, 1) - inv.getElement(2, 0) * inv.getElement(0, 1))) * invDet;

            temp9 = (inv.getElement(0, 0) * inv.getElement(1, 1) - inv.getElement(1, 0) * inv.getElement(0, 1)) * invDet;
            temp10 = -((temp7 * inv.getElement(2, 3)) - ((-temp1) * inv.getElement(0, 3) - temp2 * inv.getElement(1, 3)));
            temp11 = -((temp8 * inv.getElement(2, 3)) - ((-temp3) * inv.getElement(0, 3) - temp4 * inv.getElement(1, 3)));
            temp12 = -((temp9 * inv.getElement(2, 3)) - ((-temp5) * (new_var = inv.getElement(0, 3)) - temp6 * inv.getElement(1, 3)));

            if (inv == dest || src == dest) {
                m.setElement(0, 0, temp7 * src.getElement(2, 0) + (temp1 * src.getElement(0, 0) + temp2 * src.getElement(1, 0)));
                m.setElement(0, 1, temp7 * src.getElement(2, 1) + (temp1 * src.getElement(0, 1) + temp2 * src.getElement(1, 1)));
                m.setElement(0, 2, temp7 * src.getElement(2, 2) + (temp1 * src.getElement(0, 2) + temp2 * src.getElement(1, 2)));
                m.setElement(0, 3, temp7 * src.getElement(2, 3) + (temp1 * src.getElement(0, 3) + temp2 * src.getElement(1, 3)) + temp10);
            
                m.setElement(1, 0, temp8 * src.getElement(2, 0) + (temp3 * src.getElement(0, 0) + temp4 * src.getElement(1, 0)));
                m.setElement(1, 1, temp8 * src.getElement(2, 1) + (temp3 * src.getElement(0, 1) + temp4 * src.getElement(1, 1)));
                m.setElement(1, 2, temp8 * src.getElement(2, 2) + (temp3 * src.getElement(0, 2) + temp4 * src.getElement(1, 2)));
                m.setElement(1, 3, temp8 * src.getElement(2, 3) + (temp3 * src.getElement(0, 3) + temp4 * src.getElement(1, 3)) + temp11);
            
                m.setElement(2, 0, temp9 * src.getElement(2, 0) + (temp5 * src.getElement(0, 0) + temp6 * src.getElement(1, 0)));
                m.setElement(2, 1, temp9 * src.getElement(2, 1) + (temp5 * src.getElement(0, 1) + temp6 * src.getElement(1, 1)));
                m.setElement(2, 2, temp9 * src.getElement(2, 2) + (temp5 * src.getElement(0, 2) + temp6 * src.getElement(1, 2)));
                m.setElement(2, 3, temp9 * src.getElement(2, 3) + (temp5 * src.getElement(0, 3) + temp6 * src.getElement(1, 3)) + temp12);

                PXMtxCopy(m, dest);
            } else {
                dest.setElement(0, 0, temp7 * src.getElement(2, 0) + (temp1 * src.getElement(0, 0) + temp2 * src.getElement(1, 0)));
                dest.setElement(0, 1, temp7 * src.getElement(2, 1) + (temp1 * src.getElement(0, 1) + temp2 * src.getElement(1, 1)));
                dest.setElement(0, 2, temp7 * src.getElement(2, 2) + (temp1 * src.getElement(0, 2) + temp2 * src.getElement(1, 2)));
                dest.setElement(0, 3, temp7 * src.getElement(2, 3) + (temp1 * src.getElement(0, 3) + temp2 * src.getElement(1, 3)) + temp10);
            
                dest.setElement(1, 0, temp8 * src.getElement(2, 0) + (temp3 * src.getElement(0, 0) + temp4 * src.getElement(1, 0)));
                dest.setElement(1, 1, temp8 * src.getElement(2, 1) + (temp3 * src.getElement(0, 1) + temp4 * src.getElement(1, 1)));
                dest.setElement(1, 2, temp8 * src.getElement(2, 2) + (temp3 * src.getElement(0, 2) + temp4 * src.getElement(1, 2)));
                dest.setElement(1, 3, temp8 * src.getElement(2, 3) + (temp3 * src.getElement(0, 3) + temp4 * src.getElement(1, 3)) + temp11);
            
                dest.setElement(2, 0, temp9 * src.getElement(2, 0) + (temp5 * src.getElement(0, 0) + temp6 * src.getElement(1, 0)));
                dest.setElement(2, 1, temp9 * src.getElement(2, 1) + (temp5 * src.getElement(0, 1) + temp6 * src.getElement(1, 1)));
                dest.setElement(2, 2, temp9 * src.getElement(2, 2) + (temp5 * src.getElement(0, 2) + temp6 * src.getElement(1, 2)));
                dest.setElement(2, 3, temp9 * src.getElement(2, 3) + (temp5 * src.getElement(0, 3) + temp6 * src.getElement(1, 3)) + temp12);
            }
        }
    }

    private static void PXMtxCopy(Mtx src, Mtx dst) {
        if (src == dst) return;
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                dst.setElement(i, j, src.getElement(i, j));
            }
        }
    }

    private static void PXMtxIdentity(Mtx m) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                m.setElement(i, j, (i == j) ? 1.0f : 0.0f);
            }
        }
    }
}
