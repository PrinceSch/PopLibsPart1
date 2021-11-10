package com.example.poplibspart1.view.interfaces

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}