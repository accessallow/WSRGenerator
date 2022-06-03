package com.wsrgen.model;

import java.util.ArrayList;
import java.util.List;

public class Plan {
    private String tag;
    private List<String> points = new ArrayList<>();

    public Plan(String tag) {
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
}
