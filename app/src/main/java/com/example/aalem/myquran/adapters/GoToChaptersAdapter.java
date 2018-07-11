package com.example.aalem.myquran.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.aalem.myquran.R;
import com.example.aalem.myquran.model.ChapterModel;
import com.example.aalem.myquran.model.ViewHolder;

import java.util.List;

/**
 * Created by Aalem on 11/7/2015.
 */
public class GoToChaptersAdapter extends ArrayAdapter<ChapterModel> {
    private ViewHolder viewHolder;
    private ChapterModel currentChapter;
    private List<ChapterModel> chapters;
    private Context context;
    private int lastItemClicked = -1;

    public GoToChaptersAdapter(Context context, List<ChapterModel> chapters) {
//        super(context, R.layout.goto_chapter_list, chapters);
        //remove the following line uncomment above line
        super(context, R.layout.activity_main, chapters);
        this.chapters = chapters;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        currentChapter = chapters.get(position);
        if (convertView == null) {
            //uncomment the following lines
//            convertView = ((Activity) context).getLayoutInflater().inflate(
//                    R.layout.goto_chapter_list, parent, false);
            viewHolder = new ViewHolder();
//            viewHolder.textView = (TextView) convertView.findViewById(R.id.tvGoTo_chapterName);
//            viewHolder.nView = (TextView) convertView.findViewById(R.id.tvGoTo_chapterNumber);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(currentChapter.getChapterName());
        viewHolder.nView.setText(position + 1 + "");

//        if (lastItemClicked == position) {
//            convertView.setBackgroundColor(context.getResources().getColor(R.color.ListItemHighlightColor));
//        } else {
//            convertView.setBackgroundColor(context.getResources().getColor(R.color.white));
//        }

        convertView.setTag(viewHolder);
        return convertView;
    }

    //List item background color changer method
    public void changeListItemBackground(View previousView, View currentView, int cases) {
        switch (cases) {
            case 1:
//                currentView.setBackgroundColor(context.getResources().getColor(R.color.ListItemHighlightColor));
//                previousView.setBackgroundColor(context.getResources().getColor(R.color.white));
                break;
            case 2:
//                currentView.setBackgroundColor(context.getResources().getColor(R.color.ListItemHighlightColor));
                break;
        }

    }

    //Setters and Getters

    public int getLastItemClicked() {
        return lastItemClicked;
    }

    public void setLastItemClicked(int lastItemClicked) {
        this.lastItemClicked = lastItemClicked;
    }
}

