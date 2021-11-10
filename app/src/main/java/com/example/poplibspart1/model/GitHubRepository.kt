package com.example.poplibspart1.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepository(
    @Expose val name: String? = null,
    @Expose val description: String? = null,
    @Expose val created_at: String? = null,
    @Expose val watchers_count: String? = null,
    @Expose val forks_count: String? = null
) : Parcelable
