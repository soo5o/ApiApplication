package com.example.apiapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiapplication.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    //val apikey = BuildConfig.MY_KEY
    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private val adapter by lazy {Adapter(dataList)}
    private val dataList = mutableListOf<Item?>()
    private var currentCall: Call<PillInfo>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.btnData.setOnClickListener{
            if (currentCall == null || currentCall?.isExecuted == true) {
                val name = binding.searchPill.text.toString()
                pillRequest(name)
            }
        }
    }
    fun pillRequest(name : String) {
        //1.Retrofit 객체 초기화
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://apis.data.go.kr/")
            .addConverterFactory(ScalarsConverterFactory.create()) // 스칼라 변환기 추가
            .addConverterFactory(GsonConverterFactory.create(gson)) //gson 넣을지 말지
            .build()
        //2. 서비스 객체 생성
        val pillService:PillService = retrofit.create(PillService::class.java)
        //3. Call 객체 생성
        val apiKey = "Cf%2FfmKfKPh4xVEzDeyvrjXkWpf3w%2BBEWgMkulFHU4JDbTxGMJYlzDH1QeKWI%2FAqtRIib8w02NBybR0vZXHgUPA%3D%3D"
        val pillCall = pillService.getInfo(name,"","","","1","50","",apiKey)
        currentCall = pillCall
        if (dataList.isNotEmpty()) {
            dataList.clear()
        }
        pillCall.enqueue(object : Callback<PillInfo>{
                override fun onResponse(call: Call<PillInfo>, response: Response<PillInfo>) {
                    currentCall = null
                    if (response.isSuccessful){
                        val data = response.body()
                        val pillList = data?.body?.items
                        if (!pillList.isNullOrEmpty()) {
                            pillList.let { info ->
                                info.forEach {
                                    dataList.add(it)
                                }
                            }
                            adapter.notifyDataSetChanged()
                        }
                        Log.d("JSON_Response", response.body().toString())
                    }
                    else{
                        // 실패한 응답 처리
                        Log.e("JSON_Response", "Response not successful: ${response.code()}")
                        Log.e("JSON_Response", "Error body: ${response.errorBody()?.string()}")
                    }
                }
                override fun onFailure(call: Call<PillInfo>, t: Throwable) {
                    currentCall = null
                    Log.e("API_Request_Failure", "Error: ${t.message}", t)
                }
            })
    }
}