package com.videdesk.mobile.cocassistant.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.activity.BibleActivity;
import com.videdesk.mobile.cocassistant.activity.TopicsActivity;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.config.Videx;
import com.videdesk.mobile.cocassistant.models.Theme;

import java.util.ArrayList;
import java.util.List;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.menuHolder>  implements Filterable {

    private Context mContext;
    private List<Theme> themeList;
    private List<Theme> filterList;

    public class menuHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private ImageView imgImage;
        private LinearLayout linBg;

        public menuHolder(View view) {
            super(view);
            txtTitle = view.findViewById(R.id.theme_title);
            imgImage = view.findViewById(R.id.theme_image);
            linBg = view.findViewById(R.id.theme_bg);
        }
    }

    public ThemeAdapter(Context mContext, List<Theme> themeList) {
        this.mContext = mContext;
        this.themeList = themeList;
        this.filterList = themeList;
    }

    @Override
    public ThemeAdapter.menuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_theme, parent, false);

        return new ThemeAdapter.menuHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ThemeAdapter.menuHolder holder, int position) {
        Theme theme = themeList.get(position);
        final String node = theme.getNode();
        holder.txtTitle.setText(theme.getTitle());

        holder.linBg.setBackgroundColor(mContext.getResources()
                .getColor(Videx.getResId(theme.getColor(), R.color.class)));
        holder.imgImage.setImageResource(Videx.getResId(theme.getImage(), R.drawable.class));

        holder.imgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(node);
            }
        });
        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu(node);
            }
        });

    }

    private void openMenu(String node){
        Videx videx = new Videx(mContext);
        videx.setPref(Value.COLUMN_THEME_NODE, node);
        switch (node){
            case "1":
                // Directory

                break;
            case "2":
                // Bible
                videx.setPref(Value.COLUMN_BIBLE_PAGE, Value.KEY_BIBLE_BOOKS);
                mContext.startActivity(new Intent(mContext, BibleActivity.class));
                break;
            case "3":
                // References
                mContext.startActivity(new Intent(mContext, TopicsActivity.class));
                break;
            case "4":
                // Lessons
                mContext.startActivity(new Intent(mContext, TopicsActivity.class));
                break;
            case "5":
                // Sermons

                break;
            case  "6":
                // Bible Quiz

                break;
            case "7":
                // Events

                break;
            case "8":
                // COC News

                break;
        }
    }


    @Override
    public int getItemCount() {
        return themeList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    themeList = filterList;
                } else {

                    ArrayList<Theme> filteredList = new ArrayList<>();

                    for (Theme theme : filterList) {

                        if (theme.getTitle().toLowerCase().contains(charString)) {

                            filteredList.add(theme);
                        }
                    }
                    themeList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = themeList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                themeList = (ArrayList<Theme>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
