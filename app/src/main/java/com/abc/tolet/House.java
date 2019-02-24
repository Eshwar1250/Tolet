package com.abc.tolet;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.abc.tolet.model.House_Post;
import com.abc.tolet.sql.HouseHelper;

public class House extends AppCompatActivity implements View.OnClickListener {
    private AppCompatSpinner type, bhk;
    private EditText location, address, rent, phn, name;
    private AppCompatCheckBox water, electricity, parking, security;
    private Button button;
    private House_Post hpost;
    private FloatingActionButton imgbtn;
    private ImageView imgView;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "House";
    private HouseHelper helper;
    private final AppCompatActivity activity = House.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        initviews();
    }

    private void initviews() {
        name = findViewById(R.id.name);
        type = findViewById(R.id.type_house);
        bhk = findViewById(R.id.bhk);
        location = findViewById(R.id.location);
        address = findViewById(R.id.address);
        rent = findViewById(R.id.rent);
        phn = findViewById(R.id.phn);
        water = findViewById(R.id.water);
        electricity = findViewById(R.id.electricity);
        parking = findViewById(R.id.parking);
        security = findViewById(R.id.security);
        button = findViewById(R.id.button);
        imgbtn = findViewById(R.id.imgbtn);
        imgView = findViewById(R.id.imgView);
        hpost = new House_Post();
        helper=new HouseHelper(activity);
        pclick();
    }

    private void pclick() {
        button.setOnClickListener(this);
        imgbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                post();
                break;
            case R.id.imgbtn:
                openImageChooser();
                break;
        }
    }

    private void post() {

        if(!isInputEditTextFilled(name,name,"Enter Owner Name"))
            return;
        if(!isInputEditTextFilled(location,location,"Enter the Location"))
            return;
        if(!isInputEditTextFilled(address,address,"Enter the Location"))
            return;
        if(!isInputEditTextFilled(phn,phn,"Enter the Location"))
            return;
        if(!isInputEditTextFilled(rent,rent,"Enter the Location"))
            return;
        if(imgView.getDrawable()==null){
            Toast.makeText(getApplicationContext(), "Error :( Upload an Image of your House)",
                    Toast.LENGTH_LONG).show();
        }

        hpost.setName(name.getText().toString().trim());
        hpost.setType(String.valueOf(type.getSelectedItem()));
        hpost.setBhk(String.valueOf(bhk.getSelectedItem()));
        hpost.setLocation(location.getText().toString().trim());
        hpost.setAddress(address.getText().toString().trim());
        String value = rent.getText().toString().trim();
        hpost.setRent(Integer.parseInt(value));
        String value1 = phn.getText().toString().trim();
        hpost.setPhn(Long.parseLong(value1));
        hpost.setImg(imgView.getDrawable());
        if (water.isChecked()) {
            hpost.setWater("yes");
        } else
            hpost.setWater("no");
        if (electricity.isChecked()) {
            hpost.setElectricity("yes");
        } else
            hpost.setElectricity("no");
        if (parking.isChecked()) {
            hpost.setParking("yes");
        } else
            hpost.setParking("no");
        if (security.isChecked()) {
            hpost.setSecurity("yes");
        } else
            hpost.setSecurity("no");

        if(helper.addPost(hpost)){
        Toast.makeText(getApplicationContext(), "Posted :-)",
                Toast.LENGTH_LONG).show();

        emptyInputEditText();
        finish();}
    }
     private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);
                    Log.i(TAG, "Image Path : " + path);
                    // Set the image in ImageView
                    ((ImageView) findViewById(R.id.imgView)).setImageURI(selectedImageUri);
                }
            }
        }
    }
    private String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public boolean isInputEditTextFilled(EditText textInputEditText,EditText textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputLayout.setError(message);
            return false;
        }
        return true;
    }
    private void emptyInputEditText() {
        name.setText(null);
        location.setText(null);
        address.setText(null);
        rent.setText(null);
        phn.setText(null);
    }

}









