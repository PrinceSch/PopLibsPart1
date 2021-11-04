package com.example.poplibspart1.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val client = RetrofitKeeper().api

    fun getUsers(): Observable<List<GithubUser>> = client.loadUsers().toObservable()

}
