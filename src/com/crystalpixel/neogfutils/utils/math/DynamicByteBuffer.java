package com.crystalpixel.neogfutils.utils.math;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.BufferUnderflowException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.crystalpixel.neogfutils.utils.accesor.Accessor;

/**
 * This class provides a dynamic byte buffer that efficiently manages memory
 * while offering various functionalities for data manipulation.
 * It utilizes a byte array to store data and allows operations like:
 * - Adding and retrieving data of various primitive types (bytes, integers,
 * characters)
 * - Reading and writing strings encoded in UTF-8 format
 * - Embedding and referencing other DynamicByteBuffer instances for complex
 * data structures
 */
public class DynamicByteBuffer {
    private static final int DEFAULT_CAPACITY = 0; // Default initial buffer size
    private byte[] buffer; // Underlying byte array for data storage
    private int position; // Current position within the buffer (tracks read/write operations)
    private HashMap<Integer, DynamicByteBuffer> references; // HashMap to manage references to other DynamicByteBuffer
                                                            // objects

    /**
     * Creates a new DynamicByteBuffer with the default capacity.
     */
    public DynamicByteBuffer() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates a new DynamicByteBuffer with the specified initial capacity.
     *
     * @param initialCapacity the initial size allocated for the buffer
     */
    public DynamicByteBuffer(int initialCapacity) {
        buffer = new byte[initialCapacity];
        position = 0;
        references = new HashMap<>();
    }

    /**
     * Creates a new DynamicByteBuffer using a provided byte array as its initial
     * buffer.
     *
     * @param capacity the byte array to be used as the initial buffer
     */
    public DynamicByteBuffer(byte[] capacity) {
        buffer = capacity;
        position = 0;
        references = new HashMap<>();
    }

    /**
     * Appends a single byte to the end of the buffer.
     *
     * @param b the byte to be added
     */
    public void put(byte b) {
        ensureCapacity(1);
        buffer[position++] = b;
    }

    /**
     * Appends a complete byte array to the end of the buffer.
     *
     * @param src the source byte array to be appended
     */
    public void put(byte[] src) {
        put(src, 0, src.length);
    }

    /**
     * Appends a specific portion of a byte array to the end of the buffer.
     *
     * @param src    the source byte array
     * @param offset the starting offset within the source array
     * @param length the number of bytes to copy
     */
    public void put(byte[] src, int offset, int length) {
        ensureCapacity(length);
        System.arraycopy(src, offset, buffer, position, length);
        position += length;
    }

    /**
     * Reads a single byte from the beginning of the buffer.
     *
     * @return the next byte from the buffer
     * @throws BufferUnderflowException if the buffer is empty
     */
    public byte get() {
        if (position == buffer.length) {
            throw new BufferUnderflowException();
        }
        return buffer[position++];
    }

    /**
     * Reads a specified number of bytes from the beginning of the buffer into a
     * provided byte array.
     *
     * @param dst the destination byte array to store the read bytes
     * @throws BufferUnderflowException if there are not enough bytes remaining in
     *                                  the buffer
     */
    public void get(byte[] dst) {
        get(dst, 0, dst.length);
    }

    /**
     * Reads a specific portion of bytes from the beginning of the buffer into a
     * provided byte array.
     *
     * @param dst    the destination byte array to store the read bytes
     * @param offset the starting offset within the destination array
     * @param length the number of bytes to read
     * @throws BufferUnderflowException if there are not enough bytes remaining in
     *                                  the buffer
     */
    public void get(byte[] dst, int offset, int length) {
        if (position + length > buffer.length) {
            throw new BufferUnderflowException();
        }
        System.arraycopy(buffer, position, dst, offset, length);
        position += length;
    }

    /**
     * Reads a character value from the buffer at the specified index.
     *
     * @param index the index of the byte to be interpreted as a character
     * @return the character value at the specified index
     * @throws IndexOutOfBoundsException if the index is outside the buffer bounds
     */
    public char getChar(int index) {
        if (index < 0 || index >= buffer.length) {
            throw new IndexOutOfBoundsException();
        }
        return (char) (buffer[index] & 0xFF);
    }

    /**
     * Reads a byte value from the buffer at the specified index.
     *
     * @param index the index of the byte to be read
     * @return the byte value at the specified index
     * @throws IndexOutOfBoundsException if the index is outside the buffer bounds
     */
    public byte get(int index) {
        if (index < 0 || index >= position) {
            throw new IndexOutOfBoundsException();
        }
        return buffer[index];
    }

    /**
     * Sets the byte value at the specified index in the buffer.
     *
     * @param index the index of the byte to be modified
     * @param value the new value to be written
     * @throws IndexOutOfBoundsException if the index is outside the buffer bounds
     */
    public void set(int index, byte value) {
        buffer[index] = value;
    }

    /**
     * Sets the character value at the specified index in the buffer, converting it
     * to a byte representation.
     *
     * @param index the index of the byte to be modified
     * @param value the character value to be written
     * @throws IndexOutOfBoundsException if the index is outside the buffer bounds
     */
    public void setChar(int index, char value) {
        if (index < 0 || index + 1 >= buffer.length) {
            throw new IndexOutOfBoundsException();
        }
        buffer[index] = (byte) (value >> 8);
        buffer[index + 1] = (byte) value;
    }

    /**
     * Writes a short value (2 bytes) to the buffer at the current position.
     *
     * @param value the short value to be written
     */
    public void putShort(short value) {
        ensureCapacity(2);
        buffer[position++] = (byte) (value >> 8);
        buffer[position++] = (byte) value;
    }

    /**
     * Reads a short value (2 bytes) from the buffer at the specified index.
     *
     * @param index the starting index of the two bytes representing the short value
     * @return the short value read from the buffer
     */
    public short getShort(int index) {
        int value = (buffer[index] << 8) | (buffer[index + 1] & 0xFF);
        return (short) value;
    }

    /**
     * Sets the short value (2 bytes) at the specified index in the buffer.
     *
     * @param index the starting index of the two bytes representing the short value
     * @param value the short value to be written
     * @throws IndexOutOfBoundsException if the index is outside the buffer bounds
     */
    public void setShort(int index, short value) {
        buffer[index] = (byte) (value >> 8);
        buffer[index + 1] = (byte) value;
    }

    /**
     * Writes an integer value (4 bytes) to the buffer at the current position.
     *
     * @param value the integer value to be written
     */
    public void setInt(int index, int value) {
        buffer[index] = (byte) (value >> 24);
        buffer[index + 1] = (byte) (value >> 16);
        buffer[index + 2] = (byte) (value >> 8);
        buffer[index + 3] = (byte) value;
    }

    /**
     * Reads an integer value (4 bytes) from the buffer at the specified index.
     *
     * @param index the starting index of the four bytes representing the integer
     * @return the integer value read from the buffer
     */
    public int getInt(int index) {
        int value = 0;
        value |= buffer[index] << 24;
        value |= (buffer[index + 1] & 0xFF) << 16;
        value |= (buffer[index + 2] & 0xFF) << 8;
        value |= (buffer[index + 3] & 0xFF);
        return value;
    }

    /**
     * Reads a float value (4 bytes) from the buffer at the specified index,
     * converting the integer representation stored in the buffer to a float.
     *
     * @param index the starting index of the four bytes representing the float
     * @return the float value read from the buffer
     */
    public float getFloat(int index) {
        int intBits = getInt(index);
        return Float.intBitsToFloat(intBits);
    }

    /**
     * Writes a float value (4 bytes) to the buffer at the current position,
     * converting the float to its integer representation before storing it.
     *
     * @param index the index of the byte to be modified
     * @param value the float value to be written
     */
    public void setFloat(int index, float value) {
        int intBits = Float.floatToIntBits(value);
        setInt(index, intBits);
    }

    /**
     * Writes a String value to the buffer, encoding it in UTF-8 format.
     * The length of the string (in bytes) is stored as an integer at the beginning,
     * followed by the actual UTF-8 encoded bytes of the string.
     *
     * @param index the index of the byte to be modified
     * @param value the String value to be written
     */
    public void setString(int index, String value) {
        byte[] stringBytes = value.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(stringBytes, 0, buffer, index + 4, stringBytes.length);
        setInt(index, stringBytes.length);
    }

    /**
     * Reads a String value from the buffer at the specified index.
     * The length of the string is first read as an integer,
     * and then the specified number of bytes are interpreted as UTF-8 encoded
     * characters.
     *
     * @param index the starting index of the string data
     * @return the String value read from the buffer
     */
    public String getString(int index) {
        int length = getInt(index);
        byte[] stringBytes = new byte[length];
        System.arraycopy(buffer, index + 4, stringBytes, 0, length);
        return new String(stringBytes, StandardCharsets.UTF_8);
    }

    /**
     * Reads bytes from the buffer until a specified terminator byte is encountered.
     *
     * @param index      the starting index to begin reading
     * @param terminator the byte value representing the terminator
     * @return a byte array containing all bytes read until the terminator
     * @throws IOException if an I/O error occurs during reading
     */
    public byte[] readBytesUntil(int index, int terminator) throws IOException {
        List<Byte> byteList = new ArrayList<>();
        byte terminatorByte = (byte) (terminator & 0xFF);
        for (int i = index; i < buffer.length; i++) {
            byte b = buffer[i];
            if (b == terminatorByte) {
                break;
            }
            byteList.add(b);
        }
        byte[] byteArray = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            byteArray[i] = byteList.get(i);
        }
        return byteArray;
    }

    /**
     * Writes a boolean value (represented as a single byte: 0 for false, 1 for
     * true) to the buffer at the specified index.
     *
     * @param index the index of the byte to be modified
     * @param value the boolean value to be written
     */
    public void setBoolean(int index, boolean value) {
        buffer[index] = (byte) (value ? 1 : 0);
    }

    /**
     * Sets the unsigned integer value (4 bytes) at the specified index in the
     * buffer.
     *
     * @param index the starting index of the four bytes representing the unsigned
     *              integer
     * @param value the unsigned integer value to be written
     */
    public void setUInt(int index, long value) {
        buffer[index] = (byte) (value >> 24);
        buffer[index + 1] = (byte) (value >> 16);
        buffer[index + 2] = (byte) (value >> 8);
        buffer[index + 3] = (byte) value;
    }

    /**
     * Reads a boolean value from the buffer at the specified index.
     *
     * @param index the index of the byte to be read
     * @return the boolean value read from the buffer
     */
    public boolean getBoolean(int index) {
        byte value = buffer[index];
        return value != 0;
    }

    /**
     * Returns a copy of the underlying byte array representing the current data in
     * the buffer.
     *
     * @return a byte array containing a copy of the buffer data
     */
    public byte[] array() {
        return buffer;
    }

    /**
     * Returns the current position within the buffer (tracks read/write
     * operations).
     *
     * @return the current position within the buffer
     */
    public int position() {
        return position;
    }

    /**
     * Sets a reference to another DynamicByteBuffer object at the specified
     * location in the buffer.
     * This allows for creating complex data structures with nested objects.
     *
     * @param loc   the index within the buffer to store the reference
     * @param value the Accessor object to be referenced
     */
    public void setReference(int loc, Accessor value) {
        if (value == null)
            setReferenceStruct(loc, null);
        else
            setReferenceStruct(loc, value._s);
    }

    /**
     * Sets a reference to another DynamicByteBuffer object at the specified
     * location in the buffer,
     * handling internal reference management for nested objects.
     *
     * @param loc   the index within the buffer to store the reference
     * @param value the DynamicByteBuffer object to be referenced (its internal
     *              buffer)
     */
    public void setReferenceStruct(int loc, DynamicByteBuffer value) {
        if (!references.containsKey(loc))
            references.put(loc, value);
        references.replace(loc, value);
        if (value == null)
            references.remove(loc);
        setInt(loc, 0);
    }

    /**
     * Retrieves a reference to another DynamicByteBuffer object stored at the
     * specified location in the buffer,
     * casting it to the specified class type.
     *
     * @param loc   the index within the buffer where the reference is stored
     * @param clazz the class type of the referenced object
     * @return the referenced DynamicByteBuffer object cast to the specified class
     *         type, or null if not found
     * @throws Exception if there is an error during reflection or instantiation
     */
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

    /**
     * Retrieves a reference to another DynamicByteBuffer object stored at the
     * specified location in the buffer,
     * casting it to the specified class type. If the reference doesn't exist, it
     * creates a new instance.
     *
     * @param loc   the index within the buffer where the reference is stored
     * @param clazz the class type of the referenced object
     * @return the referenced DynamicByteBuffer object cast to the specified class
     *         type, or a new instance if not found
     * @throws Exception if there is an error during reflection or instantiation
     */
    public <T extends Accessor> T getCreateReference(int loc, Class<T> clazz) {
        T re = getReference(loc, clazz);
        if (re == null) {
            try {
                re = clazz.getDeclaredConstructor().newInstance();
                setReference(loc, re);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return re;
    }

    /**
     * Sets a portion of the buffer with the provided data, starting at the
     * specified offset.
     * If the data length exceeds the buffer capacity, it is truncated. Missing
     * bytes are filled with the specified fill character.
     *
     * @param data          the byte array containing the data to copy into the
     *                      buffer
     * @param offset        the offset from which to start copying the data into the
     *                      buffer
     * @param length        the number of bytes to copy into the buffer
     * @param fillCharacter the character to use for filling missing bytes
     */
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

    /**
     * Retrieves a portion of data from the buffer, starting at the specified offset
     * and for the specified length.
     *
     * @param offset the offset from which to start retrieving data
     * @param length the number of bytes to retrieve
     * @return a byte array containing the retrieved data
     */
    public byte[] getSubData(int offset, int length) {
        if (offset + length >= buffer.length)
            length = buffer.length - offset;

        byte[] d = new byte[length];
        for (int i = 0; i < length; i++)
            d[i] = buffer[offset + i];

        return d;
    }

    /**
     * Reads an unsigned integer (4 bytes) from the buffer at the specified index.
     *
     * @param index the starting index of the four bytes representing the unsigned
     *              integer
     * @return the unsigned integer value read from the buffer
     */
    public long getUInt(int index) {
        byte[] bytes = new byte[4];
        System.arraycopy(buffer, index, bytes, 0, 4);
        long value = 0;
        for (int i = 0; i < 4; i++) {
            value |= (bytes[i] & 0xFFL) << (i * 8);
        }

        return value;
    }

    /**
     * Retrieves an embedded DynamicByteBuffer structure from the buffer, starting
     * at the specified location and for the specified length.
     *
     * @param loc    the index within the buffer where the embedded structure begins
     * @param length the length of the embedded structure in the buffer
     * @return a new instance of DynamicByteBuffer representing the embedded
     *         structure
     */
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

    /**
     * Appends the data of a DynamicByteBuffer structure to the end of the buffer.
     *
     * @param str the DynamicByteBuffer structure to be appended
     */
    public void appendStruct(DynamicByteBuffer str) {
        setEmbeddedStruct(position, str);
    }

    /**
     * Sets an embedded DynamicByteBuffer structure at the specified location in the
     * buffer.
     *
     * @param loc the index within the buffer to store the embedded structure
     * @param str the DynamicByteBuffer structure to be embedded
     */
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

    /**
     * Creates a deep clone of the DynamicByteBuffer object.
     *
     * @return a new instance of DynamicByteBuffer containing a copy of the buffer
     *         and references
     */
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

    /**
     * Ensures the buffer has enough capacity to accommodate the specified number of
     * bytes for writing operations.
     * If the current capacity is insufficient, the buffer is resized to accommodate
     * the requested space.
     *
     * @param additionalCapacity the number of additional bytes required
     */
    public void ensureCapacity(int additionalCapacity) {
        if (position + additionalCapacity > buffer.length) {
            int newCapacity = Math.max(buffer.length * 2, position + additionalCapacity);
            byte[] newBuffer = new byte[newCapacity];
            System.arraycopy(buffer, 0, newBuffer, 0, position);
            buffer = newBuffer;
        }
    }

    /**
     * Resizes the buffer to the specified size.
     *
     * @param size the new size of the buffer
     */
    private void resize(int size) {
        byte[] newBuffer = new byte[size];
        System.arraycopy(buffer, 0, newBuffer, 0, position);
        buffer = newBuffer;
    }
}