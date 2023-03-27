package com.example.photoweather.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding>(@LayoutRes contentLayout: Int) :
    Fragment(contentLayout) {

    protected abstract val viewModel: VM
    protected abstract val binding: VB

    protected abstract fun observeViewModel()
    protected abstract fun initView()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        initView()
    }
}
