package com.apitechnosoft.mrhelper.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.FontManager;

public class LoginActivity extends AppCompatActivity {
    Typeface materialdesignicons_font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        TextView phone_icon = (TextView) findViewById(R.id.phone_icon);
        phone_icon.setTypeface(materialdesignicons_font);
        phone_icon.setText(Html.fromHtml("&#xf3f2;"));
        TextView arrowicon = (TextView) findViewById(R.id.arrowicon);
        arrowicon.setTypeface(materialdesignicons_font);
        arrowicon.setText(Html.fromHtml("&#xf054;"));
       final EditText edt_phone = (EditText) findViewById(R.id.edt_phone);
        LinearLayout loginbutton = (LinearLayout) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String no=edt_phone.getText().toString();
                if(no!=null && !no.equals("")) {
                    Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                   // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("Number", no);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"Please enter phone number",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(LoginActivity.this)) {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
