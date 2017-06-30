package com.chengzj.app.data.source.remote;

import com.chengzj.app.data.entity.GankDaily;
import com.chengzj.app.data.entity.NewsList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chengzj on 2017/6/18.
 */

public interface HttpApi {
    //http://gank.io/api/day/2016/10/12
    public static final String base_url = "http://gank.io/api/";

    //http://accounttest.csc108.com:9800/api/news20/list?req_funType=L295&req_count=20
    public static final String yt_url = "http://accounttest.csc108.com:9800/api/news20/";

    /**
     * @param year year
     * @param month month
     * @param day day
     * @return Observable<GankDaily>
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankDaily> getDaily(
            @Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("list")
    Observable<NewsList>  getNewsList(@Query("req_funType") String funType,
                                      @Query("req_count") String count);
}
