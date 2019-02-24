package com.abc.tolet.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class House_Post implements Serializable {
    private byte[] image;
    private static byte[] Image;
    private String Name;
    private String Type;
    private String Bhk;
    private String Location;
    private String Address;
    private String Water;
    private String Electricity;
    private String Parking;
    private String Security;
    private int Rent;
    private long Phn;
    private Bitmap bitmap;

    public String getName(){return Name;}

    public void setName(String Name){this.Name=Name;}

    public String getType(){return Type;}

    public void setType(String Type){this.Type=Type;}

    public String getBhk(){return Bhk;}

    public void setBhk(String Bhk){this.Bhk=Bhk;}

    public String getLocation(){return Location;}

    public void setLocation(String Location){this.Location=Location;}

    public String getAddress(){return Address;}

    public void setAddress(String Address){this.Address=Address;}

    public String getWater(){return Water;}

    public void setWater(String Water){this.Water=Water;}

    public String getElectricity(){return Electricity;}

    public void setElectricity(String Electricity){this.Electricity=Electricity;}

    public String getParking(){return Parking;}

    public void setParking(String Parking){this.Parking=Parking;}

    public String getSecurity(){return Security;}

    public void setSecurity(String Security){this.Security=Security;}

    public int getRent(){return Rent;}

    public void setRent(int Rent){this.Rent=Rent;}

    public long getPhn(){return Phn;}

    public void setPhn(long Phn){this.Phn=Phn;}

    public void setImage(byte[] image){this.image =image;}

    public byte[] getImage(){return image;}

    public String setPhone(){return String.valueOf(Phn);}

    public String setR(){return String.valueOf(Rent);}

    public void setImg(Drawable drawable) {
        BitmapDrawable bitmapDrawable = ((BitmapDrawable) drawable);
        Bitmap bitmap = bitmapDrawable .getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        Image = stream.toByteArray();
    }

    public byte[] getImg() {
        return Image;
    }

    public Bitmap getPic(){
        bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        return bitmap;
    }
}
