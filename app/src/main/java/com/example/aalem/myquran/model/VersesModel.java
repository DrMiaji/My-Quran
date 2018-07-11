package com.example.aalem.myquran.model;

import android.os.Bundle;

public class VersesModel {

    public static final String CHAPTER_NUMBER = "chapterNumber";
    public static final String JUZ_NUMBER = "juzNumber";
    public static final String HIZB_NUMBER = "hizbNumber";
    public static final String VERSE_NUMBER = "verseNumber";
    public static final String VERSE_TEXT = "verseText";
    public static final String VERSE_TRANSLATION = "verseTranslation";

    private int chapterNumber;
    private int juzNumber;
    private int manzilStart;
    private int juzStart;
    private int hizbStart;
    private int hizbNumber;
    private int verseNumber;
    private int sajda;
    private String verseText;
    private String verseTranslation;

    public VersesModel() {
    }

    public String getVerseTranslation() {
        return verseTranslation;
    }

    public void setVerseTranslation(String verseTranslation) {
        this.verseTranslation = verseTranslation;
    }


    public int getSajda() {
        return sajda;
    }

    public void setSajda(int sajda) {
        this.sajda = sajda;
    }

    public int getHizbNumber() {
        return hizbNumber;
    }

    public void setHizbNumber(int hizbNumber) {
        this.hizbNumber = hizbNumber;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setJuzNumber(int juzNumber) {
        this.juzNumber = juzNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public int getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(int verseNumber) {
        this.verseNumber = verseNumber;
    }

    public String getVerseText() {
        return verseText;
    }

    public void setVerseText(String verseText) {
        this.verseText = verseText;
    }


    public int getManzilStart() {
        return manzilStart;
    }

    public void setManzilStart(int manzilStart) {
        this.manzilStart = manzilStart;
    }

    public int getHizbStart() {
        return hizbStart;
    }

    public void setHizbStart(int hizbStart) {
        this.hizbStart = hizbStart;
    }

    public int getJuzStart() {
        return juzStart;
    }

    public void setJuzStart(int juzStart) {
        this.juzStart = juzStart;
    }

    public int getJuzNumber() {
        return juzNumber;
    }

    public VersesModel(int suraN, int ayaN, String ayaT) {
        chapterNumber = suraN;
        verseNumber = ayaN;
        verseText = ayaT;
    }


    @Override
    public String toString() {
        if (verseNumber == 0) {
            return verseText;
        } else {
            return verseText + "   " + verseNumber;
        }
    }

    //	Create from a bundle
    public VersesModel(Bundle b) {
        if (b != null) {
            this.chapterNumber = b.getInt(CHAPTER_NUMBER);
            this.verseNumber = b.getInt(VERSE_NUMBER);
            this.verseText = b.getString(VERSE_TEXT);
        }
    }

    //	Package data for transfer between activities
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putInt(CHAPTER_NUMBER, this.chapterNumber);
        b.putInt(VERSE_NUMBER, this.verseNumber);
        b.putString(VERSE_TEXT, this.verseText);
        return b;
    }

}
