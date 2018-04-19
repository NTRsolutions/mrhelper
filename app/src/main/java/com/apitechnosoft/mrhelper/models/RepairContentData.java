package com.apitechnosoft.mrhelper.models;

public class RepairContentData
{
    private DetailListDashboarddata[] detailListDashboarddata;

    private MenuheadingtData[] menuheadingtData;

    public DetailListDashboarddata[] getDetailListDashboarddata ()
    {
        return detailListDashboarddata;
    }

    public void setDetailListDashboarddata (DetailListDashboarddata[] detailListDashboarddata)
    {
        this.detailListDashboarddata = detailListDashboarddata;
    }

    public MenuheadingtData[] getMenuheadingtData ()
    {
        return menuheadingtData;
    }

    public void setMenuheadingtData (MenuheadingtData[] menuheadingtData)
    {
        this.menuheadingtData = menuheadingtData;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [detailListDashboarddata = "+detailListDashboarddata+", menuheadingtData = "+menuheadingtData+"]";
    }
}
