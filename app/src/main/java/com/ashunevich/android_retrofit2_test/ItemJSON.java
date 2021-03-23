package com.ashunevich.android_retrofit2_test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemJSON {

    @SerializedName("text")
    String textHolder;
    @SerializedName("updatedAt")
    String factNumber;

    public ItemJSON(String factText, String factNumber) {
        this.textHolder = factText;
        this.factNumber = factNumber;
    }
    public ItemJSON(){

    }

    public String getFactText() {
        return textHolder;
    }


    public String getFactNumber() {
        return factNumber;
    }


}
