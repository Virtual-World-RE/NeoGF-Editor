package com.crystalpixel.neogfutils.system;

import java.util.List;

public abstract class Condition {

    protected Integer type;
    protected List<Integer> parameters;

    public Condition(Integer type, List<Integer> parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Integer> getParameters() {
        return parameters;
    }

    public void setParameters(List<Integer> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Unknown Condition";
    }
}