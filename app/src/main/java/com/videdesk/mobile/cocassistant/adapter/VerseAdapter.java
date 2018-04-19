package com.videdesk.mobile.cocassistant.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.config.Videx;
import com.videdesk.mobile.cocassistant.models.Verse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class VerseAdapter  extends RecyclerView.Adapter<VerseAdapter.verseHolder>  implements Filterable {

    private Context mContext;
    private List<Verse> verseList;
    private List<Verse> filterList;
    private Videx videx;
    private String uid;
    private String searchKey = "";

    public class verseHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtDetails;

        public verseHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.verse_title);
            txtDetails = view.findViewById(R.id.verse_details);
        }
    }

    public VerseAdapter(Context mContext, List<Verse> verseList, String uid) {
        this.mContext = mContext;
        this.verseList = verseList;
        this.filterList = verseList;
        this.uid = uid;
    }

    @Override
    public VerseAdapter.verseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_verse, parent, false);

        return new VerseAdapter.verseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final VerseAdapter.verseHolder holder, int position) {
        final Verse verse = verseList.get(position);
        holder.txtTitle.setText(verse.getTitle());
        holder.txtDetails.setText(verse.getDetails());

        videx = new Videx(mContext);
        String key = verse.getDetails().toLowerCase(Locale.getDefault());
        if(key.contains(searchKey)){
            int startPos = key.indexOf(searchKey);
            int endPos = startPos + searchKey.length();

            Spannable spanString = Spannable.Factory.getInstance().newSpannable(holder.txtDetails.getText());
            spanString.setSpan(new ForegroundColorSpan(Color.RED), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            holder.txtDetails.setText(spanString);
        }

        holder.txtDetails.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showPopup(verse, holder.txtDetails);
                return false;
            }
        });

    }


    @Override
    public int getItemCount() {
        return verseList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                VerseAdapter.this.searchKey = charString.toLowerCase(Locale.getDefault());

                if (charString.isEmpty()) {
                    verseList = filterList;
                } else {

                    ArrayList<Verse> filteredList = new ArrayList<>();

                    for (Verse verse : filterList) {

                        if (verse.getCode().toLowerCase().contains(charString) ||
                                verse.getTitle().toLowerCase().contains(charString) ||
                                verse.getDetails().toLowerCase().contains(charString)) {
                            filteredList.add(verse);
                        }
                    }
                    verseList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = verseList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                verseList = (ArrayList<Verse>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private void showPopup(final Verse verse, View view){

        if(Integer.parseInt(verse.getNode()) > 0) {
            //creating a popup menu
            PopupMenu popup = new PopupMenu(mContext, view);
            //inflating menu from xml resource
            popup.inflate(R.menu.popup_verse);
            //adding click listener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_share:
                            shareVerse(verse);
                            break;
                        case R.id.menu_bookmark:
                            //
                            break;
                        case R.id.menu_notebook:
                            //
                            break;
                        case R.id.menu_highlight:
                            //
                            break;
                    }
                    return false;
                }
            });
            //displaying the popup
            popup.show();
        }
    }
    private void shareVerse(Verse verse){
        /*
        Matt 4:5
        Enoch is a good boy.
        KJV
         */
        String book_node = verse.getBook_node();
        String[] books = Value.books;

        String book_title = "";
        for(int s = 0; s < books.length; s++) {
            int id = s + 1;
            if(id == Integer.parseInt(book_node)) {
                String[] expo = books[s].split(",");
                book_title = expo[3];
            }
        }

        String bible = videx.getPref(Value.COLUMN_BIBLE_NODE).toUpperCase();
        String title = "Share With";
        String body = "*" + book_title + " " +  verse.getChapter_node() + ":" + verse.getTitle() +  "*\n" + verse.getDetails() +
                "\n*" + bible + "*\n\n" + "TESTING: Holy Bible by Vide Desk http://www.videdesk.com/";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, body);
        sendIntent.setType("text/plain");
        mContext.startActivity(Intent.createChooser(sendIntent, title));
    }
}
