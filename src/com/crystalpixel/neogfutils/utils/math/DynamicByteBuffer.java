package com.crystalpixel.neogfutils.utils.math;

import java.nio.BufferUnderflowException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.crystalpixel.neogfutils.utils.accesor.Accessor;

public class DynamicByteBuffer {
    private static final int DEFAULT_CAPACITY = 0;
    private byte[] buffer;
    private int position;
    private HashMap<Integer, DynamicByteBuffer> references;

    public DynamicByteBuffer() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicByteBuffer(int initialCapacity) {
        buffer = new byte[initialCapacity];
        position = 0;
        references = new HashMap<>();
    }

    public DynamicByteBuffer(byte[] capacity) {
        buffer = capacity;
        position = 0;
        references = new HashMap<>();
    }

    public void put(byte b) {
        ensureCapacity(1);
        buffer[position++] = b;
    }

    public void put(byte[] src) {
        put(src, 0, src.length);
    }

    public void put(byte[] src, int offset, int length) {
        ensureCapacity(length);
        System.arraycopy(src, offset, buffer, position, length);
        position += length;
    }

    public byte get() {
        if (position == buffer.length) {
            throw new BufferUnderflowException();
        }
        return buffer[position++];
    }

    public void get(byte[] dst) {
        get(dst, 0, dst.length);
    }

    public void get(byte[] dst, int offset, int length) {
        if (position + length > buffer.length) {
            throw new BufferUnderflowException();
        }
        System.arraycopy(buffer, position, dst, offset, length);
        position += length;
    }

    public byte get(int index) {
        if (index < 0 || index >= position) {
            throw new IndexOutOfBoundsException();
        }
        return buffer[index];
    }

    public void putShort(short value) {
        ensureCapacity(2);
        buffer[position++] = (byte) (value >> 8);
        buffer[position++] = (byte) value;
    }

    public void setInt(int index, int value) {
        buffer[index] = (byte) (value >> 24);
        buffer[index + 1] = (byte) (value >> 16);
        buffer[index + 2] = (byte) (value >> 8);
        buffer[index + 3] = (byte) value;
    }

    public int getInt(int index) {
        int value = 0;
        value |= buffer[index] << 24;
        value |= (buffer[index + 1] & 0xFF) << 16;
        value |= (buffer[index + 2] & 0xFF) << 8;
        value |= (buffer[index + 3] & 0xFF);
        return value;
    }

    public float getFloat(int index) {
        int intBits = getInt(index);
        return Float.intBitsToFloat(intBits);
    }
    
    public void setFloat(int index, float value) {
        int intBits = Float.floatToIntBits(value);
        setInt(index, intBits);
    }    

    public void setString(int index, String value) {
        byte[] stringBytes = value.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(stringBytes, 0, buffer, index + 4, stringBytes.length);
        setInt(index, stringBytes.length);
    }

    public String getString(int index) {
        int length = getInt(index);
        byte[] stringBytes = new byte[length];
        System.arraycopy(buffer, index + 4, stringBytes, 0, length);
        return new String(stringBytes, StandardCharsets.UTF_8);
    }

    public byte[] array() {
        return buffer;
    }

    public int position() {
        return position;
    }

    public void setReference(int loc, Accessor value)
    {
        if (value == null)
            setReferenceStruct(loc, null);
        else
            setReferenceStruct(loc, value._s);
    }

    public void setReferenceStruct(int loc, DynamicByteBuffer value)
    {
        if (!references.containsKey(loc))
            references.put(loc, value);
        references.replace(loc, value);
        if (value == null)
            references.remove(loc);
        setInt(loc, 0);
    }

    public <T extends Accessor> T getReference(int loc, Class<T> clazz) {
        if (references.containsKey(loc)) {
            DynamicByteBuffer reference = references.get(loc);
            try {
                T newRef = clazz.getDeclaredConstructor().newInstance();
                newRef._s = reference;
                return newRef;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setSubData(byte[] data, int offset, int length, byte fillCharacter) {
        for (int i = 0; i < length; i++) {
            if (i + offset > buffer.length)
                break;

            if (i >= data.length)
                buffer[i + offset] = fillCharacter;
            else
                buffer[i + offset] = data[i];
        }
    }

    public byte[] getSubData(int offset, int length) {
        if (offset + length >= buffer.length)
            length = buffer.length - offset;

        byte[] d = new byte[length];
        for (int i = 0; i < length; i++)
            d[i] = buffer[offset + i];

        return d;
    }

    public DynamicByteBuffer getEmbeddedStruct(int loc, int length) {
        DynamicByteBuffer s = new DynamicByteBuffer();

        s.put(getSubData(loc, length));

        for (int k : references.keySet()) {
            if (k >= loc && k < loc + length) {
                s.setReferenceStruct(k - loc, references.get(k));
            }
        }

        return s;
    }

    public void appendStruct(DynamicByteBuffer str) {
        setEmbeddedStruct(position, str);
    }

    public void setEmbeddedStruct(int loc, DynamicByteBuffer str) {
        if (str == null)
            return;

        if (loc + str.position > position)
            resize(loc + str.position);

        setSubData(str.array(), loc, str.position, (byte) 0);

        for (int i = loc; i < loc + str.position; i++) {
            if (references.containsKey(i))
                references.remove(i);
        }

        for (int k : str.references.keySet()) {
            setReferenceStruct(loc + k, str.references.get(k));
        }
    }

    public DynamicByteBuffer deepClone() {
        DynamicByteBuffer clone = new DynamicByteBuffer();
        clone.put(getSubData(0, position));

        for (int k : references.keySet()) {
            if (references.get(k) == this)
                clone.references.put(k, clone);
            else
                clone.references.put(k, references.get(k).deepClone());
        }

        return clone;
    }

    public void ensureCapacity(int additionalCapacity) {
        if (position + additionalCapacity > buffer.length) {
            int newCapacity = Math.max(buffer.length * 2, position + additionalCapacity);
            byte[] newBuffer = new byte[newCapacity];
            System.arraycopy(buffer, 0, newBuffer, 0, position);
            buffer = newBuffer;
        }
    }

    private void resize(int size) {
        byte[] newBuffer = new byte[size];
        System.arraycopy(buffer, 0, newBuffer, 0, position);
        buffer = newBuffer;
    }
}