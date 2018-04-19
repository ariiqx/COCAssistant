package com.videdesk.mobile.cocassistant.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.videdesk.mobile.cocassistant.R;
import com.videdesk.mobile.cocassistant.config.Videx;
import com.videdesk.mobile.cocassistant.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.topicHolder>  implements Filterable {

    private Context mContext;
    private List<Topic> topicList;
    private List<Topic> filterList;
    private Videx videx;
    private String theme_node;

    public class topicHolder extends RecyclerView.ViewHolder {
        private TextView txtNode, txtTitle;

        public topicHolder(View view) {
            super(view);
            txtNode = view.findViewById(R.id.topic_node);
            txtTitle = view.findViewById(R.id.topic_title);
        }
    }

    public TopicAdapter(Context mContext, List<Topic> topicList, String theme_node) {
        this.mContext = mContext;
        this.topicList = topicList;
        this.filterList = topicList;
        this.theme_node = theme_node;
    }

    @Override
    public TopicAdapter.topicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_topic, parent, false);

        return new TopicAdapter.topicHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final TopicAdapter.topicHolder holder, int position) {
        final Topic topic = topicList.get(position);
        holder.txtTitle.setText(topic.getTitle());
        holder.txtNode.setText(topic.getNode());
        holder.txtNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (theme_node){
                    case "3":
                        // References

                        break;
                    case "4":
                        // Lessons

                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {
                    topicList = filterList;
                } else {

                    ArrayList<Topic> filteredList = new ArrayList<>();

                    for (Topic topic : filterList) {

                        if (topic.getNode().toLowerCase().contains(charString) ||
                                topic.getTitle().toLowerCase().contains(charString)) {
                            filteredList.add(topic);
                        }
                    }
                    topicList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = topicList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                topicList = (ArrayList<Topic>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
