package com.apitechnosoft.mrhelper.models;

public class ContentResponce {
    private boolean status;
    private String tag;
    private String jobid;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private Orderbookedengineerwise orderbookedengineerwise;

    public Orderbookedengineerwise getOrderbookedengineerwise() {
        return orderbookedengineerwise;
    }

    public void setOrderbookedengineerwise(Orderbookedengineerwise orderbookedengineerwise) {
        this.orderbookedengineerwise = orderbookedengineerwise;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
}
