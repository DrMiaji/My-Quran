package com.example.aalem.myquran.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aalem.myquran.model.ChapterModel;
import com.example.aalem.myquran.model.StaticsModel;
import com.example.aalem.myquran.model.VersesModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "quran";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    private Cursor cursor;

    SQLiteDatabase db;

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

        SharedPreferences preferences = mContext.getSharedPreferences(StaticsModel.SHAREDPREFS, Context.MODE_PRIVATE);

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

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
//        InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }
}
