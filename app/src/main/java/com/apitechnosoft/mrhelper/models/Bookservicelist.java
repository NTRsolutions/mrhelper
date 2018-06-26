package com.apitechnosoft.mrhelper.models;

public class Bookservicelist {
    private String totalservice;

    private String serviceamount;

    private String servicesno;

    private int sno;

    private String timepicker;

    private int jobId;

    private String houseno;

    private String aftertaxamount;

    private String loc;

    private String txtdate1;

    private String serviceName;

    private String landmark;

    private String email;

    private String name;

    private String entrydate;

    private String providerResponse;

    private String mobile;

    public String getTotalservice() {
        return totalservice;
    }

    public void setTotalservice(String totalservice) {
        this.totalservice = totalservice;
    }

    public String getServiceamount() {
        return serviceamount;
    }

    public void setServiceamount(String serviceamount) {
        this.serviceamount = serviceamount;
    }

    public String getServicesno() {
        return servicesno;
    }

    public void setServicesno(String servicesno) {
        this.servicesno = servicesno;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getTimepicker() {
        return timepicker;
    }

    public void setTimepicker(String timepicker) {
        this.timepicker = timepicker;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getAftertaxamount() {
        return aftertaxamount;
    }

    public void setAftertaxamount(String aftertaxamount) {
        this.aftertaxamount = aftertaxamount;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getTxtdate1() {
        return txtdate1;
    }

    public void setTxtdate1(String txtdate1) {
        this.txtdate1 = txtdate1;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(String entrydate) {
        this.entrydate = entrydate;
    }

    public String getProviderResponse() {
        return providerResponse;
    }

    public void setProviderResponse(String providerResponse) {
        this.providerResponse = providerResponse;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String bookingStatus;

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    @Override
    public String toString() {
        return "ClassPojo [totalservice = " + totalservice + ", serviceamount = " + serviceamount + ", servicesno = " + servicesno + ", sno = " + sno + ", timepicker = " + timepicker + ", jobId = " + jobId + ", houseno = " + houseno + ", aftertaxamount = " + aftertaxamount + ", loc = " + loc + ", txtdate1 = " + txtdate1 + ", serviceName = " + serviceName + ", landmark = " + landmark + ", email = " + email + ", name = " + name + ", entrydate = " + entrydate + ", providerResponse = " + providerResponse + ", mobile = " + mobile + "]";
    }
}
