package com.crystalpixel.neogfutils.game.HSD;

import java.io.IOException;
import java.util.Arrays;

import com.crystalpixel.editor.modules.jobj.JObj;
import com.crystalpixel.editor.modules.jobj.JObjFlags;
import com.crystalpixel.neogfutils.utils.Tuple;
import com.crystalpixel.neogfutils.utils.math.vector.Vector3f;

public class JobjTest {
    public static void main(String[] args) throws IOException {
        initDummy();
        JObj jObj = readJObj();

        System.out.println("Class Name: " + jObj.getClassName());
        System.out.println("Flags: " + Arrays.toString(jObj.getFlags()));
        System.out.println("RX: " + jObj.getRX());
        System.out.println("RY: " + jObj.getRY());
        System.out.println("RZ: " + jObj.getRZ());
        System.out.println("SX: " + jObj.getSX());
        System.out.println("SY: " + jObj.getSY());
        System.out.println("SZ: " + jObj.getSZ());
        System.out.println("TX: " + jObj.getTX());
        System.out.println("TY: " + jObj.getTY());
        System.out.println("TZ: " + jObj.getTZ());

        if (jObj.getNext() != null) {
            System.out.println("-------------------------------------");
        }
    }

    private static JObj readJObj() throws IOException {
        JObj jObj = new JObj();

        jObj.setClassName(dummy[0].getThird());
        jObj.setFlags(JObjFlags.CLASSICAL_SCALING, JObjFlags.BILLBOARD, JObjFlags.VBILLBOARD, JObjFlags.HBILLBOARD,
                JObjFlags.JOINT1, JObjFlags.JOINT2, JObjFlags.EFFECTOR, JObjFlags.RBILLBOARD, JObjFlags.PBILLBOARD);
        jObj.setTX(dummy[0].getFourth().getX());
        jObj.setTY(dummy[0].getFourth().getY());
        jObj.setTZ(dummy[0].getFourth().getZ());
        jObj.setRX(dummy[0].getFifth().getX());
        jObj.setRY(dummy[0].getFifth().getY());
        jObj.setRZ(dummy[0].getFifth().getZ());
        jObj.setSX(1f);
        jObj.setSY(1f);
        jObj.setSZ(1f);

        JObj jObj2 = new JObj();
        jObj.setNext(jObj2);
        jObj.setClassName(dummy[1].getThird());
        jObj.setFlags(JObjFlags.CLASSICAL_SCALING, JObjFlags.BILLBOARD, JObjFlags.VBILLBOARD, JObjFlags.HBILLBOARD,
                 JObjFlags.RBILLBOARD, JObjFlags.PBILLBOARD);
        jObj.setTX(dummy[1].getFourth().getX());
        jObj.setTY(dummy[1].getFourth().getY());
        jObj.setTZ(dummy[1].getFourth().getZ());
        jObj.setRX(dummy[1].getFifth().getX());
        jObj.setRY(dummy[1].getFifth().getY());
        jObj.setRZ(dummy[1].getFifth().getZ());
        jObj.setSX(1f);
        jObj.setSY(1f);
        jObj.setSZ(1f);

        return jObj;
    }

    private static Tuple<Integer, Integer, String, Vector3f, Vector3f>[] dummy;

    @SuppressWarnings("unchecked")
    private static void initDummy() {
        dummy = new Tuple[] {
                new Tuple<>(1, 2, "TopN", new Vector3f(1.0f, 2.0f, 3.0f), new Vector3f(4.0f, 5.0f, 6.0f)),
                new Tuple<>(3, 4, "Object2", new Vector3f(7.0f, 8.0f, 9.0f), new Vector3f(10.0f, 11.0f, 12.0f))
        };
    }
}
