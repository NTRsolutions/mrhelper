package com.apitechnosoft.mrhelper.models;

public class HelpForBusinesdata {
    private String afterDiscountPrice;

    private int sno;

    private String filePath;

    private String discountSellprice;

    private String enteredat;

    private String type;

    private String amount;

    private String gstValue;

    private String discountinpercent;

    private String fileName;

    private String totalAmount;

    private String jibId;

    private String quantity;

    private String sellprice;

    private String imageDescription;

    private String heading;

    public String getAfterDiscountPrice ()
    {
        return afterDiscountPrice;
    }

    public void setAfterDiscountPrice (String afterDiscountPrice)
    {
        this.afterDiscountPrice = afterDiscountPrice;
    }

    public int getSno ()
    {
        return sno;
    }

    public void setSno (int sno)
    {
        this.sno = sno;
    }

    public String getFilePath ()
    {
        return filePath;
    }

    public void setFilePath (String filePath)
    {
        this.filePath = filePath;
    }

    public String getDiscountSellprice ()
    {
        return discountSellprice;
    }

    public void setDiscountSellprice (String discountSellprice)
    {
        this.discountSellprice = discountSellprice;
    }

    public String getEnteredat ()
    {
        return enteredat;
    }

    public void setEnteredat (String enteredat)
    {
        this.enteredat = enteredat;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getGstValue ()
    {
        return gstValue;
    }

    public void setGstValue (String gstValue)
    {
        this.gstValue = gstValue;
    }

    public String getDiscountinpercent ()
    {
        return discountinpercent;
    }

    public void setDiscountinpercent (String discountinpercent)
    {
        this.discountinpercent = discountinpercent;
    }

    public String getFileName ()
    {
        return fileName;
    }

    public void setFileName (String fileName)
    {
        this.fileName = fileName;
    }

    public String getTotalAmount ()
    {
        return totalAmount;
    }

    public void setTotalAmount (String totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getJibId ()
    {
        return jibId;
    }

    public void setJibId (String jibId)
    {
        this.jibId = jibId;
    }

    public String getQuantity ()
    {
        return quantity;
    }

    public void setQuantity (String quantity)
    {
        this.quantity = quantity;
    }

    public String getSellprice ()
    {
        return sellprice;
    }

    public void setSellprice (String sellprice)
    {
        this.sellprice = sellprice;
    }

    public String getImageDescription ()
    {
        return imageDescription;
    }

    public void setImageDescription (String imageDescription)
    {
        this.imageDescription = imageDescription;
    }

    public String getHeading ()
    {
        return heading;
    }

    public void setHeading (String heading)
    {
        this.heading = heading;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [afterDiscountPrice = "+afterDiscountPrice+", sno = "+sno+", filePath = "+filePath+", discountSellprice = "+discountSellprice+", enteredat = "+enteredat+", type = "+type+", amount = "+amount+", gstValue = "+gstValue+", discountinpercent = "+discountinpercent+", fileName = "+fileName+", totalAmount = "+totalAmount+", jibId = "+jibId+", quantity = "+quantity+", sellprice = "+sellprice+", imageDescription = "+imageDescription+", heading = "+heading+"]";
    }
}
