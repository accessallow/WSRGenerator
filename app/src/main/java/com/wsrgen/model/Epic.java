package com.wsrgen.model;

import java.util.ArrayList;
import java.util.List;

public class Epic {
    private String tag = "";
    private String track = "On-Track";
    private String eta = "";

    private List<Issue> issues = new ArrayList<>();

    public Epic(String tag) {
        this.tag = tag;
    }

    public Epic(String tag, String track, String eta) {
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

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "tag='" + tag + '\'' +
                ", track='" + track + '\'' +
                ", eta='" + eta + '\'' +
                ", issues=" + issues +
                '}';
    }
}
