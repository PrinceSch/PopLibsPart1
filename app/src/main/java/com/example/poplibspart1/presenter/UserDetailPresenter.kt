package com.example.poplibspart1.presenter

import com.example.poplibspart1.model.GithubUser
import com.example.poplibspart1.view.UserDetailView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailPresenter(
    private val router: Router, private val user: GithubUser
) :
    MvpPresenter<UserDetailView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
//        user.login?.let { viewState.setLogin(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}