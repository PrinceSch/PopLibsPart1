package com.example.poplibspart1.presenter

import com.example.poplibspart1.view.UserDetailView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserDetailPresenter(
    private val router: Router,
) :
    MvpPresenter<UserDetailView>() {
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}