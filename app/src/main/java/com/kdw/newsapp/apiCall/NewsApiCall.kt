package com.kdw.newsapp.apiCall


import android.util.Log
import com.kdw.newsapp.util.NetworkResult
import retrofit2.Response
import java.io.IOException

open class NewsApiCall {
    suspend fun <T: Any> safeApiCall(call: suspend() -> Response<T>, exception: String): T? {
        val ans = networkResponse(call ,exception)
        var output: T? = null

        when(ans) {
            is NetworkResult.Success ->
                output = ans.data
            is NetworkResult.Error ->
                Log.e("error", "The $exception is due to ${ans.exception}")
        }

        return output
    }

    private suspend fun <T: Any> networkResponse(call: suspend() -> Response<T>, exception: String) : NetworkResult<T> {
        val res = call.invoke()
        val msg = "Error invokes due to "

        if(res.isSuccessful) {
            res.body()?.let { responseBody ->
                return NetworkResult.Success(responseBody)
            }
        }
        return NetworkResult.Error(IOException("$msg $exception"))

    }
}