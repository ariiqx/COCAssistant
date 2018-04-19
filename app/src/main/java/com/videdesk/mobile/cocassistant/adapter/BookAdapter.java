package com.videdesk.mobile.cocassistant.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.activity.BibleActivity;
import com.videdesk.mobile.cocassistant.activity.ChapActivity;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.config.Videx;
import com.videdesk.mobile.cocassistant.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.bookHolder>  implements Filterable {

    private Context mContext;
    private List<Book> bookList;
    private List<Book> filterList;

    public class bookHolder extends RecyclerView.ViewHolder {
        private TextView txtChaps, txtTitle;

        public bookHolder(View view) {
            super(view);
            txtChaps = view.findViewById(R.id.book_chaps);
            txtTitle = view.findViewById(R.id.book_title);
        }
    }

    public BookAdapter(Context mContext, List<Book> bookList) {
        this.mContext = mContext;
        this.bookList = bookList;
        this.filterList = bookList;
    }

    @Override
    public BookAdapter.bookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_book, parent, false);

        return new BookAdapter.bookHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final BookAdapter.bookHolder holder, int position) {
        final Book book = bookList.get(position);
        holder.txtTitle.setText(book.getTitle());
        holder.txtChaps.setText(book.getChapters());

        final String node = book.getNode();
        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBook(node);
            }
        });
        holder.txtChaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBook(node);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    bookList = filterList;
                } else {

                    ArrayList<Book> filteredList = new ArrayList<>();

                    for (Book book : filterList) {

                        if (book.getChapters().toLowerCase().contains(charString) ||
                                book.getTitle().toLowerCase().contains(charString)) {
                            filteredList.add(book);
                        }
                    }
                    bookList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = bookList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                bookList = (ArrayList<Book>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private void openBook(String node){
        Videx videx = new Videx(mContext);
        if(Integer.parseInt(node) > 0) {
            videx.setPref(Value.COLUMN_BOOK_NODE, node);
            videx.setPref(Value.COLUMN_BIBLE_PAGE, Value.KEY_BIBLE_CHAPS);
            mContext.startActivity(new Intent(mContext, BibleActivity.class));
        }
    }

}
