package com.example.poplibspart1.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GitHub {

    @GET("users")
    fun loadUsers(): Single<List<GithubUser>>

    @GET
    fun loadRepos(@Url url: String) : Single<List<GitHubRepository>>
}