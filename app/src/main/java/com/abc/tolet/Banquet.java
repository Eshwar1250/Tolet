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

import com.abc.tolet.model.Banquet_Post;
import com.abc.tolet.sql.BanquetHelper;

public class Banquet extends AppCompatActivity implements View.OnClickListener {
    private AppCompatSpinner ac;
    private EditText location, address, rent, phn,capacity, name;
    private AppCompatCheckBox water, electricity, parking, security, decoration;
    private Button button;
    private Banquet_Post bpost;
    private FloatingActionButton imgbtn;
    private ImageView imgView;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "House";
    private BanquetHelper helper;
    private final AppCompatActivity activity = Banquet.this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banquet);
        initviews();
    }

    private void initviews() {
        name = findViewById(R.id.name);
        ac = findViewById(R.id.ac);
        location = findViewById(R.id.location);
        address = findViewById(R.id.address);
        capacity = findViewById(R.id.capacity);
        rent = findViewById(R.id.rent);
        phn = findViewById(R.id.phn);
        water = findViewById(R.id.water);
        electricity = findViewById(R.id.electricity);
        parking = findViewById(R.id.parking);
        security = findViewById(R.id.security);
        decoration = findViewById(R.id.decoration);
        button = findViewById(R.id.button);
        imgbtn = findViewById(R.id.imgbtn);
        imgView = findViewById(R.id.imgView);
        bpost = new Banquet_Post();
        helper=new BanquetHelper(activity);
        bclick();
    }

    private void bclick() {
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

        if(!isInputEditTextFilled(name,name,"Enter the Name"))
            return;
        if(!isInputEditTextFilled(location,location,"Enter the Location"))
            return;
        if(!isInputEditTextFilled(address,address,"Enter the Address"))
            return;
        if(!isInputEditTextFilled(capacity,capacity,"Enter the Capacity of the Hall"))
            return;
        if(!isInputEditTextFilled(phn,phn,"Enter the Phone no."))
            return;
        if(!isInputEditTextFilled(rent,rent,"Enter the Rent per Day"))
            return;
        if(imgView.getDrawable()==null){
            Toast.makeText(getApplicationContext(), "Error :( Upload an Image of your House)",
                    Toast.LENGTH_LONG).show();
        }


        bpost.setName(name.getText().toString().trim());
        bpost.setAc(String.valueOf(ac.getSelectedItem()));
        bpost.setLocation(location.getText().toString().trim());
        bpost.setAddress(address.getText().toString().trim());
        String value2 = rent.getText().toString().trim();
        bpost.setCapacity(Integer.parseInt(value2));
        String value = rent.getText().toString().trim();
        bpost.setRent(Integer.parseInt(value));
        String value1 = phn.getText().toString().trim();
        bpost.setPhn(Long.parseLong(value1));
        bpost.setImg(imgView.getDrawable());
        if (water.isChecked()) {
            bpost.setWater("yes");
        } else
            bpost.setWater("no");
        if (electricity.isChecked()) {
            bpost.setElectricity("yes");
        } else
            bpost.setElectricity("no");
        if (parking.isChecked()) {
            bpost.setParking("yes");
        } else
            bpost.setParking("no");
        if (security.isChecked()) {
            bpost.setSecurity("yes");
        } else
            bpost.setSecurity("no");
        if (decoration.isChecked()) {
            bpost.setSecurity("yes");
        } else
            bpost.setSecurity("no");

        if(helper.addPost(bpost)){
        Toast.makeText(getApplicationContext(), "Posted :-)", Toast.LENGTH_LONG).show();

        emptyInputEditText();
        finish();}
    }
    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/jpeg");
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
        location.setText(null);
        address.setText(null);
        rent.setText(null);
        phn.setText(null);
        capacity.setText(null);
        name.setText(null);
    }

}









