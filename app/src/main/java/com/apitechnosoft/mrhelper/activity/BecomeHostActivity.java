package com.apitechnosoft.mrhelper.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apitechnosoft.mrhelper.R;
import com.apitechnosoft.mrhelper.adapters.RepairExpandableListAdapter;
import com.apitechnosoft.mrhelper.adapters.ServiceSpinnerAdapter;
import com.apitechnosoft.mrhelper.circlecustomprogress.CircleDotDialog;
import com.apitechnosoft.mrhelper.framework.IAsyncWorkCompletedCallback;
import com.apitechnosoft.mrhelper.framework.ServiceCaller;
import com.apitechnosoft.mrhelper.models.ContentResponce;
import com.apitechnosoft.mrhelper.models.ContentServicelist;
import com.apitechnosoft.mrhelper.models.DetailListDashboarddata;
import com.apitechnosoft.mrhelper.models.MenuheadingtData;
import com.apitechnosoft.mrhelper.models.RepairContentData;
import com.apitechnosoft.mrhelper.models.RepairItemsListParentModel;
import com.apitechnosoft.mrhelper.models.Servicelist;
import com.apitechnosoft.mrhelper.models.Submenu;
import com.apitechnosoft.mrhelper.utilities.CompatibilityUtility;
import com.apitechnosoft.mrhelper.utilities.Contants;
import com.apitechnosoft.mrhelper.utilities.Utility;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BecomeHostActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private String userChoosenTask;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1777;
    public final int REQUEST_CAMERA = 101;
    public final int SELECT_PHOTO = 102;
    EditText fullName, cityName, phoneNo, designation, basicDetails, adress, idNo, rateRange, emailId;
    Spinner profession, idProof;
    String fullNamestr, emailIdstr, rateRangestr, idNostr, adressstr, basicDetailsstr, designationstr, phoneNostr, cityNamestr;
    String serviceName, idProofName;
    boolean selectImageFlag = false;
    String idProofImage, profileImage;
    ImageView idproofImageview, profileImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_host);
        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(BecomeHostActivity.this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {
        LinearLayout uploadPicLayout = (LinearLayout) findViewById(R.id.uploadPicLayout);
        LinearLayout idproofLayout = (LinearLayout) findViewById(R.id.idproofLayout);
        LinearLayout nextButtonLayout = (LinearLayout) findViewById(R.id.nextButtonLayout);
        idproofLayout.setOnClickListener(this);
        uploadPicLayout.setOnClickListener(this);
        nextButtonLayout.setOnClickListener(this);
        Button idproofbutton = (Button) findViewById(R.id.idproofbutton);
        idproofbutton.setOnClickListener(this);
        Button uploadbutton = (Button) findViewById(R.id.uploadbutton);
        uploadbutton.setOnClickListener(this);
        idproofImageview = (ImageView) findViewById(R.id.idproofImage);
        profileImageview = (ImageView) findViewById(R.id.profileImage);


        fullName = (EditText) findViewById(R.id.fullName);
        cityName = (EditText) findViewById(R.id.cityName);
        phoneNo = (EditText) findViewById(R.id.phoneNo);
        designation = (EditText) findViewById(R.id.designation);
        basicDetails = (EditText) findViewById(R.id.basicDetails);
        idNo = (EditText) findViewById(R.id.idNo);
        rateRange = (EditText) findViewById(R.id.rateRange);
        emailId = (EditText) findViewById(R.id.emailId);
        adress = (EditText) findViewById(R.id.adress);
        profession = (Spinner) findViewById(R.id.profession);
        profession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                Servicelist service = (Servicelist) adapterView.getItemAtPosition(pos);
                if (service != null) {
                    serviceName = service.getService();
                    //Toast.makeText(adapterView.getContext(), "The service is " + adapterView.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        idProof = (Spinner) findViewById(R.id.idProof);
        String IDENTITY_PROOF_array[] = {"ADHAR", "PAN", "DL"};
        ArrayAdapter<String> postalAdapter = new ArrayAdapter<String>(this, R.layout.spinner_row, IDENTITY_PROOF_array);
        idProof.setAdapter(postalAdapter);
        idProof.setOnItemSelectedListener(new MyOnItemSelectedListener());

        getservicelist();
    }

    private void getservicelist() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(BecomeHostActivity.this);
            dotDialog.show();
            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.callServiceListSrvice(new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        parseServiceData(result);
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, BecomeHostActivity.this);
                    }
                    if (dotDialog.isShowing()) {
                        dotDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, this);//off line msg....
        }
    }

    private void parseServiceData(String result) {
        ContentServicelist data = new Gson().fromJson(result, ContentServicelist.class);
        if (data != null) {
            Servicelist[] list = data.getServicelist();
            if (list != null) {
                ArrayList<Servicelist> serviceList = new ArrayList<Servicelist>(Arrays.asList(list));
                if (serviceList != null) {
                    ServiceSpinnerAdapter spinnerAdapter = new ServiceSpinnerAdapter(BecomeHostActivity.this, serviceList);
                    profession.setAdapter(spinnerAdapter);

                }
            }
        }
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            idProofName = parent.getItemAtPosition(pos).toString();
            //Toast.makeText(parent.getContext(), "The proof is " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    private void callBcomeHost() {
        if (Utility.isOnline(this)) {
            final CircleDotDialog dotDialog = new CircleDotDialog(BecomeHostActivity.this);
            dotDialog.show();
            JSONObject obj = new JSONObject();
            try {
                String idproofName = "idproof" + System.currentTimeMillis() + ".png";
                String profileName = "profile" + System.currentTimeMillis() + ".png";

                JSONObject PATNERobj = new JSONObject();
                JSONObject IDPROOFobj = new JSONObject();
                JSONObject PROFILEobj = new JSONObject();

                PATNERobj.put("service", serviceName);
                PATNERobj.put("fullName", fullNamestr);
                PATNERobj.put("mobileNo", phoneNostr);
                PATNERobj.put("emailId", emailIdstr);
                PATNERobj.put("address", adressstr);
                PATNERobj.put("designation", designationstr);
                PATNERobj.put("idproof", idProofName);
                PATNERobj.put("idproofNumber", idNostr);
                PATNERobj.put("city", cityNamestr);
                PATNERobj.put("basicdetail", basicDetailsstr);
                PATNERobj.put("pricerange", rateRangestr);
                PATNERobj.put("fileName", profileName);
                PATNERobj.put("idFileName", idproofName);

                IDPROOFobj.put("fileName", idproofName);
                IDPROOFobj.put("bytes", idProofImage);

                PROFILEobj.put("fileName", profileName);
                PROFILEobj.put("bytes", profileImage);

                obj.put("PATNER", PATNERobj);
                obj.put("IDPROOF", IDPROOFobj);
                obj.put("PROFILE", PROFILEobj);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            ServiceCaller serviceCaller = new ServiceCaller(this);
            serviceCaller.callBecomeHostSrvice(obj, new IAsyncWorkCompletedCallback() {
                @Override
                public void onDone(String result, boolean isComplete) {
                    if (isComplete) {
                        ContentResponce data = new Gson().fromJson(result, ContentResponce.class);
                        if (data != null) {
                            if (data.isStatus()) {
                                Utility.setUserPhoneNo(BecomeHostActivity.this,phoneNostr);
                                Toast.makeText(BecomeHostActivity.this, "Data save done.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(BecomeHostActivity.this, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(BecomeHostActivity.this, "Data not save!", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Utility.alertForErrorMessage(Contants.Error, BecomeHostActivity.this);
                    }
                    if (dotDialog.isShowing()) {
                        dotDialog.dismiss();
                    }
                }
            });
        } else {
            Utility.alertForErrorMessage(Contants.OFFLINE_MESSAGE, this);//off line msg....
        }

    }

    // ----validation -----
    private boolean isValidate() {
        String emailRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        fullNamestr = fullName.getText().toString();
        cityNamestr = cityName.getText().toString();
        phoneNostr = phoneNo.getText().toString();
        designationstr = designation.getText().toString();
        basicDetailsstr = basicDetails.getText().toString();
        adressstr = adress.getText().toString();
        idNostr = idNo.getText().toString();
        rateRangestr = rateRange.getText().toString();
        emailIdstr = emailId.getText().toString();
        if (fullNamestr.length() == 0) {
            showToast("Please enter full name");
            return false;
        } else if (cityNamestr.length() == 0) {
            showToast("Please enter city name");
            return false;
        } else if (phoneNostr.length() == 0) {
            showToast("Please enter phone no");
            return false;
        } else if (designationstr.length() == 0) {
            showToast("Please enter your designation");
            return false;
        } else if (basicDetailsstr.length() == 0) {
            showToast("Please enter basic detail");
            return false;
        } else if (adressstr.length() == 0) {
            showToast("Please enter your address");
            return false;
        } else if (idNostr.length() == 0) {
            showToast("Please enter id no");
            return false;
        } else if (rateRangestr.length() == 0) {
            showToast("Please enter rate");
            return false;
        } else if (emailIdstr.length() == 0) {
            showToast("Please Enter E-mail Address");
            return false;
        } else if (emailIdstr.contains(" ")) {
            showToast("Please Enter valid E-mail Address");
            return false;
        } else if (!emailIdstr.matches(emailRegex)) {
            showToast("Please Enter valid E-mail Address");
            return false;
        } else if (idProofImage.length() == 0) {
            showToast("Please select Id proof image");
            return false;
        } else if (profileImage.length() == 0) {
            showToast("Please select profile image");
            return false;
        }
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(BecomeHostActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.idproofbutton:
                selectImageFlag = true;
                if (checkRuntimePermission()) {
                    selectImage();
                }
                break;
            case R.id.uploadbutton:
                selectImageFlag = false;
                if (checkRuntimePermission()) {
                    selectImage();
                }
                break;
            case R.id.nextButtonLayout:
                if (isValidate()) {
                    callBcomeHost();
                }
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(BecomeHostActivity.this);
        builder.setTitle("Add Profile Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    //open camera
    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
       /* Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.png");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);*/
    }

    //select image from android.widget.Gallery
    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select Photo"), SELECT_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PHOTO)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        Uri uri = null;
        if (data != null) {
            try {
                uri = data.getData();

                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                final InputStream imageStream = getContentResolver().openInputStream(uri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                if (selectImageFlag) {
                    idProofImage = Utility.encodeToBase64(selectedImage, Bitmap.CompressFormat.PNG, 100);
                    Picasso.with(BecomeHostActivity.this).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(idproofImageview);
                } else {
                    profileImage = Utility.encodeToBase64(selectedImage, Bitmap.CompressFormat.PNG, 100);
                    Picasso.with(BecomeHostActivity.this).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(profileImageview);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        Uri uri = Utility.getImageUri(BecomeHostActivity.this, thumbnail);
        if (selectImageFlag) {
            idProofImage = Utility.encodeToBase64(thumbnail, Bitmap.CompressFormat.PNG, 100);
            Picasso.with(BecomeHostActivity.this).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(idproofImageview);
        } else {
            profileImage = Utility.encodeToBase64(thumbnail, Bitmap.CompressFormat.PNG, 100);
            Picasso.with(BecomeHostActivity.this).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(profileImageview);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    selectImage();
                } else {
                    // Permission Denied
                    Toast.makeText(BecomeHostActivity.this, "Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //check storage and camera run time permission
    private Boolean checkRuntimePermission() {
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
            permissionsNeeded.add("Storage");
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add("Camera");
       /* if (!addPermission(permissionsList, Manifest.permission.WRITE_CONTACTS))
            permissionsNeeded.add("Write Contacts");*/

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                            REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                                }
                            }
                        });
                return false;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
            return false;
        }
        return true;
    }

    //add run time permission
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(BecomeHostActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(permission))
                    return false;
            }
        }
        return true;
    }

    //show permission alert
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(BecomeHostActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .setCancelable(false)
                .create()
                .show();
    }
}
