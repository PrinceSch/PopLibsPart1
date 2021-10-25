package com.example.poplibspart1.model

class GithubUsersRepo {
    private val repositories = (0..100).map {
        GithubUser("login $it")
    }

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}
