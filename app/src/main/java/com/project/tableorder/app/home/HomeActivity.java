package com.project.tableorder.app.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.project.tableorder.R;
import com.project.tableorder.app.home.adapter.TableListAdapter;
import com.project.tableorder.model.TableModel;
import com.project.tableorder.rest.ServerClient;
import com.project.tableorder.rest.table.TableService;
import com.project.tableorder.util.Actions;
import com.project.tableorder.util.Constants;
import com.project.tableorder.util.PreferenceHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.rv_home_main_table_list) RecyclerView rvTableList;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        ButterKnife.bind(this);

        Log.d(Constants.DEBUG_TAG, PreferenceHelper.getInstance(this).getStringPreference(Constants.PREF_KEY_FIREBASE_TOKEN, ""));

        // 툴바 설정
        setSupportActionBar(toolbar);
        setTitle("Table Order");

        // 테이블 리스트 가져오기
        ServerClient.getOrCreate(TableService.class)
                .getTable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<TableModel>>() {
                    @Override
                    public void accept(ArrayList<TableModel> tableModels) throws Exception {
                        Log.d(Constants.DEBUG_TAG, tableModels.toString());

                        TableListAdapter tableListAdapter = new TableListAdapter();
                        tableListAdapter.setItems(tableModels);
                        rvTableList.setAdapter(tableListAdapter);
                        tableListAdapter.setOnSelectClickListener(new TableListAdapter.OnSelectClickListener() {
                            @Override
                            public void onSelectClick(TableModel tableModel) {
                                Intent intent = new Intent(Actions.ACTION_GOTO_ORDER);
                                intent.putExtra(Constants.INTENT_KEY_ITEM, tableModel);
                                startActivity(intent);
                            }
                        });
                    }
                });

    }
}
