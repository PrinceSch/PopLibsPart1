package com.example.poplibspart1.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poplibspart1.databinding.ItemRepositoryBinding
import com.example.poplibspart1.presenter.IReposListPresenter
import com.example.poplibspart1.view.interfaces.IRepoItemView

class RepositoryAdapter(val presenter: IReposListPresenter) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemRepositoryBinding.inflate(
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

    inner class ViewHolder(private val viewBinding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(viewBinding.root), IRepoItemView {
        override fun setName(name: String) = with (viewBinding){
            repoName.text = name
        }
        override fun setDescription(desc: String) = with (viewBinding){
            repoDescription.text = desc
        }
        override var pos = -1
    }
}