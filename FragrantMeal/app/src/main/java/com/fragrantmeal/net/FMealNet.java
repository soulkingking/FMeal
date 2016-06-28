package com.fragrantmeal.net;

import com.fragrantmeal.entity.BuyOrder;
import com.fragrantmeal.entity.DeliveryAddress;
import com.fragrantmeal.entity.Seller;
import com.fragrantmeal.entity.SellerCollection;
import com.fragrantmeal.entity.SellerComment;
import com.fragrantmeal.entity.SellerData;
import com.fragrantmeal.entity.TakeoutOrderStatus;
import com.fragrantmeal.entity.UserInfo;

import java.util.List;

import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import rx.Observable;

/**
 * Created by CaoBin on 2016/3/21.
 */
public interface FMealNet {
    @POST("/FMealLoginServlet")
    Observable<UserInfo> login(@Body UserInfo user);

    @POST("/FMealUpdateUserServlet")
    Observable<UserInfo> updateUser(@Body UserInfo user);

    /**
     * 上传头像
     * @param file
     * @return
     */
    @Multipart
    @POST("/FMealUploadUserIconServlet")
    Observable<Response> sendIcon(@Part("uploadFile") TypedFile file,@Query("u_id") String u_id);


    @GET("/FMealFindSellerServlet")
    Observable<List<Seller>> findAll(@Query("lng") double lng,@Query("lat") double lat);

    @GET("/FMealSellerDishServlet")
    Observable<SellerData> findSellerDishA(@Query("s_id") String s_id);

    @GET("/FMealDeliveryAddressServlet")
    Observable<List<DeliveryAddress>> findUserAddress(@Query("u_id") String u_id);

    @POST("/FMealAddDeliveryAddressServlet")
    Observable<List<DeliveryAddress>> AddUserAddress(@Body DeliveryAddress deliveryAddress);

    @GET("/FMealDeleteDeliveryAddressServlet")
    Observable<List<DeliveryAddress>> DeleteUserAddress(@Query("da_id") String da_id,@Query("u_id") String u_id);

    @POST("/FMealUpdateDeliveryAddressServlet")
    Observable<List<DeliveryAddress>> UpdateUserAddress(@Body DeliveryAddress deliveryAddress);

    @POST("/FMealOrderServlet")
    Observable<BuyOrder> sendOrder(@Body BuyOrder order);

    @GET("/FMealUserOrderServlet")
    Observable<List<BuyOrder>> finUserOrder(@Query("u_id") String u_id);

    @GET("/FMealImageServlet")
    Observable<List<String>> getImage();

    @GET("/FMeaFindCollectionServlet")
    Observable<SellerCollection> findCollection(@Query("s_id") String s_id,@Query("u_id") String u_id);

    @GET("/FMeaAddCollectionServlet")
    Observable<SellerCollection> addCollection(@Query("s_id") String s_id,@Query("u_id") String u_id);

    @GET("/FMeaDeleteCollectionServlet")
    Observable<SellerCollection> deleteCollection(@Query("s_id") String s_id,@Query("u_id") String u_id);

    @GET("/FMeaFindUserCollectionServlet")
    Observable<List<Seller>> findUserCollection(@Query("u_id") String u_id);

    @GET("/FMealFindSpeciesSellerServlet")
    Observable<List<Seller>> findSpeciesAll(@Query("lng") double lng,@Query("lat") double lat,@Query("species") String species);

    @POST("/FMealAddCommentServlet")
    Observable<SellerComment> addComment(@Body SellerComment comment);

    @GET("/FMealCommentServlet")
    Observable<List<SellerComment>> findSellerComment(@Query("s_id") String s_id);

    @GET("/FMealFindRegisterServlet")
    Observable<Boolean> FindRegister(@Query("username") String username);

    @POST("/FMealRegisterServlet")
    Observable<UserInfo> register(@Body UserInfo user);

    @POST("/FMealUpdatePasswordServlet")
    Observable<UserInfo> updatePassword(@Body UserInfo user);

    @GET("/FMealOrderStatusServlet")
    Observable<TakeoutOrderStatus> getOrderStatus(@Query("tos_id") String tos_id);

    @GET("/FMealUpdateOrderStatusServlet")
    Observable<Boolean> updateOrderStatus(@Query("tos_id") String tos_id);

}
