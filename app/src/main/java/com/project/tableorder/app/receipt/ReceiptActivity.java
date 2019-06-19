package com.project.tableorder.app.receipt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.project.tableorder.R;
import com.project.tableorder.app.receipt.adapter.ReceiptAdapter;
import com.project.tableorder.model.menu.AddedMenuModel;
import com.project.tableorder.model.receipt.ReceiptModel;
import com.project.tableorder.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiptActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rv_receipt_main_order_list) RecyclerView rvOrderList;
    @BindView(R.id.tv_receipt_main_order_total_price) TextView tvTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_main);
        ButterKnife.bind(this);

        // 툴바 설정
        setSupportActionBar(toolbar);
        setTitle("주문내역");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 인텐트로 받은 데이터
        final Intent intent = getIntent();
        final ReceiptModel receiptModel = (ReceiptModel) intent.getSerializableExtra(Constants.INTENT_KEY_ITEM);

        // 총 금액 계산
        int totalPrice = 0;
        for (AddedMenuModel item : receiptModel.getAddedMenuModels()) {
            totalPrice += item.getCount() * item.getMenuModel().getPrice();
        }
        tvTotalPrice.setText("총 주문 금액은 " + totalPrice + "원 입니다.");


        // 리사이클러뷰 연결
        ReceiptAdapter receiptAdapter = new ReceiptAdapter();
        receiptAdapter.setItems(receiptModel.getAddedMenuModels());
        rvOrderList.setAdapter(receiptAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
