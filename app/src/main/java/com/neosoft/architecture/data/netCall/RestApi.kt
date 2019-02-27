package com.neosoft.architecture.data.netCall

import com.neosoft.architecture.presentation.loginActivity.LoginModel
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Vijay on 26/2/19.
 */

interface RestApi {

    @FormUrlEncoded
    @POST("users/login")
    fun doLoginApi(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<LoginModel>

}