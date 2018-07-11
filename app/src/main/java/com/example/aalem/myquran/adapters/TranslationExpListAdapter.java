package com.example.aalem.myquran.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aalem.myquran.R;
import com.example.aalem.myquran.model.StaticsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Aalem on 3/7/2016.
 */
public class TranslationExpListAdapter extends BaseExpandableListAdapter {

    private Context context;

    TextView titlesTv;
    TextView translationTv;
    TextView languageTv;
    ImageView languageImage;

    ImageView currentCheckedImage;
    ImageView translationCheckBoxImage;
    int groupPosition;
    int childPosition;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String translationTitle = "";
    String translationLanguage = "";

    TranslationModel translationModel = new TranslationModel();
    TranslationModel interpreationModel = new TranslationModel();

    String translationLanguageArray[];
    String interpretationLanguageArray[];

    List<String> titles = new ArrayList<>();
    List<TranslationModel> translation = new ArrayList<>();
    List<TranslationModel> interpretation = new ArrayList<>();
    HashMap<String, List<TranslationModel>> translationMap = new HashMap<>();

    public TranslationExpListAdapter(Context context) {
        this.context = context;
//        translationLanguageArray = context.getResources().getStringArray(R.array.translations_language_array);
//        interpretationLanguageArray = context.getResources().getStringArray(R.array.interpretation_language_array);
        feedLists();
        sharedPreferences = context.getSharedPreferences(StaticsModel.SHAREDPREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        groupPosition = sharedPreferences.getInt(StaticsModel.EXPL_PARENT, 0);
        childPosition = sharedPreferences.getInt(StaticsModel.EXPL_CHILD, 0);

    }

    public void feedLists() {
        int i = 0;
//        for (String title : context.getResources().getStringArray(R.array.translation_titles)) {
//            this.titles.add(title);
//        }
//
//        for (String translation : context.getResources().getStringArray(R.array.translations_array)) {
//            translationModel = new TranslationModel();
//            translationModel.setLanguage(translationLanguageArray[i]);
//            translationModel.setTranslation(translation);
//            this.translation.add(translationModel);
//            i++;
//        }
//
//        i = 0;
//        for (String interpretation : context.getResources().getStringArray(R.array.interpretation_array)) {
//            interpreationModel = new TranslationModel();
//            interpreationModel.setLanguage(interpretationLanguageArray[i]);
//            interpreationModel.setTranslation(interpretation);
//            this.interpretation.add(interpreationModel);
//            i++;
//        }
//        translationMap.put(titles.get(0), interpretation);
//        translationMap.put(titles.get(1), translation);


    }

    public void printMap(HashMap mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public ImageView getCurrentCheckedImage() {
        return currentCheckedImage;
    }

    public void setCurrentCheckedImage(ImageView currentCheckedImage) {
        this.currentCheckedImage = currentCheckedImage;
    }

    @Override
    public int getGroupCount() {
        return titles.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return translationMap.get(titles.get(i)).size();
    }

    @Override
    public String getGroup(int i) {
        return titles.get(i);
    }

    @Override
    public TranslationModel getChild(int i, int i1) {
        return translationMap.get(titles.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String title = getGroup(i);

//        if (view == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = layoutInflater.inflate(R.layout.translation_exp_list_group, null);
//        }
//        titlesTv = (TextView) view.findViewById(R.id.chapterTitle);
//        titlesTv.setText(title);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        translationTitle = this.getChild(i, i1).getTranslation();
        translationLanguage = this.getChild(i, i1).getLanguage();

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = layoutInflater.inflate(R.layout.trasnlation_expl_item, null);
        }

//        this.languageTv = (TextView) view.findViewById(R.id.tvLanguage);
        this.languageTv.setText(translationLanguage);

//        this.languageImage = (ImageView) view.findViewById(R.id.translationFlagImage);
        this.languageImage.setImageResource(context.getResources().
                getIdentifier(translationLanguage.toLowerCase(),
                        "drawable", "com.ad.al_quran"));

//        this.translationCheckBoxImage = (ImageView) view.findViewById(R.id.translationCheckBoxImage);
        if(i == groupPosition && i1 == childPosition){
//            this.translationCheckBoxImage.setImageResource(R.drawable.ic_toggle_radio_button_on);
            setCurrentCheckedImage(translationCheckBoxImage);
        }else{
//            this.translationCheckBoxImage.setImageResource(R.drawable.ic_toggle_radio_button_off);
        }

        editor.putInt("tempCheckBoxImage",translationCheckBoxImage.getId());
//        translationTv = (TextView) view.findViewById(R.id.tvTranslation);
        translationTv.setText(translationTitle);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    private class TranslationModel {
        private String translation;
        private String language;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getTranslation() {
            return translation;
        }

        public void setTranslation(String translation) {
            this.translation = translation;
        }

    }
}

