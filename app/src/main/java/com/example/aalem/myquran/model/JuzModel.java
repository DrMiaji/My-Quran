package com.example.aalem.myquran.model;

import android.os.Bundle;

public class JuzModel {

    public static final String JUZ_NUMBER = "juzNumber";
    public static final String JUZ_TEXT = "juzText";


    private int juzNumber;
    private String juzText;

    public int getJuzNumber() {
        return juzNumber;
    }

    public void setJuzNumber(int juzNumber) {
        this.juzNumber = juzNumber;
    }

    public String getJuzText() {
        return juzText;
    }

    public void setJuzText(String juzText) {
        this.juzText = juzText;
    }

    public JuzModel() {
    }

    public JuzModel(int juzNumber, String juzText) {
        this.juzNumber = juzNumber;
        this.juzText = juzText;

    }

    public String juz() {
        return juzText + "   " + juzNumber;
    }

    @Override
    public String toString() {
        return juzNumber + "   " + juzText;
    }


    //	Create from a bundle
    public JuzModel(Bundle b) {
        if (b != null) {
            this.juzNumber = b.getInt(JUZ_NUMBER);
            this.juzText = b.getString(JUZ_TEXT);
        }
    }

    //	Package data for transfer between activities
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putInt(JUZ_NUMBER, this.juzNumber);
        b.putString(JUZ_TEXT, this.juzText);
        return b;
    }
}
