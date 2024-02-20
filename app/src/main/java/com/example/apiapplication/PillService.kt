package com.example.apiapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PillService {
    @GET("1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01")
    fun getInfo( //가져오고자 하는 데이터
        @Query("serviceKey") serviceKey:String,
        @Query("type") type: String,
        @Query("item_name") itemName: String
        //@Query("targetEntp") targetEntp: String
    ): Call<PillInfo>
}