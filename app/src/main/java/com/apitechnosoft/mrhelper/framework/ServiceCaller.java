package com.apitechnosoft.mrhelper.framework;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.utilities.Contants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Neeraj on 7/25/2017.
 */
public class ServiceCaller {
    Context context;

    public ServiceCaller(Context context) {
        this.context = context;
    }

    //call login data
    public void callLoginService(String phone, String token, final IAsyncWorkCompletedCallback workCompletedCallback) {

        final String url = Contants.SERVICE_BASE_URL + Contants.HomeData;
        JSONObject obj = new JSONObject();
        try {
            obj.put("PhoneNumber", phone);
            obj.put("DeviceId", token);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(Contants.LOG_TAG, "Payload*****" + obj);
        new ServiceHelper().callService(url, obj, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    //parseAndSaveLoginData(result, workCompletedCallback);
                } else {
                    workCompletedCallback.onDone("loginService done", false);
                }
            }
        });
    }

    //parse and save login data
  /*  public void parseAndSaveLoginData(final String result, final IAsyncWorkCompletedCallback workCompletedCallback) {
        new AsyncTask<Void, Void, Boolean>() {


            @Override
            protected Boolean doInBackground(Void... voids) {
                Boolean flag = false;
                ContentData data = new Gson().fromJson(result, ContentData.class);
                Log.d(Contants.LOG_TAG, "---" + data);
                if (data != null) {
                    if (data.getData() != null) {
                        DbHelper dbhelper = new DbHelper(context);
                        dbhelper.upsertUserData(data.getData());
                        flag = true;
                    }
                }
                return flag;
            }

            @Override
            protected void onPostExecute(Boolean flag) {
                super.onPostExecute(flag);
                if (flag) {
                    workCompletedCallback.onDone("LoginService done", true);
                } else {
                    workCompletedCallback.onDone("LoginService done", false);
                }
            }
        }.execute();
    }
*/
    //call home data
    public void callHomeService(final IAsyncWorkCompletedCallback workCompletedCallback) {

        final String url = Contants.SERVICE_BASE_URL + Contants.HomeData;
        new ServiceHelper().callService(url, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("homeService done", false);
                }
            }
        });
    }

    //call repair data
    public void callRepairService(int sNumber, final IAsyncWorkCompletedCallback workCompletedCallback) {

        final String url = Contants.SERVICE_BASE_URL + Contants.RepairData + sNumber;
        new ServiceHelper().callService(url, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("repairService done", false);
                }
            }
        });
    }
    //call otp data
    public void callOtpService(String mobileNostr, final IAsyncWorkCompletedCallback workCompletedCallback) {

        final String url = Contants.SERVICE_BASE_URL + Contants.OTP + mobileNostr;
        new ServiceHelper().callService(url, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("OtpService done", false);
                }
            }
        });
    }

    //call save data
    public void callSaveService(String dataUrl, final IAsyncWorkCompletedCallback workCompletedCallback) {

        final String url = Contants.SERVICE_BASE_URL + Contants.SAVE_REP_TYPE + dataUrl;
        new ServiceHelper().callService(url, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("SaveService done", false);
                }
            }
        });
    }

    //call party data
    public void callPartyService(int sNumber, final IAsyncWorkCompletedCallback workCompletedCallback) {

        final String url = Contants.SERVICE_BASE_URL + Contants.PartyData + sNumber;
        new ServiceHelper().callService(url, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("PartyService done", false);
                }
            }
        });
    }

    //call save data
    public void callPartySaveService(String dataUrl, final IAsyncWorkCompletedCallback workCompletedCallback) {

        final String url = Contants.SERVICE_BASE_URL + Contants.SavePartyData + dataUrl;
        new ServiceHelper().callService(url, null, new IServiceSuccessCallback() {
            @Override
            public void onDone(String doneWhatCode, String result, String error) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true);
                } else {
                    workCompletedCallback.onDone("PartySaveService done", false);
                }
            }
        });
    }
}
