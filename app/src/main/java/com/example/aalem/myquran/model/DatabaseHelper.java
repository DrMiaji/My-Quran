package com.example.aalem.myquran.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase myDataBase;
    private final Context myContext;
    SQLiteDatabase db;
    Cursor cursor;
    private String dbPath;


    //
    List<ChapterModel> chapters = new ArrayList<>();
    HashMap<ChapterModel, List<VersesModel>> verses = new HashMap<>();

    public List<ChapterModel> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterModel> chapters) {
        this.chapters = chapters;
    }

    public HashMap<ChapterModel, List<VersesModel>> getVerses() {
        return verses;
    }

    public void setVerses(HashMap<ChapterModel, List<VersesModel>> verses) {
        this.verses = verses;
    }
//


    public DatabaseHelper(Context context) {
        super(context, StaticsModel.DB_NAME, null, 1);
//        super(context, "users", null, 1);
        this.myContext = context;

        if (android.os.Build.VERSION.SDK_INT >= 4.2) {
            dbPath = myContext.getApplicationInfo().dataDir + "/databases/";
        } else {
            dbPath = "/data/data/" + myContext.getPackageName() + "/databases/";
        }
    }

    public void createDatabase() throws IOException {
        boolean dbExists = checkDatabase();

        if (dbExists) {

        } else {
            this.getReadableDatabase();
            copyDatabase();
        }
    }

    private void copyDatabase() throws IOException {
        InputStream myInput = myContext.getAssets().open(StaticsModel.DB_NAME);
        String outFileName = "/data/data/com.example.aalem.myquran/databases/"+StaticsModel.DB_NAME;
        OutputStream myOutPut = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutPut.write(buffer, 0, length);
        }
        myOutPut.flush();
        myOutPut.close();
        myInput.close();

    }

//    private void copyDatabase() throws IOException {
//        InputStream myInput = myContext.getAssets().open(StaticsModel.DB_NAME);
//        String outFileName = dbPath + StaticsModel.DB_NAME;
//        OutputStream myOutPut = new FileOutputStream(outFileName);
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myInput.read(buffer)) > 0) {
//            myOutPut.write(buffer, 0, length);
//        }
//        myOutPut.flush();
//        myOutPut.close();
//        myInput.close();
//
//    }

    public List<ChapterModel> getChapter() {
        List<ChapterModel> chapters = new LinkedList<>();
        try{
            // 1. build the query
            String query = "SELECT  * FROM " + StaticsModel.TABLECHAPTERS;

            db = this.getWritableDatabase();

            // 2. get reference to writable DB
            cursor = db.rawQuery(query, null);

            // 3. go over each row, build book and add it to list
            ChapterModel chapterModel;
            if (cursor.moveToFirst()) {
                do {
                    chapterModel = new ChapterModel();
                    chapterModel.setChapterNumber(Integer.parseInt(cursor.getString(0)));
                    chapterModel.setChapterName(cursor.getString(1));
                    chapterModel.setChapterMcMd(cursor.getString(2));
                    chapterModel.setTotalVerses(cursor.getInt(3));

                    // Add book to books
                    chapters.add(chapterModel);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Log.e("Get Chapters:\t","Can't query the database");
        }

        // return books
        return chapters;
    }

    public List<ChapterModel> getChapterVerses() {
        List<ChapterModel> chapters = new LinkedList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + StaticsModel.TABLECHAPTERS;

        db = this.getWritableDatabase();

        // 2. get reference to writable DB
        cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ChapterModel chapterModel;
        if (cursor.moveToFirst()) {
            do {
                chapterModel = new ChapterModel();
                chapterModel.setChapterNumber(Integer.parseInt(cursor.getString(0)));
                chapterModel.setChapterName(cursor.getString(1));
                chapterModel.setTotalVerses(Integer.parseInt(cursor.getString(3)));

                chapters.add(chapterModel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return chapters;
    }

    public List<ChapterModel> getJuz() {
        List<ChapterModel> chapters = new LinkedList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + StaticsModel.TABLEJUZ;

        db = this.getWritableDatabase();

        // 2. get reference to writable DB
        cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ChapterModel chapterModel;
        if (cursor.moveToFirst()) {
            do {
                chapterModel = new ChapterModel();
                chapterModel.setChapterNumber(Integer.parseInt(cursor.getString(0)));
                chapterModel.setChapterName(cursor.getString(1));

                // Add book to books
                chapters.add(chapterModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return books
        return chapters;
    }

    public List<ChapterModel> getHizb() {
        List<ChapterModel> chapters = new LinkedList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + StaticsModel.TABLEHIZB;

        db = this.getWritableDatabase();

        // 2. get reference to writable DB
        cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ChapterModel chapterModel;
        if (cursor.moveToFirst()) {
            do {
                chapterModel = new ChapterModel();
                chapterModel.setChapterNumber(Integer.parseInt(cursor.getString(0)));
                chapterModel.setChapterName(cursor.getString(1));

                // Add book to books
                chapters.add(chapterModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return books
        return chapters;
    }

    public List<ChapterModel> getManzil() {
        List<ChapterModel> chapters = new LinkedList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + StaticsModel.TABLEMANZIL;

        db = this.getWritableDatabase();

        // 2. get reference to writable DB
        cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        ChapterModel chapterModel;
        if (cursor.moveToFirst()) {
            do {
                chapterModel = new ChapterModel();
                chapterModel.setChapterNumber(Integer.parseInt(cursor.getString(0)));
                chapterModel.setChapterName(cursor.getString(1));

                // Add book to books
                chapters.add(chapterModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return books
        return chapters;
    }


    public List<VersesModel> getVerses(String condition) {
        List<VersesModel> verses = new LinkedList<>();

        // 1. build the query
        String query = "SELECT  * FROM " + StaticsModel.TABLEVERSES + condition + " ORDER BY _id";

        db = this.getWritableDatabase();

        // 2. get reference to writable DB
        cursor = db.rawQuery(query, null);

        SharedPreferences preferences = myContext.getSharedPreferences(StaticsModel.SHAREDPREFS, Context.MODE_PRIVATE);

        int checkTranslation = preferences.getInt("translation", 0);

        // 3. go over each row, build book and add it to list
        VersesModel versesModel;
        if (cursor.moveToFirst()) {
            do {
                versesModel = new VersesModel();
                versesModel.setChapterNumber(Integer.parseInt(cursor.getString(1)));
                versesModel.setJuzNumber(Integer.parseInt(cursor.getString(2)));
                versesModel.setHizbNumber(Integer.parseInt(cursor.getString(3)));
                versesModel.setVerseNumber(Integer.parseInt(cursor.getString(5)));
                versesModel.setVerseText(cursor.getString(6));
                versesModel.setJuzStart(Integer.parseInt(cursor.getString(7)));
                versesModel.setHizbStart(Integer.parseInt(cursor.getString(8)));
                versesModel.setManzilStart(Integer.parseInt(cursor.getString(9)));
                versesModel.setSajda(Integer.parseInt(cursor.getString(10)));
                switch (checkTranslation) {
                    case 0:
                        break;
                    case 1:
                        versesModel.setVerseTranslation(cursor.getString(12));
                        break;
                    case 2:
                        versesModel.setVerseTranslation(cursor.getString(13));
                        break;
                    case 3:
                        versesModel.setVerseTranslation(cursor.getString(14));
                        break;
                    case 4:
                        versesModel.setVerseTranslation(cursor.getString(15));
                        break;
                    case 5:
                        versesModel.setVerseTranslation(cursor.getString(16));
                        break;
                    case 6:
                        versesModel.setVerseTranslation(cursor.getString(17));
                        break;
                    case 7:
                        versesModel.setVerseTranslation(cursor.getString(18));
                        break;
                }

                // Add book to books
                verses.add(versesModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return books
        return verses;
    }

    public List<VersesModel> searchVerse(String condition) {
        List<VersesModel> verses = new LinkedList<>();
        List<ChapterModel> chaptersTitle = getChapter();
        //
        int tempChapter = 0;
        List<VersesModel> tempVerseList = new ArrayList<>();
        VersesModel tempVersesModel;
        ChapterModel tempChapterModel = new ChapterModel();
        //
        // 1. build the query
        String query = "SELECT  * FROM " + StaticsModel.TABLEVERSES +
                " WHERE text_clean LIKE '" + condition + " %' "
                + "OR text_clean LIKE '%" + condition + "%'"
                + "ORDER BY _id";

        // 2. get reference to writable DB
        cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        VersesModel versesModel;
        if (cursor.moveToFirst()) {
            setChapters(new ArrayList<ChapterModel>());
            setVerses(new HashMap<ChapterModel, List<VersesModel>>());
            do {
                //
                if (cursor.getInt(1) != tempChapter || cursor.isFirst()) {
                    if (cursor.isFirst()) {
                        tempChapterModel = new ChapterModel();
                        tempChapterModel.setChapterNumber(chaptersTitle.get(cursor.getInt(1) - 1).getChapterNumber());
                        tempChapterModel.setChapterName(chaptersTitle.get(cursor.getInt(1) - 1).getChapterName());
                        getChapters().add(tempChapterModel);
                        tempChapter = cursor.getInt(1);
                    } else {
                        getVerses().put(tempChapterModel, tempVerseList);
                        if (cursor.getInt(1) != tempChapter) {
                            tempChapterModel = new ChapterModel();
                            tempChapterModel.setChapterNumber(chaptersTitle.get(cursor.getInt(1) - 1).getChapterNumber());
                            tempChapterModel.setChapterName(chaptersTitle.get(cursor.getInt(1) - 1).getChapterName());
                            getChapters().add(tempChapterModel);
                        }
                        tempVerseList = new ArrayList<>();
                    }
                    tempVersesModel = new VersesModel();
                    tempVersesModel.setVerseNumber(Integer.parseInt(cursor.getString(5)));
                    tempVersesModel.setVerseText(cursor.getString(6));
                    tempVerseList.add(tempVersesModel);
                } else {
                    tempVersesModel = new VersesModel();
                    tempVersesModel.setVerseNumber(Integer.parseInt(cursor.getString(5)));
                    tempVersesModel.setVerseText(cursor.getString(6));
                    tempVerseList.add(tempVersesModel);
                }
                if (cursor.isLast()) {
                    getVerses().put(tempChapterModel, tempVerseList);
                }
                tempChapter = cursor.getInt(1);

                //
                versesModel = new VersesModel();
                versesModel.setChapterNumber(Integer.parseInt(cursor.getString(1)));
                versesModel.setVerseNumber(Integer.parseInt(cursor.getString(5)));
                versesModel.setVerseText(cursor.getString(6));
                // Add book to books
                verses.add(versesModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // return books
//        printLists();
        return verses;

    }

    //SearchVerse() backup
//    public List<VersesModel> searchVerse(String condition) {
//        List<VersesModel> verses = new LinkedList<VersesModel>();
//        List<ChapterModel> chaptersTitle = getChapter();
//        //
//        boolean round1 = true;
//        int tempChapter = 0;
//        List<String> tempVerseList = new ArrayList<>();
//        //
//        // 1. build the query
//        String query = "SELECT  * FROM " + tableVerses +
//                " WHERE text_clean LIKE '" + condition + " %' "
//                + "OR text_clean LIKE '%" + condition + "%'"
//                + "ORDER BY _id";
//
//        // 2. get reference to writable DB
//        cursor = db.rawQuery(query, null);
//
//        // 3. go over each row, build book and add it to list
//        VersesModel versesModel;
//        if (cursor.moveToFirst()) {
//            do {
//                //
//                if (cursor.getInt(1) != tempChapter || cursor.isFirst() || cursor.isLast()) {
//                    if (cursor.isFirst()) {
//                        System.out.println("Cursor IS First: Chapter " + cursor.getInt(1));
//                        getChapters().add(chaptersTitle.get(cursor.getInt(1) - 1).getChapterName());
//                        tempChapter = cursor.getInt(1);
//                    }
//                    if (cursor.isLast()) {
//                        System.out.println("Cursor IS Last: Chapter " + cursor.getInt(1));
//                        getVerses().put(chapters.get(chapters.size() - 1), tempVerseList);
//                    } else {
//                        getVerses().put(chapters.get(getChapters().size() - 1), tempVerseList);
//                        if (cursor.getInt(1) != tempChapter) {
//                            getChapters().add(chaptersTitle.get(cursor.getInt(1) - 1).getChapterName());
//                        }
//                        tempVerseList = new ArrayList<>();
//                    }
//                    tempVerseList.add(cursor.getString(6));
//                } else {
//                    tempVerseList.add(cursor.getString(6));
//                }
//                tempChapter = cursor.getInt(1);
//
//                //
//                versesModel = new VersesModel();
//                versesModel.setChapterNumber(Integer.parseInt(cursor.getString(1)));
//                versesModel.setVerseNumber(Integer.parseInt(cursor.getString(5)));
//                versesModel.setVerseText(cursor.getString(6));
//                // Add book to books
//                verses.add(versesModel);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        // return books
////        printLists();
//        return verses;
//
//    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        String myPath="";
        try {
            myPath = dbPath + StaticsModel.DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            Log.d("CheckDatabase", "Can't open the database.");
            Log.d("DB Path", ""+myPath);
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    public void openDataBase() throws SQLException {
        String myPath = dbPath + StaticsModel.DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
