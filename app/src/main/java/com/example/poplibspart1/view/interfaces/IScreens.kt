package com.example.poplibspart1.view.interfaces

import com.example.poplibspart1.model.GitHubRepository
import com.example.poplibspart1.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userDetailScreen(user: GithubUser): Screen
    fun repoDetailScreen(repository: GitHubRepository): Screen
}