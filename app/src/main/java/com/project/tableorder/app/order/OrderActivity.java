package com.project.tableorder.app.order;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.tableorder.R;
import com.project.tableorder.app.order.adapter.AddedMenuListAdapter;
import com.project.tableorder.app.order.adapter.MenuListAdapter;
import com.project.tableorder.model.TableModel;
import com.project.tableorder.model.menu.AddedMenuModel;
import com.project.tableorder.model.menu.MenuModel;
import com.project.tableorder.model.order.OrderDetailModel;
import com.project.tableorder.model.receipt.ReceiptModel;
import com.project.tableorder.rest.ServerClient;
import com.project.tableorder.rest.menu.MenuService;
import com.project.tableorder.rest.order.OrderService;
import com.project.tableorder.util.Actions;
import com.project.tableorder.util.Constants;
import com.project.tableorder.util.PreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OrderActivity extends AppCompatActivity {
    @BindView(R.id.rv_order_main_menu_list) RecyclerView rvMenuList;
    @BindView(R.id.rv_order_main_added_list) RecyclerView rvAddedMenuList;
    @BindView(R.id.tv_order_main_total_count) TextView tvTotalCount;
    @BindView(R.id.btn_order_main_menu_order) TextView btnOrder;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private List<AddedMenuModel> addedMenuList = new ArrayList<>();

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_main);
        ButterKnife.bind(this);

        // 툴바 설정
        setSupportActionBar(toolbar);
        setTitle("메뉴판");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 인텐트로 받은 데이터
        final Intent intent = getIntent();
        final TableModel tableModel = (TableModel) intent.getSerializableExtra(Constants.INTENT_KEY_ITEM);

        // 메뉴 리스트 가져오기
        ServerClient.getOrCreate(MenuService.class)
                .getMenu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<MenuModel>>() {
                    @Override
                    public void accept(ArrayList<MenuModel> menuModels) throws Exception {
                        MenuListAdapter menuListAdapter = new MenuListAdapter();
                        menuListAdapter.setItems(menuModels);
                        rvMenuList.setAdapter(menuListAdapter);
                        menuListAdapter.setOnAddClickListener(new MenuListAdapter.OnAddClickListener() {
                            @Override
                            public void onAddClick(MenuModel item, int count) {
                                if(count == 0) {
                                    Toast.makeText(OrderActivity.this, "최소 주문가능 수량은 1개입니다.", Toast.LENGTH_SHORT).show();
                                } else {
                                    boolean isEmpty = true;
                                    // 추가 버튼 클릭 시
                                    for(AddedMenuModel addedMenuModel: addedMenuList) {
                                        // 이미 선택 리스트에 있는 메뉴일 경우
                                        if(addedMenuModel.getMenuModel().getName() == item.getName()) {
                                            addedMenuModel.setCount(addedMenuModel.getCount() + count);
                                            isEmpty = false;
                                        }
                                    }
                                    // 선택되어있지 않은 메뉴일 경우에는 추가
                                    if(isEmpty) {
                                        addedMenuList.add(new AddedMenuModel(count, item));
                                    }
                                    final AddedMenuListAdapter addedMenuListAdapter = new AddedMenuListAdapter();
                                    addedMenuListAdapter.setItems(addedMenuList);
                                    rvAddedMenuList.setAdapter(addedMenuListAdapter);
                                    setTotalPrice();

                                    // 선택된 메뉴 클릭
                                    addedMenuListAdapter.setOnItemClickListener(new AddedMenuListAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AddedMenuModel item) {
                                            addedMenuList.remove(item);
                                            addedMenuListAdapter.setItems(addedMenuList);
                                            setTotalPrice();
                                        }
                                    });
                                }
                            }
                        });
                    }
                });

        // 주문버튼 클릭
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 선택한 메뉴가 없을 경우
                if(addedMenuList.size() == 0) {
                    Toast.makeText(OrderActivity.this, "메뉴를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(OrderActivity.this)
                            .setTitle("주문 확인")
                            .setMessage("주문하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int tableId = tableModel.getId();
                                    Gson gson = new Gson();
                                    String json = gson.toJson(addedMenuList);

                                    // 주문 데이터 저장
                                    ServerClient.getOrCreate(OrderService.class)
                                            .postOrder(tableModel.getId(), json, PreferenceHelper.getInstance(OrderActivity.this).getStringPreference(Constants.PREF_KEY_FIREBASE_TOKEN, ""))
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<List<OrderDetailModel>>() {
                                                @Override
                                                public void accept(List<OrderDetailModel> orderDetailModels) throws Exception {
                                                    // 주문 성공 시
                                                    ReceiptModel receiptModel = new ReceiptModel(addedMenuList);
                                                    Intent successIntent = new Intent(Actions.ACTION_GOTO_RECEIPT);
                                                    successIntent.putExtra(Constants.INTENT_KEY_ITEM, receiptModel);
                                                    startActivity(successIntent);
                                                    finish();
                                                }
                                            });
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }
            }
        });
    }

    private void setTotalPrice() {
        int totalPrice = 0;
        for (AddedMenuModel addedMenuItem: addedMenuList) {
            totalPrice += addedMenuItem.getCount() * addedMenuItem.getMenuModel().getPrice();
        }
        tvTotalCount.setText(totalPrice + "원");
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
