package com.apitechnosoft.mrhelper.professional;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.ContentResponce;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.FontManager;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;

public class FeedbackActivity extends AppCompatActivity {
    String sNo;
    String JobId;
    String phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        initView();
    }
    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(FeedbackActivity.this)) {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
    private void initView() {
        sNo = getIntent().getStringExtra("sNumber");
        JobId = getIntent().getStringExtra("JobId");
        phoneNo = getIntent().getStringExtra("PhooneNo");
        Typeface materialDesignIcons = FontManager.getFontTypefaceMaterialDesignIcons(FeedbackActivity.this, "fonts/materialdesignicons-webfont.otf");

        LinearLayout badlayout_emoji = (LinearLayout) findViewById(R.id.badlayout_emoji);
        LinearLayout itsoklayout_emoji = (LinearLayout) findViewById(R.id.itsoklayout_emoji);
        LinearLayout goodlayout_emoji = (LinearLayout) findViewById(R.id.goodlayout_emoji);
        LinearLayout awesomelayout_emoji = (LinearLayout) findViewById(R.id.awesomelayout_emoji);
        TextView bad_emojiIcon = (TextView) findViewById(R.id.bad_emojiIcon);
        TextView itsok_emojiIcon = (TextView) findViewById(R.id.itsok_emojiIcon);
        TextView good_emojiIcon = (TextView) findViewById(R.id.good_emojiIcon);
        TextView Awesome_emojiIcon = (TextView) findViewById(R.id.Awesome_emojiIcon);
        bad_emojiIcon.setTypeface(materialDesignIcons);
        itsok_emojiIcon.setTypeface(materialDesignIcons);
        good_emojiIcon.setTypeface(materialDesignIcons);
        Awesome_emojiIcon.setTypeface(materialDesignIcons);
        Awesome_emojiIcon.setText(Html.fromHtml("&#xf1f2;"));
        good_emojiIcon.setText(Html.fromHtml("&#xf1f5;"));
        itsok_emojiIcon.setText(Html.fromHtml("&#xf1f6;"));
        bad_emojiIcon.setText(Html.fromHtml("&#xf1f8;"));

        badlayout_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback(1);
            }
        });
        itsoklayout_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback(2);
            }
        });
        goodlayout_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback(3);
            }
        });
        awesomelayout_emoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback(4);
            }
        });
    }
    private void feedback(int point) {
        if (Utility.isOnline(FeedbackActivity.this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(FeedbackActivity.this);
            dotDialog.show();

            ServiceCaller serviceCaller = new ServiceCaller(FeedbackActivity.this);
            serviceCaller.callFeedbackSrvice(point, phoneNo, JobId, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        ContentResponce data = new Gson().fromJson(result, ContentResponce.class);
                        if (data != null) {
                            if (data.isStatus()) {
                                Toast.makeText(FeedbackActivity.this, "Feedback has been submited Successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(FeedbackActivity.this, ProfessionalWorkSheetActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(FeedbackActivity.this, "Feedback has not been submited!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(FeedbackActivity.this, "Feedback has not been submited!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, FeedbackActivity.this);
                    }
                    if (dotDialog.isShowing()) {
                        dotDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, FeedbackActivity.this);//off line msg....
        }
    }
}
