package com.project.tableorder.app.order.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.tableorder.R;
import com.project.tableorder.model.menu.MenuModel;
import com.project.tableorder.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder>{

    private List<MenuModel> items = new ArrayList<>();
    private Context context;

    public void setItems(List<MenuModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    private OnAddClickListener onAddClickListener;

    public void setOnAddClickListener(OnAddClickListener onAddClickListener) {
        this.onAddClickListener = onAddClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_main_menu_list_item, parent, false);
        context = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final MenuModel item = items.get(position);
        holder.bindItem(item);
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(holder.etCount.getText().toString());
                onAddClickListener.onAddClick(item, count);
                Log.d(Constants.DEBUG_TAG, count + "개가 선택되었습니다.");
            }
        });

        if(item.getPoster() != null) {
            Glide.with(context)
                    .load(item.getPoster())
                    .into(holder.ivPoster);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_main_menu_list_item_name) TextView tvName;
        @BindView(R.id.tv_order_main_menu_list_item_price) TextView tvPrice;
        @BindView(R.id.btn_order_main_menu_list_add) TextView btnAdd;
        @BindView(R.id.et_order_main_menu_list_item_count) EditText etCount;
        @BindView(R.id.iv_order_main_menu_list_item_img) ImageView ivPoster;

        private MenuModel item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(MenuModel item) {
            this.item = item;
            tvName.setText(item.getName());
            tvPrice.setText(item.getPrice() + "원");
        }
    }

    public interface OnAddClickListener {
        void onAddClick(MenuModel item, int count);
    }
}
