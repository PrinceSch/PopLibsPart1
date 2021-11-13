package com.example.poplibspart1.presenter

import com.example.poplibspart1.model.GitHubRepository
import com.example.poplibspart1.view.interfaces.RepositoryDetailView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoryDetailPresenter (private val router: Router) :
    MvpPresenter<RepositoryDetailView>() {

    fun loadData(repository: GitHubRepository){
        repository.name?.let { viewState.setName(it) }
        repository.description?.let { viewState.setDescription(it) }
        repository.created_at?.let { viewState.setCreatedDate(it) }
        repository.forks_count?.let { viewState.setForksCount(it) }
        repository.watchers_count?.let { viewState.setWatchersCount(it) }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}