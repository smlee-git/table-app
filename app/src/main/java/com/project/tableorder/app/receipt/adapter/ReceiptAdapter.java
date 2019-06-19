package com.project.tableorder.app.receipt.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.project.tableorder.R;
import com.project.tableorder.model.menu.AddedMenuModel;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ViewHolder>{
    private List<AddedMenuModel> items = new ArrayList<>();
    private Context context;

    public void setItems(List<AddedMenuModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_main_order_list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final AddedMenuModel item = items.get(position);
        holder.bindItem(item);

        if(item.getMenuModel().getPoster() != null) {
            Glide.with(context)
                    .load(item.getMenuModel().getPoster())
                    .into(holder.ivPoster);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_receipt_main_order_list_item_poster) ImageView ivPoster;
        @BindView(R.id.tv_receipt_main_order_list_item_name) TextView tvName;
        @BindView(R.id.tv_receipt_main_order_list_item_price) TextView tvPrice;
        @BindView(R.id.tv_receipt_main_order_list_item_count) TextView tvCount;

        private AddedMenuModel item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(AddedMenuModel item) {
            this.item = item;
            tvName.setText(item.getMenuModel().getName());
            tvPrice.setText(item.getMenuModel().getPrice() + "원");
            tvCount.setText(item.getCount() + "개");
        }
    }
}
