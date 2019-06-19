package com.project.tableorder.app.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.project.tableorder.R;
import com.project.tableorder.model.menu.AddedMenuModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

public class AddedMenuListAdapter extends RecyclerView.Adapter<AddedMenuListAdapter.ViewHolder>{

    private List<AddedMenuModel> items = new ArrayList<>();

    private Context context;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setItems(List<AddedMenuModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_main_added_list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AddedMenuModel item = items.get(position);
        holder.bindItem(item);
        Glide.with(context)
                .load(item.getMenuModel().getPoster())
                .into(holder.ivPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_order_main_added_menu_list_poster) ImageView ivPoster;

        private AddedMenuModel item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(AddedMenuModel item) {
            this.item = item;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(AddedMenuModel item);
    }
}
