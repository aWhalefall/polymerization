package retrofit.request;

import java.util.List;

import bean.JavaBean;
import io.reactivex.Observable;
import retrofit.respond.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Request {

    // 填上需要访问的服务器地址
     String HOST = "http://route.showapi.com/";
    
    @GET("181-1")
    Observable<Response<JavaBean>> getWeatherByAddress(
            @Query("showapi_appid") String lv1,
            @Query("showapi_sign") String v2,
            @Query("num") String v3
            );

}

