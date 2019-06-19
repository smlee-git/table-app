package com.project.tableorder.rest.order;

import com.project.tableorder.model.order.OrderDetailModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OrderService {
    @FormUrlEncoded
    @POST("api/order")
    Observable<List<OrderDetailModel>> postOrder(
            @Field("table_id") int tableId,
            @Field("order_data") String orderData,
            @Field("fb_token") String fbToken
    );
}
