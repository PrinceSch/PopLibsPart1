package com.example.poplibspart1.presenter

import com.example.poplibspart1.view.IItemView
import com.example.poplibspart1.view.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>