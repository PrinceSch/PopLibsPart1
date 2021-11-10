package com.example.poplibspart1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.poplibspart1.App
import com.example.poplibspart1.databinding.FragmentRepositoryDetailBinding
import com.example.poplibspart1.model.GitHubRepository
import com.example.poplibspart1.presenter.RepositoryDetailPresenter
import com.example.poplibspart1.view.interfaces.BackButtonListener
import com.example.poplibspart1.view.interfaces.RepositoryDetailView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryDetailFragment: MvpAppCompatFragment(), RepositoryDetailView, BackButtonListener {

    private var _binding: FragmentRepositoryDetailBinding? = null
    private val binding: FragmentRepositoryDetailBinding
        get() {
            return _binding!!
        }
    private var repoBundle: GitHubRepository = GitHubRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryDetailBinding.inflate(inflater)
        return binding.root
    }

    private val presenter: RepositoryDetailPresenter by moxyPresenter {
        RepositoryDetailPresenter(App.instance.router)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repoBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: GitHubRepository()
        repoBundle.let { presenter.loadData(it) }
    }

    companion object {
        const val BUNDLE_EXTRA = "repository"

        fun newInstance(bundle: Bundle): RepositoryDetailFragment {
            val fragment = RepositoryDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun setName(name: String) {
        binding.detailRepoName.text = name
    }

    override fun setDescription(desc: String) {
        binding.detailRepoDescription.text = desc
    }

    override fun setCreatedDate(date: String) {
        binding.detailRepoCreated.text = "Дата создания: ${date}"
    }

    override fun setForksCount(count: String) {
        binding.detailRepoForks.text = "Количество форков: ${count}"
    }

    override fun setWatchersCount(count: String) {
        binding.detailRepoWatchers.text = "Просмотров: ${count}"
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}