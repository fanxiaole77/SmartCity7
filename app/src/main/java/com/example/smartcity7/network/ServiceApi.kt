package com.example.smartcity7.network

import com.example.smartcity7.SmartCityApplication
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ServiceApi {

    @POST("/prod-api/api/login")
    fun postLogin(@Body user:Login):Call<Message>

    @GET("/prod-api/api/rotation/list")
    fun getHomeBanner():Call<HomeBanner>

    @GET("/prod-api/api/service/list")
    fun getHomeService(@Query("serviceName")serviceName:String?):Call<HomeSerive>

    @GET("/prod-api/press/category/list")
    fun getNewsType():Call<NewsType>

    @GET("/prod-api/press/press/list")
    fun getHot(@Query("hot")hot:String):Call<NewsList>

    @GET("/prod-api/press/press/list")
    fun getNewsList(@Query("title")title:String?,@Query("type")type:String?):Call<NewsList>

    @GET("/prod-api/press/press/{id}")
    fun getNewsContent(@Path("id")id:Int):Call<NewsContent>

    @GET("/prod-api/api/common/user/getInfo")
    fun getUserInfo(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<UserInfo>

    @PUT("/prod-api/api/common/user")
    fun putUser(@Body user:User,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @PUT("/prod-api/api/common/user/resetPwd")
    fun putPass(@Body pass:Pass,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @POST("/prod-api/api/common/feedback")
    fun postFeed(@Body feed:Feed,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @Multipart
    @POST("prod-api/common/upload")
    fun unload(@Part file:MultipartBody.Part,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<ImageUpload>

    @GET("/prod-api/api/pet-hospital/pet-type/list")
    fun getPetType(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<PetType>

    @GET("/prod-api/api/pet-hospital/inquiry/my-list")
    fun getMyVisList(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<MyVisList>

    @GET("/prod-api/api/pet-hospital/inquiry/list")

    fun getInquiry(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Inquiry>

    @GET("/prod-api/api/pet-hospital/pet-doctor/list")
    fun getDoctor(@Query("typeId")typeId:Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Doctor>

    @GET("/prod-api/api/pet-hospital/pet-doctor/list")
    fun getDoctor1(@Query("id")id:Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Doctor>

    @POST("/prod-api/api/pet-hospital/inquiry")
    fun postIn(@Body body:In,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<Message>

    @GET("/prod-api/api/metro/list")
    fun getMetroList(@Query("currentName")currentName:String = "建国门",@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<MetroList>

    @GET("/prod-api/api/metro/line/{id}")
    fun getPartContent(@Path("id")id: Int,@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<PartContent>

    @GET("/prod-api/api/logistics-inquiry/ad-banner/list")
    fun getLogBanner(@Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LogBanner>

    @GET("/prod-api/api/logistics-inquiry/logistics_company/list")
    fun getLogList(@Query("mame")name:String?, @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LogList>

    @GET("/prod-api/api/logistics-inquiry/logistics_company/{id}")
    fun getLogContent(@Path("id")id:Int, @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LogContent>

    @GET("/prod-api/api/logistics-inquiry/logistics_info/{no}")
    fun getLogInfo(@Path("no")no:String, @Header("Authorization")Authorization:String = SmartCityApplication.TOKEN):Call<LogInfo>


}