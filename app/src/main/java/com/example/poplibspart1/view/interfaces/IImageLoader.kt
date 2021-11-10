package com.example.poplibspart1.view.interfaces

interface IImageLoader<T> {
    fun loadInto(url: String?, container: T)
}