package com.example.poplibspart1.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.poplibspart1.databinding.ItemUserBinding
import com.example.poplibspart1.presenter.IUserListPresenter
import com.example.poplibspart1.view.interfaces.IImageLoader
import com.example.poplibspart1.view.interfaces.UserItemView

class UsersRVAdapter(val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val viewBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(viewBinding.root), UserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(viewBinding) {
            userLogin.text = text
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadInto(url, viewBinding.userAvatar)
        }
    }
}