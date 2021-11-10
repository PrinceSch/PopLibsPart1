package com.example.poplibspart1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibspart1.App
import com.example.poplibspart1.databinding.FragmentUserDetailsBinding
import com.example.poplibspart1.model.GithubUser
import com.example.poplibspart1.presenter.UserDetailPresenter
import com.example.poplibspart1.view.interfaces.BackButtonListener
import com.example.poplibspart1.view.interfaces.UserDetailView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailFragment : MvpAppCompatFragment(), UserDetailView, BackButtonListener {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding: FragmentUserDetailsBinding
        get() {
            return _binding!!
        }

    private var userBundle: GithubUser = GithubUser()
    private val imageLoader = GlideImageLoader()
    lateinit var adapter: RepositoryAdapter

    private val presenter: UserDetailPresenter by moxyPresenter {
        UserDetailPresenter(
            AndroidSchedulers.mainThread(),
            App.instance.repository,
            App.instance.router, AndroidScreens()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: GithubUser()
        userBundle.let { presenter.loadData(it) }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun init() {
        with(binding){
            recyclerDetail.layoutManager = LinearLayoutManager(context)
            adapter = RepositoryAdapter(presenter.repositoryListPresenter)
            recyclerDetail.adapter = adapter
        }
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun setLogin(login: String) {
        binding.detailLogin.text = login
    }

    override fun setAvatar(url: String?) {
        imageLoader.loadInto(url, binding.detailAvatar)
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    companion object {
        const val BUNDLE_EXTRA = "user"

        fun newInstance(bundle: Bundle): UserDetailFragment {
            val fragment = UserDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}