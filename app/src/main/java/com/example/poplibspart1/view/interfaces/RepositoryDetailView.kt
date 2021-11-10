package com.example.poplibspart1.view.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepositoryDetailView : MvpView {
    fun setName(name: String)
    fun setDescription (desc: String)
    fun setCreatedDate(date: String)
    fun setForksCount(count: String)
    fun setWatchersCount(count: String)
}