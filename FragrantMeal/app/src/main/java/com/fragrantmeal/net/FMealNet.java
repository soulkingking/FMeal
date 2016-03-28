package com.fragrantmeal.net;

import com.fragrantmeal.entity.State;
import com.fragrantmeal.entity.User;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by CaoBin on 2016/3/21.
 */
public interface FMealNet {
    @POST("/LoginServlet.do")
    Observable<State> login(@Body User user);


}
