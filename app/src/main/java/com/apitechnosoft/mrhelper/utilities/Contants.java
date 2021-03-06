package com.apitechnosoft.mrhelper.utilities;

import android.os.Environment;

/**
 * Created by Neeraj on 3/8/2017.
 */
public class Contants {
    public static final Boolean IS_DEBUG_LOG = true;

    public static final String LOG_TAG = "MrhelperLog";
    public static final String APP_NAME = "appName"; // Do NOT change this value; meant for user preference

    public static final String DEFAULT_APPLICATION_NAME = "mrhelper";

    public static final String APP_DIRECTORY = "/E" + DEFAULT_APPLICATION_NAME + "Directory/";
    public static final String DATABASE_NAME = "mr_helper.db";// Environment.getExternalStorageDirectory() +  APP_DIRECTORY + "epharma_client.db";

    public static String SERVICE_BASE_URL = "http://mrhelper.in:8084/service/resources/";//"http://104.149.239.224:8084/Helper/resources/";//"http://mrhelper.in:8084/service/resources/";

    public static String outputBasePath = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String outputDirectoryLocation = outputBasePath + "/mehelperUnzipped/";


    public static boolean isProd_PN_TOKEN = true;
    public static final String SENDER = "HP-MOBKNT";
    public static final int LIST_PAGE_SIZE = 100;
    public static String InternetMessage = "Oops! You are Offline. Please check your Internet Connection.";
    public static final String BAD_NETWORK_MESSAGE = "We are trying hard to get latest content but the network seems to be slow. " +
            "You may continue to use app and get latest with in the app itself. ";

    public static final String OFFLINE_MESSAGE = "Oops! You are Offline. Please check your Internet Connection.";
    public static final String Error = "Server Side error!";
    public static final String ImageUrl = "http://mrhelper.in:8084/";


    public static final String HomeData = "HomeServices/gethomeData";
    public static final String RepairData = "ProductServices/getproductData?p=Repair&service=";

    public static final String OTP = "OtpServices/getOtp?mobiles=";
    public static final String SAVE_REP_TYPE = "SaveServiceRepType/saveservicesreptype?";

    public static final String PartyData = "PartyMServices/getPartyMServices?service=";

    public static final String SavePartyData = "SaveServiceDetailParType/saveServicedetailpartype?";

    public static final String GetServicelist = "ServiceList/getServicelist";
    public static final String BecomeHost = "SavePaertnerform/savepaertnerform";
    public static final String Login = "login/dologin?";
    public static final String UserInfoService = "UserInfoService/getuserInfoService?mobile=";
    public static final String UserRegistrationServices = "UserRegistrationServices/doRegister?";
    public static final String BookServicesCaller = "BookServicesCaller/getbookservices?mobileno=";
    public static final String CancelRequestApi = "CancelRequestApi/docancelRequest?sno=";
    public static final String GetshowLoctionService = "ShowLoctionServiceApi/getshowLoctionService?loc=";
    public static final String HostLogin = "loginenginee/dologinenginee?";
    public static final String GetProfessionalInfo = "PartnerDetailsForPartnerEdit/getpartnerdetailsForpartner?";
    public static final String OrderBookedListEngineerWise = "OrderBookedListEngineerWise/getorderbookedlistengineerwise?";
    public static final String GetOtp = "OrderBookedEngineerWise/getorderbookedengineerwise?bookingsno=";
    public static final String UpdateBookingstatus = "UpdateBookingstatus/setupdatebookingstatus?";
    public static final String Feedback = "ServicesReviewApi/setservicesreviewApi?";
    public static final String CompleteJob = "CompleteJobListApi/getCompleteJobListApi?mobile=";
    public static final String CanceledJob = "CancelServicesCaller/getcanceleservices?mobileno=";
}
