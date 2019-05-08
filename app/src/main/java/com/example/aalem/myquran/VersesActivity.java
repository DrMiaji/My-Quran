package com.example.aalem.myquran;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalem.myquran.model.DatabaseHelper;
import com.example.aalem.myquran.model.VersesModel;

import java.io.IOException;
import java.util.List;

public class VersesActivity extends AppCompatActivity {

    private ActionMode actionMode;
    List<VersesModel> verses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verses);

//        DatabaseHelper db = new DatabaseHelper(this);
//        try {
//            db.createDatabase();
//        } catch (IOException e) {
//            Toast.makeText(this, "Error Creating Database", Toast.LENGTH_LONG)
//                    .show();
//        }
//        verses = db.getVerses(getIntent().getStringExtra("condition"));

        verses = ((MyApplication)getApplication()).dbHelper.getVerses(getIntent().getStringExtra("condition"));

        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView versesList = findViewById(R.id.recycler_verses);
        versesList.setLayoutManager(new LinearLayoutManager(this));
        versesList.setAdapter(new MyAdapter(this, verses));

        versesList.addOnItemTouchListener(new RecyclerItemClickListener(this, versesList, new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onLongItemClick(View view, int position) {
                if (actionMode != null) {
                    return;
                }
                actionMode = startSupportActionMode(mActionModeCallBack);
                view.setBackgroundColor(getResources().getColor(R.color.pressed_color));
                return;
            }
        }));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.finish();
        return true;
    }


    // Recycler Adapter
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        Context myContext;
        List<VersesModel> verses;

        public MyAdapter(Context context, List<VersesModel> verses) {
            this.myContext = context;
            this.verses = verses;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(myContext).inflate(R.layout.verses_custom_list, parent, false);
//            view.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    if (actionMode != null) {
//                        return false;
//                    }
//                    actionMode = startSupportActionMode(mActionModeCallBack);
//                    view.setBackgroundColor(getResources().getColor(R.color.pressed_color));
//                    return true;
//                }
//            });

            ViewHolder vH = new ViewHolder(view);
            return vH;
        }

        @Override
        public void onBindViewHolder(final MyAdapter.ViewHolder holder, int position) {

            if (position % 2 == 0) {
                holder.verseBackground.setBackgroundColor(getResources().getColor(R.color.ColorAlternateList1));
            } else {
                holder.verseBackground.setBackgroundColor(getResources().getColor(R.color.ColorAlternateList2));
            }

            holder.verse.setText(verses.get(position).getVerseText());
            holder.verseNumber.setText("" + verses.get(position).getVerseNumber());
            holder.hizbNumber.setText("" + verses.get(position).getHizbNumber());
            holder.juzNumber.setText("" + verses.get(position).getJuzNumber());
            holder.chapterName.setImageResource(getResources().getIdentifier("chapter" + verses.get(position).getChapterNumber(), "mipmap", getPackageName()));

            setViewVisibility(holder.header, verses.get(position).getVerseNumber());
            if (verses.get(position).getChapterNumber() != 1 && verses.get(position).getChapterNumber() != 9) {
                setViewVisibility(holder.bismillah, verses.get(position).getVerseNumber());
            }
            setViewVisibility(holder.hizbIconRL, verses.get(position).getHizbStart());
            setViewVisibility(holder.juzIconRL, verses.get(position).getJuzStart());


        }

        @Override
        public int getItemCount() {
            return verses.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            View itemView;
            TextView verse;
            TextView verseNumber;
            TextView hizbNumber;
            TextView juzNumber;
            RelativeLayout header;
            RelativeLayout bismillah;
            RelativeLayout juzIconRL;
            RelativeLayout hizbIconRL;
            ConstraintLayout verseBackground;
            ImageView chapterName;


            public ViewHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                verse = itemView.findViewById(R.id.textView_Verse);
                verseNumber = itemView.findViewById(R.id.textView_verseNumber);
                hizbNumber = itemView.findViewById(R.id.textView_hizbNumber);
                juzNumber = itemView.findViewById(R.id.textView_juzNumber);
                header = itemView.findViewById(R.id.headerRelativeLayout);
                bismillah = itemView.findViewById(R.id.bismillahRL);
                juzIconRL = itemView.findViewById(R.id.juzNumberIcRL);
                hizbIconRL = itemView.findViewById(R.id.hizbNumberIcRL);
                verseBackground = itemView.findViewById(R.id.verses_background);
                chapterName = itemView.findViewById(R.id.chapterNameImage);
            }
        }
    }

    // ActionMode
    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode myActionMode, Menu menu) {
            myActionMode.getMenuInflater().inflate(R.menu.cab_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode myActionMode, MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.delete) {
                Toast.makeText(VersesActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                myActionMode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode myActionMode) {
            actionMode = null;
        }
    };

    // View visibility
    public void setViewVisibility(View view, int condition) {
        if (condition == 1) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;


        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onLongItemClick(View view, int position);
    }

}
