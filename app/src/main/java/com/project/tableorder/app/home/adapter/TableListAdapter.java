package com.project.tableorder.app.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.project.tableorder.R;
import com.project.tableorder.model.TableModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TableListAdapter extends RecyclerView.Adapter<TableListAdapter.ViewHolder> {

    private List<TableModel> items = new ArrayList<>();

    public void setItems(List<TableModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    private OnSelectClickListener onSelectClickListener;

    public void setOnSelectClickListener(OnSelectClickListener onSelectClickListener) {
        this.onSelectClickListener = onSelectClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_main_table_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final TableModel item = items.get(position);
        holder.bindItem(item);
        holder.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectClickListener.onSelectClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_home_main_table_list_item_name) TextView tvName;
        @BindView(R.id.btn_home_main_table_list_item_select) TextView btnSelect;

        private TableModel item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItem(TableModel item) {
            this.item = item;
            tvName.setText(item.getTable_no() + "번 테이블");
        }
    }
    public interface OnSelectClickListener {
        void onSelectClick(TableModel tableModel);
    }
}
