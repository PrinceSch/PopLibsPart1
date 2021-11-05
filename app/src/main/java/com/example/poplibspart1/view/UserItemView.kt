package com.example.poplibspart1.view

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}