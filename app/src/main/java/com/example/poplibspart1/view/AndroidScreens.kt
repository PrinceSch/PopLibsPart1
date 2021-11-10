package com.example.poplibspart1.view

import android.os.Bundle
import com.example.poplibspart1.model.GitHubRepository
import com.example.poplibspart1.model.GithubUser
import com.example.poplibspart1.view.interfaces.IScreens
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

    override fun repoDetailScreen(repository: GitHubRepository): Screen {
        return FragmentScreen {
            RepositoryDetailFragment.newInstance(
                Bundle().apply {
                    putParcelable(RepositoryDetailFragment.BUNDLE_EXTRA, repository)
                }
            )
        }
    }
}