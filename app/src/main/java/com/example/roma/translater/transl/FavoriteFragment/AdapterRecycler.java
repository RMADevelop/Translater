package com.example.roma.translater.transl.FavoriteFragment;

import android.content.ContentValues;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roma.translater.R;
import com.example.roma.translater.data.TranslateItem;

import java.util.ArrayList;
import java.util.List;


public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.FavoriteViewHolder> {

    FavoriteContract.FavoriteRecyclerItemClickListener listener;
    Context context;
    private List<TranslateItem> list = new ArrayList<>();
    private List<TranslateItem> listDelete = new ArrayList<>();


    public AdapterRecycler(FavoriteContract.FavoriteRecyclerItemClickListener listener, List<TranslateItem> list, Context context) {
        this.listener = listener;
        this.list = list;
        this.context = context;
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFavorite;
        TextView wordIn;
        TextView wordOut;
        TextView langs;
        ConstraintLayout container;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            wordIn = (TextView) itemView.findViewById(R.id.word_in);
            wordOut = (TextView) itemView.findViewById(R.id.word_out);
            imageFavorite = (ImageView) itemView.findViewById(R.id.image_favorite);
            imageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.clickFavorite(list.get(getAdapterPosition()));
                }
            });
            container = (ConstraintLayout) itemView.findViewById(R.id.container_child_favorite);
            container.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listDelete.contains(list.get(getAdapterPosition()))) {
                        listDelete.remove(list.get(getAdapterPosition()));
                        list.get(getAdapterPosition()).setDelete(0);
                    } else {
                        listDelete.add(list.get(getAdapterPosition()));
                        list.get(getAdapterPosition()).setDelete(1);
                    }
                    listener.sendListDelete(listDelete);
                    notifyItemChanged(getAdapterPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.favorite_child_recycler, parent, false);

        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        holder.wordIn.setText(list.get(position).getWordIn());
        holder.wordOut.setText(list.get(position).getWordOut());
        if (list.get(position).isDelete()) {
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.grey));
        } else
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.cardview_light_background));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void updateDataInRecycler() {
        notifyDataSetChanged();
    }

    public void updateDataInRecycler(List<TranslateItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void updateWithoutDelete(){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isDelete()) {
                list.remove(i);
                i--;
            }
        }
        notifyDataSetChanged();
        Log.v("DFSDSDFSDFDF", " " + list.size());
    }
}
