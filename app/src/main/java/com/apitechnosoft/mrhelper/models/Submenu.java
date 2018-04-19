package com.apitechnosoft.mrhelper.models;

public class Submenu {
    private String sectionSno;

    private String sno;

    private String rate1;

    private String rate2;

    private String totalAmount;

    private String jobid;

    private String serviceName;

    public String getSectionSno ()
    {
        return sectionSno;
    }

    public void setSectionSno (String sectionSno)
    {
        this.sectionSno = sectionSno;
    }

    public String getSno ()
    {
        return sno;
    }

    public void setSno (String sno)
    {
        this.sno = sno;
    }

    public String getRate1 ()
    {
        return rate1;
    }

    public void setRate1 (String rate1)
    {
        this.rate1 = rate1;
    }

    public String getRate2 ()
    {
        return rate2;
    }

    public void setRate2 (String rate2)
    {
        this.rate2 = rate2;
    }

    public String getTotalAmount ()
    {
        return totalAmount;
    }

    public void setTotalAmount (String totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getJobid ()
    {
        return jobid;
    }

    public void setJobid (String jobid)
    {
        this.jobid = jobid;
    }

    public String getServiceName ()
    {
        return serviceName;
    }

    public void setServiceName (String serviceName)
    {
        this.serviceName = serviceName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sectionSno = "+sectionSno+", sno = "+sno+", rate1 = "+rate1+", rate2 = "+rate2+", totalAmount = "+totalAmount+", jobid = "+jobid+", serviceName = "+serviceName+"]";
    }
}

