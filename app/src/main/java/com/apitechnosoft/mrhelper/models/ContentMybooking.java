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
    private PartnerDetailsForPartner PartnerDetailsForPartner;

    public PartnerDetailsForPartner getPartnerDetailsForPartner ()
    {
        return PartnerDetailsForPartner;
    }

    public void setPartnerDetailsForPartner (PartnerDetailsForPartner PartnerDetailsForPartner)
    {
        this.PartnerDetailsForPartner = PartnerDetailsForPartner;
    }

    private OrderBookedListEngineerWise[] OrderBookedListEngineerWise;

    public OrderBookedListEngineerWise[] getOrderBookedListEngineerWise ()
    {
        return OrderBookedListEngineerWise;
    }

    public void setOrderBookedListEngineerWise (OrderBookedListEngineerWise[] OrderBookedListEngineerWise)
    {
        this.OrderBookedListEngineerWise = OrderBookedListEngineerWise;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [bookservicelist = "+bookservicelist+"]";
    }
}
