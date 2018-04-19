package com.videdesk.mobile.cocassistant.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.activity.BibleActivity;
import com.videdesk.mobile.cocassistant.activity.VersesActivity;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.config.Videx;
import com.videdesk.mobile.cocassistant.models.Book;
import com.videdesk.mobile.cocassistant.models.Chap;

import java.util.ArrayList;
import java.util.List;

public class ChapAdapter extends RecyclerView.Adapter<ChapAdapter.chapHolder>  implements Filterable {

    private Context mContext;
    private List<Chap> chapList;
    private List<Chap> filterList;
    private Videx videx;

    public class chapHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtCaption;
        private LinearLayout layout;

        public chapHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.chap_title);
            txtCaption = view.findViewById(R.id.chap_caption);
            layout = view.findViewById(R.id.chap_bg);
        }
    }

    public ChapAdapter(Context mContext, List<Chap> chapList) {
        this.mContext = mContext;
        this.chapList = chapList;
        this.filterList = chapList;
    }

    @Override
    public ChapAdapter.chapHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_chap, parent, false);

        return new ChapAdapter.chapHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ChapAdapter.chapHolder holder, int position) {
        Chap chap = chapList.get(position);
        final String node = chap.getNode();

        videx = new Videx(mContext);

        if(chap.getNode() != null) {
            holder.txtTitle.setText(chap.getTitle());
            holder.txtCaption.setText(mContext.getResources().getString(R.string.txt_ch));
            holder.layout.setBackgroundColor(videx.getColor("500"));
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewChap(node);
            }
        });

        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewChap(node);
            }
        });

        holder.txtCaption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewChap(node);
            }
        });
    }

    private void viewChap(String node){
        if(node != null) {
            videx.setPref(Value.COLUMN_CHAPTER_NODE, node);
            videx.setPref(Value.COLUMN_BIBLE_PAGE, Value.KEY_BIBLE_VERSES);
            mContext.startActivity(new Intent(mContext, BibleActivity.class));
        }
    }

    @Override
    public int getItemCount() {
        return chapList.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    chapList = filterList;
                } else {

                    ArrayList<Chap> filteredList = new ArrayList<>();

                    for (Chap chap : filterList) {

                        if (chap.getNode().toLowerCase().contains(charString) ||
                                chap.getTitle().toLowerCase().contains(charString)) {
                            filteredList.add(chap);
                        }
                    }
                    chapList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = chapList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                chapList = (ArrayList<Chap>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
