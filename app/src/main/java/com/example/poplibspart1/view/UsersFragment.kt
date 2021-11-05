package com.example.poplibspart1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poplibspart1.App
import com.example.poplibspart1.databinding.FragmentUsersBinding
import com.example.poplibspart1.presenter.UsersPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter( AndroidSchedulers.mainThread(),
            App.instance.repository,
            App.instance.router, AndroidScreens()
        )
    }
    private lateinit var adapter: UsersRVAdapter

    private var _binding: FragmentUsersBinding? = null
    private val binding: FragmentUsersBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun init() {
        with(binding){
            rvUsers.layoutManager = LinearLayoutManager(context)
            adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
            rvUsers.adapter = adapter
        }

    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}