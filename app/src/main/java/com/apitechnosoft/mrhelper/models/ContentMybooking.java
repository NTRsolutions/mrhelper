package com.apitechnosoft.mrhelper.models;

public class ContentMybooking {
    private Bookservicelist[] bookservicelist;

    public Bookservicelist[] getBookservicelist ()
    {
        return bookservicelist;
    }

    public void setBookservicelist (Bookservicelist[] bookservicelist)
    {
        this.bookservicelist = bookservicelist;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [bookservicelist = "+bookservicelist+"]";
    }
}
