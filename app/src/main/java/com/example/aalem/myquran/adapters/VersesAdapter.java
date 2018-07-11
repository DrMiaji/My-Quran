package com.example.aalem.myquran.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aalem.myquran.R;
import com.example.aalem.myquran.model.ChapterModel;
import com.example.aalem.myquran.model.StaticsModel;
import com.example.aalem.myquran.model.VersesModel;
import com.example.aalem.myquran.model.ViewHolder;

import java.util.List;

/**
 * Created by Aalem on 12/1/2015.
 */
public class VersesAdapter extends ArrayAdapter<VersesModel> {
    Context context;
    List<VersesModel> verses;
    public List<ChapterModel> chapterName;
    VersesModel currentVerse;
    ViewHolder viewHolder;
    SharedPreferences sharedPreferences;
    Typeface font;
    int rowBackground1;
    int rowBackground2;

    public VersesAdapter(Context context, List<VersesModel> verses, List<ChapterModel> chapterName) {
//        super(context, R.layout.verses_custom_list, verses);
        //UNCOMMENT above and remove the following

        super(context, R.layout.activity_main, verses);
        this.verses = verses;
        this.context = context;
        this.chapterName = chapterName;
        sharedPreferences = context.getSharedPreferences(StaticsModel.SHAREDPREFS, Context.MODE_PRIVATE);
        //UNCOMMENT the following

//        rowBackground1 = ContextCompat.getColor(context, R.color.ColorAlternateList1);
//        rowBackground2 = ContextCompat.getColor(context, R.color.ColorAlternateList2);
        font = Typeface.createFromAsset(context.getAssets(), "UthmanTN1_Ver09.otf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        currentVerse = verses.get(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();

            //UNCOMMENT THE FOLLOWING
//            convertView = ((Activity) context).getLayoutInflater().inflate(
//                    R.layout.verses_custom_list, parent, false);
//            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView_Verse);
//            viewHolder.textView.setTextSize(sharedPreferences.getInt(StaticsModel.VERSEFONTSIZE, 30));
//            viewHolder.nView = (TextView) convertView.findViewById(R.id.textView_verseNumber);
//            viewHolder.hizbNumber = (TextView) convertView.findViewById(R.id.textView_hizbNumber);
//            viewHolder.juzbNumber = (TextView) convertView.findViewById(R.id.textView_juzNumber);
//            viewHolder.hizbRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.hizbNumberIcRL);
//            viewHolder.juzRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.juzNumberIcRL);
//            viewHolder.textViewTranslation = (TextView) convertView.findViewById(R.id.textView_VerseTranslation);
//            viewHolder.textViewTranslation.setTextSize(sharedPreferences.getInt(StaticsModel.TRANSLATIONFONTSIZE, 18));
//            viewHolder.headerRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.headerRelativeLayout);
//            viewHolder.bismillahRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.bismillahRL);
//            viewHolder.chapterName = (TextView) convertView.findViewById(R.id.chapterName);
//            viewHolder.chapterNameBorder = (ImageView) convertView.findViewById(R.id.chapterNameBorder);
//            viewHolder.chapterNameImage = (ImageView) convertView.findViewById(R.id.chapterNameImage);
            viewHolder.chapterName.setTypeface(font);
            viewHolder.textView.setTypeface(font);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setRowBackground(convertView, position);
        setHizbIconVisibility(viewHolder.hizbNumber, viewHolder.hizbRelativeLayout, currentVerse.getHizbStart(), currentVerse.getHizbNumber());
        setJuzIconVisibility(viewHolder.juzbNumber, viewHolder.juzRelativeLayout, currentVerse.getJuzStart(), currentVerse.getJuzNumber());

        if (currentVerse.getVerseNumber() == 1) {
            viewHolder.chapterModel = chapterName.get(currentVerse.getChapterNumber() - 1);
            viewHolder.headerRelativeLayout.setVisibility(View.VISIBLE);
            viewHolder.chapterNameImage.setImageResource(context.getResources().
                    getIdentifier("chapter" + (currentVerse.getChapterNumber()),
                            "drawable", "com.ad.al_quran"));

            bismillahLayoutVisibility(currentVerse.getChapterNumber(), viewHolder.bismillahRelativeLayout);
        } else {
            viewHolder.headerRelativeLayout.setVisibility(View.GONE);
            viewHolder.bismillahRelativeLayout.setVisibility(View.GONE);
        }
        viewHolder.textView.setText(currentVerse.getVerseText());
        translationVisibility(sharedPreferences.getInt(StaticsModel.TRANSLATION, 0), viewHolder.textViewTranslation);
        viewHolder.textViewTranslation.setText(currentVerse.getVerseTranslation());
        viewHolder.nView.setText(currentVerse.getVerseNumber() + "");

        return convertView;
    }

    // Change alternate row background color
    public void setRowBackground(View tempConvertView, int position) {
        if (position % 2 == 1) {
            tempConvertView.setBackgroundColor(rowBackground1);
        } else {
            tempConvertView.setBackgroundColor(rowBackground2);
        }
    }

    // Show/hide hizb icon
    public void setHizbIconVisibility(TextView hizbNumberTv, RelativeLayout hizbLayout, int hizbStart, int hizbNumber) {
        if (hizbStart != 0) {
            hizbNumberTv.setText(hizbNumber + "");
            hizbLayout.setVisibility(View.VISIBLE);
        } else {
            hizbLayout.setVisibility(View.GONE);
        }
    }

    // Show/hide juz icon
    public void setJuzIconVisibility(TextView juzNumberTv, RelativeLayout juzLayout, int juzStart, int juzNumber) {
        if (juzStart != 0) {
            juzNumberTv.setText(juzNumber + "");
            juzLayout.setVisibility(View.VISIBLE);
        } else {
            juzLayout.setVisibility(View.GONE);
        }
    }

    // Show/hide bismillah layout
    public void bismillahLayoutVisibility(int chapterNumber, RelativeLayout bismillahLayout) {
        if (chapterNumber == 1 || chapterNumber == 9) {
            bismillahLayout.setVisibility(View.GONE);
        } else {
            bismillahLayout.setVisibility(View.VISIBLE);
        }
    }

    // Show/hide verse translation TextView
    public void translationVisibility(int translationColumn, TextView translationTextView) {
        if (sharedPreferences.getInt(StaticsModel.TRANSLATION, 0) == 0) {
            translationTextView.setVisibility(View.GONE);
        } else {
            translationTextView.setVisibility(View.VISIBLE);
        }
    }
}
