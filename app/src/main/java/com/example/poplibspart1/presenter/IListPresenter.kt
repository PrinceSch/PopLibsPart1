package com.example.poplibspart1.presenter

import com.example.poplibspart1.view.interfaces.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}