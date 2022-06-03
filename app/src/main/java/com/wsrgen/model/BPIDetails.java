package com.wsrgen.model;

import java.util.ArrayList;
import java.util.List;

public class BPIDetails {
    private String track = "BPI";
    private BPIDevelopment devActivities = new BPIDevelopment("BPI Development","On-Track","");
    private BPITesting testingActivities = new BPITesting("BPI Testing","On-Track","");
    private List<Value> values = new ArrayList<>();
    private List<Plan> plans  = new ArrayList<>();
    private List<Risk> risks = new ArrayList<>();
    private String status = "green";

    public BPIDetails() {
        this.track = track;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    public List<Risk> getRisks() {
        return risks;
    }

    public void setRisks(List<Risk> risks) {
        this.risks = risks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BPIDevelopment getDevActivities() {
        return devActivities;
    }

    public void setDevActivities(BPIDevelopment devActivities) {
        this.devActivities = devActivities;
    }

    public BPITesting getTestingActivities() {
        return testingActivities;
    }

    public void setTestingActivities(BPITesting testingActivities) {
        this.testingActivities = testingActivities;
    }
}
