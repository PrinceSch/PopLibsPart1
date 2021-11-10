package com.example.poplibspart1.view.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailView : MvpView{
    fun init()
    fun updateList()
    fun setLogin(login: String)
    fun setAvatar(url: String?)
}