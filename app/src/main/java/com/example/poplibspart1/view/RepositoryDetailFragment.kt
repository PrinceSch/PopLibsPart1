package com.example.poplibspart1.view

import android.os.Bundle
import moxy.MvpAppCompatFragment

class RepositoryDetailFragment: MvpAppCompatFragment() {

    companion object {
        const val BUNDLE_EXTRA = "repository"

        fun newInstance(bundle: Bundle): RepositoryDetailFragment {
            val fragment = RepositoryDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}