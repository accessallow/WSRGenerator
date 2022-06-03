package com.wsrgen.model;

public class JiraIssue {
    private String id;
    private String name;
    private String epicName;
    private String epicId;
    private String issueType;
    private String status;
    private String priority;

    public JiraIssue() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEpicName() {
        return epicName;
    }

    public void setEpicName(String epicName) {
        this.epicName = epicName;
    }

    public String getEpicId() {
        return epicId;
    }

    public void setEpicId(String epicId) {
        this.epicId = epicId;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "JiraIssue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", epicName='" + epicName + '\'' +
                ", epicId='" + epicId + '\'' +
                ", issueType='" + issueType + '\'' +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
