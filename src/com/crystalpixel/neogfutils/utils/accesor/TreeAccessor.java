package com.crystalpixel.neogfutils.utils.accesor;

import java.util.ArrayList;
import java.util.List;

public class TreeAccessor<T extends TreeAccessor<T>> extends ListAccessor<T> {
    public T Child;
    private final Class<T> clazz;

    public TreeAccessor(Class<T> clazz) {
        super(clazz);
        this.clazz = clazz;
    }

    public T[] getChildren() {
        List<T> childList = new ArrayList<>();
        if (Child != null) {
            childList.addAll(Child.getList());
        }
        return childList.toArray((T[]) java.lang.reflect.Array.newInstance(clazz, childList.size()));
    }

    public List<T> getTreeList() {
        List<T> list = new ArrayList<>();
        list.add(newInstance());
        if (Child != null)
            list.addAll(Child.getTreeList());
        if (Next != null)
            list.addAll(Next.getTreeList());
        return list;
    }

    public void addChild(T t) {
        if (Child == null)
            Child = t;
        else
            Child.add(t);
    }

    private T newInstance() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeChildAt(int index) {
        if (index >= getChildren().length)
            return;

        T next = getChildren()[index].getNext();

        if (index - 1 < 0) {
            Child = next;
            return;
        }

        T prev = getChildren()[index - 1];

        prev.setNext(next);
    }

    public void setNext(T next) {
        Next = next;
    }
}