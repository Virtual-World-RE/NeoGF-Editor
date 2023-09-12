package HSD;

import java.io.IOException;
import java.io.RandomAccessFile;

public class JObj {
    int unknown;
    JObjFlags flags;
    int childOffset;
    int nextOffset;
    int dobjOffset;
    float[] rotationXYZ = new float[3];
    float[] scaleXYZ = new float[3];
    float[] translationXYZ = new float[3];
    int inverseWorldTransformOffset;
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

    public InverseBindMatrix readInverseWorldTransform(RandomAccessFile randomAccessFile) throws IOException {

        randomAccessFile.seek(inverseWorldTransformOffset);

        InverseBindMatrix inverseTransform = new InverseBindMatrix();
        inverseTransform.setRotationXX(randomAccessFile.readFloat());
        inverseTransform.setRotationXY(randomAccessFile.readFloat());
        inverseTransform.setRotationXZ(randomAccessFile.readFloat());
        inverseTransform.setTranslationX(randomAccessFile.readFloat());
        inverseTransform.setRotationYX(randomAccessFile.readFloat());
        inverseTransform.setRotationYY(randomAccessFile.readFloat());
        inverseTransform.setRotationYZ(randomAccessFile.readFloat());
        inverseTransform.setTranslationY(randomAccessFile.readFloat());
        inverseTransform.setRotationZX(randomAccessFile.readFloat());
        inverseTransform.setRotationZY(randomAccessFile.readFloat());
        inverseTransform.setRotationZZ(randomAccessFile.readFloat());
        inverseTransform.setTranslationZ(randomAccessFile.readFloat());
        return inverseTransform;
    }
}
