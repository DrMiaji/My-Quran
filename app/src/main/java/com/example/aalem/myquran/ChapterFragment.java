package com.example.aalem.myquran;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalem.myquran.model.ChapterModel;
import com.example.aalem.myquran.model.DatabaseHelper;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterFragment extends Fragment {
    public List<ChapterModel> chapters;
    MyAdapter adapter;
    Typeface font;
    Context myContext;
    DatabaseHelper db;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chapter, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_chapters);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        db = new DatabaseHelper(getActivity());
        try {
            db.createDatabase();
        } catch (IOException e) {
            Toast.makeText(myContext, "Error Creating Database", Toast.LENGTH_LONG)
                    .show();
        }
        chapters = db.getChapter();


        adapter = new MyAdapter(chapters, getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        Context context;
        List<ChapterModel> myData;

        public MyAdapter(List<ChapterModel> myData, Context context) {
            this.myData = myData;
            this.context = context;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.chapters_custom_list, parent, false);

            ViewHolder vH = new ViewHolder(view);
            return vH;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {

            if (position % 2 == 1) {
                holder.parent.setBackgroundColor(getResources().getColor(R.color.ColorAlternateList1));
            } else {
                holder.parent.setBackgroundColor(getResources().getColor(R.color.ColorAlternateList2));
            }

            holder.chapterNumber.setText("" + myData.get(position).getChapterNumber());
            holder.chapterName.setText("" + myData.get(position).getChapterName());
            holder.chapterVerses.setText("" + myData.get(position).getTotalVerses());
            holder.mcMdText.setText("" + myData.get(position).getChapterMcMd());

            holder.mcMdImage.setImageResource(myData.get(position).
                    getChapterMcMd().equals("Meccan") ?
                    R.mipmap.meccan :
                    R.mipmap.medinan
            );

            holder.chapterImage.setImageResource(getActivity().getResources().
                    getIdentifier("chapter" + (myData.get(position).getChapterNumber()),
                            "mipmap", getActivity().getPackageName()));

            holder.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, VersesActivity.class);
                    intent.putExtra("title", myData.get(position).getChapterName());
                    intent.putExtra("condition", " WHERE sura="+myData.get(position).getChapterNumber());

                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return myData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            View parent;
            TextView chapterNumber;
            TextView chapterName;
            TextView chapterVerses;
            ImageView mcMdImage;
            TextView mcMdText;
            ImageView chapterImage;

            public ViewHolder(View view) {
                super(view);
                parent = view;
                chapterNumber = view.findViewById(R.id.tvChapterNumber);
                chapterName = view.findViewById(R.id.tvChapterName);
                chapterVerses = view.findViewById(R.id.tvTotalVerses);
                mcMdText = view.findViewById(R.id.tvMcMd);
                mcMdImage = view.findViewById(R.id.imgMcMd);
                chapterImage = view.findViewById(R.id.imgChapterName);
            }
        }
    }
}