package com.example.poplibspart1.presenter

import com.example.poplibspart1.model.GithubUser
import com.example.poplibspart1.model.GithubUsersRepo
import com.example.poplibspart1.view.IScreens
import com.example.poplibspart1.view.UserItemView
import com.example.poplibspart1.view.UsersView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    val screens: IScreens
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = (1..20).map { GithubUser("login $it") }.toMutableList()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login!!)
        }
    }

    val usersListPresenter = UsersListPresenter()

    private val disposable = usersRepo.getUsers()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(it)
            viewState.updateList()
        }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(
                screens.userDetailScreen(usersListPresenter.users[itemView.pos]),
                true
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}