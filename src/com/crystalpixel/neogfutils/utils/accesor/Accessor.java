package com.crystalpixel.neogfutils.utils.accesor;

import java.lang.reflect.Field;
import java.util.HashSet;

import com.crystalpixel.neogfutils.utils.math.DynamicByteBuffer;

public class Accessor {
    public DynamicByteBuffer _s;

    public int trimmedSize = -1;

    public Accessor() {
        if (trimmedSize != -1) {
            _s = new DynamicByteBuffer(new byte[trimmedSize]);
        } else {
            _s = new DynamicByteBuffer(new byte[0]);
        }
    }

    public void setStructFlags() {
        for (Field v : getClass().getDeclaredFields()) {
            v.setAccessible(true);
            try {
                if (Accessor.class.isAssignableFrom(v.getType())) {
                    Accessor ac = (Accessor) v.get(this);
                    if (ac != null && ac != this) {
                        ac.setStructFlags();
                    }
                } else if (v.getType().isArray()) {
                    Accessor[] arr = (Accessor[]) v.get(this);
                    if (arr != null) {
                        for (Accessor ai : arr) {
                            if (ai != null && ai != this) {
                                ai.setStructFlags();
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    protected int trim() {
        int trimmed = 0;
        if (trimmedSize != -1 && _s.array().length > trimmedSize) {
            System.out.println(getClass().getSimpleName() + ": 0x" + _s.array().length + " => 0x" + trimmedSize);
            trimmed += _s.array().length - trimmedSize;
            _s.ensureCapacity(trimmedSize);
        }
        return trimmed;
    }

    public int optimize() {
        return optimize(new HashSet<>());
    }

    private int optimize(HashSet<DynamicByteBuffer> trimmedList) {
        int trimmed = 0;
        if (trimmedList.contains(_s)) {
            return trimmed;
        }
        trimmedList.add(_s);
        trimmed += trim();
        for (Field v : getClass().getDeclaredFields()) {
            v.setAccessible(true);
            try {
                if (Accessor.class.isAssignableFrom(v.getType())) {
                    Accessor ac = (Accessor) v.get(this);
                    if (ac != null && ac != this) {
                        trimmed += ac.optimize(trimmedList);
                    }
                } else if (v.getType().isArray()) {
                    Accessor[] arr = (Accessor[]) v.get(this);
                    if (arr != null) {
                        for (Accessor ai : arr) {
                            if (ai != null && ai != this) {
                                trimmed += ai.optimize(trimmedList);
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return trimmed;
    }

    public static <T extends Accessor> T deepClone(Accessor a, Class<T> type) {
        T clone;
        try {
            clone = type.getDeclaredConstructor().newInstance();
            clone._s = a._s.deepClone();
            return clone;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Accessor) {
            Accessor acc = (Accessor) obj;
            return _s.equals(acc._s);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return _s.hashCode();
    }
}
