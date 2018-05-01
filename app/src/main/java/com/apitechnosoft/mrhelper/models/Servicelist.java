package com.apitechnosoft.mrhelper.models;

public class Servicelist {
    private String sno;

    private String service;

    private String city;

    public String getSno ()
    {
        return sno;
    }

    public void setSno (String sno)
    {
        this.sno = sno;
    }

    public String getService ()
    {
        return service;
    }

    public void setService (String service)
    {
        this.service = service;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sno = "+sno+", service = "+service+", city = "+city+"]";
    }
}
