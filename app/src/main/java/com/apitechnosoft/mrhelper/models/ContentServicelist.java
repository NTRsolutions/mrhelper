package com.apitechnosoft.mrhelper.models;

public class ContentServicelist {
    private Servicelist[] servicelist;

    public Servicelist[] getServicelist ()
    {
        return servicelist;
    }

    public void setServicelist (Servicelist[] servicelist)
    {
        this.servicelist = servicelist;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [servicelist = "+servicelist+"]";
    }
}
