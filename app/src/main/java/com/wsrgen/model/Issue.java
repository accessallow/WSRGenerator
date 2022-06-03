package com.wsrgen.model;

public class Issue {
    private String tag;
    private String track;
    private String eta;

    public Issue(String tag, String track, String eta) {
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
        return "Issue{" +
                "tag='" + tag + '\'' +
                ", track='" + track + '\'' +
                ", eta='" + eta + '\'' +
                '}';
    }
}
