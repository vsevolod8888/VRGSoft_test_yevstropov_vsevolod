package com.example.vrgsoft_test_yevstropov_vsevolod
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vrgsoft_test_yevstropov_vsevolod.api.RedditApiService
import com.example.vrgsoft_test_yevstropov_vsevolod.api.retrofit
import com.example.vrgsoft_test_yevstropov_vsevolod.domain.RedditDomain
import com.example.vrgsoft_test_yevstropov_vsevolod.dto.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class Repozitory (){
    var listRedditdomainREP: MutableLiveData<List<RedditDomain>> = MutableLiveData()


    suspend fun refreshDataFromInternet(){
        withContext(Dispatchers.IO) {
            val ourApiService: RedditApiService = retrofit.create(RedditApiService::class.java)
            val requestCall = try {
                ourApiService.getListRedditApiDto()
            } catch (e: Exception) {
                Log.e("OOO", "${e.message}")
                null
            }
            if (requestCall?.isSuccessful == true) {
                val sentroutelistarchive = requestCall.body()
                sentroutelistarchive?.let {
                    listRedditdomainREP.postValue(asDomainModel(it)) // postValue это для бэкграунт потока в отличии от value
                }
                Log.d("OOO", "НОРМАЛЬНО")
            } else {
                Log.d("OOO", "ПЛОХО")
            }
        }
    }
}