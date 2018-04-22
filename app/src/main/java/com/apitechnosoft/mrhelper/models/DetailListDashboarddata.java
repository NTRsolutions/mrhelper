package com.apitechnosoft.mrhelper.models;

public class DetailListDashboarddata {
    private String sectionSno;

    private String sno;

    private String rate1;

    private String rate2;

    private String jobid;

    private String serviceName;

    private String option5;

    private String option2;

    private String subHeading;

    private String free;

    private String option3;

    private String option4;

    private String name;

    private String totalAmount;

    private String heading;

    private String option;

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

    public String getOption5 ()
    {
        return option5;
    }

    public void setOption5 (String option5)
    {
        this.option5 = option5;
    }

    public String getOption2 ()
    {
        return option2;
    }

    public void setOption2 (String option2)
    {
        this.option2 = option2;
    }

    public String getSubHeading ()
    {
        return subHeading;
    }

    public void setSubHeading (String subHeading)
    {
        this.subHeading = subHeading;
    }

    public String getFree ()
    {
        return free;
    }

    public void setFree (String free)
    {
        this.free = free;
    }

    public String getOption3 ()
    {
        return option3;
    }

    public void setOption3 (String option3)
    {
        this.option3 = option3;
    }

    public String getOption4 ()
    {
        return option4;
    }

    public void setOption4 (String option4)
    {
        this.option4 = option4;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getTotalAmount ()
    {
        return totalAmount;
    }

    public void setTotalAmount (String totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getHeading ()
    {
        return heading;
    }

    public void setHeading (String heading)
    {
        this.heading = heading;
    }

    public String getOption ()
    {
        return option;
    }

    public void setOption (String option)
    {
        this.option = option;
    }
private boolean isSelected=false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sectionSno = "+sectionSno+", sno = "+sno+", rate1 = "+rate1+", rate2 = "+rate2+", jobid = "+jobid+", serviceName = "+serviceName+", option5 = "+option5+", option2 = "+option2+", subHeading = "+subHeading+", free = "+free+", option3 = "+option3+", option4 = "+option4+", name = "+name+", totalAmount = "+totalAmount+", heading = "+heading+", option = "+option+"]";
    }
}


