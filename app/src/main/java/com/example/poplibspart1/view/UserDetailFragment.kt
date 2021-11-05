package com.example.poplibspart1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.poplibspart1.App
import com.example.poplibspart1.databinding.FragmentUserDetailsBinding
import com.example.poplibspart1.model.GithubUser
import com.example.poplibspart1.presenter.UserDetailPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailFragment : MvpAppCompatFragment(), UserDetailView, BackButtonListener {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding: FragmentUserDetailsBinding
        get() {
            return _binding!!
        }

    private val userBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: GithubUser()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater)
        return binding.root
    }

    private val presenter: UserDetailPresenter by moxyPresenter {
        UserDetailPresenter(
            App.instance.router, userBundle
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLogin()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setLogin() {
        binding.detailLogin.text = userBundle.login
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