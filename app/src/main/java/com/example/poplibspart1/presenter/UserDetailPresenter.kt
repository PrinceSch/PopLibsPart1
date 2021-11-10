package com.example.poplibspart1.presenter

import com.example.poplibspart1.model.GitHubRepository
import com.example.poplibspart1.model.GithubAPI
import com.example.poplibspart1.model.GithubUser
import com.example.poplibspart1.view.interfaces.IRepoItemView
import com.example.poplibspart1.view.interfaces.IScreens
import com.example.poplibspart1.view.interfaces.UserDetailView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserDetailPresenter(
    private val uiScheduler: Scheduler,
    private val repository: GithubAPI,
    private val router: Router, val screens: IScreens
) :
    MvpPresenter<UserDetailView>() {

    val repositoryListPresenter = RepositoryListPresenter()

    class RepositoryListPresenter : IReposListPresenter {
        val reposList = mutableListOf<GitHubRepository>()
        override var itemClickListener: ((IRepoItemView) -> Unit)? = null

        override fun bindView(view: IRepoItemView) {
            val repository = reposList[view.pos]
            repository.name?.let { view.setName(it) }
            repository.description?.let { view.setDescription(it) }
        }

        override fun getCount(): Int = reposList.size

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        repositoryListPresenter.itemClickListener = { itemView ->
            router.navigateTo(
                screens.repoDetailScreen(repositoryListPresenter.reposList[itemView.pos]), true
            )
        }
    }

    fun loadData(user: GithubUser) {
        user.login?.let { viewState.setLogin(it) }
        user.avatarUrl?.let { viewState.setAvatar(it) }
        user.reposUrl?.let {
            repository.getRepos(it)
                .observeOn(uiScheduler)
                .subscribe({ repos ->
                    repositoryListPresenter.reposList.clear()
                    repositoryListPresenter.reposList.addAll(repos)
                    viewState.updateList()
                }, {
                    println("Error: ${it.message}")
                })
        }
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}