package com.gdsc.todo.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder<T: ViewDataBinding> (val binding: T) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup, layoutId: Int) : this(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
    )
}