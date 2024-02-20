package com.example.apiapplication

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

//apikey = BuildConfig.MY_KEY 엥 안됨
class MainActivity : AppCompatActivity() {
    val apikey = BuildConfig.MY_KEY
    lateinit var binding : ActivityMainBinding
    private val dataList = mutableListOf<PillInfo.Body.Item?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = MyAdapter(dataList)
        binding.btnData.setOnClickListener{
            pillRequest()
        }
    }
    fun pillRequest() {
        //1.Retrofit 객체 초기화
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://apis.data.go.kr/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson)) //gson 넣을지 말지
            .build()
        //2. 서비스 객체 생성
        //val apiService: PillService = retrofit.create(PillService::class.java)
        val pillService:PillService? = retrofit.create(PillService::class.java)
        //3. Call 객체 생성
        //val pillname = 입력값
/*        val pillCall = apiService.getpillinfo(
            apikey,
            "타이레놀",
            "json"
        )
        if (!dataList.isEmpty()) {
            dataList.clear()
        }
        pillCall.enqueue(object : Callback<PillData> {
            override fun onResponse(call: Call<PillData>, response: Response<PillData>) {
                val data = response.body()
                val pillinfo = data?.body?.items
                Log.d("Allyak", "통신여부코드: "+response.code())
                if(response.isSuccessful()) {
                    Log.d("Allyak", "성공 코드: "+response.code())
                    if (!pillinfo.isNullOrEmpty()) {
                        pillinfo?.let { info ->
                            info.forEach {
                                dataList.add(it)
                            }
                        }
                        (binding.recyclerview.adapter as MyAdapter).notifyDataSetChanged()
                    }
                }else{
                    Log.d("Allyak", "실패 코드: "+response.code())
                }
            }
            override fun onFailure(call: Call<PillData>, t: Throwable) {
                Log.d("Allyak", "실패")
                call.cancel()
            }
        })*/
        if (!dataList.isEmpty()) {
            dataList.clear()
        }
        pillService?.getInfo(apikey, "json", "타이레놀")
            ?.enqueue(object : Callback<PillInfo>{
                override fun onResponse(call: Call<PillInfo>, response: Response<PillInfo>) {
                    val pillList = response.body()?.body?.items
                    Log.d("Allyak", "통신여부코드: " + response.code())
                    if (response.isSuccessful()) {
                        Log.d("Allyak", "성공 코드: " + response.code())
                        if (!pillList.isNullOrEmpty()) {
                            pillList?.let { info ->
                                info.forEach {
                                    dataList.add(it)
                                }
                            }
                            (binding.recyclerview.adapter as MyAdapter).notifyDataSetChanged()
                        }
                    } else {
                        Log.d("Allyak", "실패 코드: " + response.code())
                    }
                }
                override fun onFailure(call: Call<PillInfo>, t: Throwable) {
                    Log.d("AllyakE", t.message!!)
                }
            })
    }
}