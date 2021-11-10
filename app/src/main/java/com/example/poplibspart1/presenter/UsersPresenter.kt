package com.example.poplibspart1.presenter

import com.example.poplibspart1.model.GithubUser
import com.example.poplibspart1.model.GithubAPI
import com.example.poplibspart1.view.interfaces.IScreens
import com.example.poplibspart1.view.interfaces.UserItemView
import com.example.poplibspart1.view.interfaces.UsersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UsersPresenter(val uiScheduler: Scheduler,
                     private val usersRepo: GithubAPI,
                     private val router: Router,
                     val screens: IScreens
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let {view.loadAvatar(it)}
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(
                screens.userDetailScreen(usersListPresenter.users[itemView.pos]),
                true
            )
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}