package com.crystalpixel.neogfutils.utils;

public class Tuple<A, B, C, D, E> {
    private final A first;
    private final B second;
    private final C third;
    private final D fourth;
    private final E fifth;

    public Tuple(A first, B second, C third, D fourth, E fifth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }

    public D getFourth() {
        return fourth;
    }

    public E getFifth() {
        return fifth;
    }
}
