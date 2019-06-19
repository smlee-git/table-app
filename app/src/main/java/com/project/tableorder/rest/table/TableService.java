package com.project.tableorder.rest.table;

import com.project.tableorder.model.TableModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TableService {

    @GET("api/table")
    Observable<ArrayList<TableModel>> getTable();
}
