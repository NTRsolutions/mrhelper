package com.apitechnosoft.mrhelper.models;

public class User {

    private String sno;

    private String jobId;

    private String houseno;

    private String password;

    private String emailid;

    private String name;

    private String loc;

    private String mobileno;

    private String landmark;

    private String email;


    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClassPojo [ sno = " + sno + ", jobId = " + jobId + ", houseno = " + houseno + ",  password = " + password + ", emailid = " + emailid + ",  name = " + name + ", loc = " + loc + ", mobileno = " + mobileno + ", landmark = " + landmark + ", email = " + email + "]";
    }
}
