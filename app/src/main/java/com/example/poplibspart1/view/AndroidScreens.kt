package com.example.poplibspart1.view

import android.os.Bundle
import com.example.poplibspart1.model.GithubUser
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun userDetailScreen(user: GithubUser): Screen {
        return FragmentScreen {
            UserDetailFragment.newInstance(
                Bundle().apply {
                    putParcelable(UserDetailFragment.BUNDLE_EXTRA, user)
                }
            )
        }
    }
}