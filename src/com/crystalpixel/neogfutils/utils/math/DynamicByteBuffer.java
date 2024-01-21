package com.crystalpixel.neogfutils.utils.math;

import java.nio.BufferUnderflowException;

public class DynamicByteBuffer {
    private static final int DEFAULT_CAPACITY = 0;
    private byte[] buffer;
    private int position;

    public DynamicByteBuffer() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicByteBuffer(int initialCapacity) {
        buffer = new byte[initialCapacity];
        position = 0;
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

    public byte[] array() {
        return buffer;
    }

    public int position() {
        return position;
    }

    private void ensureCapacity(int additionalCapacity) {
        if (position + additionalCapacity > buffer.length) {
            int newCapacity = Math.max(buffer.length * 2, position + additionalCapacity);
            buffer = java.util.Arrays.copyOf(buffer, newCapacity);
        }
    }
}
