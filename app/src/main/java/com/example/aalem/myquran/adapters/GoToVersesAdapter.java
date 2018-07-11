package com.example.aalem.myquran.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aalem.myquran.R;
import com.example.aalem.myquran.model.ViewHolder;

import java.util.ArrayList;

/**
 * Created by Aalem on 11/7/2015.
 */
public class GoToVersesAdapter extends ArrayAdapter<Integer> {
    private ViewHolder viewHolder;

    private Context context;

    private boolean condition = false;
    private View view;

    public ArrayList<Integer> versesArray = new ArrayList<Integer>();

    public GoToVersesAdapter(Context context, ArrayList<Integer> versesArray) {
//        super(context, R.layout.goto_chapter_list, versesArray);
        //remove this line and uncomment the above one
        super(context, R.layout.activity_main, versesArray);
        this.context = context;
        this.versesArray = versesArray;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //uncomment these lines
//            convertView = ((Activity) context).getLayoutInflater().inflate(
//                    R.layout.goto_versenumber_list, parent, false);
            viewHolder = new ViewHolder();
//            viewHolder.textView = (TextView) convertView.findViewById(R.id.gototv_versenumber);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(versesArray.get(position) + "");

        convertView.setTag(viewHolder);
        if(view!=null){
            selectView(view);
        }
        return convertView;
    }

    public void generateVerses(int totalVerses) {
        versesArray.clear();
        for (int i = 1; i <= totalVerses; i++) {
            versesArray.add(i);
        }
    }

    public void selectView(View view) {
        this.view = view;
        if (condition) {

        } else {
            view.setSelected(true);
        }
    }

    //Setters and Getters
    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }
}
