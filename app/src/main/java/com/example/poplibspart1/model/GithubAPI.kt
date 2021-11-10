package com.example.poplibspart1.model

import io.reactivex.rxjava3.core.Observable

class GithubAPI {
    private val client = RetrofitKeeper().api

    fun getUsers(): Observable<List<GithubUser>> = client.loadUsers().toObservable()

    fun getRepos(url: String): Observable<List<GitHubRepository>> = client.loadRepos(url).toObservable()

}
