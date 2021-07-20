package com.ashunevich.android_retrofit2_test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemJSON {

   @SerializedName("title")
    String textHolder;

    @SerializedName("id")
    int factNumber;

    public ItemJSON(String factText, int factNumber) {
        this.textHolder = factText;
        this.factNumber = factNumber;
    }

    public String getFactText() {
        return textHolder;
    }


    public int getFactNumber() {
        return factNumber;
    }
}
