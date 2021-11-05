package com.example.poplibspart1.view

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}