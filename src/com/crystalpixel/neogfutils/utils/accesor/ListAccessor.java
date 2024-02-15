package com.crystalpixel.neogfutils.utils.accesor;

import java.util.ArrayList;
import java.util.List;

public class ListAccessor<T extends ListAccessor<T>> extends Accessor {
    public T Next;
    private final Class<T> clazz;

    public ListAccessor(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> getList() {
        List<T> list = new ArrayList<>();

        T i = newInstance();
        while (i != null) {
            list.add(i);
            i = i.getNext();
        }
        return list;
    }

    public void add(T t) {
        if (Next == null) {
            Next = t;
        } else {
            Next.add(t);
        }
    }

    public void removeLast() {
        if (Next == null)
            return;

        if (Next.getNext() == null)
            Next = Next.getNext();
        else
            Next.removeLast();
    }

    private T newInstance() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public T getNext() {
        return Next;
    }
}

