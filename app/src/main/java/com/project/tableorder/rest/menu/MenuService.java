package com.project.tableorder.rest.menu;

import com.project.tableorder.model.menu.MenuModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MenuService {
    @GET("api/menu")
    Observable<ArrayList<MenuModel>> getMenu();
}
