package retrofit.request;

import java.util.List;

import bean.JavaBean;
import io.reactivex.Observable;
import retrofit.respond.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Request {

    // 填上需要访问的服务器地址
     String HOST = "http://api.map.baidu.com/telematics/v3/weather";
    
    @POST("?service=sser.getList")
    Observable<Response<List<JavaBean>>> getWeatherByAddress(
            @Query("location") String lv1,
            @Query("output") String v2,
            @Query("ak") String v3
            );

}

