package com.wsrgen.model;

import java.util.ArrayList;
import java.util.List;

public class Value {
    private String tag;
    private List<String> points = new ArrayList<>();

    public Value(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<String> getPoints() {
        return points;
    }

    public void setPoints(List<String> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Value{" +
                "tag='" + tag + '\'' +
                ", points=" + points +
                '}';
    }
}
