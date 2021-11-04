package com.example.poplibspart1.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GitHub {

    @GET("users")
    fun loadUsers(): Single<List<GithubUser>>
}