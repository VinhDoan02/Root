package com.example.root.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.root.databinding.SearchItemBinding
import com.example.root.ui.viewmodel.SearchItemViewModel

class RecyclerViewAdapter(
    private val layoutId : Int
) : RecyclerView.Adapter<ItemViewHolder>() {

    private var items = mutableListOf<ItemViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater,layoutId,parent,false)

        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(itemViewModel : List<ItemViewModel>) {
        items = itemViewModel.toMutableList()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        items[holder.adapterPosition].let {holder.bind(it)}
        holder.binding.executePendingBindings()
    }

}

class ItemViewHolder(val binding : ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(itemViewModel: ItemViewModel) {
        if(binding is SearchItemBinding) {
            binding.viewModel = (itemViewModel as SearchItemViewModel)
        }
    }
}

interface ItemViewModel

data class RecyclerViewItemWrapper(
    @LayoutRes val layoutId: Int,
    val itemList : List<ItemViewModel> = emptyList()
)
