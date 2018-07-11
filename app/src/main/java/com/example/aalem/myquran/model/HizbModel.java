package com.example.aalem.myquran.model;

import android.os.Bundle;

public class HizbModel {

    public static final String CHAPTER_NUMBER = "chapterNumber";
    public static final String CHAPTER_TEXT = "chapterText";


    private int chapterNumber;
    private String chapterText;


    public HizbModel() {
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getChapterText() {
        return chapterText;
    }

    public void setChapterText(String chapterText) {
        this.chapterText = chapterText;
    }



    public HizbModel(int chapNumber, String chapText) {
        chapterNumber = chapNumber;
        chapterText = chapText;

    }

    public String chapter() {
        return chapterText + "   " + chapterNumber;
    }

    @Override
    public String toString() {
        return chapterNumber + "   " + chapterText;
    }

    //	Create from a bundle
    public HizbModel(Bundle b) {
        if (b != null) {
            this.chapterNumber = b.getInt(CHAPTER_NUMBER);
            this.chapterText = b.getString(CHAPTER_TEXT);
        }
    }

    //	Package data for transfer between activities
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putInt(CHAPTER_NUMBER, this.chapterNumber);
        b.putString(CHAPTER_TEXT, this.chapterText);
        return b;
    }

}
