package com.wsrgen.model;

import java.util.ArrayList;
import java.util.List;

public class BPIDevelopment{
    private String tag;
    private String track;
    private String eta;

    private List<Epic> epics = new ArrayList<>();

    public List<Epic> getEpics() {
        return epics;
    }

    public void setEpics(List<Epic> epics) {
        this.epics = epics;
    }

    public BPIDevelopment(String tag, String track, String eta) {
        this.tag = tag;
        this.track = track;
        this.eta = eta;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    @Override
    public String toString() {
        return "BPIDevelopment{" +
                "tag='" + tag + '\'' +
                ", track='" + track + '\'' +
                ", eta='" + eta + '\'' +
                ", epics=" + epics +
                '}';
    }
}
