package com.example.aalem.myquran.model;

import android.os.Bundle;

public class ChapterModel {

    public static final String CHAPTER_NUMBER = "chapterNumber";
    public static final String CHAPTER_TEXT = "chapterName";
    public static final String CHAPTER_McMd = "chapterMcMd";

    private int chapterNumber;
    private int totalVerses;
    private String chapterName;
    private String chapterMcMd;


    public ChapterModel() {
    }


    public String getChapterMcMd() {
        return chapterMcMd;
    }

    public void setChapterMcMd(String chapterMcMd) {
        this.chapterMcMd = chapterMcMd;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public int getTotalVerses() {
        return totalVerses;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }
    public void setTotalVerses(int totalVerses) {
        this.totalVerses = totalVerses;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }


    public ChapterModel(int chapNumber, String chapText) {
        chapterNumber = chapNumber;
        chapterName = chapText;

    }

    public String chapter() {
        return chapterName + "   " + chapterNumber;
    }

    @Override
    public String toString() {
        return chapterNumber + "   " + chapterName;
    }

    //	Create from a bundle
    public ChapterModel(Bundle b) {
        if (b != null) {
            this.chapterNumber = b.getInt(CHAPTER_NUMBER);
            this.chapterName = b.getString(CHAPTER_TEXT);
        }
    }

    //	Package data for transfer between activities
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putInt(CHAPTER_NUMBER, this.chapterNumber);
        b.putString(CHAPTER_TEXT, this.chapterName);
        return b;
    }

}
