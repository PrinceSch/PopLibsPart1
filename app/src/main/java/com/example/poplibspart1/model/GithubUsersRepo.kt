package com.example.poplibspart1.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val repositories = (0..100).map {
        GithubUser("login $it")
    }

    fun getUsers(): Observable<List<GithubUser>> {
        return Observable.just(repositories)
    }
}
