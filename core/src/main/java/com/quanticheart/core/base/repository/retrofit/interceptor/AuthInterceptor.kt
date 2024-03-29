package com.quanticheart.core.base.repository.retrofit.interceptor

import android.util.Log
import com.quanticheart.core.base.repository.retrofit.HttpConstants
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Api-Token", HttpConstants.authKey)
        val request = requestBuilder.build()
        val response = chain.proceed(request)
        if (response.code == 401) {
            Log.e("Auth", "Error API KEY")
        }
        return response
    }
}